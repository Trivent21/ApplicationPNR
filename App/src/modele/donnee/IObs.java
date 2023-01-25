package modele.donnee;

import java.util.ArrayList;
/**
 * @author Anthony Hascoet - Mateo FLEJOU
 * Interface - implement dans Chouette et NidGCI
 */
public interface IObs<T> {

	/**
	 * Méthode d'interface chouette et GCI
	 * @param obs - Observation
	 */
	void ajouteUneObs(T obs);

	/**
	 * Méthode d'interface chouette et GCI
	 * @param obs ArrayListe de Observation
	 */
	void ajoutePlsObs(ArrayList<T> obs);
	/**Méthode d'interface chouette et GCI*/
	void videObs();

	/**
	 * Méthode d'interface chouette et GCI
	 * @param idObs id d'une observation
	 * @return Boolean false ou true
	 */
	boolean retireObs(int idObs);
	/**
	 * Méthode d'interface chouette et GCI
	 * @return int - nombre Observation
	*/
	int nbObs();

}