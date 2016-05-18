package com.firstgame.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Player extends GameObject
{
   private Color playerColour;

   private Handler handler;

   private Random r = new Random();

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
               }
               else if (temp.getId() == ID.EnemyBossBullet)
               {
                  Game.hud.incrementHealth(-50);
                  Game.removeQue.add(temp);
               }
               else if ( temp.getId() == ID.HealthUp )
               {
                  Game.hud.incrementHealth(100);
                  Game.hud.setScore(Game.hud.getScore()+50);                  
                  Game.removeQue.add(temp);
               }
               else
               {
                  Game.hud.incrementHealth(-2);
               }
            }
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
