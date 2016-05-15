package com.firstgame.main;

import java.awt.*;
import java.util.Random;

public class PlayerBullet extends GameObject
{
   private static Color enemyColour = Game.PLAYER_ONE_COLOR;

   private Random r = new Random();

   private Handler handler;

   public PlayerBullet(int x, int y, int velX, int velY, ID id, Handler handler)
   {
      super(x, y, id);

      this.velX = velX;
      this.velY = velY;
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

      collision();
   }

   public void collision()
   {

   }

   @Override
   public void render(Graphics g)
   {
      g.setColor(enemyColour);

      g.fillOval((int)x, (int)y, xSize, ySize);
   }
}
