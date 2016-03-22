package main;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.*;

public class DrawPanel extends JPanel {

    Gui gui;

    public DrawPanel(Gui g) {
        super();
        this.gui = g;
    }

    /**
     * Surchage de paintComponent
     *
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponents(g);

        System.out.println("Rentre ds paintcomponent");

        //Graphics2D g2 = (Graphics2D)g;
        try {
            initialize(g);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Initialise la grille et l'affichage
     *
     * @param g Graphics
     * @throws Exception IOException
     */
    public void initialize(Graphics g) throws Exception {

        int temp = 0;
        for (int i = 0; i <= this.gui.grille.length; i += 1) {
            g.drawLine(0, temp, this.gui.grille.length * 100, temp);
            temp += 100;
        }
        temp = 0;
        for (int i = 0; i <= this.gui.grille.length; i += 1) {
            g.drawLine(temp, 0, temp, this.gui.grille.length * 100);
            temp += 100;
        }

        for (int i = 0; i < this.gui.grille.length; i++) {
            for (int j = 0; j < this.gui.grille.length; j++) {
                if (this.gui.grille.matrice[i][j].color == 0) {
                    try {
                        Image img = ImageIO.read(new File("img/noir.png"));
                        g.drawImage(img, j * 100, i * 100, this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        int tempcol = this.gui.grille.matrice[i][j].color;
                        Image img = ImageIO.read(new File("img/" + tempcol + "start.png"));
                        g.drawImage(img, j * 100, i * 100, this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        this.gui.grille.affiche();
    }

}
