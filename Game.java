import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static String targetWord = "";
    public static boolean roundWin = false;
    public static String hint = "XXXXX";

    public static String generateWord(ArrayList<String> wordList) {
        String word = wordList.get((int) (Math.random() * (wordList.size() + 1))); //generates random number between 0 and the max size of the wordList and sets "word" to that random index
        return word;
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>(); //sets up an array of strings to upload file data into
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f); // creates scanner object to scan the previously created file object's data
            //this is like Scanner(System.in) but instead of the System input, it reads from file "data"
            while (s.hasNextLine()) { //repeat until every line of file f has been read
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line); //adds every line to a separate entry of ArrayList fileData.
            }
            return fileData;
        } catch (FileNotFoundException e) { //catches the error in case file is not found
            return fileData;
        }
    }

    public static boolean checkValidWord (String word){ // checks if a word is a valid number of characters
        return word.length() == targetWord.length();
    }

    public static void checkGreen (String guess) {
        for (int x = 0; x < guess.length(); x++) {
            if (guess.substring(x, x + 1).equalsIgnoreCase(Game.targetWord.substring(x, x + 1))){ //compares the characters of user guess and target word directly
                Game.hint = Game.hint.substring(0, x) + "O" + Game.hint.substring(x + 1); //if a match is found, "insert" a 0 at that location
                }
            }
        }

    public static void checkYellow (String guess){
        for (int x = 0; x < guess.length(); x++){
            for (int y = 0; y < targetWord.length(); y++){
                if(guess.substring(x, x + 1).equalsIgnoreCase(Game.targetWord.substring(y, y + 1)) && x != y){
                        Game.hint = Game.hint.substring(0, x) + "?" + Game.hint.substring(x + 1); //if a match is found, "insert" a 0 at that location
                    }
                }
            }
        }

//
//    public static void checkGrey (String guess){}

    public static void clearHint (){ //static function to clear hint
        Game.hint = "XXXXX";
    }
}





