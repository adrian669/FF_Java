package main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class DrawPanel extends JPanel  {
    Gui gui;
    
    DrawPanel(Gui g) {
        super();
        this.gui = g;
    }
    
    @Override
    public void paintComponent(Graphics g) {
       
       super.paintComponents(g);
             
     System.out.println("Est rentre ds paintcomponent");
   
     Graphics2D g2 = (Graphics2D)g;
     initialize(g);
     
     g2.drawString("1", 40,40);
       
    
    }
    
    public void initialize(Graphics g) {
     int height = getHeight() - 1;
     int width =  getWidth() - 1;
 
     //g.drawLine(0, 0, width, 0);
     //g.drawLine(0, 0, 0, height);
     g.drawLine(width, 0, width, height);
     g.drawLine(0, height, width, height);
     
     

     int tx = height/gui.grille.length + 1;
     int ty = width/gui.grille.length + 1;
     
     System.out.println(gui.grille.matrice[1][1].color);
     
     for (int i = 0; i < height; i = i + tx) {
         g.drawLine(0, i, width, i);
     }
     
     for (int i = 0; i < width; i = i + ty) {
         g.drawLine(i, 0, i, height);
     }
        
    }
            
    
    public static void main (String args[]) {
        
    }
    
}
