/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author erwalind
 */
public class TestFFSSM {
    Club c;
    Moniteur president;
    Moniteur employe1;
    Calendar dEmbauche, fEmbauche,fEmbauche2, randomDate, delivrance1, delivrance2;
    Embauche embauche1;
    Site site1;
    Plongee plongee1;
    Plongeur plongeur1;
    Licence licence1, licence2;
    
    
    
    @Before
    public void setUp(){
        Calendar naissance0 = new GregorianCalendar(1995,03,25);
        Calendar naissance1 = new GregorianCalendar(1997,07,27);
        president = new Moniteur("1234", "Dubois", "Joël", "rue des Lilas", "0561487985", naissance0, 1, 5);
        employe1 = new Moniteur("5678", "Martin", "Théo", "allée des Forges", "0669874125", naissance1, 2, 3);
        c = new Club(president, "Club Requin", "0561486523");
        
        dEmbauche = new GregorianCalendar(2018,03,03);
        fEmbauche = new GregorianCalendar(2018,07,03); 
        fEmbauche2 = new GregorianCalendar(2018,07,05); 
        embauche1 = new Embauche(dEmbauche, employe1, c);
        

        
        site1 = new Site("Narbonne","sous l'eau");
        randomDate = new GregorianCalendar(2018,8,22);
        plongee1 = new Plongee(site1, employe1, randomDate, 30, 60);
        
        plongeur1 = new Plongeur("1234", "Durand", "Florence", "avenue Césaire", "0698425187", naissance1, 7);
        
        delivrance1 = new GregorianCalendar(2017,03,31);
        licence1 = new Licence(plongeur1, "8", delivrance1, 5, c);
        
        delivrance2 = new GregorianCalendar(2018,9,29);
        licence2 = new Licence(plongeur1, "9", delivrance2, 3, c);

        
        
        
        
        
    }
    
    @Test
    public void testEmployeur(){
        assertEquals("L'employé a déjà un emploi",null,employe1.employeur());
        employe1.nouvelleEmbauche(c, dEmbauche);        
        assertEquals("Le club renvoyé n'est pas le bon",c, employe1.employeur());
        
        
    }
    
    @Test
    public void testEmbauche(){        
        embauche1.terminer(fEmbauche);
        assertEquals(true, embauche1.estTerminee());
        assertEquals(null,employe1.employeur());
        
        embauche1.setFin(fEmbauche2);
        assertEquals(fEmbauche2, embauche1.getFin());
        
        assertEquals(dEmbauche, embauche1.getDebut());
        assertEquals(c, embauche1.getEmployeur());
        assertEquals(employe1, embauche1.getEmploye());
        
        //assertEquals(employe1.myEmbauches,employe1.emplois());
    }
    
    @Test
    public void testLicence(){
        assertEquals(plongeur1, licence1.getPossesseur());
        assertEquals(true, licence1.estValide(dEmbauche));
        
        Calendar today = Calendar.getInstance();
        assertEquals(false, licence1.estValide(today));
    }
    
    @Test
    public void testPlongeur(){
        plongeur1.ajouteLicence(licence1);
        assertEquals(true, plongeur1.myLicences.contains(licence1));
    }
    
    @Test
    public void testPlongee(){
        plongee1.ajouteParticipant(plongeur1);
        assertEquals(true, plongee1.myPlongeurs.contains(plongeur1));
        
        assertEquals(randomDate, plongee1.getDate());
        
        plongeur1.ajouteLicence(licence1);
        assertEquals(false,plongee1.estConforme());
        
        plongeur1.ajouteLicence(licence2);
        assertEquals(true,plongee1.estConforme());
    }
}
