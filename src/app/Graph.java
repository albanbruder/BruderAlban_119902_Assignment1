package app;

import java.util.*;
import java.util.function.Function;

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

  public String toGraphviz() {
    String graphviz = "strict graph {" + "\n";
    for(Node<E> vertex : getVertices()) {
      for(Node<E> adjecent : vertex.getAdjacents()) {
        graphviz += "  " + vertex.getValue() + " -- " + adjecent.getValue() + "\n";
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

  public static <E> Graph<E> fromTrivialGraphFormat(String tgf, Function<String, E> mapper) {
    Graph<E> graph = new Graph<E>();

    String[] tgfParts = tgf.split("\n#\n");
    String[] nodes = tgfParts[0].split("\n");
    String[] edges = tgfParts[1].split("\n");

    // parse nodes
    for(String nodeLine : nodes) {
      String[] nodeParts = nodeLine.split(" ");
      Node<E> node = new Node<E>(mapper.apply(nodeParts[1]));
      graph.addVertex(node);
    }

    // parse edges
    for(String edge : edges) {
      String[] edgeParts = edge.split(" ");
      graph.addEdge(
        graph.getVertex(Integer.parseInt(edgeParts[0])),
        graph.getVertex(Integer.parseInt(edgeParts[1]))
      );
    }

    return graph;
  }
}