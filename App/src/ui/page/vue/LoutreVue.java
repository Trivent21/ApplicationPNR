package ui.page.vue;

import modele.donnee.IndiceLoutre;
import modele.donnee.Lieu;
import modele.donnee.ObsLoutre;
import modele.donnee.Observateur;
import ui.page.element.Page;
import ui.Launcher;
// dep ui
import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.sql.*;

/**
 * It's a subclass of the Page class, and it's called LoutreVue
 */
public class LoutreVue extends Page {
    /**
     * A private variable that is used to store the table.
     */
    private JTable table;

    /**
     * It gets all the data from the database and puts it in an ArrayList
     * 
     * @return An ArrayList of ObsLoutre objects.
     */
    public ArrayList<ObsLoutre> getData() {
        ArrayList<ObsLoutre> data = new ArrayList<ObsLoutre>();
        IndiceLoutre ind = null;
        String buff = null;
        ResultSet rs = this.getLauncher().getBdd().query(
                "SELECT idObs, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, idObservateur, nom, prenom, indice FROM Observation JOIN Obs_Loutre ON idObs = obsL JOIN aObserve ON idObs = lobservation JOIN observateur ON idObservateur = lobservateur;");
        try {
            int check = 0;

            while (rs.next()) {
                ArrayList<Observateur> obs = new ArrayList<Observateur>();
                do {
                    check = rs.getInt("idObs");
                    obs.add(new Observateur(rs.getInt("idObservateur"), rs.getString("nom"), rs.getString("prenom")));
                } while (rs.next() && check == rs.getInt("idObs"));
                rs.previous();
                if ((buff = rs.getString("indice")).equals("positif")) {
                    ind = IndiceLoutre.POSITIF;
                } else if (buff.equals("negatif")) {
                    ind = IndiceLoutre.NEGATIF;
                } else {
                    ind = IndiceLoutre.NON_PROSPECTION;
                }
                data.add(new ObsLoutre(rs.getInt("idObs"), rs.getDate("dateObs"), rs.getTime("heureObs"),
                        new Lieu(rs.getDouble("lieu_Lambert_X"), rs.getDouble("lieu_Lambert_Y")), obs, ind));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        return (data);
    }

    /**
     * A constructor.
     * 
     * @param lc the Launcher object.
     */
    public LoutreVue(Launcher lc) {
        super(lc, "Loutre");
        ArrayList<ObsLoutre> data = getData();
        String[] entetes = { "id", "date", "heure", "x", "y", "observateurs", "indice" };
        Object[][] table = new Object[data.size()][entetes.length];
        String buffer = "";
        int i = 0;

        for (ObsLoutre cl : data) {
            buffer = "";
            for (Observateur obs : cl.getLesObservateurs()) {
                buffer += (obs.getNom() == null ? " " : obs.getNom()) + " " + obs.getPrenom() + ", ";
            }
            Object[] buff = { cl.getIdObs(), cl.getDateObs(), cl.getHeureObs(), cl.getLieuObs().getXCoord(),
                    cl.getLieuObs().getYCoord(), buffer, cl.getIndice().toString() };
            table[i] = buff;
            i++;
        }
        this.setLayout(new BorderLayout());
        this.table = new JTable(table, entetes);
        this.table.setAutoCreateRowSorter(true);
        this.setPreferredSize(new Dimension(1080, 1920));
        this.add(this.table.getTableHeader(), BorderLayout.NORTH);
        this.table.setPreferredScrollableViewportSize(this.table.getPreferredSize());
        this.table.setFillsViewportHeight(true);
        this.add(this.table, BorderLayout.CENTER);
        this.add(new JScrollPane(this.table), JScrollBar.VERTICAL);
        JScrollPane tableSP = new JScrollPane(this.table);
        tableSP.setPreferredSize(null);
        this.add(tableSP);
    }
}
