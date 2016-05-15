package com.firstgame.main;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject
{
   private static Color enemyColour = Color.white;

   private Random r = new Random();

   private Handler handler;


   public EnemyBossBullet(int x, int y, ID id, Handler handler)
   {
      super(x, y, id);

      velY = r.nextInt(6) - 1;
      velX = r.nextInt(10) - 5;

      while( ((int)velX) == 0 )
      {
         velX = r.nextInt(6) - 1;
      }
      while( ((int)velY) == 0 )
      {
         velY = r.nextInt(6) - 1;
      }

      this.handler = handler;

      accX = 0;
      accY = 0;

      xSize = 16;
      ySize = 16;
   }

   @Override
   public Rectangle getBounds()
   {
      return new Rectangle((int)x, (int)y, xSize, ySize);
   }

   @Override
   public void tick()
   {
      x += velX;
      y += velY;

      if( (y<= 0) || (y >= Game.HEIGHT - ySize) ) Game.removeQue.add(this);
      if( (x<= 0) || (x >= Game.WIDTH - xSize) ) Game.removeQue.add(this);
   }

   @Override
   public void render(Graphics g)
   {
      g.setColor(enemyColour);

      g.fillOval((int)x, (int)y, xSize, ySize);
   }
}
