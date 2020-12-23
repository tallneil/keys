import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import java.net.*;

public class Keyboard extends JPanel {
   
   private Player tone;
   private String[] alpha = { "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
      "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","0" };
   private int blue = -1;
   private int[] sharp = {1,3,6,8,10,13,15,18,20,22,25,27,30,32,34};
   private int[] corrs = {1,2,4,5, 6, 8, 9,11,12,13,15,16,18,19,20};
   private int[] natural = {0,2,4,5,7,9,11,12,14,16,17,19,21,23,24,26,28,29,31,33,35};
   //private Key k;
   
   public Keyboard(){
      setBorder( BorderFactory.createLineBorder(Color.BLACK,1) );
      setBackground(Color.WHITE);
      //k = new Key();
      //addKeyListener( k );
      setFocusable(true);
      tone = new Player();
      repaint();
   }
   
   //PAINT.............................................................
   public void paintComponent( Graphics g ){
      super.paintComponent( g );
      int w = getWidth();
      int h = getHeight();
      
      //to know when to skip black keys
      int count = 2; 
      boolean two = false; 
      
      //for drawing blue keys
      int index = -1; 
      boolean black = false;
      boolean drawn = false;
      
      //key position holders
      int pt1 = 0, pt2 = 0;
      
      //get which key to fill blue
      if (blue > -1){
         for (int a = 0; a < sharp.length; a++){
            if ( blue == sharp[a] ){
               black = true;
               index = a;
            }
         }
         for (int a = 0; a < natural.length; a++){
            if ( blue == natural[a] )
               index = a; 
         }
      }
      
      for (int i = 0; i < 21; i++){
         count++;
         pt1 = 30*i;   //for white keys
         pt2 = pt1-10; //for black keys
         
         //draw white key
         g.setColor(Color.BLACK);
         g.drawRect( pt1,0, 30,h ); 
         
         //fill white key blue
         if (!black && index > -1 && !drawn){
            g.setColor(Color.CYAN);
            g.fillRect( 30*index,0 , 30,h );
            drawn = true;
         }
         
         //draw black key
         g.setColor(Color.BLACK);
         if (count < 3)
            g.fillRect( pt2,0 , 20,5*h/9);
         
         //fill black key blue
         if ( black && (i == corrs[index]) ){
            g.setColor(Color.CYAN);
            g.fillRect( pt2,0 , 20,5*h/9);
         }
         
         //algorithm for skipping black keys
         if (two && count == 3){
            count = -1; 
            two = false;
         }
         else if (!two && count == 3){
            count = 0; 
            two = true;
         }
      }
      
   }
   //................................................................
   
   /*
   //KEYLISTENER.....................................................
   public class Key extends KeyAdapter {
      String x; int y;
      
      public void keyPressed(KeyEvent e){
         x = KeyEvent.getKeyText( e.getKeyCode() );
         x = x.toLowerCase();
         for ( int i = 0; i < alpha.length; i++){
            if ( x.equals( alpha[i] ) )
               y = i;
         }
         blue = y;
         repaint();
      }
      
      public void keyTyped(KeyEvent e){
         tone.playTone(y);
         //Window.getMenu().getRight().setEnabled(true);
         //System.out.println(y);
      }
      
      public void keyReleased(KeyEvent e){
         //tone.stopTone(0);
      }
   }
   //..............................................................
   */
   
   //CONFIG METHODS................................................
   public JTextField[] getConfig(){
      JTextField[] stringnames = new JTextField[36];
      for ( int i = 0; i < 36; i ++ ){
         stringnames[i] = new JTextField( alpha[i] );
         stringnames[i].setEditable(false);
      }
      return stringnames;
   }
   
   public String[] getTextConfig(){
      return alpha;
   }
   
   public void setConfig( String[] newConfig ){
      alpha = newConfig;
   }
   //.............................................................
   
   //MAKESHIFT KEYLISTENER........................................
   // to be called from other panels.
   String x; int y;
   public void act1(KeyEvent e){
      x = KeyEvent.getKeyText( e.getKeyCode() );
      x = x.toLowerCase();
      for ( int i = 0; i < alpha.length; i++){
         if ( x.equals( alpha[i] ) )
            y = i;
      }
      blue = y;
      repaint();
   }
   public void act2(){
      tone.playTone(y);
   }
   public void act3(){
      blue = -1;
      repaint();
   }
   //.............................................................
   
}
