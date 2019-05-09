package app;

import java.util.*;
import java.util.function.Function;
import java.util.function.BiFunction;

class Graph<E> {

  private ArrayList<Node<E>> vertices = new ArrayList<Node<E>>();

  public void addVertex(Node<E> vertex) {
    vertices.add(vertex);
  }

  public Node<E> getVertex(int index) {
    return vertices.get(index);
  }

  public void removeVertex(Node<E> vertex) {
    // remove all edges between vertex and his adjacents
    for(Node<E> adjacent : vertex.getAdjacents()) {
      vertex.removeAdjacent(adjacent);
      adjacent.removeAdjacent(vertex);
    }

    // delete vertex object
    vertices.remove(vertex);
  }

  public void addEdge(Node<E> a, Node<E> b) {
    a.addAdjacent(b);
    b.addAdjacent(a);
  }

  public void removeEdge(Node<E> a, Node<E> b) {
    a.removeAdjacent(b);
    b.removeAdjacent(a);
  }

  public ArrayList<Node<E>> getVertices() {
    return vertices;
  }

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