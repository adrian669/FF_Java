
package main;


public class Case {
    //Attributs
     int i; //position ligne
     int j; //position colonne
     int color; //couleur (>=1) ou vide (0)
     boolean marked; //vrai si la case est remplie 0 si elle est vide
     int nb_marked = 0; //0 si la case est vide, -1 si c'est une case départ, ou "h" pour h-ieme case marquée
     
     
     //Constructeur
     Case(int i1,int j1, int color1, boolean marked1, int nb_marked1){
        this.i = i1; 
        this.j = j1; 
        this.color = color1; 
        this.marked = marked1;
        this.nb_marked=nb_marked1;
    }
     
     
     //Fonctions
     //fonction affiche
     void affiche(){
        System.out.println("\n");
        System.out.println(this.color+"");
    }
     
    
     //fonction mark
     //marque une case
     //arguments : color, nb_marked
     void mark(int color1, int nb_marked1){
             this.color=color1;
             this.nb_marked=nb_marked1;
             this.marked=true;
     }
     
     
     
     //fonction comparaison
     //compare cette case avec une autre case donnee
     //arguments: 1 case
     //sortie: boolean
     boolean comparaison(Case case1){
         boolean c;
         c = (this.i == case1.i)&(this.j == case1.j);
         return c;
     }
         
     //foncion SetPosition_i
     //change la position i de la case
     // argument la nouvelle position
     
     int SetPosition_i(int position_i) {
         this.i = position_i;    
         return i;
     }
     
     //foncion SetPosition_j
     //change la position j de la case
     // argument la nouvelle position
     int SetPosition_j(int position_j) {
         this.j = position_j;    
         return j;
     }
     
     int SetColor(int new_color) {
         this.color = new_color;
         return color;
     }
     
     int SetMarked() {
         this.nb_marked = -1;
         return nb_marked;
     }
                
}
