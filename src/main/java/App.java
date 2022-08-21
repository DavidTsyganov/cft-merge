import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


@Command(name = "sort-it.exe", mixinStandardHelpOptions = true, version = "sorter 1.0",
        description = "Sorts files.")
public class App implements Callable<Integer> {
    String[] inputFiles; // At least 1 input file
    String outputFile; // Name of the output file
    String sortType; // Ascending or Descending
    String dataType; // String or Integer

//    @Parameters(index = "0", description = "The output file")
//    private String outputFile;
//
//    @Option(names = "-a, -d")
//    String sortingOrder = "ascending";
//    @Option(names = "-d")
//    sortingOrder = "descending";




    @Override
    public Integer call() throws Exception {
        return null;
    }

    public static void main(String[] args) {

    }
}
