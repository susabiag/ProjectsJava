import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/* Santiago Usabiaga
 [CS1101] Comprehensive Lab 3
 This work is to be done individually. It is not permitted to.
 share, reproduce, or alter any part of this assignment for any
 purpose. Students are not permitted to share code, upload
 this assignment online in any form, or view/receive/
 modifying code written by anyone else. This assignment is part.
 of an academic course at The University of Texas at El Paso and
 a grade will be assigned for the work produced individually by
 the student.
*/

public class WordBank {

  /*  This first method implementation is completed for you already. 
      Do not modify the method signature 
   */
  public static String getAnswerForPuzzleNumber(int puzzleNumber) {
    try {
      /* Create a file scanner to read answers.txt */
      Scanner scanner = new Scanner(new File("answers.txt"));

      /* Skip the first puzzleNumber number of words in the file */
      for (int i = 0; i < puzzleNumber; i++) {
        scanner.next();
      }

      /* Return the very next word */ 
      return scanner.next();

    } catch (Exception e) {
      /* Handle exception */
      System.out.println("Input/File not found!");
    }
    return null;
  }

  /* Do not modify the method signature. */
  //checkInDictionary if the word input by the user equals one inside the dictionary it is valid and is return true, else false.
  public static boolean checkInDictionary(String proposed) {
    try{
      Scanner dictScanner = new Scanner(new File("dictionary.txt"));
      //Until there is a next line keep scanning
      while(dictScanner.hasNextLine()){
        //Tenporary variable to prove if given word is in dictionary
        String temp = dictScanner.nextLine();
        //If String given equals the temporary word inside the dictionary return true
        if(proposed.equals(temp)){
          return true;
        }
      }
      //Exception Handling
    }catch(Exception e){
      System.out.println("Input/File not found!");
    
    }
    //Return false if not inside of dictionary.
    return false;
    
  }
}