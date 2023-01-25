package modele.traitement;

import modele.donnee.*;
import java.sql.*;

/**
 * The class Sommet is a class that represents a vertex in a graph. It contains
 * the id of the observation, the coordinates of the place where the observation
 * was made, the date of the observation and the species observed.
 */
public class Sommet {
    /**
     * A private variable that is used to store the id of the observation.
     */
    private int id;
    /**
     * A private variable that is used to store the coordinates of the place where
     * the observation was made.
     */
    private Lieu coordLieu;
    /**
     * A private variable that is used to store the date of the observation.
     */
    private Date date;
    /**
     * A private variable that is used to store the species observed.
     */
    private EspeceObservee espece;

    /**
     * A constructor that takes an Observation object as a parameter and assigns the
     * values of the Observation object to the Sommet object.
     * 
     * @param obs Observation.
     */
    public Sommet(Observation obs) {
        this.id = obs.getIdObs();
        this.coordLieu = obs.getLieuObs();
        this.date = obs.getDateObs();
        this.espece = obs.especeObs();
    }

    /**
     * A constructor that takes 4 parameters and assigns them to the variables of
     * the Sommet object.
     * 
     * @param id        of the observation
     * @param coordLieu coordinate of the observation
     * @param date      date of the observation
     * @param espece    species observed in the observation
     */
    public Sommet(int id, Lieu coordLieu, Date date, EspeceObservee espece) {
        this.id = id;
        this.coordLieu = coordLieu;
        this.date = date;
        this.espece = espece;
    }

    /**
     * This function calculates the distance between two points.
     * 
     * @param som the destination vertex.
     * @return The distance between two points.
     */
    public double calculDist(Sommet som) {
        double dist = Math.pow(this.coordLieu.getXCoord() - som.getCoordLieu().getXCoord(), 2)
                + Math.pow(som.getCoordLieu().getYCoord() - this.coordLieu.getYCoord(), 2);
        dist = Math.sqrt(dist);
        return (dist);
    }

    /**
     * This function returns the id of the current object.
     * 
     * @return The id of the object.
     */
    public int getId() {
        return (this.id);
    }

    /**
     * This function returns the coordinates of the place.
     * 
     * @return The coordinates of the place.
     */
    public Lieu getCoordLieu() {
        return (this.coordLieu);
    }

    /**
     * This function returns the date of the event.
     * 
     * @return The date of the event.
     */
    public Date getDate() {
        return (this.date);
    }

    /**
     * This function returns the species of the observed object.
     * 
     * @return The espece attribute of the class.
     */
    public EspeceObservee getEspece() {
        return (this.espece);
    }

}