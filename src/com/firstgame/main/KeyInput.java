package com.firstgame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{

   private Handler handler;

   private boolean[] keyDown;
   private boolean[] fireKeyDown;

   public KeyInput(Handler handler) {
      super();

      this.handler = handler;

      keyDown = new boolean[4];
      fireKeyDown = new boolean[4];

      for(boolean b : keyDown)
      {
         b = false;
      }
   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();

      for(GameObject temp : handler.object)
      {
         if(temp.getId() == ID.Player && Game.gameState == Game.STATE.Game)
         {
            //key events for player1
            if(key == KeyEvent.VK_W) { temp.setVelY(-temp.getSpeed()); keyDown[0] = true; }
            if(key == KeyEvent.VK_S) { temp.setVelY(temp.getSpeed()); keyDown[1] = true; }
            if(key == KeyEvent.VK_A) { temp.setVelX(-temp.getSpeed()); keyDown[2] = true; }
            if(key == KeyEvent.VK_D) { temp.setVelX(temp.getSpeed()); keyDown[3] = true; }

            if(key == KeyEvent.VK_UP) { fireKeyDown[0] = true; }
            if(key == KeyEvent.VK_DOWN) { fireKeyDown[1] = true; }
            if(key == KeyEvent.VK_LEFT) { fireKeyDown[2] = true; }
            if(key == KeyEvent.VK_RIGHT) { fireKeyDown[3] = true; }

            ((Player)temp).shoot(fireKeyDown);
         }
         else if(temp.getId() == ID.Player_Demo && Game.gameState != Game.STATE.Game)
         {
            //key events for playerDEMO
            if(key == KeyEvent.VK_W) { temp.setVelY(-temp.getSpeed()); keyDown[0] = true; }
            if(key == KeyEvent.VK_S) { temp.setVelY(temp.getSpeed()); keyDown[1] = true; }
            if(key == KeyEvent.VK_A) { temp.setVelX(-temp.getSpeed()); keyDown[2] = true; }
            if(key == KeyEvent.VK_D) { temp.setVelX(temp.getSpeed()); keyDown[3] = true; }

            if(key == KeyEvent.VK_UP) { fireKeyDown[0] = true; }
            if(key == KeyEvent.VK_DOWN) { fireKeyDown[1] = true; }
            if(key == KeyEvent.VK_LEFT) { fireKeyDown[2] = true; }
            if(key == KeyEvent.VK_RIGHT) { fireKeyDown[3] = true; }

            ((Player)temp).shoot(fireKeyDown);
         }

      }

      //todo - remove this later, maybe?
      if(key == KeyEvent.VK_ESCAPE)
      {
         if( Game.gameState != Game.STATE.Menu )
         {
            Game.gameState = Game.STATE.Menu;
         }
      }
   }

   @Override
   public void keyReleased(KeyEvent e)
   {
      int key = e.getKeyCode();

      for(GameObject temp : handler.object)
      {
         if(temp.getId() == ID.Player && Game.gameState == Game.STATE.Game)
         {
            //key events for player
            if(key == KeyEvent.VK_W)   keyDown[0] = false; //temp.setVelY(0);
            if(key == KeyEvent.VK_S)   keyDown[1] = false; //temp.setVelY(0);
            if(key == KeyEvent.VK_A)   keyDown[2] = false; //temp.setVelX(0);
            if(key == KeyEvent.VK_D)   keyDown[3] = false; //temp.setVelX(0);

            if(key == KeyEvent.VK_UP) { fireKeyDown[0] = false; }
            if(key == KeyEvent.VK_DOWN) { fireKeyDown[1] = false; }
            if(key == KeyEvent.VK_LEFT) { fireKeyDown[2] = false; }
            if(key == KeyEvent.VK_RIGHT) { fireKeyDown[3] = false; }

            if(!keyDown[0] && !keyDown[1])
            {
               temp.setVelY(0);
            }
            if(!keyDown[2] && !keyDown[3])
            {
               temp.setVelX(0);
            }

         }
         else if(temp.getId() == ID.Player_Demo && Game.gameState != Game.STATE.Game)
         {
            //key events for playerDEMO
            if(key == KeyEvent.VK_W)   keyDown[0] = false; //temp.setVelY(0);
            if(key == KeyEvent.VK_S)   keyDown[1] = false; //temp.setVelY(0);
            if(key == KeyEvent.VK_A)   keyDown[2] = false; //temp.setVelX(0);
            if(key == KeyEvent.VK_D)   keyDown[3] = false; //temp.setVelX(0);

            if(key == KeyEvent.VK_UP) { fireKeyDown[0] = false; }
            if(key == KeyEvent.VK_DOWN) { fireKeyDown[1] = false; }
            if(key == KeyEvent.VK_LEFT) { fireKeyDown[2] = false; }
            if(key == KeyEvent.VK_RIGHT) { fireKeyDown[3] = false; }

            if(!keyDown[0] && !keyDown[1])
            {
               temp.setVelY(0);
            }
            if(!keyDown[2] && !keyDown[3])
            {
               temp.setVelX(0);
            }
         }

      }
   }
}
