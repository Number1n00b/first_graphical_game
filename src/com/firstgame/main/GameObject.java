package com.firstgame.main;

import java.awt.*;

public abstract class GameObject
{
   protected ID id;

   //Positional params.
   protected float x;
   protected float y;
   protected float velX;
   protected float velY;
   protected int accX, accY;

   //Speed
   protected int speed;

   //Size params
   protected int xSize;
   protected int ySize;

   //Health
   protected int health = 30;
   protected int MAX_HEALTH = health;

   public GameObject(int x, int y, ID id)
   {
      this.x = x;
      this.y = y;
      this.id = id;

      speed = 0;

      velX  = 0;
      velY = 0;

      accX = 0;
      accY = 0;
   }

   public abstract void tick();
   public abstract void render(Graphics g);
   public abstract Rectangle getBounds();

   //getters
   public float getX()
   {
      return this.x;
   }

   public float getY()
   {
      return this.y;
   }

   public ID getId()
   {
      return this.id;
   }

   public float getVelX()
   {
      return this.velX;
   }

   public float getVelY()
   {
      return this.velY;
   }

   public int getSpeed()
   {
      return this.speed;
   }

   public int getAccX()
   {
      return this.accX;
   }

   public int getAccY()
   {
      return this.accY;
   }

   public int getHealth()
   {
      return health;
   }

   //setters
   public void setX(float x)
   {
      this.x = x;
   }

   public void setY(float y)
   {
      this.y = y;
   }

   public void setID(ID id)
   {
      this.id = id;
   }

   public void setVelX(float velX)
   {
      this.velX = velX;
   }

   public void setVelY(float velY)
   {
      this.velY = velY;
   }

   public void setVel(int velX, int velY)
   {
      this.velX = velX;
      this.velY = velY;
   }

   public void setSpeed(int speed)
   {
      this.speed = speed;
   }

   public void setAccX(int accX)
   {
      this.accX = accX;
   }

   public void setAccY(int accY)
   {
      this.accY = accY;
   }

   public void setHealth(int health)
   {
      this.health = health;
   }

   public void incrementHealth(int increment)
   {
      System.out.println("Increment called! from/to: " + health + "/" + (health+increment));

      health += increment;
      health = (int)Game.clamp((float)health, 0, MAX_HEALTH);
   }
}
