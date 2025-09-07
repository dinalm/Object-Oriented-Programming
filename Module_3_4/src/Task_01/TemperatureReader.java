package Task_01;

import java.io.*;
import java.net.*;
import java.util.*;

public class TemperatureReader {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://users.metropolia.fi/~jarkkov/temploki.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String headerLine = reader.readLine();
            String[] headers = headerLine.split(";");

            int ulkoTaloIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equalsIgnoreCase("UlkoTalo")) {
                    ulkoTaloIndex = i;
                    break;
                }
            }

            List<Double> temperatures = new ArrayList<>();
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null && lineCount < 144) {
                String[] fields = line.split(";");

                if (fields.length > ulkoTaloIndex) {
                    String tempStr = fields[ulkoTaloIndex].trim();
                    try {
                        tempStr = tempStr.replace(',', '.');
                        double temperature = Double.parseDouble(tempStr);
                        temperatures.add(temperature);
                    } catch (NumberFormatException e) {
                        // Skip invalid values
                    }
                }
                lineCount++;
            }

            reader.close();

            if (!temperatures.isEmpty()) {
                double sum = 0;
                for (double temp : temperatures) {
                    sum += temp;
                }
                double average = sum / temperatures.size();
                System.out.printf("Average temperature for January 1st, 2023: %.2f°C", average);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}