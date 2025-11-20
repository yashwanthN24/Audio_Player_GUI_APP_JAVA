import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame implements ActionListener
{
 //IMAGES
 ImageIcon play = new ImageIcon(new ImageIcon("play.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
 ImageIcon stop = new ImageIcon(new ImageIcon("stop.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
 ImageIcon reset = new ImageIcon(new ImageIcon("reset3.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
 ImageIcon quit = new ImageIcon(new ImageIcon("quit.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

 //BUTTONS
 JButton but1=new JButton();//reset
 JButton but2=new JButton();//play and stop
 JButton but3=new JButton();//quit

 //CLIP
 Clip clip;

 //BOOLEAN
 boolean playing=true;

 //CONSTRUCTOR
 Main()
 {
  //AUDIO SETTINGS
  try {
   File file=new File("song1.wav");
   AudioInputStream ais = AudioSystem.getAudioInputStream(file);
   clip=AudioSystem.getClip();
   clip.open(ais);
  }
  catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
   JOptionPane.showMessageDialog(this, "Audio file error: " + e.getMessage());
  }

  //BUTTON
  but1.setIcon(reset);
  but2.setIcon(play);
  but3.setIcon(quit);

  but1.setBounds(50, 100, 52, 52);   // reset button
  but2.setBounds(104, 100, 52, 52);  // play/stop button
  but3.setBounds(158, 100, 52, 52);  //quit

  but1.addActionListener(this);
  but2.addActionListener(this);
  but3.addActionListener(this);

  //FRAME
  this.setLayout(null);
  this.getContentPane().setBackground(Color.pink);
  this.add(but1);
  this.add(but2);
  this.add(but3);
  this.setSize(300,200);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setVisible(true);
 }

 //MAIN FUNCTION
    public static void main(String[]args)
  {
     new Main();
  }

  //ACTION
  public void actionPerformed(ActionEvent e)
  {
   //RESET
   if(e.getSource()==but1)
   {
    if(clip!=null)
    {
     clip.setMicrosecondPosition(0);
    }
   }

   //PLAY/STOP
   if(e.getSource()==but2)
   {
    if(clip!=null) {
     if(playing) {
      clip.start();
      but2.setIcon(stop);
      playing=false;
     }
     else if(!playing)
     {
       clip.stop();
       but2.setIcon(play);
       playing=true;
     }
    }
   }

   //QUIT
   if(e.getSource()==but3)
   {
    if(clip!=null&& clip.isOpen())
    {
     clip.stop();
     clip.close();
     this.dispose();
    }
   }

  }
 }
