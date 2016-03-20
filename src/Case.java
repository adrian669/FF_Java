package main;

public class Case {

    //Attributs
    int i; //position ligne
    int j; //position colonne
    int color; //couleur (>=1) ou vide (0)
    boolean marked; //vrai si la case est remplie faux si elle est vide
    int nb_marked = 0; //0 si la case est vide, -1 si c'est une case départ, ou "h" pour h-ieme case marquée

    /**
     *
     * @param i1 position ligne
     * @param j1 position colonne
     * @param color1 numéro couleur ou vide (0)
     * @param marked1 vrai si la case est remplie faux si elle est vide
     * @param nb_marked1 //0 si la case est vide, -1 si c'est une case départ, ou "h" pour h-ieme case marquée
     */
        public Case(int i1, int j1, int color1, boolean marked1, int nb_marked1) {
        this.i = i1;
        this.j = j1;
        this.color = color1;
        this.marked = marked1;
        this.nb_marked = nb_marked1;
    }

    /**
     * affiche la couleur la ligne et la colonne
     */
        public void affiche() {
        System.out.println("\n");
        System.out.println("couleur : " + this.color + " ,  ligne : " + this.i + " , colonne : " + this.j);
    }

    /**
     * marque une case
     * @param color1 couleur
     * @param nb_marked1 nb_marked
     */
        public void mark(int color1, int nb_marked1) {
        this.color = color1;
        this.nb_marked = nb_marked1;
        this.marked = true;
    }

    /**
     * compare la position de cette case avec une autre case donnée
     * @param case1 case comparée
     * @return vrai si c'est les memes faux sinon
     */  
          public boolean comparaison(Case case1) {
            return (this.i == case1.i) && (this.j == case1.j);
    }
}
