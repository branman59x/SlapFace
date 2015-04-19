import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import java.applet.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import java.net.*;
//import JMyron.*;

public class SlapFace 
{
	private static JFrame frame;
	private static JLabel imgLabel;
	private static JLabel slapLabel;
	private static Timer timer;
	private static int slaps;
	private static JRadioButton defaultBtn, customBtn;
	private static JButton startBtn, custLBtn, custRBtn, custCBtn, custChokeBtn, custTauntBtn;
	private static String leftFace, rightFace, centerFace, fistFace, tauntFace;
	private static JFileChooser fc;
	private static boolean custom, setTaunt, setFist;
	
	public static void main(String[] args) throws IOException
	{
		start();
	}
	
	private static void start() throws IOException
	{
		if (frame != null)
			frame.dispose();
		frame = new JFrame("SlapFace");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0,1));
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("beach.jpg"))));
		background.setLayout(new GridLayout(0, 1));
		frame.setContentPane(background);
		JLabel label1 = new JLabel("Welcome to the wonderful world of stress relief...");
		label1.setFont(new Font("Lucida Calligraphy",Font.BOLD,18));
		background.add(label1);
		JLabel label2 = new JLabel("Please choose your stress relief assistant:");
		label2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
		background.add(label2);
		defaultBtn = new JRadioButton("Default");
		defaultBtn.setFont(new Font("Harrington",Font.BOLD,18));
		customBtn = new JRadioButton("Custom");
		customBtn.setFont(new Font("Harrington",Font.BOLD,18));
		ButtonGroup grp = new ButtonGroup();
		JPanel pnl1 = new JPanel(), pnl2 = new JPanel();
		pnl1.setLayout(new GridLayout(0, 2));
		pnl1.add(new JLabel());
		pnl1.setOpaque(false);
		pnl2.setLayout(new GridLayout(0, 2));
		pnl2.add(new JLabel());
		pnl2.setOpaque(false);
		grp.add(defaultBtn);
		grp.add(customBtn);
		defaultBtn.addChangeListener(new ChoiceListener());
		customBtn.addChangeListener(new ChoiceListener());
		pnl1.add(defaultBtn);
		pnl2.add(customBtn);
		background.add(pnl1);
		background.add(pnl2);
		custLBtn = new JButton("Left Face");
		custLBtn.setVisible(false);
		custLBtn.addActionListener(new FileChooserListener());
		custRBtn = new JButton("Right Face");
		custRBtn.setVisible(false);
		custRBtn.addActionListener(new FileChooserListener());
		custCBtn = new JButton("Center Face");
		custCBtn.setVisible(false);
		custCBtn.addActionListener(new FileChooserListener());
		custChokeBtn = new JButton("Choke Face (Optional)");
		custChokeBtn.setVisible(false);
		custChokeBtn.addActionListener(new FileChooserListener());
		custTauntBtn = new JButton("Taunt Face (Optional)");
		custTauntBtn.setVisible(false);
		custTauntBtn.addActionListener(new FileChooserListener());
		JPanel pnl3 = new JPanel();
		pnl3.setLayout(new GridLayout(0, 2));
		pnl3.add(custChokeBtn);
		pnl3.add(custTauntBtn);
		background.add(custLBtn);
		background.add(custRBtn);
		background.add(custCBtn);
		pnl3.setOpaque(false);
		background.add(pnl3);
		startBtn = new JButton("Commence Reliefment!");
		startBtn.setEnabled(false);
		startBtn.addActionListener(new StartListener());
		startBtn.setFont(new Font("Georgia",Font.BOLD,18));
		background.add(startBtn);
		slapLabel = new JLabel();
		//frame.add(new ImagePanel("beach.jpg"));
		frame.setSize(504, 590);
		fc = new JFileChooser();
		leftFace = rightFace = centerFace = fistFace = tauntFace = "";
		frame.setVisible(true);
		setTaunt = true;
		setFist = true;
	}
	
	private static class FileChooserListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int val = fc.showOpenDialog(frame.getContentPane());
			if (val == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				JButton src = (JButton)e.getSource();
				if (src.equals(custLBtn))
				{
					leftFace = file.getPath();
					custLBtn.setForeground(new Color(57, 204, 59));
				}
				else
					if (src.equals(custRBtn))
					{
						rightFace = file.getPath();
						custRBtn.setForeground(new Color(57, 204, 59));
					}
					else
						if (src.equals(custCBtn))
						{
							centerFace = file.getPath();
							custCBtn.setForeground(new Color(57, 204, 59));
						}
						else
							if (src.equals(custChokeBtn))
							{
								fistFace = file.getPath();
								custChokeBtn.setForeground(new Color(57, 204, 59));
							}
							else
								if (src.equals(custTauntBtn))
								{
									tauntFace = file.getPath();
									custTauntBtn.setForeground(new Color(57, 204, 59));
								}
				if (!leftFace.equals("") && !rightFace.equals("") && !centerFace.equals(""))
					startBtn.setEnabled(true);
				else
					startBtn.setEnabled(false);
			}
		}
	}
	
	private static class ChoiceListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			JRadioButton src = (JRadioButton)e.getSource();
			if (src.getText().equals("Default") && src.isSelected())
			{
				custLBtn.setVisible(false);
				custRBtn.setVisible(false);
				custCBtn.setVisible(false);
				custChokeBtn.setVisible(false);
				custTauntBtn.setVisible(false);
				startBtn.setEnabled(true);
			}
			else
				if (src.getText().equals("Custom") && src.isSelected())
				{
					custLBtn.setVisible(true);
					custRBtn.setVisible(true);
					custCBtn.setVisible(true);
					custChokeBtn.setVisible(true);
					custTauntBtn.setVisible(true);
					if (!leftFace.equals("") && !rightFace.equals("") && !centerFace.equals(""))
						startBtn.setEnabled(true);
					else
						startBtn.setEnabled(false);
				}
		}
	}
	
	private static class StartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			custom = customBtn.isSelected();
			frame.dispose();
			frame = new JFrame("SlapFace");
			JPanel panel2 = new JPanel();
			slapLabel.setText("Slap Counter: " + slaps);
			panel2.add(slapLabel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			imgLabel = new JLabel();
			if (!custom)
			{
				leftFace = "./leftJared.jpg";
				rightFace = "./rightJared.jpg";
				centerFace = "./restJared.jpg";
				tauntFace = "./tauntJared.jpg";
				fistFace = "./fistJared.jpg";
			}
			else
			{
				if (tauntFace.equals(""))
				{
					tauntFace = centerFace;
					setTaunt = false;
				}
				if (fistFace.equals(""))
				{
					fistFace = centerFace;
					setFist = false;
				}
			}
			imgLabel.setIcon(new ImageIcon(centerFace));
			frame.getContentPane().add(imgLabel);
			//frame.getContentPane().add(panel2);
			frame.addKeyListener(new MyoListener());
			frame.pack();
			frame.setVisible(true);
			timer = new Timer(1000, new TimeListener());
			slaps = 0;
		}
	}
	
	private static class MyoListener implements KeyListener
	{
		public void keyTyped(KeyEvent e) 
		{
			
		}
		
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT)
			{
				imgLabel.setIcon(new ImageIcon(leftFace));
				playSound("slap.wav");
				slaps++;
				slapLabel.setText("Slap Counter: " + slaps);
			}
			else
				if (key == KeyEvent.VK_RIGHT)
				{
					imgLabel.setIcon(new ImageIcon(rightFace));
					playSound("slap.wav");
					slaps++;
					slapLabel.setText("Slap Counter: " + slaps);
				}
				else
					if (key == KeyEvent.VK_T)
					{
						imgLabel.setIcon(new ImageIcon(tauntFace));
						if (setTaunt)
							playSound("grr.wav");
						slaps = 0;
					}
					else
						if (key == KeyEvent.VK_F)
						{
							imgLabel.setIcon(new ImageIcon(fistFace));
							if (setFist)
								playSound("choke.wav");
							
						}
						else
							if (key == KeyEvent.VK_S)
								try
								{
									start();
								} catch (Exception e1) {};
			timer.restart();
		}
		
		private void playSound(String str)
		{
			try
			{
				AudioClip clip = Applet.newAudioClip(new URL("file:./" + str));
				clip.play();
			}
			catch (Exception e) {e.printStackTrace();};
		}
		
		public void keyReleased(KeyEvent e) 
		{
			
		}
	}
	
	private static class TimeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (slaps > 7 && !custom)
				imgLabel.setIcon(new ImageIcon("sadJared.jpg"));
			else
				imgLabel.setIcon(new ImageIcon(centerFace));
			timer.stop();
		}
	}
}
