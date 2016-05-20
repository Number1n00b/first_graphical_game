package com.firstgame.main;

import java.util.Random;

public class Spawn
{
   private Handler handler;
   public HUD hud;

   private int scoreKeep = 250;

   private Random r = new Random();


   public Spawn(Handler handler, HUD hud)
   {
      this.handler = handler;
      this.hud = hud;
   }

   public void setKeep(int keep)
   {
      scoreKeep = keep;
   }

   public void tick()
   {
      scoreKeep++;

      if (r.nextInt(100) == 0)
      {
         Game.objQueue.add(new HealthUp(Game.randomPoint('w'), Game.randomPoint('h'), ID.HealthUp, handler));
      }

      if(scoreKeep >= 250)
      {
         scoreKeep = 0;
         hud.setLevel(hud.getLevel() + 1);

         switch(hud.getLevel())
         {
            case 1:
               //Add player and enemies to game.
               Game.objQueue.add(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));

               Game.objQueue.add(new BasicEnemy(Game.randomPoint('w'), Game.randomPoint('h'), ID.BaiscEnemy, handler));
            break;
            case 2:
               Game.objQueue.add(new BasicEnemy(Game.randomPoint('w'), Game.randomPoint('h'), ID.BaiscEnemy, handler));
            break;
            case 3:
               Game.objQueue.add(new BasicEnemy(Game.randomPoint('w'), Game.randomPoint('h'), ID.BaiscEnemy, handler));
               Game.objQueue.add(new FastEnemy(Game.randomPoint('w'), Game.randomPoint('h'), ID.BaiscEnemy, handler));
            break;
            case 4:
               Game.objQueue.add(new SmartEnemy(Game.randomPoint('w'), Game.randomPoint('h'), ID.SmartEnemy, handler));
            break;
            case 5:
               Game.objQueue.add(new BossEnemy(Game.WIDTH/2-100, -200, ID.BossEnemy, handler));
            break;
            case 6:
               Game.objQueue.add(new SmartEnemy(Game.randomPoint('w'), Game.randomPoint('h'), ID.SmartEnemy, handler));
               Game.objQueue.add(new FastEnemy(Game.randomPoint('w'), Game.randomPoint('h'), ID.BaiscEnemy, handler));
               Game.objQueue.add(new FastEnemy(Game.randomPoint('w'), Game.randomPoint('h'), ID.BaiscEnemy, handler));
            break;

         }
      }

   }

   public void render()
   {

   }

}
