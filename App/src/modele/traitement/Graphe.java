package modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

/**
 * It's a class that represents a graph
 */
public class Graphe {
    /**
     * Creating a HashMap of Sommet objects and ArrayList of Sommet objects.
     */
    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;

    /**
     * It returns the vertex with the given id.
     * 
     * @param id the id of the vertex.
     * @return The method returns a Sommet object.
     */
    private Sommet getSomFromId(int id) {
        Iterator<Sommet> buffer = sommetsVoisins.keySet().iterator();
        boolean fd = false;
        Sommet pt = null;

        if (id >= 0) {
            while (!fd && buffer.hasNext()) {
                if ((pt = buffer.next()).getId() == id) {
                    fd = true;
                }
            }
        }
        return ((fd) ? pt : null);
    }

    /**
     * It returns false if the edge already exists, otherwise it adds the edge and
     * returns true.
     * 
     * @param a Sommet.
     * @param b Sommet.
     * @return The method returns a boolean value.
     */
    private boolean addArete(Sommet a, Sommet b) {
        return (this.sommetsVoisins.get(a).contains(b) ? false : this.sommetsVoisins.get(a).add(b));
    }

    /**
     * This function removes the edge between two vertices if it exists.
     * 
     * @param a Sommet.
     * @param b Sommet.
     * @return The method returns a boolean value.
     */
    private boolean subArete(Sommet a, Sommet b) {
        return ((!this.sommetsVoisins.get(a).contains(b)) ? false : this.sommetsVoisins.get(a).remove(b));
    }

    /**
     * It return a list of vertices present in the graph.
     * 
     * @return The method returns an ArrayList of Sommets.
     */
    private ArrayList<Sommet> getSommets() {
        ArrayList<Sommet> res = new ArrayList<Sommet>();

        this.sommetsVoisins.forEach((key, value) -> {
            res.add(key);
        });
        return (res);
    }

    /**
     * It returns the distance between two vertices, or -1 if they are not
     * connected.
     * 
     * @param a   the starting point.
     * @param b   the destination vertex.
     * @param rec the number of recursion.
     * @param max if true, the function will return the maximum distance between the
     *            two vertices, if false, it will return the minimum distance.
     * @return The distance between two vertices.
     */
    private int distArrRec(Sommet a, Sommet b, int rec, boolean max) {
        Iterator<Sommet> it = null;
        int buff = 0;
        int res = -1;

        if (rec < nbAretes()) {
            rec++;
            it = this.sommetsVoisins.get(a).iterator();
            if (this.sommetsVoisins.get(a).contains(b)) {
                res = (max && res > rec) ? res : rec;
            }
            while (it.hasNext()) {
                buff = distArrRec(it.next(), b, rec, max);
                if (buff != -1 && ((max && buff > res) || (buff < res || res < 0))) {
                    res = buff;
                }
            }
        }
        return (res);
    }

    /**
     * It takes an ArrayList of integers containing the ids of vertices and returns
     * an ArrayList of Sommet objects.
     * 
     * @param idSoms an array of integers.
     * @return An ArrayList of Sommet objects.
     */
    public ArrayList<Sommet> getSomsFromId(ArrayList<Integer> idSoms) {
        ArrayList<Sommet> res = new ArrayList<Sommet>();

        for (int idSom : idSoms) {
            res.add(getSomFromId(idSom));
        }
        return (res);
    }

    /**
     * It takes a list of vertices and returns a map of vertices to their neighbors.
     * 
     * @param sommets ArrayList of Sommet objects.
     * @return A HashMap of Sommet and ArrayList of Sommet.
     */
    public HashMap<Sommet, ArrayList<Sommet>> toHashMap(ArrayList<Sommet> sommets) {
        HashMap<Sommet, ArrayList<Sommet>> res = new HashMap<Sommet, ArrayList<Sommet>>();

        for (Sommet som : sommets) {
            res.put(som, sommetsVoisins.get(som));
        }
        return (res);
    }

    /**
     * Creating a graph from a list of vertices and a minimum distance.
     * 
     * @param sommets list of vertices.
     * @param distMin minimum distance for vertex to be connected.
     */
    public Graphe(ArrayList<Sommet> sommets, double distMin) {
        ArrayList<Sommet> buffer = new ArrayList<Sommet>();

        this.sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();
        for (int i = 0; i < sommets.size(); i++) {
            this.sommetsVoisins.put(sommets.get(i), new ArrayList<Sommet>());
        }
        for (int i = 0; i < sommets.size(); i++) {
            for (int j = 0; j < sommets.size(); j++) {
                if (i != j && distMin >= sommets.get(i).calculDist(sommets.get(j))) {
                    if (this.ajouteArete(sommets.get(i).getId(), sommets.get(j).getId())) {
                        buffer.add(sommets.get(j));
                    }
                }
            }
            buffer.clear();
        }
    }

    /**
     * Creating a graph from a list of vertices and a minimum distance.
     * 
     * @param somVoisins a HashMap of Sommet objects and ArrayList of Sommet
     *                   objects.
     */
    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) {
        if (somVoisins != null) {
            this.sommetsVoisins = somVoisins;
        }
    }

    /**
     * A copy constructor.
     * 
     * @param g the graph to copy.
     */
    public Graphe(Graphe g) {
        this.setSommetVoisins(g.getSommetVoisins());
    }

    /**
     * It prints the id of the vertex and the list of its neighbors.
     * 
     * @param id the id of the vertex.
     */
    public void printSommet(int id) {
        Iterator<Sommet> i = null;
        String str = "";

        if (estDansGraphe(id)) {
            i = (this.sommetsVoisins.get(getSomFromId(id))).iterator();
            while (i.hasNext()) {
                str += " " + i.next().getId();
            }
        }
        System.out.println("id: " + id + "\nliste:" + str);
    }

    /**
     * It returns a string containing the id of each vertex and the list of its
     * neighbors.
     * 
     * @return The string representation of the graph.
     */
    public String toString() {
        Set<Entry<Sommet, ArrayList<Sommet>>> set = this.sommetsVoisins.entrySet();
        String str = "";

        for (Entry<Sommet, ArrayList<Sommet>> entry : set) {
            str += "\nid : " + entry.getKey().getId() + "\nliste : ";
            for (Sommet node : entry.getValue()) {
                str += node.getId() + " ";
            }
        }
        return (str);
    }

    /**
     * This function returns a HashMap of vertices and their neighbors.
     * 
     * @return The HashMap of the vertices and their neighbors.
     */
    public HashMap<Sommet, ArrayList<Sommet>> getSommetVoisins() {
        return (this.sommetsVoisins);
    }

    /**
     * This function sets the sommetsVoisins attribute of the Graphe object to the
     * HashMap passed as a parameter.
     * 
     * @param somVoisins a HashMap of Sommet objects, with an ArrayList of Sommet
     *                   objects as values.
     */
    public void setSommetVoisins(HashMap<Sommet, ArrayList<Sommet>> somVoisins) {
        this.sommetsVoisins = somVoisins;
    }

    /**
     * This function returns the number of vertices in the graph.
     * 
     * @return The number of vertices in the graph.
     */
    public int nbSommets() {
        return (this.sommetsVoisins.size());
    }

    /**
     * It returns the number of edges in the graph.
     * 
     * @return The number of edges in the graph.
     */
    public int nbAretes() {
        int[] somSommet = new int[1];

        this.sommetsVoisins.forEach((key, value) -> {
            somSommet[0] += calculeDegre(key.getId());
        });
        return (somSommet[0] / 2);
    }

    /**
     * This function returns true if the vertex with the given id is in the graph,
     * false otherwise.
     * 
     * @param idSom the id of the node.
     * @return The method returns a boolean value.
     */
    public boolean estDansGraphe(int idSom) {
        return (getSomFromId(idSom) != null) ? true : false;
    }

    /**
     * It returns the degree of a vertex in a graph.
     * 
     * @param idSom the ID of the vertex.
     * @return The degree of the vertex.
     */
    public int calculeDegre(int idSom) {
        Iterator<Sommet> it = null;
        boolean fd = false;
        Sommet a = null;
        int res = 0;

        if (estDansGraphe(idSom)) {
            it = (sommetsVoisins.keySet()).iterator();
            while (!fd && it.hasNext()) {
                if ((a = it.next()).getId() == idSom) {
                    fd = true;
                }
            }
            res = sommetsVoisins.get(a).size();
        } else {
            System.err.println("Error - ID not found");
        }
        return (res);
    }

    /**
     * This function calculates the degree of each vertex in the graph.
     * 
     * @return A HashMap of Sommet and Integer.
     */
    public HashMap<Sommet, Integer> calculeDegres() {
        HashMap<Sommet, Integer> degre = new HashMap<Sommet, Integer>();

        this.sommetsVoisins.forEach((key, value) -> {
            degre.put(key, value.size());
        });
        return (degre);
    }

    /**
     * "If the two vertices exist, return whether they are adjacent."
     * 
     * The function is called with two integers, which are the IDs of the vertices.
     * The function first gets the vertices from the IDs.
     * If either of the vertices is null, then the function returns false.
     * Otherwise, the function returns whether the first vertex is adjacent to the
     * second vertex.
     * 
     * @param idSom1 the id of the first vertex.
     * @param idSom2 the id of the second vertex.
     * @return a boolean value.
     */
    public boolean sontVoisins(int idSom1, int idSom2) {
        Sommet a = getSomFromId(idSom1);
        Sommet b = getSomFromId(idSom2);

        return ((a == null || b == null) ? false : this.sommetsVoisins.get(a).contains(b));
    }

    /**
     * It checks if there is a path between two nodes
     * 
     * @param idSom1 the id of the first vertex.
     * @param idSom2 the id of the destination node.
     * @return The method returns a boolean value.
     */
    public boolean existeChemin(int idSom1, int idSom2) {
        boolean res = (idSom1 == idSom2) ? true : false;
        Sommet a = (!res) ? getSomFromId(idSom1) : null;
        Sommet b = (!res) ? getSomFromId(idSom2) : null;
        Iterator<Sommet> it = null;

        if (a != null && b != null) {
            it = this.sommetsVoisins.get(a).iterator();
            res = this.sommetsVoisins.get(a).contains(b);
            while (!res && it.hasNext()) {
                res = ((distArrRec(it.next(), b, 0, false) == -1) ? false : true);
            }
        }
        return (res);
    }

    /**
     * It returns the list of neighbors of a vertex.
     * 
     * @param idSom the id of the vertex.
     * @return The method returns the list of neighbors of the vertex with the given
     *         id.
     */
    public ArrayList<Sommet> voisins(int idSom) {
        Sommet a = getSomFromId(idSom);

        return ((a == null) ? null : this.sommetsVoisins.get(a));
    }

    /**
     * It returns the maximum degree of the graph.
     * 
     * @return The maximum degree of the graph.
     */
    public int maxDegre() {
        Set<Entry<Sommet, Integer>> degre = (calculeDegres()).entrySet();
        int max = 0;

        for (Entry<Sommet, Integer> entry : degre) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return (max);
    }

    /**
     * It adds an edge between two vertices.
     * 
     * @param idSom1 the id of the first vertex.
     * @param idSom2 the id of the second vertex.
     * @return The method returns a boolean value.
     */
    public boolean ajouteArete(int idSom1, int idSom2) {
        Sommet a = getSomFromId(idSom1);
        Sommet b = getSomFromId(idSom2);

        return (addArete(a, b) && addArete(b, a));
    }

    /**
     * It removes an edge from the graph.
     * 
     * @param idSom1 the id of the first vertex.
     * @param idSom2 the id of the second vertex.
     * @return The method returns a boolean value.
     */
    public boolean retireArete(int idSom1, int idSom2) {
        Sommet a = getSomFromId(idSom1);
        Sommet b = getSomFromId(idSom2);

        return (subArete(a, b) && subArete(b, a));
    }

    /**
     * It creates a matrix of size n x n+1, where n is the number of vertices in the
     * graph, and fills it with 1s and 0s depending on whether the vertices are
     * adjacent or not.
     * 
     * @return The method returns a matrix of integers.
     */
    public int[][] matriceAdjacence() {
        int[][] mtx = new int[sommetsVoisins.size()][sommetsVoisins.size() + 1];
        int id = 1;

        for (int[] row : mtx) {
            row[0] = id;
            for (int i = 1; i < row.length; i++) {
                if (sontVoisins(id, i)) {
                    row[i] = 1;
                }
            }
            id++;
        }
        return (mtx);
    }

    /**
     * It checks if the graph is connected by checking if all the nodes are
     * connected to each other.
     * 
     * @return A boolean value.
     */
    public boolean estConnexe() {
        ArrayList<Integer> visited = new ArrayList<Integer>();
        ArrayList<Integer> stack = new ArrayList<Integer>();
        boolean connexe = false;
        int id = 4;

        do {
            if (!stack.isEmpty()) {
                id = stack.get(0);
                stack.remove(0);
            }
            visited.add(id);
            for (Sommet voisin : voisins(id)) {
                if (!stack.contains(voisin.getId()) && !visited.contains(voisin.getId())) {
                    stack.add(voisin.getId());
                }
            }
            if (visited.size() == sommetsVoisins.size()) {
                connexe = true;
            }
        } while (!connexe && !stack.isEmpty());
        return connexe;
    }

    /**
     * It returns an ArrayList of Graphe objects, each of which is a connected
     * component of the original Graphe object.
     * 
     * @return A list of connected components.
     */
    public ArrayList<Graphe> composanteConnexe() {
        ArrayList<Integer> visitedGlobal = new ArrayList<Integer>();
        ArrayList<Integer> visitedLocal = new ArrayList<Integer>();
        ArrayList<Integer> stack = new ArrayList<Integer>();
        ArrayList<Graphe> res = new ArrayList<Graphe>();
        ArrayList<Sommet> tmp;
        ArrayList<Sommet> sommets = getSommets();
        int id = sommets.get(0).getId();

        while (visitedGlobal.size() != sommetsVoisins.size()) {
            visitedLocal.clear();
            for (Sommet s : sommets) {
                if (!visitedGlobal.contains(s.getId())) {
                    id = s.getId();
                }
            }
            do {
                if (!stack.isEmpty()) {
                    id = stack.get(0);
                    stack.remove(0);
                }
                visitedLocal.add(id);
                for (Sommet voisin : voisins(id)) {
                    System.out.println(voisin.getId());
                    if (!stack.contains(voisin.getId()) && !visitedLocal.contains(voisin.getId())) {
                        stack.add(voisin.getId());
                    }
                }
            } while (visitedLocal.size() != sommetsVoisins.size() && !stack.isEmpty());
            visitedGlobal.addAll(visitedLocal);
            tmp = getSomsFromId(visitedLocal);
            res.add(new Graphe(toHashMap(tmp)));
        }
        return (res);
    }

    /**
     * It returns the distance between two nodes in a graph.
     * 
     * @param idSom1 the id of the first vertex.
     * @param idSom2 the id of the second vertex.
     * @return The distance between two vertices.
     */
    public double distArrete(int idSom1, int idSom2) {
        Sommet a = getSomFromId(idSom1);
        Sommet b = getSomFromId(idSom2);

        return ((a != null && b != null) ? distArrRec(a, b, 0, false) : -1);
    }

    /**
     * It returns the distance between the furthest vertex from the vertex with the
     * id passed as a parameter.
     * 
     * @param idSom the id of the vertex.
     * @return The excentricity of the vertex.
     */
    public int excentricite(int idSom) {
        Sommet a = getSomFromId(idSom);
        Iterator<Sommet> it = (a != null) ? this.sommetsVoisins.keySet().iterator() : null;
        int buffer = 0;
        int ex = 0;

        if (it != null) {
            while (it.hasNext()) {
                if ((buffer = distArrRec(a, it.next(), 0, false)) > ex) {
                    ex = buffer;
                }
            }
        }
        return (ex);
    }

    /**
     * It returns the maximum distance between any two vertices in the graph.
     * 
     * @return The diameter of the graph.
     */
    public int diametre() {
        Iterator<Sommet> key = this.sommetsVoisins.keySet().iterator();
        int buff = 0;
        int res = 0;

        while (key.hasNext()) {
            if ((buff = excentricite(key.next().getId())) > res) {
                res = buff;
            }
        }
        return (res);
    }

    /**
     * It returns the minimum distance between two vertices in a graph.
     * 
     * @return The radius of the graph.
     */
    public int rayon() {
        Iterator<Sommet> key = this.sommetsVoisins.keySet().iterator();
        int buff = 0;
        int res = -1;

        if (estConnexe()) {
            while (key.hasNext()) {
                buff = excentricite(key.next().getId());
                if (buff < res || res == -1) {
                    res = buff;
                }
            }
        }
        return (res);
    }
}