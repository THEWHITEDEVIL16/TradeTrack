import java.util.ArrayList;
import java.util.List;

public class JournalManager {

    private ArrayList<Trade> trades;

    public JournalManager() {
        this.trades = new ArrayList<>();
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
        System.out.println("\n  Trade logged successfully!");
    }

    public ArrayList<Trade> getAllTrades() { return trades; }

    public void setTrades(ArrayList<Trade> trades) { this.trades = trades; }

    public double getTotalPnL() {
        double total = 0;
        for (Trade t : trades) total += t.getPnL();
        return total;
    }

    public int getWinCount() {
        int count = 0;
        for (Trade t : trades) if (t.isProfitable()) count++;
        return count;
    }

    public double getWinRate() {
        if (trades.isEmpty()) return 0;
        return (getWinCount() * 100.0) / trades.size();
    }

    public Trade getBestTrade() {
        if (trades.isEmpty()) return null;
        Trade best = trades.get(0);
        for (Trade t : trades) if (t.getPnL() > best.getPnL()) best = t;
        return best;
    }

    public Trade getWorstTrade() {
        if (trades.isEmpty()) return null;
        Trade worst = trades.get(0);
        for (Trade t : trades) if (t.getPnL() < worst.getPnL()) worst = t;
        return worst;
    }

    public List<Trade> getTradesByStrategy(String strategy) {
        List<Trade> filtered = new ArrayList<>();
        for (Trade t : trades)
            if (t.getStrategy().equalsIgnoreCase(strategy)) filtered.add(t);
        return filtered;
    }

    public void printSummary() {
        if (trades.isEmpty()) {
            System.out.println("  No trades logged yet.");
            return;
        }
        int losses = trades.size() - getWinCount();
        System.out.println("\n  ==========================================");
        System.out.println("             JOURNAL SUMMARY               ");
        System.out.println("  ==========================================");
        System.out.printf( "  Total Trades  : %d%n", trades.size());
        System.out.printf( "  Total PnL     : %.2f%n", getTotalPnL());
        System.out.printf( "  Win Rate      : %.1f%%%n", getWinRate());
        System.out.printf( "  Wins / Losses : %d / %d%n", getWinCount(), losses);
        if (getBestTrade() != null)
            System.out.printf("  Best Trade    : %s (+%.2f)%n",
                getBestTrade().getSymbol(), getBestTrade().getPnL());
        if (getWorstTrade() != null)
            System.out.printf("  Worst Trade   : %s (%.2f)%n",
                getWorstTrade().getSymbol(), getWorstTrade().getPnL());
        System.out.println("  ==========================================");
    }
}