package app;

class City {
  private String name;

  public City(String name) {
    setName(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return name;
  }
}