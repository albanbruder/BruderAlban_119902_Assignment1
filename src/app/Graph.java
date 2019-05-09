package app;

import java.util.*;
import java.util.function.Function;
import java.util.function.BiFunction;

/**
 * A gerneric representation of a graph.
 * 
 * @param <E> The value type.
 */
class Graph<E> {

  /** List of nodes in this graph. */
  private ArrayList<Node<E>> vertices = new ArrayList<Node<E>>();

  /**
   * Adds a vertex to the graph.
   * 
   * @param vertex
   */
  public void addVertex(Node<E> vertex) {
    vertices.add(vertex);
  }

  /**
   * Return the vertex at the specified position.
   * 
   * @param index index of the vertex to return
   * @return the vertex at the specified position
   * @throws IndexOutOfBoundsException
   */
  public Node<E> getVertex(int index) throws IndexOutOfBoundsException {
    return vertices.get(index);
  }

  /**
   * Removes a vertex from the graph.
   * 
   * @param vertex the vertex to remove
   */
  public void removeVertex(Node<E> vertex) {
    // remove all edges between vertex and his adjacents
    for(Node<E> adjacent : vertex.getAdjacents()) {
      vertex.removeAdjacent(adjacent);
      adjacent.removeAdjacent(vertex);
    }

    // delete vertex object
    vertices.remove(vertex);
  }

  /**
   * Connects vertex a and vertex b.
   * 
   * @param a vertex
   * @param b vertex
   */
  public void addEdge(Node<E> a, Node<E> b) {
    a.addAdjacent(b);
    b.addAdjacent(a);
  }

  /**
   * Remove connection between vertex a and vertex b.
   */
  public void removeEdge(Node<E> a, Node<E> b) {
    a.removeAdjacent(b);
    b.removeAdjacent(a);
  }

  /**
   * Return all vertices of the graph.
   * 
   * @return all vertices of the graph
   */
  public ArrayList<Node<E>> getVertices() {
    return vertices;
  }

  /**
   * Returns the graph in the graphviz dot format.
   * 
   * @param labelMapper a function that maps <E> to label string
   * @return graph in the graphviz dot format
   */
  public String toGraphviz(Function<E, String> labelMapper) {
    String graphviz = "strict graph {" + "\n\n";

    // generate labels
    for(Node<E> vertex : vertices) {
      graphviz += "  " + vertices.indexOf(vertex) + " [label=" + labelMapper.apply(vertex.getValue()) + "]\n";
    }

    graphviz += "\n";

    for(Node<E> vertex : getVertices()) {
      for(Node<E> adjecent : vertex.getAdjacents()) {
        graphviz += "  " + vertices.indexOf(vertex) + " -- " + vertices.indexOf(adjecent) + "\n";
      }
    }
    graphviz += "}\n";
    return graphviz;
  }

  /**
   * Returns the graph in the trivial graph format.
   * 
   * @return graph in the trivial graph format
   */
  public String toTrivialGraphFormat() {
    String tgf = "";
    for(Node<E> vertex : getVertices()) {
      tgf += getVertices().indexOf(vertex) + " " + vertex.getValue() + "\n";
    }
    tgf += "#\n";
    for(Node<E> vertex : getVertices()) {
      for(Node<E> adjecent : vertex.getAdjacents()) {
        tgf += getVertices().indexOf(vertex) + " " + getVertices().indexOf(adjecent) + "\n";
      }
    }
    return tgf;
  }

  /**
   * Parses a graph in the trivial graph format.
   * 
   * @param <E> value type
   * @param tgf graph in the trivial graph format
   * @param mapper function that maps tgf id and label to <E>
   * @return Graph<E>
   */
  public static <E> Graph<E> fromTrivialGraphFormat(String tgf, BiFunction<String, String, E> mapper) {
    Graph<E> graph = new Graph<E>();

    String[] tgfParts = tgf.split("\n#\n");
    String[] nodes = tgfParts[0].split("\n");
    String[] edges = tgfParts[1].split("\n");

    HashMap<String, Node<E>> tmpNodes = new HashMap<String, Node<E>>();

    // parse nodes
    for(String data : nodes) {
      String[] dataParts = data.split(" ", 2);
      String label = dataParts.length == 2 ? dataParts[1] : "";
      Node<E> node = new Node<E>(mapper.apply(dataParts[0], label));
      tmpNodes.put(dataParts[0], node);
      graph.addVertex(node);
    }

    // parse edges
    for(String edge : edges) {
      String[] edgeParts = edge.split(" ");
      graph.addEdge(
        tmpNodes.get(edgeParts[0]),
        tmpNodes.get(edgeParts[1])
      );
    }

    return graph;
  }
}