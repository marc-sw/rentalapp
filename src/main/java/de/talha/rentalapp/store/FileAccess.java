package de.talha.rentalapp.store;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileAccess {

    private final File file;

    public FileAccess(File file) {
        this.file = file;
    }

    private void write(String content, boolean append) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(List<String> lines) {
        String content = String.join(System.lineSeparator(), lines) + System.lineSeparator();
        write(content, false);
    }

    public void clearFile() {
        write("", false);
    }

    public List<String> readLines() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return reader.lines().collect(Collectors.toCollection(ArrayList::new));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void appendLine(String content) {
        write(content + System.lineSeparator(), true);
    }

    public void removeLine(int lineIndex) {
        List<String> lines = readLines();
        lines.remove(lineIndex);
        write(lines);
    }

    public void editLine(int lineIndex, String content)  {
        List<String> lines = readLines();
        lines.set(lineIndex, content);
        write(lines);
    }
}
