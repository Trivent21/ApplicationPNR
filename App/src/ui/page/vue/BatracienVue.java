package ui.page.vue;

import modele.donnee.*;
import ui.Launcher;
import ui.page.element.Page;

import java.sql.*;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;

/**
 * Display a table with data from a database.
 */
public class BatracienVue extends Page {
    /**
     * A private variable of the class BatracienVue.
     */
    private JTable tab;

    /**
     * A constructor of the class BatracienVue.
     * 
     * @param lc the launcher.
     */
    public BatracienVue(Launcher lc) {
        super(lc, "Batracien");
        int obsB = 0;
        String espece;
        EspeceBatracien espBa;
        ArrayList<Observation> grandeListe = new ArrayList<Observation>();

        try {
            ResultSet rs = this.getLauncher().getBdd().query(
                    "SELECT idObs,dateObs,heureObs,lieu_Lambert_X,lieu_Lambert_Y,idObservateur,nom,prenom,espece,nombreAdultes,nombreAmplexus,nombrePonte,nombreTetard FROM observation JOIN obs_batracien ON idObs = obsB JOIN aobserve ON idObs = lobservation JOIN observateur ON idObservateur = lobservateur");
            rs.next();
            int[] resObs1 = { rs.getInt("nombreAdultes"), rs.getInt("nombreAmplexus"), rs.getInt("nombrePonte"),
                    rs.getInt("nombreTetard") };
            Lieu resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
            Observateur resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                    rs.getString("prenom"));
            ArrayList<Observateur> listObs = new ArrayList<Observateur>();
            listObs.add(resObservateur);
            if (rs.getString("espece").equals("calamite")) {
                espBa = EspeceBatracien.CALAMITE;
            } else {
                espBa = EspeceBatracien.PELODYTE;
            }
            Observation LObs = new ObsBatracien(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"),
                    resLieu, listObs, resObs1, espBa);
            grandeListe.add(LObs);
            while (rs.next()) {
                obsB = rs.getInt("idObs");
                espece = rs.getString("espece");
                rs.previous();
                if (rs.getInt("idObs") == obsB && espece.equals(rs.getString("espece"))) {
                    rs.next();
                    resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                            rs.getString("prenom"));
                    LObs.ajouteObservateur(resObservateur);
                } else if (rs.getInt("idObs") != obsB || !espece.equals(rs.getString("espece"))) {
                    rs.next();
                    int[] resObs2 = { rs.getInt("nombreAdultes"), rs.getInt("nombreAmplexus"), rs.getInt("nombrePonte"),
                            rs.getInt("nombreTetard") };
                    resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
                    resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                            rs.getString("prenom"));
                    listObs = new ArrayList<Observateur>();
                    listObs.add(resObservateur);
                    if (rs.getString("espece").equals("calamite")) {
                        espBa = EspeceBatracien.CALAMITE;
                    } else {
                        espBa = EspeceBatracien.PELODYTE;
                    }
                    LObs = new ObsBatracien(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"), resLieu,
                            listObs, resObs2, espBa);
                }
                grandeListe.add(LObs);
            }

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        String[] entetes = { "IdObs", "date", "heure", "Coord_X", "Coord_Y", "Liste observateur", "espece",
                "nombreAdultes", "nombreAmplexus", "nombrePonte", "nombreTetard" };
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
                    cl.getLieuObs().getYCoord(), lesObservateurs, ((ObsBatracien) cl).getEspece(),
                    ((ObsBatracien) cl).getNombreAdultes(), ((ObsBatracien) cl).getNombreAmplexus(),
                    ((ObsBatracien) cl).getNombreAmplexus(), ((ObsBatracien) cl).getNombrePonte(),
                    ((ObsBatracien) cl).getNombreTetard() };
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
}