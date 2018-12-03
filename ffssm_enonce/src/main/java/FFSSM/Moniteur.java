/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    private List<Embauche> myEmbauches = new LinkedList<>(); 

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, Calendar naissance, int numeroDiplome, int  niveau) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance, niveau);
        this.numeroDiplome = numeroDiplome;
    }

    public Club employeur() {
        if (myEmbauches.isEmpty())
            return null;
        Embauche e = myEmbauches.get(myEmbauches.size()-1);
        if (e.estTerminee()==false)
            return e.getEmployeur();
        else
            return null;
    }
    
    public void nouvelleEmbauche(Club employeur, Calendar debutNouvelle) {   
        Embauche e = new Embauche (debutNouvelle,this, employeur);
        myEmbauches.add(e);            
    }

    public List<Embauche> emplois() {
        return myEmbauches;
    }

}
