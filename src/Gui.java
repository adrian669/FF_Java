package main;

import javax.swing.*;

public class Gui extends javax.swing.JFrame {

    public int choixTaille;
    public Grille grille;
    public boolean choixAleatoire;
    public boolean aDemarrer = false; // Pour empecher le joueur de cliquer au mauvais endroit
    public boolean effaceCase = false;
    public int choixCouleur; // Quelle couleur a été choisi par l'utilisateur
    public int choixNumeroGrille = -1;

    public Gui() {
        this.choixAleatoire = false;
        this.choixTaille = 0;
        this.grille = new Grille(0);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new DrawPanel(this);
        jButtonDemarrer = new javax.swing.JButton();
        jComboBoxChoixTaille = new javax.swing.JComboBox();
        jLabelChoixTaille = new javax.swing.JLabel();
        jLabelChoixCouleur = new javax.swing.JLabel();
        jComboBoxChoixCouleur = new javax.swing.JComboBox();
        ImageIcon imageIcon = new ImageIcon("img/numberlink.png");
        jLabelNumberlink = new javax.swing.JLabel(imageIcon);
        jButtonSolution = new javax.swing.JButton();
        jButtonEffaceCase = new javax.swing.JButton();
        jLabelStatutEffaceCase = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaExemple = new javax.swing.JTextArea();
        ImageIcon imageIcon2 = new ImageIcon("img/exemple.png");
        jLabelExemple = new javax.swing.JLabel(imageIcon2);
        jButtonRecommenezGrille = new javax.swing.JButton();
        jLabelChoixInput = new javax.swing.JLabel();
        jComboBoxChoixNumeroGrille = new javax.swing.JComboBox();
        jCheckBoxChoixAleatoire = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Numberlink");
        setPreferredSize(new java.awt.Dimension(1443, 1300));
        setResizable(false);
        setSize(new java.awt.Dimension(1443, 1300));

        jPanel1.setPreferredSize(new java.awt.Dimension(901, 901));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
        );

        jButtonDemarrer.setText("Démarrer");
        jButtonDemarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDemarrerActionPerformed(evt);
            }
        });

        jComboBoxChoixTaille.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "5x5", "6x6", "7x7", "8x8", "9x9" }));
        jComboBoxChoixTaille.setActionCommand("");
        jComboBoxChoixTaille.setName("choixTaille"); // NOI18N
        jComboBoxChoixTaille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxChoixTailleActionPerformed(evt);
            }
        });

        jLabelChoixTaille.setText("Choix de la taille de la grille");
        jLabelChoixTaille.setName("choixTailleLabel"); // NOI18N

        jLabelChoixCouleur.setText("Choix de la couleur");
        jLabelChoixCouleur.setName("choixCouleurLabel"); // NOI18N

        jComboBoxChoixCouleur.setName("choixCouleur"); // NOI18N
        jComboBoxChoixCouleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxChoixCouleurActionPerformed(evt);
            }
        });

        jButtonSolution.setText("Solution");
        jButtonSolution.setToolTipText("Génère la solution de la grille et l'affiche. Peut prendre du temps pour de grandes grilles");
        jButtonSolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSolutionActionPerformed(evt);
            }
        });

        jButtonEffaceCase.setText("Effacer une case");
        jButtonEffaceCase.setToolTipText("Efface la case choisie et les suivantes");
        jButtonEffaceCase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEffaceCaseActionPerformed(evt);
            }
        });

        jLabelStatutEffaceCase.setText("Le boutton efface case est : désactivé");

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextAreaExemple.setEditable(false);
        jTextAreaExemple.setBackground(new java.awt.Color(240, 240, 240));
        jTextAreaExemple.setColumns(40);
        jTextAreaExemple.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaExemple.setLineWrap(true);
        jTextAreaExemple.setRows(2);
        jTextAreaExemple.setText("Les règles sont simples ! Remplissez entièrement la grille en reliant les couleurs deux à deux sans que les chemins ne se croisent. Bonne chance !\nExemple :");
        jTextAreaExemple.setWrapStyleWord(true);
        jTextAreaExemple.setAutoscrolls(false);
        jTextAreaExemple.setBorder(null);
        jTextAreaExemple.setCaretColor(new java.awt.Color(240, 240, 240));
        jTextAreaExemple.setFocusable(false);
        jScrollPane1.setViewportView(jTextAreaExemple);

        jButtonRecommenezGrille.setText("Recommencez la grille actuelle");
        jButtonRecommenezGrille.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonRecommenezGrilleMouseClicked(evt);
            }
        });

        jLabelChoixInput.setText("Choix du numéro de grille");

        jComboBoxChoixNumeroGrille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxChoixNumeroGrilleActionPerformed(evt);
            }
        });

        jCheckBoxChoixAleatoire.setText("Cocher si vous voulez une grille aléatoire");
        jCheckBoxChoixAleatoire.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBoxChoixAleatoire.setName("ifGrilleAleatoire"); // NOI18N
        jCheckBoxChoixAleatoire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxChoixAleatoireActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jLabelNumberlink, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jButtonSolution))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabelChoixCouleur)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxChoixCouleur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelExemple)
                        .addGap(165, 945, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelChoixTaille)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxChoixTaille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(252, 252, 252)
                                .addComponent(jButtonEffaceCase)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButtonDemarrer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRecommenezGrille)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelStatutEffaceCase)
                        .addGap(135, 135, 135))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabelChoixInput)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxChoixNumeroGrille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBoxChoixAleatoire))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(582, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonRecommenezGrille)
                            .addComponent(jButtonDemarrer))
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxChoixTaille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelChoixTaille))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBoxChoixAleatoire)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelChoixInput)
                            .addComponent(jComboBoxChoixNumeroGrille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelChoixCouleur)
                            .addComponent(jComboBoxChoixCouleur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEffaceCase))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelStatutEffaceCase)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonSolution)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabelExemple)))
                .addGap(28, 28, 28)
                .addComponent(jLabelNumberlink, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDemarrerActionPerformed

        aDemarrer = true;

//        jCheckBoxChoixAleatoire.setVisible(false);
//        jComboBoxChoixTaille.setVisible(false);      
        if (choixTaille == 0) {
            JOptionPane.showMessageDialog(this,
                    "Choisis une taille de grille avant de démarrer une partie",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        // Pour actualiser !
        refreshjPanel();

        System.out.println(this.choixNumeroGrille);

        if (choixAleatoire == true) {
            this.grille = new Grille(this.choixTaille);
            //this.grille.aleaGrille(choixTaille, 10000);
        } else {
            if (this.choixNumeroGrille != -1 || this.choixTaille == 0) {
                switch (this.choixTaille) { // Pas besoin de spécifier input/nomdufichier car pris en compte dans Grille
                    case 5:
                        try {
                            this.grille = new Grille("test5x5.csv", this.choixNumeroGrille);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            this.grille = new Grille("test6x6.csv", this.choixNumeroGrille);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            this.grille = new Grille("test7x7.csv", this.choixNumeroGrille);
                        } catch (Exception e) {
                        }
                        break;
                    case 8:
                        try {
                            this.grille = new Grille("test8x8.csv", this.choixNumeroGrille);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 9:
                        try {
                            this.grille = new Grille("test9x9.csv", this.choixNumeroGrille);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Choisis un numéro de grille",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        refreshComboBoxChoixCouleur();
    }//GEN-LAST:event_jButtonDemarrerActionPerformed

    private void jComboBoxChoixTailleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxChoixTailleActionPerformed
        JComboBox comboBox = (JComboBox) evt.getSource();
        Object selected = comboBox.getSelectedItem();
        String temp = selected.toString();
        switch (temp) {
            case "5x5":
                this.choixTaille = 5;
                break;
            case "6x6":
                this.choixTaille = 6;
                break;
            case "7x7":
                this.choixTaille = 7;
                break;
            case "8x8":
                this.choixTaille = 8;
                break;
            case "9x9":
                this.choixTaille = 9;
                break;
        }
        this.choixNumeroGrille = -1;
        refreshComboBoxChoixNumeroGrille();
    }//GEN-LAST:event_jComboBoxChoixTailleActionPerformed

    private void jCheckBoxChoixAleatoireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxChoixAleatoireActionPerformed
        AbstractButton abstractButton = (AbstractButton) evt.getSource();
        boolean select = abstractButton.getModel().isSelected();
        this.choixAleatoire = select == true;
        if (this.choixAleatoire) {
            jComboBoxChoixNumeroGrille.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" "}));
            this.choixNumeroGrille = -1;
        } else {
            refreshComboBoxChoixNumeroGrille();
        }
    }//GEN-LAST:event_jCheckBoxChoixAleatoireActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        if (aDemarrer) {
            Case casetemp = getCase(evt.getY(), evt.getX());
            if (effaceCase) {
                this.grille.eraseCasebis(casetemp);
                this.grille.sucess = false;
                refreshjPanel();
            } else {
                if (casetemp.nb_marked != -1) {
                    //changeCouleur(casetemp);
                    if (this.grille.canMark(casetemp, choixCouleur)) {
                        this.grille.mark(casetemp, choixCouleur);
                        refreshjPanel();
                    }
                } else {
                    this.grille.eraseAllCasesColor(casetemp.color);
                    this.grille.sucess = false;
                    refreshjPanel();
                }
                this.grille.sucess = false;
            }
            if (this.grille.solved()) {

                Object[] options = {"Continuez",
                    "Recommencez",};
                int n = JOptionPane.showOptionDialog(this,
                        "Grille résolue ! ",
                        "Bravo",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 1) {
                    this.grille.reinitialize();
                    refreshjPanel();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Démarre une partie avant de cliquer de partout !",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }
    }//GEN-LAST:event_jPanel1MouseClicked


    private void jComboBoxChoixCouleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxChoixCouleurActionPerformed
        JComboBox comboBox = (JComboBox) evt.getSource();
        Object selected = comboBox.getSelectedItem();
        String temp = selected.toString();
        this.effaceCase = false;
        refreshjLabelStatutEffaceCase();
        switch (temp) {
            case "Blanc":
                this.choixCouleur = 1;
                break;
            case "Rouge":
                this.choixCouleur = 2;
                break;
            case "Rose":
                this.choixCouleur = 3;
                break;
            case "Marron":
                this.choixCouleur = 4;
                break;
            case "Vert":
                this.choixCouleur = 5;
                break;
            case "Orange":
                this.choixCouleur = 6;
                break;
            case "Violet":
                this.choixCouleur = 7;
                break;
            case "Cyan":
                this.choixCouleur = 8;
                break;
            case "Gris":
                this.choixCouleur = 9;
                break;
        }
    }//GEN-LAST:event_jComboBoxChoixCouleurActionPerformed

    private void jButtonSolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSolutionActionPerformed
        if (aDemarrer) {
            //System.out.println("Bouton solution");
            this.grille.solve2(10000);
            refreshjPanel();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Tu ne peux pas demander une solution si tu n'as pas de grille !",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSolutionActionPerformed

    private void jButtonEffaceCaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEffaceCaseActionPerformed
        if (this.aDemarrer) {
            this.effaceCase = true;
            refreshjLabelStatutEffaceCase();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Ne demande pas d'effacer une case si tu n'as pas de grille !",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonEffaceCaseActionPerformed

    private void jButtonRecommenezGrilleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRecommenezGrilleMouseClicked
        if (this.aDemarrer) {
            this.grille.reinitialize();
            refreshjPanel();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Demarre une partie avant",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonRecommenezGrilleMouseClicked

    private void jComboBoxChoixNumeroGrilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxChoixNumeroGrilleActionPerformed
        JComboBox comboBox = (JComboBox) evt.getSource();
        Object selected = comboBox.getSelectedItem();
        String temp = selected.toString();
        switch (temp) {
            case "1":
                this.choixNumeroGrille = 1;
                break;
            case "2":
                this.choixNumeroGrille = 2;
                break;
            case "3":
                this.choixNumeroGrille = 3;
                break;
            case "4":
                this.choixNumeroGrille = 4;
                break;
            case "5":
                this.choixNumeroGrille = 5;
                break;
            case "6":
                this.choixNumeroGrille = 6;
                break;
            case "7":
                this.choixNumeroGrille = 7;
                break;
            case "8":
                this.choixNumeroGrille = 8;
                break;
        }
        this.choixAleatoire = false;
        jCheckBoxChoixAleatoire.setSelected(false);
    }//GEN-LAST:event_jComboBoxChoixNumeroGrilleActionPerformed

    /**
     * Retourne la case sur laquelle l'utilisateur a cliqué
     *
     * @param x Coordonnée en x
     * @param y Coordonnée en u
     * @return Case
     */
    public Case getCase(int x, int y) {
        return this.grille.matrice[x / 100][y / 100];
    }

    /**
     * Actualise la liste de choix des couleurs
     */
    public void refreshComboBoxChoixCouleur() {
        switch (this.grille.nb_color) {
            case 1:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc"}));
                break;
            case 2:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc", "Rouge", "Rose"}));
                break;
            case 3:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc", "Rouge", "Rose"}));
                break;
            case 4:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc", "Rouge", "Rose", "Marron"}));
                break;
            case 5:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc", "Rouge", "Rose", "Marron", "Vert"}));
                break;
            case 6:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc", "Rouge", "Rose", "Marron", "Vert", "Orange"}));
                break;
            case 7:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc", "Rouge", "Rose", "Marron", "Vert", "Orange", "Violet"}));
                break;
            case 8:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc", "Rouge", "Rose", "Marron", "Vert", "Orange", "Violet", "Cyan"}));
                break;
            case 9:
                jComboBoxChoixCouleur.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Blanc", "Rouge", "Rose", "Marron", "Vert", "Orange", "Violet", "Cyan", "Gris"}));
                break;
        }
    }

    /**
     * Mets à jour le combobox de choix de numéro de grill
     */
    public void refreshComboBoxChoixNumeroGrille() {
        switch (this.choixTaille) {
            case 5:
                jComboBoxChoixNumeroGrille.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" ", "1", "2", "3", "4", "5"}));
                break;
            case 6:
                jComboBoxChoixNumeroGrille.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" ", "1", "2", "3", "4", "5", "6", "7"}));
                break;
            case 7:
                jComboBoxChoixNumeroGrille.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" ", "1", "2", "3"}));
                break;
            case 8:
                jComboBoxChoixNumeroGrille.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" ", "1", "2", "3", "4"}));
                break;
            case 9:
                jComboBoxChoixNumeroGrille.setModel(new javax.swing.DefaultComboBoxModel(new String[]{",", "1"}));
        }

    }

    /**
     * Mets à jour le jLabel de status d'efface case
     */
    public void refreshjLabelStatutEffaceCase() {
        if (this.effaceCase) {
            jLabelStatutEffaceCase.setText("Le bouton efface case est : activé ");
        } else {
            jLabelStatutEffaceCase.setText("Le bouton efface case est : désactivé ");
        }
    }

    /**
     * Actualise le jPanel
     */
    public void refreshjPanel() {
        jPanel1.setVisible(false);
        jPanel1.setVisible(true);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDemarrer;
    private javax.swing.JButton jButtonEffaceCase;
    private javax.swing.JButton jButtonRecommenezGrille;
    private javax.swing.JButton jButtonSolution;
    private javax.swing.JCheckBox jCheckBoxChoixAleatoire;
    private javax.swing.JComboBox jComboBoxChoixCouleur;
    private javax.swing.JComboBox jComboBoxChoixNumeroGrille;
    private javax.swing.JComboBox jComboBoxChoixTaille;
    private javax.swing.JLabel jLabelChoixCouleur;
    private javax.swing.JLabel jLabelChoixInput;
    private javax.swing.JLabel jLabelChoixTaille;
    private javax.swing.JLabel jLabelExemple;
    private javax.swing.JLabel jLabelNumberlink;
    private javax.swing.JLabel jLabelStatutEffaceCase;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaExemple;
    // End of variables declaration//GEN-END:variables

}
