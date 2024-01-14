
/*ICS3U1 CPT: Rob the Bank
 * Program description: A choose your own adventure game where you attempt to rob a bank; Every choice is important.
 * robber: the player; choices are made through the Jpanels: BankEntry, BankChoice,HallwayChoice, and watercooler. And the game adjusts based off of your choices
 * - the robber class is also used to make the robber move or fly
 * - I used while loops to prevent the player from closing any of the Jpanels
 * - the background timer controls all options
 * */


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class RobtheBank extends JPanel implements ActionListener ,  MouseMotionListener {

	private Robber robber;
	private int WIDTH = 700, HEIGHT = 500;

	//title, robber,
	private ImageIcon imgTitle,imgTitle2,trolldance,coolface,robberstand,namedrobber,sadrob;

	//bank + background
	public ImageIcon bankbackground,bankoutside;

	//choices
	private int BankChoice, HallwayChoice;

	//timer
	private Timer background;
	private int seconds;

	//watercooler
	private int watercooler;
	private boolean changescreen1,thegame, gamebegin,roof;
	private boolean capequip,capeinvisible,speedshoes,crashcheck,rip;
	private boolean robbermove,notinvisible,guardedoorinvisible,guardedoorvisible;
	private boolean spotted,jailed,knockout,win,win2;
	private boolean knockoutguard1, knockoutguard2;
	public boolean hallway;

	public ImageIcon crash,pensiverob;
	public ImageIcon guard1,guard2;
	public ImageIcon robberbackwardvisible,robberbackwardinvisible;
	public ImageIcon fist,teleporter,obvious;

	//fist
	private int fistX = 200, fistY = 100;

	private Font f;
	private FontMetrics fm;
	private String name;
	private int strWidth;
	private int BankEntry;


	//end screens
	Random rnd = new Random();

	public RobtheBank()
	{
		//Start screen image icons
		imgTitle = new ImageIcon("title 1.gif");
		imgTitle2 = new ImageIcon("title 2.png");
		trolldance = new ImageIcon("troll icon 2.gif");

		//player images
		robberstand = new ImageIcon("robber.png");
		robberbackwardvisible = new ImageIcon("rob cape visible backwards.png");
		robberbackwardinvisible = new ImageIcon("rob cape invisible backwards.png");

		//icons for Jpanel
		coolface = new ImageIcon("thisface.png");
		namedrobber = new ImageIcon("named robber.png");
		sadrob = new ImageIcon("sadrob.jpg");
		pensiverob = new ImageIcon("robber frown.png");
		obvious = new ImageIcon("stuff.jfif");

		//bank setting 1
		bankbackground = new ImageIcon("bank outside.png");
		bankoutside = new ImageIcon("outside bank.png");

		//crash bubble
		crash = new ImageIcon("crash.png");

		//guards
		guard1 = new ImageIcon("guard 1.png");
		guard2 = new ImageIcon("guard 2.png");

		//fist
		fist = new ImageIcon("fist.png");

		//telporter
		teleporter = new ImageIcon("teleporter.png");


		//background timer
		background =  new Timer(1000,this);

		//robber
		robber = new Robber(100,200);

		setLayout(null);
		setFocusable(true);
		addMouseMotionListener(this);
		requestFocus();

		//Jframe
		JFrame frame = new JFrame();
		frame.setContentPane(this);
		frame.setTitle("Rob the Bank");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.setVisible(true);
		frame.setResizable(false);

		// Initialize the background timer
		background.start();
		seconds = 0;

		//changes the first screen
		changescreen1 = false;
		thegame = false;

		//starts the game
		gamebegin = false;

		//robber move animations
		robbermove = false;

		//to get to the roof
		roof = false;

		//items and their results
		capequip = false;
		speedshoes = false;
		crashcheck = false;
		rip = false;

		//guarded door
		guardedoorinvisible = false;
		guardedoorvisible = false;

		//if robber is spotted
		spotted = false;

		// to knock guards out
		knockoutguard1 = false;
		knockoutguard2 = false;



		
	}


	public static void main(String[] args)
	{

		new RobtheBank();
		new Robber(10, 20);

	}





@Override
public void actionPerformed(ActionEvent e)
{
	seconds++;
	//background timer
	if(e.getSource() == background)
	{

		if(seconds == 2)
		{
			changescreen1 = true;
			repaint();
		}
		if(seconds == 5)
		{
			thegame = true;
		}
		if(seconds == 7)
		{
			gamebegin = true;

		}
		if(seconds == 9)
		{
			//instructions
			JOptionPane.showMessageDialog(null, "You are a bank robber, your objective is to get to the vault at the back of the bank by any means.",
					"Instructions", JOptionPane.INFORMATION_MESSAGE,coolface);
		}
		if(seconds == 10)
		{
			//to name the robber
			 name = JOptionPane.showInputDialog(null, "What would you like the robber's name to be?", "Robber namer", JOptionPane.INFORMATION_MESSAGE);
			while(true)
			{
				try
				{
					if(name.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Hey! You forgot to give him a name! " , "Name", JOptionPane.INFORMATION_MESSAGE, sadrob);
						name = JOptionPane.showInputDialog(null, "What would you like the robber's name to be?", "Robber namer", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "His name is " + name, "Name", JOptionPane.INFORMATION_MESSAGE, namedrobber);

						//fonts
						f = new Font("Britannic Bold", Font.PLAIN, 35);
						fm = getFontMetrics(f);
						strWidth = fm.stringWidth(name);

						break;
				}
				}
				catch(NullPointerException i)
				{
					JOptionPane.showMessageDialog(null, "Hey! You forgot to give him a name! " , "Name", JOptionPane.INFORMATION_MESSAGE, sadrob);
					name = JOptionPane.showInputDialog(null, "What would you like the robber's name to be?", "Robber namer", JOptionPane.INFORMATION_MESSAGE);
				}

				}
		}

		if(seconds == 11)
		{
			//option buttons
			String[] buttons = { "1.", "2." };

		     BankEntry = JOptionPane.showOptionDialog(null, "How would you like to enter the bank?"
			 	 		+ "\n1. Walk in through the front door\n2. Climb the roof", "Bank entry",
						        JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[1]);

		     	while (true)
		     	{
				if(BankEntry == 0)
				{
				bankoutside = new ImageIcon("inside bank.png");
				JOptionPane.showMessageDialog(null, "You are now inside " , "Bank inside", JOptionPane.INFORMATION_MESSAGE, namedrobber);

				 BankChoice = JOptionPane.showOptionDialog(null, "You have two items with you, Which would you like to use?"
				 		+ "\n1. Speed shoes\n2. Invisible cape", "Bank choice",
					        JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[1]);

				 	if (BankChoice == 0)
				 		{
						//speed shoes
						speedshoes = true;
						JOptionPane.showMessageDialog(null, "You have chosen speed shoes.",
								"Instructions", JOptionPane.INFORMATION_MESSAGE,coolface);
						robbermove = true;
						break;
				 		}

				 	else if (BankChoice == 1)
					{
						capequip = true;
						break;
					}

				 }
				else if(BankEntry == 1)
				{

				 //roof route begins
				 bankoutside = new ImageIcon("bank side.png");
				 roof = true;

				break;
				}

				else
				{
				 JOptionPane.showMessageDialog(null, "You must make a choice!" , "Bank entry", JOptionPane.INFORMATION_MESSAGE, sadrob);
				 BankEntry = JOptionPane.showOptionDialog(null, "How would you like to enter the bank?"
					 		+ "\n1. Walk in through the front door\n2. Climb the roof", "Bank entry",
						        JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[1]);
				 break;

				}

				}
		}
		//storage room
		else if (BankEntry == 1 && seconds == 18)
		{
			bankoutside = new ImageIcon("storage room.png");
			JOptionPane.showMessageDialog(null, "You find youself inside of a storage room" , "Storage room", JOptionPane.INFORMATION_MESSAGE, coolface);
		}
		else if(BankEntry == 1 && seconds == 21)
		{
			String[] buttons3 = { "Look around.", "Don't look around" };

		int	lookaround = JOptionPane.showOptionDialog(null, "Would you like to look around?",
		        "Storage room", JOptionPane.INFORMATION_MESSAGE, 0, null, buttons3, buttons3[1]);

		//if robber decides to look around
		if (lookaround == 0)
		{
			JOptionPane.showMessageDialog(null, "You found a teleporter!" , "Storage room", JOptionPane.INFORMATION_MESSAGE, teleporter);
			win2 = true;
			JOptionPane.showMessageDialog(null, "You teleported straight to the vault" , "Vault", JOptionPane.INFORMATION_MESSAGE, obvious);
			JOptionPane.showMessageDialog(null, "You win!" , "Winner", JOptionPane.INFORMATION_MESSAGE, coolface);
			System.exit(0);
		}
		//if robber decides not to look around
		else if (lookaround == 1)
		{
			JOptionPane.showMessageDialog(null, "You did not look around" , "Storage room", JOptionPane.INFORMATION_MESSAGE, coolface);
			jailed = false;
			bankoutside = new ImageIcon("tripped.png");
			JOptionPane.showMessageDialog(null, "You tripped over a box and knocked yourself out" , "Storage room", JOptionPane.INFORMATION_MESSAGE, sadrob);
			System.exit(0);

		}

		}
		//robber crashes into a wall
		else if(BankEntry == 0 && BankChoice == 0 && seconds == 13)
		{
			crashcheck = true;
		}
		else if (BankEntry == 0 && BankChoice == 0 && seconds == 15)
		{
			crashcheck = false;
			rip = true;
			JOptionPane.showMessageDialog(null, "You crashed into a wall.",
					"Rip", JOptionPane.INFORMATION_MESSAGE,pensiverob);
			robbermove = false;
		}
		else if(BankEntry == 0 && BankChoice == 0 && seconds == 16)
		{
			JOptionPane.showMessageDialog(null, "Game over :/", "You lost", JOptionPane.INFORMATION_MESSAGE,pensiverob);
			System.exit(0);
		}
		//robber puts on a invisible cape
		 if(BankChoice == 1 && seconds == 13)
			{
				capeinvisible = true;
				JOptionPane.showMessageDialog(null, "You are invisible",
						"Invisibility", JOptionPane.INFORMATION_MESSAGE,coolface);
			}

		//hallway
		 else if(BankChoice == 1 && seconds == 15)
			{
				hallway = true;

				 while(true)
				 {
					 JOptionPane.showMessageDialog(null, "You have entered a hallway",
								"Hallway", JOptionPane.INFORMATION_MESSAGE,coolface);

						String[] buttons2 = { "left", "right" };
						 HallwayChoice = JOptionPane.showOptionDialog(null, "Which way would you like to go?", "Hallway ",
								        JOptionPane.INFORMATION_MESSAGE, 0, null, buttons2, buttons2[1]);

				//the robber chooses to go left
				 if (HallwayChoice == 0)
				 {
					 hallway = false;
					 bankoutside = new ImageIcon("water cooler.png");

					 //buttons
					 String[] buttons3 = { "take a drink", "do not take a drink" };



					 JOptionPane.showMessageDialog(null, "there is a water cooler",
								"water", JOptionPane.INFORMATION_MESSAGE,coolface);


					watercooler = JOptionPane.showOptionDialog(null, "Would you like to take a drink?", "Hallway",
					        JOptionPane.INFORMATION_MESSAGE, 0, null, buttons3, buttons3[1]);

					//watercooler
					if (watercooler == 0)
					 {
						notinvisible = true;
						JOptionPane.showMessageDialog(null, "you are no longer invisible",
								"water", JOptionPane.INFORMATION_MESSAGE,sadrob);
						hallway = true;
						notinvisible = false;
					 }


					else if (watercooler == 1)
					{
						hallway = true;

					}
						break;
				 }
				//the robber chooses to go right
				 else if(HallwayChoice == 1)
				 {
					 JOptionPane.showMessageDialog(null, "you continued down the hallway",
								"continue", JOptionPane.INFORMATION_MESSAGE,coolface);
					 guardedoorinvisible = true;
					 knockout = true;
					 JOptionPane.showMessageDialog(null, "drag the fist to knock out the guards",
								"continue", JOptionPane.INFORMATION_MESSAGE,coolface);
					 break;
				 }


		}

			}
		 //what happens if you're invisible in front of the guards and you turned left
		if( BankChoice == 1 && HallwayChoice == 0 && watercooler == 1 && seconds == 18)
		 {
			  guardedoorinvisible = true;
		 }
		 else if(BankChoice == 1 && HallwayChoice == 0 && watercooler == 1 && seconds == 20)
		 {
			 JOptionPane.showMessageDialog(null, "drag the fist to knock out the guards",
						"knockout", JOptionPane.INFORMATION_MESSAGE,coolface);
			 knockout = true;
		 }
		 //what happens if you're invisible in front of the guards and you turned right
		 else if(BankChoice == 1 && HallwayChoice == 0 && watercooler == 1 && knockoutguard1 && knockoutguard2 )
		 {
			 win = true;
			 JOptionPane.showMessageDialog(null, "You win!",
						"Winner!", JOptionPane.INFORMATION_MESSAGE,namedrobber);
			 System.exit(0);

		 }
		 else if(BankChoice == 1 && HallwayChoice == 1 && knockoutguard1 && knockoutguard2 )
		 {
			 win = true;
			 JOptionPane.showMessageDialog(null, "You win!",
						"Winner!", JOptionPane.INFORMATION_MESSAGE,namedrobber);
			 System.exit(0);

		 }
		 //what happens if you're not invisible in front of the guards and you turned left
		   if(BankChoice == 1 && HallwayChoice == 0 && watercooler == 0 && seconds == 18)
		  {
			  guardedoorvisible = true;
		  }
		  else if(BankChoice == 1 && HallwayChoice == 0 && watercooler == 0 && seconds == 20)
		  {
			  spotted = true;
		  }
		  else if(BankChoice == 1 && HallwayChoice == 0 && watercooler == 0 && seconds == 22)
		  {
			  jailed = true;
		  }
		  else if(BankChoice == 1 && HallwayChoice == 0 && watercooler == 0 && seconds == 24)
		  {

			 int endingmessage = rnd.nextInt(3) + 1;
			 if (endingmessage == 1)
			 {
				 JOptionPane.showMessageDialog(null, "you lose",
							"Jailed!", JOptionPane.INFORMATION_MESSAGE,sadrob);
				  System.exit(0);
			 }
			 else if(endingmessage == 2)
			 {
				 JOptionPane.showMessageDialog(null, "you were caught and therefore sentenced to life to the slammer :/",
							"Jailed!", JOptionPane.INFORMATION_MESSAGE,sadrob);

				 System.exit(0);
			 }
			 else if(endingmessage == 3)
			 {
				 JOptionPane.showMessageDialog(null, "you were spotted by the guards xd",
							"Jailed!", JOptionPane.INFORMATION_MESSAGE,sadrob);
				 System.exit(0);
			 }

		  }


	}
}




	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		Rectangle2D guard1mask = new Rectangle2D.Double(100, 200, guard1.getIconWidth(), guard1.getIconHeight());
		Rectangle2D guard2mask = new Rectangle2D.Double(400, 200, guard2.getIconWidth(), guard2.getIconHeight());
		Rectangle2D fistmask = new Rectangle2D.Double(fistX, fistY, fist.getIconWidth(), fist.getIconHeight());

		//opening screen
		if (changescreen1)
		{
			g2.drawImage(imgTitle.getImage(),100,300,this);
			g2.drawImage(trolldance.getImage(),220,50,this);
			repaint();
		}
		if(thegame)
		{
			g2.drawImage(imgTitle2.getImage(),210,400,this);
		}
		//the background for the game has appeared
		if(gamebegin)
		{
			g2.drawImage(bankbackground.getImage(),-10, -100,this);
			g2.drawImage(bankoutside.getImage(),-10,-98,this);
			g2.drawImage(robberstand.getImage(), robber.getX(0), robber.getY(-100), this);
		}
		//robber flies up to the roof
		if (roof)
		{
			g2.drawImage(robberstand.getImage(), robber.getX(100), robber.getY(-100), this);
			robber.fly();
			guardedoorvisible = false;

		}
		//cape has been equipped
		if(capequip)
		{
			robberstand = new ImageIcon("rob cape.png");

			if(capeinvisible)
			{
				robberstand = new ImageIcon("rob cape 2.png");
			}
		}
		//speed shoes has been equipped
		else if (speedshoes)
		{
			robberstand = new ImageIcon("rob shoes.png");

			if(crashcheck)
			{
				g2.drawImage(crash.getImage(), 260, 120, this);

			}
		}

		//robber zoomed off screen, then crashed
		if (robbermove)
		{
			robber.move();

		}

		if(rip)
		{
			bankoutside = new ImageIcon("here lies.png");
			crash = new ImageIcon("this picture does not exist");
			g2.setFont(f);
			g2.drawString(name, 325, 200);

		}
		//hallway
		if(hallway)
		{
			bankoutside = new ImageIcon("hallway.png");
			robberstand = new ImageIcon("this picture does not exist");
		}
		//robber is not invisible
		if(notinvisible)
		{
			robberstand = new ImageIcon("rob cape visible.png");

		}
		//robber is invisible and standing infront of a vault
		if(guardedoorinvisible)
		{
			bankoutside = new ImageIcon("vault door.png");
			robberstand = new ImageIcon("this picture does not exist");
			g2.drawImage(robberbackwardinvisible.getImage(),230,259,this);
			g2.drawImage(guard1.getImage(),100,200,this);
			g2.drawImage(guard2.getImage(), 400,200, this);
			hallway = false;

		}
		//robber is visible and standing infront of a vault
		if(guardedoorvisible)
		{
			bankoutside = new ImageIcon("vault door.png");
			robberstand = new ImageIcon("this picture does not exist");
			g2.drawImage(robberbackwardvisible.getImage(),230,259,this);
			g2.drawImage(guard1.getImage(),100,200,this);
			g2.drawImage(guard2.getImage(), 400,200, this);
			hallway = false;
		}
		//robber has been spotted
		if(spotted)
		{
			guard1 = new ImageIcon("guard 1 mad.png");
			guard2 = new ImageIcon("guard 2 hey...png");
			robberbackwardvisible = new ImageIcon("this picture does not exist");
		}
		//robber has jailed
		if(jailed)
		{
			bankoutside = new ImageIcon("jailed.png");
			guard1 = new ImageIcon("no image");
			guard2 = new ImageIcon("no image");
			robberbackwardinvisible = new ImageIcon("no image");

		}

		//to knockout the guards
		if(knockout)
		{
			g2.drawImage(fist.getImage(), fistX, fistY, this);


				if (guard1mask.intersects(fistmask))
				{
					knockoutguard1 = true;
					guard1  = new ImageIcon("guard 1 knocked out.png");
				}
				else if (guard2mask.intersects(fistmask))
				{
					knockoutguard2 = true;
					guard2  = new ImageIcon("guard 1 knocked out.png");

				}
			}
		//winning endings
		if (win)
		{
			fist = new ImageIcon("image");
			guard1 = new ImageIcon("image");
			guard2 = new ImageIcon("image");
			bankoutside = new ImageIcon("vault.png");
		}
		if(win2)
		{
			bankoutside = new ImageIcon("vault.png");
		}
		repaint();
		}










	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseDragged(MouseEvent e) {
		fistX = e.getX();
		fistY = e.getY();
		repaint();

	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}





