import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/* Santiago Usabiaga
 [CS1101] Comprehensive Lab 1
 This work is to be done individually. It is not permitted to.
 share, reproduce, or alter any part of this assignment for any
 purpose. Students are not permitted to share code, upload
 this assignment online in any form, or view/receive/
 modifying code written by anyone else. This assignment is part.
 of an academic course at The University of Texas at El Paso and
 a grade will be assigned for the work produced individually by
 the student.
*/
/*Check in: 1.For this solution first I ask for user input for asking the file path
			2.I load the maze without being hardcoded I just put the memory on the row because java allows it and it makes it easier, I also use a try and catch scenario.
			3.I download the first position in order to know where the initial position is inside the maze and return the maze.
			4.Made a method in order to print maze, makes it easier later on(in the while loop).
			5.Make a movePlayer method that returns a boolean, but mainly moves the position inside the maze based on the decision of the movements.
			6.The main while loop is based on the boolean from the movePlayer method. When the condition is met it stops asking for movements and no user input is asked because we escaped.
			
*/

public class CL2_Usabiaga{
    
    public static void main(String[] args){
    	//File reading
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String filePath = scanner.nextLine();

        //Variable for ending main loop
        boolean end = false;
    


        //while Loop in order to avoid hardcoding the array memory.
        int rowsCount = 0;
        try{
        	Scanner countRow = new Scanner(new File(filePath));
	        while(countRow.hasNextLine()){
	            	rowsCount++;
	            	countRow.nextLine();
	        }
	        countRow.close();
	    }catch(Exception e){
            System.out.println("Error reading file! " + e);
        }
        
        
        

        //Storing the array without the initial position
        String [][] mazeDisplay = readFileDisplayMaze(filePath, rowsCount);
        
        
      	//First display of the maze containing the P initial value
        String [][] initialPosition = firstPosition(mazeDisplay);

        //Asking the decision for the first time, in order to start a while loop
        System.out.println("Move (WASD): ");
        String decision = scanner.nextLine();

        //Converting everything to lower case for user friendly interface
        decision = decision.toLowerCase();


        //Next move until they choose correct input
        Scanner scannerMethod = new Scanner(System.in);


    	//Main loop
    	while(end==false){
		    if(decision.equals("w")||decision.equals("a")||decision.equals("s")||decision.equals("d")){
		    	//Moves the position and returns boolean if true ends loop.
		        end = movePlayer(mazeDisplay, decision);
		        //Prints updated maze
		        printMaze(mazeDisplay);
		        if(end==false){
		        	//asks until position reaches F.
		        	System.out.println("Move (WASD): ");
		            decision = scanner.nextLine().toLowerCase();
		        }else{
		            System.out.println("Congrats you won!");
		        }
		    }else{
		    	//If user input wrong
		        System.out.println("Choose a correct option.");
		        System.out.println("Move (WASD): ");
		        decision = scanner.nextLine().toLowerCase();
		    }
		}
        

        
    }
    
    //This reads the maze and displays it
    //I need to store it in a new array in order to play with it.
    public static String[][] readFileDisplayMaze(String filePath, int rowsCount){
        try{
            Scanner file = new Scanner(new File(filePath));
            int row = 0;
            //In order to avoid hardcoding the square. I just have to count the amount of rows because its a sqaure and apply that memory.
            String [][] maze = new String[rowsCount][];
            while(file.hasNextLine()){
                maze[row] = file.nextLine().trim().split("");
                row++;
            }
            //Clsoing file in order to avoid errors.
            file.close();
            return maze;
            
            
        }catch(Exception e){
            System.out.println("You cannot go oustide the maze!  or file not read properly!" + e);
            System.exit(0);
            return new String[0][0];
        }
        

        
        
    }

    //Create a method to see where it all starts.
    //Put a P on the S so we know where to start.
    public static String [][] firstPosition(String [][] arr){
    	for(int i =0;i<arr.length;i++){
    		for(int j = 0;j<arr[i].length;j++){
    			if(arr[i][j].equals("S")){
    				arr[i][j]="P";
    			}
    			System.out.print(arr[i][j]);
    		}
    		//Prints a new line in order to avoid printing everything in one line.
    		System.out.println();
    		

    	}
    	return arr;
    } 

    //A method to print the maze to make it easier
    public static void printMaze(String [][] arr){
    	for(int i = 0;i<arr.length;i++){
    		for(int j = 0;j<arr[i].length;j++){
    			System.out.print(arr[i][j]);
    		}
    		System.out.println();
    	}
    }

    //A method that moves the player and displays the P an leaves behind a .
    //Restricts access to walls
    //It also returns a boolean in order to end the main while loop
    public static boolean movePlayer(String [][] arr,String decision){
	    int currentPositionRow = 0;
	    int currentPositionColumn = 0;
	    int newPositionRow = 0;
	    int newPositionColumn = 0;
	    
	    for(int i = 0; i < arr.length; i++){
	        for(int j = 0; j < arr[i].length; j++){
	            if(arr[i][j].equals("P")){
	                currentPositionRow = i;
	                currentPositionColumn = j;
	                newPositionRow = i;
	                newPositionColumn = j;
	            }
	        }
	    }

	    
	    if(decision.equals("a")){
	        newPositionColumn -= 1;
	    }else if(decision.equals("d")){
	        newPositionColumn += 1;
	    }else if(decision.equals("s")){
	        newPositionRow += 1;
	    }else if(decision.equals("w")){
	    	newPositionRow -=1;
	    }
	    //It restricts movement when trying to pass through walls
		if(arr[newPositionRow][newPositionColumn].equals("#")){
		    System.out.println("Cannot pass through walls");
		    return false;
		   	
		}
		//Stops the maze when you escape and puts the P over the F.
		if(arr[newPositionRow][newPositionColumn].equals("F")){
			arr[currentPositionRow][currentPositionColumn] = ".";
			arr[newPositionRow][newPositionColumn]="P";
			return true;
		}
		//This acts as the movement replaces the the . to P after each movement
		arr[currentPositionRow][currentPositionColumn] = ".";
		arr[newPositionRow][newPositionColumn] = "P";
		return false;
	
	    		


	}


}	
