import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    public List read(List<String> inputFiles) {
        List<String> list = new ArrayList<>();

        for (String str : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(str))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Input file isn't found.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error occurred while reading input data.");
                e.printStackTrace();
           }
        }
        return list;
    }
}


//TODO:
// - Посмотреть, что можно сделать с исключениями