package utility;

import java.util.ArrayList;
import java.util.List;

public class ProcessInput {
    public static final List<String> FLAGS = List.of("-a", "-d", "-i", "-s");

    public static List<String> getFlags(String[] args) {
        List<String> flags = new ArrayList<>();

        for (String str : args) {
            if (FLAGS.contains(str)) {
                flags.add(str);
            }
        }
        return flags;
    }

    public static boolean getSortingOrder(List<String> flags) {
        for (String flag : flags) {
            if (flag.equals("-d")) {
                return false;
            }
        }
        return true;
    }

    public static boolean getInputType(List<String> flags) {
        for (String flag : flags) {
            if (flag.equals("-i")) {
                return true;
            }
            if (flag.equals("-s")) {
                return false;
            }
        }
        return false;
    }

    public static String getOutputFile(String[] args) {
        String outputFile = null;

        for (String str : args) {
            if (str.endsWith(".txt")) {
                outputFile = str;
                break;
            }
        }

        return outputFile;
    }

    public static List<String> getInputFiles(String[] args) {
        List<String> inputFiles = new ArrayList<>();
        for (int i = 0; i > args.length; i++) {
            if (args[i].endsWith(".txt")) {
                inputFiles.add(args[i]);
            }
            inputFiles.remove(0);
        }
        return inputFiles;
    }
}
