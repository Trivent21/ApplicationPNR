package modele.donnee;

import java.util.ArrayList;
/** 
 * @author Anthony Hascoet
 * Observasion de Batracien 
 * 
 */
public class ObsBatracien extends Observation {
	/** nombre de batracien adulte */
	private int nombreAdultes;
	/** Nombre d'amplexus */
	private int nombreAmplexus;
	/** nombre de Tetard */
	private int nombreTetard;
	/** nombre de Ponte */
	private int nombrePonte;
	/** Espece du batracien */
	private EspeceBatracien espece;

	/**
	 * Constructeur de l'object ObsBatracien
	 * @param id id de l'observation
	 * @param date date de l'observation
	 * @param heure heure de l'observation
	 * @param lieu lieu d'observation
	 * @param observateurs Créateur de l'observation
	 * @param resObs tableau de 4 int 
	 * @param lEspece L'espece du Batracien
	 */
	public ObsBatracien(int id, java.sql.Date date, java.sql.Time heure, Lieu lieu, ArrayList<Observateur> observateurs, int[] resObs, EspeceBatracien lEspece) {
		super(id, date, heure, lieu, observateurs);
		if(resObs != null && resObs.length == 4 && lEspece != null){
			this.nombreAdultes = resObs[0];
			this.nombreAmplexus = resObs[1];
			this.nombreTetard = resObs[2];
			this.nombrePonte = resObs[3];
			espece = lEspece;
		}else{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Renvoie le nombre de batracien adulte 
	 * @return le nombre de batracien adulte 
	 */
	public int getNombreAdultes() {
		return this.nombreAdultes;
	}

	/**
	 * Definie le nombre de batracien adulte 
	 * @param nombreAdultes le nombre de batracien adulte 
	 */
	public void setNombreAdultes(int nombreAdultes) {
		this.nombreAdultes = nombreAdultes;
	}

	/**
	 * Renvoie le nombre d'amplexus
	 * @return le nombre d'amplexus
	 */
	public int getNombreAmplexus() {
		return this.nombreAmplexus;
	}

	/**
	 * Definie le nombre d'amplexus
	 * @param nombreAmplexus le nombre d'amplexus
	 */
	public void setNombreAmplexus(int nombreAmplexus) {
		this.nombreAmplexus = nombreAmplexus;
	}

	/**
	 * Renvoie le nombre de Tetard
	 * @return le nombre de Tetard
	 */
	public int getNombreTetard() {
		return this.nombreTetard;
	}

	/**
	 * Definie le nombre de Tetard
	 * @param nombreTetard le nombre de Tetard
	 */
	public void setNombreTetard(int nombreTetard) {
		this.nombreTetard = nombreTetard;
	}

	/**
	 * Renvoie le nombre de ponte
	 * @return le nombre de ponte
	 */
	public int getNombrePonte() {
		return this.nombrePonte;
	}

	/**
	 * Definie le nombre de ponte
	 * @param nombrePonte le nombre de ponte
	 */
	public void setNombrePonte(int nombrePonte) {
		this.nombrePonte = nombrePonte;
	}

	/**
	 * Renvoie l'espece du batracien
	 * @return l'espece du batracien
	 */
	public EspeceBatracien getEspece(){
		return this.espece;
	}

	/**
	 * Definie l'espece du batracien
	 * @param lEspece l'espece du batracien
	 */
	public void setEspece(EspeceBatracien lEspece){
		this.espece = lEspece;
	}

	/**
	 * Renvoie l'espece batracien
	 * @return l'espece du batracien
	 */
	public EspeceObservee especeObs(){
		return EspeceObservee.BATRACIEN;
	}
}