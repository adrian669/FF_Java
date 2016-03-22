package main;

import java.util.*;
import java.io.*;

public class Grille {

    //Attributs
    Case[][] matrice; //la grille (indices commencent à 0)
    int length;//taille de la grille (length*length)
    int nb_color; //nombre de couleurs à relier
    int nb_cases_marked = 0;//nombre de cases marquées
    int nb_color_linked = 0;// nombre de couleurs liées
    boolean arret = false;//vrai si on arrete de chercher une solution, faux si on continue
    boolean sucess = false;//vrai si une solution existe; faux sinon
    LinkedList<Case>[] parcours;//liste des cases marquees pour chaque couleur (avec depart et arrivee) et la premiere liste contient toutes les cases marquees couleurs confondues

    /**
     * Constructeur d'une grille vide de taille donnée et avec un nb de couleur
     * donné
     *
     * @param length1 taille
     * @param nb_color1 nombre de couleur
     */
    public Grille(int length1, int nb_color1) {
        this.matrice = new Case[length1][length1];
        this.length = length1;
        this.nb_color = nb_color1;
        this.parcours = new LinkedList[nb_color1 + 1];
        for (int j = 0; j <= nb_color1; j++) {
            LinkedList<Case> l = new LinkedList<Case>();
            this.parcours[j] = l;
        }
    }

    /**
     * Constructeur de grille aléatoire
     *
     * @param length1 taille de la grille en lligne
     */
    public Grille(int length1) {
        this.matrice = new Case[length1][length1];
        this.length = length1;
        this.nb_color = length1 - 1;
        Random rand1 = new Random();
        this.nb_cases_marked = 0;
        this.nb_color_linked = 0;
        this.arret = false;
        this.sucess = false;

        // Les points aléatoires
        int x1;
        int y1;
        int x2;
        int y2;

        switch (length1) {
            case 5:
                this.nb_color = rand1.nextInt(2) + 4; // Pour avoir 4, 5 ou 6 couleurs
                break;
            case 6:
                this.nb_color = rand1.nextInt(3) + 4; // Pour avoir 4, 5 ou 6 couleurs
                break;
            case 7:
                this.nb_color = rand1.nextInt(3) + 5; // Pour avoir 5, 6 ou 7 couleurs
                break;
            case 8:
                this.nb_color = rand1.nextInt(3) + 6; // Pour avoir 6,7 ou 8 couleurs
                break;
            case 9:
                this.nb_color = rand1.nextInt(3) + 7; // Pour avoir 7,8 ou 9 couleurs
                break;
        }

        // On initialise les cases
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length1; j++) {
                this.matrice[i][j] = new Case(i + 1, j + 1, 0, false, 0);
            }
        }

        //parcours
        this.parcours = new LinkedList[this.nb_color + 1];
        for (int j = 0; j <= this.nb_color; j++) {
            LinkedList<Case> l = new LinkedList<Case>();
            this.parcours[j] = l;
        }

        for (int i = 1; i <= this.nb_color; i++) {
            Random rand = new Random();
            x1 = rand.nextInt(length1);
            y1 = rand.nextInt(length1);
            x2 = rand.nextInt(length1);
            y2 = rand.nextInt(length1);
            //On verifie qu'on ne prends ps deux fois la meme case et que ces deux case sont libre ( color == 0)
            if ((!((x1 == x2) && (y1 == y2))) && (this.matrice[x1][y1].color == 0) && (this.matrice[x2][y2].color == 0)) {

                this.matrice[x1][y1].i = x1 + 1;
                this.matrice[x1][y1].j = y1 + 1;
                this.matrice[x1][y1].color = i;
                this.matrice[x1][y1].nb_marked = -1;
                this.matrice[x1][y1].marked = true;
                this.matrice[x2][y2].i = x2 + 1;
                this.matrice[x2][y2].j = y2 + 1;
                this.matrice[x2][y2].color = i;
                this.matrice[x2][y2].nb_marked = -1;
                this.matrice[x2][y2].marked = true;

            } else {
                i--; // Sinon on recommence !
            }
            // Si on arrive à une situation irrésoluble dans un coin on recommence depuis le début
            if (!bloquerCoin()) {
                System.out.println("!bloquercoin");
                for (int k = 0; k < this.length; k++) {
                    for (int j = 0; j < this.length; j++) {
                        this.matrice[k][j].i = k + 1;
                        this.matrice[k][j].j = j + 1;
                        this.matrice[k][j].color = 0;
                        this.matrice[k][j].nb_marked = 0;
                        this.matrice[k][j].marked = false;
                    }
                }
                i = 0;
            }

        }

//                    for (int i = 0; i < this.length; i++) {
//                for (int j = 0; j < this.length; j++) {
//                    System.out.print(this.matrice[i][j].color);
//                }
//            }
//        
//                    System.out.println("nb de couleur : " + this.nb_color);
//                           
//        
//        for (int i = 0; i < this.length; i++) {
//            for (int j = 0; j < this.length; j++) {
//                System.out.println(this.matrice[i][j].marked);
//            }
//        }
        this.parcours();

        // System.out.println(this.matrice[1][1].color);
        //System.out.println(this.matrice[0][1].color);
    }

    /**
     * Constructeur à partir d'un fichier csv contenant des grilles
     *
     * @param nom_fich nom du fichier en .csv
     * @param choix_input choix de la grille parmi celle presente dans le .csv
     * @throws Exception Exception si le fichier n'existe pas
     */
    public Grille(String nom_fich, int choix_input) throws Exception {
        int tempo = 0; // Va prendre le int dans le scanner.
        int nb_ligne = 0; // Nombre de ligne pour une grille
        int max = 0; // Pour connaitre le numéro de la plus grande couleur dans le fichier. On en déduit le nombre de couleur.
        int compteur = 0; // Pour parcourir une par une les cases contenus dans le vector de case
        boolean temp;// Pour gerer la fin d'une ligne. Si le scanner n'est pas un int temp devient faux.
        int choix_nb_ligne = Integer.parseInt(nom_fich.substring(4, 5)); // Pour savoir la taille de la grille en fonction du nom du fichier
        this.arret = false;
        this.sucess = false;
        this.nb_cases_marked = 0;
        this.nb_color_linked = 0;
        Vector<Case> vector = new Vector(); // On stocke les cases dans un vector

        try {
            Scanner scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(
                                    new File("input/" + nom_fich)))); // Fichier dans le dossier input !

            nb_ligne = choix_nb_ligne;
            this.matrice = new Case[nb_ligne][nb_ligne];
            this.length = nb_ligne;

            if (choix_input > 1) {
                for (int i = 1; i <= (nb_ligne + 1) * (choix_input - 1); i++) {
                    scanner.nextLine(); // On saute des lignes jusqu'à atteindre l'input voulu
                }
            }

            scanner.useDelimiter("[;\n\r]");

            // On initialise les cases et on les mets dans un vector
            for (int ligne = 0; ligne < this.length; ligne++) {
                for (int col = 0; col < this.length; col++) {
                    this.matrice[ligne][col] = new Case(ligne + 1, col + 1, 0, false, 0);
                    vector.add(matrice[ligne][col]);
                }
            }

            // On remplit les attributs des cases comme il faut (couleur, case départ ou non , marqué ou non)
            for (int i = 1; i <= nb_ligne * nb_ligne + nb_ligne; i++) { //  Nombre de case +  nombre de ligne
                if (scanner.hasNextInt()) {
                    tempo = scanner.nextInt();
                    if (tempo > max) {
                        max = tempo;
                    }
                    if (tempo == 0) {
                        vector.elementAt(compteur).color = tempo;
                        compteur++;
                    } else {
                        vector.elementAt(compteur).color = tempo; // Change la couleur
                        vector.elementAt(compteur).nb_marked = -1; // Change nb_marked en -1
                        vector.elementAt(compteur).marked = true; //  Change marked en true
                        compteur++;
                    }
                    temp = true;

                } else {
                    temp = false;
                }
                if (scanner.hasNext() && temp == false) { // La condition temp == false doit etre verifie sinon les 2 scanners.has sont vrais et l'on saute des nombres
                    scanner.next();
                }
            }

            this.nb_color = max;

            this.parcours = new LinkedList[this.nb_color + 1];
            for (int j = 0; j <= this.nb_color; j++) {
                LinkedList<Case> l = new LinkedList<Case>();
                this.parcours[j] = l;
            }
            this.parcours();

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Fichier pas trouvé de grille");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Null Pointer exception de grille");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception de grille");
        }

    }

    /**
     * Conjstructeur de copie
     *
     * @param gr Grille
     */
    public Grille(Grille gr) {
        this.matrice = gr.matrice;
        this.length = gr.length;
        this.nb_cases_marked = gr.nb_cases_marked;
        this.nb_color = gr.nb_color;
        this.sucess = gr.sucess;
        this.arret = gr.arret;
        this.nb_color_linked = gr.nb_color_linked;
        this.parcours = gr.parcours;
    }

    /**
     * Verifie si on arrive à une grille irrésoluble dans un coin
     *
     * @return vrai si on n'est pas bloqué faux sinon
     */
    public boolean bloquerCoin() {
        boolean temp = true;
        if ((this.matrice[0][1].color != this.matrice[1][0].color) && (this.matrice[0][0].color == 0)) {
            temp = false;
        }
        if ((this.matrice[this.length - 2][0].color != this.matrice[this.length - 1][1].color) && (this.matrice[this.length - 1][0].color == 0)) {
            temp = false;
        }
        if ((this.matrice[this.length - 2][this.length - 1].color != this.matrice[this.length - 1][this.length - 2].color) && (this.matrice[this.length - 1][this.length - 1].color == 0)) {
            temp = false;
        }
        if ((this.matrice[0][this.length - 2].color != this.matrice[1][this.length - 1].color) && (this.matrice[0][this.length - 1].color == 0)) {
            temp = false;
        }
        if ((this.matrice[0][1].color != this.matrice[1][0].color) && (this.matrice[0][1].color != this.matrice[0][0].color) && (this.matrice[0][0].color != this.matrice[1][0].color)) {
            temp = false;
        }
        if ((this.matrice[this.length - 2][0].color != this.matrice[this.length - 1][1].color) && (this.matrice[this.length - 2][0].color != this.matrice[this.length - 1][0].color) && (this.matrice[this.length - 1][1].color != this.matrice[this.length - 1][0].color)) {
            temp = false;
        }
        if ((this.matrice[this.length - 1][this.length - 2].color != this.matrice[this.length - 2][this.length - 1].color) && (this.matrice[this.length - 1][this.length - 2].color != this.matrice[this.length - 1][this.length - 1].color) && (this.matrice[this.length - 2][this.length - 1].color != this.matrice[this.length - 1][this.length - 1].color)) {
            temp = false;
        }
        if ((this.matrice[0][this.length - 2].color != this.matrice[1][this.length - 1].color) && (this.matrice[0][this.length - 2].color != this.matrice[0][this.length - 1].color) && (this.matrice[1][this.length - 1].color != this.matrice[0][this.length - 1].color)) {
            temp = false;
        }
        return temp;
    }

    /**
     * Créer une grille aléatoire qui est resolu par l'algorithme et qui véfifie
     * les règles du jeu.
     *
     * @param length1 nombre de case sur une ligne
     * @param time1 temps maximum par couleur pour résoudre une grille
     * @return Grille solution
     */
    public Grille aleaGrille(int length1, long time1) {
        boolean b = true;
        Grille grille1 = new Grille(length1);
        grille1.affiche();

        //grille1.affiche();
        //Grille grille1copie = grille1;
        //Grille grille1copie = new Grille(grille1);       
        while (b) {
            try {
                System.out.println("debut while");
                grille1.Solve2(time1);

                //grille1.affiche();
                System.out.println("debut solved");
                b = !grille1.solved();
                System.out.println("valeur de b ds le try " + b);
            } catch (NoSuchElementException e) {
                //e.printStackTrace();
                b = true;
                System.out.println("valeur de b ds le catch NoSuchElement : " + b);
            } catch (ArrayIndexOutOfBoundsException e) {
                b = true;
                System.out.println("valeur de b ds le catch Array :  " + b);
            } catch (Exception e) {
                e.printStackTrace();
                b = true;
                System.out.println("valeur de b ds le catch Exception :  " + b);
            }

            if (b) {
                grille1 = new Grille(length1);
                grille1.affiche();
                //grille1copie = grille1;
                //grille1copie = new Grille(grille1); 
                System.out.println("dans le if");
            } else {
                System.out.println("dans le else");
            }
        }
        this.matrice = grille1.matrice;
        this.length = grille1.length;
        this.nb_cases_marked = grille1.nb_cases_marked;
        this.nb_color = grille1.nb_color;
        this.sucess = grille1.sucess;
        this.arret = grille1.arret;
        this.nb_color_linked = grille1.nb_color_linked;
        this.parcours = grille1.parcours;

        System.out.println("fin aleagrille");
        return grille1;

//        //on vide la grille             
//        int ligne = this.length - 1;
//        int col = this.length - 1;
//        while ((ligne > -1) & (col > -1)) {
//            if (grille1copie.matrice[ligne][col].nb_marked != -1) {
//                grille1copie.matrice[ligne][col].nb_marked = 0;
//                grille1copie.matrice[ligne][col].color = 0;
//            }
//            //on change de case
//            if (col - 1 > -1) {
//                col = col - 1;
//            } else {
//                ligne = ligne - 1;
//                col = this.length - 1;
//            }
//
//        }
//        //on vide les parcours
//        for (int c = 0; c <= grille1copie.nb_color; c++) {
//            grille1copie.parcours[c].clear();
//        }
//        
//        this.matrice = grille1copie.matrice;
//        this.length = grille1copie.length;
//        this.parcours = grille1copie.parcours;
//        this.nb_color = grille1copie.nb_color;
    }

    /**
     * Reinitialise la grille au départ
     */
    public void reinitialize() {
        //on vide la grille             
        int ligne = this.length - 1;
        int col = this.length - 1;
        while ((ligne > -1) & (col > -1)) {
            if (this.matrice[ligne][col].nb_marked != -1) {
                this.matrice[ligne][col].nb_marked = 0;
                this.matrice[ligne][col].color = 0;
            }
            //on change de case
            if (col - 1 > -1) {
                col -= 1;
            } else {
                ligne -= 1;
                col = this.length - 1;
            }

        }
        //on vide les parcours
        for (int c = 0; c <= this.nb_color; c++) {
            this.parcours[c].clear();
        }

        this.nb_cases_marked = 0;
        this.nb_color_linked = 0;
        this.arret = false;
        this.sucess = false;
    }

    /**
     * Remplit une case de la grille à partir d'une case
     *
     * @param case1 case
     */
    public void remplir(Case case1) {
        this.matrice[case1.i - 1][case1.j - 1] = case1;
    }

    /**
     * Initialise la grille une fois qu'elle a les cases departs
     */
    public void parcours() {
        int i = 1;
        while (i <= this.nb_color) {
            Case d = this.FindStart(i);
            this.parcours[i].add(d);
            i += 1;
        }
    }

    /**
     * Affiche toute la liste. Couleur ou 0 pour toutes les cases marquees
     *
     * @param col1 couleur
     */
    public void afficheparcours(int col1) {
        System.out.println("\n");
        System.out.println("affiche parcours, couleur :" + col1);
        LinkedList l = this.parcours[col1];
        for (int j = 0; j < l.size(); j++) {
            this.parcours[col1].get(j).affiche();
        }
        System.out.println("FIN affiche parcours, couleur :" + col1);
        System.out.println("\n");
    }

    /**
     * Affiche
     */
    public void affiche() {
        int ligne = 0;
        int col = 0;
        System.out.print("\n");
        while (ligne < this.length) {
            String chaine = "";
            while (col < this.length) {
                chaine = chaine + "|" + this.matrice[ligne][col].color;
                col++;
            }
            System.out.println(chaine + "|");
            col = 0;
            ligne++;
        }
        System.out.print("\n");
    }

    /**
     * Affiche nb marked
     */
    public void affichenbmarked() {
        int ligne = 0;
        int col = 0;
        System.out.print("\n");
        while (ligne < this.length) {
            String chaine = "";
            while (col < this.length) {
                chaine = chaine + "|" + this.matrice[ligne][col].nb_marked;
                col++;
            }
            System.out.println(chaine + "|");
            col = 0;
            ligne++;
        }
        System.out.print("\n");
    }

    /**
     * Trouve la case de depart d'une couleur
     *
     * @param color1 couleur
     * @return case
     */
    public Case FindStart(int color1) {
        Case start;
        int ligne = 0;
        int col = 0;
        start = this.matrice[ligne][col];
        int s = 0;
        while ((s == 0) & (ligne < this.length) & (col < this.length)) {
            if ((this.matrice[ligne][col].color == color1) & (this.matrice[ligne][col].nb_marked == -1)) {
                s = 1;
                start = this.matrice[ligne][col];
            } else {
                if (col + 1 < this.length) {
                    col += 1;
                } else {

                    ligne += 1;
                    col = 0;
                }
            }
        }
        return start;
    }

    /**
     * Trouve la case d'arrivée d'une couleur
     *
     * @param color1 couelur
     * @return case case
     */
    public Case FindEnd(int color1) {
        Case end;
        int ligne = this.length - 1;
        int col = this.length - 1;
        end = this.matrice[ligne][col];
        int s = 0;
        while ((s == 0) & (ligne > -1) & (col > -1)) {
            if ((this.matrice[ligne][col].color == color1) & (this.matrice[ligne][col].nb_marked == -1)) {
                s = 1;
                end = this.matrice[ligne][col];
            } else {
                if (col - 1 > -1) {
                    col -= 1;
                } else {

                    ligne -= 1;
                    col = this.length - 1;
                }
            }
        }
        return end;
    }

    /**
     * retourne la case au Nord (1), a l'Est (2) ,au Sud (3) ou a l'Ouest (4) de
     * la case donnee
     *
     * @param case1 case
     * @param pos position
     * @return case (la meme si l'autre n'existe pas)
     */
    public Case donneCase(Case case1, int pos) {
        Case end = case1;
        if ((pos == 1) & ((case1.i - 2) > -1) & ((case1.i - 2) < this.length)) {
            end = this.matrice[case1.i - 2][case1.j - 1];//coeff doivent toujou etre rectifie par - 1
        } else if ((pos == 2) & ((case1.j) > -1) & ((case1.j) < this.length)) {
            end = this.matrice[case1.i - 1][case1.j];
        } else if ((pos == 3) & ((case1.i) > -1) & ((case1.i) < this.length)) {
            end = this.matrice[case1.i][case1.j - 1];
        } else if ((pos == 4) & ((case1.j - 2) > -1) & ((case1.j - 2) < this.length)) {
            end = this.matrice[case1.i - 1][case1.j - 2];
        }

        return end;
    }

    /**
     * dit si on peut marquer une case par une couleur (ie case vide(?) a cote
     * de la derniere case marquee par cette couleur )
     *
     * @param case1 case
     * @param color1 couleur
     * @return boolean
     */
    public boolean canMark(Case case1, int color1) {
        //on calcule le maximum de nb_marked pour une couleur
        int nb = -1;//point de depart
        int ligne = this.length - 1;
        int col = this.length - 1;
        while ((ligne > -1) & (col > -1)) {
            if ((this.matrice[ligne][col].nb_marked > nb) & (this.matrice[ligne][col].color == color1)) {//case marquee par couleur 1 et apres nb
                nb = this.matrice[ligne][col].nb_marked;
            }
            //on change de case
            if (col - 1 > -1) {
                col -= 1;
            } else {
                ligne -= 1;
                col = this.length - 1;
            }
        }
        //on teste si une case autour de case1 a la derniere case marquee par la couleur color1
        return ((!(case1.nb_marked == -1)) & (!(case1.comparaison(donneCase(case1, 1))) & (donneCase(case1, 1).color == color1) & (donneCase(case1, 1).nb_marked == nb)) || ((!(case1.comparaison(donneCase(case1, 2)))) & (donneCase(case1, 2).color == color1) & (donneCase(case1, 2).nb_marked == nb)) || ((!(case1.comparaison(donneCase(case1, 3))) & (donneCase(case1, 3).color == color1) & (donneCase(case1, 3).nb_marked == nb))) || ((!(case1.comparaison(donneCase(case1, 4))) & (donneCase(case1, 4).color == color1) & (donneCase(case1, 4).nb_marked == nb))));
    }

    /**
     * on marque une cellule dans la grille (! verifier dans algo avt avec
     * canMark!) rmq: si on marque une case par la couleur 1 deja marquee pour
     * une couleur 2 alors toutes les cases marquees par 2 se vident (sauf les
     * cases depart) si on marque une case par la couleur 1 deja marquee pour
     * cette couleur alors on efface les cases seulement marquee par cette
     * couleur et apres cette case
     *
     * @param case1 case
     * @param color1 couleur
     */
    public void mark(Case case1, int color1) {
        if (case1.color == 0) {//case vide
            case1.color = color1;
            case1.marked = true;
            case1.nb_marked = this.nb_cases_marked + 1;
            this.nb_cases_marked += 1;
            //on ajoute la nouvelle case dans le parcours associe a la couleur
            this.parcours[color1].addLast(case1);
            //et dans la liste regroupant toutes les cases marquees 
            this.parcours[0].addLast(case1);
        } else if (case1.color == color1) {//case deja marquee par cette couleur 1

            //on efface les cases marquees par la couleur 1
            int nbcaseseffacees = 0;
            int maxcaseseffacees = 0;
            int ligne = this.length - 1;
            int col = this.length - 1;
            while ((ligne > -1) & (col > -1)) {
                if ((this.matrice[ligne][col].color == color1) & (this.matrice[ligne][col].nb_marked != -1) & (this.matrice[ligne][col].nb_marked > case1.nb_marked)) {//case marquee par couleur 1 et apres case1 mais pas de depart
                    nbcaseseffacees += 1;
                    //on vide cette case
                    this.matrice[ligne][col].color = 0;
                    this.matrice[ligne][col].marked = false;
                    if (this.matrice[ligne][col].nb_marked > maxcaseseffacees) {
                        maxcaseseffacees = this.matrice[ligne][col].nb_marked;
                    }
                    this.matrice[ligne][col].nb_marked = 0;

                }
                //on change de case
                if (col - 1 > -1) {
                    col -= 1;
                } else {
                    ligne -= 1;
                    col = this.length - 1;
                }
                //on ajoute la nouvelle case dans le parcours associe a la couleur
                this.parcours[color1].addLast(case1);
                //et dans la liste regroupant toutes les cases marquees 
                this.parcours[0].addLast(case1);
            }

            //on rectifie les nb_marked de toutes les cases
            ligne = this.length - 1;
            col = this.length - 1;
            while ((ligne > -1) & (col > -1)) {
                if (this.matrice[ligne][col].nb_marked > maxcaseseffacees) {//case marquee par couleur 2 mais pas de depart
                    this.matrice[ligne][col].nb_marked -= nbcaseseffacees;
                }
                //on change de case
                if (col - 1 > -1) {
                    col -= 1;
                } else {
                    ligne -= 1;
                    col = this.length - 1;
                }
            }

            //on adapte le nombre de cases marquees
            this.nb_cases_marked -= nbcaseseffacees;
            case1.nb_marked = this.nb_cases_marked;
        } else {//case marquee par une autre couleur 2
            //on marque la case par la nouvelle couleur 1
            int color2;
            color2 = case1.color;
            case1.color = color1;
            case1.marked = true;

            //on efface les cases marquees par la couleur 2
            int nbcaseseffacees = 0;
            int maxcaseseffacees = 0;
            int ligne = this.length - 1;
            int col = this.length - 1;
            while ((ligne > -1) & (col > -1)) {
                if ((this.matrice[ligne][col].color == color2) & (this.matrice[ligne][col].nb_marked != -1)) {//case marquee par couleur 2 mais pas de depart
                    nbcaseseffacees += 1;
                    //on vide cette case
                    this.matrice[ligne][col].color = 0;
                    this.matrice[ligne][col].marked = false;
                    if (this.matrice[ligne][col].nb_marked > maxcaseseffacees) {
                        maxcaseseffacees = this.matrice[ligne][col].nb_marked;
                    }
                    this.matrice[ligne][col].nb_marked = 0;
                    //liste couleur 2
                    this.parcours[color2].remove(this.matrice[ligne][col]);
                    //liste ttes cases marquees
                    this.parcours[0].remove(this.matrice[ligne][col]);
                }
                //on change de case
                if (col - 1 > -1) {
                    col -= 1;
                } else {
                    ligne -= 1;
                    col = this.length - 1;
                }

            }

            //on rectifie les nb_marked de toutes les cases
            ligne = this.length - 1;
            col = this.length - 1;
            while ((ligne > -1) & (col > -1)) {
                if (this.matrice[ligne][col].nb_marked > maxcaseseffacees) {//case marquee par couleur 2 mais pas de depart
                    this.matrice[ligne][col].nb_marked -= nbcaseseffacees;
                }
                //on change de case
                if (col - 1 > -1) {
                    col -= 1;
                } else {
                    ligne -= 1;
                    col = this.length - 1;
                }
            }

            //on adapte le nombre de cases marquees
            this.nb_cases_marked -= nbcaseseffacees;
            case1.nb_marked = this.nb_cases_marked;
        }
    }

    /**
     *
     * @param color1 couleur
     * @return vrai si la couleur est liee, faux sinon
     */
    public boolean colorLinked(int color1) {
        boolean end = false;
        boolean while0 = true;
        Case case0 = this.FindStart(color1);
        Case case1 = this.FindStart(color1);
        Case caseend = this.FindEnd(color1);
        int nb = 0;//future case doit etre >
        int nb2 = 100;//doit etre <
        int ligne = this.length - 1;
        int col = this.length - 1;
        //on trouve la premiere case marquee pour cette couleur
        while ((ligne > -1) & (col > -1)) {
            if ((this.matrice[ligne][col].color == color1) & (this.matrice[ligne][col].nb_marked > nb) & (this.matrice[ligne][col].nb_marked < nb2)) {//case marquee par couleur 1 et avt nb
                case1 = this.matrice[ligne][col];
                nb2 = this.matrice[ligne][col].nb_marked;
            }
            //on change de case
            if (col - 1 > -1) {
                col -= 1;
            } else {
                ligne -= 1;
                col = this.length - 1;
            }
        }
        //on trouve de quelle case elle est partie
        if ((this.donneCase(case1, 1).comparaison(this.FindStart(color1))) || (this.donneCase(case1, 2).comparaison(this.FindStart(color1))) || (this.donneCase(case1, 3).comparaison(this.FindStart(color1))) || (this.donneCase(case1, 4).comparaison(this.FindStart(color1)))) {
            case0 = this.FindStart(color1);
            caseend = this.FindEnd(color1);
        } else if ((this.donneCase(case1, 1).comparaison(this.FindEnd(color1))) || (this.donneCase(case1, 2).comparaison(this.FindEnd(color1))) || (this.donneCase(case1, 3).comparaison(this.FindEnd(color1))) || (this.donneCase(case1, 4).comparaison(this.FindEnd(color1)))) {
            case0 = this.FindEnd(color1);
            caseend = this.FindStart(color1);
        }
//        else{
//            System.out.println("case0 :  i:" +case0.i+"   j:"+case0.j);
//            System.out.println("case1 :  i:" +case1.i+"   j:"+case1.j);
//            System.out.println("caseend :  i:" +caseend.i+"   j:"+caseend.j);
//            System.out.println("Erreur 1 colorLinked");
//        }
        while (while0) {
            nb = case1.nb_marked;
            nb2 = 100;
            case0 = case1;
            ligne = this.length - 1;
            col = this.length - 1;
            //on trouve la premiere case marquee pour cette couleur
            while ((ligne > -1) & (col > -1)) {
                if ((this.matrice[ligne][col].color == color1) & (this.matrice[ligne][col].nb_marked > nb) & (this.matrice[ligne][col].nb_marked < nb2)) {//case marquee par couleur 1 et avt nb
                    case1 = this.matrice[ligne][col];
                    nb2 = this.matrice[ligne][col].nb_marked;
                }
                //on change de case
                if (col - 1 > -1) {
                    col -= 1;
                } else {
                    ligne -= 1;
                    col = this.length - 1;
                }
            }
            if (case1.comparaison(case0)) {
                while0 = false;
                if ((this.donneCase(case1, 1).comparaison(caseend)) || (this.donneCase(case1, 2).comparaison(caseend)) || (this.donneCase(case1, 3).comparaison(caseend)) || (this.donneCase(case1, 4).comparaison(caseend))) {
                    end = true;
                }
            }
        }
        return end;
    }

    /**
     *
     * @param color1 couleur
     * @return vrai si la couleur est liee, faux sinon
     */
    public boolean colorLinked2(int color1) {
        Case case1;
        Case case2;
        if (this.parcours[color1].size() <= 1) {// si le parcours de la couleur est vide (pas <1 ?)
            return false;
        } else {//sinon
            case1 = (Case) this.parcours[color1].getLast();//derniere case pour la couleur 
            //case2=(Case)this.parcours[color1].getFirst();//premiere case pour la couleur 
            return ((case1.nb_marked == -1) & (!case1.comparaison(this.FindStart(color1))));
            //vrai si la premiere case est la case de depart et si la derniere est la case d arrivee
        }
    }

    /**
     * dit si la partie est finie (pour le joueur)
     *
     * @return vrai si la partie est finie faux sinon
     */
    public boolean solved() {
        //calcul du nombre de couleurs liees
        int nblinked = 0;
        int colori = 1;
        while (colori <= this.nb_color) {
            if (this.colorLinked(colori)) {
                nblinked += 1;
            }
            colori += 1;
        }
        this.nb_color_linked = nblinked;

        //calcul du nombre de cases vides
        int nbempty = 0;
        int ligne = this.length - 1;
        int col = this.length - 1;
        while ((ligne > -1) & (col > -1)) {
            if (this.matrice[ligne][col].color == 0) {//case vide
                nbempty += 1;
            }
            //on change de case
            if (col - 1 > -1) {
                col -= 1;
            } else {
                ligne -= 1;
                col = this.length - 1;
            }
        }
        //toutes les couleurs sont liees et pas de cases vides
        if ((nbempty == 0) & (this.nb_color_linked == this.nb_color)) {
            this.sucess = true;
        }
        System.out.println("solved");
        return this.sucess;
    }

    /**
     * dit si la partie est finie (pour le joueur)
     *
     * @return boolean
     */
    public boolean solved2() {
        //calcul du nombre de cases vides
        int nbempty = 0;
        int ligne = this.length - 1;
        int col = this.length - 1;
        while ((ligne > -1) & (col > -1)) {
            if (this.matrice[ligne][col].color == 0) {//case vide
                nbempty += 1;
            }
            //on change de case
            if (col - 1 > -1) {
                col -= 1;
            } else {
                ligne -= 1;
                col = this.length - 1;
            }
        }
        System.out.println("solved2");
        return ((nbempty == 0) & this.solvedAlgo2());
    }

    /**
     * dit si la partie est finie (pour l'Algo donc ss compter cases vides)
     *
     * @return boolean
     */
    public boolean solvedAlgo2() {
        boolean s = true;
        for (int i = 1; i <= this.nb_color; i++) {
            s = (s & this.colorLinked2(i));//couleurs de 1 a i liees ?
        }
        System.out.println("solvedAlgo2");
        return s;
    }

    /**
     * dit si la partie est finie (pour l'Algo donc ss compter cases vides)
     *
     * @return boolean
     */
    public boolean solvedAlgo() {
        //calcul du nombre de couleurs liees
        int nblinked = 0;
        int colori = 1;
        while (colori <= this.nb_color) {
            if (this.colorLinked(colori)) {
                nblinked += 1;
            }
            colori += 1;
        }
        this.nb_color_linked = nblinked;

        //toutes les couleurs sont liees et pas de cases vides
        if (this.nb_color_linked == this.nb_color) {
            this.sucess = true;
        }
        return this.sucess;
    }

    /**
     * Efface toutes les cases marquees par la couleur color1
     *
     * @param color1 couleur
     */
    public void eraseAllCasesColor(int color1) {
        int ligne = this.length - 1;
        int col = this.length - 1;
        while ((ligne > -1) & (col > -1)) {
            if (this.matrice[ligne][col].color == color1) {
                eraseCase(this.matrice[ligne][col]);
            }
            //on change de case
            if (col - 1 > -1) {
                col -= 1;
            } else {
                ligne -= 1;
                col = this.length - 1;
            }
        }
        this.sucess = false;
        int h = this.parcours[color1].size();
        for (int j = h; j > 1; j--) {//?pourquoi pas >0 ?
            //on enleve la case du parcours de la couleur 2
            Case c = this.parcours[color1].removeLast();
            int index = this.parcours[0].indexOf(c);
            //on enleve la case de ttes les cases marquees
            this.parcours[0].remove(index);
        }
    }

    /**
     * Efface cette case (ie reinitialise a vide si pas case depart) et rectifie
     * bien les nb_marked des autres cases
     *
     * @param case1 case
     */
    public void eraseCase(Case case1) {
        if (case1.nb_marked != -1) {
            int nb1 = case1.nb_marked;
            //on enleve cette case du parcours de sa couleur 
            this.parcours[case1.color].remove(case1);
            //on enleve cette case de la liste de tts les cases marquees
            this.parcours[0].remove(case1);
            case1.color = 0;
            case1.marked = false;
            case1.nb_marked = 0;
            int ligne = this.length - 1;
            int col = this.length - 1;

            //on rectifie les nb_marked de tts les autres cases
            while ((ligne > -1) & (col > -1)) {
                if (this.matrice[ligne][col].nb_marked > nb1) {
                    this.matrice[ligne][col].nb_marked -= 1;
                }
                //on change de case
                if (col - 1 > -1) {
                    col -= 1;
                } else {
                    ligne -= 1;
                    col = this.length - 1;
                }
            }
        }
    }

    /**
     * Ancienne méthode Donne la direction utilisée par la case marquée avant
     * celle-ci par la meme couleur
     *
     * @param case1 case
     * @return int (direction)
     */
    public int directionCaseAvt(Case case1) {
        int direction;
        if ((case1.color == 0) || (case1.nb_marked == -1)) {
            direction = 0;
        } else {
            int nb1 = case1.nb_marked;
            int nb2 = 0;
            Case case2;
            int ligne = this.length - 1;
            int col = this.length - 1;
            case2 = this.matrice[ligne][col];
            //on cherche la case (case2) precedente
            while ((ligne > -1) & (col > -1)) {
                if ((this.matrice[ligne][col].nb_marked < nb1) & (this.matrice[ligne][col].nb_marked >= nb2) & (this.matrice[ligne][col].color == case1.color)) {
                    nb2 = this.matrice[ligne][col].nb_marked;
                    case2 = this.matrice[ligne][col];
                }
                //on change de case
                if (col - 1 > -1) {
                    col -= 1;
                } else {
                    ligne -= 1;
                    col = this.length - 1;
                }
            }
            if (nb2 == -1) {
                case2 = this.FindStart(case1.color);
            }
            if (case2.i == case1.i) {//East (2) or West (4)
                if (case1.j == case2.j - 1) {
                    direction = 4;
                    //System.out.println("caseavt :" + CaseAvt(case1).i+" "+CaseAvt(case1).j+" couleur :"+CaseAvt(case1).color);
                } else {
                    direction = 2;
                    //System.out.println("caseavt :" + CaseAvt(case1).i+" "+CaseAvt(case1).j+" couleur :"+CaseAvt(case1).color);
                }
            } else {//North (1) or South (3)
                if (case1.i == case2.i - 1) {
                    direction = 3;
                    //System.out.println("caseavt :" + CaseAvt(case1).i+" "+CaseAvt(case1).j+" couleur :"+CaseAvt(case1).color);
                } else {
                    direction = 1;
                    //System.out.println("caseavt :" + CaseAvt(case1).i+" "+CaseAvt(case1).j+" couleur :"+CaseAvt(case1).color);
                }
            }
        }

        return direction;
    }

    /**
     * Donne la direction utilisee par la case avant celle-ci (marquee ou case
     * depart ?). Uniquement pour la fonction solve
     *
     * @param case1 case
     * @return int (direction)
     */
    public int directionCaseAvt2(Case case1) {
        int direction;
        Case case2 = this.CaseAvt2(case1);
        if (case2.comparaison(case1)) {//premiere case marquee
            //direction=1;
            case2 = this.FindStart(case1.color);
            if (case2.i == case1.i) {//East (2) or West (4)
                if (case1.j == case2.j - 1) {
                    direction = 4;
                } else {
                    direction = 2;
                }
            } else {//North (1) or South (3)
                if (case1.i == case2.i - 1) {
                    direction = 1;
                } else {
                    direction = 3;
                }
            }
        } else {
            if (case2.color != case1.color) {//pas de la meme couleur
                case2 = this.FindStart(case1.color);
            }
            if (case2.i == case1.i) {//East (2) or West (4)
                if (case1.j == case2.j - 1) {
                    direction = 4;
                } else {
                    direction = 2;
                }
            } else {//North (1) or South (3)
                if (case1.i == case2.i - 1) {
                    direction = 1;
                } else {
                    direction = 3;
                }
            }
        }

        return direction;
    }

    /**
     * CaseAvt (ancienne methode)
     *
     * @param case1 case
     * @return case precedemment marquee ou la case depart (pas forcement de la
     * mm couleur)
     */
    public Case CaseAvt(Case case1) {
        int nb1 = case1.nb_marked;
        int nb2 = 0;
        Case case2;
        int ligne = this.length - 1;
        int col = this.length - 1;
        case2 = this.matrice[ligne][col];
        //on cherche la case (case2) precedente
        while ((ligne > -1) & (col > -1)) {
            if ((this.matrice[ligne][col].nb_marked < nb1) & (this.matrice[ligne][col].nb_marked > nb2)) {
                nb2 = this.matrice[ligne][col].nb_marked;
                case2 = this.matrice[ligne][col];
            }
            //on change de case
            if (col - 1 > -1) {
                col -= 1;
            } else {
                ligne -= 1;
                col = this.length - 1;
            }
        }
        return case2;
    }

    /**
     * CaseAvt2 (avec listes)
     *
     * @param case1 case
     * @return case precedemment marquee (pas forcement de la mm couleur)
     */
    public Case CaseAvt2(Case case1) {

        int index = this.parcours[0].indexOf(case1);
        if (index <= 0) {//la case n'est pas marquee ou c est la premiere a l etre
            return case1;
        } else {
            return this.parcours[0].get(index - 1);
        }

    }

    //fonction Solve2 (avec listes)
    //arguments : none
    //sortie: none
    //resout la grille (avec possiblement des cases vides) 
    //se referer a article + voir ideee tableau "prise de decision" sur papier du prof
    //possible pb : retourne couleur d avant ds etape backward : mettre a jour color
    //possible pb : si pas de solution ?
    //super long ?
    /**
     * Resout la grille (avec possiblement des cases vides)
     *
     * @param limit temps maximum pour la résolution d'une grille
     */
    public void Solve2(long limit) {
        //Big Start
        int color = 1;// on commence par la couleur 1
        Case case0;//case marquee
        Case case1;//case a marquer
        int direction;
        int directionavt = 0;
        Case casedepart;
        Case casearrivee;
        boolean b;//Color Linked ?
        boolean c;//Any unmarked path ?
        int[][] decision = new int[this.length][this.length];//tableau prise de decision
        int dec = 0;
        long time = 0;//temps
        long timeBefore = System.currentTimeMillis();//point de depart

        //initialisation prise de decision
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.length; j++) {
                decision[i][j] = 0;//pas de prise de decision
            }
        }

        System.out.println(this.nb_color);
        while (!this.solvedAlgo2() && (time < this.nb_color * limit)) {
            System.out.println("debut while 1");
            casedepart = this.FindStart(color);//case depart
            casearrivee = this.FindEnd(color);//case arrivee
            direction = 1;//on commence par la direction 1 (North)
            case0 = casedepart;
            decision[casedepart.i - 1][casedepart.j - 1] = 1;//prise de decision ?
            //this.parcours[0].add(casedepart);//cases depart ds ttes cases marquees? NON
            time = System.currentTimeMillis() - timeBefore;
            System.out.println("time1 " + time + " " + this.nb_color * limit);
            b = true;
            //this.afficheparcours(0);
            while (b && (time < this.nb_color * limit)) {//Start
                System.out.println("debut while 2");
                time = System.currentTimeMillis() - timeBefore;
                System.out.println("time2 " + time + " " + this.nb_color * limit);
                //Any obstacles ?
                if ((case0.comparaison(this.donneCase(case0, direction))) || ((this.donneCase(case0, direction).color != 0) & (!this.donneCase(case0, direction).comparaison(casearrivee)))) {//derniere case dans cette direction ou case non vide (hors case arrivee)
                    //Yes
                    dec = decision[case0.i - 1][case0.j - 1];//on veut savoir si prise de decision avant
                    decision[case0.i - 1][case0.j - 1] = 1;//obstacle donc prise de decision
                    c = false;
                    //Any unmarked path ?
                    if ((this.donneCase(case0, 1).color == 0) || (this.donneCase(case0, 2).color == 0) || (this.donneCase(case0, 3).color == 0) || (this.donneCase(case0, 4).color == 0)) {//une case vide a cote
                        c = true;
                        //on change de direction
                        if ((direction != 1) & (this.donneCase(case0, 1).color == 0) & ((dec == 0) || ((dec == 1) & (direction < 1)))) {
                            direction = 1;
                            //decision[case0.i-1][case0.j-1]=1;
                        } else if ((direction != 2) & (this.donneCase(case0, 2).color == 0) & ((dec == 0) || ((dec == 1) & (direction < 2)))) {
                            direction = 2;
                            //decision[case0.i-1][case0.j-1]=1;
                        } else if ((direction != 3) & (this.donneCase(case0, 3).color == 0) & ((dec == 0) || ((dec == 1) & (direction < 3)))) {
                            direction = 3;
                            //decision[case0.i-1][case0.j-1]=1;
                        } else if ((direction != 4) & (this.donneCase(case0, 4).color == 0) & ((dec == 0) || ((dec == 1) & (direction < 4)))) {
                            direction = 4;
                            //decision[case0.i-1][case0.j-1]=1;
                        } else {//cas possible ? cas arret ?
//                                System.out.println("Pb direction >4 + decavt :"+dec);
                            c = false;
                        }
                    }
//                    else if((this.donneCase(case0,1).comparaison(casearrivee))||(this.donneCase(case0,2).comparaison(casearrivee))||(this.donneCase(case0,3).comparaison(casearrivee))||(this.donneCase(case0,4).comparaison(casearrivee))){//la case arrivee est a cote: normalement inutile
//                        c=true;
//                    }
                    while (!c) {// While No
                        //Turn one cell back
                        System.out.println("debut while 3");
                        System.out.println("time3 " + time + " " + this.nb_color * limit);
                        if (case0.comparaison(this.FindStart(case0.color))) {//on a change de couleur
                            case0 = this.parcours[0].getLast();//derniere case marquee
                            color -= 1;//on retourne a la couleur d avant
                            casedepart = this.FindStart(color);//case depart
                            casearrivee = this.FindEnd(color);//case arrivee
//                            System.out.println("couleur :"+color+" cases d et a :");
//                            casedepart.affiche();
//                            casearrivee.affiche();
                        }
                        case1 = this.CaseAvt2(case0);

                        //on adapte :
                        if (case1.comparaison(case0)) {//premiere case marquee
                            case1 = this.FindStart(case0.color);
//                            System.out.println("pb1 :");
                        }

                        //System.out.println("direction  :"+direction);
                        directionavt = this.directionCaseAvt2(case0);
                        this.eraseCase(case0);
//                        System.out.println("direction avant :"+directionavt);
//                        System.out.println("erase + affche case effacee");
//                        case0.affiche();

//                        System.out.println("reinit a 0 decision");//reinitialisation a 0 !!!! verif pas case depart ?
                        decision[case0.i - 1][case0.j - 1] = 0;

                        case0 = case1;
                        //decision[case0.i-1][case0.j-1]=1;//on doit prendre une decision
                        //direction=1;//???!!!!

                        //pas sur :
                        if (case0.color == color - 1) {
                            //retour couleur avant
//                            System.out.println("retour couleur avt");
                            color -= 1;
                            casedepart = this.FindStart(color);//case depart
                            casearrivee = this.FindEnd(color);//case arrivee
                        } //on acualise c : else ?
                        else if (((directionavt != 1) & (this.donneCase(case0, 1).color == 0)) || ((directionavt != 2) & (this.donneCase(case0, 2).color == 0)) || ((directionavt != 3) & (this.donneCase(case0, 3).color == 0)) || ((directionavt != 4) & (this.donneCase(case0, 4).color == 0))) {//une case vide a cote autre que celle qu'on vient d effacer
                            c = true;
                            //on recupere la nouvelle direction
                            if ((directionavt != 1) & (this.donneCase(case0, 1).color == 0) & ((decision[case0.i - 1][case0.j - 1] == 0) || ((decision[case0.i - 1][case0.j - 1] == 1) & (directionavt < 1)))) {
//                                System.out.println("dir1");
//                                case0.affiche();
//                                this.donneCase(case0,1).affiche();
                                direction = 1;
                                decision[case0.i - 1][case0.j - 1] = 1;//on a pris une decision?
                            } else if ((directionavt != 2) & (this.donneCase(case0, 2).color == 0) & ((decision[case0.i - 1][case0.j - 1] == 0) || ((decision[case0.i - 1][case0.j - 1] == 1) & (directionavt < 2)))) {
                                direction = 2;
                                decision[case0.i - 1][case0.j - 1] = 1;//?
                            } else if ((directionavt != 3) & (this.donneCase(case0, 3).color == 0) & ((decision[case0.i - 1][case0.j - 1] == 0) || ((decision[case0.i - 1][case0.j - 1] == 1) & (directionavt < 3)))) {
                                direction = 3;
                                decision[case0.i - 1][case0.j - 1] = 1;//?
                            } else if ((directionavt != 4) & (this.donneCase(case0, 4).color == 0) & ((decision[case0.i - 1][case0.j - 1] == 0) || ((decision[case0.i - 1][case0.j - 1] == 1) & (directionavt < 4)))) {
                                direction = 4;
                                decision[case0.i - 1][case0.j - 1] = 1;//?
                            } else {//cas possible ? cas arret ?
                                decision[case0.i - 1][case0.j - 1] = 1;
//                                System.out.println("Pb direction bis >4");
                                c = false;
//                                System.out.println("direction  :"+directionavt);
//                                System.out.println("decision  dec:"+dec);
//                                System.out.println("decision  :"+decision[case0.i-1][case0.j-1]);
                            }
//                            System.out.println("nvlle direction  bis :"+direction);
//                            System.out.println("decision  :"+decision[case0.i-1][case0.j-1]);
//                            case0.affiche();
                        }
//                        else if((this.donneCase(case0,1).comparaison(casearrivee))||(this.donneCase(case0,2).comparaison(casearrivee))||(this.donneCase(case0,3).comparaison(casearrivee))||(this.donneCase(case0,4).comparaison(casearrivee))){//la case arrivee est a cote: normalement inutile
//                            c=true;
//                        }
                        //mark path

                    }

                    //Turn to unmarked path
                    //deja fait ds etape avt en editant direction
                    //Mark path
                    //? deja fait
                    //
                }
//                else if(case0.nb_marked!=-1){//ie pas d'obstacle donc pas de prise de decision hors case depart
//                    System.out.println("pas decision pour case de ligne: "+(case0.i)+" colonne: "+(case0.j)+" dec :"+dec+" decision :"+decision[case0.i-1][case0.j-1]);
//                    decision[case0.i-1][case0.j-1]=0;//on a suivi la direction de la case d avant
//                    System.out.println("decision actu: "+decision[case0.i-1][case0.j-1]);
//                }

                //System.out.println("avant add1");
                //case0.affiche();
                //Move forward
                if ((this.donneCase(case0, 1).comparaison(casearrivee)) || (this.donneCase(case0, 2).comparaison(casearrivee)) || (this.donneCase(case0, 3).comparaison(casearrivee)) || (this.donneCase(case0, 4).comparaison(casearrivee))) {
                    this.parcours[color].add(casearrivee);
//                    System.out.println("add1");
                } else {
//                    directionavt=this.directionCaseAvt2(case0);
//                    System.out.println("dir avt "+directionavt);
//                    if((case0.nb_marked!=-1)&(direction==directionavt)){//on reinitialise la decision
//                        System.out.println("reinit a 0 decision");
//                        //decision[this.donneCase(case0, direction).i-1][this.donneCase(case0, direction).j-1]=0;
//                        decision[case0.i-1][case0.j-1]=0;
//                    }
//                    System.out.println("avt mark decision: "+decision[case0.i-1][case0.j-1]);
                    this.mark(this.donneCase(case0, direction), color);
                    case0 = this.donneCase(case0, direction);

//                    System.out.println("mark ligne : "+case0.i+" colonne: "+case0.j+" dec "+dec+" decision "+decision[case0.i-1][case0.j-1]);
                    if ((this.donneCase(case0, 1).comparaison(casearrivee)) || (this.donneCase(case0, 2).comparaison(casearrivee)) || (this.donneCase(case0, 3).comparaison(casearrivee)) || (this.donneCase(case0, 4).comparaison(casearrivee))) {
                        this.parcours[color].add(casearrivee);
//                        System.out.println("add2");
//                        System.out.print("cases premiere et derniere du parcours "+color);
//                        this.parcours[color].getFirst().affiche();
//                        this.parcours[color].getLast().affiche();
                    }
                }

                //Number is found ?
//                System.out.println("color "+(color));
                if (this.parcours[color].getLast().comparaison(casearrivee)) {//if(this.colorLinked2(color)){   
                    //Yes
                    //End *color
                    color += 1;
                    b = false;
                    //System.out.println("color "+(color-1)+" linked ");

                    if (color >= this.nb_color) {
                        // System.out.print("end ");
                    } else {
                        //this.afficheparcours(0);
                        casedepart = this.FindStart(color);//case depart
                        casearrivee = this.FindEnd(color);//case arrivee
//                        System.out.print("cases depart et arrivee ");
//                        casedepart.affiche();
//                        System.out.print("col 4 "+this.matrice[4][0].nb_marked);
//                        casearrivee.affiche();
                    }

                } else {
                    //No
                    //again  
                    //this.affiche();
                }
            }//End
            System.out.println("solve2");
        }//Big End

    }

    /**
     * Fonction rectif (nb_marked et parcours) Rectifie l'erreur ds les
     * nb_marked
     */
    public void rectif() {
        //parcours
        for (int c = 0; c <= this.nb_color; c++) {
            this.parcours[c].clear();
        }

        //nb_marked
        int ligne = this.length - 1;
        int col = this.length - 1;
        int min = 1;//le minimum
        int min0;//le minimum a rectifier
        int i = 0;//ligne
        int j = 0;//colonne
        System.out.println("limite min :" + (this.length * this.length - 2 * this.nb_color));
        while (min <= (this.length * this.length - 2 * this.nb_color)) {
            ligne = this.length - 1;
            col = this.length - 1;
            i = ligne;
            j = col;
            min0 = this.matrice[ligne][col].nb_marked;
            while ((ligne > -1) & (col > -1)) {
                if ((this.matrice[ligne][col].nb_marked < min0) & (this.matrice[ligne][col].nb_marked >= min)) {
                    i = ligne;
                    j = col;
                    min0 = this.matrice[ligne][col].nb_marked;
                } else if (min0 < min) {
                    i = ligne;
                    j = col;
                    min0 = this.matrice[ligne][col].nb_marked;
                }
                //on change de case
                if (col - 1 > -1) {
                    col -= 1;
                } else {
                    ligne -= 1;
                    col = this.length - 1;
                }

            }
            System.out.println("ligne :" + (i + 1) + " colonne :" + (j + 1));
            System.out.println("min : " + min + " min0 :" + min0);
            this.matrice[i][j].nb_marked = min;
            this.parcours[0].add(this.matrice[i][j]);//toutes les cases marquees
            this.parcours[this.matrice[i][j].color].add(this.matrice[i][j]);//toutes les cases de la couleur
            min += 1;
        }

    }
}
