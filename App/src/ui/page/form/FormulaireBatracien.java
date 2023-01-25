package ui.page.form;

import java.awt.*;
import javax.swing.*;
import java.sql.ResultSet;
import ui.page.element.Formulaire;

/**
 * It's a JPanel that contains a JComboBox, 4 JTextFields and a JLabel
 */
public class FormulaireBatracien extends JPanel implements Formulaire {
    /**
     * It's a constant array of String.
     */
    private final String[] BATRACIENS = { "CALAMITE", "PELODYTE" };

    /**
     * It's a private variable of type JTextField.
     */
    private JTextField nbAdultes;
    /**
     * It's a private variable of type JTextField.
     */
    private JTextField nbAmplexus;
    /**
     * It's a private variable of type JTextField.
     */
    private JTextField nbTetard;
    /**
     * It's a private variable of type JTextField.
     */
    private JTextField nbPonte;

    /**
     * It's a private variable of type JComboBox.
     */
    private JComboBox<String> espece;

    /** It's a private variable of type JLabel. */
    private JLabel erreur;
    /**
     * It's a reference to the view of the JPanel on which this object is situated.
     */
    private AjouteEspece view;

    /**
     * Constructor of the object FormulaireBatracien
     * 
     * @param view view of the JPanel on which this object is situated
     */
    public FormulaireBatracien(AjouteEspece view) {
        this.view = view;
        this.setLayout(new GridLayout(9, 2));

        JLabel nomEspece = new JLabel("");
        JLabel especeL = new JLabel("Espece");
        JLabel nbAdultesL = new JLabel("Nombre d'adultes");
        JLabel nbAmplexusL = new JLabel("Nombre d'amplexus");
        JLabel nbTetardL = new JLabel("Nombre de tetards");
        JLabel nbPonteL = new JLabel("Nombre des pontes");
        erreur = new JLabel();

        nbAdultes = new JTextField();
        nbAmplexus = new JTextField();
        nbTetard = new JTextField();
        nbPonte = new JTextField();

        espece = new JComboBox<String>(BATRACIENS);

        this.add(nomEspece);
        this.add(erreur);
        this.add(especeL);
        this.add(espece);
        this.add(nbAdultesL);
        this.add(nbAdultes);
        this.add(nbAmplexusL);
        this.add(nbAmplexus);
        this.add(nbTetardL);
        this.add(nbTetard);
        this.add(nbPonteL);
        this.add(nbPonte);
    }

    /**
     * It takes the values of the text fields and sends them to the database
     */
    public void valide() {
        try {
            int adulte;
            int amplexus;
            int tetard;
            int ponte;
            int id;
            ResultSet rs = this.view.getLauncher().getBdd().query("SELECT max(idObs) FROM Observation;");

            rs.next();
            id = 1 + rs.getInt("max(idObs)");
            rs.close();
            if (nbAdultes.getText() != null || nbAmplexus.getText() != null || nbTetard.getText() != null
                    || nbPonte.getText() != null) {
                adulte = Integer.valueOf(nbAdultes.getText());
                amplexus = Integer.valueOf(nbAmplexus.getText());
                tetard = Integer.valueOf(nbTetard.getText());
                ponte = Integer.valueOf(nbPonte.getText());
                view.envoie(id);
                view.envoieBatracien(id, adulte, amplexus, tetard, ponte, (String) espece.getSelectedItem());
            } else {
                throw new Exception("Nombre d'adulte invalide");
            }
        } catch (Exception e) {
            erreur.setText(e.getMessage());
        }
    }
}