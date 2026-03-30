import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Fetches live Indian stock prices using Yahoo Finance
// No API key needed — completely free
public class StockAPI {

    // Yahoo Finance base URL
    private static final String BASE_URL =
        "https://query1.finance.yahoo.com/v8/finance/chart/";

    // Returns live price of a stock, or -1 if it fails
    public static double getLivePrice(String symbol) {
        try {
            // Yahoo Finance URL e.g. .../RELIANCE.NS?interval=1d
            String urlStr = BASE_URL + symbol + "?interval=1d&range=1d";
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            // Yahoo needs a browser-like header or it blocks the request
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return extractPrice(response.toString());

        } catch (Exception e) {
            System.out.println("  Could not fetch live price: " + e.getMessage());
            return -1;
        }
    }

    // Extracts the current price from Yahoo Finance JSON response
    private static double extractPrice(String json) {
        // Yahoo returns: "regularMarketPrice":{"raw":2450.75,...}
        String key = "\"regularMarketPrice\":{\"raw\":";
        int start = json.indexOf(key);
        if (start == -1) return -1;

        start += key.length();
        int end = json.indexOf(",", start);
        if (end == -1) return -1;

        String priceStr = json.substring(start, end).trim();
        return Double.parseDouble(priceStr);
    }

    // Prints live price nicely in the console
    public static void printLivePrice(String symbol) {
        System.out.println("\n  Fetching live price for " + symbol + "...");
        double price = getLivePrice(symbol);
        if (price == -1) {
            System.out.println("  Failed. Check symbol format or internet connection.");
            System.out.println("  Tip: Use RELIANCE.NS for NSE or RELIANCE.BO for BSE");
        } else {
            System.out.printf("  Live Price of %-15s : Rs. %.2f%n", symbol, price);
        }
    }
}