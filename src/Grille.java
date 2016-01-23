/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.*;
import java.io.*;

public class Grille{
    
    //Attributs
    
     Case[][] matrice; //la grille (indices commencent à 0)
     int length;//taille de la grille (length*length)
     int nb_color; //nombre de couleurs à relier
     int nb_cases_marked=0;//nombre de cases marquées
     int nb_color_linked=0;// nombre de couleurs liées
     boolean arret=false;//vrai si on arrete de chercher une solution, faux si on continue
     boolean sucess=false;//vrai si une solution existe; faux sinon
     LinkedList[] parcours;//liste des cases marquees pour chaque couleur (avec depart et arrivee) et la premiere liste contient toutes les cases marquees couleurs confondues
     
     
     
     //Constructeurs
     
     //constructeur d'une grille vide de taille donnée et avec un nb de couleur donné
     Grille(int length1, int nb_color1){
         this.matrice = new Case [length1][length1];
         this.length=length1;
         this.nb_color=nb_color1;
         this.parcours=new LinkedList[nb_color1];//a ajouter ds constructeur Adrian
         int i=1;
//         while (i<=this.nb_color){
//             this.parcours[i].addLast(this.FindStart(i));
//         }
    }
     
    //constructeur genration aleatoire
    Grille(int length1) {

        this.matrice = new Case[length1][length1];
        this.length = length1;
        this.nb_color = length1;
        nb_cases_marked = 0;
        nb_color_linked = 0;
        arret = false;
        sucess = false;
        int x1;
        int y1;
        int x2;
        int y2;

        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length1; j++) {
                this.matrice[i][j] = new Case(i + 1, j + 1, 0, false, 0);
            }
        }

        for (int i = 1; i <= length1 - 1; i++) {
            Random rand = new Random();
            x1 = rand.nextInt(length1);
            y1 = rand.nextInt(length1);
            x2 = rand.nextInt(length1);
            y2 = rand.nextInt(length1);

            if ((!((x1 == x2) && (y1 == y2))) && (!(this.matrice[x1][y1].color != 0)) && (!(this.matrice[x2][y2].color != 0)) && (this.matrice[x1][y1].nb_marked == 0) && (this.matrice[x2][y2].nb_marked == 0)) {
                this.matrice[x1][y1].i = this.matrice[x1][y1].SetPosition_i(x1 + 1);
                this.matrice[x1][y1].j = this.matrice[x1][y1].SetPosition_j(y1 + 1);
                this.matrice[x1][y1].color = this.matrice[x1][y1].SetColor(i);
                this.matrice[x1][y1].nb_marked = this.matrice[x1][y1].SetMarked();
                this.matrice[x2][y2].i = this.matrice[x2][y2].SetPosition_i(x2 + 1);
                this.matrice[x2][y2].j = this.matrice[x2][y2].SetPosition_j(y2 + 1);
                this.matrice[x2][y2].color = this.matrice[x2][y2].SetColor(i);
                this.matrice[x2][y2].nb_marked = this.matrice[x2][y2].SetMarked();
            } else {
                i = i - 1;
            }
        }
    }
    

     //constructeur a partir d'un fichier texte contenant 1 grille
    Grille(String nom_fich) throws java.io.IOException {
        BufferedReader br = null;
        FileReader reader = null;
        File f = null;
        int tempo;
        int nb_ligne = 0;
        arret = false;
        sucess = false;
        nb_cases_marked = 0;
        nb_color_linked = 0;
        //int [] tokens;

        try {
            String line;
            tempo = 0;
            f = new File(nom_fich);
            reader = new FileReader(f);
            br = new BufferedReader(reader);
            Scanner scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(
                                    new File(nom_fich))));

            //scanner.useDelimiter("[; \n]");
            while ((line = br.readLine()) != null) {
                nb_ligne = nb_ligne + 1;
            }
            //System.out.println(scanner.hasNextInt());
            //tokens = new int[nb_ligne];
            // int i = 0;
            // while(scanner.hasNextInt()) {
            //    nb_ligne = nb_ligne + 1;
            //System.out.print(scanner.next() + "|");
            //tokens[i] = scanner.nextInt();
            // System.out.print(tokens[i] + "|");
            //}
            //System.out.println(tokens[5]);
            this.matrice = new Case[nb_ligne][nb_ligne];
            this.length = nb_ligne;

            int max = 0;
            for (int ligne = 0; ligne < this.length; ligne++) {
                for (int col = 0; col < this.length; col++) {
                    if (scanner.hasNextInt()) {
                        tempo = scanner.nextInt();
                        //System.out.println("tempo");
                        if (tempo > max) {
                            max = tempo;
                        }
                        if (tempo == 0) {
                            matrice[ligne][col] = new Case(ligne + 1, col + 1, tempo, false, 0);
                            //System.out.println("matrice 1");
                        } else {
                            matrice[ligne][col] = new Case(ligne + 1, col + 1, tempo, true, -1);
                            //System.out.println("matrice 2");
                        }
                    }
                }
            }
            
            scanner.close();
            
            
        //System.out.println(this.matrice[0][4].color);

            //this.nb_color = max;
            //System.out.println(nb_ligne + "");
            //System.out.println(this.matrice[0][0].i + "");
            //Random rand = new Random();
            //System.out.println(rand.nextInt(6) + 1);           
        } catch (FileNotFoundException e) {
            System.out.println("Fichier pas trouvé de grille");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOexception de grille");
        } catch (NullPointerException e) {
            System.out.println("Null Pointer exception de grille");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception de grille");
        } finally {
            try {
                //scanner.close();
                br.close();
                reader.close();
            } catch (Exception e) {
                System.out.println("Exception de finally");
            }
        }

    }
     
     
     //Fonctions
     
     //fonction remplir
     //arguments: case
     //remplit une case de la grille a partir d'une case
     void remplir(Case case1){
         this.matrice[case1.i -1][case1.j -1]=case1;
     }
     
     //fonction affiche
     void affiche(){
        int ligne=0;
        int col=0;
        System.out.print("\n");
        while(ligne<this.length){
            String chaine="";
            while(col<this.length){
                chaine=chaine+"|"+this.matrice[ligne][col].color;
                col++;
            }
            System.out.println(chaine+"|");
            col=0;
            ligne++;
        }
        System.out.print("\n");
    }
     
     //fonction FindStart
     //trouve la case de depart d'une couleur
     //arguments: color,
     //sorties: case
     Case FindStart(int color1){
         Case start;
         int ligne=0;
         int col=0;
         start=this.matrice[ligne][col];
         int s=0;
         while((s==0)&(ligne<this.length)&(col<this.length)){
             if((this.matrice[ligne][col].color==color1)&(this.matrice[ligne][col].nb_marked==-1)){
                 s=1;
                 start=this.matrice[ligne][col];
             }
             else{
                 if(col+1<this.length){
                     col=col+1;
                 }
                 else{
                     
                         ligne=ligne+1;
                         col=0;
                 }
             }
         }
         return start;
     }
     
     //fonction FindEnd
     //trouve la case d'arrivee d'une couleur
     //arguments: color,
     //sorties: case
     Case FindEnd(int color1){
         Case end;
         int ligne=this.length-1;
         int col=this.length-1;
         end=this.matrice[ligne][col];
         int s=0;
         while((s==0)&(ligne>-1)&(col>-1)){
             if((this.matrice[ligne][col].color==color1)&(this.matrice[ligne][col].nb_marked==-1)){
                 s=1;
                 end=this.matrice[ligne][col];
             }
             else{
                 if(col-1>-1){
                     col=col-1;
                 }
                 else{
                     
                         ligne=ligne-1;
                         col=this.length-1;
                 }
             }
         }
         return end;
     }
     
     //fonction donneCase
     //retourne la case au Nord (1), a l'Est (2) ,au Sud (3) ou a l'Ouest (4) de la case donnee
     //arguments : 1 case et 1 position
     //sortie: 1 case (la meme si l'autre n'existe pas)
     Case donneCase(Case case1, int pos){
         Case end=case1;
         if((pos==1)&((case1.i -2) >-1)&((case1.i -2) <this.length)){
             end=this.matrice[case1.i -2][case1.j-1];//coeff doivent toujou etre rectifie par - 1
         }
         else if((pos==2)&((case1.j) >-1)&((case1.j) <this.length)){
             end=this.matrice[case1.i-1][case1.j];
         }
         else if((pos==3)&((case1.i) >-1)&((case1.i) <this.length)){
             end=this.matrice[case1.i][case1.j-1];
         }
         else if((pos==4)&((case1.j -2) >-1)&((case1.j -2) <this.length)){
             end=this.matrice[case1.i-1][case1.j-2];
         }
         
         return end;
     }
     
     //fonction canMark
     //dit si on peut marquer une case par une couleur (ie case vide a cote de la derniere case marquee par cette couleur )
     //arguments : 1 case, 1 couleur
     //sortie : boolean
     boolean canMark(Case case1, int color1){
         //on calcule le maximum de nb_marked pour une couleur
         int nb=-1;//point de depart
         int ligne=this.length-1;
         int col=this.length-1;
        while((ligne>-1)&(col>-1)){
            if((this.matrice[ligne][col].nb_marked>nb)&(this.matrice[ligne][col].color==color1)){//case marquee par couleur 1 et apres nb
                nb=this.matrice[ligne][col].nb_marked;
            }
            //on change de case
             if(col-1>-1){
                 col=col-1;
             }
             else{
                     ligne=ligne-1;
                     col=this.length-1;
             }
        }
         //on teste si une case autour de case1 a la derniere case marquee par la couleur color1
         return ((!(case1.nb_marked==-1))&(!(case1.comparaison(donneCase(case1,1)))&(donneCase(case1,1).color==color1)&(donneCase(case1,1).nb_marked==nb))||((!(case1.comparaison(donneCase(case1,2))))&(donneCase(case1,2).color==color1)&(donneCase(case1,2).nb_marked==nb))||((!(case1.comparaison(donneCase(case1,3)))&(donneCase(case1,3).color==color1)&(donneCase(case1,3).nb_marked==nb)))||((!(case1.comparaison(donneCase(case1,4)))&(donneCase(case1,4).color==color1)&(donneCase(case1,4).nb_marked==nb))));
     }
     
     //fonction mark
     //on marque une cellule dans la grille (! verifier dans algo avt avec canMark!)
     //rmq: si on marque une case par la couleur 1 deja marquee pour une couleur 2 alors toutes les cases marquees par 2 se vident (sauf les cases depart)
     //si on marque une case par la couleur 1 deja marquee pour cette couleur alors on efface les cases seulement marquee par cette couleur et apres cette case
     //arguments : case, color
     void mark(Case case1, int color1){
         if(case1.color==0){//case vide
             case1.color=color1;
             case1.marked=true;
             case1.nb_marked=this.nb_cases_marked+1;
             this.nb_cases_marked=this.nb_cases_marked+1;
         }
         else if(case1.color==color1){//case deja marquee par cette couleur 1
             
             //on efface les cases marquees par la couleur 1
             int nbcaseseffacees=0;
             int maxcaseseffacees=0;
             int ligne=this.length-1;
             int col=this.length-1;
            while((ligne>-1)&(col>-1)){
                if((this.matrice[ligne][col].color==color1)&(this.matrice[ligne][col].nb_marked!=-1)&(this.matrice[ligne][col].nb_marked>case1.nb_marked)){//case marquee par couleur 1 et apres case1 mais pas de depart
                    nbcaseseffacees=nbcaseseffacees+1;
                    //on vide cette case
                    this.matrice[ligne][col].color=0;
                    this.matrice[ligne][col].marked=false;
                    if(this.matrice[ligne][col].nb_marked>maxcaseseffacees){
                        maxcaseseffacees=this.matrice[ligne][col].nb_marked;
                    }
                    this.matrice[ligne][col].nb_marked=0;
                    
                }
                //on change de case
                 if(col-1>-1){
                     col=col-1;
                 }
                 else{
                         ligne=ligne-1;
                         col=this.length-1;
                 }
                 //on ajoute la nouvelle case dans le parcours associe a la couleur
                 this.parcours[color1].addLast(case1);
                 //et dans la liste regroupant toutes les cases marquees 
                 this.parcours[0].addLast(case1);
            }
             
             
             
             //on rectifie les nb_marked de toutes les cases
             ligne=this.length-1;
             col=this.length-1;
            while((ligne>-1)&(col>-1)){
                if(this.matrice[ligne][col].nb_marked>maxcaseseffacees){//case marquee par couleur 2 mais pas de depart
                    this.matrice[ligne][col].nb_marked=this.matrice[ligne][col].nb_marked-nbcaseseffacees;
                }
                //on change de case
                 if(col-1>-1){
                     col=col-1;
                 }
                 else{
                         ligne=ligne-1;
                         col=this.length-1;
                 }
            }
            
            //on adapte le nombre de cases marquees
             this.nb_cases_marked=this.nb_cases_marked-nbcaseseffacees;
             case1.nb_marked=this.nb_cases_marked;
         }
         else{//case marquee par une autre couleur 2
             //on marque la case par la nouvelle couleur 1
             int color2;
             color2=case1.color;
             case1.color=color1;
             case1.marked=true;
             
             //on efface les cases marquees par la couleur 2
             int nbcaseseffacees=0;
             int maxcaseseffacees=0;
             int ligne=this.length-1;
             int col=this.length-1;
            while((ligne>-1)&(col>-1)){
                if((this.matrice[ligne][col].color==color2)&(this.matrice[ligne][col].nb_marked!=-1)){//case marquee par couleur 2 mais pas de depart
                    nbcaseseffacees=nbcaseseffacees+1;
                    //on vide cette case
                    this.matrice[ligne][col].color=0;
                    this.matrice[ligne][col].marked=false;
                    if(this.matrice[ligne][col].nb_marked>maxcaseseffacees){
                        maxcaseseffacees=this.matrice[ligne][col].nb_marked;
                    }
                    this.matrice[ligne][col].nb_marked=0;
                    //liste couleur 2
                    this.parcours[color2].remove(this.matrice[ligne][col]);
                    //liste ttes cases marquees
                    this.parcours[0].remove(this.matrice[ligne][col]);
                }
                //on change de case
                 if(col-1>-1){
                     col=col-1;
                 }
                 else{
                         ligne=ligne-1;
                         col=this.length-1;
                 }
                 
            }
             
             
             
             //on rectifie les nb_marked de toutes les cases
             ligne=this.length-1;
             col=this.length-1;
            while((ligne>-1)&(col>-1)){
                if(this.matrice[ligne][col].nb_marked>maxcaseseffacees){//case marquee par couleur 2 mais pas de depart
                    this.matrice[ligne][col].nb_marked=this.matrice[ligne][col].nb_marked-nbcaseseffacees;
                }
                //on change de case
                 if(col-1>-1){
                     col=col-1;
                 }
                 else{
                         ligne=ligne-1;
                         col=this.length-1;
                 }
            }
            
            //on adapte le nombre de cases marquees
             this.nb_cases_marked=this.nb_cases_marked-nbcaseseffacees;
             case1.nb_marked=this.nb_cases_marked;
         }
     }
     
     //fonction colorLinked (ancienne methode)
     //arguments : 1 couleur
     //sortie: boolean
     //vrai si la couleur est liee, faux sinon
     boolean colorLinked(int color1){
         boolean end=false;
         boolean while0=true;
         Case case0=this.FindStart(color1);
         Case case1=this.FindStart(color1);
         Case caseend=this.FindEnd(color1);
         int nb=0;//future case doit etre >
         int nb2=100;//doit etre <
         int ligne=this.length-1;
         int col=this.length-1;
         //on trouve la premiere case marquee pour cette couleur
        while((ligne>-1)&(col>-1)){
            if((this.matrice[ligne][col].color==color1)&(this.matrice[ligne][col].nb_marked>nb)&(this.matrice[ligne][col].nb_marked<nb2)){//case marquee par couleur 1 et avt nb
                case1=this.matrice[ligne][col];
                nb2=this.matrice[ligne][col].nb_marked;
            }
            //on change de case
             if(col-1>-1){
                 col=col-1;
             }
             else{
                     ligne=ligne-1;
                     col=this.length-1;
             }
        }
        //on trouve de quelle case elle est partie
        if((this.donneCase(case1, 1).comparaison(this.FindStart(color1)))||(this.donneCase(case1, 2).comparaison(this.FindStart(color1)))||(this.donneCase(case1, 3).comparaison(this.FindStart(color1)))||(this.donneCase(case1, 4).comparaison(this.FindStart(color1)))){
            case0=this.FindStart(color1);
            caseend=this.FindEnd(color1);
        }
        else if((this.donneCase(case1, 1).comparaison(this.FindEnd(color1)))||(this.donneCase(case1, 2).comparaison(this.FindEnd(color1)))||(this.donneCase(case1, 3).comparaison(this.FindEnd(color1)))||(this.donneCase(case1, 4).comparaison(this.FindEnd(color1)))){
            case0=this.FindEnd(color1);
            caseend=this.FindStart(color1);
        }
//        else{
//            System.out.println("case0 :  i:" +case0.i+"   j:"+case0.j);
//            System.out.println("case1 :  i:" +case1.i+"   j:"+case1.j);
//            System.out.println("caseend :  i:" +caseend.i+"   j:"+caseend.j);
//            System.out.println("Erreur 1 colorLinked");
//        }
         while (while0){
             nb=case1.nb_marked;
             nb2=100;
             case0=case1;
             ligne=this.length-1;
             col=this.length-1;
             //on trouve la premiere case marquee pour cette couleur
            while((ligne>-1)&(col>-1)){
                if((this.matrice[ligne][col].color==color1)&(this.matrice[ligne][col].nb_marked>nb)&(this.matrice[ligne][col].nb_marked<nb2)){//case marquee par couleur 1 et avt nb
                    case1=this.matrice[ligne][col];
                    nb2=this.matrice[ligne][col].nb_marked;
                }
                //on change de case
                 if(col-1>-1){
                     col=col-1;
                 }
                 else{
                         ligne=ligne-1;
                         col=this.length-1;
                 }
            }
            if(case1.comparaison(case0)){
                while0=false;
                if((this.donneCase(case1, 1).comparaison(caseend))||(this.donneCase(case1, 2).comparaison(caseend))||(this.donneCase(case1, 3).comparaison(caseend))||(this.donneCase(case1, 4).comparaison(caseend))){
                   end=true;
                }
            }
         }
         return end;
     }
        
     //fonction colorLinked2 (avec liste)
     //arguments : 1 couleur
     //sortie: boolean
     //vrai si la couleur est liee, faux sinon
     boolean colorLinked2(int color1){
         Case case1;
         Case case2;
         case1= (Case)this.parcours[color1].getLast();//derniere case pour la couleur 
         case2=(Case)this.parcours[color1].getFirst();//premiere case pour la couleur 
         return((case1.nb_marked==-1)&(case2.nb_marked==-1)&(!case1.comparaison(case2)));
         //vrai si la premiere case est la case de depart et si la derniere est la case d arrivee
     }
     
     //fonction solved
     //arguments : none
     //sortie : boolean
     //dit si la partie est finie (pour le joueur)
     boolean solved(){
         //calcul du nombre de couleurs liees
         int nblinked=0;
         int colori=1;
         while(colori<=this.nb_color){
             if(this.colorLinked(colori)){
                 nblinked=nblinked+1;
             }
             colori=colori+1;
         }
         this.nb_color_linked=nblinked;
         
         //calcul du nombre de cases vides
         int nbempty=0;
         int ligne=this.length-1;
         int col=this.length-1;
        while((ligne>-1)&(col>-1)){
            if(this.matrice[ligne][col].color==0){//case vide
                nbempty=nbempty+1;
            }
            //on change de case
             if(col-1>-1){
                 col=col-1;
             }
             else{
                     ligne=ligne-1;
                     col=this.length-1;
             }
        }
         //toutes les couleurs sont liees et pas de cases vides
         if((nbempty==0)&(this.nb_color_linked==this.nb_color)){
             this.sucess=true;
         }
         return this.sucess;
     }
     
     //fonction solvedAlgo
     //arguments : none
     //sortie : boolean
     //dit si la partie est finie (pour l'Algo donc ss compter cases vides)
     boolean solvedAlgo(){
         //calcul du nombre de couleurs liees
         int nblinked=0;
         int colori=1;
         while(colori<=this.nb_color){
             if(this.colorLinked(colori)){
                 nblinked=nblinked+1;
             }
             colori=colori+1;
         }
         this.nb_color_linked=nblinked;
         
         //toutes les couleurs sont liees et pas de cases vides
         if(this.nb_color_linked==this.nb_color){
             this.sucess=true;
         }
         return this.sucess;
     }
     
     //fonction eraseAllCasesColor
     //arguments: 1 color
     //efface toutes les cases marquees par la couleur color1
     void eraseAllCasesColor(int color1){
         int ligne=this.length-1;
            int col=this.length-1;
            while((ligne>-1)&(col>-1)){
                if(this.matrice[ligne][col].color==color1){
                    eraseCase(this.matrice[ligne][col]);
                }
                //on change de case
                if(col-1>-1){
                    col=col-1;
                }
                else{
                     ligne=ligne-1;
                     col=this.length-1;
                }
            }
        this.sucess=false;
     }
    
     //fonction eraseCase
     //arguments : 1 case
     //efface cette case (ie reinitialise a vide si pas case depart) et rectifie bien les nb_marked des autres cases
     void eraseCase(Case case1){
         if(case1.nb_marked!=-1){
            int nb1=case1.nb_marked;
            //on enleve cette case du parcours de sa couleur 
            this.parcours[case1.color].remove(case1);
            //on enleve cette case de la liste de tts les cases marquees
            this.parcours[0].remove(case1);
            case1.color=0;
            case1.marked=false;
            case1.nb_marked=0;
            int ligne=this.length-1;
            int col=this.length-1;
            
            //on rectifie les nb_marked de tts les autres cases
            while((ligne>-1)&(col>-1)){
                if(this.matrice[ligne][col].nb_marked>nb1){
                    this.matrice[ligne][col].nb_marked=this.matrice[ligne][col].nb_marked-1;
                }
                //on change de case
                if(col-1>-1){
                    col=col-1;
                }
                else{
                     ligne=ligne-1;
                     col=this.length-1;
                }
            }
         }
    }
     
     //fonction directionCaseAvt (ancienne methode)
     //arguments: 1 case
     //sortie : int (direction)
     //donne la direction utilisee par la case marquee avant celle-ci par la meme couleur
     int directionCaseAvt(Case case1) {
        int direction;
        if ((case1.color == 0)||(case1.nb_marked == -1)) {
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
                if ((this.matrice[ligne][col].nb_marked < nb1) & (this.matrice[ligne][col].nb_marked >= nb2)& (this.matrice[ligne][col].color == case1.color)) {
                    nb2 = this.matrice[ligne][col].nb_marked;
                    case2 = this.matrice[ligne][col];
                }
                //on change de case
                if (col - 1 > -1) {
                    col = col - 1;
                } else {
                    ligne = ligne - 1;
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
     
     //fonction directionCaseAvt2 (avec listes)
     //arguments: 1 case
     //sortie : int (direction)
     //donne la direction utilisee par la case marquee avant celle-ci par la meme couleur
     
     //fonction CaseAvt (ancienne methode)
     //arguments : 1 case
     //sortie: case
     //donne la case precedemment marquee ou la case depart (pas forcement de la mm couleur)
     Case CaseAvt(Case case1){
        int nb1 = case1.nb_marked;
        int nb2=0;
        Case case2;
        int ligne = this.length - 1;
        int col = this.length - 1;
        case2=this.matrice[ligne][col];
        //on cherche la case (case2) precedente
        while ((ligne > -1) & (col > -1)) {
            if ((this.matrice[ligne][col].nb_marked < nb1)&(this.matrice[ligne][col].nb_marked>nb2)) {
                nb2=this.matrice[ligne][col].nb_marked;
                case2=this.matrice[ligne][col];
            }
            //on change de case
            if (col - 1 > -1) {
                col = col - 1;
            } else {
                ligne = ligne - 1;
                col = this.length - 1;
            }
        }
        return case2; 
     }
     
     //fonction CaseAvt2 (avec listes)
     //arguments : 1 case
     //sortie: case
     //donne la case precedemment marquee ou la case depart (pas forcement de la mm couleur)

     
     
     
//     void Solve() {
//        boolean casarret=false;
//        int color1 = 1; //on commence par la couleur 1
//        int direction = 1; //on commence par la direction 1 (North)
//        //on trouve les cases de depart et de fin pour la couleur color1
//        Case casedepart = this.FindStart(color1);
//        Case casearrivee = this.FindEnd(color1);
//        Case case0;
//        Case case1;
//        case0 = casedepart; 
//        //System.out.println("test fonction Solve 1");
//        //System.out.println(""+this.solvedAlgo());
//        while ((!this.solvedAlgo())&(!casarret)) {//tant que les couleurs ne sont pas liees ie solvedAlgo faux
//            System.out.println("test fonction Solve");
//            this.affiche();
//            while (!this.colorLinked(color1)&(!casarret)) {//tant que la couleur color1 n est pas liee
//                System.out.println("test fonction Solve: couleur 1 non liée");
//                this.affiche();
//                if (donneCase(case0, direction).comparaison(case0)) {//si la case est la derniere dans cette direction 
//                    if (direction < 4) {//on change de direction
//                        direction=direction+1;
//                        System.out.println("test fonction Solve: chgt dir 1");
//                    } 
//                    else {//on efface la derniere case car on est bloque et on change de direction (par rapport directionavt?)
//                        boolean w=true;
//                        while(w & (!casarret)){
//                            System.out.println("test fonction Solve: efface 1");
//                            if(case0.nb_marked==-1){//si la derniere case0 actuelle est une case depart (ou arrivee possible?)
//                                if(case0.comparaison(this.FindStart(1))){//si c'est la case depart de la couleur 1
//                                    casarret=true;//cas d arret algo : on revient case depart et pas d autre direction possible
//                                    System.out.println("test fonction Solve casarret");
//                                }
//                                else{//sinon
//                                while(this.directionCaseAvt(case0)>3){//tant que la direction de la case d avant est West (4)
//                                    case1=this.CaseAvt(case0);
//                                    this.eraseCase(case0);//effacer derniere case marquee
//                                    //changer case0 et direction
//                                    case0=case1;
//                                    this.nb_cases_marked=this.nb_cases_marked-1;
//                                    color1=color1-1;//changer couleur
//                                    this.nb_color_linked=this.nb_color_linked-1;//delier derniere couleur
//                                    casedepart = this.FindStart(color1);
//                                    casearrivee = this.FindEnd(color1);
//                                }
//                                //changer direction
//                                System.out.println("test fonction Solve: efface 1.1");
//                                direction=this.directionCaseAvt(case0)+1;
//                                w=false;
//                                }
//                            }
//                            else{//sinon
//                                //effacer dernieres cases marquees jusqu a nouvelle direction possible
//                                while(this.directionCaseAvt(case0)>3){//tant que la direction de la case d avant est West (4)
//                                    case1=this.CaseAvt(case0);
//                                    this.eraseCase(case0);
//                                    //changer case0 et direction
//                                    case0=case1;
//                                    this.nb_cases_marked=this.nb_cases_marked-1;
//                                    System.out.println("test fonction Solve: efface 1.2 while");
//                                }
//                                direction=this.directionCaseAvt(case0)+1;
//                                w=false;
//                                System.out.println("test fonction Solve: efface 1.2"+this.directionCaseAvt(case0));
//                            }
//                        }
//                    }
//                } else {//si la case cherchee existe
//                    System.out.println("test fonction Solve: case cherchee existe");
//                    this.affiche();
//                    case1=donneCase(case0, direction);
//                    if(this.canMark(case1,color1)&(case1.nb_marked==0)){//si on peut la marquer
//                        System.out.println("test fonction Solve canMark");
//                        case1.affiche();
//                        System.out.println(case1.nb_marked+"");
//                        //on la marque et on change les cases
//                        case1.color=color1;
//                        case1.marked=true;
//                        this.nb_cases_marked=this.nb_cases_marked+1;
//                        case1.nb_marked=this.nb_cases_marked;
//                        case0=case1;
//                    }
//                    else if(!case1.comparaison(casearrivee)){//ce n est pas la case arrivee
//                        System.out.println("test fonction Solve comp");
//                        if (direction <4){//sinon on passe a une autre direction (si possible) et on reteste
//                            direction=direction+1;
//                            System.out.println("test fonction Solve comp dir");
//                        }
//                        else{//si impossible, efface derniere case
//                            boolean w=true;
//                            while(w & (!casarret)){
//                                System.out.println("test fonction Solve 3 bis");
//                                this.affiche();
//                                if(case0.nb_marked==-1){//si la derniere case0 actuelle est une case depart (ou arrivee possible?)
//                                    if(case0.comparaison(this.FindStart(1))){//si c'est la case depart de la couleur 1
//                                        casarret=true;//cas d arret algo : on revient case depart et pas d autre direction possible
//                                        System.out.println("test fonction Solve casarret");
//                                    }
//                                    else{//sinon
//                                        while(this.directionCaseAvt(case0)>3){//tant que la direction de la case d avant est West (4)
//                                            case1=this.CaseAvt(case0);
//                                            System.out.println("test fonction Solve erase bis");
//                                            this.eraseCase(case0);//effacer derniere case marquee
//                                            //changer case0 et direction
//                                            case0=case1;
//                                            this.nb_cases_marked=this.nb_cases_marked-1;
//                                            color1=color1-1;//changer couleur
//                                            this.nb_color_linked=this.nb_color_linked-1;//delier derniere couleur
//                                            casedepart = this.FindStart(color1);
//                                            casearrivee = this.FindEnd(color1);
//                                        }
//                                        //changer direction
//                                        direction=this.directionCaseAvt(case0)+1;
//                                        w=false;
//                                    }
//                                }
//                                else{//sinon
//                                    //effacer dernieres cases marquees jusqu a nouvelle direction possible
//                                    System.out.println("test fonction Solve erase 0");
//                                    System.out.println("case0");
//                                    if(this.directionCaseAvt(case0)<=3){//tant que la direction de la case d avant est West (4)
//                                        direction=this.directionCaseAvt(case1)+1;
//                                        case1=this.CaseAvt(case0);
//                                        System.out.println("test fonction Solve erase");
//                                        this.eraseCase(case0);
//                                        this.affiche();
//                                        //changer case0 et direction
//                                        case0=case1;
//                                        
//                                        this.nb_cases_marked=this.nb_cases_marked-1;
//                                        w=false;
//                                        
//                                    }
//                                    else{
//                                        while(this.directionCaseAvt(case0)>3){//tant que la direction de la case d avant est West (4)
//                                            direction=this.directionCaseAvt(case1)+1;
//                                            case1=this.CaseAvt(case0);
//                                            System.out.println("test fonction Solve erase");
//                                            this.eraseCase(case0);
//                                            this.affiche();
//                                            //changer case0 et direction
//                                            case0=case1;
//                                            this.nb_cases_marked=this.nb_cases_marked-1;
//                                        }
//                                    }
//                                    
//                                    //d
//                                    System.out.println("direction :"+direction);
//                                }
//                            }
//                        }
//                    }
//                }
//            }//sinon changer de couleur color1 (si possible)
//            this.nb_color_linked=color1;
//            if (color1+1<=this.nb_color){
//                color1=color1+1;//on passe a la couleur suivante
//                direction=1;//on reinitialise la direction à 1(North)
//                //on reinitialie les cases depart,arrivee et case0 pour l algo :
//                casedepart = this.FindStart(color1);
//                casearrivee = this.FindEnd(color1);
//                case0 = casedepart;
//                //case1=case0 ?
//            }
//        }//sinon fini
//        //this.sucess=true:pas forcement
//    }
}