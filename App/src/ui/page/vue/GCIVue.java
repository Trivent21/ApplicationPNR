package ui.page.vue;

import java.sql.*;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;

import modele.donnee.*;
import ui.Launcher;
import ui.page.element.Page;

/**
 * I'm trying to create a table with data from a database.
 */
public class GCIVue extends Page {
    /**
     * A private variable of the class BatracienVue.
     */
    private JTable tab;

    /**
     * Creating a table with the data from the database.
     * 
     * @param lc the launcher.
     */
    public GCIVue(Launcher lc) {
        super(lc, "Chouette");
        int obsGCI = 0;
        ContenuNid leConNid;
        ArrayList<Observateur> listObs = new ArrayList<Observateur>();
        ArrayList<NidGCI> listGCI = new ArrayList<NidGCI>();
        ArrayList<Observation> grandeListe = new ArrayList<Observation>();

        try {
            ResultSet rs = this.getLauncher().getBdd().query(
                    "SELECT idObs,dateObs,heureObs,lieu_Lambert_X,lieu_Lambert_Y,idObservateur,nom,prenom,nature,nombre,idNid,nomPlage,nbEnvol FROM observation JOIN obs_gci ON idObs = obsG JOIN nid_gci ON idNid = leNid  JOIN aobserve ON idObs = lobservation JOIN observateur ON idObservateur = lobservateur;");
            rs.next();
            leConNid = ChoixContenuNid(rs);
            NidGCI newNidGCI = new NidGCI(rs.getInt("idNid"), rs.getString("nomPlage"));
            Observateur resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                    rs.getString("prenom"));
            listObs.add(resObservateur);
            Lieu resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
            ObsGCI LObs = new ObsGCI(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"), resLieu,
                    listObs, leConNid, rs.getInt("nombre"));
            newNidGCI.ajouteUneObs(LObs);
            listGCI.add(newNidGCI);
            grandeListe.add(LObs);
            while (rs.next()) {
                obsGCI = rs.getInt("idObs");
                rs.previous();
                if (obsGCI == rs.getInt("idObs")) { // Nouvelle observation
                    rs.next();
                    resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                            rs.getString("prenom"));
                    listObs.add(resObservateur);
                    resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
                    LObs = new ObsGCI(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"), resLieu,
                            listObs, leConNid, rs.getInt("nombre"));
                    newNidGCI.ajouteUneObs(LObs);
                } else { // Nouvelle chouette
                    rs.next();
                    leConNid = ChoixContenuNid(rs);
                    newNidGCI = new NidGCI(rs.getInt("idNid"), rs.getString("nomPlage"));
                    resObservateur = new Observateur(rs.getInt("idObservateur"), rs.getString("nom"),
                            rs.getString("prenom"));
                    listObs = new ArrayList<Observateur>();
                    listObs.add(resObservateur);
                    resLieu = new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y"));
                    LObs = new ObsGCI(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"), resLieu,
                            listObs, leConNid, rs.getInt("nombre"));
                    newNidGCI.ajouteUneObs(LObs);
                    listGCI.add(newNidGCI);
                }
                grandeListe.add(LObs);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        // entête pour le tableau
        String[] entetes = { "IdObs", "Coord_X", "Coord_Y", "Liste observateur", "nature", "NombreOeuf", "IdNid",
                "nbEnvol", "NomPlage" };
        // Convertion de l'Arrayliste en tableau
        Object[][] donnee = new Object[grandeListe.size()][entetes.length];
        int i = 0;
        String lesObservateurs = "";
        for (NidGCI cl : listGCI) {
            for (Observation cl1 : cl.getLesObservation()) {
                for (Observateur cl2 : cl1.getLesObservateurs()) {
                    int last = cl1.getLesObservateurs().size();
                    if (last != cl1.getLesObservateurs().size()) {
                        lesObservateurs = lesObservateurs + cl2.getNom() + " " + cl2.getPrenom() + ", ";
                    } else {
                        lesObservateurs = lesObservateurs + cl2.getNom() + " " + cl2.getPrenom();
                    }
                }
                Object[] table = { cl1.getIdObs(), cl1.getLieuObs().getXCoord(), cl1.getLieuObs().getYCoord(),
                        lesObservateurs, ((ObsGCI) cl1).getNatureObs(), ((ObsGCI) cl1).getNombre(), cl.getIdNid(),
                        cl.getNbEnvol(), cl.getNomPlage() };
                donnee[i] = table;
                i++;
                lesObservateurs = "";
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
     * It takes a ResultSet as a parameter and returns a ContenuNid enum
     * 
     * @param rs ResultSet
     * @return A ContenuNid object.
     */
    public ContenuNid ChoixContenuNid(ResultSet rs) {
        ContenuNid nid = null;
        try {
            if (rs.getString("nature").equals("oeuf")) {
                nid = ContenuNid.OEUF;
            } else if (rs.getString("nature").equals("poussin")) {
                nid = ContenuNid.POUSSIN;
            } else {
                nid = ContenuNid.NID_SEUL;
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        return nid;
    }
}