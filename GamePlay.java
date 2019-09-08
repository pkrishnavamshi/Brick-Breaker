package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //movement of ball
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;    //detects key pressed on keyboard
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener,ActionListener {
	
	private boolean play = false;                   //game should not start on opening it
	private int score = 0; 
	
	private int totBricks = 21;                    //no of bricks
	
	private Timer timer;                           //speed of ball
	private int delay = 5;                        //speed of timer

	private int playerX = 310;                    //position of slider on start
	
	private int ballposX = 120;                   //initial position of balls
	private int ballposY = 350;
	private int ballXdir = -1;                    //direction of balls
	private int ballYdir = -2;
	private MapGenerator map;                     //create mapgenerator object
	public GamePlay() {                            //create class with above properties
		map=new MapGenerator(3,7);
		
		addKeyListener(this);
		setFocusable(true);                         //area of textfield
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g) {           
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//draw bricks
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.blue);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
        g.fillRect(680, 0, 3,592 );
        
        //score
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("arial",Font.BOLD,20));
        g.drawString(""+score,590, 30);
        
        if(totBricks==0)
        {play=false;
    	ballYdir=0;
    	ballXdir=0;
    	g.setColor(Color.RED);
    	g.setFont(new Font("comicsanms",Font.BOLD,60));
    	g.drawString("YOU WON", 190, 300);
    	g.setFont(new Font("comicsanms",Font.BOLD,20));
    	g.drawString("PRESS ENTER TO RESART", 260, 370);
     	
        	
        }
        if(ballposY>570)
        {
        	play=false;
        	ballYdir=0;
        	ballXdir=0;
        	g.setColor(Color.RED);
        	g.setFont(new Font("comicsanms",Font.BOLD,60));
        	g.drawString("GAMEOVER", 190, 300);
        	g.setFont(new Font("comicsanms",Font.BOLD,20));
        	g.drawString("PRESS ENTER TO RESART", 260, 370);
         	
        }
        
        
        //paddle
        g.setColor(Color.white);
        g.fillRect(playerX, 550, 100, 8);
        
        //ball
        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        g.dispose();
     }
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play)
		{
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
				ballYdir=-ballYdir;
			
			A: for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++)
				{
					if(map.map[i][j]>0)
					{
						Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
						Rectangle brickrect=new Rectangle(j*map.brickwidth+80, i*map.brickheight+50, map.brickwidth,map. brickheight);
						if(ballrect.intersects(brickrect)) {
							
								map.setbrickvalue(0, i, j);
							  totBricks--;
							  score++;
							  
							  if(ballposX+19<=brickrect.x || ballposX+1>= brickrect.x+brickrect.width)
							  {ballXdir=-ballXdir;}
							  else {
								  ballYdir=-ballYdir;
							  }
							  break A;
							 
							}
							
						    
					}
				}
				
			}
		}
		
		ballposX+=ballXdir;
		ballposY+=ballYdir;
		
		if(ballposX<0) {
			ballXdir= -ballXdir;
		} 
		if(ballposY<0) {
			ballYdir= -ballYdir;
		}
		if(ballposX>670) {
			ballXdir= -ballXdir;
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX>=600) {
				playerX=600;
			}
			else
				moveRight();
		}
	
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX<=10) {
				playerX=10;
			}
			else
				moveLeft();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER)	
		{
			if(!play)
			{
				play = false;                   
		        score = 0; 
		        totBricks = 21;                  
				delay = 5;                       
				playerX = 310;                    				
				ballposX = 120;                   
				ballposY = 350;
				ballXdir = -1;                    
				ballYdir = -2;
				map=new MapGenerator(3,7);
				
				repaint();
			}
		}
		
	}

	public void moveRight() {
		play = true;
		playerX+=20;
		
	}
	
	public void moveLeft() {
		play = true;
		playerX-=20;
	}

}
