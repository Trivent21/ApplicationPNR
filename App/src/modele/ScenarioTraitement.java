package modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.Map.Entry;

import modele.donnee.*;
import modele.traitement.*;

/**
 * It creates a graph and tests the methods of the graph class
 */
public class ScenarioTraitement {
    /**
     * It creates a graph and tests the methods of the graph class.
     * 
     * @param args not used.
     */
    public static void main(String[] args) {
        EspeceObservee espece1 = EspeceObservee.LOUTRE;
        Date Date = new Date(999999999);

        Lieu l1 = new Lieu(20, 20);
        Lieu l2 = new Lieu(20, 10);
        Lieu l3 = new Lieu(30, 20);
        Lieu l4 = new Lieu(80, 80);

        Sommet s1 = new Sommet(1, l1, Date, espece1);
        Sommet s2 = new Sommet(2, l2, Date, espece1);
        Sommet s3 = new Sommet(3, l3, Date, espece1);
        Sommet s4 = new Sommet(4, l4, Date, espece1);

        ArrayList<Sommet> listeGraph = new ArrayList<Sommet>();
        listeGraph.add(s1);
        listeGraph.add(s2);
        listeGraph.add(s3);
        listeGraph.add(s4);

        Graphe monGraph = new Graphe(listeGraph, 11);

        System.out.println("****TEST TOSTRINGHASMAP****");
        System.out.println(monGraph.toString());

        System.out.println("\n****TEST AJOUTEARETE****");
        monGraph.ajouteArete(1, 4);
        System.out.println(monGraph.toString());

        System.out.println("\n****TEST RETIREARETE****");
        monGraph.retireArete(1, 4);
        System.out.println(monGraph.toString());

        System.out.println("\n****TEST CALCULEDEGRE****");
        monGraph.printSommet(s1.getId());
        if (monGraph.calculeDegre(s1.getId()) == 2) {
            System.out.println("CalculeDegre OK : " + monGraph.calculeDegre(s1.getId()));
        } else {
            System.out.println("CalculeDegre FAIL " + monGraph.calculeDegre(s1.getId()));
        }

        System.out.println("****TEST CALCULEDEGRES****");
        Set<Entry<Sommet, Integer>> entrySet = monGraph.calculeDegres().entrySet();
        Integer valueIn;

        System.out.println("calculeDegres : ");
        for (Entry<Sommet, Integer> entry : entrySet) {
            System.out.println("id : " + entry.getKey().getId() + " ");
            valueIn = entry.getValue();
            System.out.println("Nombre degre :  " + valueIn);
        }

        System.out.println("****TESTE SONTVOISION****\nsontVoision Resultat true : "
                + monGraph.sontVoisins(s1.getId(), s2.getId()));
        System.out.println("sontVoision Resultat false : " + monGraph.sontVoisins(-1, s2.getId()));

        System.out.println("****TESTE MATRICEADJACENCE****\nmatriceAdjacence : "
                + Arrays.deepToString(monGraph.matriceAdjacence()));

        System.out.println("****TESTE ESTDANSGRAPHE****\nestDansGraphe true : " + monGraph.estDansGraphe(s1.getId()));
        System.out.println("estDansGraphe false : " + monGraph.estDansGraphe(-1));

        System.out.println("****TESTE MAXDEGRE****");
        if (monGraph.maxDegre() == 2) {
            System.out.println("maxDegre OK : " + monGraph.maxDegre());
        } else {
            System.out.println("maxDegre FAIL " + monGraph.maxDegre());
        }

        System.out.println("****TESTE VOISINS****");
        ArrayList<Sommet> teste = monGraph.voisins(s1.getId());
        System.out.print("id : " + s1.getId() + "\nListe : ");
        for (Sommet cl : teste) {
            System.out.print(cl.getId() + " ");
        }

        System.out.println("\n****TESTE EXISTECHEMIN****\nexisteChemin 1 et 2 true : " + monGraph.existeChemin(1, 2));
        System.out.println("existeChemin entre 1 et 4 false : " + monGraph.existeChemin(1, 4));

        System.out.println("****TESTE ESTCONNEXE****\nestConnexe false : " + monGraph.estConnexe());
        monGraph.ajouteArete(1, 4);
        System.out.println("estConnexe true : " + monGraph.estConnexe());

        System.out.println("\n****TESTE COMPOSANTCONNEXE****");
        monGraph.retireArete(1, 4);
        ArrayList<Graphe> listgraphe = monGraph.composanteConnexe();

        for (Graphe cl : listgraphe) {
            cl.toString();
            System.out.println("Verification de connexe : " + cl.estConnexe());
        }

        System.out.println("\n****TESTE DISARETES****");
        System.out.println("Distance entre 2 et 3 = 2 : " + monGraph.distArrete(1, 1));

        System.out.println("\n****TESTE EXCENTRICITE****");
        System.out.println("Excentricite : " + monGraph.excentricite(1));

        System.out.println("\n****TESTE RAYON ET DIAMETRE****");

        Lieu p1 = new Lieu(0, 0);
        Lieu p2 = new Lieu(1, 0);
        Lieu p3 = new Lieu(0, 1);
        Lieu p4 = new Lieu(2, 0);
        Lieu p5 = new Lieu(0, 2);

        Sommet v1 = new Sommet(1, p1, Date, espece1);
        Sommet v2 = new Sommet(2, p2, Date, espece1);
        Sommet v3 = new Sommet(3, p3, Date, espece1);
        Sommet v4 = new Sommet(4, p4, Date, espece1);
        Sommet v5 = new Sommet(5, p5, Date, espece1);

        ArrayList<Sommet> listeGraphtest = new ArrayList<Sommet>();
        listeGraphtest.add(v1);
        listeGraphtest.add(v2);
        listeGraphtest.add(v3);
        listeGraphtest.add(v4);
        listeGraphtest.add(v5);
        Graphe monGraphtest = new Graphe(listeGraphtest, 1);

        monGraphtest.ajouteArete(2, 3);
        System.out.println(monGraphtest.toString());
        System.out.println("Rayon doit etre 2 : " + monGraphtest.rayon());
        System.out.println("Diametre doit etre 3 :" + monGraphtest.diametre());
    }
}