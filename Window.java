import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JPanel {
  
  public static Keyboard k; 
  public static Menu m;
   
   public Window(){
      setFocusable(true);
      setBackground( Color.WHITE );
      setLayout( new BorderLayout() );
      k = new Keyboard();
      m = new Menu();
      k.setPreferredSize( new Dimension(635,150) );
      m.setPreferredSize( new Dimension(635,400) );
      add( k,BorderLayout.SOUTH );
      add( m,BorderLayout.CENTER );
   }
   
   public static Keyboard getKeyboard(){
     return k;
   }
   public static Menu getMenu(){
     return m;
   }
   
}
