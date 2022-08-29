import java.io.*;
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

    public static void merge(LinkedList<LineIterator> listOfIterators) throws IOException {
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
            File tempFile = new File(UUID.randomUUID().toString());
            if (filesProcessed == 0) {
                tempFile = mergeTwoFiles(listOfIterators.get(filesProcessed++), listOfIterators.get(filesProcessed++), comparator, tempFile);
            } else {
                tempFile = mergeTwoFiles(listOfIterators.getLast(), listOfIterators.get(filesProcessed++), comparator, tempFile);
            }
            resultFiles.add(tempFile);
        }

        File finalResultFile = resultFiles.removeLast();
        resultFiles.forEach(File::delete);
        // Переделать потом
        Reader resultReader = new FileReader(finalResultFile) {
        };

        FileWriter resultFileWriter = new FileWriter(finalResultFile);
        LineIterator resultIterator = new LineIterator(resultReader);

        while (resultIterator.hasNext()) {
            resultFileWriter.write(resultIterator.next());
            resultFileWriter.write(System.lineSeparator());
        }

        finalResultFile.renameTo(new File("final-result-file.txt"));
    }

    public static <E extends Comparable<E>> File mergeTwoFiles(LineIterator iterator1,
                                                                  LineIterator iterator2,
                                        Comparator<Integer> comparator, File file) throws IOException {

        Integer value1 = Integer.valueOf(iterator1.hasNext() ? iterator1.next() : null);
        Integer value2 = Integer.valueOf(iterator2.hasNext() ? iterator2.next() : null);

        FileWriter localFileWriter = new FileWriter(file);

        // Получим строки из файлов, пока в них есть значения
        while (value1 != null || value2 != null) {
            if (value2 == null || (value1 != null && comparator.compare(value1, value2) <= 0)) {

                localFileWriter.write(String.valueOf(value1));
                localFileWriter.write(System.lineSeparator());
                value1 = (iterator1.hasNext() ? Integer.valueOf(iterator1.next()) : null);
            } else {
                localFileWriter.write(String.valueOf(value2));
                localFileWriter.write(System.lineSeparator());
                value2 = (iterator2.hasNext() ? Integer.valueOf(iterator2.next()) : null);
            }
        }
        localFileWriter.close();
        return file;
    }
}
