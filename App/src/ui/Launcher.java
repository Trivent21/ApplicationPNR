package ui;

// dep custom ui element
import ui.page.element.MySQL;
import ui.page.element.Page;
import ui.page.*;
// dep ui
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;

/**
 * It's a JFrame that displays a JPanel (Page) in the center, and a JPanel
 * (navbar) on the top.
 */
public class Launcher extends JFrame {
    /**
     * It's declaring a variable that will be used to store the current page.
     */
    private MySQL bdd;
    /**
     * It's declaring a variable that will be used to store the current page.
     */
    private JPanel navbar;
    /**
     * It's a JPanel that is used to store the title of the page.
     */
    private JPanel titleb;
    /**
     * It's declaring a variable that will be used to store the current page.
     */
    private JLabel title;
    /**
     * It's declaring a variable that will be used to store the current page.
     */
    private JButton back;
    /**
     * It's declaring a variable that will be used to store the current page.
     */
    private Page page;

    /**
     * This function sets the bdd attribute of the class to a new MySQL object
     * 
     * @param user The username of the database
     * @param mdp  password
     */
    public void setBdd(String user, String mdp) {
        this.bdd = new MySQL(MySQL.url("localhost", 3306, "bd_pnr"), user, mdp);
    }

    /**
     * This function returns the MySQL object
     * 
     * @return The MySQL object.
     */
    public MySQL getBdd() {
        return (this.bdd);
    }

    /**
     * It adds a JPanel to the top of the window, and then adds a JLabel to that
     * JPanel
     * 
     * @param page The page to be displayed
     */
    public void setPage(Page page) {
        this.getContentPane().removeAll();
        this.setLayout(new BorderLayout());
        this.page = page;
        this.page.setEnabled(true);
        this.page.setVisible(true);
        this.navbar = new JPanel(new BorderLayout());
        // Wizardy stuff to rescale an icon on a button.
        this.back = new JButton("backward", new ImageIcon(new ImageIcon(
                "..\\data\\static\\icon\\back.png").getImage().getScaledInstance(
                        75, 75, java.awt.Image.SCALE_SMOOTH)));
        this.back.setPreferredSize(new Dimension(80, 80));
        this.back.addActionListener(new backListener(this));
        this.navbar.add(this.back, BorderLayout.WEST);
        this.title = new JLabel(this.page.getTitle());
        this.title.setFont(new Font("Calibri", Font.PLAIN, 50));
        this.title.setForeground(Color.WHITE);
        this.titleb = new JPanel(new GridBagLayout());
        this.titleb.setBackground(new ColorUIResource(0, 147, 110));
        this.titleb.add(this.title);
        this.navbar.add(this.titleb);

        this.add(this.navbar, BorderLayout.NORTH);
        this.add(this.page, BorderLayout.CENTER);
        this.doLayout();
    }

    /**
     * It's the constructor of the class Launcher.
     */
    public Launcher() {
        this.setIconImage(new ImageIcon("..\\data\\static\\icon\\pnr.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setPage(new Login(this));
    }

    /**
     * It creates a new instance of the Launcher class and sets it to visible.
     * 
     * @param args instruction in parameters.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Launcher().setVisible(true);
            }
        });
    }

    /**
     * This function returns the page object
     * 
     * @return The page object.
     */
    public Page getPage() {
        return (this.page);
    }

    /**
     * It's a class that implements the ActionListener interface and has a
     * constructor that takes a Launcher object as a parameter
     */
    private final class backListener implements ActionListener {
        /**
         * It's a constructor for the class backListener.
         */
        private Launcher lc;

        /**
         * It's a constructor for the class backListener.
         * 
         * @param lc the launcher
         */
        public backListener(Launcher lc) {
            this.lc = lc;
        }

        /**
         * If the button pressed is the back button, then the current page is set to
         * invisible and the page is set to the selection page, otherwise the program is
         * closed.
         * 
         * @param e the event that triggered the action
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("backward")) {
                if (!this.lc.page.getTitle().equals("Connexion")) {
                    this.lc.page.setVisible(false);
                    this.lc.setPage(new Selection(lc));
                } else {
                    this.lc.removeAll();
                    this.lc.dispose();
                }
            }
        }
    }
}
