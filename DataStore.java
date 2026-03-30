import java.io.*;
import java.util.ArrayList;

public class DataStore {

    private static final String FILE_NAME = "trades.csv";

    public static void saveToFile(ArrayList<Trade> trades) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("id,symbol,quantity,buyPrice,sellPrice,date,strategy,notes");
            for (Trade t : trades) writer.println(t.toCSV());
            System.out.println("  Trades saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("  Error saving: " + e.getMessage());
        }
    }

    public static ArrayList<Trade> loadFromFile() {
        ArrayList<Trade> trades = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return trades;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { isFirstLine = false; continue; }
                String[] parts = line.split(",", 8);
                if (parts.length < 8) continue;
                Trade t = new Trade(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    Integer.parseInt(parts[2]),
                    Double.parseDouble(parts[3]),
                    Double.parseDouble(parts[4]),
                    parts[5], parts[6], parts[7]
                );
                trades.add(t);
            }
            if (!trades.isEmpty())
                System.out.println("  Loaded " + trades.size() + " saved trade(s).");
        } catch (IOException e) {
            System.out.println("  Error loading file: " + e.getMessage());
        }
        return trades;
    }
}