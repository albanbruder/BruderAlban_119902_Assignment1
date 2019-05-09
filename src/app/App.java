package app;

import java.nio.file.Files;
import java.nio.file.Paths;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) throws Exception {
        AppOptions options = CommandLine.populateCommand(new AppOptions(), args);
        
        String tgf = new String(Files.readAllBytes(Paths.get(options.file.getAbsolutePath())));
        Graph<City> graph = Graph.<City>fromTrivialGraphFormat(tgf, (String s) -> new City(s));

        // generate graphviz dot format
        String graphviz = graph.toGraphviz();
        System.out.print(graphviz);
    }
}