import javafx.scene.canvas.GraphicsContext;

public class ConcreteClass extends DrawableObject {

   public ConcreteClass(float x, float y) {
      super(x, y);
   }
   
   public void drawMe(float playerx, float playery, GraphicsContext gc) {
       
   }
   
   
   //create a variable forceX and forceY in main 
   //put this into animation handler when it is done
   
   double forceX, forceY; 
   
   public class KeyListenerDown implements EventHandler<KeyEvent> {
        public void handle(KeyEvent event) {
        
            if (event.getCode() == KeyCode.A) {
               
               if (forceX>=-5) {
                  forceX = forceX-.1;
               }
                           
            } else if (event.getCode() == KeyCode.S) {                 
               
               if (forceY>=-5) {
                  forceY = forceY-.1;
               }
                
            } else if (event.getCode() == KeyCode.D) {
                
               if (forceX<=-5) {
                  forceX = forceX+.1;
               }
                
            } else if (event.getCode() == KeyCode.W) {          
               
               if (forceY<=-5) {
                  forceY = forceY+.1;
               }
            
            }
        }
     }
    
    // key listener for when you are not holding it down 
    public class KeyListenerUp implements EventHandler<KeyEvent> {
        public void handle(KeyEvent event) {
            forceX = forceX*.75; 
            forceY = forceY*.75; 
            
            if (-.25<forceX<.25) {
               forceX = 0;
            }
            
            if (-.25<forceY<.25) {
               forceY = 0;
            }
            
        }
     }


   
}
