/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import exceptionhandlers.InvalidDataException;
import java.util.Calendar;
import datamodels.Investor;

/**
 *
 * @author arodrig7
 */
public interface BrokerInterface {
    
    @Override
    public boolean equals(Object obj);
    
    /**
     * Returns the name of the Broker object
     * @return String
     */
    public String getName();
    
    /**
     * Returns the address of the Broker object
     * @return String
     */
    public String getAddress();
    
    /**
     * Returns the DOB of the Broker object
     * @return Calendar
     */
    public Calendar getDateOfBirth();
    
    /**
     * Returns the ID of the Broker object
     * @return long
     */
    public long getId();
    
    /**
     * Returns the hire date of the Broker object
     * @return Calendar
     */
    public Calendar getDateOfHire();
    
    /**
     * Returns the termination date of the Broker object
     * @return Calendar
     */
    public Calendar getDateOfTermination();
    
    /**
     * Returns the status of the Broker object
     * @return String
     */
    public String getStatus();
    
    /**
     * Returns the salary of the Broker object
     * @return double
     */
    public double getSalary();
    
    /**
     * These methods compares two Broker objects to determine equality.
     *
     * @return true if members are equal
     */
    @Override
    public int hashCode();
    
    /**
     * Sets the name of the Broker object
     * @param name
     * @throws InvalidDataException
     */
    public void setName(String name) throws InvalidDataException;
    
    /**
     * Sets the address of the Broker object
     * @param address
     * @throws InvalidDataException
     */
    public void setAddress(String address) throws InvalidDataException;
    
    /**
     * Sets the DOB of the Broker object
     * @param dateOfBirth
     */
    public void setDateOfBirth(Calendar dateOfBirth);
    
    /**
     * Sets the ID of the Broker object
     * @param id
     * @throws InvalidDataException
     */
    public void setId(long id) throws InvalidDataException;
    
    /**
     * Sets the hire date of the Broker object
     * @param dateOfHire
     */
    public void setDateOfHire(Calendar dateOfHire);
    
    /**
     * Sets the termination date of the Broker object
     * @param dateOfTermination
     */
    public void setDateOfTermination(Calendar dateOfTermination);
    
    /**
     * Sets the status of the Broker object
     * @param status
     * @throws InvalidDataException
     */
    public void setStatus(String status) throws InvalidDataException;
    
    /**
     * Sets the salary of the Broker object
     * @param salary
     * @throws InvalidDataException
     */
    public void setSalary(double salary) throws InvalidDataException;
    
    /**
     * Adds an investor to the Broker object
     * @param client
     */
    public void addClient(Investor client);
    
    /**
     * Returns a String representation of the Broker object in JSON format
     * @return String
     */
    @Override
    public String toString();
    
}
