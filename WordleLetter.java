public class WordleLetter {
	private char letter;
	private String color;

	//WordleLetter Constructor
	public WordleLetter(char letterIn){
		this.letter = letterIn;
	}
	//WordleLetter Setters
	public void setColor(String colorIn){
		this.color = colorIn;
	}
	//WordleLetter Getters
	public String getColor(){
		return this.color;
	}

	/*  -----------------------------------------------------------------------
		TODO - include the below code back in once rest of class is implemented. 
		Do not modify this method implementation. 
		-----------------------------------------------------------------------*/ 
	public String toString() {
		//---------------------------------------------------------------------
		if(color == null) {
        	return Character.toString(letter); // or any other default handling
    	}
    	//----------------------------------------------------------------------
		/*	Determine the special characters to add at the beginning of the String
			to change the text color to the right color. */
		String colorCode;
		if(color.equals("green")) {
			colorCode = "\u001B[32m";
		} else if(color.equals("yellow")) {
			colorCode = "\u001B[33m";
		} else {
			colorCode = "\u001B[31m";
		}
	
		/*	These are the special character to add 
			to the end of the String 
			to signify the end of the color change. */
		String resetCode = "\u001B[0m";

		/*  Surround the letter with space characters and with 
			the above color changing special characters. */ 
		return String.format(
			"%s %s %s",
			colorCode, letter, resetCode);
	}

	//isColorSet required method
	public boolean isColorSet(){
		if(color!=null){
			return true;
		}
		return false;
	}
	//isGreen required method maybe helpful for finishing the game.
	public boolean isGreen(){
		if(color.equals("green")){
			return true;
		}
		return false;
	}
}