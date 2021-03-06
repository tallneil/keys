import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.applet.AudioClip;
import java.applet.Applet;

public class Player extends JFrame{
   
  private AudioClip[] sound = new AudioClip[36];
  
  public Player(){
    try {
       for (int i = 1; i <= 36; i++){
          String note = "key" + ((Integer)i).toString() + ".wav";
          sound[i-1] = Applet.newAudioClip( getClass().getResource(note) );
       }
    }
    catch( Exception e ){
      System.out.println("Error while loading sounds: " + e);
    }
  }

   public void playTone( int x ){
      sound[x].play();
   }
   public void stopTone( int x ){
      sound[x].stop();
   }
   public void loopTone( int x ){
      sound[x].loop();
   }
}
