import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - provides two short demonstrations showing how to use the 
 * Canvas class. 
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private int bordaRect;
    private ArrayList<BouncingBall> balls;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        myCanvas.setVisible(true);
        this.bordaRect = 20;
        balls = new ArrayList<BouncingBall>();
    }
 
    /**
     * Demonstrate some of the drawing operations that are
     * available on a Canvas object.
     */
    public void drawDemo()
    {
        myCanvas.setFont(new Font("helvetica", Font.BOLD, 14));
        myCanvas.setForegroundColor(Color.red);

        myCanvas.drawString("We can draw text, ...", 20, 30);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.black);
        myCanvas.drawString("...draw lines...", 60, 60);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawLine(200, 20, 300, 50);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.blue);
        myCanvas.drawLine(220, 100, 370, 40);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.green);
        myCanvas.drawLine(290, 10, 320, 120);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawString("...and shapes!", 110, 90);

        myCanvas.setForegroundColor(Color.red);

        // the shape to draw and move
        int xPos = 10;
        Rectangle rect = new Rectangle(xPos, 150, 30, 20);

        // move the rectangle across the screen
        for(int i = 0; i < 200; i ++) {
            myCanvas.fill(rect);
            myCanvas.wait(10);
            myCanvas.erase(rect);
            xPos++;
            rect.setLocation(xPos, 150);
        }
        // at the end of the move, draw once more so that it remains visible
        myCanvas.fill(rect);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce(int amountBalls)
    {
        if(amountBalls > 0){
            int ground = 400;   // position of the ground line

            myCanvas.setVisible(true);

            Dimension size = myCanvas.getSize();  
            int lineFimX = size.width - (2 * bordaRect) + 2; 
            int lineY = size.height - (2 * bordaRect) + 2;
            // draw the ground
            myCanvas.drawLine(42, lineY, lineFimX, lineY); 

            // crate and show the balls
            this.createBalls(amountBalls, size, lineY);
            for(BouncingBall ball : this.balls){
                ball.draw();
            }

            // make them bounce
            boolean finished =  false;
            int countBallsFinish = 0;
            while(!finished) {
                myCanvas.wait(50);           // small delay
                for(BouncingBall ball : this.balls){
                    ball.move();
                    if(ball.getXPosition() >= lineFimX-10){
                        countBallsFinish++;
                    }
                }
               // ball.move();
               // ball2.move();
                // stop once ball has travelled a certain distance on x axis
                //if(ball.getXPosition() >= lineFimX && ball2.getXPosition() >= lineFimX) {
                  if(countBallsFinish == amountBalls)
                    finished = true;
                //}
            }
            for(BouncingBall ball : this.balls){
                ball.erase();
            }
            //ball2.erase();
        }else{
            System.out.println("É necessário adicionar pelo menos uma bola para poder utilizar o método.");
        }
    }
	
	public void setCanvas(int width, int height){
		myCanvas.setSize(width, height);
	}
	
	/**
	* Método de criação de quadro com 20px de distãncia das bordas do canvas
	*/
	public void drawFrame(){
		myCanvas.erase();
		Dimension size = myCanvas.getSize();  
		size.width = size.width - (2 * this.bordaRect); 
		size.height = size.height - (2 * this.bordaRect);
		Rectangle rect = new Rectangle(this.bordaRect, this.bordaRect, size.width, size.height);
		myCanvas.draw(rect);
	}

    /**
	* Método de criação das bolas para animação
	*/
    public void createBalls(int amoutBalls, Dimension size, int lineY){

        Random generatorSizeBall = new Random();
        for(int i = 0; i < amoutBalls; i++){
            int roundY = Math.round(size.height/2);
            System.out.println(generatorSizeBall.nextInt(roundY)+20);
            System.out.println(generatorSizeBall.nextInt(20)+1);
            System.out.println(lineY);
            BouncingBall ball = new BouncingBall(42, generatorSizeBall.nextInt(roundY)+20, generatorSizeBall.nextInt(20)+5, Color.blue, lineY, this.myCanvas);
            this.balls.add(ball);
        }
    }

	
}
