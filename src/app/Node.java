package app;

import java.util.*;

/**
 * A generic representation of a graph node.
 * 
 * @param <E> The value type.
 */
class Node<E> {

  /** Value of the node. */
  private E value;

  /** List of adjecent nodes. */
  private ArrayList<Node<E>> adjacents = new ArrayList<Node<E>>();

  /**
   * Create an instance of Node.
   * 
   * @param value Value of the node
   */
  public Node(E value) {
    this.value = value;
  }

  /**
   * Set the value of the node.
   */
  public void setValue(E value) {
    this.value = value;
  }

  /**
   * Returns the value of the node.
   * 
   * @return value of the node
   */
  public E getValue() {
    return value;
  }

  /**
   * Adds an adjecent node.
   * 
   * @param adjacent an adjecent node
   */
  public void addAdjacent(Node<E> adjacent) {
    adjacents.add(adjacent);
  }

  /**
   * Remove an adjecent node.
   */
  public void removeAdjacent(Node<E> adjacent) {
    adjacents.remove(adjacent);
  }

  /**
   * Returns all adjecent nodes.
   * 
   * @return all adjecent nodes
   */
  public ArrayList<Node<E>> getAdjacents() {
    return adjacents;
  }

}