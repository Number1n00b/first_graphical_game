package com.firstgame.main;


import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Player extends GameObject
{
   private Color playerColour;

   private boolean collision = false;

   private Handler handler;

   private Random r = new Random();

   private int shootDelay = 0;
   private int MAX_DELAY = 5;

   public Player(int x, int y, ID id, Handler handler)
   {
      super(x, y, id);

      this.handler = handler;

      this.setSpeed(Game.PLAYER_BASE_SPEED);

      xSize = 42;
      ySize = 42;

      if(id == ID.Player)
      {
         playerColour = Color.blue;
      }
      else if(id == ID.Player_Demo)
      {
         playerColour = Color.GRAY;
      }
      else
      {
         playerColour = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
      }
   }

   @Override
   public Rectangle getBounds()
   {
      return new Rectangle((int)x, (int)y, xSize, ySize);
   }

   @Override
   public void tick()
   {
      shootDelay--;
      shootDelay = (int)Game.clamp((float)shootDelay, 0, MAX_DELAY);

      x += velX;
      y += velY;

      x = Game.clamp(x, 0, Game.WIDTH - xSize);
      y = Game.clamp(y, 0, Game.HEIGHT - ySize);

      if(id == ID.Player_Demo)
      {
         Game.menuObjQueue.add(new Trail((int)x, (int)y, playerColour, xSize, ySize, 0.03f, ID.Trail, handler));
      }
      else
      {
         Game.objQueue.add(new Trail((int)x, (int)y, playerColour, xSize, ySize, 0.12f, ID.Trail, handler));
      }

      collision();
   }

   private void collision()
   {
      try{
      for(GameObject temp : handler.object)
      {
         if(collision) {
            if (this.getBounds().intersects(temp.getBounds())) {
               if (temp.getId() == ID.BossEnemy) 
               {
                  Game.hud.incrementHealth(-1000);
               }
            }
            if (Game.isEnemy(temp.getId())) {
               //collision code!
               if (this.getBounds().intersects(temp.getBounds())) {
                  if (temp.getId() == ID.SmartEnemy) {
                     Game.hud.incrementHealth(-10);
                  } else if (temp.getId() == ID.EnemyBossBullet) {
                     Game.hud.incrementHealth(-50);
                     Game.removeQue.add(temp);
                  } else if (temp.getId() == ID.HealthUp) {
                     Game.hud.incrementHealth(100);
                     Game.removeQue.add(temp);
                  } else {
                     Game.hud.incrementHealth(-2);
                  }
               }
            }
         }
      }
      }catch(Exception e){};
   }


   public void shoot(boolean[] dir)
   {
      boolean UP = dir[0];
      boolean DOWN = dir[1];
      boolean LEFT = dir[2];
      boolean RIGHT = dir[3];

      if( shootDelay <= 0 )
      {
         shootDelay = MAX_DELAY;

         int sp = 7;
         int diagSp = (int)Math.round(Math.sqrt( (sp*sp)/2 ));

         float localX = x + xSize/2 - 8;
         float localY = y + ySize/2 - 8;

         LinkedList<GameObject> objQueue;

         if(id == ID.Player_Demo)
         {
            objQueue = Game.menuObjQueue;
         }
         else
         {
            objQueue = Game.objQueue;
         }

         if( UP && LEFT )
         {
            objQueue.add(new PlayerBullet((int)localX, (int)localY, -diagSp, -diagSp, ID.PlayerBullet, handler, playerColour));
         }
         else if( UP && RIGHT )
         {
            objQueue.add(new PlayerBullet((int)localX, (int)localY, diagSp, -diagSp, ID.PlayerBullet, handler, playerColour));
         }
         else if( DOWN && LEFT )
         {
            objQueue.add(new PlayerBullet((int)localX, (int)localY, -diagSp, diagSp, ID.PlayerBullet, handler, playerColour));
         }
         else if( DOWN && RIGHT )
         {
            objQueue.add(new PlayerBullet((int)localX, (int)localY, diagSp, diagSp, ID.PlayerBullet, handler, playerColour));
         }
         else if( UP )
         {
            objQueue.add(new PlayerBullet((int)localX, (int)localY, 0, -sp, ID.PlayerBullet, handler, playerColour));
         }
         else if( DOWN )
         {
            objQueue.add(new PlayerBullet((int)localX, (int)localY, 0, sp, ID.PlayerBullet, handler, playerColour));
         }
         else if( LEFT )
         {
            objQueue.add(new PlayerBullet((int)localX, (int)localY, -sp, 0, ID.PlayerBullet, handler, playerColour));
         }
         else if( RIGHT )
         {
            objQueue.add(new PlayerBullet((int)localX, (int)localY, sp, 0, ID.PlayerBullet, handler, playerColour));
         }
      }
   }


   @Override
   public void render(Graphics g)
   {
      g.setColor(playerColour);

      g.fillRect((int)x, (int)y, xSize, ySize);
   }

}
