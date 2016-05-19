package com.firstgame.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
   public static final int WIDTH = 960;
   public static final int HEIGHT = WIDTH / 12 * 9; //810
   public static final int PLAYER_BASE_SPEED = 10;
   public static final Color PLAYER_ONE_COLOR = Color.blue;
   private Color BACKGROUND_COLOUR = Color.black;

   private Thread thread;
   private boolean running = false;

   private Random r = new Random();

   private Handler handler;
   private Handler menuHandler;

   public static HUD hud;
   private static Spawn spawner;

   private Menu menu;

   public static enum STATE
   {
      Menu,
      Help,
      Game_Over,
      Win,
      Game
   }

   public static STATE gameState = STATE.Menu;

   public static LinkedList<GameObject> objQueue;
   public static LinkedList<GameObject> removeQue;

   public static LinkedList<GameObject> menuObjQueue;
   public static LinkedList<GameObject> menuRemoveQueue;

   public int num_enemys = 15;

   Player demo;

   /**
    * Set up the game state here.
    */
   public Game()
   {
      objQueue = new LinkedList<GameObject>();
      removeQue = new LinkedList<GameObject>();

      menuObjQueue = new LinkedList<GameObject>();
      menuRemoveQueue = new LinkedList<GameObject>();

      //Create hud and handler.
      handler = new Handler();
      menuHandler = new Handler();

      hud = new HUD(200);
      spawner = new Spawn(handler, hud);

      menu = new Menu(this, handler);

      //Initialise user input.
      this.addKeyListener(new KeyInput(handler));
      this.addMouseListener(menu);

      this.addKeyListener(new KeyInput(menuHandler));

      //Create the game window.
      new Window(WIDTH + 5, HEIGHT+10, "My game!", this);

      //handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));

      //Add demo player to game.
      menuHandler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player_Demo, menuHandler));
   }

   public synchronized void start()
   {
      thread = new Thread(this);

      System.out.println("Game started.");

      thread.start();

      running = true;
   }

   public void stop()
   {
      System.out.println("Stopping game.");
      try
      {
         thread.join();
         running = false;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }



   /**
    * Updates the game state.
    */
   private void tick()
   {
      if(gameState == STATE.Game)
      {
         handler.tick();
         hud.tick();

         spawner.tick();
         if(hud.getHealth() == 0)
         {
            gameState = STATE.Game_Over;
            //delete all objects!
            for(GameObject obj : handler.object)
            {
               removeQue.add(obj);
            }
         }

         while(objQueue.size() > 0)
         {
            handler.addObject(objQueue.peekFirst());
            objQueue.removeFirst();
         }

         while(removeQue.size() > 0)
         {
            handler.removeObject(removeQue.peekFirst());
            removeQue.removeFirst();
         }
      }
      else
      {
         menuHandler.tick();

         while(menuObjQueue.size() > 0)
         {
            menuHandler.addObject(menuObjQueue.peekFirst());
            menuObjQueue.removeFirst();
         }

         while(menuRemoveQueue.size() > 0)
         {
            menuHandler.removeObject(menuRemoveQueue.peekFirst());
            menuRemoveQueue.removeFirst();
         }

         menu.tick();
      }

   }

   /**
    * Render objects.
    */
   private void render()
   {
      BufferStrategy bs = this.getBufferStrategy();

      if(bs == null)
      {
         this.createBufferStrategy(3);
      }
      else
      {
         Graphics g = bs.getDrawGraphics();
         //Draw background first.
         g.setColor(BACKGROUND_COLOUR);
         g.fillRect(0, 0, WIDTH, HEIGHT);

         if(gameState == STATE.Game)
         {
            //Draw GameObjects
            handler.render(g);

            //Draw HUD on top.
            hud.render(g);
         }
         else
         {
            menuHandler.render(g);
            menu.render(g);
         }

         g.dispose();
         bs.show();
      }

   }

   /**
    * Sets a given variable to within the given bounds.
    *
    * @param var The value which to clamp.
    * @param min The minimum possible value for var.
    * @param max The maximum possible value of var.
    * @return int The clamped value for var.
    */
   public static float clamp(float var, int min, int max)
   {
      if(var >= max)
      {
         return max;
      }
      else if (var <= min)
      {
         return min;
      }
      else
      {
         return var;
      }
   }

   public static int randomPoint(char dim)
   {
      Random r = new Random();
      int min, max;

      if(dim == 'w')
      {
         min = 0;
         max = WIDTH - 100;
      }
      else
      {
         min = 0;
         max = HEIGHT - 100;
      }

      return r.nextInt(max) + min;
   }

   public static boolean isEnemy(ID id)
   {
      return ( id != ID.Player && id != ID.Player_Demo && id != ID.Trail && id != ID.PlayerBullet && id != ID.BossEnemy);
   }

   public static void reset()
   {
      hud.setLevel(0);
      hud.setScore(0);
      hud.incrementHealth(200);

      spawner.setKeep(250);
   }

   public static void resetAll()
   {
      hud.setLevel(0);
      hud.setScore(0);
      hud.incrementHealth(200);

      spawner.setKeep(250);


   }

   public void run()
   {
      this.requestFocus();

      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0;

      long timer = System.currentTimeMillis();
      int frames = 0;

      while(running)
      {
         //System.out.println("while running");
         long now = System.nanoTime();
         delta += (now - lastTime) / ns;

         lastTime = now;
         while(delta >= 1)
         {
            tick();
            delta--;
         }
         if(running)
         {
            render();
         }

         frames++;

         if(System.currentTimeMillis() - timer > 1000)
         {
            timer += 1000;
            //System.out.println("FPS: " + frames);
            for(GameObject temp : handler.object)
            {
               //todo - remove this debug if needed
               /*if( temp.id == ID.Player )
               {
                  System.out.println("Player pos: (" + temp.getX() + ", " + temp.getY() + ")");
                  System.out.println("Player velocity: (" + temp.getVelX() + ", " + temp.getVelY() + ")\n");
               }*/
            }
            frames = 0;
         }
      }
   }//end of run()


   /**
    * Creates the game.
    * @param args n/a
    */
   public static void main(String[] args)
   {
      System.out.println("Starting game!");
      new Game();
   }

}
