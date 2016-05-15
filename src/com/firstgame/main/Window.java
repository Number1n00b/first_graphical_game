package com.firstgame.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas
{
   public static final long serialVersionUID = 566213;

   public Window(int width, int height, String title, Game game)
   {
      JFrame frame = new JFrame(title);

      frame.setPreferredSize(new Dimension(width+13, height+35));
      frame.setMaximumSize(new Dimension(width+13, height+35));
      frame.setMinimumSize(new Dimension(width+13, height+35));

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(true);
      frame.setLocationRelativeTo(null);

      frame.add(game);
      frame.setVisible(true);
      game.start();

      System.out.println("Frame Size   : " + frame.getSize() );
      System.out.println("Frame Insets : " + frame.getInsets() );
      System.out.println("Content Size : " + frame.getContentPane().getSize() );

   }


}
