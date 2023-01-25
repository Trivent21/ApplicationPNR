package ui.page.form;

import javax.swing.*;

import ui.page.element.Formulaire;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to create a JPanel that will be used to add a Hippocampus
 * to the database
 */
public class FormulaireHippocampe extends JPanel implements Formulaire {
    /**
     * Declaring a variable of type JComboBox that can only hold Strings.
     */
    private JComboBox<String> listeEspece;
    /**
     * Declaring a variable of type JComboBox that can only hold Strings.
     */
    private JComboBox<String> listePeche;
    /**
     * Declaring a variable of type JRadioButton.
     */
    private JRadioButton male;
    /**
     * Declaring a variable of type JRadioButton.
     */
    private JRadioButton femelle;
    /**
     * Declaring a variable of type JRadioButton.
     */
    private JRadioButton gestY;
    /**
     * Declaring a variable of type JRadioButton.
     */
    private JRadioButton gestN;
    /**
     * A text field that will be used to enter the size of the animal.
     */
    private JTextField taille;

    /**
     * Declaring a constant array of strings.
     */
    private final String[] HIPPOCAMPES = { "SYNGNATHUS_ACUS", "HYPPOCAMPUS_GUTTULATUS", "HIPPOCAMPUS_HIPPOCAMPUS",
            "ENTERURUS_AEQUOREUS" };
    /**
     * Declaring a constant array of strings.
     */
    private final String[] PECHES = { "CASIER_CREVETTES", "CASIER_MORGATES", "PETIT_FILET", "VERVEUX_ANGUILLES",
            "NON_RENSEIGNE" };
    /**
     * A reference to the parent class.
     */
    private AjouteEspece view;
    /**
     * A label that will be used to display error messages.
     */
    private JLabel erreur;

    /**
     * Constructor of the object FormulaireGCI
     * 
     * @param view view of the JPanel on which this object is situated
     */
    public FormulaireHippocampe(AjouteEspece view) {
        this.view = view;
        taille = new JTextField();
        taille.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume(); // if it's not a number, ignore the event
                }
            }
        });

        JLabel nomEspece = new JLabel("");
        JLabel espece = new JLabel("Espece");
        JLabel sexe = new JLabel("Sexe");
        JLabel peche = new JLabel("Type de peche");
        JLabel gestant = new JLabel("Gestant");
        JLabel tailleL = new JLabel("Taille");
        erreur = new JLabel();

        JPanel radioB1 = new JPanel();
        radioB1.setLayout(new GridLayout(1, 2));

        JPanel radioB2 = new JPanel();
        radioB2.setLayout(new GridLayout(1, 2));

        this.listeEspece = new JComboBox<String>(HIPPOCAMPES);
        this.listePeche = new JComboBox<String>(PECHES);

        this.male = new JRadioButton("M");
        this.femelle = new JRadioButton("F", true);
        this.gestY = new JRadioButton("Oui");
        this.gestN = new JRadioButton("Non", true);

        ButtonGroup b1 = new ButtonGroup();
        b1.add(male);
        b1.add(femelle);

        ButtonGroup b2 = new ButtonGroup();
        b2.add(gestY);
        b2.add(gestN);

        this.setLayout(new GridLayout(9, 1));
        this.add(nomEspece);

        this.add(erreur);
        this.add(espece);
        this.add(listeEspece);
        this.add(sexe);
        this.add(radioB1);
        radioB1.add(male);
        radioB1.add(femelle);
        this.add(peche);
        this.add(listePeche);
        this.add(gestant);
        this.add(radioB2);
        radioB2.add(gestY);
        radioB2.add(gestN);
        this.add(tailleL);
        this.add(taille);
    }

    /**
     * It takes the id of the observation and the id of the owl, and inserts them
     * into the database
     */
    public void valide() {
        int id = 0;
        boolean sexeM = false;
        boolean gestant = false;
        double t;
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
        if (male.isSelected() || !femelle.isSelected()) {
            sexeM = true;
        }
        if (gestY.isSelected() || !gestN.isSelected()) {
            gestant = true;
        }
        try {
            if (taille.getText() != null) {
                t = Integer.parseInt(taille.getText());
                view.envoie(id);
                view.envoieHippocampe(id, (String) listeEspece.getSelectedItem(), (String) listePeche.getSelectedItem(),
                        sexeM, gestant, t);
            } else {
                throw new Exception("Taille invalide");
            }
        } catch (Exception e) {
            erreur.setText(e.getMessage());
        }
    }
}