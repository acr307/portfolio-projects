package view;

import controllers.MainMenuController;

public class MainMenu extends javax.swing.JFrame {

    // Menu form components
    // Top level menu options
    private javax.swing.JMenu InvestmentCompaniesMenuOption = new javax.swing.JMenu("Investment Companies");
    private javax.swing.JMenu BrokersMenuOption = new javax.swing.JMenu("Brokers");
    private javax.swing.JMenu InvestorsMenuOption = new javax.swing.JMenu("Investors");
    private javax.swing.JMenu StockQuotesMenuOption = new javax.swing.JMenu("Stock Quotes");
    private javax.swing.JMenu fileMenuOption = new javax.swing.JMenu("File");

    // Sub-menu choices
    private javax.swing.JMenuItem addStockQuote = new javax.swing.JMenuItem("Add Stock Quote");
    private javax.swing.JMenuItem addInvestor = new javax.swing.JMenuItem("Add Investor");
    private javax.swing.JMenuItem addBroker = new javax.swing.JMenuItem("Add Broker");
    private javax.swing.JMenuItem addInvestmentCompany = new javax.swing.JMenuItem("Add Investment Company");
    private javax.swing.JMenuItem listAllStockQuotes = new javax.swing.JMenuItem("List Available Stocks");
    private javax.swing.JMenuItem listAllInvestors = new javax.swing.JMenuItem("List All Investors");
    private javax.swing.JMenuItem listAllBrokers = new javax.swing.JMenuItem("List All Brokers");
    private javax.swing.JMenuItem listAllInvestmentCompanies = new javax.swing.JMenuItem("List All Investment Companies");
    private javax.swing.JMenuItem exitApplication = new javax.swing.JMenuItem("Exit");
    private javax.swing.JMenuItem saveData = new javax.swing.JMenuItem("Save Data");
    private javax.swing.JMenuItem loadData = new javax.swing.JMenuItem("Load Data");

    // Menu bar that contains the top level menu options
    private javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();

    /**
     * Constructor
     */
    public MainMenu(MainMenuController controller) {

        // Add the top level menu choices to the menu bar
        menuBar.add(fileMenuOption);
        menuBar.add(StockQuotesMenuOption);
        menuBar.add(InvestorsMenuOption);
        menuBar.add(BrokersMenuOption);
        menuBar.add(InvestmentCompaniesMenuOption);

        // Add the sub menu items
        fileMenuOption.add(saveData);
        fileMenuOption.add(loadData);
        fileMenuOption.add(exitApplication);
        StockQuotesMenuOption.add(addStockQuote);
        StockQuotesMenuOption.add(listAllStockQuotes);
        InvestorsMenuOption.add(addInvestor);
        InvestorsMenuOption.add(listAllInvestors);
        BrokersMenuOption.add(addBroker);
        BrokersMenuOption.add(listAllBrokers);
        InvestmentCompaniesMenuOption.add(addInvestmentCompany);
        InvestmentCompaniesMenuOption.add(listAllInvestmentCompanies);

        // Link the menu bar to the form
        setJMenuBar(menuBar);

        // Once the form is created, link the main menu controller to the sub menu items
        addStockQuote.addActionListener(controller);
        listAllStockQuotes.addActionListener(controller);
        addInvestor.addActionListener(controller);
        listAllInvestors.addActionListener(controller);
        addBroker.addActionListener(controller);
        listAllBrokers.addActionListener(controller);
        addInvestmentCompany.addActionListener(controller);
        listAllInvestmentCompanies.addActionListener(controller);
        saveData.addActionListener(controller);
        loadData.addActionListener(controller);
        exitApplication.addActionListener(controller);
       
        /**
         * set close function to dispose of the form
         */
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Set Size of form after creating
        setSize(600, 600);

        // center form
        setLocationRelativeTo(null);
    }

}
