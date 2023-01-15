package cs445.rec11;

import java.util.List;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvReader {

    public static List<String> readIn(String filename) {

        List<String> allData = null;
        try {
            allData = Files.readAllLines(Paths.get("build/resources/main/" + filename), Charset.defaultCharset());
        } catch(IOException e) {
            System.err.println("Error reading " + filename);
            e.printStackTrace();
            return null;
        }
        // return all the data of the read file as one long string
        return allData;

    }

}

