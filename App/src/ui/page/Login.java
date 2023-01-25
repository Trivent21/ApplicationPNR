package ui.page;

import ui.Launcher;
import ui.page.element.*;
// dep ui
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * It's a page that contains a login form
 */
public class Login extends Page {
    /**
     * It's a panel that contains the login form.
     */
    private JPanel bound;
    /**
     * It's a panel that contains the login form.
     */
    private JPanel login;
    /**
     * It's a text field for the id.
     */
    private JLabel idT;
    /**
     * It's a text field for the id.
     */
    private JTextField id;
    /**
     * It's a label for the password field.
     */
    private JLabel mdpT;
    /**
     * It's a password field.
     */
    private JPasswordField mdp;
    /**
     * It's a button that will be used to connect to the database.
     */
    private JPanel bouton;
    /**
     * It's a button that will be used to connect to the database.
     */
    private JButton connexion;

    /**
     * Create a login form with a JTextField and a JPasswordField
     */
    private void element() {
        // Set the layout
        this.setLayout(new BorderLayout());
        this.add(this.bound = new JPanel(new GridBagLayout()), BorderLayout.CENTER);
        this.bound.add(this.login = new JPanel());
        // login field
        this.login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
        this.login.add(this.idT = new JLabel("Identifiant:"));
        this.idT.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.login.add(this.id = new JTextField("root"));
        this.id.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.id.setPreferredSize(new Dimension(80, 40));
        this.id.setToolTipText("Identifiant");
        // password field
        this.login.add(this.mdpT = new JLabel("Mot de passe:"));
        this.mdpT.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.login.add(this.mdp = new JPasswordField("1Mp341@l1st3*"));
        this.mdp.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.mdp.setPreferredSize(new Dimension(80, 40));
        this.mdp.setToolTipText("mot de passe");
        this.mdp.setEchoChar('*');
        // button field
        this.login.add(this.bouton = new JPanel(new GridBagLayout()));
        this.bouton.add(this.connexion = new JButton("Connexion"));
        this.connexion.addActionListener(new LoginListener(this));
    }

    /**
     * It's the constructor of the class Login.
     * 
     * @param lc the launcher of the page.
     */
    public Login(Launcher lc) {
        super(lc, "Connexion");
        this.element();
    }

    /**
     * It's a listener for the login button
     */
    private final class LoginListener implements ActionListener {
        /**
         * It's a reference to the login page.
         */
        private Login login;

        /**
         * It's the constructor of the class LoginListener.
         * 
         * @param login the login page
         */
        public LoginListener(Login login) {
            this.login = login;
        }

        /**
         * If the button is pressed, the launcher will set the database with the id and
         * password, the login page will be set to invisible, and the launcher will set
         * the page to the selection page.
         * 
         * @param e the event that triggered the action
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Connexion")) {
                this.login.getLauncher().setBdd(id.getText(), String.valueOf(mdp.getPassword()));
                this.login.setVisible(false);
                this.login.getLauncher().setPage(new Selection(this.login.getLauncher()));
            }
        }
    }
}