
package modele.donnee;

import java.util.*;

/**
 * @author Mateo FLEJOU
 *         Class principale - Observation
 */
public abstract class Observation {
    /** Visibiliter proteger */
    protected Lieu lieuObs;
    /** Visibiliter proteger */
    protected ArrayList<Observateur> lesObservateurs;
    /** Visibiliter proteger */
    protected int idObs;
    /** Visibiliter proteger */
    protected java.sql.Date dateObs;
    /** Visibiliter proteger */
    protected java.sql.Time heureObs;

    /**
     * Construteur de la class Observation
     * 
     * @param id           - l'id de l'observation, un entier supérieur ou égale à
     *                     zero.
     * @param date         - date de l'observation.
     * @param heure        - heure de l'observation.
     * @param lieu         - lieu de l'observation.
     * @param observateurs - Liste de observateur.
     */
    public Observation(int id, java.sql.Date date, java.sql.Time heure, Lieu lieu,
            ArrayList<Observateur> observateurs) {
        if (id >= 0 && lieu != null && observateurs != null) {
            this.dateObs = date;
            this.idObs = id;
            this.heureObs = heure;
            this.lieuObs = lieu;
            this.lesObservateurs = observateurs;
        } else {
            throw new IllegalArgumentException("\nBad setting");
        }
    }

    /**
     * Ajoute un observateur à la liste
     * 
     * @param o - Observateur
     */
    public void ajouteObservateur(Observateur o) {
        if (o != null) {
            this.lesObservateurs.add(o);
        }
    }

    /**
     * Retire un observateur de la liste grace a son ID
     * 
     * @param idObservateur - ID de l'observateur
     */
    public void retireObservateur(int idObservateur) {
        boolean res = false;
        Iterator<Observateur> it = lesObservateurs.iterator();
        Observateur tmp;
        while (!res && it.hasNext()) {
            tmp = (Observateur) it.next();
            if (tmp.getIdObservateur() == idObs) {
                it.remove();
                res = true;
            }
        }
    }

    /**
     * Donne l'attribut Les Observateurs de l'objet Observation.
     * 
     * @return lesObservateurs
     */
    public ArrayList<Observateur> getLesObservateurs() {
        return (lesObservateurs);
    }

    /**
     * Méthode abstract
     * 
     * @return EspeceObservee
     */
    public abstract EspeceObservee especeObs();

    /**
     * Donne l'attribut indice de l'objet de observateur.
     * 
     * @return idObs - Le ID
     */
    public int getIdObs() {
        return this.idObs;
    }

    /**
     * Change l'attribut idObs de l'objet ObsLoutre.
     * 
     * @param idObs - Nouveau ID
     */
    public void setIdObs(int idObs) {
        this.idObs = idObs;
    }

    /**
     * Donne l'attribut dateObs de l'objet Observation.
     * 
     * @return dateObs - La date
     */
    public java.sql.Date getDateObs() {
        return this.dateObs;
    }

    /**
     * Change l'attribut dateObs de l'objet Observation.
     * 
     * @param dateObs - Nouvelle dateObs
     */
    public void setDateObs(java.sql.Date dateObs) {
        this.dateObs = dateObs;
    }

    /**
     * Donne l'attribut heureObs de l'objet Observation.
     * 
     * @return heureObs - L'heure
     */
    public java.sql.Time getHeureObs() {
        return this.heureObs;
    }

    /**
     * Change l'attribut heureObs de l'objet Observation.
     * 
     * @param heureObs - Nouvelle heureObs
     */
    public void setHeureObs(java.sql.Time heureObs) {
        this.heureObs = heureObs;
    }

    /**
     * This function returns the value of the attribute lieuObs
     * 
     * @return The value of the variable lieuObs.
     */
    public Lieu getLieuObs() {
        return (this.lieuObs);
    }
}