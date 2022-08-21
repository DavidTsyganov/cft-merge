import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {
    public void write(List list, String outputFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < list.size(); i++) {
                bufferedWriter.write(list.get(i) + "\n");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing output data occurred. ");
            e.printStackTrace();
        }
    }
}

//TODO:
// - Посмотреть, что можно сделать с исключениями
// - \n в строке 10. Для чего нужно?
