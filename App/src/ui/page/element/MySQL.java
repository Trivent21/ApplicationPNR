package ui.page.element;

import java.sql.*;

/**
 * It's a class that allows you to connect to a MySQL database and execute
 * queries
 */
public class MySQL {

    /**
     * It's a private variable. It's only accessible from within the class.
     */
    private Connection bdd;

    /**
     * It returns a string that is the url of the database
     * 
     * @param reseau the IP address of the server
     * @param port   the port number of the MySQL server
     * @param base   the name of the database
     * @return The url of the database.
     */
    public static String url(String reseau, int port, String base) {
        return ("jdbc:mysql://" + reseau + ":" + port + "/" + base);
    }

    /**
     * It's a constructor. It's called when you create a new instance of the class.
     * 
     * @param addresse database url
     * @param user     the user who use the bdd
     * @param mdp      password of the user
     */
    public MySQL(String addresse, String user, String mdp) {
        Connection bdd = null;

        try {
            bdd = DriverManager.getConnection(addresse, user, mdp);
        } catch (SQLTimeoutException e) {
            System.out.println(e.getErrorCode() + " : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " : " + e.getMessage());
        }
        this.bdd = bdd;
    }

    /**
     * It takes a query as a parameter, executes it, and returns the result set
     * 
     * @param query The query to execute
     * @return A ResultSet object.
     */
    public ResultSet query(String query) {
        ResultSet rs = null;

        try {
            rs = this.bdd.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs);
    }

    /**
     * It takes a query as a parameter, prepares it, and executes it
     * 
     * @param query The query to execute
     */
    public void insert(String query) {
        try {
            this.bdd.prepareStatement(query).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}