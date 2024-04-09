import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.image.*;

import java.io.*;

import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;


public class Mines {

   static int way = 1;
   static double colorValue; 
   int color = 0;
   
   int x, y;
   
   public Mines (int x, int y) 
   {
      this.x = x;
      this.y = y;
   }
   
   public int getX ()
   {
      return x;
   }
   
   public int getY () 
   {
      return y;
   }
   
   public static void advanceColor()
   {
      colorValue += 0.01f * way;
      
      if(colorValue > 1)
      {
         colorValue = 1;
         way = - 1;
      }
      
      if(colorValue < 0)
      {
         colorValue = 0;
         way = 1;
      }
   }
   
   public void drawMine(GraphicsContext gc, float playerX, float playerY) 
   {
      
      gc.setFill(Color.WHITE.interpolate(Color.RED,colorValue));

      
      gc.fillOval(x-playerX,y-playerY,11,11);
      
   }
}