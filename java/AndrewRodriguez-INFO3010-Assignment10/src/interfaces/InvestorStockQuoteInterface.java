/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import exceptionhandlers.InvalidDataException;
import datamodels.StockQuote;


/**
 *
 * @author arodrig7
 */
public interface InvestorStockQuoteInterface {
    
    public int getShares();
    
    public void setShares(int shares) throws InvalidDataException;
    
    public StockQuote getStock();
    
    public void setStock(StockQuote stock) throws InvalidDataException;
    
    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    @Override
    String toString();
    
}
