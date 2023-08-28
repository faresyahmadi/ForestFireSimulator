// PROJECT 2: you may not add import statements.
//    If you think additional classes need to be imported, contact Prof. Reilly
import java.io.*;
import java.util.Random;

/**
* Runs the forest fire simulation.
*
* @author Fares Yahmadi and Darren Kissi 
*/
public class ForestSimulation {

  // PROJECT 2: you may not add instance or class variables

  /** The simulated forest */
  private Forest simForest;

  /** The grid display of the simulated forest */
  private DisplayForest simDisplay;

  /**
  * PROJECT 2: DO NOT MODIFY THIS METHOD
  *
  * Creates a ForestSimulation object with a 3x3 grid. All transition probablities
  * are set to 0.5, probability of spontaneous combustion is set to 0. The grid
  * is initialized with the middle patch burning and all other patches in the
  * high growth state.
  *
  * @param inFile The file containing the data for the starting forest.
  */
  public ForestSimulation() {
    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0.5;
    ForestPatch.burnMed_burnMild = 0.5;
    ForestPatch.burnMild_ashes = 0.5;
    ForestPatch.ashes_growLow = 0.5;
    ForestPatch.growLow_growMed = 0.5;
    ForestPatch.growMed_growHigh = 0.5;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 0.5;


    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.BURN_HOT); // middle
    myForestArray[1][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[2][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][2] = new ForestPatch(ForestState.GROW_HIGH); // corner

    simForest = new Forest(myForestArray);

    simDisplay = new DisplayForest(simForest);

  } // end of default constructor

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Creates a ForestSimulation object using data from a file as the starting forest.
  *
  * @param inFile The file containing the data for the starting forest.
  */
  public ForestSimulation(String inFile) {

     // Project 2: write this method

    // Suggested steps: use similar steps as shown in the default constructor.
    // Call the ForestIO method that reads a forest data file to initialize
    // the ForestPatch static variables and to create the array of ForestPatch objects.
    try{
      ForestIO initializer = new ForestIO();
  
      ForestPatch[][] myForestArray = initializer.readFile(inFile);
  
      this.simForest = new Forest(myForestArray);
  
      this.simDisplay = new DisplayForest(simForest);
      }
      catch(FileNotFoundException | IncorrectFormatException | IncorrectLineException e){
        if(e instanceof FileNotFoundException){
          System.out.println("File not found.");
        }
        else{
          System.out.println(e.getMessage());
        }
        System.exit(0);
      }
  
    } // end of parameterized constructor

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Runs the forest fire simulation for the specified number of steps then saves
  * the ending state to the output file.
  *
  * @param steps The number of steps of the simulation
  */
  public void runSimulation(int steps, String outFile) {

    // Project 2: write this method

    // Suggested steps:
    // 1) Run the simulation for the specified number of steps.
    // 2) Call the ForestIO method that writes the forest data file to save
    //    the current forest state to a file.
    for (int i = 0; i < steps; i++) {
      this.simForest.runOneStep();
      this.simDisplay.update();
    }

    ForestIO output = new ForestIO();

    try{
      output.outputFile(simForest.getForest(), outFile);
    }
    catch(FileNotFoundException e){
      System.out.println("File not found.");
      System.exit(0);
    }

  } // end of runSimulation method

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * main method starts the simulation. Command line arguments: the input
  * file pathname, the output file pathname, and the number of simulation steps.
  *
  * @param args The pathnames for the input and output files
  */
  public static void main(String[] args) {

    // Project 2: write this method

    // Suggested steps:
    // 1) Validate the command line arguments.
    // 2) Create a ForestSimulation object.
    // 3) Run the simulation for the specified number of steps.
    if(args.length != 3){
      System.out.println("Expected three command line arguments. Found: " + args.length);
      System.exit(0);
    }
    ForestPatch.rand = new Random(5000); //put for testing.
    ForestSimulation run = new ForestSimulation(args[0]);
    run.runSimulation(Integer.valueOf(args[2]), args[1]);

  } // end of main method
} // end of ForestSimulation class
