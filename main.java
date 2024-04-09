import java.net.*;
import java.io.FileOutputStream;
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
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import java.util.ArrayList;
import javafx.scene.text.Text; 


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
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.lang.Math;


public class main extends Application
{
   FlowPane fp;
   
   Canvas theCanvas = new Canvas(600,600);

   GraphicsContext gc;
   
   Player thePlayer = new Player(300, 300);
   
   boolean distance, keyDown, left, down, right, up;
   
   float playerSpeed = 1;
   
   boolean safe = true;

   
   public void start(Stage stage)
   {   
      fp = new FlowPane();
      fp.getChildren().add(theCanvas);
      gc = theCanvas.getGraphicsContext2D();
      drawBackground(300,300,gc);

      
      gc.setFill(Color.WHITE);
      gc.fillText("High Score: ", 10, 50);
      
      Scene scene = new Scene(fp, 600, 600);
      stage.setScene(scene);
      stage.setTitle("Project :)");
      stage.show();
      
      theCanvas.setOnKeyPressed(new KeyListenerDown());
      theCanvas.setOnKeyReleased(new KeyListenerUp());
      
      theCanvas.requestFocus();
      
      AnimationHandler ah = new AnimationHandler();
      ah.start();
   }
   
   Image background = new Image("stars.png");
   Image overlay = new Image("starsoverlay.png");
   
   Random backgroundRand = new Random();
   //this piece of code doesn't need to be modified
   public void drawBackground(float playerx, float playery, GraphicsContext gc)
   {
	  //re-scale player position to make the background move slower. 
      playerx*=.1;
      playery*=.1;
   
	//figuring out the tile's position.
      float x = (playerx) / 400;
      float y = (playery) / 400;
      
      int xi = (int) x;
      int yi = (int) y;
      
	  //draw a certain amount of the tiled images
      for(int i=xi-3;i<xi+3;i++)
      {
         for(int j=yi-3;j<yi+3;j++)
         {
            gc.drawImage(background,-playerx+i*400,-playery+j*400);
         }
      }
      
	  //below repeats with an overlay image
      playerx*=2f;
      playery*=2f;
   
      x = (playerx) / 400;
      y = (playery) / 400;
      
      xi = (int) x;
      yi = (int) y;
      
      for(int i=xi-3;i<xi+3;i++)
      {
         for(int j=yi-3;j<yi+3;j++)
         {
            gc.drawImage(overlay,-playerx+i*400,-playery+j*400);
         }
      }
   }
   
   float forceX, forceY; 
   
   public class KeyListenerDown implements EventHandler<KeyEvent> {
        public void handle(KeyEvent event) {
            
            keyDown = true;
        
            if (event.getCode() == KeyCode.A) {
            
               left = true;
                  
            } else if (event.getCode() == KeyCode.S) { 
            
               down = true; 
      
            } else if (event.getCode() == KeyCode.D) {
            
               right = true;
  
            } else if (event.getCode() == KeyCode.W) {          
                      
               up = true;
                       
            }
        }
     }
     
      
    int highScore;
     
    // key listener for when you are not holding it down 
    public class KeyListenerUp implements EventHandler<KeyEvent> {
        public void handle(KeyEvent event) {
         
        
            keyDown = false; 
            
            if (event.getCode() == KeyCode.A) {
            
               left = false;
                  
            } else if (event.getCode() == KeyCode.S) { 
            
               down = false; 
      
            } else if (event.getCode() == KeyCode.D) {
            
               right = false;
  
            } else if (event.getCode() == KeyCode.W) {          
                      
               up = false;
                       
            }
            
        }
     }
   
   
   ArrayList<Mines> minesList = new ArrayList<Mines>();
   
   public class AnimationHandler extends AnimationTimer
   {
      public void handle(long currentTimeInNanoSeconds) 
      {
      
      
         gc.clearRect(0,0,600,600);
         
         readFile(gc);
         
         //USE THIS CALL ONCE YOU HAVE A PLAYER
         drawBackground(thePlayer.getX(),thePlayer.getY(),gc);
         
       if (safe) {
         if (!right && !left) {
            if (-.25<forceX && forceX<.25) {
               forceX = 0;
            } else if (forceX >= -.25) {
               forceX -= 0.025f;
            } else if (forceX <= -.25) {
               forceX += 0.025f;
            }
         }
         
         if (!up && !down) {
            if (-.25<forceY && forceY<.25) {
               forceY = 0;
            } else if (forceY >= -.25) {
               forceY -= 0.025f;
            } else if (forceY <= -.25) {
               forceY += 0.025f;
            }  
         }
         
         //A
         if (left) {
            if (forceX>5) {
               forceX = 5;
            } else {
               forceX -= 0.1f;
            }
         }
         
         //D
         if (right) {
            if (forceX < -5) {
               forceX = -5;
            } else {
               forceX += 0.1f;
            }
         }
         
         //W
         if (up) {
            if (forceY > 5) {
               forceY = 5;
            } else {
               forceY -= 0.1f;
            }
         }
         
         //S
         if (down) {
            if (forceY < -5) {
               forceY = -5;
            } else if (forceY  >= -5) {
               forceY += 0.1f;
            }
         }   
        
         
         thePlayer.setY(thePlayer.getY() + (forceY));
         thePlayer.setX(thePlayer.getX() + (forceX));
       }
         
         int score = (int) Distance.distance(thePlayer.getX(),thePlayer.getY());
          
         Mines.advanceColor(); 
         
         gc.setFill(Color.WHITE);
         gc.fillText("Score: " + score, 10, 20);
         
         gc.setFill(Color.WHITE);
         gc.fillText("High Score: " + highScore, 10, 50);
         
         
	      //example calls of draw - this should be the player's call for draw
         thePlayer.draw(300,300,gc,safe); 
         
         
        //Mines  
        int dx = 0, dy = 0;
        if (left)
            dx = -1;
        else if (right)
            dx = 1;
        if (up)
            dy = -1;
        else if (down)
            dy = 1;

         // Calculate the position in front of the player
         int frontX = (int) (thePlayer.getX() + dx * 500); // Adjust the distance as needed
         int frontY = (int) (thePlayer.getY() + dy * 500); // Adjust the distance as needed

         // Spawn the mines in front of the player
         for (int i = 0; i < 9; i++) {
            int N = (i * 100);

            // Calculate the grid position of the mine relative to the front position
            int playerGridX = (frontX + N) / 100;
            int playerGridY = (frontY + N) / 100;

            // Calculate the maximum number of mines for this grid square
            int maxMines = (int) Distance.distanceToPlayer(playerGridX, playerGridY, thePlayer.getX(), thePlayer.getY()) / 1000;

            // Determine the chance of creating a mine
            int chance = random.nextInt(100); // Random number between 0 and 99

            // Check if the chance is within the 30% range
            if (chance < 30 && minesList.size() < maxMines) {
               // Randomly determine the position of the new mine within the grid square
               int randomX = frontX + N + random.nextInt(100);
               int randomY = frontY + N + random.nextInt(100);

               // Add the new mine to the list

               Mines newMine = new Mines(randomX, randomY);
               if(Math.sqrt(Math.pow((thePlayer.getX() + newMine.getX()), 2) + Math.pow((thePlayer.getX() + newMine.getX()), 2)) > 800)
               {
                  minesList.add(new Mines(randomX, randomY));
               }

            }
           } 
           
           
         
    for (int t = 0; t < minesList.size(); t++) {
    Mines mine = minesList.get(t);
    int randomX = minesList.get(t).getX()-303;
    int randomY = minesList.get(t).getY()-303;

    int mine_to_player_distance = (int) Distance.distanceToPlayer(randomX, randomY, thePlayer.getX(), thePlayer.getY());

    // If the player hits a mine within a radius of 20 units, set safe to false
    if (mine_to_player_distance <= 20) {
        safe = false;
        
        if (score>highScore){
        highScore = score; 
        fileNew(highScore);
        }
        // Optionally, you can remove the mine that was hit
        minesList.remove(t);
        // Update the index to avoid skipping the next mine in the list
        t--;
    }
} 
         
            
            for (int j=0; j<minesList.size(); j++) {
               //mines grid
               int mgridx = ((int)minesList.get(j).getX())/100;
               int mgridy = ((int)minesList.get(j).getY())/100;                    
            }              
            
         
         for(int i=0; i<minesList.size(); i++)
         {
            minesList.get(i).drawMine(gc,thePlayer.getX(),thePlayer.getY());
         } 
         
         //Mines delete 
         
         for (int i=0; i<minesList.size(); i++) {
            int mineX = minesList.get(i).getX();
            int mineY = minesList.get(i).getY();     
            int mine_to_player_distance = (int)Distance.distanceToPlayer(mineX,mineY,thePlayer.getX(),thePlayer.getY());            
         
            if (mine_to_player_distance > 800) {
               minesList.remove(i);
               i--;
            }
         }
      }
   }
   
  public void readFile(GraphicsContext gc) {
    try {
        String filePath = "HighScore.txt"; // Relative file path
        File file = new File(filePath);
        
        // Check if the file exists before attempting to read from it
        if (!file.exists()) {
            System.out.println("High score file not found. Creating a new one.");
            fileNew(0); // Create a new file with initial high score 0 if not found
            return;
        }
        
        Scanner myReader = new Scanner(file);
        
        // Check if the file has content to read
        if (myReader.hasNextLine()) {
            // Read the high score as an integer
            int data = myReader.nextInt();
            highScore = data;
        } else {
            // If the file is empty, set the high score to 0
            highScore = 0;
        }
        
        myReader.close();
        
    } catch (FileNotFoundException e) {
        System.out.println("High score file not found: " + e.getMessage());
    } catch (NoSuchElementException e) {
        // If there are no more tokens to read, the file might be empty or not contain valid integer data
        System.out.println("Error reading high score: Invalid file format or empty file.");
    } catch (Exception e) {
        // Handle any other unexpected exceptions
        System.out.println("An error occurred while reading high score file: " + e.getMessage());
        e.printStackTrace();
    }
}
  
   public void fileNew(int highScore) {
      try {
         // Specify the full path to the directory where you want to save the file
         String filePath = "/Users/connerjwilson/Desktop/Project/HighScore.txt"; // Replace this with the actual directory path
        
         FileOutputStream fos = new FileOutputStream(filePath, false);
         PrintWriter print = new PrintWriter(fos);
         print.println(highScore);
         print.close();
         System.out.println("High score saved to file successfully.");
      } catch (Exception e) {
         System.out.println("Error saving high score to file: " + e.getMessage());
         e.printStackTrace(); // Print the stack trace for more detailed error information
      }
   }
      
   Random random = new Random();
      

   public static void main(String[] args)
   {
      launch(args);
   }
}                 