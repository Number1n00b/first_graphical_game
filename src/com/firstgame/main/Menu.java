package com.firstgame.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter
{
   private Game game;

   private Handler handler;

   public Menu(Game game, Handler handler)
   {
      this.game = game;
      this.handler = handler;
   }

   public void mousePressed(MouseEvent e)
   {
      int mx = e.getX();
      int my = e.getY();

      if(Game.gameState != Game.STATE.Game) {
         //Play Button
         if (mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 3, 200, 64)) {
            Game.gameState = Game.STATE.Game;
         }

         //Help button
         if (mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 150, 200, 64)) {
            Game.gameState = Game.STATE.Help;
         }

         if (Game.gameState == Game.STATE.Help)
         {
            //Back button for help
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 300, 200, 64)) {
               Game.gameState = Game.STATE.Menu;
            }
         }
         else if( Game.gameState == Game.STATE.Game_Over )
         {
            //Restart button.
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 300, 200, 64)) {
               Game.reset();
               Game.gameState = Game.STATE.Menu;
            }
         }
         else if( Game.gameState == Game.STATE.Menu )
         {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 300, 200, 64)) {
               System.exit(0);
            }

            if( mouseOver(mx, my, 10, Game.HEIGHT - 45, 100, 40) )
            {
               //remove all objects, and re-add the player.. then reset the game.
               for( GameObject o : handler.object )
               {
                  Game.removeQue.add(o);
               }

               Game.reset();
            }
         }
         else if( Game.gameState == Game.STATE.Win )
         {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 300, 200, 64)) {
               Game.gameState = Game.STATE.Menu;
            }
         }

      }

   }

   public void mouseReleased(MouseEvent e)
   {
      //empty for now
   }

   private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
   {
      if( mx > x && mx < x+width )
      {
         if(my > y && my < y+height)
         {
            return true;
         }
      }

      return false;
   }

   public void render(Graphics g)
   {
      if(Game.gameState == Game.STATE.Menu) {
         Font font = new Font("arial", 1, 50);
         Font font2 = new Font("arial", 1, 30);

         g.setFont(font);
         g.setColor(Color.white);
         g.drawString("Menu", Game.WIDTH / 2 - 70, 150);

         g.setFont(font2);
         g.setColor(Color.white);
         g.drawString("Play", Game.WIDTH / 2 - 35, Game.HEIGHT / 3 + 40);

         g.setColor(Color.white);
         g.drawString("Help", Game.WIDTH / 2 - 35, Game.HEIGHT / 3 + 190);

         g.setColor(Color.white);
         g.drawString("Quit", Game.WIDTH / 2 - 35, Game.HEIGHT / 3 + 340);

         if( game.hud.getScore() == 0 )
         {
            g.setColor(Color.green);
         }
         else
         {
            g.setColor(Color.red);
         }
         g.drawString("Reset", 15, Game.HEIGHT - 15);

         g.setColor(Color.white);
         g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 3, 200, 64);

         g.setColor(Color.white);
         g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 150, 200, 64);

         g.setColor(Color.white);
         g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 300, 200, 64);

         if( game.hud.getScore() == 0 )
         {
            g.setColor(Color.green);
         }
         else
         {
            g.setColor(Color.red);
         }
         g.drawRect(10, Game.HEIGHT - 45, 100, 40);
      }
      else if (Game.gameState == Game.STATE.Help)
      {
         Font font = new Font("arial", 1, 50);
         Font font2 = new Font("arial", 1, 30);
         Font font3 = new Font("arial", 1, 20);

         g.setFont(font);
         g.setColor(Color.white);
         g.drawString("Help", Game.WIDTH / 2 - 70, 150);

         g.setFont(font2);
         g.drawString("Back", Game.WIDTH / 2 - 35, Game.HEIGHT / 3 + 340);
         g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 300, 200, 64);

         g.setFont(font3);
         g.drawString("Use WASD keys to move the blue cube and dodge enemies!", 250, 380);
      }
      else if (Game.gameState == Game.STATE.Game_Over)
      {
         Font font = new Font("arial", 1, 80);
         Font font2 = new Font("arial", 1, 30);

         g.setFont(font);
         g.setColor(Color.RED);
         g.drawString("!!!GAME OVER!!!", Game.WIDTH / 2 - 320, 150);


         g.setFont(font2);
         g.drawString("Retry", Game.WIDTH / 2 - 35, Game.HEIGHT / 3 + 340);
         g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 300, 200, 64);
      }
      else
      {
         Font font = new Font("arial", 1, 80);
         Font font2 = new Font("arial", 1, 30);

         g.setFont(font);
         g.setColor(Color.GREEN);
         g.drawString("!!!YOU WIN!!!", Game.WIDTH / 2 - 260, 150);


         g.setFont(font2);
         g.drawString("Retry", Game.WIDTH / 2 - 35, Game.HEIGHT / 3 + 340);
         g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 300, 200, 64);
      }
   }

   public void tick()
   {

   }


}
