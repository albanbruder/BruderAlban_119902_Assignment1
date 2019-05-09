package app;

import java.io.File;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class AppOptions {
    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    public boolean helpRequested = false;

    @Parameters(paramLabel = "FILE", description = "one ore more files to archive")
    File file;
}