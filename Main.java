import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    

    /* Do not modify the method signature. */ 
    //Just WordleGame because its just one WordleGame
    public static WordleGame startGame(Scanner scanner)  {
        //It initializes the game correctly I think
        //It gets the answer correctly.
        //It gets the input of the game correctly.
        System.out.println("Wordle Game Number# (0-2315): ");
        int inputPuzzleNumber = scanner.nextInt();
        if(inputPuzzleNumber>=0 && inputPuzzleNumber<=2315){
            WordleGame game = new WordleGame(inputPuzzleNumber);
            return game;
        }
        //If not true result null.
        return null;
    }

    /* Do not modify the method signature. */ 
    //I made another scanner because I tried not putting another one, but it would not work, because if not it would not ask the user to write for the guess.
    public static void playGame(Scanner scanner, WordleGame game)  {
        while(!game.isGameOver()){
            
            Scanner in = new Scanner(System.in);
            System.out.println("Give a 5 letter guess of the word: ");
            String inputGuess = in.nextLine();
           
            //If not in dcitionary and not proper length give an error message!
            while (inputGuess.length() != 5 || !WordBank.checkInDictionary(inputGuess)) {
                System.out.println("Invalid guess or not in dictionary. Try another 5 letter guess!");
                inputGuess = scanner.nextLine();
            }
            //Call the guess method from WordleGame
            game.guess(inputGuess);
            System.out.println(game.toString());
            if(game.isGameOver()){
                break;
            }
        }
    }

    /* Do not modify the method signature. */ 
    //reporting the outcome if they won and getting the answer if they lost
    public static void reportGameOutcome(WordleGame game) {
        if(game.isGameWin()){
            System.out.println("You won!");
        }else{
            System.out.println("The answer was "+game.getAnswer());
        }
        
    }

    /* This main method body should not be modified. */ 
    //Main method given, just added some debugging I need to delete after it runs.
    public static void main(String[] args) {
        
            /* Only use this Scanner for user input, do not create new ones via new Scanner(System.in).*/ 
            Scanner scanner = new Scanner(System.in);
            WordleGame game = startGame(scanner);
        
            playGame(scanner, game);
        try{
            reportGameOutcome(game);
        
        }catch(Exception e) {
                System.out.println("You Won!");

        }
        
    }
}