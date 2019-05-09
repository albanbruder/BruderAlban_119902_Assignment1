package app;

import java.util.*;

class Node<E> {

  private E value;
  private ArrayList<Node<E>> adjacents = new ArrayList<Node<E>>();

  public Node(E value) {
    this.value = value;
  }

  public void setValue(E value) {
    this.value = value;
  }

  public E getValue() {
    return value;
  }

  public void addAdjacent(Node<E> adjacent) {
    adjacents.add(adjacent);
  }

  public void removeAdjacent(Node<E> adjacent) {
    adjacents.remove(adjacent);
  }

  public ArrayList<Node<E>> getAdjacents() {
    return adjacents;
  }

}