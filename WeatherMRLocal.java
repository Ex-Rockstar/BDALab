import java.io.*;
import java.util.*;

public class WeatherMRLocal {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java WeatherMRLocal <input_file_path>");
            return;
        }

        String filePath = args[0];
        Map<String, Integer> maxTempsByYear = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+"); // split by space
            if (parts.length != 2) continue;

            String date = parts[0]; // e.g., 2012-06-01
            String year = date.substring(0, 4); // get "2012"

            int temp;
            try {
                temp = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                continue; // skip invalid lines
            }

            // Reducer logic: max temperature per year
            int currentMax = maxTempsByYear.getOrDefault(year, Integer.MIN_VALUE);
            if (temp > currentMax) {
                maxTempsByYear.put(year, temp);
            }
        }

        reader.close();

        // Print results
        System.out.println("Max Temperature Per Year:");
        for (String year : maxTempsByYear.keySet()) {
            System.out.println(year + " : " + maxTempsByYear.get(year) + "°C");
        }
    }
}
