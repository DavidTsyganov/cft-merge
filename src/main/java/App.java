
import utility.*;

import java.io.*;
import java.util.*;


public class App {
    public static void main(String[] args) throws IOException {
        List<String> inputFiles = ProcessInput.getInputFiles(args);

        String outputFile = ProcessInput.getOutputFile(args);

        List<String> flags = ProcessInput.getFlags(args);
        boolean sortingOrder = ProcessInput.getSortingOrder(flags);
        System.out.println("Order of sorting: " + sortingOrder);
        boolean inputType = ProcessInput.getInputType(flags);
        System.out.println("Type of input: " + inputType);

        List<LineIterator> listOfIterators = new LinkedList<>();
        File outputResultFile = new File(outputFile);
        FileWriter resultFileWriter = new FileWriter(outputResultFile);

        for (String inputFile : inputFiles) {
            File file = new File(inputFile);

            if (file.length() == 0) {
                continue;
            }

            Reader reader = new FileReader(file);
            LineIterator iterator = new LineIterator(reader);
            listOfIterators.add(iterator);
        }

        if (inputType) {
            if (sortingOrder) {
                IntAscComparator intAscComparator = new IntAscComparator();
                IntegerSorter integerSorter = new IntegerSorter(resultFileWriter, intAscComparator);
                IntegerSorter.merge(listOfIterators);
            } else {
                IntDescComparator intDescComparator = new IntDescComparator();
                IntegerSorter integerSorter = new IntegerSorter(resultFileWriter, intDescComparator);
                IntegerSorter.merge(listOfIterators);
            }
        } else {
            if (sortingOrder) {
                StrAscComparator strAscComparator = new StrAscComparator();
                StringSorter stringSorter = new StringSorter(resultFileWriter, strAscComparator);
                StringSorter.merge(listOfIterators);
            } else {
                StrDescComparator strDescComparator = new StrDescComparator();
                StringSorter stringSorter = new StringSorter(resultFileWriter, strDescComparator);
                StringSorter.merge(listOfIterators);
            }
        }

        resultFileWriter.close();

    }
}
