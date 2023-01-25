package modele.donnee;

/**
 * @author Matéo FLEJOU
 *         Class Observateur
 */
public class Observateur {
    /** Id de l'Observateur : permet de retrouve Visibilité prive */
    private int idObservateur;
    /** Nom de l'observateur. Visibilité prive */
    private String nom;
    /** Prénom de l'observateur. Visibilité prive */
    private String prenom;

    /**
     * Constructeur de L'observation
     * 
     * @param id       - Le Id de l'Observateur
     * @param leNom    - Le nom de l'Observateur
     * @param lePrenom - Le Prenom de l'Observateur
     */
    public Observateur(int id, String leNom, String lePrenom) {
        if (id >= 0) {
            this.idObservateur = id;
            this.nom = leNom;
            this.prenom = lePrenom;
        } else {
            throw new IllegalArgumentException("\nBad setting - Observateur");
        }
    }

    /**
     * Donne l'id de l'observateur
     * 
     * @return idObservateur - Le Id
     */
    public int getIdObservateur() {
        return this.idObservateur;
    }

    /**
     * Change l'id de l'observateur
     * 
     * @param idObservateur - Nouvelle id
     */
    public void setIdObservateur(int idObservateur) {
        this.idObservateur = idObservateur;
    }

    /**
     * Donne le nom de l'observateur
     * 
     * @return nom - Le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Change nom de l'observateur
     * 
     * @param nom - Nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Donne le prénom de l'observateur
     * 
     * @return prenom - Le prenom
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Change le prenom de l'observateur
     * 
     * @param prenom - Nouveau prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}