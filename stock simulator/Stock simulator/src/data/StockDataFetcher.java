package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockDataFetcher {

    private static final String API_URL = "https://www.alphavantage.co/query?";
    private final String apiKey;  // Store the API key

    // Constructor to initialize the class with the API key
    public StockDataFetcher(String apiKey) {
        this.apiKey = apiKey;
    }

    // Method to fetch stock data for a given symbol (like "AAPL")
    public String fetchStockData(String symbol) {
        StringBuilder result = new StringBuilder();
        try {
            // Build the URL to call the API with symbol and API key
            String endpoint = API_URL + "function=TIME_SERIES_INTRADAY&symbol=" + symbol
                    + "&interval=5min&apikey=" + this.apiKey;

            // Create a URL object from the endpoint
            URL url = new URL(endpoint);

            // Open a connection and set the request method to GET
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response from the API
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);  // Append the response to the result
            }
            rd.close();  // Close the reader
        } catch (Exception e) {
            e.printStackTrace();  // Print any errors if they occur
        }

        // Return the full API response as a string
        return result.toString();
    }

    // Main method to test the API fetch
    public static void main(String[] args) {
        String apiKey = "";  // Replace with your actual API key
        StockDataFetcher fetcher = new StockDataFetcher(apiKey);  // Create an object with the API key

        String stockData = fetcher.fetchStockData("AAPL");  // Fetch data for Apple stock (AAPL)
        System.out.println(stockData);  // Print the raw data to the console
    }
}
