package ui.page.element;

import ui.page.Selection;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;

/**
 * This class is used to create a card that contains an image and two buttons.
 */
public class Carte extends JPanel {
    /**
     * Declaring a variable of type JButton.
     */
    JButton ajoute;
    /**
     * Declaring a variable of type JButton.
     */
    JButton vue;
    /**
     * Declaring a listener.
     */
    ActionListener listener;
    /**
     * Declaring a Selection table.
     */
    Selection view;
    /**
     * Declaring a variable of type Jpanel.
     */
    JPanel carteV;
    /**
     * Declaring a variable of type Jpanel.
     */
    JPanel bouttonPanel;
    /**
     * Declaring a variable of type BufferedImage.
     */
    BufferedImage image;
    /**
     * Declaring a variable of type JLabel.
     */
    JLabel monImage;
    /**
     * Declaring a variable of type ImageIcon.
     */
    ImageIcon ico;

    /**
     * The constructor of the class Carte.
     * 
     * @param view      window
     * @param lienImage image path.
     */
    public Carte(Selection view, String lienImage) {
        this.view = view;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Panel
        carteV = new JPanel();
        bouttonPanel = new JPanel();
        bouttonPanel.setLayout(new FlowLayout());
        // Boutton Ajoute
        ajoute = new JButton();
        bouttonPanel.add(ajoute);
        ajoute.setText("Ajoute");
        // Boutton Vue
        vue = new JButton();
        bouttonPanel.add(vue);
        vue.setText("Vue");

        // Image
        monImage = new JLabel("Image non disponible");
        ico = new ImageIcon(new ImageIcon(lienImage).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        monImage = new JLabel(ico);
        // paramétre des panel
        carteV.setPreferredSize(new Dimension(250, 260));
        carteV.add(monImage);
        carteV.add(bouttonPanel);
        carteV.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        this.add(carteV);
    }

    /**
     * This function is used to set the listener of the button
     * 
     * @param listener ActionListener
     */
    public void setListener(ActionListener listener) {
        this.listener = listener;
        ajoute.addActionListener(this.listener);
        vue.addActionListener(this.listener);
    }

    /**
     * This function returns the view of the selection
     * 
     * @return The view.
     */
    public Selection getView() {
        return (this.view);
    }

    /**
     * This function returns the button ajoute
     * 
     * @return The button ajoute.
     */
    public JButton getAjoute() {
        return (ajoute);
    }

    /**
     * This function returns the JButton object that is the view of the model
     * 
     * @return The vue button.
     */
    public JButton getVue() {
        return (vue);
    }
}
