
package main;

public class FF {


    public static void main(String[] args) {
       
        
        Case case11= new Case (1,1,1,true,-1);
        Case case12= new Case (1,2,0,false,0);
        Case case13= new Case (1,3,0,false,0);
        Case case21= new Case (2,1,0,false,0);
        Case case22= new Case (2,2,2,true,-1);
        Case case23= new Case (2,3,0,false,0);
        Case case31= new Case (3,1,0,false,0);
        Case case32= new Case (3,2,1,true,-1);
        Case case33= new Case (3,3,2,true,-1);
        
        Grille grille1= new Grille(3,2);
        //System.out.println(grille1.length);
        
        grille1.remplir(case11);
        grille1.remplir(case12);
        grille1.remplir(case13);
        grille1.remplir(case21);
        grille1.remplir(case22);
        grille1.remplir(case23);
        grille1.remplir(case31);
        grille1.remplir(case32);
        grille1.remplir(case33);
        
        //test fonction affiche
        grille1.affiche();
        
        //test fonctions FindStart et FindEnd
        System.out.println("Start couleur 1:  i:"+grille1.FindStart(1).i+", j:"+grille1.FindStart(1).j);
        System.out.println("End couleur 1:  i:"+grille1.FindEnd(1).i+", j:"+grille1.FindEnd(1).j);
        
        //test fctn donneCase
        System.out.println(grille1.donneCase(case31, 1).i+"   "+grille1.donneCase(case31, 1).j);
        System.out.println(grille1.donneCase(case31, 2).i+"   "+grille1.donneCase(case31, 2).j);
        System.out.println(grille1.donneCase(case31, 3).i+"   "+grille1.donneCase(case31, 3).j);
        
        //test fonction canMark
        System.out.println(grille1.canMark(case31, 2)+"");
        System.out.println(grille1.canMark(case31, 1)+"");
        
        //test fonction mark
        //grille1.mark(case31, 1);
        //grille1.affiche();
//        grille1.mark(case21, 1);
//        grille1.affiche();
//        grille1.mark(case31, 2);
//        grille1.affiche();
        
        //test fonction colorLinked
        grille1.affiche();
        System.out.println("couleur 1 liee ? "+grille1.colorLinked(1)+"");
        grille1.mark(case21, 1);
        grille1.mark(case31, 1);
        grille1.affiche();
        System.out.println("couleur 1 liee ? "+grille1.colorLinked(1)+"");
        System.out.println("couleur 2 liee ? "+grille1.colorLinked(2)+"");
        
        //test fonction solved
        grille1.affiche();
        System.out.println("jeu resolu ? "+grille1.solved()+"");
        grille1.mark(case12, 2);
        grille1.mark(case13, 2);
        grille1.mark(case23, 2);
        grille1.affiche();
        System.out.println("jeu resolu ? "+grille1.solved()+"");
        
        //test fonction eraseCase
        grille1.eraseCase(case31);
        grille1.affiche();
        
        //test fonction eraseAllCasesColor
        grille1.eraseAllCasesColor(2);
        grille1.affiche();
        
        //test directionCaseAvt
        System.out.println(grille1.directionCaseAvt(case21)+"");
        
        
        
        
        //test constructeur Adrian par txt
        try{
        Grille grille2= new Grille("test.txt");
        grille2.affiche();
        System.out.println(grille2.length);        
        }
        catch(Exception e){
             System.out.println("marche pas");
        }
        
        
        Grille grille3 = new Grille(4);
        grille3.affiche();
        
                
        
    
    }
    
}
