package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class GamePieces {
	Color Bbrown= new Color(204,153,102);
	Color bananaColor= new Color(249,233,88);
	Color snakeColor= new Color(50,70,250);
	
	public GamePieces() {
		
	}
	public void drawSnake(Graphics g, int X, int Y)
	{
		g.setColor(Color.RED);
		g.fillOval(X+19,Y+2,2,14);
		g.setColor(snakeColor);
		g.fillRoundRect(X+15,Y+7,10,30,10,15);
		g.setColor(Color.BLACK);
		g.fillOval(X+17,Y+11,2,2);
		g.fillOval(X+21,Y+11,2,2);
    }
	//method for bananas
   public void drawBanana(Graphics g, boolean unlocked, int X, int Y)
   {
      //checks to see if your able to get bananas and draws the red box accordingly
      if(unlocked==false)
      {
         g.setColor(Color.RED);
         g.drawRect(X,Y,40,40);
      }
      g.setColor(bananaColor);
      g.fillOval(X+7, Y+5,25,30);
      g.setColor(Color.WHITE);
      g.fillOval(X+2,Y+4,20,27);
      g.setColor(Bbrown);
      g.fillOval(X+15,Y+4, 3, 2);
   }
   
   //gamepiece
   public void drawSafariHat(Graphics g,int X, int Y)
   {
      g.setColor(new Color(247,221,195));
      g.fillArc(X+9,Y+10,22,26,0,180);
      g.setColor(new Color(166,100,34));
      g.drawArc(X+9,Y+10,22,26,0,180);
      g.fillRoundRect(X+5,Y+23,30,5,4,5);     
      g.fillOval(X+18,Y+8,4,2);
   }
   
	public void drawElephant(Graphics g, int X, int Y)
	{
	//elephant
     g.setColor(new Color( 177, 224, 253 ) ); // light blue
     //body
     g.fillOval( X +5, Y+15, 25, 20 );
     //head
	  g.fillOval( X +24, Y+10, 13, 12); 
	  //ears
     g.fillOval( X +23, Y +7, 9, 9 );
	  //trunk used a polygon so the rectangle will be rotated
     Polygon trunk = new Polygon();
	  trunk.addPoint(X+35,Y+13);
	  trunk.addPoint(X+46,Y+17);
	  trunk.addPoint(X+45,Y+22);
	  trunk.addPoint(X+33,Y+18);
	  g.fillPolygon(trunk);
	  //tail at an angle
	  Polygon rectangle = new Polygon();
	  rectangle.addPoint(X +9,Y+18);
	  rectangle.addPoint(X +9,Y+21);
	  rectangle.addPoint(X -3,Y + 26);
	  rectangle.addPoint(X,Y +23);
	  g.fillPolygon(rectangle);
     //flatten bottom
      g.clearRect( X +5, Y + 31, 35, 4 );
     //feet
	  Polygon triangle = new Polygon();
	  triangle.addPoint(X +12,Y + 28);
	  triangle.addPoint(X +7,Y + 35);
	  triangle.addPoint(X +17,Y + 35);
	  g.fillPolygon(triangle);  
	  Polygon triangle2 = new Polygon();	  	  
	  triangle2.addPoint(X +24, Y + 28);
	  triangle2.addPoint(X +19,Y + 35);
	  triangle2.addPoint(X +29,Y + 35);
	  g.fillPolygon(triangle2);

   }



}
