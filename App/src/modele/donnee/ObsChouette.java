package modele.donnee;

import java.util.*;

/**
 * @author Clement MONFORT
 * Object pour stocker les observations sur les Chouettes.
 */
public class ObsChouette extends Observation {
	/**
	 * visibilité prive
	 */
    private TypeObservation typeObs;
	/**
	 * visibilité prive
	 */
    private String idChouette;

    /**
     * Constructeur de l'objet ObsChouette.
     * 
     * @param id           (String), l'identifiant de la chouette observée.
     * @param date         (sql.Date), la date de l'observation.
     * @param heure        (sql.Time), l'heure de l'observation.
     * @param lieu         (Lieu), le lieu de l'observation.
     * @param observateurs (observateur), les personnes qui ont fait l'observation.
     * @param type         (enum), comment la chouette à été observé.
     */
    public ObsChouette(int id, java.sql.Date date, java.sql.Time heure, Lieu lieu, ArrayList<Observateur> observateurs,
            TypeObservation type) {
        super(id, date, heure, lieu, observateurs);
		if (type != null) {
			setTypeObs(type);
		} else {
			throw new IllegalArgumentException("Bad setting - ObsChouette");
		}
    }

    /**
     * Renvoie le type d'observation de l'objet ObsChouette.
     * 
     * @return (enum:TypeObservation), le type d'observation.
     */
    public TypeObservation getTypeObs() {
        return (this.typeObs);
    }

    /**
     * Change le type d'observation de l'objet ObsChouette.
     * 
     * @param typeObs (enum:TypeObservation), le nouveau type d'observation.
     */
    public void setTypeObs(TypeObservation typeObs) {
        this.typeObs = typeObs;
    }

	/**
     * Donne l'espece de la sous-classe
     * @return EspeceObservee.CHOUETTE
     */
	public EspeceObservee especeObs() {
		
		return EspeceObservee.CHOUETTE;
	}

}