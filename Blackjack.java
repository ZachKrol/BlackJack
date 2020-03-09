/*
Title: Blackjack Casino Game
Author: Zach Krol
Last Updated: 02-05-2020
*/

import java.util.Scanner;
public class Blackjack
{
    public static void main(String[] args)
    {
        // Declare variables that will be used throughout code
        Scanner scanner = new Scanner(System.in);
        P1Random rng = new P1Random();
        int selection=0;
        int gameCounter=1;
        int myWin=0;
        int dealerWin=0;
        int tieCounter=0;

        // Game loop. Repeats as long as user doesn't exit code by selecting option 4
        while (selection != 4)
        {
            System.out.println("START GAME #" + gameCounter);

            // Reset player hand to 0 at start of new game
            int myHand = 0;

            // Loop for drawing a new card
            // Always true because 'break' and 'continue' are used inside the loop to control flow
            while (true)
            {
                // When statistics are printed(3),  the player doesn't draw a card
                if (selection!=3)
                {
                    // Sequence for drawing a card
                    // Generate number, display card, calculate point value
                    int myNum = rng.nextInt(13) + 1;

                    if (myNum > 1 && myNum <11)
                    {
                        System.out.println("Your card is a " + myNum + "!");
                        myHand+=myNum;
                    }
                    else if(myNum == 1)
                    {
                        System.out.println("Your card is a ACE!");
                        myHand++;
                    }
                    else if(myNum == 11)
                    {
                        System.out.println("Your card is a JACK!");
                        myHand+=10;
                    }
                    else if(myNum == 12)
                    {
                        System.out.println("Your card is a QUEEN!");
                        myHand+=10;
                    }
                    else if(myNum == 13)
                    {
                        System.out.println("Your card is a KING!");
                        myHand+=10;
                    }

                    // Display player's hand after each card drawn
                    System.out.println("Your hand is: " + myHand);

                    // Check for BLACKJACK or BUST
                    // Uses 'break' to exit card-drawing loop
                    // Update win and game counters
                    if (myHand == 21)
                    {
                        System.out.println("BLACKJACK! You win!");
                        myWin++;
                        gameCounter++;
                        break;
                    }
                    else if (myHand > 21)
                    {
                        System.out.println("You exceeded 21! You lose.");
                        dealerWin++;
                        gameCounter++;
                        break;
                    }
                }

                // Prompt user for next action
                System.out.println(
                    "1.  Get another card\n" +
                    "2.  Hold hand\n" +
                    "3.  Print statistics\n" +
                    "4.  Exit\n");

                System.out.println("Choose an option: ");
                selection = scanner.nextInt();

                // if new card, restart card-drawing loop
                if (selection==1)
                {
                    continue;
                }
                else //noinspection StatementWithEmptyBody
                    if (selection==2)
                {
                // I guess I could have put the remaining code in the card-drawing loop in here...
                }
                else if (selection==3)
                {
                    System.out.println("Number of Player wins: " + myWin);
                    System.out.println("Number of Dealer wins: " + dealerWin);
                    System.out.println("Number of tie games: " + tieCounter);
                    System.out.println("Total # of games played is: " + (gameCounter-1));
                    double winAverage= Math.round(((double)myWin/((double)gameCounter-1.0))*1000.0/10.0);
                    System.out.println("Percentage of Player wins: " + winAverage + "%");
                    continue;
                }
                else if(selection==4)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid input!\nPlease enter an integer value between 1 and 4.");
                    selection=3;
                    continue;
                }

                // Generate dealer's hand
                int dealerHand = rng.nextInt(11) + 16;

                System.out.println("Dealer's hand: " + dealerHand);
                System.out.println("Your hand is: " + myHand);

                // If statements to determine a winner or tie
                // Dealer BUST
                if (dealerHand > 21)
                {
                    System.out.println("You win!");
                    myWin++;
                    gameCounter++;
                    break;
                }
                // Dealer's hand is higher
                else if (dealerHand > myHand)
                {
                    System.out.println("Dealer wins!");
                    dealerWin++;
                    gameCounter++;
                    break;
                }
                // Player's hand is higher
                else if (myHand > dealerHand)
                {
                    System.out.println("You win!");
                    myWin++;
                    gameCounter++;
                    break;
                }
                // Same hand. Tie
                else
                {
                    System.out.println("It's a tie! No one wins!");
                    tieCounter++;
                    gameCounter++;
                    break;
                }
            }
        }
    }
}
