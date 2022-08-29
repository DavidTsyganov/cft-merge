import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Sorter {

    // FileWriter, который хранит путь, куда записываем ИТОГОВЫЙ файл
    private static FileWriter fileWriter;
    private static Comparator comparator;

    public Sorter(final FileWriter fileWriter, final Comparator comparator) {
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
        }

        LinkedList<File> resultFiles = new LinkedList<>();
        int filesProcessed = 0;
        int i = 0;

        while (filesProcessed < listOfIterators.size()) {
            File tempFile1 = new File("src/main/java/temp_file1.txt");
            File tempFile2 = new File("src/main/java/temp_file2.txt");

            FileWriter localFileWriter1 = new FileWriter(tempFile1);
            FileWriter localFileWriter2 = new FileWriter(tempFile2);

            if (filesProcessed == 0) {
                while ()
                localFileWriter1.write(mergeTwoFiles(listOfIterators.get(filesProcessed++), listOfIterators.get(filesProcessed++), comparator));
            } else {
                file = mergeTwoFiles(listOfIterators.getLast(), listOfIterators.get(filesProcessed++), comparator);
            }
            resultFiles.add(file);
            i++;
            String fileAsString = Files.readString(file.toPath());
            System.out.println(fileAsString);
            System.out.println(System.lineSeparator());
        }

        File finalResultFile = resultFiles.removeLast();
        finalResultFile.renameTo(new File("final-result-file.txt"));
    }

    public static <E extends Comparable<E>> void mergeTwoFiles(LineIterator iterator1,
                                                                  LineIterator iterator2,
                                        Comparator<Integer> comparator) throws IOException {

        Integer value1 = Integer.valueOf(iterator1.hasNext() ? iterator1.next() : null);
        Integer value2 = Integer.valueOf(iterator2.hasNext() ? iterator2.next() : null);

        // Получим строки из файлов, пока в них есть значения
        while (value1 != null || value2 != null) {
            if (value2 == null || (value1 != null && comparator.compare(value1, value2) <= 0)) {

                fileWriter.write(String.valueOf(value1));
                fileWriter.write(System.lineSeparator());
                value1 = (iterator1.hasNext() ? Integer.valueOf(iterator1.next()) : null);
            } else {
                fileWriter.write(String.valueOf(value2));
                fileWriter.write(System.lineSeparator());
                value2 = (iterator2.hasNext() ? Integer.valueOf(iterator2.next()) : null);
            }
        }
        fileWriter.close();
    }
}
