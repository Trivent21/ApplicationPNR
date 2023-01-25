package ui.page.form;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.page.element.Formulaire;

/**
 * It's a JPanel that contains a JComboBox, a JTextField, two JRadioButtons and a JLabel
 */
public class FormulaireChouette extends JPanel implements Formulaire {
    /** It's a constant array of String objects.*/ 
    private final String[] TYPEOBS = {"SONORE", "VISUELLE", "SONORE_VISUELLE"};

    /**  It's a JComboBox that contains String objects. */
    JComboBox<String> typeObs;
    /** It's a JTextField that contains a String object. */
    JTextField id;
    /** It's a JRadioButton that contains a boolean object.*/ 
    JRadioButton protocoleY;
    /** It's a JRadioButton that contains a boolean object. */ 
    JRadioButton protocoleN;
    /** It's a JLabel that contains a String object.*/ 
    JLabel erreur;

    /** It's a reference to the view of the JPanel on which this object is situated.*/
    AjouteEspece view;
    /**
     * Constructor of the object FormulaireChouette
     * @param view view of the JPanel on which this object is situated 
     */
    public FormulaireChouette(AjouteEspece view){
        this.view = view;
        this.setLayout(new GridLayout(9,2));

        JLabel nomEspece = new JLabel("Ajout de chouettes");
        JLabel typeObsL = new JLabel("Type d'observation");
        JLabel idL = new JLabel("Id  de chouette");
        JLabel protoL = new JLabel("Protocole");
        erreur = new JLabel();

        typeObs = new JComboBox<String>(TYPEOBS);
        id = new JTextField();
        id.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
             }
        });

        ButtonGroup b1 = new ButtonGroup();
        b1.add(protocoleY);
        b1.add(protocoleN);

        JPanel buttonP = new JPanel();
        buttonP.setLayout(new GridLayout(1,2));

        this.add(nomEspece);
        this.add(typeObsL);
        this.add(typeObs);
        this.add(idL);
        this.add(id);
        this.add(protoL);
        this.add(buttonP);
        buttonP.add(protocoleY);
        buttonP.add(protocoleN);
    }

    /**
     * It takes the id of the observation and the id of the owl, and inserts them into the database
     */
    public void valide() {
        String iD;
        int id1 = 0;
        int idChouette = 0;
        boolean prot = false;
        try {
            ResultSet rs = this.view.getLauncher().getBdd().query("SELECT max(idObs) FROM Observation;");

            rs.next();
            id1 = 1 + rs.getInt("max(idObs)");
            rs.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        try{
            iD = id.getText();
            if(protocoleY.isSelected() ||!protocoleN.isSelected()){
                prot = true;
            }
            
            if(id.getText() != null){
                idChouette = Integer.parseInt(id.getText());
            }
            if(iD != null){
                view.envoie(id1);
                view.envoieChouette(id1,(String) typeObs.getSelectedItem(),idChouette,prot);
            }else{
                throw new Exception("Id invalide");
            }
        } catch (Exception e){
            erreur.setText("ID invalide");
        }
    }
}