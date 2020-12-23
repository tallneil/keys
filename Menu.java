import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel {
   
   private static LeftPanel a;
   private static RightPanel b;
   
   public Menu(){
      setBackground(Color.WHITE);
      setLayout( new GridLayout(1,2) );
      a = new LeftPanel();
      b = new RightPanel();
      add(a); add(b);
   }
   
   public static LeftPanel getLeft(){
      return a;
   }
   public static RightPanel getRight(){
      return b;
   }

}