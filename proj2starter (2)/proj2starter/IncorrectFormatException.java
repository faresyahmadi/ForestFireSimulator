/**
* Represents an incorrect format of a line exception.
*
* @author Fares Yahmadi and Darren Kissi
*/
public class IncorrectFormatException extends Exception {

    /**
    * Calls superclass to pass through an exception message
    *
    *
    * @param line the line number of where the error occured.
    */
    public IncorrectFormatException(int line){
      super("Incorrect format in line: " + line);
    }
  
  }
  