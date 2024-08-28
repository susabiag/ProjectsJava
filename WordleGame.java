import java.io.FileNotFoundException;

public class WordleGame {
  /* allGuesses represents the wordle game */
  //The fixed 2D array of all potential guesses
  private WordleLetter[][] allGuesses = new WordleLetter[6][5];
  private String answer;

  //For getAnswer()
  private int puzzleNumber;

  //For guess()
  private String guessWord;

  //Initiate number of guesses in order to make an if statement and start working with it
  private int numberOfGuesses=0;

  
  //Constructor for WordleGame
  //Because I can only get one parameter I used getAnswer from another method to introduce this.answer to getAnswer();
  public WordleGame(int puzzleNumberIn){
    this.puzzleNumber = puzzleNumberIn;
    this.answer = getAnswer();
    
  }
  
  //getAnswer method
  //Uses the WordBank file and gets the answer from the given puzzleNumber.
  public String getAnswer(){
    return WordBank.getAnswerForPuzzleNumber(puzzleNumber);
  }

//guess() method
 public void guess(String guessWord){
        //Stops when maximum number of guesses
        if(numberOfGuesses >= 6){ 
            System.out.println("Maximum number of guesses reached.");
            //return nothing when achieved because its a void method.
            return; 
        }
        //Added [] because a 1D array of letters 
        WordleLetter[] guessLetters = new WordleLetter[5]; 
        boolean isCorrect = true; 

        //At first I assing a default color red and then I add a for loop and if the position matches exactly I assign the color yellow to the letter.
        for(int i = 0; i < guessWord.length(); i++){ 
            char guessedChar = guessWord.charAt(i); 
            WordleLetter letter = new WordleLetter(guessedChar);
            //Debugging code
            //MAKING SURE ITS NOT NULL
            if (guessLetters[i] == null) {  
                guessLetters[i] = new WordleLetter(guessedChar);
            }
            //^^^ MAKING SURE ITS NOT NULL
            //---------------BEFORE WAS GUESSLETTERS[I].SETCOLOR("RED") // Create a new WordleLetter object for the guessed character
            letter.setColor("red");
            //------------------------------

            if(guessedChar == answer.charAt(i)){ 
              //---------------BEFORE WAS LETTER.SETCOLOR("RED")
                letter.setColor("green"); 
                //------------------------------
            }else{
                boolean foundMatch = false; 
                //Check if the guessed character matches the character in the answer
                for(int j = 0; j < answer.length(); j++){
                    if(guessedChar == answer.charAt(j)){ 
                      //---------------BEFORE WAS LETTER.SETCOLOR("RED")
                      //Set the color to yellow for a correct character in the wrong position
                        letter.setColor("yellow"); 
                        //------------------------------
                        foundMatch = true; 
                        break; 
                    }
                }
                //If no match set color to red
                //I assign red again due to debugging in order to make sure the setColor parameter is not null and avoid errors
                if(!foundMatch){
                    letter.setColor("red"); 
                    isCorrect = false; 
                }
            }
            //Storing the WordleLetter object in the guessLetters array
            guessLetters[i] = letter; 


            //Debugging status of the Letter 
            //-----------------------------------------------------------------------------------
            //System.out.println("Guessed Char: " + guessedChar + ", Color: " + letter.getColor());
            //-----------------------------------------------------------------------------------
        }
        //Store the array of guessed letters in the allGuesses array at the current index
        allGuesses[numberOfGuesses] = guessLetters; 
        // If the entire word was guessed correctly
        if(isCorrect==true){ 
            //If the entire word was guessed correctly
            numberOfGuesses = 6; 
        }else{
            numberOfGuesses++;
        }
        
    }

    //returns the numberOfGuesses
    public int getNumberGuessesSoFar(){
      return numberOfGuesses;
    }

    public WordleLetter[] getGuess(int guessNumber){
      //Reassures if guessNumber more than 6 return nothing
        if (guessNumber >= numberOfGuesses) {
            return null; 
        }
        //Obviously if within range return the correct value
        return allGuesses[guessNumber];
    }
  
   public boolean isGameWin(){
    if(numberOfGuesses<=0){
      return false;
    }
    //Getting the array of WordleLetters for the last guess made 1D array.
    WordleLetter[] lastGuess = allGuesses[numberOfGuesses-1];

    //Check every WordleLetter in the last guess
    for(int i = 0; i < lastGuess.length; i++){
        if(!lastGuess[i].isGreen()){
            return false;
        }
    }
    //If all letters are green, the player has won
    return true; 
    }
    //GameOver if more than 6 guesses or if they won
    public boolean isGameOver(){
        if(numberOfGuesses >= 6 || isGameWin()) {
            return true;
        }
        return false;
    }




  public String toString(){
    /* result will be used to build the full answer String */ 
    String result = ""; 
       /* for each word guessed so far */ 
    for(int i = 0; i < getNumberGuessesSoFar(); i++){
         /* get each letter of each word */
      for(int j = 0; j < 5; j++){
           /* concatenate it to the result */ 
           /* WordleLetter's toString() is automatically invoked here. */
         result += getGuess(i)[j];
      }
         /* new line separator between each word */ 
       result += "\n";
    }
    return result;
  }
}

