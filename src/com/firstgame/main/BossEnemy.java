package com.firstgame.main;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends GameObject
{
   private static Color enemyColour = Color.darkGray;

   private Random r = new Random();

   private int timer = 60;
   private int timer2 = 50;

   private Handler handler;

   public BossEnemy(int x, int y, ID id, Handler handler)
   {
      super(x, y, id);

      health = 100;
      MAX_HEALTH = 100;

      velX = 0;
      velY = 5;

      this.handler = handler;

      xSize = 600;
      ySize = 96;
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
         Game.gameState = Game.STATE.Win;

         for( GameObject o : handler.object )
         {
            Game.removeQue.add(o);
         }

         Game.reset();
      }

      x += velX;
      y += velY;

      if(timer <= 0)
      {
         velY = 0;
      }
      else
      {
         timer--;
      }

      if(timer <= 0)
      {
         timer2--;
      }

      if(timer2 <= 0)
      {
         if(velX == 0) velX = 2;
         int spawn = r.nextInt(10);
         if(spawn == 0 || spawn == 1)
         {
            Game.objQueue.add(new EnemyBossBullet((int)(((x+xSize)/2)), (int)(((y+ySize))), ID.EnemyBossBullet, handler));
         }
      }

      //if( (y<= 0) || (y >= Game.HEIGHT - ySize) ) velY *= -1;
      if( (x<= 0) || (x >= (Game.WIDTH-xSize-5)) ) velX *= -1;
   }

   @Override
   public void render(Graphics g)
   {
      //Outline box
      g.setColor(Color.gray);
      g.fillRect(Game.WIDTH - 215, 15, 200, 32);

      g.drawString("Boss Health!", Game.WIDTH - 215, 64);

      //Health bar
      g.setColor(Color.red);
      g.fillRect(Game.WIDTH - 215, 15, (int)(((float)health/(float)MAX_HEALTH) * 200), 32);


      g.setColor(enemyColour);

      g.fillRect((int)x, (int)y, xSize, ySize);
   }
}
