import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    static Scanner sc = new Scanner(System.in);
    static JournalManager journal = new JournalManager();

    public static void main(String[] args) {

        journal.setTrades(DataStore.loadFromFile());

        System.out.println("\n  ==========================================");
        System.out.println("       TradeTrack - Trading Journal         ");
        System.out.println("            by [Your Name]                  ");
        System.out.println("  ==========================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("  Enter choice: ");

            switch (choice) {
                case 1: addTrade();                              break;
                case 2: viewAllTrades();                         break;
                case 3: journal.printSummary();                  break;
                case 4: filterByStrategy();                      break;
                case 5: DataStore.saveToFile(journal.getAllTrades()); break;
                case 6:
                    DataStore.saveToFile(journal.getAllTrades());
                    System.out.println("\n  Goodbye! Trade smart.\n");
                    running = false;
                    break;
                default:
                    System.out.println("  Invalid option. Enter 1 to 6.");
            }
        }
        sc.close();
    }

    static void printMenu() {
        System.out.println("\n  +--------------------------+");
        System.out.println("  |  1. Log a Trade          |");
        System.out.println("  |  2. View All Trades      |");
        System.out.println("  |  3. Summary & Stats      |");
        System.out.println("  |  4. Filter by Strategy   |");
        System.out.println("  |  5. Save Trades          |");
        System.out.println("  |  6. Exit                 |");
        System.out.println("  +--------------------------+");
    }

    static void addTrade() {
        System.out.println("\n  --- LOG NEW TRADE ---");

        System.out.print("  Stock Symbol (e.g. RELIANCE, TCS): ");
        String symbol = sc.nextLine().trim();

        int qty       = getIntInput("  Quantity (number of shares): ");
        double buy    = getDoubleInput("  Buy Price (e.g. 2450.50): ");
        double sell   = getDoubleInput("  Sell Price (e.g. 2600.00): ");

        System.out.print("  Date (DD-MM-YYYY): ");
        String date   = sc.nextLine().trim();

        System.out.print("  Strategy (Breakout / Swing / Scalping / BTST): ");
        String strategy = sc.nextLine().trim();

        System.out.print("  Notes (press Enter to skip): ");
        String notes  = sc.nextLine().trim();
        if (notes.isEmpty()) notes = "-";

        Trade t = new Trade(symbol, qty, buy, sell, date, strategy, notes);
        journal.addTrade(t);
        System.out.println("\n  " + t);
    }

    static void viewAllTrades() {
        ArrayList<Trade> trades = journal.getAllTrades();
        if (trades.isEmpty()) {
            System.out.println("\n  No trades yet. Use option 1 to log one.");
            return;
        }
        System.out.println("\n  --- ALL TRADES (" + trades.size() + " total) ---");
        for (Trade t : trades) {
            System.out.println("\n  " + t);
            System.out.println("  Date: " + t.getDate() + "  |  Notes: " + t.getNotes());
            System.out.println("  --------------------------------------------------");
        }
    }

    static void filterByStrategy() {
        System.out.print("\n  Enter strategy to filter (e.g. Swing): ");
        String strategy = sc.nextLine().trim();
        List<Trade> filtered = journal.getTradesByStrategy(strategy);
        if (filtered.isEmpty()) {
            System.out.println("  No trades found for: " + strategy);
            return;
        }
        System.out.println("\n  --- " + strategy.toUpperCase() + " TRADES ---");
        for (Trade t : filtered) System.out.println("  " + t);
        System.out.println("  Total: " + filtered.size() + " trade(s).");
    }

    static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Enter a valid whole number.");
            }
        }
    }

    static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Enter a valid number (e.g. 2450.50).");
            }
        }
    }
}