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

    public static void merge(LinkedList<LineIterator> listOfIterators) throws IOException {
        List<Iterator> iteratorList = new ArrayList<>();

        // Если список содержит только один итератор, то просто копируем содержимое в result.txt (потому что все уже отсортировано)
        if (listOfIterators.size() == 1) {
            File resultFile = new File("final-result-file.txt");
            fileWriter.write(String.valueOf(listOfIterators.get(0)));
        }

        LinkedList<File> resultFiles = new LinkedList<>();
        int filesProcessed = 0;
        int i = 0;
        while (filesProcessed < listOfIterators.size()) {



            File file = new File("interim_file" + i + ".txt");
            if (filesProcessed == 0) {
                file = mergeSortedLists(listOfIterators.get(filesProcessed++), listOfIterators.get(filesProcessed++), comparator);
            } else {
                file = mergeSortedLists(listOfIterators.getLast(), listOfIterators.get(filesProcessed++), comparator);
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

    public static <E extends Comparable<E>> File mergeSortedLists(LineIterator iterator1,
                                                                  LineIterator iterator2,
                                        Comparator<Integer> comparator) throws IOException {
        //
        Integer value1 = Integer.valueOf(iterator1.hasNext() ? iterator1.next() : null);
        Integer value2 = Integer.valueOf(iterator2.hasNext() ? iterator2.next() : null);

        File file = new File("interim_file.txt");

        FileWriter localFileWriter = new FileWriter(file);

        // Получем строки из файлов, пока в них есть значения
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
        fileWriter.close();

        return file;
    }
}
