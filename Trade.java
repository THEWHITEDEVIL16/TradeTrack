public class Trade {

    private static int idCounter = 1;

    private int id;
    private String symbol;
    private int quantity;
    private double buyPrice;
    private double sellPrice;
    private String date;
    private String strategy;
    private String notes;

    public Trade(String symbol, int quantity, double buyPrice, double sellPrice,
                 String date, String strategy, String notes) {
        this.id        = idCounter++;
        this.symbol    = symbol.toUpperCase();
        this.quantity  = quantity;
        this.buyPrice  = buyPrice;
        this.sellPrice = sellPrice;
        this.date      = date;
        this.strategy  = strategy;
        this.notes     = notes;
    }

    public Trade(int id, String symbol, int quantity, double buyPrice, double sellPrice,
                 String date, String strategy, String notes) {
        this.id        = id;
        this.symbol    = symbol;
        this.quantity  = quantity;
        this.buyPrice  = buyPrice;
        this.sellPrice = sellPrice;
        this.date      = date;
        this.strategy  = strategy;
        this.notes     = notes;
        if (id >= idCounter) idCounter = id + 1;
    }

    public double  getPnL()           { return (sellPrice - buyPrice) * quantity; }
    public boolean isProfitable()     { return getPnL() > 0; }
    public double  getReturnPercent() { return ((sellPrice - buyPrice) / buyPrice) * 100; }
    public int     getId()            { return id; }
    public String  getSymbol()        { return symbol; }
    public int     getQuantity()      { return quantity; }
    public double  getBuyPrice()      { return buyPrice; }
    public double  getSellPrice()     { return sellPrice; }
    public String  getDate()          { return date; }
    public String  getStrategy()      { return strategy; }
    public String  getNotes()         { return notes; }

    @Override
    public String toString() {
        String outcome = isProfitable() ? "WIN" : "LOSS";
        return String.format(
            "[#%d] %-10s | %-5s | Qty: %-4d | Buy: %7.2f | Sell: %7.2f | PnL: %8.2f (%+.1f%%) | %s",
            id, symbol, outcome, quantity, buyPrice, sellPrice,
            getPnL(), getReturnPercent(), strategy
        );
    }

    public String toCSV() {
        return id + "," + symbol + "," + quantity + "," + buyPrice + ","
             + sellPrice + "," + date + "," + strategy + ","
             + notes.replace(",", ";");
    }
}