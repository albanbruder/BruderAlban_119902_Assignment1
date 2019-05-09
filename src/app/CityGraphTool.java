package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class CityGraphTool {

  public static void main(String[] args) throws IOException {
    System.out.println(args[0]);
    
    String tgf = new String(Files.readAllBytes(Paths.get(args[0])));
    Graph<City> graph = Graph.<City>fromTrivialGraphFormat(tgf, (String s) -> new City(s));

    // generate graphviz dot format
    String graphviz = graph.toGraphviz();
    System.out.print(graphviz);
  }
}