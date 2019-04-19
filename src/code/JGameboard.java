package code;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class JGameboard extends JPanel
{
	Random rand;
	int SAFARI=10,ELEPHANT=3, SNAKE=2, BANANA=1,FINISH=100;
   int x=0, y=0,points=0;
   int rows=14, cols=14;
   int startX, startY;
   int SafariX=0, SafariY=0;
   Boolean gameStarted=false, gameOver=false, gameWon=false, unlocked=false, isOn=false, stopg=false;
   Color bgColor=Color.WHITE;
   Color Bbrown= new Color(204,153,102);
   Color Byellow= new Color(249,233,88);
   Color Sblue= new Color(50,70,250);
	int[][] storage= new int[rows][cols];
	
	public JGameboard()
	{
      setOpaque(true);
      setPreferredSize(new Dimension(560,560));
      setBackground(Color.WHITE);
      //at bottom is always finish
		storage[rows-1][cols-1]=FINISH;
      rand = new Random();
  	}
	
   //initializes level and all the statements needed for start of game
	public void play(int level)
	{
      storage[rows-1][cols-1]=FINISH;
      int Samount=10;
      if (level==1)
			Samount=40;
		if (level==2)
			Samount=70;
		if (level==3)
			Samount=90;
      int Bamount=20;
      addCharacters(Samount, SNAKE);
      addCharacters(Bamount, BANANA);
      addCharacters(1, ELEPHANT);
      storage[0][0]=SAFARI;
      points=0;
      gameStarted=true;
		stopg=false;
      gameOver=false;
      gameWon=false;
      unlocked=false;
      repaint();
   }
         
   public void addCharacters(int amount, int kind)   
   {
      while(amount>0)
      {
         startX = rand.nextInt(rows);
			startY = rand.nextInt(cols);

			if(storage[startY][startY]==0)
			{
				storage[startX][startY]=kind;
				amount--;
			}
		}
	}
   public boolean stopgame()
	{
		return stopg;
	}
   public int totalPoints()
   {
      return points;
   }
   
   //method for ending the game from outside class
   public void gameOver(int w)
   {
      for (int r=0; r<rows;r++)
      {
         for (int c=0; c<cols; c++)
         {
            storage[r][c]=0;
         }
      }
      if (w==1)
         gameOver=true;
      if(w==2)
         gameWon=true;
      gameStarted=false;
      stopg=true;
      isOn=false;
      repaint();
   }
   
   //checks the positions to see if open
   public void move(String move)
   {
		boolean drawCharacter = true;
      for (int r=0; r<rows;r++)
      {
         for (int c=0; c<cols; c++)
         {
            if((storage[r][c]==10||storage[r][c]==20)&&drawCharacter){
					System.out.println("Move is: "+move);
					System.out.println("R:"+r+" C:"+c);
               try
               {
               //checks if anything is open up in 2D array
						if(move=="up")
	               {
							System.out.println("Moving up.");
	                  if(storage[r-1][c]==0)
	                  {
	                     storage[r][c]=0;
	                     storage[r-1][c]=10;
                        points+=10;
	                  }
	                  if(storage[r-1][c]==1&&unlocked==true)
	                  {
	                     points+=30;
	                     storage[r][c]=0;
	                     storage[r-1][c]=20;
	                  }
	                  if(storage[r-1][c]==2)
	                  {	  
								points-=100;
	                     storage[r][c]=0;
	                     storage[r-1][c]=10;
								if(isOn==true)
								{
									addCharacters(1, ELEPHANT);
									unlocked=false;
									isOn=false;
								}
								else 
								{
									stopg=true;          
									gameOver(1);
								}
			
	                  }              
							if(storage[r-1][c]==3)
                     {
                        points+=200;
                        unlocked=true;
                        storage[r][c]=0;
                        storage[r-1][c]=20;
								isOn=true;
                     }
                     /*if(storage[r-1][c]==100)
	                  {
	                     storage[r][c]=0;
	                     storage[r-1][c]=10;
                        points+=500;
                        stopg=true;
                        gameOver();
                      }*/
	                  

	               }
	               //checks if anything is open left in 2D array
	               if(move=="left")
	               {
							System.out.println("Moving left.");
	                  if(storage[r][c-1]==0)
	                  {
	                     storage[r][c]=0;
	                     storage[r][c-1]=10;
                        points+=10;
	                  }
	                  if(storage[r][c-1]==1&&unlocked==true)
	                  {
	                     points+=30;
	                     storage[r][c]=0;
	                     storage[r][c-1]=20;
	                  }
	                  if(storage[r][c-1]==2)
	                  {
	                     points-=100;
	                     storage[r][c]=0;
	                     storage[r][c-1]=10;
								if(isOn==true)
								{
									addCharacters(1, ELEPHANT);
									unlocked=false;
									isOn=false;
								}
								else 
								{
									stopg=true;          
									gameOver(1);
								}
	                  }
                     if(storage[r][c-1]==3)
                     {
                        points+=200;
                        unlocked=true;
                        storage[r][c]=0;
                        storage[r][c-1]=20;
								isOn=true;
                     }
                     /*
                     if(storage[r][c-1]==100)
	                  {
	                     storage[r][c]=0;
	                     storage[r][c-1]=10;
                        points+=500;
                        stopg=true;
                        gameOver();
	                  }*/

	               }
	            //checks if anything is open right in 2D array
	               if(move=="right")
	               {							
	                  if(storage[r][c+1]==0)
	                  {
	                     storage[r][c]=0;
	                     storage[r][c+1]=10;
                        points+=10;								
	                  }
	                  if(storage[r][c+1]==1&&unlocked==true)
	                  {
	                     points+=30;
	                     storage[r][c]=0;
	                     storage[r][c+1]=20;
	                  }
	                  if(storage[r][c+1]==2)
	                  {
	                     points-=100;
	                     storage[r][c]=0;
	                     storage[r][c+1]=10;
								if(isOn==true)
								{
									addCharacters(1, ELEPHANT);
									unlocked=false;
									isOn=false;
								}
								else 
								{
									stopg=true;          
									gameOver(1);
								}

	                  }
                     if(storage[r][c+1]==3)
                     {
                        points+=200;
                        unlocked=true;
                        storage[r][c]=0;
                        storage[r][c+1]=20;
								isOn=true;
                     }
                     if(storage[r][c+1]==100)
	                  {
	                     storage[r][c]=0;
	                     storage[r][c+1]=10;
                        points+=500;
                        stopg=true;
                        gameOver(2);
	                  }

	               }
	               //checks if anything is open down in 2D array
	               if(move=="down")
	               {
	                  if(storage[r+1][c]==0)
	                  {
	                     storage[r][c]=0;
	                     storage[r+1][c]=10;
                        points+=10;
	                  }
	                  if(storage[r+1][c]==1&&unlocked==true)
	                  {
	                     points+=30;
	                     storage[r][c]=0;
	                     storage[r+1][c]=20;
	                  }
	                  if(storage[r+1][c]==2)
	                  {
	                     points-=100;
	                     storage[r][c]=0;
	                     storage[r+1][c]=10;
								if(isOn==true)
								{
									addCharacters(1, ELEPHANT);
									unlocked=false;
									isOn=false;
								}
								else 
								{
									stopg=true;          
									gameOver(1);
								}
	                  }
                     if(storage[r+1][c]==3)
                     {
                        points+=200;
                        unlocked=true;
                        storage[r][c]=0;
                        storage[r+1][c]=20;
								isOn=true;
                     }
                     if(storage[r+1][c]==100)
	                  {
	                     storage[r][c]=0;
	                     storage[r+1][c]=10;
                        points+=500;
                        stopg=true;
                        gameOver(2);
	                  }
                     
	               } 
						repaint();						
						drawCharacter=false;       
               }
               catch( ArrayIndexOutOfBoundsException ae)
               {
                  System.out.println("Sorry cant move there");
               } 
                
            }
         }
      }
      System.out.println("It got to the move method");
      
   }
	
	public void paint(Graphics g)
	{
  		super.paint(g);		
		g.setColor(bgColor);
      g.fillRect(x,y,600,600);    
		if(gameStarted)
      {
         //drawGrid
         for (int r=0; r<rows;r++)
         {
            for (int c=0; c<cols; c++)
            {
               g.setColor(Color.BLACK);
               g.drawRect(x, y, 40, 40);            
               x+=40;
               
            }
            x=0;
            y+=40;
         }
         x=0;
         y=0;
         //drawAnimals
         for (int r=0; r<rows;r++)
         {
            for (int c=0; c<cols; c++)
            {
               if(storage[r][c]==SAFARI)
                  drawSafariHat(g,x,y);
               else if(storage[r][c]==BANANA)
                  drawBanana(g,x,y);
               else if(storage[r][c]==SNAKE)
                  drawSnake(g,x,y);
               else if(storage[r][c]==ELEPHANT)
                  drawElephant(g,x,y);
					else if(storage[r][c]==20)
						drawSafariHat(g,x,y);
					else if (storage[r][c]==100)
					{
						g.setColor(Color.BLACK);
               	g.fillRect(x, y, 40, 40);
					}
					System.out.print(storage[r][c]+"  ");
               x+=40;
            }
            System.out.println();
            x=0;
            y+=40;
         }
         x=0;
         y=0;
      }
      //clears the screen when game is over
      if(gameOver==true)
      {
         super.paint(g);
         setBackground(Color.BLACK);
         g.setColor(Color.WHITE);
         g.setFont(new Font("Helvetica", Font.BOLD, 50));
         g.drawString("GAME OVER",100,400);
      }
      
      //clears the screen when game is Won
      if(gameWon==true)
      {
         super.paint(g);
         setBackground(Color.BLACK);
         g.setColor(Color.WHITE);
         g.setFont(new Font("Helvetica", Font.BOLD, 50));
         g.drawString("YOU WON",100,400);
       } 
          
	}
   
   //draws the snake
   public void drawSnake(Graphics g, int X, int Y)
   {
      g.setColor(Color.RED);
      g.fillOval(X+19,Y+2,2,14);
      g.setColor(Sblue);
      g.fillRoundRect(X+15,Y+7,10,30,10,15);
      g.setColor(Color.BLACK);
      g.fillOval(X+17,Y+11,2,2);
      g.fillOval(X+21,Y+11,2,2);
      
   }
   //method for bananas
   public void drawBanana(Graphics g, int X, int Y)
   {
      //checks to see if your able to get bananas and draws the red box accordingly
      if(unlocked==false)
      {
         g.setColor(Color.RED);
         g.drawRect(X,Y,40,40);
      }
      g.setColor(Byellow);
      g.fillOval(X+7, Y+5,25,30);
      g.setColor(bgColor);
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
     g.fillOval( x +5, y+15, 25, 20 );
     //head
	  g.fillOval( x +24, y+10, 13, 12); 
	  //ears
     g.fillOval( x +23, y +7, 9, 9 );
	  //trunk used a polygon so the rectangle will be rotated
     Polygon trunk = new Polygon();
	  trunk.addPoint(x+35,y+13);
	  trunk.addPoint(x+46,y+17);
	  trunk.addPoint(x+45,y+22);
	  trunk.addPoint(x+33,y+18);
	  g.fillPolygon(trunk);
	  //tail at an angle
	  Polygon rectangle = new Polygon();
	  rectangle.addPoint(x +9,y+18);
	  rectangle.addPoint(x +9,y+21);
	  rectangle.addPoint(x -3,y + 26);
	  rectangle.addPoint(x,y +23);
	  g.fillPolygon(rectangle);
     //flatten bottom
      g.clearRect( x +5, y + 31, 35, 4 );
     //feet
	  Polygon triangle = new Polygon();
	  triangle.addPoint(x +12,y + 28);
	  triangle.addPoint(x +7,y + 35);
	  triangle.addPoint(x +17,y + 35);
	  g.fillPolygon(triangle);  
	  Polygon triangle2 = new Polygon();	  	  
	  triangle2.addPoint(x +24, y + 28);
	  triangle2.addPoint(x +19,y + 35);
	  triangle2.addPoint(x +29,y + 35);
	  g.fillPolygon(triangle2);

   }

}