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
        String hint = "";

        while(!Game.roundWin){ //while the user has not guessed the word correctly
            userInput = scan.nextLine(); //waits for user's guess

            if (userInput.equalsIgnoreCase(Game.targetWord))  //if the user's guess matches the target word, end the round.
            {
                System.out.println("You got it right!!!");
                Game.roundWin = true;
            }else{
                System.out.println("Wrong!!!");

                if(!Game.checkValidWord(userInput)){
                    System.out.println("Your word is not " + Game.targetWord.length() + " characters long!");
                }else{
                    for(int x = 0; x < Game.targetWord.length(); x++){
                        for(int y = 0; y < Game.targetWord.length(); y++){
                            if(userInput.substring(x, x + 1).equalsIgnoreCase(Game.targetWord.substring(y, y + 1))){
                                if (x == y){
                                    hint += "O";
                                } else {
                                    hint += "?";
                                }
                            }


                        }



                    }

                }
            }


        }

    }
}

