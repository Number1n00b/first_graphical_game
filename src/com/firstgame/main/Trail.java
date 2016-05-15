package com.firstgame.main;

import java.awt.*;

public class Trail extends GameObject
{
   private double alpha = 1;
   private Handler handler;
   private Color color;

   private int width, height;
   float life; //life between 0.001-0.1

   public Trail(int x, int y, Color color, int width, int height, float life, ID id, Handler handler)
   {
      super(x, y, id);
      this.handler = handler;
      this.color = color;
      this.width = width;
      this.height = height;
      this.life = life;
   }

   @Override
   public void tick()
   {
      if(alpha > life)
      {
         alpha -= (life - 0.001f);
      }
      else
      {
         if(Game.gameState != Game.STATE.Game)
         {
            Game.menuRemoveQueue.add(this);
         }
         else
         {
            Game.removeQue.add(this);
         }
      }
   }

   @Override
   public void render(Graphics g)
   {
      Graphics2D g2d = (Graphics2D) g;

      g2d.setComposite(makeTransparent(alpha));
      g.setColor(color);
      g.fillRect((int)x, (int)y, width, height);

      g2d.setComposite(makeTransparent(1));
   }

   private AlphaComposite makeTransparent(double alpha)
   {
      int type = AlphaComposite.SRC_OVER;

      return (AlphaComposite.getInstance(type, (float)alpha));
   }

   @Override
   public Rectangle getBounds()
   {
      return null;
   }
}
