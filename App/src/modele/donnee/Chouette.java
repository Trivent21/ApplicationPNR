package modele.donnee;

import java.sql.Time;
import java.util.*;

/**
 * @author Clement MONFORT
 *         Object pour stocker les informations collectées sur une Chouette.
 */
public class Chouette implements IObs<ObsChouette> {
    /**
     * Les observations sur la chouette.
     * visibilité prive
     */
    private ArrayList<ObsChouette> lesObservations;
    /**
     * L'espéce d'une chouette.
     * visibilité prive
     */
    private EspeceChouette espece;
    /**
     * Le genre d'une chouette.
     * visibilité prive
     */
    private Sexe sexe;
    /**
     * L'identifiant d'une chouette.
     * visibilité prive
     */
    private String idChouette;

    /**
     * Constructeur de le l'objet Chouette:
     * 
     * @param id     (String), l'identifiant de la chouette.
     * @param leSexe (enum), le sexe de la chouette.
     * @param Espece (enum), l'espece de la chouette.
     */
    public Chouette(String id, Sexe leSexe, EspeceChouette Espece) {
        if (!id.equals("") || leSexe != null || Espece != null) {
            setIdChouette(id);
            setEspece(Espece);
            setSexe(leSexe);
            this.lesObservations = new ArrayList<ObsChouette>();
        } else {
            throw new IllegalArgumentException("Bad setting - nidGCI");
        }
    }

    /**
     * Renvoie l'identifiant de l'objet chouette.
     * 
     * @return (String), l'identifiant de la chouette.
     */
    public String getIdChouette() {
        return (this.idChouette);
    }

    /**
     * Change l'identifiant de l'objet chouette.
     * 
     * @param idChouette (String), le nouvel identifiant.
     */
    public void setIdChouette(String idChouette) {
        this.idChouette = idChouette;
    }

    /**
     * Renvoie l'espece de l'objet chouette.
     * 
     * @return (enum), l'espece de la chouette.
     */
    public EspeceChouette getEspece() {
        return this.espece;
    }

    /**
     * Change l'espece de l'objet chouette.
     * 
     * @param espece (String), la nouvelle espece.
     */
    public void setEspece(EspeceChouette espece) {
        this.espece = espece;
    }

    /**
     * Renvoie le sexe de l'objet chouette.
     * 
     * @return (enum), le sexe de la chouette.
     */
    public Sexe getSexe() {
        return (this.sexe);
    }

    /**
     * Change le genre de l'objet chouette.
     * 
     * @param sexe (String), le nouveau genre.
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    /**
     * Renvoie l'attribut lesObservation de l'objet Chouette.
     * 
     * @return ArrayList(ObsChouette),
     */
    public ArrayList<ObsChouette> getLesObservation() {
        return (this.lesObservations);
    }

    /**
     * Change l'attribut lesObservation de l'objet Chouette.
     * 
     * @param lesObservation ArrayListe(ObsChouette), les nouvelles observation.
     */
    public void setLesObservation(ArrayList<ObsChouette> lesObservation) {
        this.lesObservations = lesObservation;
    }

    /**
     * Ajoute un objet ObsChouette à l'attribut lesObservation.
     * 
     * @param obs (ObsChouette), l'observation à ajoutées.
     */
    public void ajouteUneObs(ObsChouette obs) {
        this.lesObservations.add(obs);
    }

    /**
     * Ajoute plusieus objets ObsChouette à l'attribut lesObservation.
     * 
     * @param obs ArrayListe(ObsChouette), les observation à ajoutées.
     */
    public void ajoutePlsObs(ArrayList<ObsChouette> obs) {
        if (obs != null) {
            this.lesObservations.addAll(obs);
        } else {
            System.out.println("Erreur - Arraylist null - ajoutePlsObs");
        }

    }

    /**
     * Vide l'attribut lesObservation de tout objet ObsChouette.
     */
    public void videObs() {
        this.lesObservations.clear();
    }

    /**
     * Retire un objet ObsChouette de l'attribut lesObservation.
     * 
     * @param idObs (int), l'identifiant de l'observation à supprimé.
     * @return res - Un boolean False = pas obs retire | true = un obs est retiré
     */
    public boolean retireObs(int idObs) {
        boolean res = false;
        int tmp = 0;
        Lieu place = new Lieu(0, 0);
        ArrayList<Observateur> listIntil = new ArrayList<Observateur>();

        Date date1 = new Date();
        long timeInMilliSeconds = date1.getTime();
        java.sql.Date date = new java.sql.Date(timeInMilliSeconds);
        long now = System.currentTimeMillis();
        Time heure = new Time(now);

        Observation tmpObs = new ObsChouette(0, date, heure, place, listIntil, TypeObservation.VISUELLE);
        for (Observation cl : this.lesObservations) {
            tmp = cl.getIdObs();
            if (tmp == idObs) {
                tmpObs = cl;
                res = true;
            }
        }
        if (res) {
            this.lesObservations.remove(tmpObs);
        }
        return res;
    }

    /**
     * Renvoie ne nombre d'observation lié à la chouette.
     */
    public int nbObs() {
        return (this.lesObservations.size());
    }

}