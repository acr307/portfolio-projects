/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datamodels;

import exceptionhandlers.InvalidDataException;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import interfaces.InvestmentCompanyInterface;

/**
 *
 * @author arodrig7
 */
/** XML element tags are Used to read/write XML  */
@XmlRootElement(name = "InvestmentCompany")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvestmentCompany implements Serializable, InvestmentCompanyInterface {
    
    @XmlElement(name = "companyName")
    private String companyName;
    @XmlElement(name = "listOfBrokers")
    private List<Broker> listOfBrokers;
    
    /**
     * no-argument constructor
     * initializes new list of Brokers
     */
    public InvestmentCompany() {
        this.listOfBrokers = new java.util.LinkedList<>();
    }
    
    /**
     * This constructor takes the name, address, dateOfBirth and id
     *
     * @param companyName The name of the InvestmentCompany object
     * @param listOfBrokers The broker list of the InvestmentCompany object
     * @throws exceptionhandlers.InvalidDataException
     */
    public InvestmentCompany(String companyName, List<Broker> listOfBrokers) 
            throws InvalidDataException {
        if(companyName.isEmpty()) {
            throw new InvalidDataException("Creating investment company object failed, company name not specified");
        }
        this.companyName = companyName;
        this.listOfBrokers = listOfBrokers;
    }
    
    /**
     * This constructor takes the name, address, dateOfBirth and id
     *
     * @param companyName The name of the InvestmentCompany object
     * @throws exceptionhandlers.InvalidDataException
     */
    public InvestmentCompany(String companyName) throws InvalidDataException {
        if(companyName.isEmpty()) {
            throw new InvalidDataException("Creating investment company object failed, company name not specified");
        }
        this.companyName = companyName;
    }
    
    /**
     * Returns the company name of the InvestmentCompany object
     *
     * @return companyName
     */
    public String getCompanyName() {
        return this.companyName;
    }
    
    /**
     * Returns the broker list of the InvestmentCompany object
     *
     * @return listOfBrokers
     */
    public List<Broker> getListOfBrokers() {
        return this.listOfBrokers;
    }
    
    /**
     * Sets the company name of the InvestmentCompany object
     *
     * @param companyName
     * @throws exceptionhandlers.InvalidDataException
     */
    public void setCompanyName(String companyName) throws InvalidDataException {
        if(companyName.isEmpty()) {
            throw new InvalidDataException("Setting investment company name failed, company name not specified");
        }
        this.companyName = companyName;
    }
    
    /**
     * Adds a broker to the broker list in the InvestmentCompany object
     *
     * @param broker
     */
    public void addBroker(Broker broker) {
        listOfBrokers.add(broker);
    }
    
    /**
     * Returns a String representation of the InvestmentCompany object
     * @return result
     */
    @Override
    public String toString() {
        String result = "Investment Company: " + this.companyName + "\n\n" 
                    + "List of Brokers:\n"
                    + "------------\n";

        if (this.listOfBrokers != null) {
            for (Broker broker : listOfBrokers) {
                result += broker.getName() + "\n\n";  // Accessing the name of each Broker using string concatenation
            }
        } else {
            result += "No brokers available.\n";
        }
        return result;
    }
}
