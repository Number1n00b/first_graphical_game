package com.firstgame.main;

import java.awt.*;
import java.util.Random;

public class PlayerBullet extends GameObject
{
   private Color color;

   private Random r = new Random();

   private Handler handler;

   private int playerDamage = 10;

   public PlayerBullet(int x, int y, int velX, int velY, ID id, Handler handler, Color col)
   {
      super(x, y, id);

      this.color = col;
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
      if(this.id != ID.Player_Demo) {
         for (GameObject o : handler.object) {
            try {
               if (this.getBounds().intersects(o.getBounds())) {
                  if (Game.isEnemy(o.getId()) && !(o.getId() == ID.EnemyBossBullet)) {
                     o.incrementHealth(-playerDamage);
                     Game.removeQue.add(this);
                  } else if (o.getId() == ID.EnemyBossBullet) {
                     Game.removeQue.add(o);
                  } else if (o.getId() == ID.BossEnemy) {
                     o.incrementHealth(-(playerDamage / 2));
                     Game.removeQue.add(this);
                  }
               }
            } catch (NullPointerException e) {/*System.out.println("np Ex");*/}
         }
      }
   }

   @Override
   public void render(Graphics g)
   {
      g.setColor(color);

      g.fillOval((int)x, (int)y, xSize, ySize);
   }
}
