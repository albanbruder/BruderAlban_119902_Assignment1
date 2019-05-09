package app;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class InteractiveMode {
    
    Scanner scanner;

    public InteractiveMode(InputStream in) throws Exception {
        scanner = new Scanner(in);

        System.out.println("You entered Interactive Mode:");
        System.out.println("Enter <help> to see options or <exit> to close program");

        Graph<City> graph = new Graph<City>();
        HashMap<String, Node<City>> nodes = new HashMap<String, Node<City>>();

        System.out.print("> ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] options = line.split(" ");

            switch (options[0]) {
            case "help":
                displayHelp();
                break;
            case "create":
                createCity(options, graph, nodes);
                break;
            case "delete":
                deleteCity(options, graph, nodes);
                break;
            case "connect":
                connectCities(options, graph, nodes);
                break;
            case "disconnect":
                disconnectCities(options, graph, nodes);
                break;
            case "set":
                setCityMeta(options, graph, nodes);
                break;
            case "unset":
                unsetCityMeta(options, graph, nodes);
                break;
            case "print":
                System.out.println(graph.toGraphviz((City c) -> c.toGraphvizLabel()));
                break;
            case "exit":
                return;
            }
            System.out.print("> ");
        }
    }

    private static void displayHelp() {
        System.out.println("Usage:");
        System.out.println("  create [city]");
        System.out.println("  delete [city]");
        System.out.println("  connect [city] [city]");
        System.out.println("  disconnect [city] [city]");
        System.out.println("  set [city] [key] [value]");
        System.out.println("  unset [city] [key]");
        System.out.println("  print");
    }

    /**
     * Command: create [city]
     * 
     * @param options
     * @param graph
     * @param nodes
     */
    private static void createCity(String[] options, Graph<City> graph, HashMap<String,Node<City>> nodes) {
        if(options.length != 2) {
            return;
        }

        System.out.println("Creating city " + options[1]);
        Node<City> node = new Node<City>(new City(options[1]));
        nodes.put(options[1], node);
        graph.addVertex(node);
    }

    /**
     * Command: delete [city]
     */
    private static void deleteCity(String[] options, Graph<City> graph, HashMap<String,Node<City>> nodes) {
        if(options.length != 2) {
            return;
        }

        Node<City> node = nodes.get(options[1]);
        if(node == null) {
            System.out.println("No such city " + options[1]);
            return;
        }

        System.out.println("Deleting city " + options[1]);
        graph.removeVertex(node);
        nodes.remove(options[1]);
    }

    /**
     * Command: connect [city] [city]
     * 
     * @param options
     * @param graph
     * @param nodes
     */
    private static void connectCities(String[] options, Graph<City> graph, HashMap<String,Node<City>> nodes) {
        if(options.length != 3) {
            return;
        }

        Node<City> nodeA = nodes.get(options[1]);
        if(nodeA == null) {
            System.out.println("No such city " + options[1]);
            return;
        }

        Node<City> nodeB = nodes.get(options[2]);
        if(nodeB == null) {
            System.out.println("No such city " + options[2]);
            return;
        }

        System.out.println("Connecting " + options[1] + " and " + options[2]);
        graph.addEdge(nodeA, nodeB);
    }

    /**
     * Command: disconnect [city] [city]
     */
    private static void disconnectCities(String[] options, Graph<City> graph, HashMap<String,Node<City>> nodes) {
        if(options.length != 3) {
            return;
        }

        Node<City> nodeA = nodes.get(options[1]);
        if(nodeA == null) {
            System.out.println("No such city " + options[1]);
            return;
        }

        Node<City> nodeB = nodes.get(options[2]);
        if(nodeB == null) {
            System.out.println("No such city " + options[2]);
            return;
        }

        System.out.println("Disconnecting " + options[1] + " and " + options[2]);
        graph.removeEdge(nodeA, nodeB);
    }

    /**
     * Command: set [city] [key] [value]
     * @param options
     * @param graph
     * @param nodes
     */
    private static void setCityMeta(String[] options, Graph<City> graph, HashMap<String,Node<City>> nodes) {
        if(options.length != 4) {
            return;
        }

        Node<City> node = nodes.get(options[1]);
        if(node == null) {
            System.out.println("No such city " + options[1]);
            return;
        }

        node.getValue().addMeta(options[2], options[3]);
    }

    /**
     * Command: unset [city] [key]
     */
    private static void unsetCityMeta(String[] options, Graph<City> graph, HashMap<String,Node<City>> nodes) {
        if(options.length != 3) {
            return;
        }

        Node<City> node = nodes.get(options[1]);
        if(node == null) {
            System.out.println("No such city " + options[1]);
            return;
        }

        node.getValue().removeMeta(options[2]);
    }
}