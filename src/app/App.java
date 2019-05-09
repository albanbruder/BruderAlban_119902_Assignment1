package app;

import java.nio.file.Files;
import java.nio.file.Paths;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) throws Exception {
        AppOptions options = CommandLine.populateCommand(new AppOptions(), args);

        if (options.usageHelpRequested) {
            CommandLine.usage(new AppOptions(), System.out);
            return;
        }

        if (options.file == null) {
            startInteractiveMode();
            return;
        }
        
        String tgf = new String(Files.readAllBytes(Paths.get(options.file.getAbsolutePath())));
        Graph<City> graph = Graph.<City>fromTrivialGraphFormat(tgf, (String name, String meta) -> parseCity(name, meta));

        if(options.outputFormat == OutputFormat.tgf) {
            // generate tgf format
            String out = graph.toTrivialGraphFormat();
            System.out.print(out);
        } else {
            // generate graphviz dot format
            String graphviz = graph.toGraphviz((City city) -> city.toGraphvizLabel());
            System.out.print(graphviz);
        }
    }

    private static City parseCity(String name, String label) {
        City city = new City(name);

        if(label.length() == 0) {
            return city;
        }

        for(String meta : label.split(",")) {
            String[] parts = meta.split("=", 2);
            city.addMeta(parts[0], parts[1]);
        }
        return city;
    }

    public static void startInteractiveMode() {
        System.out.println("Interactive Mode:\n");
    }
}