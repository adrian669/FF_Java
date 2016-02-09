package main;
import java.awt.Graphics;

public class DrawPanel extends test {
    
    public void paintComponent(Graphics g) {
        
       super.paintComponents(g);
        
       System.out.println(" test ");
       
       
//     int height = getHeight() - 1;
//     int width =  getWidth() - 1;
//     
//     g.drawLine(0, 0, width, 0);
//     g.drawLine(0, 0, 0, height);
//     g.drawLine(width, 0, width, height);
//     g.drawLine(0, height, width, height);
//      
//     g.drawLine(0, 100, 100, 100);

     
        
    }
    
    public static void main (String args[]) {
        
        DrawPanel panel = new DrawPanel();
        test essai = new test();
     
        
    }
    
}
