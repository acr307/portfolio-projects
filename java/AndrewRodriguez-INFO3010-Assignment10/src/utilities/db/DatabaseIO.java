package utilities.db;

import datacontainers.InvestorDataContainer;
import datacontainers.StockQuoteDataContainer;
import datacontainers.BrokerDataContainer;
import datacontainers.InvestmentCompanyDataContainer;
import datamodels.Investor;
import datamodels.InvestorStockQuote;
import datamodels.StockQuote;
import datamodels.Broker;
import datamodels.InvestmentCompany;
import exceptionhandlers.DatabaseErrorPopup;
import exceptionhandlers.DatabaseException;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.FileException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods to write to and read from, a Mysql database I
 * suppose I could have put them in the I/O class but I decided to leave all the
 * database code together.
 */
public class DatabaseIO {

    // Used in all methods, declared once
    static Statement insertStatement;
    static Statement queryStatement;
    static Connection connection;
    static String commandString;

    /**
     * Store all stockquotes to database
     */
    public static void storeStockQuotes(StockQuoteDataContainer stockquoteDataContainer) throws DatabaseException {

        // Retrieve the database connection and create the statement object
        try {
            connection = DatabaseUtilities.openDatabaseConnection();
            insertStatement = connection.createStatement();

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured, could not connect to database: " + error.getMessage());
        }

        //Loo pthrough the list of stock quotes. 
        for (StockQuote quote : stockquoteDataContainer.getStockQuoteList()) {
            try {

                // Create the string for the sql statement
                String command = "INSERT INTO stockquotes (tickersymbol, value, date)"
                        + "VALUES ('"
                        + quote.getTickerSymbol() + "','"
                        + quote.getValue() + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(quote.getQuoteDate()) + "')";

                // Execute the statement
                insertStatement.executeUpdate(command);

            } catch (SQLException error) {
                // eat the error
            }
        }
    }

    public static void storeInvestors(InvestorDataContainer investorDataContainer) throws DatabaseException {

        // Retrieve the database connection and create the statement object
        try {
            connection = DatabaseUtilities.openDatabaseConnection();
            insertStatement = connection.createStatement();

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured, could not connect to database: " + error.getMessage());
        }

        // Loop through the list of investors and store each one in the investors table
        for (Investor investor : investorDataContainer.getInvestorList()) {
            try {

                // Create the string for the sql statement
                String command1 = "INSERT INTO investors (id, name, address, dateOfBirth, memberSince)"
                        + "VALUES ('"
                        + investor.getId() + "','"
                        + investor.getName() + "','"
                        + investor.getAddress() + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(investor.getDateOfBirth()) + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(investor.getMemberSince()) + "')";

                // Execute the statement
                insertStatement.executeUpdate(command1);

            } catch (SQLException error) {
              // eat the error
            }

            try {

                //Loop through list of stocks for this investor
                for (InvestorStockQuote quote : investor.getListOfStocks()) {
                    //For each stock create an investorquote entry
                    String command2 = "INSERT INTO investorstocks (id, tickersymbol, shares)"
                            + "VALUES ('"
                            + investor.getId() + "','"
                            + quote.getStock().getTickerSymbol() + "','"
                            + quote.getShares() + "')";

                    // Execute the statement
                    insertStatement.executeUpdate(command2);
                }

            } catch (SQLException error) {
               // eat the error
            }
        }
    }
    
    /**
     * Store all brokers to database
     * @param brokerDataContainer
     * @throws DatabaseException
     */
    public static void storeBrokers(BrokerDataContainer brokerDataContainer) throws DatabaseException {

        // Retrieve the database connection and create the statement object
        try {
            connection = DatabaseUtilities.openDatabaseConnection();
            insertStatement = connection.createStatement();

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured, could not connect to database: " + error.getMessage());
        }

        //Loop through the list of brokers. 
        for (Broker broker : brokerDataContainer.getBrokerList()) {
            try {

                // Create the string for the sql statement
                String command = "INSERT INTO brokers (id, name, address, dateOfBirth, dateOfHire, dateOfTermination, salary, status)"
                        + "VALUES ('"
                        + broker.getId() + "','"
                        + broker.getName() + "','"
                        + broker.getAddress() + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfBirth()) + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfHire()) + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfTermination()) + "','"
                        + broker.getSalary() + "','"
                        + broker.getStatus() + "')";

                // Execute the statement
                insertStatement.executeUpdate(command);

            } catch (SQLException error) {
                // eat the error
            }
            
            try {

                //Loop through list of investors for this broker
                for (Investor investor : broker.getListOfClients()) {
                    //For each investor create an investor entry
                    String command2 = "INSERT INTO clients (id, name, address, dateOfBirth, memberSince)"
                            + "VALUES ('"
                            + investor.getId() + "','"
                            + investor.getName() + "','"
                            + investor.getAddress() + "','"
                            + DatabaseDateUtilities.getSqlFormattedDate(investor.getDateOfBirth()) + "','"
                            + DatabaseDateUtilities.getSqlFormattedDate(investor.getMemberSince()) + "')";

                    // Execute the statement
                    insertStatement.executeUpdate(command2);
                }

            } catch (SQLException error) {
               // eat the error
            }
        }
    }
    
    /**
     * Store all investment companies to database
     */
    public static void storeInvestmentCompany(InvestmentCompanyDataContainer investmentCompanyDataContainer) throws DatabaseException {

        // Retrieve the database connection and create the statement object
        try {
            connection = DatabaseUtilities.openDatabaseConnection();
            insertStatement = connection.createStatement();

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured, could not connect to database: " + error.getMessage());
        }

        //Loop through the list of investment companies. 
        for (InvestmentCompany company : investmentCompanyDataContainer.getInvestmentCompanyList()) {
            try {

                // Create the string for the sql statement
                String command = "INSERT INTO investmentCompany (name, address, dateOfBirth, id, dateOfHire, dateOfTermination, salary, status)"
                        + "VALUES ('"
                        + company.getCompanyName() + "')";

                // Execute the statement
                insertStatement.executeUpdate(command);

            } catch (SQLException error) {
                // eat the error
            }
            
            try {

                //Loop through list of brokers for this company
                for (Broker broker : company.getListOfBrokers()) {
                    //For each broker create a company broker entry
                    String command2 = "INSERT INTO companyBrokers (id, name, address, dateOfBirth, dateOfHire, dateOfTermination, salary, status)"
                        + "VALUES ('"
                        + broker.getId() + "','"
                        + broker.getName() + "','"
                        + broker.getAddress() + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfBirth()) + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfHire()) + "','"
                        + DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfTermination()) + "','"
                        + broker.getSalary() + "','"
                        + broker.getStatus() + "')";

                    // Execute the statement
                    insertStatement.executeUpdate(command2);
                }

            } catch (SQLException error) {
               // eat the error
            }
        }
    }
    
    public static List<StockQuote> retrieveStockQuotes() throws DatabaseException {

        ArrayList<StockQuote> listOfStockQuotes = new ArrayList<>();

        // Retrieve the database connection and create the statement object
        try {
            connection = DatabaseUtilities.openDatabaseConnection();
            queryStatement = connection.createStatement();
        } catch (SQLException error) {
            throw new DatabaseException("A database error occured, could not connect to database: " + error.getMessage());
        }

        try {

            // Create the string for the statement object
            String command = "SELECT tickersymbol, value, date FROM stockquotes ORDER BY tickersymbol";

            // Execute the statement object 
            ResultSet results = queryStatement.executeQuery(command);

            // Call private helper method to parse the result set into the array list
            listOfStockQuotes = parseStockQuote(results);

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured retrieving data from the stock quote table " + error.getMessage());
        }

        return listOfStockQuotes;
    }

    /**
     * Populate the array list with data from the database
     */
    private static ArrayList<StockQuote> parseStockQuote(ResultSet results) throws DatabaseException {

        ArrayList<StockQuote> listOfStockquotes = new ArrayList<>();

        try {
            while (results.next()) {
                StockQuote quote = new StockQuote();
                quote.setTickerSymbol(results.getString(1));
                quote.setValue(Double.parseDouble(results.getString(2)));
                quote.setQuoteDate(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("date")));
                listOfStockquotes.add(quote);
            }
        } catch (InvalidDataException | NumberFormatException | SQLException e) {
            throw new DatabaseException("Error parsing database results"
                    + " stockquotes table " + e.getMessage());
        }

        return listOfStockquotes;
    }

    public static List<Investor> retrieveInvestors() throws DatabaseException {

        ArrayList<Investor> listOfInvestors = new ArrayList<>();

        // Retrieve the database connection and create the statement object
        try {
            connection = DatabaseUtilities.openDatabaseConnection();
            queryStatement = connection.createStatement();

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured, could not connect to database: " + error.getMessage());
        }

        try {

            //Get list of investors from the db.  
            commandString = "SELECT id, name, address, dateOfBirth, memberSince FROM investors";
            ResultSet investors = queryStatement.executeQuery(commandString);
            listOfInvestors = parseInvestorListResults(investors);

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured retrieving data from the investor table " + error.getMessage());
        }

        try {

            for (Investor investor : listOfInvestors) {
                //   Create a sql statement to retrieve all the stocks associated with the investor ID
                commandString = "SELECT tickersymbol, shares FROM investorstocks WHERE id = ?";
                PreparedStatement pstmt = connection.prepareStatement(commandString);
                pstmt.setLong(1, investor.getId());
                ResultSet unparsedInvestorStockQuotes = pstmt.executeQuery();
                ArrayList<InvestorStockQuote> listOfParsedStockQuotes = parseInvestorStockQuotes(unparsedInvestorStockQuotes);
                for (InvestorStockQuote parsedInvestorStockQuote : listOfParsedStockQuotes) {
                    investor.addStock(parsedInvestorStockQuote);
                }
            }
            
        } catch (SQLException | FileException | InvalidDataException error) {
            throw new DatabaseException("A database error occured retrieving data from the investor stocks table " + error.getMessage());
        }

        return listOfInvestors;
    }
    
    public static List<Broker> retrieveBrokers() throws DatabaseException {

        ArrayList<Broker> listOfBrokers = new ArrayList<>();

        // Retrieve the database connection and create the statement object
        try {
            connection = DatabaseUtilities.openDatabaseConnection();
            queryStatement = connection.createStatement();

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured, could not connect to database: " + error.getMessage());
        }

        try {

            //Get list of investors from the db.  
            commandString = "SELECT id, name, address, dateOfBirth, dateOfHire, dateOfTermination, salary, status FROM brokers";
            ResultSet brokers = queryStatement.executeQuery(commandString);
            listOfBrokers = parseBrokerListResults(brokers);

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured retrieving data from the investor table " + error.getMessage());
        }

        try {

            for (Broker broker : listOfBrokers) {
                //   Create a sql statement to retrieve all the investors associated with the broker ID
                commandString = "SELECT tickersymbol, shares FROM investorstocks WHERE id = ?";
                PreparedStatement pstmt = connection.prepareStatement(commandString);
                pstmt.setLong(1, broker.getId());
                ResultSet unparsedClients = pstmt.executeQuery();
                ArrayList<Investor> listOfParsedInvestors = parseInvestorListResults(unparsedClients);
                for (Investor parsedClient : listOfParsedInvestors) {
                    broker.addClient(parsedClient);
                }
            }
            
        } catch (SQLException error) {
            throw new DatabaseException("A database error occured retrieving data from the investors table " + error.getMessage());
        }

        return listOfBrokers;
    }
    
    public static List<InvestmentCompany> retrieveCompanies() throws DatabaseException {

        ArrayList<InvestmentCompany> listOfCompanies = new ArrayList<>();

        // Retrieve the database connection and create the statement object
        try {
            connection = DatabaseUtilities.openDatabaseConnection();
            queryStatement = connection.createStatement();

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured, could not connect to database: " + error.getMessage());
        }

        try {

            //Get list of investors from the db.  
            commandString = "SELECT companyName FROM investmentCompany";
            ResultSet companies = queryStatement.executeQuery(commandString);
            listOfCompanies = parseInvestmentCompanyListResults(companies);

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured retrieving data from the investor table " + error.getMessage());
        }

        try {

            for (InvestmentCompany company : listOfCompanies) {
                //   Create a sql statement to retrieve all the brokers associated with the investmentCompany name
                commandString = "SELECT id, name, address, dateOfBirth, dateOfHire, dateOfTermination, salary, status FROM investmentCompany WHERE name = ?";
                PreparedStatement pstmt = connection.prepareStatement(commandString);
                pstmt.setString(1, company.getCompanyName());
                ResultSet unparsedBrokers = pstmt.executeQuery();
                ArrayList<Broker> listOfParsedBrokers = parseBrokerListResults(unparsedBrokers);
                for (Broker parsedBroker : listOfParsedBrokers) {
                    company.addBroker(parsedBroker);
                }
            }
            
        } catch (SQLException error) {
            throw new DatabaseException("A database error occured retrieving data from the investors table " + error.getMessage());
        }

        return listOfCompanies;
    }

    private static ArrayList<InvestorStockQuote> parseInvestorStockQuotes(ResultSet investorQuotes) throws SQLException, FileException, InvalidDataException {

        ArrayList<InvestorStockQuote> investorStockQuotes = new ArrayList<>();

        connection = DatabaseUtilities.openDatabaseConnection();
        
        while (investorQuotes.next()) {
            // Retrieving the stock quotes using this list 
            commandString = "SELECT tickersymbol, value, date FROM stockquotes WHERE tickersymbol IN (?) ";
            PreparedStatement pstmt = connection.prepareStatement(commandString);
            pstmt = connection.prepareStatement(commandString);
            pstmt.setString(1, investorQuotes.getString(1));
            ResultSet stockQuotes = pstmt.executeQuery();

            stockQuotes.next();

            StockQuote stockQuote = new StockQuote();
            stockQuote.setTickerSymbol(stockQuotes.getString(1));
            stockQuote.setValue(stockQuotes.getFloat(2));
            stockQuote.setQuoteDate(DatabaseDateUtilities.getJavaFormattedDate(stockQuotes.getDate("date")));

            InvestorStockQuote investorStockQuote = new InvestorStockQuote();
            investorStockQuote.setStock(stockQuote);
            investorStockQuote.setShares(investorQuotes.getInt(2));

            investorStockQuotes.add(investorStockQuote);
        }

        return investorStockQuotes;
    }

    private static ArrayList<Investor> parseInvestorListResults(ResultSet results) throws DatabaseException {

        ArrayList<Investor> listOfInvestors = new ArrayList<>();

        try {
            while (results.next()) {
                Investor investor = new Investor();
                investor.setId(results.getLong(1));
                investor.setName(results.getString(2));
                investor.setAddress(results.getString(3));
                investor.setDateOfBirth(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("dateOfBirth")));
                investor.setMemberSince(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("memberSince")));

                listOfInvestors.add(investor);
            }
        } catch (InvalidDataException | NumberFormatException | SQLException e) {
            throw new DatabaseException("Error parsing database results"
                    + " investor table " + e.getMessage());
        }

        return listOfInvestors;
    }
    
    private static ArrayList<Broker> parseBrokerListResults(ResultSet results) throws DatabaseException {

        ArrayList<Broker> listOfBrokers = new ArrayList<>();

        try {
            while (results.next()) {
                Broker broker = new Broker();
                broker.setId(results.getLong(1));
                broker.setName(results.getString(2));
                broker.setAddress(results.getString(3));
                broker.setSalary(results.getDouble(4));
                broker.setStatus(results.getString(5));
                broker.setDateOfBirth(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("dateOfBirth")));
                broker.setDateOfHire(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("dateOfHire")));
                broker.setDateOfTermination(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("dateOfTermination")));
                

                listOfBrokers.add(broker);
            }
        } catch (InvalidDataException | NumberFormatException | SQLException e) {
            throw new DatabaseException("Error parsing database results"
                    + " broker table " + e.getMessage());
        }

        return listOfBrokers;
    }

    private static ArrayList<InvestmentCompany> parseInvestmentCompanyListResults(ResultSet results) throws DatabaseException {

        ArrayList<InvestmentCompany> listOfCompanies = new ArrayList<>();

        try {
            while (results.next()) {
                InvestmentCompany company = new InvestmentCompany();
                company.setCompanyName(results.getString(1));

                listOfCompanies.add(company);
            }
        } catch (InvalidDataException | NumberFormatException | SQLException e) {
            throw new DatabaseException("Error parsing database results"
                    + " investment company table " + e.getMessage());
        }

        return listOfCompanies;
    }
}
