package modele.donnee;

import java.util.ArrayList;

/**
 * @author Anthony Hascoet
 * 
 *         Observasion de ObsHippocampe
 */
public class ObsHippocampe extends Observation {
    /** Le type de peche utilisé */
    private Peche typePeche;
    /** L'espece de l'hippocampe */
    private EspeceHippocampe espece;
    /** le sexe de l'hippocampe */
    private Sexe sexe;
    /** La taille de l'hippocampe */
    private double taille;
    /** Si l'hippocampe est gestant ou non */
    private boolean estGestant;

    /**
     * Constructeur de l'object Hippocampe
     * 
     * @param id           Id de l'observation
     * @param date         Date de l'observation
     * @param heure        Heure d'observation
     * @param lieu         Lieude l'observation
     * @param observateurs Observateurs de cette observation
     * @param laTaille     La Taillede l'hippocampe
     * @param leTypePeche  Le type de peche utilisé
     * @param Espece       Espece de l'hippocampe
     * @param leSexe       Le sexe de l'hippocampe
     */
    public ObsHippocampe(int id, java.sql.Date date, java.sql.Time heure, Lieu lieu,
            ArrayList<Observateur> observateurs, double laTaille, Peche leTypePeche, EspeceHippocampe Espece,
            Sexe leSexe) {
        super(id, date, heure, lieu, observateurs);
        this.taille = laTaille;
        this.typePeche = leTypePeche;
        this.espece = Espece;
        this.sexe = leSexe;
    }

    /**
     * Renvoie la taille de l'hippocampe
     * 
     * @return la taille de l'hippocampe
     */
    public double getTaille() {
        return this.taille;
    }

    /**
     * Definie la taille de l'hippocampe
     * 
     * @param taille la taille de l'hippocampe
     */
    public void setTaille(double taille) {
        this.taille = taille;
    }

    /**
     * Renvoie si l'hippocampe est gestant ou non
     * 
     * @return vrai si l'hippocampe est gestant et non si il ne l'est pas
     */
    public boolean getEstGestant() {
        return this.estGestant;
    }

    /**
     * Definie si l'hippocampe est gestant
     * 
     * @param estGestant vrai si l'hippocampe est gestant et non si il ne l'est pas
     */
    public void setEstGestant(boolean estGestant) {
        this.estGestant = estGestant;
    }

    /**
     * Renvoie le sexe de l'hippocampe
     * 
     * @return le sexe de l'hippocampe
     */
    public Sexe getSexe() {
        return this.sexe;
    }

    /**
     * Definie le sexe de l'hippocampe
     * 
     * @param sexe le sexe de l'hippocampe
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    /**
     * Renvoie l'espece de l'hippocampe
     * 
     * @return l'espece de l'hippocampe
     */
    public EspeceHippocampe getEspece() {
        return this.espece;
    }

    /**
     * Definie l'espece de l'hippocampe
     * 
     * @param espece l'espece de l'hippocampe
     */
    public void setEspece(EspeceHippocampe espece) {
        this.espece = espece;
    }

    /**
     * Renvoie le type de peche utilisé pour peché l'hippocampe
     * 
     * @return le type de peche utilisé pour peché l'hippocampe
     */
    public Peche getPeche() {
        return this.typePeche;
    }

    /**
     * Definie le type de peche utilisé pour peché l'hippocampe
     * 
     * @param peche le type de peche utilisé pour peché l'hippocampe
     */
    public void setPeche(Peche peche) {
        this.typePeche = peche;
    }

    /**
     * Renvoie l'espece Hippocampe
     * 
     * @return l'espece Hippocampe
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.HIPPOCAMPE;
    }
}