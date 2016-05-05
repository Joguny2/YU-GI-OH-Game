package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;



public class EXITAL implements ActionListener{
 
     private JFrame frame;
     public EXITAL(JFrame frame){
         this.frame= frame;
     }
     public void actionPerformed(ActionEvent event){
      frame.setVisible(false); 
         frame.dispose();
     }
 

}