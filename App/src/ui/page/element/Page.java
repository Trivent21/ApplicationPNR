package ui.page.element;

import javax.swing.JPanel;

import ui.Launcher;

/**
 * It's a class that is used to create a page for the launcher.
 */
public class Page extends JPanel {
    /**
     * A private variable that is used to store the title of the page.
     */
    private String title;
    /**
     * A private variable that is used to store the launcher object.
     */
    private Launcher lc;

    /**
     * A constructor that takes in a launcher object and a string as parameters. It
     * then sets the launcher object to the launcher object passed in as a parameter
     * and sets the title to the string passed in as a parameter.
     * 
     * @param lc    the launcher hosting the page
     * @param title the title of the page
     */
    public Page(Launcher lc, String title) {
        this.setLaucher(lc);
        this.title = title;
    }

    /**
     * This function returns the title of the book
     * 
     * @return The title of the book.
     */
    public String getTitle() {
        return (this.title);
    }

    /**
     * This function returns the current page.
     * 
     * @return The page object.
     */
    public Page getPage() {
        return (this);
    }

    /**
     * This function sets the launcher object to the launcher object passed in as a
     * parameter
     * 
     * @param lc The launcher object that is used to launch the game.
     */
    public void setLaucher(Launcher lc) {
        this.lc = lc;
    }

    /**
     * This function returns the launcher object
     * 
     * @return The launcher object.
     */
    public Launcher getLauncher() {
        return (this.lc);
    }
}
