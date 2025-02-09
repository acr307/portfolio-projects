/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.util.Calendar;
import exceptionhandlers.InvalidDataException;

/**
 *
 * @author arodrig7
 */
public interface InvestorInterface {
    
    /**
     * This method compares two InvestmentCompany objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
    @Override
    public boolean equals(Object obj);
    
    /**
     * Returns the name of the Investor object
     * @return String
     */
    public String getName();
    
    /**
     * Returns the address of the Investor object
     * @return String
     */
    public String getAddress();
    
    /**
     * Returns the DOB of the Investor object
     * @return Calendar
     */
    public Calendar getDateOfBirth();
    
    /**
     * Returns the ID of the Investor object
     * @return long
     */
    public long getId();
    
    /**
     * Returns the member since date of the Investor object
     * @return Calendar
     */
    public Calendar getMemberSince();
    
    @Override
    public int hashCode();
    
    /**
     * Sets the name of the Investor object
     * @param name
     * @throws InvalidDataException
     */
    public void setName(String name) throws InvalidDataException;
    
    /**
     * Sets the address of the Investor object
     * @param address
     * @throws InvalidDataException
     */
    public void setAddress(String address) throws InvalidDataException;
    
    /**
     * Sets the DOB of the Investor object
     * @param dateOfBirth
     * @throws InvalidDataException
     */
    public void setDateOfBirth(Calendar dateOfBirth) throws InvalidDataException;
    
    /**
     * Sets the ID of the Investor object
     * @param id
     * @throws InvalidDataException
     */
    public void setId(long id) throws InvalidDataException;
    
    /**
     * Sets the member since date of the Investor object
     * @param memberSince
     * @throws InvalidDataException
     */
    public void setMemberSince(Calendar memberSince) throws InvalidDataException;
    
    /**
     * Returns a String representation of the Investor object in JSON format
     * @return String
     */
    @Override
    public String toString();
    
}
