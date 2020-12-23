import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Saved {
   
   private String[] list;
   private String name;
   
   public Saved( String[] given, String n ){
      list = given;
      name = n;
   }
   
   public String[] getList(){
      return list;
   }
   
   public String toString(){
      return name;
   }
   
}
