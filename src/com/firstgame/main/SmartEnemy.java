package com.firstgame.main;

import java.awt.*;
import java.util.Random;

public class SmartEnemy extends GameObject
{
   private static Color enemyColour = new Color(160,32,240); //purple

   private GameObject player;

   private Random r = new Random();

   private Handler handler;

   public SmartEnemy(int x, int y, ID id, Handler handler)
   {
      super(x, y, id);

      MAX_HEALTH = 50;
      health = 50;

      this.handler = handler;

      for(GameObject ob : handler.object)
      {
         if(ob.getId() == ID.Player)
         {
            player = ob;
         }
      }

      xSize = 50;
      ySize = 50;
   }

   @Override
   public Rectangle getBounds()
   {
      return new Rectangle((int)x, (int)y, xSize, ySize);
   }

   @Override
   public void tick()
   {
      if(this.health <= 0 )
      {
         Game.removeQue.add(this);
      }

      float diffX = x - player.getX();
      float diffY = y - player.getY();

      float dist = (float)Math.sqrt( (diffX * diffX) + (diffY * diffY) );

      velX = (float)((-1.0 / dist) * diffX * 3);
      velY = (float)((-1.0 / dist) * diffY * 3);

      x += velX;
      y += velY;

      if( (y<= 0) || (y >= Game.HEIGHT - ySize) ) velY *= -1;
      if( (x<= 0) || (x >= Game.WIDTH - xSize) ) velX *= -1;

      Game.objQueue.add(new Trail((int)x, (int)y, enemyColour, xSize, ySize, 0.15f, ID.Trail, handler));
   }

   @Override
   public void render(Graphics g)
   {
      //Outline box
      g.setColor(Color.gray);
      g.fillRect((int)x-5, (int)(y+ySize+5), xSize+5, 5);

      //Health bar
      g.setColor(Color.red);
      g.fillRect((int)x-5, (int)(y+ySize+5), (int)(((float)health/(float)MAX_HEALTH) * (xSize+5)), 5);

      g.setColor(enemyColour);

      g.fillOval((int)x, (int)y, xSize, ySize);
   }
}
