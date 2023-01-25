package modele.donnee;

import java.util.Date;
import java.sql.Time;
import java.util.*;

/**
 * @author Anthony hascoet
 *         Objet NidCGI qui stocke les informations collectées sur un Nid.
 */
public class NidGCI implements IObs<ObsGCI> {
    /**
     * Liste d'observation sur le nid
     * visibilité prive
     */
    private ArrayList<ObsGCI> LesObservations;
    /**
     * Id du nid
     * Visibilite prive
     */
    private int idNid;
    /**
     * Nombre d'envol effectué dans le nid
     * Visibilite prive
     */
    private int nbEnvol;
    /**
     * Plage du nid
     * Visibilite prive
     */
    private String nomPlage;

    /**
     * Constructeur de l'objet NidCGI
     * 
     * @param id    - Id supérieur ou égale à 0
     * @param plage - Nom de la palge
     */
    public NidGCI(int id, String plage) {
        if (id >= 0 && !plage.equals("")) {
            this.idNid = id;
            this.nomPlage = plage;
            nbEnvol = 0;
            this.LesObservations = new ArrayList<ObsGCI>();
        } else {
            throw new IllegalArgumentException("Bad setting - nidGCI");
        }
    }

    /**
     * Renvoie l'attribut lesObservation de l'objet NidGCI.
     * 
     * 
     * @return ArrayList(ObsGCI)
     */
    public ArrayList<ObsGCI> getLesObservation() {
        return (this.LesObservations);
    }

    /**
     * Renvoie la date de debut de l'observation
     * 
     * @return la date de debut de l'observation
     */
    public java.sql.Date dateDebutObs() {
        Date date = new Date();
        long timeInMilliSeconds = date.getTime();
        java.sql.Date dateSql = new java.sql.Date(timeInMilliSeconds);
        if (LesObservations.size() != 0) {
            dateSql = this.LesObservations.get(0).getDateObs();
        } else {
            System.out.println("Impossible - Liste vide - dateDebutObs");
        }
        return dateSql;
    }

    /**
     * Renvoie la date de fin de l'observation
     * 
     * @return la date de fin de l'observation
     */
    public java.sql.Date dateFinObs() {
        Date date = new Date();
        long timeInMilliSeconds = date.getTime();
        java.sql.Date dateSql = new java.sql.Date(timeInMilliSeconds);
        if (LesObservations.size() != 0) {
            dateSql = this.LesObservations.get(this.LesObservations.size() - 1).getDateObs();
        } else {
            System.out.println("Impossible - Liste vide - dateFinObs");
        }
        return dateSql;
    }

    /**
     * Renvoie l'id du nid
     * 
     * @return l'id du nid
     */
    public int getIdNid() {
        return this.idNid;
    }

    /**
     * Definie l'id du nid
     * 
     * @param idNid l'id du nid
     */
    public void setIdNid(int idNid) {
        this.idNid = idNid;
    }

    /**
     * Renvoie le nombre d'envol effectué dans le nid
     * 
     * @return le nombre d'envol effectué dans le nid
     */
    public int getNbEnvol() {
        return this.nbEnvol;
    }

    /**
     * Definie le nombre d'envol effectué dans le nid
     * 
     * @param nbEnvol le nombre d'envol effectué dans le nid
     */
    public void setNbEnvol(int nbEnvol) {
        this.nbEnvol = nbEnvol;
    }

    /**
     * Renvoie le nom de Plage du nid
     * 
     * @return le nom de Plage du nid
     */
    public String getNomPlage() {
        return this.nomPlage;
    }

    /**
     * Definie le nom de Plage du nid
     * 
     * @param nomPlage le nom de Plage du nid
     */
    public void setNomPlage(String nomPlage) {
        if (nomPlage != null) {
            this.nomPlage = nomPlage;
        } else {
            System.out.println("Erreur setNomPlage - Parametre invalide");
        }
    }

    /**
     * Ajoute une observation sur le nid
     * 
     * @param obs une observation sur le nid
     */
    public void ajouteUneObs(ObsGCI obs) {
        if (obs != null) {
            this.LesObservations.add((ObsGCI) obs);
        } else {
            System.out.println("Erreur ajouteUneObs - Parametre invalide");
        }
    }

    /**
     * Ajoute une liste d'observations sur le nid
     * 
     * @param obs une liste d'observations sur le nid
     */
    public void ajoutePlsObs(ArrayList<ObsGCI> obs) {
        if (obs != null) {
            for (ObsGCI observateur : obs) {
                this.LesObservations.add(observateur);
            }
        } else {
            System.out.println("Erreur ajoutePlsObs - Parametre invalide");
        }
    }

    /**
     * Vide la liste d'observation
     */
    public void videObs() {
        this.LesObservations.clear();
    }

    /**
     * Retire une observation selon son id
     * 
     * @param idObs id de l'observation dans la liste
     */
    public boolean retireObs(int idObs) {
        boolean res = false;
        Iterator<ObsGCI> it = LesObservations.iterator();
        Observation tmp;
        while (!res && it.hasNext()) {
            tmp = (Observation) it.next();
            if (tmp.getIdObs() == idObs) {
                it.remove();
                res = true;
            }
        }
        return res;
    }

    /**
     * Renvoie le nombre d'observation sur le nid
     * 
     * @return nombre d'observation sur le nid
     */
    public int nbObs() {
        return this.LesObservations.size();
    }
}