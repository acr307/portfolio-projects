/*
 * Listens for events on the main menu form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 */
package controllers;

import controllers.reportformcontrollers.ListAllStockQuotesController;
import controllers.reportformcontrollers.ListAllBrokersController;
import controllers.reportformcontrollers.ListAllInvestmentCompaniesController;
import controllers.reportformcontrollers.ListAllInvestorsController;
import controllers.inputformcontrollers.InputStockQuoteFormController;
import controllers.inputformcontrollers.InputBrokerFormController;
import controllers.inputformcontrollers.InputInvestmentCompanyFormController;
import controllers.inputformcontrollers.InputInvestorFormController;
import java.awt.event.ActionListener;
import datacontainers.StockQuoteDataContainer;
import datacontainers.InvestorDataContainer;
import datacontainers.InvestmentCompanyDataContainer;
import datacontainers.BrokerDataContainer;
import exceptionhandlers.ErrorPopup;
import exceptionhandlers.FileException;
import utilities.io.StockQuoteIO;
import utilities.io.BrokerIO;
import utilities.io.InvestmentCompanyIO;
import utilities.io.InvestorIO;

import view.MainMenu;

public class MainMenuController implements ActionListener {

    // The data models are instantiated here and passed to the 
    // constructors for the controllers
    private final StockQuoteDataContainer stockQuoteDataContainer = new StockQuoteDataContainer();
    private final InvestorDataContainer investorDataContainer = new InvestorDataContainer();
    private final BrokerDataContainer brokerDataContainer = new BrokerDataContainer();
    private final InvestmentCompanyDataContainer investmentCompanyDataContainer = new InvestmentCompanyDataContainer();

    // File location to store output files
    private String fileLocation;

    /**
     * Constructor
     *
     * @param fileLocation
     */
    public MainMenuController(String fileLocation) {

        // Store the file location, used in the save  and load methods
        // The file location is read in from the command line arguments
        this.fileLocation = fileLocation;
    }

    // The main menu form gets created here. Notice it takes this controller object
    // as an argument to the constructor
    private MainMenu mainMenu = new MainMenu(this);

    /**
     * The ActionListener interface contains a single method, actionPerformed
     * SInce this class implements the ActionListener interface, we need to
     * implement that single method
     */
    public void actionPerformed(java.awt.event.ActionEvent event) {

        //  Figure out which button was clicked
        String menuItemClicked = event.getActionCommand();

        // create the controller which will open the correct form
        if (menuItemClicked.equals("Add Stock Quote")) {
            InputStockQuoteFormController inputController = new InputStockQuoteFormController(stockQuoteDataContainer);
        } else if (menuItemClicked.equals("List Available Stocks")) {
            ListAllStockQuotesController reportController = new ListAllStockQuotesController(stockQuoteDataContainer);
        } else if (menuItemClicked.equals("Add Investor")) {
            InputInvestorFormController inputController = new InputInvestorFormController(investorDataContainer, stockQuoteDataContainer);
        } else if (menuItemClicked.equals("List All Investors")) {
            ListAllInvestorsController reportController = new ListAllInvestorsController(investorDataContainer);
        } else if (menuItemClicked.equals("Add Broker")) {
            InputBrokerFormController inputController = new InputBrokerFormController(brokerDataContainer, investorDataContainer);
        } else if (menuItemClicked.equals("List All Brokers")) {
            ListAllBrokersController reportController = new ListAllBrokersController(brokerDataContainer);
        } else if (menuItemClicked.equals("Add Investment Company")) {
            InputInvestmentCompanyFormController inputController = new InputInvestmentCompanyFormController(investmentCompanyDataContainer, brokerDataContainer);
        } else if (menuItemClicked.equals("List All Investment Companies")) {
            ListAllInvestmentCompaniesController reportController = new ListAllInvestmentCompaniesController(investmentCompanyDataContainer);
        
        } else if (menuItemClicked.equals("Exit")) {
            System.exit(0);
        } else if (menuItemClicked.equals("Save Data")) {
            try {
                // All 4 types for demonstation
                //StockQuote
//                StockQuoteIO.writeSerializedFile(fileLocation, stockQuoteDataContainer);
//                StockQuoteIO.writeTextFile(fileLocation, stockQuoteDataContainer);
//                StockQuoteIO.writeXMLFile(fileLocation, stockQuoteDataContainer);
                StockQuoteIO.writeJSONFile(fileLocation, stockQuoteDataContainer);
                //Investor
//                InvestorIO.writeSerializedFile(fileLocation, investorDataContainer);
//                InvestorIO.writeTextFile(fileLocation, investorDataContainer);
//                InvestorIO.writeXMLFile(fileLocation, investorDataContainer);
                InvestorIO.writeJSONFile(fileLocation, investorDataContainer);
                //Broker
//                BrokerIO.writeSerializedFile(fileLocation, brokerDataContainer);
//                BrokerIO.writeTextFile(fileLocation, brokerDataContainer);
//                BrokerIO.writeXMLFile(fileLocation, brokerDataContainer);
                BrokerIO.writeJSONFile(fileLocation, brokerDataContainer);
                //Investment Company
//                InvestmentCompanyIO.writeSerializedFile(fileLocation, investmentCompanyDataContainer);
//                InvestmentCompanyIO.writeTextFile(fileLocation, investmentCompanyDataContainer);
//                InvestmentCompanyIO.writeXMLFile(fileLocation, investmentCompanyDataContainer);
                InvestmentCompanyIO.writeJSONFile(fileLocation, investmentCompanyDataContainer);
            } catch (FileException exp) {
                new ErrorPopup(mainMenu, exp);
            }
        } else if (menuItemClicked.equals("Load Data")) {
            try {
                //StockQuote
//                stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readSerializedFile(fileLocation));
//                stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readTextFile(fileLocation));
//                stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readXMLFile(fileLocation).getStockQuoteList());
                stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readJSONFile(fileLocation));
                //Investor
//                investorDataContainer.setInvestorList(InvestorIO.readSerializedFile(fileLocation));
//                investorDataContainer.setInvestorList(InvestorIO.readTextFile(fileLocation));
//                investorDataContainer.setInvestorList(InvestorIO.readXMLFile(fileLocation).getInvestorList());
                investorDataContainer.setInvestorList(InvestorIO.readJSONFile(fileLocation));
                //Broker
//                brokerDataContainer.setBrokerList(BrokerIO.readSerializedFile(fileLocation));
//                brokerDataContainer.setBrokerList(BrokerIO.readTextFile(fileLocation));
//                brokerDataContainer.setBrokerList(BrokerIO.readXMLFile(fileLocation).getBrokerList());
                brokerDataContainer.setBrokerList(BrokerIO.readJSONFile(fileLocation));
                //Investment Company
//                investmentCompanyDataContainer.setCompanyList(InvestmentCompanyIO.readSerializedFile(fileLocation));
//                investmentCompanyDataContainer.setCompanyList(InvestmentCompanyIO.readTextFile(fileLocation));
//                investmentCompanyDataContainer.setCompanyList(InvestmentCompanyIO.readXMLFile(fileLocation).getInvestmentCompanyList());
                investmentCompanyDataContainer.setCompanyList(InvestmentCompanyIO.readJSONFile(fileLocation));
            } catch (FileException exp) {
                new ErrorPopup(mainMenu, exp);
            }
        }
    }
       
        // Getter used in the Application.java class to get a copy of the main menu form
    public MainMenu getMainMenu() {
        return mainMenu;
    }
}
