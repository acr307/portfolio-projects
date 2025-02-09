/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import exceptionhandlers.InvalidDataException;

/**
 *
 * @author arodrig7
 */
public interface InvestmentCompanyInterface {
    
    /**
     * This method compares two InvestmentCompany objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
    @Override
    public boolean equals(Object obj);
    
    /**
     * Returns the company name of the InvestmentCompany object
     * @return String
     */
    public String getCompanyName();
    
    /**
     * Sets the company name of the InvestmentCompany object
     * @param name
     * @throws InvalidDataException
     */
    public void setCompanyName(String name) throws InvalidDataException;
    
    /**
     * Returns a String representation of the InvestmentCompany object in JSON format
     * @return String
     */
    @Override
    public String toString();
}
