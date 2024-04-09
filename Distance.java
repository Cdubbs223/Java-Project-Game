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


public class Distance {

   static float x, y, z, v;

   public Distance (float x, float y) 
   {
      this.x = x;
      this.y = y;
   }
   
   public static float distance (float x, float y) 
   {
      float distance = (float) Math.sqrt((x - 300f) * (x - 300f) + (y - 300f) * (y - 300f));
      return distance;
   }
   
   public static float distanceToPlayer (float x, float y, float z, float v) 
   {
      float distance = (float) Math.sqrt((x - z) * (x - z) + (y - v) * (y - v));
      return distance;
   }
}