package ui.page.form;

import ui.page.element.Formulaire;

import java.awt.event.*;

/**
 * It's a listener for the button "Ajouter" in the view AjouteEspece
 */
public class AjouteEspeceListener implements ActionListener {
    /** It's a variable that contains the view of the panel of wich the element to react is situated */ 
    AjouteEspece view;
    /** It's a variable that contains the form of the information of the animal */ 
    Formulaire infoEspece;

    /**
     * Constructor of the object AjouteEspeceListener
     * @param view view of the panel of wich the element to react is situated
     * @param infoEspece form of the information of the animal
     */
    public AjouteEspeceListener(AjouteEspece view, Formulaire infoEspece) {
        this.view = view;
        this.infoEspece = infoEspece;
    }

    /**
     * It's a function that is called when a button is pressed
     * 
     * @param e the event that triggered the action
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("Coucou");
        infoEspece.valide();
    }
}