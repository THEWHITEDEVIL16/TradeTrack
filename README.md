# TradeTrack - Trading Journal

A console-based trading journal built in Java for traders who want to
log, track, and analyze their stock market trades with live price data.

## What it does

TradeTrack helps retail traders solve a real problem — most beginners
never review their trades, so they keep repeating the same mistakes.
This app gives you a structured journal to record every trade, see your
actual performance stats, and check live NSE/BSE prices without leaving
the terminal.

## Features

- Log trades with symbol, quantity, buy/sell price, strategy and notes
- Auto-fetch live stock price from Yahoo Finance (NSE/BSE supported)
- Automatic P&L and return percentage calculation per trade
- Win rate, total P&L, best and worst trade summary
- Filter trades by strategy (Swing, Breakout, Scalping, BTST etc.)
- Save and load trades from a CSV file — your data persists across sessions
- Check live price of any NSE/BSE stock on demand

## Live Price — Stock Symbol Format

| Stock        | NSE Symbol       | BSE Symbol       |
|--------------|------------------|------------------|
| Reliance     | RELIANCE.NS      | RELIANCE.BO      |
| TCS          | TCS.NS           | TCS.BO           |
| Infosys      | INFY.NS          | INFY.BO          |
| HDFC Bank    | HDFCBANK.NS      | HDFCBANK.BO      |
| Nifty 50     | ^NSEI            | —                |
| Sensex       | ^BSESN           | —                |

Add `.NS` for NSE stocks, `.BO` for BSE stocks.

## How to Run

### Prerequisites
- Java JDK 8 or above
- Internet connection (for live price feature)

### Steps
```bash
# 1. Clone the repository
git clone https://github.com/THEWHITEDEVIL16/TradeTrack.git
cd TradeTrack

# 2. Compile all files
javac *.java

# 3. Run the app
java Main
```

## Menu Options
```
+--------------------------+
|  1. Log a Trade          |   Add a new trade to your journal
|  2. View All Trades      |   See every trade you have logged
|  3. Summary & Stats      |   Win rate, total P&L, best/worst trade
|  4. Filter by Strategy   |   See only Swing trades, or only Breakouts etc.
|  5. Save Trades          |   Save all trades to trades.csv
|  6. Check Live Price     |   Fetch real-time NSE/BSE price instantly
|  7. Exit                 |   Auto-saves and exits
+--------------------------+
```

## Sample Output
```
  ==========================================
       TradeTrack - Trading Journal
  ==========================================

  Live Price of RELIANCE.NS    : Rs. 2847.35

  ==========================================
             JOURNAL SUMMARY
  ==========================================
  Total Trades  : 5
  Total PnL     : 4350.00
  Win Rate      : 80.0%
  Wins / Losses : 4 / 1
  Best Trade    : RELIANCE (+3200.00)
  Worst Trade   : ZOMATO (-850.00)
  ==========================================
```

## Project Structure
```
TradeTrack/
├── Trade.java           # Model class — one trade object with all fields
├── JournalManager.java  # Logic — add, view, filter, calculate stats
├── DataStore.java       # File I/O — save and load trades as CSV
├── StockAPI.java        # Live data — fetches real-time prices from Yahoo Finance
├── Main.java            # Entry point — menu and user interaction
└── trades.csv           # Auto-generated when you save trades
```

## Concepts Used

- Object-Oriented Programming (Classes, Objects, Methods)
- ArrayList and List for data management
- File I/O with BufferedReader and PrintWriter
- HTTP connections for live API data
- Exception handling with try-catch
- String parsing and formatting

## Future Roadmap

- [ ] JavaFX GUI with charts and graphs
- [ ] Monthly and weekly performance breakdown
- [ ] SQLite database instead of CSV
- [ ] Portfolio tracker with current market value
- [ ] Stop-loss and target price alerts

## Author

Poorvansh Jain
GitHub: https://github.com/THEWHITEDEVIL16

