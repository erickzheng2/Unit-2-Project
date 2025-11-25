import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);//sets up scanner for user input
        ArrayList<String> wordList = Game.getFileData("src/data"); //gets the full Wordle word list from the file "data" and uploads it to an arrayList
        String userInput = "";
        String hint = "";

        Game.introductionPrint();

        while(!Game.gameEnd) {//while the user has not lost yet
            Game.calcTargetScore(); //calculates the goal the player must reach depending on number of rounds played so far

            System.out.println("YOU MUST REACH " + Game.targetScore + " POINTS TO PASS THIS ROUND");
            System.out.println("______________________________________________________");

            Game.roundWin = false; //sets round win to false again so that another round can being
            while (!Game.roundWin) { //while the user has not passed targetScore yet

                Game.targetWord = Game.generateWord(wordList); //sets a public static variable in the object "Game" to a random word from the word list

                Game.wordCorrect = false; //sets wordCorrect to false again so that another word can be generated and guessed by the player
                while (!Game.wordCorrect){
                    userInput = scan.nextLine(); //waits for user's guess
                    if (userInput.equalsIgnoreCase(Game.targetWord))  //if the user's guess matches the target word, end the round.
                    {
                        System.out.println("You got it right!!!");
                        System.out.println("A new word has been generated and your number of guesses has been reset!");
                        Game.guesses = 1;
                        Game.wordCorrect = true;
                    } else { //else move on to detecting invalid guesses and providing hints for wrong guesses
                        Game.guesses++; //keeps track of amount of guesses
                        System.out.println("You have " + (Game.maxGuesses - Game.guesses) + " guesses remaining...");
                        System.out.println("Your current score is " + Game.userScore + ". You must reach " + Game.targetScore + ".");

                        if (Game.guesses >= Game.maxGuesses){ //when the player runs out of guesses, take away 1 life and randomize the word again
                            Game.lives--;
                            System.out.println("You've run out of guesses. You have " + Game.lives + " lives remaining.");
                            System.out.println("A new word has been generated!");
                            Game.targetWord = Game.generateWord(wordList);
                        }

                        if (!Game.checkValidWord(userInput)) { //lets user know if guess was invalid
                            System.out.println("Your word is not " + Game.targetWord.length() + " characters long!");
                        } else { //else since guess was valid but wrong, generate hint

                            Game.checkYellow(userInput);
                            Game.checkGreen(userInput);
                            Game.renderHintColor(userInput);
                            System.out.println(Game.hint);
                            Game.clearHint();


                        }
                        if (Game.lives == 0){
                            System.out.println("YOU HAVE RUN OUT OF LIVES. GAME OVER.");
                            Game.wordCorrect = true;
                            Game.roundWin = true;
                            Game.gameEnd = true;
                        }
                    }

                }
                Game.calcScore();// calculates player score and evaluates it against target score, if more or equal to, Game.roundWin will be set to true.
            }
            Game.rounds++;//increments every completed round
        }

    }

}


