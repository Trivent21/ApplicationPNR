package ui.page;

import ui.Launcher;
import ui.page.element.*;
import ui.page.form.AjouteEspece;
import ui.page.vue.BatracienVue;
import ui.page.vue.ChouetteVue;
import ui.page.vue.GCIVue;
import ui.page.vue.HippocampVue;
import ui.page.vue.LoutreVue;

import java.awt.*;
import java.awt.event.*;

/**
 * It creates a new JPanel, sets its layout to GridBagLayout, and adds 5 JLabels
 * to it
 */
public class Selection extends Page {
    /**
     * It's a reference to the Carte object that is passed to the constructor.
     */
    private Carte loutre;
    /**
     * It's a reference to the Carte object that is passed to the constructor.
     */
    private Carte hippocampe;
    /**
     * It's a reference to the Carte object that is passed to the constructor.
     */
    private Carte chouette;
    /**
     * It's a reference to the Carte object that is passed to the constructor.
     */
    private Carte batracien;
    /**
     * It's a reference to the Carte object that is passed to the constructor.
     */
    private Carte gci;

    /**
     * A constructor. It is called when you create a new object of the class.
     * 
     * @param lc window.
     */
    public Selection(Launcher lc) {
        super(lc, "Selection");
        initComponents();
    }

    /**
     * It creates a new JPanel, sets its layout to GridBagLayout, and adds 5 JLabels
     * to it
     */
    public void initComponents() {
        this.setLayout(new GridBagLayout());
        this.add(this.loutre = new Carte(this, "..\\data\\static\\icon\\loutre.jpg"));
        this.loutre.setListener(new LoutreListener(this.loutre));
        this.add(hippocampe = new Carte(this, "..\\data\\static\\icon\\Hippocamp.jpg"));
        this.hippocampe.setListener(new HippocampListener(this.hippocampe));
        this.add(chouette = new Carte(this, "..\\data\\static\\icon\\Chouette.png"));
        this.chouette.setListener(new ChouetteListener(this.chouette));
        this.add(batracien = new Carte(this, "..\\data\\static\\icon\\batracien.png"));
        this.batracien.setListener(new BatracienListener(this.batracien));
        this.add(gci = new Carte(this, "..\\data\\static\\icon\\GCI.png"));
        this.gci.setListener(new GCIListener(this.gci));

    }

    /**
     * It's a listener for the buttons in the Carte class
     */
    private final class LoutreListener implements ActionListener {
        /**
         * It's a reference to the Carte object that is passed to the constructor.
         */
        Carte view;

        /**
         * It's a constructor. It is called when you create a new object of the class.
         * 
         * @param view the view
         */
        public LoutreListener(Carte view) {
            this.view = view;
        }

        /**
         * I want to change the page of the launcher, but I can't because the launcher
         * is not accessible from the controller
         * 
         * @param e the event that triggered the action
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.view.getAjoute()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher().setPage(
                        new AjouteEspece(this.view.getView().getLauncher(), "Loutre"));
            } else if (e.getSource() == this.view.getVue()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher()
                        .setPage(new LoutreVue(this.view.getView().getLauncher()));
            }
        }
    }

    /**
     * I want to change the page of the launcher, but I can't because the launcher
     * is not accessible from the controller
     */
    private final class ChouetteListener implements ActionListener {
        /**
         * It's a reference to the Carte object that is passed to the constructor.
         */
        Carte view;

        /**
         * It's a constructor. It is called when you create a new object of the class.
         * 
         * @param view the view
         */
        public ChouetteListener(Carte view) {
            this.view = view;
        }

        /**
         * I want to change the page of the launcher, but I can't because the launcher
         * is not accessible from the controller
         * 
         * @param e the event that triggered the action
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.view.getAjoute()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher().setPage(
                        new AjouteEspece(this.view.getView().getLauncher(), "Chouette"));
            } else if (e.getSource() == this.view.getVue()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher()
                        .setPage(new ChouetteVue(this.view.getView().getLauncher()));
            }
        }
    }

    /**
     * I want to change the page of the launcher, but I can't because the launcher
     * is not accessible from the controller
     */
    private final class BatracienListener implements ActionListener {
        /**
         * It's a reference to the Carte object that is passed to the constructor.
         */
        Carte view;

        /**
         * It's a constructor. It is called when you create a new object of the class.
         * 
         * @param view the view
         */
        public BatracienListener(Carte view) {
            this.view = view;
        }

        /**
         * I want to change the page of the launcher, but I can't because the launcher
         * is not accessible from the controller
         * 
         * @param e the event that triggered the action
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.view.getAjoute()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher().setPage(
                        new AjouteEspece(this.view.getView().getLauncher(), "Batracien"));
            } else if (e.getSource() == this.view.getVue()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher()
                        .setPage(new BatracienVue(this.view.getView().getLauncher()));
            }
        }
    }

    /**
     * I want to change the page of the launcher, but I can't because the launcher
     * is not accessible
     * from the controller
     */
    private final class HippocampListener implements ActionListener {
        /**
         * It's a reference to the Carte object that is passed to the constructor.
         */
        Carte view;

        /**
         * It's a constructor. It is called when you create a new object of the class.
         * 
         * @param view the view
         */
        public HippocampListener(Carte view) {
            this.view = view;
        }

        /**
         * I want to change the page of the launcher, but I can't because the launcher
         * is not accessible from the controller
         * 
         * @param e the event that triggered the action
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.view.getAjoute()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher().setPage(
                        new AjouteEspece(this.view.getView().getLauncher(), "Hippocampe"));
            } else if (e.getSource() == this.view.getVue()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher()
                        .setPage(new HippocampVue(this.view.getView().getLauncher()));
            }
        }
    }

    /**
     * I want to change the page of the launcher, but I can't because the launcher
     * is not accessible
     * from the controller
     */
    private final class GCIListener implements ActionListener {
        /**
         * It's a reference to the Carte object that is passed to the constructor.
         */
        Carte view;

        /**
         * It's a constructor. It is called when you create a new object of the class.
         * 
         * @param view the view
         */
        public GCIListener(Carte view) {
            this.view = view;
        }

        /**
         * I want to change the page of the launcher, but I can't because the launcher
         * is not accessible from the controller
         * 
         * @param e the event that triggered the action
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.view.getAjoute()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher().setPage(
                        new AjouteEspece(this.view.getView().getLauncher(), "GCI"));
            } else if (e.getSource() == this.view.getVue()) {
                this.view.getView().getLauncher().getPage().setVisible(false);
                this.view.getView().getLauncher()
                        .setPage(new GCIVue(this.view.getView().getLauncher()));
            }
        }
    }
}