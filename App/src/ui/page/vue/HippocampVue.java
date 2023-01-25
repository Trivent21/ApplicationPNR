package ui.page.vue;

import java.sql.*;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;

import modele.donnee.*;
import ui.Launcher;
import ui.page.element.Page;

/**
 * Display the hippocampe table
 */
public class HippocampVue extends Page {
    /**
     * A variable that is used to store the table.
     */
    private JTable tab;

    /**
     * A constructor.
     * 
     * @param lc the launcher of the page
     */
    public HippocampVue(Launcher lc) {
        super(lc, "Hippocampe");
        int obsHip = 0;
        EspeceHippocampe espHi;
        Peche laPeche;
        Sexe leSexe;
        ArrayList<Observation> grandeListe = new ArrayList<Observation>();

        try {
            ResultSet rs = this.getLauncher().getBdd().query(
                    "SELECT idObs,dateObs,heureObs,lieu_Lambert_X,lieu_Lambert_Y,idObservateur,nom,prenom,typePeche,taille,espece,sexe FROM observation JOIN obs_hippocampe ON idObs = obsH JOIN aobserve ON idObs = lobservation JOIN observateur ON idObservateur = lobservateur");
            rs.next();
            espHi = ChoixEspece(rs);
            laPeche = ChoixPeche(rs);
            leSexe = ChoixSexe(rs);
            Lieu resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
            Observateur resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                    rs.getString("prenom"));
            ArrayList<Observateur> listObs = new ArrayList<Observateur>();
            listObs.add(resObservateur);
            Observation LObs = new ObsHippocampe(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"),
                    resLieu, listObs, rs.getDouble("taille"), laPeche, espHi, leSexe);
            grandeListe.add(LObs);
            while (rs.next()) {
                obsHip = rs.getInt("idObs");
                rs.previous();
                if (rs.getInt("idObs") == obsHip) {
                    rs.next();
                    resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                            rs.getString("prenom"));
                    LObs.ajouteObservateur(resObservateur);
                } else {
                    rs.next();
                    espHi = ChoixEspece(rs);
                    laPeche = ChoixPeche(rs);
                    leSexe = ChoixSexe(rs);
                    resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
                    resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                            rs.getString("prenom"));
                    listObs = new ArrayList<Observateur>();
                    listObs.add(resObservateur);
                    LObs = new ObsHippocampe(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"), resLieu,
                            listObs, rs.getDouble("taille"), laPeche, espHi, leSexe);
                }
                grandeListe.add(LObs);
            }

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        // entête pour le tableau
        String[] entetes = { "IdObs", "date", "heure", "Coord_X", "Coord_Y", "Liste observateur", "taille", "Peche",
                "Espece", "Sexe" };
        // Convertion de l'Arrayliste en tableau
        Object[][] donnee = new Object[grandeListe.size()][entetes.length];
        int i = 0;
        String lesObservateurs = "";

        for (Observation cl : grandeListe) {
            // copier les valeurs dans chaque ligne du tableau
            for (Observateur cl2 : cl.getLesObservateurs()) {
                int last = cl.getLesObservateurs().size();
                if (last != cl.getLesObservateurs().size()) {
                    lesObservateurs = lesObservateurs + cl2.getNom() + " " + cl2.getPrenom() + ", ";
                } else {
                    lesObservateurs = lesObservateurs + cl2.getNom() + " " + cl2.getPrenom();
                }
            }
            Object[] table = { cl.getIdObs(), cl.getDateObs(), cl.getHeureObs(), cl.getLieuObs().getXCoord(),
                    cl.getLieuObs().getYCoord(), lesObservateurs, ((ObsHippocampe) cl).getTaille(),
                    ((ObsHippocampe) cl).getPeche(), ((ObsHippocampe) cl).getEspece(), ((ObsHippocampe) cl).getSexe() };
            donnee[i] = table;
            i++;
            lesObservateurs = "";
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
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return leSexe;
    }

    /**
     * It takes a ResultSet and returns an EspeceHippocampe
     * 
     * @param rs ResultSet
     * @return The method returns the enum value of the species of the seahorse.
     */
    public EspeceHippocampe ChoixEspece(ResultSet rs) {
        EspeceHippocampe esp = null;
        try {
            if (rs.getString("Espece") != null && rs.getString("Espece").equals("Hippocampus guttulatus")) {
                esp = EspeceHippocampe.HYPPOCAMPUS_GUTTULATUS;
            } else if (rs.getString("Espece") != null && rs.getString("Espece").equals("Syngnathus acus")) {
                esp = EspeceHippocampe.SYNGNATHUS_ACUS;
            } else if (rs.getString("Espece") != null && rs.getString("Espece").equals("Hippocampus hippocampus")) {
                esp = EspeceHippocampe.HIPPOCAMPUS_HIPPOCAMPUS;
            } else if (rs.getString("Espece") != null && rs.getString("Espece").equals("Enterurus aequoreus")) {
                esp = EspeceHippocampe.ENTERURUS_AEQUOREUS;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return esp;
    }

    /**
     * It takes a ResultSet as a parameter and returns a Peche enum
     * 
     * @param rs ResultSet
     * @return A Peche object.
     */
    public Peche ChoixPeche(ResultSet rs) {
        Peche pec = null;
        try {
            if ((rs.getString("typePeche") != null && rs.getString("typePeche").equals("casierCrevettes"))) {
                pec = Peche.CASIER_CREVETTES;
            } else if ((rs.getString("typePeche") != null && rs.getString("typePeche").equals("casierMorgates"))) {
                pec = Peche.CASIER_MORGATES;
            } else if ((rs.getString("typePeche") != null && rs.getString("typePeche").equals("petitFilet"))) {
                pec = Peche.PETIT_FILET;
            } else if ((rs.getString("typePeche") != null && rs.getString("typePeche").equals("verveuxAnguilles"))) {
                pec = Peche.VERVEUX_ANGUILLES;
            } else if ((rs.getString("typePeche") != null && (rs.getString("typePeche").equals("NonRenseigne")))) {
                pec = Peche.NON_RENSEIGNE;
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        return pec;
    }
}
