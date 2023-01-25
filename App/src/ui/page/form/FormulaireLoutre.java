package ui.page.form;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import ui.page.element.Formulaire;

/**
 * It's a JPanel that contains a JComboBox and a JLabel
 */
public class FormulaireLoutre extends JPanel implements Formulaire {
    /**
     * Declaring a constant array of strings.
     */
    private final String[] indiceLoutre = { "POSITIF", "NEGATIF", "NON_PROSPECTION" };
    /**
     * A reference to the parent view.
     */
    private AjouteEspece view;
    /**
     * Declaring a variable of type JComboBox.
     */
    private JComboBox<String> indice;

    /**
     * Constructor of the object FormulaireGCI
     * 
     * @param view view of the JPanel on which this object is situated
     */
    public FormulaireLoutre(AjouteEspece view) {
        this.view = view;
        this.setLayout(new GridLayout(2, 2));
        JLabel nomEspece = new JLabel("");
        JLabel indiceL = new JLabel("Indice");
        JLabel blankLabel = new JLabel();
        indice = new JComboBox<String>(indiceLoutre);
        this.add(nomEspece);
        this.add(blankLabel);
        this.add(indiceL);
        this.add(indice);
    }

    /**
     * It takes the id of the observation and the id of the owl, and inserts them
     * into the database
     */
    public void valide() {
        int id = 0;

        try {
            ResultSet rs = this.view.getLauncher().getBdd().query("SELECT max(idObs) FROM Observation;");

            rs.next();
            id = 1 + rs.getInt("max(idObs)");
            rs.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }

        view.envoie(id);
        view.envoieLoutre(id, (String) indice.getSelectedItem());
    }
}