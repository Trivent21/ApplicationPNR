package ui.page.form;

import java.awt.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.page.element.Formulaire;
import java.awt.event.*;

/**
 * It's a JPanel that contains a JComboBox, two JTextFields, two JRadioButtons
 * and a JLabel
 */
public class FormulaireGCI extends JPanel implements Formulaire {
    /**
     * It's a constant array of String.
     */
    private final String[] CONTENU = { "OEUF", "POUSSIN", "NID_SEUL" };

    /**
     * It's a JComboBox that contains String.
     */
    JComboBox<String> contenu;
    /**
     * It's a JTextField that contains a number.
     */
    JTextField nb;
    /**
     * It's a JTextField that contains a number.
     */
    JTextField idNid;
    /**
     * It's a JRadioButton that contains a String.
     */
    JRadioButton obsY;
    /**
     * It's a JRadioButton that contains a String.
     */
    JRadioButton obsN;
    /**
     * It's a reference to the parent class.
     */
    AjouteEspece view;

    /**
     * Constructor of the object FormulaireGCI
     * 
     * @param view view of the JPanel on which this object is situated
     */
    public FormulaireGCI(AjouteEspece view) {
        this.view = view;
        this.setLayout(new GridLayout(2, 2));

        JLabel nomEspece = new JLabel("Ajout de GCI");
        JLabel contenuL = new JLabel("Indice");
        JLabel idNidL = new JLabel("Id du nid");
        JLabel nbL = new JLabel("Nombre d'oeuf");
        JLabel obsL = new JLabel("Observe ?");
        JLabel blankLabel = new JLabel();

        JPanel button = new JPanel();

        contenu = new JComboBox<String>(CONTENU);
        idNid = new JTextField();
        idNid.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume(); // if it's not a number, ignore the event
                }
            }
        });
        nb = new JTextField();
        nb.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume(); // if it's not a number, ignore the event
                }
            }
        });
        obsY = new JRadioButton("Y");
        obsN = new JRadioButton("N");
        ButtonGroup b1 = new ButtonGroup();
        b1.add(obsY);
        b1.add(obsN);

        this.add(nomEspece);
        this.add(blankLabel);
        this.add(contenuL);
        this.add(contenu);
        this.add(idNidL);
        this.add(idNid);
        this.add(nbL);
        this.add(nb);
        this.add(obsL);
        this.add(button);
        button.add(obsY);
        button.add(obsN);
    }

    /**
     * It takes the id of the observation and the id of the owl, and inserts them
     * into the database
     */
    public void valide() {
        int id = 0;
        int leNb = 0;
        int leidNid = 0;
        boolean obsObse = false;
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
        if (obsY.isSelected() || !obsN.isSelected()) {
            obsObse = true;
        }
        if (nb.getText() != null) {
            leNb = Integer.parseInt(nb.getText());
        }
        if (idNid.getText() != null) {
            leidNid = Integer.parseInt(idNid.getText());
        }
        view.envoie(id);
        view.envoieGCI(id, (String) contenu.getSelectedItem(), leNb, obsObse, leidNid);
    }
}