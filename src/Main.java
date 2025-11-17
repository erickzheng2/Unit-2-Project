import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);//sets up scanner for user input
        ArrayList<String> wordList = Game.getFileData("src/data"); //gets the full Wordle word list from the file "data" and uploads it to an arrayList
        Game.targetWord = Game.generateWord(wordList); //sets a public static variable in the object "Game" to a random word from the word list
        System.out.println(Game.targetWord);
        String userInput = "";

        userInput = scan.nextLine();
        if (userInput.equalsIgnoreCase(Game.targetWord)){
            System.out.println("You got it right!!!");
        }
        else{
            System.out.println("Wrong!!!");
        }

    }
}
