package app;

import java.util.HashMap;
import java.util.Map.Entry;

class City {
  private String name;
  private HashMap<String, String> meta = new HashMap<String, String>();

  public City(String name) {
    setName(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addMeta(String key, String value) {
    meta.put(key, value);
  }

  public HashMap<String, String> getMeta() {
    return meta;
  }

  public String getMeta(String key) {
    return meta.get(key);
  }

  public String toString() {
    return name;
  }

  public String toGraphvizLabel() {
    String label = "<<b>"+name+"</b>";

    for(Entry<String, String> entry : meta.entrySet()) {
      label += "<br/>" + entry.getKey() + ": " + entry.getValue();
    }

    return label + ">";
  }
}