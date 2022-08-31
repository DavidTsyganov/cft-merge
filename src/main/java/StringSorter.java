import java.io.*;
import java.util.*;

public class StringSorter {
    private static FileWriter fileWriter;
    private static Comparator comparator;

    public StringSorter(final FileWriter fileWriter, final Comparator comparator) {
        this.fileWriter = fileWriter;
        this.comparator = comparator;
    }

    public static void merge(List<LineIterator> listOfIterators) throws IOException {
        List<Iterator> iteratorList = new ArrayList<>();

        if (listOfIterators.size() == 1) {
            while (listOfIterators.get(0).hasNext()) {
                fileWriter.write(listOfIterators.get(0).next());
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
            return;
        }

        LinkedList<File> resultFiles = new LinkedList<>();
        int filesProcessed = 0;
        int i = 0;

        while (filesProcessed < listOfIterators.size()) {
            File tempFile = new File("src/main/java/temp_file.txt");
            Reader tempFileReader = new FileReader(tempFile);
            LineIterator tempFileIter = new LineIterator(tempFileReader);

            if (listOfIterators.get(filesProcessed) == null) {
                continue;
            }

            if (filesProcessed == 0) {
                mergeTwoFiles(listOfIterators.get(filesProcessed++), listOfIterators.get(filesProcessed++), comparator, tempFile);
                resultFiles.add(tempFile);
            } else {
                mergeTwoFiles(tempFileIter, listOfIterators.get(filesProcessed++), comparator, tempFile);
                resultFiles.add(tempFile);
            }
        }

        File resultFile = resultFiles.getLast();
        Reader resultReader = new FileReader(resultFile);
        LineIterator resultIterator = new LineIterator(resultReader);

        while (resultIterator.hasNext()) {
            fileWriter.write(resultIterator.next());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    public static <E extends Comparable<E>> File mergeTwoFiles(LineIterator iterator1,
                                                               LineIterator iterator2,
                                                               Comparator<String> comparator, File file) throws IOException {

        String value1 = iterator1.hasNext() ? String.valueOf(iterator1.next()) : null;
        String value2 = iterator2.hasNext() ? String.valueOf(iterator2.next()) : null;

        FileWriter localFileWriter = new FileWriter(file);

        while (value1 != null || value2 != null) {
            if (value2 == null || (value1 != null && comparator.compare(value1, value2) <= 0)) {

                localFileWriter.write(String.valueOf(value1));
                localFileWriter.write(System.lineSeparator());
                value1 = (iterator1.hasNext() ? String.valueOf(iterator1.next()) : null);
            } else {
                localFileWriter.write(String.valueOf(value2));
                localFileWriter.write(System.lineSeparator());
                value2 = (iterator2.hasNext() ? String.valueOf(iterator2.next()) : null);
            }
        }
        localFileWriter.close();
        return file;
    }
}
