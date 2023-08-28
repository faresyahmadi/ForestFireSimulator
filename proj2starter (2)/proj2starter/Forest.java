// PROJECT 2: you may not add import statements.
//    If you think additional classes need to be imported, contact Prof. Reilly
import java.awt.Color;
import java.util.Arrays;

/**
* Represents a forest in the ForestFire simulation using a 2-dimensional array.
*
* @author Fares Yahmadi and Darren Kissi 
*/
public class Forest {

  // PROJECT 2: you may not add instance or class variables

  /** The two-dimensional array that represents the forest */
  private ForestPatch[][] forest;

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Creates a Forest object by making a deep copy of the parameter array.
  *
  * @param f The array to copy into this Forest object.
  *
  * @throws IllegalArgumentException if the rows of the parameter array are not all the same length.
  */
  public Forest(ForestPatch[][] f) {
	
  	// Project 2: write this method
    this.forest = new ForestPatch[f.length][f[0].length];

    for(int i = 0; i<f.length; i++){
      if(f[i].length == f[0].length){ //checks if it has same row as next element, for the last element, checks if it has same amount of row as previous row
        for(int b = 0; b<f[i].length; b++){
          ForestPatch copy = new ForestPatch(f[i][b].getState());
          this.forest[i][b] = copy;
        }
      }
      else{
        throw new IllegalArgumentException();
      }
    }
  } // end of constructor

   } // end of constructor

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Returns a deep copy of the array representing this Forest object.
  *
  * @return A deep copy of the array representing this Forest object.
  */
  public ForestPatch[][] getForest() {

    // Project 2: write this method
    ForestPatch[][] copy = new ForestPatch[this.forest.length][this.forest[0].length];

    for(int i = 0; i<forest.length; i++){
      for(int b = 0; b<forest[0].length; b++){
        ForestPatch copyobj = new ForestPatch(this.forest[i][b].getState());
        copy[i][b] = copyobj;
      }
    }
    return copy;
  }

    // Placeholder return; replace with actual return when you write this method
   
	   
   // end of getForest method

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Returns an array of the Color objects associated with each patch in this forest.
  *
  * @return An array of the Color objects associated with each patch in this forest.
  */
  public Color[][] getForestColors() {

    // Project 2: write this method
    Color[][] colors = new Color[this.forest.length][this.forest[0].length];

    for(int i = 0; i<forest.length; i++){
      for(int b = 0; b<forest[0].length; b++){
        colors[i][b] = forest[i][b].getState().getColor();
      }
    }
    return colors;
  }

    // Placeholder return; replace with actual return when you write this method
	 

  } // end of getForestColors method

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Runs one step of the forest fire simulation. The next state of forest patch
  * is determined based on the current state of its neighbors.
  */
  public void runOneStep() {

	  // Project 2: write this method
    Forest copy = new Forest(this.forest);
    ForestPatch[][] copyarray = copy.getForest();

    for(int i = 0; i<forest.length; i++){
      for(int b = 0; b<forest[0].length; b++){
        if(forest[i][b]==forest[0][0]){ //top left corner
          ForestPatch[] neighboring = new ForestPatch[2];

          neighboring[0] = copyarray[i][b+1];
          neighboring[1] = copyarray[i+1][b];

          this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
        }
        else if(forest[i][b]==forest[forest.length-1][forest[0].length-1]){ //bottom right corner
          ForestPatch[] neighboring = new ForestPatch[2];

          neighboring[0] = copyarray[i][b-1];
          neighboring[1] = copyarray[i-1][b];

          this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
        }
        else if(forest[i][b]==forest[0][forest[0].length-1]){ //top right corner
          ForestPatch[] neighboring = new ForestPatch[2];

          neighboring[0] = copyarray[i][b-1];
          neighboring[1] = copyarray[i+1][b];

          this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
        }
        else if(forest[i][b]==forest[forest.length-1][0]){ //bottom left corner
          ForestPatch[] neighboring = new ForestPatch[2];

          neighboring[0] = copyarray[i][b+1];
          neighboring[1] = copyarray[i-1][b];

          this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
        }
        else{
          boolean flag = false; //this is to continue the loop after side patches are determined.

          try{
            ForestPatch tester = forest[i][b+1]; //check if it is far right
          }
          catch(ArrayIndexOutOfBoundsException e){
            ForestPatch[] neighboring = new ForestPatch[3];

            neighboring[0] = copyarray[i-1][b]; //once the corners are set, all other elements must have one element above and one below.
            neighboring[1] = copyarray[i+1][b];
            neighboring[2] = copyarray[i][b-1];//if it doesnt have one on the right, it definetely has one on the left

            this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
            flag = true;
            continue;

          }

          try{
            ForestPatch tester = forest[i][b-1]; //check if it is far left
          }
          catch(ArrayIndexOutOfBoundsException e){
            ForestPatch[] neighboring = new ForestPatch[3];

            neighboring[0] = copyarray[i-1][b];
            neighboring[1] = copyarray[i+1][b];
            neighboring[2] = copyarray[i][b+1];

            this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
            flag = true;
            continue;
          }

          try{
            ForestPatch tester = forest[i+1][b]; //check if it is far bottom
          }
          catch(ArrayIndexOutOfBoundsException e){
            ForestPatch[] neighboring = new ForestPatch[3];

            neighboring[0] = copyarray[i-1][b]; //once the corners are set, all other elements must have one element above and one below.
            neighboring[1] = copyarray[i][b+1];
            neighboring[2] = copyarray[i][b-1];//if it doesnt have one on the right, it definetely has one on the left

            this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
            flag = true;
            continue;
          }

          try{
            ForestPatch tester = forest[i-1][b]; //check if it on far top
          }
          catch(ArrayIndexOutOfBoundsException e){
            ForestPatch[] neighboring = new ForestPatch[3];

            neighboring[0] = copyarray[i+1][b];
            neighboring[1] = copyarray[i][b+1];
            neighboring[2] = copyarray[i][b-1];

            this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
            flag = true;
            continue;
          }

          if(!flag){
            ForestPatch[] neighboring = new ForestPatch[4];

            neighboring[0] = copyarray[i-1][b];
            neighboring[1] = copyarray[i+1][b];
            neighboring[2] = copyarray[i][b+1];
            neighboring[3] = copyarray[i][b-1];

            this.forest[i][b] = new ForestPatch(this.forest[i][b].nextState(neighboring));
          }

        }
      }
    }
  } // end of runOneStep method
 
  } // end of runOneStep method

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Returns a string representation of this forest where each patch is represented
  * by its enum value. The string has one line per row of the forest.
  *
  * @return A string representation of this forest.
  */
  @Override
  public String toString() {

    // Project 2: write this method
    String initialmessage = "";
    StringBuilder message = new StringBuilder(initialmessage); //using string builder so I can append the string.

    for(int i = 0; i < forest.length; i++){
      message.append("\n");
      for(int b = 0; b < forest[0].length; b++){
        String enumval = String.valueOf(this.forest[i][b].getState().getChar());
        message.append(enumval);
      }
    }
    return message.toString();

  }

    // Placeholder return; replace with actual return when you write this method
   
  // end of toString method
}


