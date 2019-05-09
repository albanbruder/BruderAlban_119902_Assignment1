package app;

import java.io.File;

import picocli.CommandLine.Option;

enum OutputFormat {
    tgf, graphviz
}

public class AppOptions {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean usageHelpRequested;
    
    @Option(names = { "-i", "--input" }, description = "a file to convert")
    File file;

    @Option(names = { "-t", "--type" }, description = "the output format. Valid values: ${COMPLETION-CANDIDATES} (default: ${DEFAULT-VALUE})")
    public OutputFormat outputFormat = OutputFormat.graphviz;
}