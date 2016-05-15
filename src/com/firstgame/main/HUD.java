package com.firstgame.main;

import java.awt.*;

public class HUD
{
   private static final int MAX_HEALTH = 200;
   private float health = 200;

   private int greenVal = 255;
   private int score = 0;
   private int level = 0;

   public HUD(int health)
   {
      this.health = health;
   }

   public void tick()
   {
      health = Game.clamp(health, 0, MAX_HEALTH);

      greenVal = 2*(int)health;

      greenVal = (int)Game.clamp(greenVal, 0, 255);

      score++;
   }

   public void render(Graphics g)
   {
      //Outline box
      g.setColor(Color.gray);
      g.fillRect(15, 15, 200, 32);

      g.drawString("Level: " + level, 18, 64);
      g.drawString("Score: " + score, 18, 84);

      //Health bar
      g.setColor(new Color(75, greenVal, 0));
      g.fillRect(15, 15, (int)health, 32);

   }


   public float getHealth()
   {
      return this.health;
   }

   public void setScore(int score)
   {
      this.score = score;
   }

   public int getScore()
   {
      return this.score;
   }

   public int getLevel()
   {
      return this.level;
   }

   public void setLevel(int level)
   {
      this.level = level;
   }

   public void incrementHealth(int delta)
   {
      health += delta;
   }


}
