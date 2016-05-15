package com.firstgame.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler
{
   LinkedList<GameObject> object = new LinkedList<GameObject>();

   public void tick()
   {
      for( GameObject temp : object )
      {
         temp.tick();
      }
   }

   public void render(Graphics g)
   {
      for( GameObject temp : object )
      {
         temp.render(g);
      }
   }

   public void clearEnemys()
   {
      for( GameObject temp : object )
      {
         if(temp.getId() != ID.Player)
         {
            Game.removeQue.add(temp);
         }
      }
   }

   public void clearAll()
   {
      for( GameObject temp : object )
      {
         Game.removeQue.add(temp);
      }
   }

   public void addObject(GameObject ob)
   {
      this.object.add(ob);
   }

   public void removeObject(GameObject ob)
   {
      this.object.remove(ob);
   }

}
