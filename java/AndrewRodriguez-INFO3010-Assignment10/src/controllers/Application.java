package controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import utilities.date.DateFunctions;

/*
 * This is the main entry into the application. It creates a menu controller object
 * and the controller object creates the forms and the data models as needed
 */
public class Application {   

    // Create the logger 
    // APPLICATION_LOGGER will create log records
    // APPLICATION_LOGGER is taking the name of the class to use in the log messages
    // This helps determine where the message came from (this is optional)
    private static final Logger APPLICATION_LOGGER = Logger.getLogger(Application.class.getName());
    
    // Declare the file handler for the log file. It will be created in a try/catch block below
    // APPLICATION_FILEHANDLER will log the messages created by APPLICATION_LOGGER, to a file
    private static FileHandler APPLICATION_FILEHANDLER;

    // Get methods retrieve the logger instance so we can call it's log methods
    public static Logger getAPPLICATION_LOGGER() {
        return APPLICATION_LOGGER;
    }

    /**
     * main entry into the application
     */
    public static void main(String[] args) {
  
        try {

            // The following 4 statements do a lot of work!
            
            // Create a log file at the location specified by args[0], this is
            // the same location as the I/O files. 
            // This statement also appends the current date to thelog file name 
            // so that every time you run the app, it creats a new log file with 
            // the current date. Note there is a new method in DateFunctions 
            // that is called to format the date for the log file.
            
            // The FileHandler class constructor creates a file object 
            // used for writing log data.
            APPLICATION_FILEHANDLER = new FileHandler(args[0] + "/log_"
                    + DateFunctions.logFileDate(Calendar.getInstance()) + ".txt");

            // Log file output needs to be formatted, we will use a simple 
            // formatter class for the log file
            APPLICATION_FILEHANDLER.setFormatter(new SimpleFormatter());

            // Set the log level. There are several levels of logging built into
            // the Java language. For our app, we will log every event that 
            // contains a log statement.  
            APPLICATION_LOGGER.setLevel(Level.FINEST);             
            
            // Link the file handler created above, to the actual logger object
            APPLICATION_LOGGER.addHandler(APPLICATION_FILEHANDLER);

        } catch (IOException e) {
            // OOPS! Something's wrong with the log file, let's exit rather than allow
            // users to create things anonymously 
            System.exit(0);
        }

        // Create main menu controller, the controller creates the menu form
        MainMenuController controller = new MainMenuController(args[0]);

        // Retrieve the main menu form from the controller and make it visible
        controller.getMainMenu().setVisible(true);

    }

}
