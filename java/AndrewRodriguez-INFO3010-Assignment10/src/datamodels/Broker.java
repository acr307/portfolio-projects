/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datamodels;

import exceptionhandlers.InvalidDataException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import utilities.date.DateFunctions;
import interfaces.BrokerInterface;

/**
 *
 * @author arodrig7
 */
/** XML element tags are Used to read/write XML  */
@XmlRootElement(name = "Broker")
@XmlAccessorType(XmlAccessType.FIELD)
public class Broker extends Person implements Serializable, BrokerInterface {
    
    @XmlElement(name = "dateOfHire")
    private Calendar dateOfHire;
    @XmlElement(name = "dateOfTermination")
    private Calendar dateOfTermination;
    @XmlElement(name = "salary")
    private double salary;
    @XmlElement(name = "status")
    private String status;
    @XmlElement(name = "listOfClients")
    private List<Investor> listOfClients;
    
    /**
     * no-argument constructor
     * initializes new list of clients
     */
    public Broker() {
        // initialized for null lists
        this.listOfClients = new java.util.LinkedList<>();
    }
    
    /**
     * This constructor takes the name, address, dateOfBirth, id and memberSince
     *
     * @param name The name of the Broker object
     * @param address The address of the Broker object
     * @param dateOfBirth The dateOfBirth of the Broker object
     * @param id The id of the Broker object
     * @param dateOfHire The dateofHire of the Broker object
     * @param dateOfTermination the dateOfTermination of the Broker object
     * @param salary the salary of the Broker object
     * @param status the status of the Broker object
     * @throws exceptionhandlers.InvalidDataException
     */
    public Broker(String name, String address, Calendar dateOfBirth, long
id, Calendar dateOfHire, Calendar dateOfTermination, double salary,
String status) throws InvalidDataException {
        super(name, address, dateOfBirth, id);
        if(salary <= 0) {
            throw new InvalidDataException("Creating broker object failed, salary is invalid");
        }
        if(!status.equals("Full Time") && !status.equals("Part Time")) {
            throw new InvalidDataException("Creating broker object failed, status is invalid");
        }
        this.dateOfHire = dateOfHire;
        this.dateOfTermination = dateOfTermination;
        this.salary = salary;
        this.status = status;
    }
    
    /**
     * Returns the dateOfHire of the Broker object
     *
     * @return dateOfHire
     */
    public Calendar getDateOfHire() {
        return this.dateOfHire;
    }
    
    /**
     * Returns the dateOfTermination of the Broker object
     *
     * @return dateOfTermination
     */
    public Calendar getDateOfTermination() {
        return this.dateOfTermination;
    }
    
    /**
     * Returns the salary of the Broker object
     *
     * @return salary
     */
    public double getSalary() {
        return this.salary;
    }
    
    /**
     * Returns the status of the Broker object
     *
     * @return status
     */
    public String getStatus() {
        return this.status;
    }
    
    /**
     * Returns the listOfClients of the Broker object
     *
     * @return listOfClients
     */
    public List<Investor> getListOfClients() {
        return this.listOfClients;
    }
    
    /**
     * Sets the dateOfHire of the Broker object
     *
     * @param dateOfHire
     */
    public void setDateOfHire(Calendar dateOfHire) {
        this.dateOfHire = dateOfHire;
    }
    
    /**
     * Sets the dateOfTermination of the Broker object
     *
     * @param dateOfTermination
     */
    public void setDateOfTermination(Calendar dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }
    
    /**
     * Sets the salary of the Broker object
     *
     * @param salary
     */
    public void setSalary(double salary) throws InvalidDataException {
        if(salary <= 0) {
            throw new InvalidDataException("Setting salary failed, salary is invalid");
        }
        this.salary = salary;
    }
    
    /**
     * Sets the status of the Broker object
     *
     * @param status
     * @throws InvalidDataException
     */
    public void setStatus(String status) throws InvalidDataException {
        if(status.equals("Full Time") || status.equals("Part Time")) {
            this.status = status;
        } else {
            throw new InvalidDataException("Setting status failed, status is invalid");
        }
        
    }
    
    /**
     * Adds a new client to the Investor object
     *
     * @param newClient
     */
    public void addClient(Investor newClient) {
        this.listOfClients.add(newClient);
    }
    
    /**
     * These methods compare two Broker objects to determine equality.
     *
     * @return true if members are equal
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.getName());
        hash = 67 * hash + Objects.hashCode(this.getAddress());
        hash = 67 * hash + Objects.hashCode(this.getDateOfBirth());
        hash = 67 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 67 * hash + Objects.hashCode(this.dateOfHire);
        hash = 67 * hash + Objects.hashCode(this.dateOfTermination);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.salary) ^ (Double.doubleToLongBits(this.salary) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.status);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Broker other = (Broker) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getAddress(), other.getAddress())) {
            return false;
        }
        if (!Objects.equals(this.getDateOfBirth(), other.getDateOfBirth())) {
            return false;
        }
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        if (!Objects.equals(this.dateOfHire, other.dateOfHire)) {
            return false;
        }
        if (!Objects.equals(this.dateOfTermination, other.dateOfTermination)) {
            return false;
        }
        if (!Objects.equals(this.salary, other.salary)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a String representation of the Broker object
     * @return String
     */
    @Override
    public String toString() {
        String result =  "Broker:\n------------\n" +
                this.getName() + "\n" +
                this.getAddress() + "\n" +
                "Date of Birth: " + DateFunctions.dateToString(this.getDateOfBirth()) + "\n" +
                "Date of Hire: " + DateFunctions.dateToString(this.dateOfHire) + "\n" +
                "Date of Termination: " + DateFunctions.dateToString(this.dateOfTermination) + "\n" +
                "Salary: " + this.salary + "\n" +
                "Status: " + this.status + "\n" +
                "ID: " + this.getId() + "\n\n" +
                "List of Clients:\n" +
                "------------\n";
        if (this.listOfClients != null) {
            for (Investor client : listOfClients) {
                result += client.getName() + "\n";  // Accessing the name of each Broker using string concatenation
            }
        } else {
            result += "No clients available.\n\n";
        }
        return result;
    }
}
