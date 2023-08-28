/**
* Represents an incorrect amount of line exception.
*
* @author Fares Yahmadi and Darren Kissi 
*/
public class IncorrectLineException extends Exception {

    /**
    * Calls superclass to pass through an exception message
    *
    *
    * @param line lower bound of the amount of lines in the file.
    * @param expected the expected amount.
    */
    public IncorrectLineException(int line, int expected){
      super("Not enough lines. Actual: " + line +  " Expected: " + expected);
    }
    /**
    * Calls superclass to pass through an exception message
    *
    *
    *
    * @param expected the expected amount of lines.
    */
    public IncorrectLineException(int expected){
      super("File may have more than expected lines. Expected: " + expected);
    }
  
  }
  