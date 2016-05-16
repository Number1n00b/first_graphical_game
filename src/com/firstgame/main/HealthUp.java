package com.firstgame.main;

import java.awt.*;
import java.util.Random;

public class HealthUp extends GameObject
{
   private static Color col = new Color(255,105,180);

   private Random r = new Random();

   private Handler handler;

   public HealthUp(int x, int y, ID id, Handler handler)
   {
      super(x, y, id);

      velX = 0;
      velY = 0;

      this.handler = handler;

      accX = 0;
      accY = 0;

      xSize = 10;
      ySize = 10;
   }

   @Override
   public Rectangle getBounds()
   {
      return new Rectangle((int)x, (int)y, xSize, ySize);
   }

   @Override
   public void tick() {
      x += velX;
      y += velY;

      if ((y <= 0) || (y >= Game.HEIGHT - ySize)) velY *= -1;
      if ((x <= 0) || (x >= Game.WIDTH - xSize)) velX *= -1;

      //Game.objQueue.add(new Trail((int)x, (int)y, enemyColour, xSize, ySize, 0.1f, ID.Trail, handler));
      
      collision();
   }

   private void collision()
   {
      for(GameObject temp : handler.object)
      {
         if(Game.isEnemy(temp.getId()))
         {
            //collision code!
            if(this.getBounds().intersects(temp.getBounds()))
            {
               if(temp.getId() == ID.SmartEnemy)
               {
                  Game.hud.incrementHealth(-10);
                  Game.removeQue.add(this);
               }
               if(temp.getId() == ID.BaiscEnemy)
               {
                  Game.removeQue.add(this);
               }
            }
         }
      }
   }

   @Override
   public void render(Graphics g)
   {
      g.setColor(col);

      g.fillRect((int)x, (int)y, xSize, ySize);
   }
}

