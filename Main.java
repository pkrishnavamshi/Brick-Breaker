package brickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//JFrame is used for outerwindow (i.e., minimise,close,etc)
		JFrame frame = new JFrame();
		GamePlay gameplay= new GamePlay();                               //panel in which we run our game
		frame.setBounds(10, 10, 700, 600);              				 //size of window
		frame.setTitle("BreakWithBall");                 				 //name of frame
		frame.setResizable(false);                      				 //whether frame is resizable
		frame.setVisible(true);                                          //whether frame is visible or not
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //close operation
		frame.add(gameplay);                                             //panel is added to frame

	}

}
