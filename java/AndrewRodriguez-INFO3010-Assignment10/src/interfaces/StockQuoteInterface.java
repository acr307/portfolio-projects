/*
 * Interface for all the methods in the StockQuote class
 */
package interfaces;

import exceptionhandlers.InvalidDataException;
import java.util.Calendar;

public interface StockQuoteInterface {

    @Override
    public boolean equals(Object obj);

    /**
     * Returns the quoteDate of the StockQuote object
     * @return Calendar
     */
    public Calendar getQuoteDate();

    /**
     * Returns the tickerSymbol of the StockQuote object
     * @return String
     */
    public String getTickerSymbol();

    /**
     * Returns the value of the StockQuote object
     * @return double
     */
    public double getValue();

    
   @Override
   public  int hashCode();

    /**
     * Sets the quoteDate of the StockQuote object
     * @param quoteDate
     */
   public  void setQuoteDate(Calendar quoteDate);

    /**
     * Sets the ticker symbol of the StockQuote object
     * @param tickerSymbol
     * @throws InvalidDataException
     */
    public void setTickerSymbol(String tickerSymbol) throws InvalidDataException;

    /**
     * Sets the value of the StockQuote object
     * @param value
     * @throws InvalidDataException
     */
    public void setValue(double value) throws InvalidDataException;

    /**
     * Returns a String representation of the StockQuote object in JSON format
     * @return String
     */
    @Override
    public String toString();
    
}
