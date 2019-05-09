package app;

import java.nio.file.Files;
import java.nio.file.Paths;

import picocli.CommandLine;

/**
 * Commandlineutility for creating city graphs.
 */
public class App {
    public static void main(String[] args) throws Exception {
        // parse args with picocli
        AppOptions options = CommandLine.populateCommand(new AppOptions(), args);

        if (options.usageHelpRequested) {
            CommandLine.usage(new AppOptions(), System.out);
            return;
        }

        // if not file is specified, start interactive mode
        if (options.file == null) {
            new InteractiveMode(System.in);
            return;
        }
        
        // read tgf file from specified filepath
        String tgf = new String(Files.readAllBytes(Paths.get(options.file.getAbsolutePath())));

        // create graph from loaded file
        Graph<City> graph = Graph.<City>fromTrivialGraphFormat(
            tgf, 
            (String name, String meta) -> parseCity(name, meta)
        );

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

    /**
     * Create City from custom tgf label format.
     * 
     * @param name
     * @param label
     * @return City
     */
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
}