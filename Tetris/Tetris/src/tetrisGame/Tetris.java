package tetrisGame;
/*
Student: Aroofa Mohammad(aam170007)
Class: CS 4361.002 - Computer Graphics
Assignment: 1
Version: 1
Due: Friday, September 9, 2022

Tetris.java:
	Create a program that will draw the GUI image or interface of the Tetris game.

Resource Sited:
	Copied from Section 1.4 of
		Ammeraal, L. and K. Zhang (2007). Computer Graphics for Java Programmers, 2nd Edition,
			Chichester: John Wiley.
	Istrop.java from "Source code for all algorithms in the textbook" in elearning
*/

//Imports
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;


public class Tetris extends Frame{
	
	// constant variables for the window's height and width
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	
	// constructor for class Tetris 
	public Tetris() {
		super("Tetris in isotropic mapping mode");						// titling the game's window
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}	// so the window can close by clicking 'x'
		});
		setSize(WIDTH, HEIGHT);											// setting the size of the window
		add("Center", new CvTetris());
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));	
		setVisible(true);												// so the window in visible
	}
	
	class CvTetris extends Canvas {
		   int centerX, centerY;	// center of the window
		   float pixelSize, rWidth = 300.0F, rHeight = 300.0F, xP=100000, yP, xPause=100000, yPause;
		   boolean mainArea = false; // A flag, whether the user's mouse is in the main area
		   
		   CvTetris() {
			  // For the 'QUIT' box, allow the program to exit once clicked
		      addMouseListener(new MouseAdapter() {
		         public void mousePressed(MouseEvent evt) {
		            xP = evt.getX(); yP = evt.getY();
		            // the size and position of the 'QUIT' box
		            if((xP<iX(65))&&(xP>iX(30))&&(yP<iY(-95))&&(yP>iY(-77)))
					{
						System.exit(0); // exit the program
					}
		         }
		      });
		      // For the 'PAUSE' box, allow the user to see the pause box when in the main area
		      addMouseMotionListener(new MouseAdapter() {
		    	  public void mouseMoved(MouseEvent evt) {
		    		  xPause = evt.getX(); yPause = evt.getY();
		    		  // if the user's mouse is in the main area then display the pause box
		    		  if((xPause<iX(0))&&(xPause>iX(-100))&&(yPause<iY(-100))&&(yPause>iY(100)))
						{
							mainArea = true; // flag is true so display
							repaint();
						}
						else
						{
							mainArea = false; // flag is false so don't display
							repaint();
						}
		    	  }
		      });
		   }
		// code used from the textbook cited above
		   void initgr() {
		      Dimension d = getSize();
		      int maxX = d.width - 1, maxY = d.height - 1;
		      pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		      centerX = maxX / 2; centerY = maxY / 2;
		   }
		   // variables used from the textbook cited above
		   int iX(float x) {return Math.round(centerX + x / pixelSize);} 
		   int iY(float y) {return Math.round(centerY - y / pixelSize);}
		   float fx(int x) {return (x - centerX) * pixelSize;}
		   float fy(int y) {return (centerY - y) * pixelSize;}
		   // the size of one unit of pixel
		   int pixelSize(float l) {return Math.round(l/pixelSize);} 

		   public void paint(Graphics g) {
			   initgr();
				// coordinates for the main area 
				int mainAreaRectLeftX=iX(-100);
				int mainAreaRectRightX=iX(0);
				int mainAreaRectTopY=iY(100);
				int mainAreaRectBottomY=iY(-100);
				// drawing main area 
				g.drawLine(mainAreaRectLeftX, mainAreaRectTopY, mainAreaRectRightX,mainAreaRectTopY);
				g.drawLine(mainAreaRectLeftX, mainAreaRectBottomY, mainAreaRectRightX, mainAreaRectBottomY);
				g.drawLine(mainAreaRectLeftX, mainAreaRectTopY, mainAreaRectLeftX, mainAreaRectBottomY);
				g.drawLine(mainAreaRectRightX,mainAreaRectTopY, mainAreaRectRightX, mainAreaRectBottomY);
							
				// coordinates for small box in the right corner
				int smallRectLeftX=iX(20);
				int smallRectRightX=iX(70);
				int smallRectTopY=iY(95);
				int smallRectBottomY=iY(65);
				// drawing the small box in the right corner
				g.drawLine(smallRectLeftX, smallRectTopY, smallRectRightX,smallRectTopY);
				g.drawLine(smallRectLeftX, smallRectBottomY, smallRectRightX, smallRectBottomY);
				g.drawLine(smallRectLeftX, smallRectTopY, smallRectLeftX, smallRectBottomY);
				g.drawLine(smallRectRightX,smallRectTopY, smallRectRightX, smallRectBottomY);
				
				// coordinates for quit box
				int quitRectLeftX=iX(30);
				int quitRectRightX=iX(65);
				int quitRectTopY=iY(-77);
				int quitRectBottomY=iY(-95);
				// drawing the quit box
				g.drawLine(quitRectLeftX, quitRectTopY, quitRectLeftX, quitRectBottomY);
				g.drawLine(quitRectRightX,quitRectTopY, quitRectRightX, quitRectBottomY);
				g.drawLine(quitRectLeftX, quitRectTopY, quitRectRightX,quitRectTopY);
				g.drawLine(quitRectLeftX, quitRectBottomY, quitRectRightX, quitRectBottomY);
				// draw the "QUIT" text on the window
				g.drawString("QUIT", iX(38), iY(-90));
				
				// coordinates for pause box
				int pauseRectLeftX=iX(-70);
				int pauseRectRightX=iX(-30);
				int pauseRectTopY=iY(10);
				int pauseRectBottomY=iY(-10);
				// drawing the pause box
				if(mainArea)
				{
					//Drawing Pause rectangle
					g.setColor(Color.lightGray);
					g.drawLine(pauseRectLeftX, pauseRectTopY, pauseRectRightX,pauseRectTopY);
					g.drawLine(pauseRectLeftX, pauseRectTopY, pauseRectLeftX, pauseRectBottomY);
					g.drawLine(pauseRectLeftX, pauseRectBottomY, pauseRectRightX, pauseRectBottomY);
					g.drawLine(pauseRectRightX,pauseRectTopY, pauseRectRightX, pauseRectBottomY);
					g.setColor(Color.lightGray);
					g.drawString("PAUSE", iX(-65), iY(-5));
				}
				
				// coloring the orange boxes
				g.setColor(Color.orange);
				g.fillRect(iX(-100), iY(-90), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-90), iY(-90), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-80), iY(-90), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-70), iY(-90), pixelSize(10), pixelSize(10));
				// outlining the orange boxes
				g.setColor(Color.black);
				g.drawRect(iX(-100), iY(-90), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-90), iY(-90), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-80), iY(-90), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-70), iY(-90), pixelSize(10), pixelSize(10));
				
				// coloring the blue boxes
				g.setColor(Color.blue);
				g.fillRect(iX(-100), iY(-80), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-100), iY(-70), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-100), iY(-60), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-90), iY(-60), pixelSize(10), pixelSize(10));
				// outlining the blue boxes
				g.setColor(Color.black);
				g.drawRect(iX(-100), iY(-80), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-100), iY(-70), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-100), iY(-60), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-90), iY(-60), pixelSize(10), pixelSize(10));
	
				// coloring the green boxes
				g.setColor(Color.green);
				g.fillRect(iX(-50), iY(-90), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-60), iY(-90), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-40), iY(-80), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-50), iY(-80), pixelSize(10), pixelSize(10));
				// outlining the blue boxes
				g.setColor(Color.black);
				g.drawRect(iX(-50), iY(-90), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-60), iY(-90), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-40), iY(-80), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-50), iY(-80), pixelSize(10), pixelSize(10));
				
				// coloring the red boxes
				g.setColor(Color.red);
				g.fillRect(iX(30), iY(90), pixelSize(10), pixelSize(10));
				g.fillRect(iX(30), iY(80), pixelSize(10), pixelSize(10));
				g.fillRect(iX(40), iY(80), pixelSize(10), pixelSize(10));
				g.fillRect(iX(50), iY(80), pixelSize(10), pixelSize(10));
				// coloring the red boxes
				g.setColor(Color.black);
				g.drawRect(iX(30), iY(90), pixelSize(10), pixelSize(10));
				g.drawRect(iX(30), iY(80), pixelSize(10), pixelSize(10));
				g.drawRect(iX(40), iY(80), pixelSize(10), pixelSize(10));
				g.drawRect(iX(50), iY(80), pixelSize(10), pixelSize(10));
				
				// coloring the yellow boxes
				g.setColor(Color.yellow);
				g.fillRect(iX(-50), iY(70), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-60), iY(70), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-50), iY(60), pixelSize(10), pixelSize(10));
				g.fillRect(iX(-60), iY(60), pixelSize(10), pixelSize(10));
				// coloring the yellow boxes
				g.setColor(Color.black);
				g.drawRect(iX(-50), iY(70), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-60), iY(70), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-50), iY(60), pixelSize(10), pixelSize(10));
				g.drawRect(iX(-60), iY(60), pixelSize(10), pixelSize(10));
				
				// the extra text add to the GUI view
				g.drawString("Level:1", iX(25), iY(25));
				g.drawString("Lines:0", iX(25), iY(-5));
				g.drawString("Score:0", iX(25), iY(-35));
				
		   }
		}
	
	// main method
	public static void main(String[] args) {
		// calling the class constructor
		new Tetris();
	}

}
