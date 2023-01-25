package modele.donnee;

/**
 * @author Mateo Flejou
 *         class Lieu
 */
public class Lieu {
    /**
     * Coordonne geographique Y d'un lieu
     * Private visibillity
     */
    private double yCoord;
    /**
     * Coordonne geographique X d'un lieu
     * Private visibillity
     */
    private double xCoord;

    /**
     * Constructeur de l'objetLieu
     * 
     * @param x - Coordonne X
     * @param y - Coordonne Y
     */
    public Lieu(double x, double y) {
        this.yCoord = y;
        this.xCoord = x;
    }

    /**
     * Donne la coordonné Y du Lieu
     * 
     * @return Y - La coordonne Y
     */
    public double getYCoord() {
        return this.yCoord;
    }

    /**
     * Change la coordonné Y du Lieu
     * 
     * @param yCoord Y - Nouvelle coordonne Y
     */
    public void setYCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Donne la coordonné X du Lieu
     * 
     * @return xCoord - La coordonne X
     */
    public double getXCoord() {
        return this.xCoord;
    }

    /**
     * Change la coordonné X du Lieu
     * 
     * @param xCoord X - Nouvelle coordonne X
     */
    public void setXCoord(double xCoord) {
        this.xCoord = xCoord;
    }
}