package modele.donnee;

import java.util.ArrayList;
/**
 * @author Clement MONFORT
 * Class ObsLoutre
 */
public class ObsLoutre extends Observation {
   /** Visibilité privé */
    private IndiceLoutre indice;

    /**
     * Constructeur de l'objet ObsLoutre:
     * 
     * @param id l'identifiant de l'observation d'une Loutre.
     * @param date date de l'observation.
     * @param heure l'heure de l'observation.
     * @param lieu lieu de l'observation.
     * @param observateurs les personnes qui ont fait l'observation.
     * @param indice le type d'observation.
     */
    public ObsLoutre(int id, java.sql.Date date, java.sql.Time heure, Lieu lieu,ArrayList<Observateur> observateurs, IndiceLoutre indice) {
        super(id, date, heure, lieu, observateurs);
        setIndice(indice);
    }

    /**
     * Rentourne l'identifiant de l'objet ObsLoutre.
     * 
     * @return l'identifiant de l'ObsLoutre.
     */
    public IndiceLoutre getIndice() {
        return (this.indice);
    }

    /**
     * Change l'attribut indice de l'objet ObsLoutre.
     * 
     * @param indice le nouvelle indice de l'ObsLoutre.
     */
    public void setIndice(IndiceLoutre indice) {
        this.indice = indice;
    }

    /**
     * Donne l'espece de la sous-classe
     * @return EspeceObservee.LOUTRE
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.LOUTRE;
    }
}