package ui.page.form;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import ui.Launcher;
import ui.page.element.Formulaire;
import ui.page.element.Page;

/**
 * AjouteEspece is a class that extends Page and is used to add a new animal to
 * the database
 */
public class AjouteEspece extends Page {
    /**
     * A private variable that is used to store the type of animal.
     */
    private String animal;
    /**
     * form of the informations of the animal
     */
    private Formulaire infoEspece;
    /**
     * name of the observator
     */
    private JTextField nomObservateur;
    /**
     * coordinate X of where the observation has been made
     */
    private JTextField coordX;
    /**
     * coordinate Y of where the observation has been made
     */
    private JTextField coordY;
    /**
     * A private variable that is used to store the date of the observation.
     */
    private JTextField date;
    /**
     * A private variable that is used to store the time of the observation.
     */
    private JTextField heure;
    /**
     * A private variable that is used to store the description of the observation.
     */
    private JTextField description;

    /**
     * Creating a new window with a form to fill in.
     * 
     * @param lc     launcher of the application
     * @param animal type of animal
     */
    public AjouteEspece(Launcher lc, String animal) {
        super(lc, "Ajout " + animal);
        this.animal = animal;
        // GENERAL
        nomObservateur = new JTextField();
        coordX = new JTextField();
        coordY = new JTextField();
        date = new JTextField();
        heure = new JTextField();
        description = new JTextField();

        // Initialisation des JLabel
        JLabel observateur = new JLabel("Observateur");
        JLabel coord = new JLabel("Coordonnees");
        JLabel dateHeure = new JLabel("Date et heure d'observation");
        JLabel descriptionL = new JLabel("Description");
        JLabel coordXL = new JLabel("X:");
        JLabel coordYL = new JLabel("Y:");

        // Initialisation des JPanel
        this.setLayout(new BorderLayout());

        JPanel coordXP = new JPanel();
        coordXP.setLayout(new GridLayout(1, 2));

        JPanel coordYP = new JPanel();
        coordYP.setLayout(new GridLayout(1, 2));

        JPanel coordP = new JPanel();
        coordP.setLayout(new GridLayout(1, 2));

        JPanel dateHeureP = new JPanel();
        dateHeureP.setLayout(new GridLayout(1, 2));

        JPanel info = new JPanel();
        info.setLayout(new GridLayout(1, 2));

        JPanel general = new JPanel();
        general.setLayout(new GridLayout(9, 1));

        // Initialisation du JButton
        JButton valide = new JButton("Valide");

        // Ajout de JPanel
        this.add(valide, BorderLayout.SOUTH);
        this.add(info, BorderLayout.CENTER);
        info.add(general);

        general.add(observateur);
        general.add(nomObservateur);
        general.add(coord);
        general.add(coordP);
        coordP.add(coordXP);
        coordXP.add(coordXL);
        coordXP.add(coordX);
        coordP.add(coordYP);
        coordYP.add(coordYL);
        coordYP.add(coordY);
        general.add(dateHeure);
        general.add(dateHeureP);
        dateHeureP.add(date);
        dateHeureP.add(heure);
        general.add(descriptionL);
        general.add(description);
        // infoEspece
        if (animal.equals("Hippocampe")) {
            infoEspece = new FormulaireHippocampe(this);
        }
        if (animal.equals("Loutre")) {
            infoEspece = new FormulaireLoutre(this);
        }
        if (animal.equals("Chouette")) {
            infoEspece = new FormulaireChouette(this);
        }
        if (animal.equals("Batracien")) {
            infoEspece = new FormulaireBatracien(this);
        }
        if (animal.equals("GCI")) {
            infoEspece = new FormulaireGCI(this);
        }
        info.add((JPanel) infoEspece);
        AjouteEspeceListener listener = new AjouteEspeceListener(this, infoEspece);
        valide.addActionListener(listener);
    }

    /**
     * It takes an id as a parameter, and inserts a new observation in the database
     * 
     * @param id the id of the observation
     */
    public void envoie(int id) {
        try {
            String[] obs = nomObservateur.getText().split(", ");
            this.getLauncher().getBdd()
                    .insert("INSERT INTO Lieu VALUES (" + coordX.getText() + ", " + coordY.getText() + ");");
            this.getLauncher().getBdd()
                    .insert("INSERT INTO Observation VALUES (" + id + ", \'" + date.getText() + "\', \'"
                            + heure.getText() + "\', " + coordX.getText() + ", " + coordY.getText() + ");");
            int cpt;

            for (int i = 0; obs.length > i; i++) {
                String[] buff = obs[i].trim().split(" ");

                ResultSet rs = this.getLauncher().getBdd()
                        .query("SELECT * FROM Observateur WHERE nom IN(\""
                                + buff[1] + "\") AND prenom IN(\"" + buff[0] + "\");");
                rs.next();
                if ((cpt = 1 + rs.getInt("idObservateur")) > 0) {
                    this.getLauncher().getBdd().insert("INSERT INTO AObserve VALUES (" + cpt + ", " + id + ");");
                } else {
                    ResultSet tmp = this.getLauncher().getBdd().query("SELECT max(idObs) FROM Observateur;");
                    tmp.next();
                    cpt = 1 + tmp.getInt("max(idObservateur)");
                    tmp.close();
                    this.getLauncher().getBdd().insert("INSERT INTO Observateur VALUES ("
                            + cpt + ", " + buff[0] + ", " + buff[1] + ");");
                    this.getLauncher().getBdd().insert("INSERT INTO AObserve VALUES (" + cpt + ", " + id + ");");
                }
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }

    }

    /**
     * It inserts a row in a table
     * 
     * @param id     the id of the observation
     * @param indice String
     */
    public void envoieLoutre(int id, String indice) {
        this.getLauncher().getBdd()
                .insert("INSERT INTO Obs_Loutre VALUES (" + id + ", " + null + ", " + null + ", \'" + indice + "\');");
    }

    /**
     * It inserts a new row in the database
     * 
     * @param id        id of the observation
     * @param espece    species of the animal
     * @param typePeche type of fishing
     * @param sexeM     sex of the animal, true if male, false if female
     * @param gestant   true if it's gestan, false if not
     * @param taille    height of the animal
     */
    public void envoieHippocampe(int id, String espece, String typePeche, boolean sexeM, boolean gestant,
            double taille) {
        String sexe = "";
        if (sexeM) {
            sexe = "male";
        } else {
            sexe = "femelle";
        }
        this.getLauncher().getBdd()
                .insert("INSERT INTO Obs_Hippocampe VALUES (" + id + ", " + espece + ", " + sexe + ", " + null + ", "
                        + typePeche + ", " + taille + ", " + gestant + ");");
    }

    /**
     * It inserts a row in a table
     * 
     * @param id      the id of the observation
     * @param typeObs 1 for a new observation, 2 for a modification, 3 for a
     *                deletion
     * @param idCh    the id of the chouette
     * @param proto   boolean
     */
    public void envoieChouette(int id, String typeObs, int idCh, boolean proto) {
        int protocole = 0;
        if (proto) {
            protocole = 1;
        }
        this.getLauncher().getBdd()
                .insert("INSERT INTO Obs_Chouette VALUES (" + protocole + ", " + typeObs + ", " + idCh + ", " + id
                        + ");");
    }

    /**
     * It inserts a row in a table
     * 
     * @param id       the id of the observation
     * @param adulte   number of adult frogs
     * @param amplexus 0 or 1
     * @param tetard   tadpole
     * @param ponte    number of egg masses
     * @param espece   species of the animal
     */
    public void envoieBatracien(int id, int adulte, int amplexus, int tetard, int ponte, String espece) {
        this.getLauncher().getBdd()
                .insert("INSERT INTO Obs_Batracien VALUES (" + id + ", " + espece + ", " + adulte + ", " + amplexus
                        + ", " + ponte + ", " + tetard + ", " + null + ", " + null + ", " + null + ", " + null + ", "
                        + null + ", " + id + ", " + id + ");");
    }

    /**
     * This function inserts a new row in the table Obs_Batracien, with the values
     * of the parameters
     * passed to the function.
     * 
     * @param id           the id of the observation
     * @param contenu      the name of the species
     * @param nombre       number of frogs
     * @param PresentouNon is a boolean that is true if the user has checked the
     *                     checkbox
     * @param leNid        the id of the nest
     */
    public void envoieGCI(int id, String contenu, int nombre, boolean PresentouNon, int leNid) {
        int PrsenMaisNoObs = 0;
        if (PresentouNon) {
            PrsenMaisNoObs = 1;
        }
        this.getLauncher().getBdd()
                .insert("INSERT INTO Obs_Batracien VALUES (" + id + ", " + contenu + ", " + nombre + ", "
                        + PrsenMaisNoObs + ", " + leNid + ");");
    }
}