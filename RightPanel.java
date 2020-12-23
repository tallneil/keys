import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RightPanel extends JPanel{
   
   private JLabel title;
   private JPanel area;
   private JPanel btnPanel;
   private JLabel[] prefix;
   private JTextField[] config;
   private String[] newConfig;
   private String[] keys = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
   private JButton choose;
   private JButton save;
   public static Keyboard k;
   private LeftPanel left;
   public static Key2 listen;
   
   public RightPanel(){
      setBackground( Color.WHITE );
      setBorder( BorderFactory.createLineBorder(Color.BLACK,1) );
      setLayout( new BorderLayout() );
      
      title = new JLabel("CURRENT CONFIGURATION", SwingConstants.CENTER);
      title.setHorizontalTextPosition( SwingConstants.CENTER );
      title.addKeyListener(listen);
      add(title,BorderLayout.NORTH);
      
      area = new JPanel();
      area.setLayout( new GridLayout( 36,2 ) );
      JScrollPane scroll = new JScrollPane(area);
      
      k = Window.getKeyboard();
      prefix = new JLabel[36];
      config = k.getConfig();
      newConfig = new String[36];
      
      left = Window.getMenu().getLeft();
      
      listen = new Key2();
      addKeyListener( listen );
      
      display();
      add(scroll,BorderLayout.CENTER);
      
      btnPanel = new JPanel();
      btnPanel.setLayout(new GridLayout(1,2));
      
      choose = new JButton("Choose New Keys");
      choose.addActionListener(new reConfig());
      choose.addKeyListener( listen );
      btnPanel.add(choose);
      
      save = new JButton("Save");
      save.addActionListener(new saveClicked());
      save.addKeyListener(listen);
      btnPanel.add(save);
      
      add(btnPanel,BorderLayout.SOUTH);
      
   }
   
   //display everything...........................................
   public void display(){
      String use;
      for ( int i = 0; i < 36; i++ ){
         if (i < 12)
            use = " Low " + keys[i%12];
         else if (i < 24)
            use = " Middle " + keys[i%12];
         else
            use = " High " + keys[i%12];
         prefix[i] = new JLabel( use );
         
         prefix[i].addKeyListener(listen);
         config[i].addKeyListener(listen);
         
         //config[i].setBorder(null);
         
         area.add( prefix[i] ); 
         area.add( config[i] );
      }
   }
   //...........................................................
   
   //Choose New Keys listener...................................
   public class reConfig implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         
         if (choose.getText().equals("Choose New Keys")){
            choose.setText("Done");
            for (JTextField temp : config)
               temp.setEditable(true);
         }
         
         else {
            choose.setText("Choose New Keys");
            for (int i = 0; i < 36; i++){
               config[i].setEditable(false);
               newConfig[i] = config[i].getText().trim();
            }
            k.setConfig(newConfig);
         }
      }
   }
   //............................................................
   
   //Save listener...............................................
   public class saveClicked implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         
         for (int i = 0; i < 36; i++)
            newConfig[i] = config[i].getText().trim();
         String name = JOptionPane.showInputDialog( "Save","Name this configuration");
         Saved s = new Saved(newConfig, name ); 
         left.addList(s);
         
      }
   }
   //............................................................
   
   //KeyListner (to call Keyboard makeshift methods).............
   public class Key2 extends KeyAdapter {
      public void keyPressed(KeyEvent e){
         k.act1(e);
      }
      public void keyTyped(KeyEvent e){
         k.act2();
      }
      public void keyReleased(KeyEvent e){
         k.act3();
      }
   }
   //...........................................................
   
   // show strings from saved settings.........................
   public void setNew(String[] given){
      for (int i = 0; i < 36; i++){
         config[i].setText(given[i]);
      }
   }
   //...........................................................
   
}
