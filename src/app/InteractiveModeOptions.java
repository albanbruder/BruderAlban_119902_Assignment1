package app;

import picocli.CommandLine.Option;

enum OutputFormat {
    tgf, graphviz
}

public class InteractiveModeOptions {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean usageHelpRequested;
}