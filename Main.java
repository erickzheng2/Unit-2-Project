import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);//sets up scanner for user input
        ArrayList<String> wordList = Game.getFileData("src/data"); //gets the full Wordle word list from the file "data" and uploads it to an arrayList
        //Game.targetWord = Game.generateWord(wordList); //sets a public static variable in the object "Game" to a random word from the word list
        Game.targetWord = "scoop";
        System.out.println(Game.targetWord);
        String userInput = "";
        String hint = "";

        while(!Game.gameEnd) {//while the user has not lost yet
            while (!Game.roundWin) { //while the user has not guessed the word correctly
                userInput = scan.nextLine(); //waits for user's guess

                if (userInput.equalsIgnoreCase(Game.targetWord))  //if the user's guess matches the target word, end the round.
                {
                    System.out.println("You got it right!!!");
                    Game.calcScore();
                    Game.roundWin = true;
                } else { //else move on to detecting invalid guesses and providing hints for wrong guesses
                    System.out.println("Wrong!!!");

                    if (!Game.checkValidWord(userInput)) { //lets user know if guess was invalid
                        System.out.println("Your word is not " + Game.targetWord.length() + " characters long!");
                    } else { //else since guess was valid but wrong, generate hint

                        Game.checkYellow(userInput);
                        Game.checkGreen(userInput);
                        Game.renderHintColor(userInput);
                        System.out.println(Game.hint);
                        Game.clearHint();


                    }


                }
            }
        }

    }

}


