package modele.donnee;


import java.util.ArrayList;

/**
 * @author Anthony Hascoet 
 */
public class ObsGCI extends Observation {
	/**Visibiliter privé */
	private ContenuNid natureObs;
	/**Visibiliter privé */
	private int nombre;

	/**
	 * Constructeur de l'objet ObsCGI
	 * @param id id d'observation
	 * @param date date d'observation
	 * @param heure heure d'observation
	 * @param lieu lieu d'observation
	 * @param observateurs Liste des observateurs
	 * @param nature de l'observation
	 * @param leNombre nombre d'oeuf
	 */
	public ObsGCI(int id, java.sql.Date date, java.sql.Time heure, Lieu lieu, ArrayList<Observateur> observateurs, ContenuNid nature, int leNombre) {
		super(id, date, heure, lieu, observateurs);
		if (nature != null && leNombre >= 0 ) {
			this.natureObs = nature;
			this.nombre = leNombre;
		} else  {
			throw new IllegalArgumentException("\nBad setting");
		}
	}
	/**
	 * Donne la nature de l'observation
	 * 
	 * @return natureObs - La Nature actuelle
	 */
	public ContenuNid getNatureObs() {
		return this.natureObs;
	}
	/**
	 * Change la nature de l'observation
	 * 
	 * @param natureObs - Le nouvelle Nature
	 */
	public void setNatureObs(ContenuNid natureObs) {
		this.natureObs = natureObs;
	}
	/**
	 * Donne le nombre d'oeuf de l'objet ObsGCI
	 * 
	 * @return nombre d'oeuf
	 */
	public int getNombre() {
		return nombre;
	}
	/** 
	 * Change le nombre d'euf de l'objet ObsGCI
	 * 
	 * @param nombre - Nouveau nombre d'oeuf
	 */
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	/**
	 * Donne l'espece de la sous-Classe
	 * 
	 * @return GCI - Espece de l'Observee
	 */
	public EspeceObservee especeObs() {
		return EspeceObservee.GCI;
	}

}