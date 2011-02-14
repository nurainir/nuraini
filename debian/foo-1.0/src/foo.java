import javax.swing.*;
import java.awt.BorderLayout;

import lutuna.*;


public class foo extends JFrame {

   public static void main(String args[]) {
       JFrame.setDefaultLookAndFeelDecorated(true);

       JFrame frame = new JFrame("HelloWorldSwing");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JLabel label = new JLabel("Hello World "+mama.papa);
       frame.getContentPane().add(label,BorderLayout.NORTH);

	JLabel label2 = new JLabel("Hello World "+adik.kakak);
       frame.getContentPane().add(label2,BorderLayout.SOUTH);

       frame.setSize(300,200);
       frame.setVisible(true);
   }
}
