package code;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.Scanner;
import java.io.*;

public class JungleGame extends JFrame
{
	private Container contents;
	private JGameboard game;
   private JButton start;
   private JLabel []black;
   private JLabel highscore,tlabel, tcount, pointsLabel;
	private JTextField points;
   private JTextArea dir;
   private JPanel directions, dummy1, dummy2,controls,TIME;
   private JRadioButton one, two, three;
   private ButtonGroup levelGroup;
   private KeyListenerHandler klh;
	Boolean stop=false;
   private Timer timer;
	
   DecimalFormat df= new DecimalFormat("00");
   private int level=1, hscore=0,yourscore, cdown;
   
	public JungleGame()
	{
  		super("Jungle Maze"); 	
		contents=getContentPane();
   	contents.setLayout(new BorderLayout(10,10));
      contents.setBackground(new Color(153,255,153));
   	
      //direction panel
      dir = new JTextArea("       The Goal of this game is to capture as many bananas as possible and get to the finish line in the time given.     \n"
                        + "       You are the safari hat. Since the bananas are up in the tree, the only way you can reach them is by getting      \n"
								+ "       on the elephant first. Once you've reached the elephant the bananas will be free to grab. If you run into a      \n"
								+ "       a snake while on the elephant you'll lose your elephant and if your not on elephant you will die.     \n"
                        + "       Use your keyboard arrows to move. Press start to begin.");
      dir.setEditable(false);
		 
      directions= new JPanel();
      directions.setBackground(new Color(33,142,33));
      directions.setOpaque(true);
      directions.setLayout(new FlowLayout());
		directions.setPreferredSize(new Dimension(700,150));
      directions.add(dir);
      
		//Timer
		tlabel=new JLabel("TIMER", SwingConstants.CENTER);
		tlabel.setForeground(Color.WHITE);
      tlabel.setFont(new Font("Helvetica", Font.BOLD, 10));
		tcount=new JLabel(" 30 ", SwingConstants.CENTER);
		tcount.setBackground(Color.WHITE);
		tcount.setOpaque(true);
		TIME=new JPanel();
		TIME.setLayout(new GridLayout(2,1,10,1));
		TIME.setPreferredSize(new Dimension(50,50));
		TIME.setBackground(Color.BLACK);
		TIME.add(tlabel);
		TIME.add(tcount);
		directions.add(TIME);
      
      //score
		highscore= new JLabel("Current High Score:   "+ hscore+ "  ");
		highscore.setBackground(new Color(192,192,192));
		highscore.setOpaque(true);
		directions.add(highscore);
      pointsLabel= new JLabel("      Your Score ");
      points= points=new JTextField("0",5);
		points.setEditable(false);
      
      directions.add(pointsLabel);
      directions.add(points);

      contents.add(directions,BorderLayout.NORTH);
      
      //left and right spacing panels
      dummy1= new JPanel();
      dummy1.setBackground(new Color(152,103,54));
      dummy1.setPreferredSize(new Dimension(50,560));  
      dummy2= new JPanel();
      dummy2.setBackground(new Color(152,103,54));
      dummy2.setPreferredSize(new Dimension(50,560));
      contents.add(dummy1,BorderLayout.WEST);
      contents.add(dummy2,BorderLayout.EAST);
		
		      
      //control panel for level and start buttons
      one= new JRadioButton("Beginner",true);
      one.setOpaque(false);
      two= new JRadioButton("Intermediate");
      two.setOpaque(false);
      three= new JRadioButton("Extreme");
      three.setOpaque(false);
      levelGroup = new ButtonGroup();
      levelGroup.add(one);
      levelGroup.add(two);
      levelGroup.add(three);
      
      RadioButtonHandler rbh= new RadioButtonHandler();
      one.addItemListener(rbh);
      two.addItemListener(rbh);
      three.addItemListener(rbh);
      
      start= new JButton("START");
      ButtonHandler bh= new ButtonHandler();
      start.addActionListener(bh);
            		
		
		
      //control panel
      controls= new JPanel();
		controls.setPreferredSize(new Dimension (700,75));
      controls.setBackground(new Color(33,142,33));
      controls.setLayout(new GridLayout(3,4));
		black= new JLabel[4];
		for(int i=0; i<4; i++)
      {
         black[i]= new JLabel("");
         black[i].setBackground(Color.GRAY);
         controls.add(black[i]);
      }
      controls.add(one);
      controls.add(two);
      controls.add(three);
      controls.add(start);  
      //adds blank spacing
      for(int i=0; i<4; i++)
      {
         black[i]= new JLabel("");
         black[i].setBackground(Color.GRAY);
         controls.add(black[i]);
      }
      contents.add(controls, BorderLayout.SOUTH);
      
   
   	//gameboard instantiation
      game=new JGameboard();
      klh= new KeyListenerHandler();
      start.addKeyListener(klh);

   	contents.add(game, BorderLayout.CENTER);    
            
   	setSize(681,828);
   	setVisible(true);
	
	
	}
   //method for reading the previous  high score in the file
	public void readHighScore()
	{
		try
		{	
		Scanner file= new Scanner(new File("highscore.txt"));		
			while(file.hasNext())
			{
				hscore=file.nextInt();
				System.out.println(""+hscore);
			}
			file.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("File not Found");
		}
		
	}
	
   //method for checking and writing new high score
	public void writeHighScore()
	{
		try
		{
			FileOutputStream fos= new FileOutputStream("highscore.txt", false);
			PrintWriter pw= new PrintWriter(fos);
			if(yourscore>hscore)
			{
				pw.print(yourscore);
				highscore.setText("congrats new high score:  " + yourscore);
				hscore=yourscore;
			}
			else
				highscore.setText("Current high score:  "+ hscore);
			pw.close();
		}
		catch(FileNotFoundException fnfe)
		{
		}
	}

		
   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed (ActionEvent ae)
      {
   		//starts the game and also starts the timer
         readHighScore();      
			game.play(level);
         TimeCounter tc= new TimeCounter(31);
         timer= new Timer(1000,tc);
         timer.start();
			if(cdown>0&&cdown<30)
			{
            tcount.setText("00");
            game.gameOver(1);
            cdown=30;
				timer.stop();
            
				game.play(level);
				timer.start();
				
			}
      }
   }
   
   public class TimeCounter implements ActionListener
   {
      //new timer listener
      public TimeCounter(int Counter)
      {
         cdown=Counter;
      }
      public void actionPerformed(ActionEvent tc)
      {
         cdown--;
         if (cdown>0)
            tcount.setText("" + df.format(cdown));
         
			else
         {
            cdown=0;
            timer.stop();
            tcount.setText("00");
				yourscore= game.totalPoints();
            game.gameOver(1);
				writeHighScore();
         }
			if(stop==true)
			{
            cdown=0;
				timer.stop();
				stop=false;
				tcount.setText("00");
				yourscore= game.totalPoints();
				writeHighScore();
			}
      }
   }
   
   private class KeyListenerHandler implements KeyListener
   {
      public void keyPressed(KeyEvent e)
      {
         //System.out.println("It got to the move method");
         if (e.getKeyCode()==e.VK_UP)
            game.move("up");
         else if (e.getKeyCode()==e.VK_DOWN)
            game.move("down");
         else if (e.getKeyCode()==e.VK_RIGHT)    
			   game.move("right");
         else if (e.getKeyCode()==e.VK_LEFT)    
            game.move("left");
         points.setText(""+ game.totalPoints()); 
			stop=game.stopgame();
      }
		public void keyTyped (KeyEvent e){}
      public void keyReleased(KeyEvent e){}
   }
   
   private class RadioButtonHandler implements ItemListener
   {
      public void itemStateChanged(ItemEvent ie)
      {
         if(ie.getSource()==one)
            level=1;
         if(ie.getSource()==two)
            level=2;
         if(ie.getSource()==three)
            level=3; 
      }
   }
   
   //main
	public static void main (String[] args)
	{
   	JungleGame jg= new JungleGame();
   	jg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}