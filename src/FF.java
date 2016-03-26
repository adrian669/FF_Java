package main;

import java.io.*;

public class FF{

    public static void main(String[] args) {
//
//        Case case11 = new Case(1, 1, 1, true, -1);
//        Case case12 = new Case(1, 2, 0, false, 0);
//        Case case13 = new Case(1, 3, 0, false, 0);
//        Case case21 = new Case(2, 1, 0, false, 0);
//        Case case22 = new Case(2, 2, 2, true, -1);
//        Case case23 = new Case(2, 3, 0, false, 0);
//        Case case31 = new Case(3, 1, 0, false, 0);
//        Case case32 = new Case(3, 2, 1, true, -1);
//        Case case33 = new Case(3, 3, 2, true, -1);
//
//        Grille grille1 = new Grille(3, 2);
//       
//        System.out.println(case11.comparaison(case11));

//        //System.out.println(grille1.length);
//
//        grille1.remplir(case11);
//        grille1.remplir(case12);
//        grille1.remplir(case13);
//        grille1.remplir(case21);
//        grille1.remplir(case22);
//        grille1.remplir(case23);
//        grille1.remplir(case31);
//        grille1.remplir(case32);
//        grille1.remplir(case33);
        //test fonction affiche
//        grille1.affiche();
//        grille1.FindStart(1).affiche();
//        grille1.parcours();
//        grille1.parcours[1].getFirst().affiche();
//        
//        
//      
//        //test fonctions FindStart et FindEnd
//        System.out.println("Start couleur 1:  i:"+grille1.FindStart(1).i+", j:"+grille1.FindStart(1).j);
//        System.out.println("End couleur 1:  i:"+grille1.FindEnd(1).i+", j:"+grille1.FindEnd(1).j);
//        
//        //test fctn donneCase (ie selon direction)
//        System.out.println(grille1.donneCase(case31, 1).i+"   "+grille1.donneCase(case31, 1).j);
//        System.out.println(grille1.donneCase(case31, 2).i+"   "+grille1.donneCase(case31, 2).j);
//        System.out.println(grille1.donneCase(case31, 3).i+"   "+grille1.donneCase(case31, 3).j);
//        
//        //test fonction canMark
//        System.out.println(grille1.canMark(case31, 2)+"");
//        System.out.println(grille1.canMark(case31, 1)+"");
//        
//        //test fonction mark
//        grille1.mark(case31, 1);
//        grille1.affiche();
//        
//        grille1.mark(case21, 1);
//        grille1.affiche();
//        grille1.mark(case31, 2);
//        grille1.affiche();
//        
////        //test fonction colorLinked
//        grille1.affiche();
//        System.out.println("couleur 1 liee ? "+grille1.colorLinked(1)+"");
//        grille1.mark(case21, 1);
//        grille1.mark(case31, 1);
//        grille1.affiche();
//        System.out.println("couleur 1 liee ? "+grille1.colorLinked(1)+"");
//        System.out.println("couleur 2 liee ? "+grille1.colorLinked(2)+"");
////        
////        //test fonction solved
//        grille1.affiche();
//        System.out.println("jeu resolu ? "+grille1.solved()+"");
//        grille1.mark(case12, 2);
//        grille1.mark(case13, 2);
//        grille1.mark(case23, 2);
//        grille1.affiche();
//        System.out.println(grille1.directionCaseAvt(case21)+" 21");
//        System.out.println(grille1.directionCaseAvt(case12)+" 12");
//        System.out.println(grille1.directionCaseAvt(case13)+" 13");
//        System.out.println(grille1.directionCaseAvt(case21)+" 21");
//        System.out.println(grille1.directionCaseAvt(case23)+" 23");
//        System.out.println("jeu resolu ? "+grille1.solved()+"");
////        
////        //test fonction eraseCase
//        System.out.println("test eraseCase");
//        grille1.eraseCase(case31);
//        grille1.affiche();
////        
////        //test fonction eraseAllCasesColor
//        System.out.println("test eraseAllCasesColor");
//        grille1.eraseAllCasesColor(2);
//        grille1.affiche();
////        
////        //test directionCaseAvt
//        System.out.println("test directionCaseAvt");
//        grille1.affiche();
//        System.out.println(grille1.directionCaseAvt(case21)+" 21");
////        
//        System.out.println("test fonction Solve");
//
        //grille1.Solve2(10000);
//        //grille1.mark(case12, 1);
//        grille1.affiche();
        //test constructeur Adrian par txt
//        try{
//        Grille grille2= new Grille("test7x7.csv" , 1);
//        grille2.affiche();
//        System.out.println(grille2.length);    
//        System.out.println(grille2.nb_color+" ");
//        grille2.afficheparcours(1);
//        grille2.parcours[1].getFirst().affiche();
//        System.out.println(grille2.parcours[0].size()+" ");
//        grille2.Solve2(10000);
//        grille2.affiche();
//        }
//        catch(NullPointerException e){
//             System.out.println("NullPoinerException de FF");
//        }
//        catch(FileNotFoundException e){
//             System.out.println("FileNotFound Exception de FF");
//        }
//        catch(Exception e){
//                e.printStackTrace();
//             System.out.println("Exception de FF");
////        }
//        try {
//            Grille grille3 = new Grille("test5x5.csv", 1);
//            grille3.affiche();
//            System.out.println(grille3.length);
//            System.out.println(grille3.nb_color + " ");
//            grille3.afficheparcours(1);
//            grille3.parcours[1].getFirst().affiche();
//            System.out.println(grille3.parcours[0].size() + " ");
//            grille3.Solve2(10000);
//            grille3.affiche();
//        } catch (NullPointerException e) {
//            System.out.println("NullPoinerException de FF");
//        } catch (FileNotFoundException e) {
//            System.out.println("FileNotFound Exception de FF");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Exception de FF");
//        }
//        try {
//            Grille grille3 = new Grille("test5x5.csv", 1);
//            grille3.affiche();
//            System.out.println(grille3.length);
//            System.out.println(grille3.nb_color + " ");
//            grille3.afficheparcours(1);
//            grille3.parcours[1].getFirst().affiche();
//            System.out.println(grille3.parcours[0].size() + " ");
//            grille3.Solve2(10000);
//            grille3.affiche();
//            grille3.affichenbmarked();
//            grille3.rectif();
//            grille3.affichenbmarked();
//            System.out.println("" + grille3.colorLinked(1));
//            System.out.println("" + grille3.colorLinked(2));
//            System.out.println("" + grille3.colorLinked(3));
//            System.out.println("" + grille3.colorLinked(4));
//            System.out.println("" + grille3.solved());
//        } catch (NullPointerException e) {
//            System.out.println("NullPoinerException de FF");
//        } catch (FileNotFoundException e) {
//            System.out.println("FileNotFound Exception de FF");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Exception de FF");
//        }
//        try {
//           long t = 100;
//            Grille grille6 = new Grille(5);
        // grille6.affiche();
        //         grille6.Solve2(t);
//////            //boolean test = !grille6.solved();
//           grille6.aleaGrille(5, t);
//            grille6.affiche();
//            System.out.println("fin ff");
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            System.out.println("NullPoinerException de FF");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Exception de FF");
//        }
//        
////        generation aleatoire
//        Grille grille3 = new Grille(4);
//        grille3.affiche();
//        
//        
//        
//        //verif parcours
//        grille1.eraseAllCasesColor(1);
//        grille1.eraseAllCasesColor(2);
//        grille1.affiche();
//        grille1.mark(case12, 2);
//        grille1.mark(case13, 2);
//        grille1.mark(case23, 2);
//        grille1.affiche();
//        grille1.parcours[2].element().affiche();
//        grille1.afficheparcours(2);//OK
//        System.out.println(" parcours, couleur :"+0+" , taille : "+grille1.parcours[0].size());
//        System.out.println(" parcours, couleur :"+1+" , taille : "+grille1.parcours[1].size());
//        System.out.println(" parcours, couleur :"+2+" , taille : "+grille1.parcours[2].size());
//        grille1.eraseAllCasesColor(2);
//        System.out.println(" parcours apres eraseallcasecolor, couleur :"+0+" , taille : "+grille1.parcours[0].size());
//        System.out.println(" parcours apres eraseallcasecolor, couleur :"+2+" , taille : "+grille1.parcours[2].size());
//        grille1.afficheparcours(2);//OK
//        
//        
//        //test parcours  constructeur aleatoire
//        grille3.affiche();
//        grille3.parcours[1].getFirst().affiche();
//       
//        
//  
//       
//try {
//            Grille grilletest = new Grille("test8x8.csv", 4);
//            grilletest.affiche();
//        } catch (Exception e) {
//
//        }
        // Pour reconnaitre tes fichiers de test :
        // test  new Grille("test6x6", 4);
        // test2 new Grille("test6x6.csv", 5);
        // test4  new Grille("test5x5.csv", 4);
        // test5 new Grille("test5x5.csv", 5);
        // test 6 new Grille("test6x6.csv", 6);
        // test 7 new Grille("test6x6.csv", 7);
        // test ex new Grille("test8x8.csv", 4);
//         Lancement de l'interface graphique
        Gui essai = new Gui();
        essai.setVisible(true);

    }



}
