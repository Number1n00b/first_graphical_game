package com.firstgame.main;

import java.awt.*;
import java.util.Random;

public class FastEnemy extends GameObject
{
   private static Color enemyColour = Color.orange;

   private Random r = new Random();

   private Handler handler;

   public FastEnemy(int x, int y, ID id, Handler handler)
   {
      super(x, y, id);

      velX = 12;
      velY = 12;

      this.handler = handler;

      accX = 0;
      accY = 0;

      xSize = 25;
      ySize = 25;
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

      if( (y<= 0) || (y >= Game.HEIGHT - ySize) ) velY *= -1;
      if( (x<= 0) || (x >= Game.WIDTH - xSize) ) velX *= -1;

      Game.objQueue.add(new Trail((int)x, (int)y, enemyColour, xSize, ySize, 0.07f, ID.Trail, handler));
   }

   @Override
   public void render(Graphics g)
   {
      g.setColor(enemyColour);

      g.fillRect((int)x, (int)y, xSize, ySize);
   }
}
