package ui.page.vue;

import modele.donnee.*;
import ui.Launcher;
import ui.page.element.Page;

import javax.swing.*;
import java.awt.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * A class that extends the Page class.
 */
public class ChouetteVue extends Page {
    /**
     * A variable that is used to store the table.
     */
    private JTable tab;

    /**
     * A constructor.
     * 
     * @param lc the launcher of the page
     */
    public ChouetteVue(Launcher lc) {
        super(lc, "Chouette");
        int obsB = 0;
        String NumChouette;
        EspeceChouette espCh;
        TypeObservation typeCh;
        Sexe SexeCh;
        ArrayList<Observateur> listObs = new ArrayList<Observateur>();
        ArrayList<Chouette> listCh = new ArrayList<Chouette>();
        ArrayList<Observation> grandeListe = new ArrayList<Observation>();
        try {
            ResultSet rs = this.getLauncher().getBdd().query(
                    "SELECT idObs,dateObs,heureObs,lieu_Lambert_X,lieu_Lambert_Y,typeObs,leNumIndividu,numObs,numIndividu,espece,sexe FROM observation JOIN obs_Chouette ON idObs = numObs JOIN chouette ON lenumIndividu = numIndividu");
            rs.next();
            SexeCh = ChoixSexe(rs);
            espCh = ChoixEspese(rs);
            Chouette newChouette = new Chouette(rs.getString("numIndividu"), SexeCh, espCh);
            typeCh = ChoixType(rs);
            Lieu resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
            ObsChouette LObs = new ObsChouette(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"),
                    resLieu, listObs, typeCh);
            newChouette.ajouteUneObs(LObs);
            listCh.add(newChouette);
            grandeListe.add(LObs);
            while (rs.next()) {
                obsB = rs.getInt("idObs");
                NumChouette = rs.getString("numIndividu");
                rs.previous();
                if (NumChouette.equals(rs.getString("numIndividu"))) { // Nouvelle observation
                    rs.next();
                    typeCh = ChoixType(rs);
                    resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
                    LObs = new ObsChouette(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"), resLieu,
                            listObs, typeCh);
                    newChouette.ajouteUneObs(LObs);
                } else { // Nouvelle chouette
                    rs.next();
                    SexeCh = ChoixSexe(rs);
                    espCh = ChoixEspese(rs);
                    newChouette = new Chouette(rs.getString("numIndividu"), SexeCh, espCh);
                    typeCh = ChoixType(rs);
                    resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
                    LObs = new ObsChouette(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"), resLieu,
                            listObs, typeCh);
                    newChouette.ajouteUneObs(LObs);
                    listCh.add(newChouette);
                }
                grandeListe.add(LObs);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        // entête pour le tableau
        String[] entetes = { "IdObs", "Coord_X", "Coord_Y", "TypeObs", "idChouette", "Espece", "Sexe" };
        // Convertion de l'Arrayliste en tableau
        Object[][] donnee = new Object[grandeListe.size()][entetes.length];
        int i = 0;
        for (Chouette cl : listCh) {
            for (Observation cl1 : cl.getLesObservation()) {
                Object[] table = { cl1.getIdObs(), cl1.getLieuObs().getXCoord(), cl1.getLieuObs().getYCoord(),
                        ((ObsChouette) cl1).getTypeObs(), cl.getIdChouette(), cl.getEspece(), cl.getSexe() };
                donnee[i] = table;
                i++;
            }
        }
        this.setLayout(new BorderLayout());
        this.tab = new JTable(donnee, entetes);
        this.tab.setAutoCreateRowSorter(true);
        this.setPreferredSize(new Dimension(1080, 1920));
        this.add(this.tab.getTableHeader(), BorderLayout.NORTH);
        this.tab.setPreferredScrollableViewportSize(this.tab.getPreferredSize());
        this.tab.setFillsViewportHeight(true);
        this.add(this.tab, BorderLayout.CENTER);
        this.add(new JScrollPane(this.tab), JScrollBar.VERTICAL);
        JScrollPane tableSP = new JScrollPane(this.tab);
        tableSP.setPreferredSize(null);
        this.add(tableSP);
    }

    /**
     * It takes a ResultSet as a parameter and returns a Sexe enum
     * 
     * @param rs ResultSet
     * @return A Sexe object.
     */
    public Sexe ChoixSexe(ResultSet rs) {
        Sexe leSexe = null;
        try {
            if (rs.getString("sexe").equals("male")) {
                leSexe = Sexe.MALE;
            } else if (rs.getString("sexe").equals("femelle")) {
                leSexe = Sexe.FEMELLE;
            } else {
                leSexe = Sexe.INCONNU;
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        return leSexe;
    }

    /**
     * It takes a ResultSet object as a parameter and returns an EspeceChouette
     * object
     * 
     * @param rs ResultSet
     * @return The method returns the value of the variable espChEx.
     */
    public EspeceChouette ChoixEspese(ResultSet rs) {
        EspeceChouette espChEx = null;
        try {
            if (rs.getString("espece").equals("Hulotte")) {
                espChEx = EspeceChouette.HULOTTE;
            } else if (rs.getString("espece").equals("Effraie")) {
                espChEx = EspeceChouette.EFFRAIE;
            } else {
                espChEx = EspeceChouette.CHEVECHE;
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        return espChEx;
    }

    /**
     * It takes a ResultSet as a parameter and returns a TypeObservation
     * 
     * @param rs ResultSet
     * @return The method returns the type of observation.
     */
    public TypeObservation ChoixType(ResultSet rs) {
        TypeObservation typeChEx = null;
        try {
            if (rs.getString("typeObs").equals("Sonore")) {
                typeChEx = TypeObservation.SONORE;
            } else if (rs.getString("typeObs").equals("Visuel")) {
                typeChEx = TypeObservation.VISUELLE;
            } else {
                typeChEx = TypeObservation.SONORE_VISUELLE;
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        return typeChEx;
    }
}