package app;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Class representing a city.
 * 
 * @author Alban Bruder
 */
class City {

  /** The name of the city. */
  private String name;

  /** Information about the city. */
  private HashMap<String, String> meta = new HashMap<String, String>();

  /**
   * Create an instance if City.
   * 
   * @param name The name of the city.
   */
  public City(String name) {
    setName(name);
  }

  /**
   * Sets the name of the city.
   * 
   * @param name The name of the city.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the city.
   */
  public String getName() {
    return name;
  }

  /**
   * Adds information about the city.
   * 
   * @param key 
   * @param value
   */
  public void addMeta(String key, String value) {
    meta.put(key, value);
  }

  /**
   * Get all information about the city.
   * 
   * @return informations about the city
   */
  public HashMap<String, String> getMeta() {
    return meta;
  }

  /**
   * Get a single information about the city.
   * 
   * @param key
   * @return an information or null if the key does not exist
   */
  public String getMeta(String key) {
    return meta.get(key);
  }

  /**
   * Returns the name of the city.
   */
  public String toString() {
    return name;
  }

  /**
   * Returns a graphviz label with all information about the city.
   * 
   * @return graphviz label
   */
  public String toGraphvizLabel() {
    String label = "<<b>"+name+"</b>";

    for(Entry<String, String> entry : meta.entrySet()) {
      label += "<br/>" + entry.getKey() + ": " + entry.getValue();
    }

    return label + ">";
  }
}