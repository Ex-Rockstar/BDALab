import java.io.*;
import java.util.*;

public class WordCountLocal {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java WordCountLocal <input_file_path>");
            return;
        }

        String filePath = args[0];
        Map<String, Integer> wordCounts = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.toLowerCase().replaceAll("[^a-z ]", " "); // remove punctuation, lowercase
            String[] words = line.split("\\s+"); // split by spaces

            for (String word : words) {
                if (!word.isEmpty()) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        }

        reader.close();

        // Sort and print the result
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(wordCounts.entrySet());
        sorted.sort(Map.Entry.comparingByKey());

        System.out.println("Word Count:");
        for (Map.Entry<String, Integer> entry : sorted) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
