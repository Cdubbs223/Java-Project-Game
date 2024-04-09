import javafx.scene.paint.*;
import javafx.scene.canvas.*;

//this is an example object
public class Player extends DrawableObject
{
	//takes in its position
   public Player(float x, float y)
   {
      super(x,y);
   }
   
   //draws itself at the passed in x and y.
   public void drawMe(float x, float y, GraphicsContext gc)
   {
      gc.setFill(Color.GREY);
      gc.fillOval(x-12,y-12,24,24);
      
      gc.setFill(Color.LIGHTBLUE);
      gc.fillOval(x-5,y-5,11,11);
   }
}
