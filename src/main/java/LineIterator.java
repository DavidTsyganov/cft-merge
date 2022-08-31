import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LineIterator implements Iterator<String>, Closeable {
    private final BufferedReader bufferedReader;
    private String cashedLine;
    private boolean finished;

    public LineIterator(final Reader reader) throws IllegalArgumentException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader mustn't be null");
        }
        if (reader instanceof BufferedReader) {
            bufferedReader = (BufferedReader) reader;
        } else {
            bufferedReader = new BufferedReader(reader);
        }
    }

    public boolean hasNext() {
        if (cashedLine != null) {
            return true;
        }
        if (finished) {
            return false;
        }
        try {
            while (true) {
                final String line = bufferedReader.readLine();

                if (line == null) {
                    finished = true;
                    return false;
                }

                if (line.isEmpty()) {
                    finished = true;
                    return false;
                }

                if (isValidLine(line)) {
                    cashedLine = line;
                    return true;
                }
            }
        } catch (final IOException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException(e);
        }
    }
    protected boolean isValidLine(final String line) {
        if (line.contains(" ")) {
            return false;
        }
        return true;
    }

    public String next() {
        return nextLine();
    }
    public String nextLine() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        final String currentLine = cashedLine;
        cashedLine = null;
        return currentLine;
    }

    public void close() throws IOException {
        finished = true;
        cashedLine = null;
        bufferedReader.close();
    }
}
