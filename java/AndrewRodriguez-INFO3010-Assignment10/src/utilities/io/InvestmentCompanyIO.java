/*
 *  This Class contains methods to write out the Investment Company objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import datacontainers.InvestmentCompanyDataContainer;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import datamodels.InvestmentCompany;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.FileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class InvestmentCompanyIO implements Serializable {
    /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods and should never be instantiated
     */
    private InvestmentCompanyIO() {
    }
    
    /**
     * Writes out a text file containing all investment companies in the investmentCompany
     * data container
     *
     * The format of the text file is:
     *
     * Example: FA301,BROKER
     * @param fileLocation
     * @param dataContainer
     * @throws FileException
     */
    public static void writeTextFile(String fileLocation, InvestmentCompanyDataContainer dataContainer) throws FileException {

        PrintWriter textFile = null;

        try {
            // Create output file
            // We are putting it in a location specified when the program is run
            // This is done via a command line argument
            textFile = new PrintWriter(fileLocation + "/investmentCompanies.txt");

            // Loop through the array list of investment companies and print delimited text to a file
            for (InvestmentCompany company : dataContainer.getInvestmentCompanyList()) {
                textFile.println(company.getCompanyName());
            }
        } catch (FileNotFoundException exp) {
            throw new FileException(exp.getMessage());
        } finally {
            // Flush the output stream and close the file
            if (textFile != null) {
                textFile.flush();
                textFile.close();
            }
        }
    }
    
    /**
     * Creates a serialized object output file containing all Investment Companies in the
     * InvestmentCompany data container
     * @param fileLocation
     * @param dataContainer
     * @throws FileException
     */
    public static void writeSerializedFile(String fileLocation, InvestmentCompanyDataContainer dataContainer) 
            throws FileException {
        try {
            // Create output file
            ObjectOutputStream serializedFile = new ObjectOutputStream(
                    new FileOutputStream(fileLocation + "/investmentCompanies.ser"));
            // Write out the data
            serializedFile.writeObject(dataContainer.getInvestmentCompanyList());
        } catch (IOException exp) {
            throw new FileException("Can't serialize file");
        }
    }
    
    /**
     * Creates an XML output file containing all Investment Companies in the
     * InvestmentCompany data container using the JAXB libraries
     * 
     * @param fileLocation
     * @param dataContainer
     * @throws FileException
     */
    public static void writeXMLFile(String fileLocation, InvestmentCompanyDataContainer dataContainer) 
            throws FileException {
        try {
            // Create the format of the xml
            JAXBContext jaxbContext = JAXBContext.newInstance(InvestmentCompanyDataContainer.class);
            // Create the marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // Create nicely formatted xml
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Marshal the investment company list into an xml file
            jaxbMarshaller.marshal(dataContainer, new File(fileLocation + "/investmentCompanies.xml"));
        } catch (JAXBException exp) {
            throw new FileException(exp.getMessage());
        }
    }
    
    /**
     * Writes out the Investment Company data in JSON format containing all
     * Investment Companies in the InvestmentCompany data container
     *
     * @param fileLocation
     * @param dataContainer
     * @throws FileException
     */
    public static void writeJSONFile(String fileLocation, InvestmentCompanyDataContainer dataContainer) 
            throws FileException {

        PrintWriter jsonFile = null;

        try {
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "/investmentCompanies.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert investment company list to JSON format
            gson.toJson(dataContainer.getInvestmentCompanyList(), jsonFile);

        } catch (JsonIOException | FileNotFoundException exp) {
            throw new FileException(exp.getMessage());
        } finally {
            // Flush the output stream and close the file
            if (jsonFile != null) {
                jsonFile.flush();
                jsonFile.close();
            }
        }
    }
    
    /**
     * Reads a set of InvestmentCompany objects from a serialized file and returns an
     * array list of Investment Companies
     * @param fileLocation
     * @return listOfCompanies
     * @throws FileException
     */
    public static ArrayList<InvestmentCompany> readSerializedFile(String fileLocation) 
            throws FileException {

        ArrayList<InvestmentCompany> listOfCompanies = new ArrayList<>();

        try {
            ObjectInputStream serializedFile = new ObjectInputStream(
                    new FileInputStream(fileLocation + "/investmentCompanies.ser"));
            // Read the serialized object and cast to its original type
            listOfCompanies = (ArrayList<InvestmentCompany>) serializedFile.readObject();
            return listOfCompanies;
        } catch (IOException | ClassNotFoundException exp) {
            throw new FileException("Can't deserialize investment company file");
        }
    }
    
    /**
     * Reads a delimited text file of investment companies and returns an array 
     * list of companies.
     *
     * An end of file flag is used to keep track of whether we hit the end of
     * the file, It starts out false and if we hit the end of file (null input),
     * it changes to true and execution stops.
     *
     * The format of the text file is:
     *
     * Example: FA301,InvestmentCompany
     * @param fileLocation
     * @return listOfCompanies
     * @throws FileException
     */
    public static ArrayList<InvestmentCompany> readTextFile(String fileLocation) 
            throws FileException {

        ArrayList<InvestmentCompany> listOfCompanies = new ArrayList<>();

        try {
            boolean eof = false;
            BufferedReader textFile = new BufferedReader(new FileReader(fileLocation + "/investmentCompanies.txt"));
            while (!eof) {
                String lineFromFile = textFile.readLine();
                if (lineFromFile == null) {
                    eof = true;
                } else {
                    // Create an investor
                    InvestmentCompany company = new InvestmentCompany();

                    // Split the input line into investor elements using the delimiter
                    String[] lineElements = lineFromFile.split(",");

                    // The first element is the company name
                    company.setCompanyName(lineElements[0]);
                    System.out.println(lineElements[0]);
                    
                   

                    // add the investor to the arraylist
                    listOfCompanies.add(company);
                }
            }
            return listOfCompanies;
        } catch (InvalidDataException | IOException exp) {
            throw new FileException(exp.getMessage());
        }
    }
    
    /**
     * Read in an XML file of InvestmentCompany objects
     *
     * @param fileLocation
     * @return
     * @throws FileException
     */
    public static InvestmentCompanyDataContainer readXMLFile(String fileLocation) 
            throws FileException {

        try {
            // Create the format of the xml
            JAXBContext jaxbContext = JAXBContext.newInstance(InvestmentCompanyDataContainer.class);
            // Create the unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //Unmarshal the file
            return (InvestmentCompanyDataContainer) jaxbUnmarshaller.unmarshal(new File(fileLocation + "/investmentCompanies.xml"));
        } catch (JAXBException exp) {
            throw new FileException(exp.getMessage());
        }
    }
    
    /**
     * Reads a JSON formatted file of investment companies and returns an array 
     * list of companies.
     * 
     * @param fileLocation
     * @return listOfCompanies
     * @throws FileException
     */
    public static ArrayList<InvestmentCompany> readJSONFile(String fileLocation) 
            throws FileException {

        ArrayList<InvestmentCompany> listOfCompanies = new ArrayList<>();

        try {
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/investmentCompanies.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            InvestmentCompany[] companyArray = gson.fromJson(jsonFile, InvestmentCompany[].class);

            // Convert to arraylist for the data model
            listOfCompanies.addAll(Arrays.asList(companyArray));
            return listOfCompanies;
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
            throw new FileException(exp.getMessage());
        }
    }
}
