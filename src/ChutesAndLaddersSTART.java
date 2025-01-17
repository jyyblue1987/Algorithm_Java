/*
 *        Class: IT-516 - Data Structures & Algorithms
 *   Instructor: 
 *         Date: DD MM YYYY
 *      Student: Student Name
 *   Assignment: HW07
 *
 *   Compile: javac-algs4 ChutesAndLaddersSTART.java
 *
 *       Run: java-algs4 ChutesAndLaddersSTART 4 [four player game]
 */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class ChutesAndLaddersSTART {
    public static void main(String[] args) {
        int numPlayers = Integer.parseInt(args[0]);
        if( numPlayers < 1 || numPlayers > 4 )
        {
            StdOut.println("Player's Count should be between 1 and 4:");
            return;
        }
//        int numPlayers = 3;
        StdOut.println("Welcome to Chutes and Ladders!");
        StdOut.println("This will be a " + numPlayers + " player game.");
        StdOut.println("The game begins now:");

        Integer[] playerSpace = new Integer[numPlayers];                           // Array to hold player's current space
        int dieRolls = 0;                                                          // Count of all times the die is rolled
        int [] dieRollsPalyer = new int[numPlayers];                               // Make an array to hold count of player's rolls
        int [] dieRollResult = new int[6];                                         // Make an array to hold most common die rolls
        int [][] dieRollPlayerResult = new int[numPlayers][6];
        int [] climbed = new int[numPlayers];                                      // Make an array to hold number of ladders a player climbs
        int [] slides = new int[numPlayers];                                       // Make an array to hold number of chutes a player slides down

        for (int j = 0; j < numPlayers; j++) {                                     // Initialize all the values in all arrays to 0
            playerSpace[j] = 0;
        }

//        In in = new In("spacesSTART.txt");                                         // This can be changed to a different, local file with Digraph data in it
        In in = new In("spaces.txt");
        Digraph spacesDigraph = new Digraph(in);
        int winningSpace = spacesDigraph.V() - 1;

        // Main Game Loop
        for (int i = 0; i < 1000000; i++) {                                        // Arbitrary big number of turns. We use "break" command at bottom to exit.
            int player = i % numPlayers;                                           // Mathematically determines which player's turn it is by remainder
            Random r = new Random();                                               // Create a random number holder
            int roll = r.nextInt((6)) + 1;                                         // Create an integer to hold the random number & randomize
            dieRollResult[roll - 1]++;
            dieRollPlayerResult[player][roll - 1]++;

            dieRolls++;
            dieRollsPalyer[player]++;
            // Increment times die has been rolled
            //playerRolls[player]++;                                                 // Increment times player has rolled the die
            // commonDieRolls[roll * numPlayers - 1]++;                               // Increment this die roll in the array of die rolls per player
            StdOut.println("Player " + (player + 1) + " rolls a " + roll);
            playerSpace[player] = playerSpace[player] + roll;                      // "Move" the player forward by the die roll
            StdOut.println("         Moves to space " + playerSpace[player]);
            if (playerSpace[player] < winningSpace) {
                int numJumps = spacesDigraph.outdegree(playerSpace[player]);           // Check the digraph to see if there are any chutes or ladders
                if (numJumps != 0) {            // and if there are (and player hasn't already moved past 100)
                    for (Integer bigMove : spacesDigraph.adj(playerSpace[player])) {   // get ready to move them.
                        if (playerSpace[player] < bigMove) {                           // If it's a ladder (player space is less than digraph space)
                            playerSpace[player] = bigMove;                             // move them forward.
                            StdOut.println("         Climbs ladder to space " + bigMove);
                            climbed[player]++;
                        } else {                                                       // Otherwise, it's a chute (player space is more than digraph space)
                            playerSpace[player] = bigMove;                             // so move them back.
                            StdOut.println("         Slides down chute to space " + bigMove);
                            slides[player]++;
                        }
                    }
                }
            }
            if (playerSpace[player] >= winningSpace) {                             // If player's new space is greater than or equal to the win space
                StdOut.println("Player " + (player + 1) + " wins!");               // display end of game message
                break;                                                             // and exit the loop.
            }
        }

        StdOut.println();
        StdOut.println("End of game stats:");
        StdOut.println();
        StdOut.println("Total die rolls for the game " + dieRolls);
        for (int z = 0; z < numPlayers; z++) {                                     // Loop through die roll stats
            StdOut.println("Total die rolls for player " + (z + 1) + " is " + dieRollsPalyer[z]);   // Replace ??? with variable for player rolls
        }
        Integer[] commonDieRollsResults = new Integer[6];                          // Create "6-sided" array for each die value
        for (int w = 0; w < 6; w++) {                                              // Loop through common die roll results
            commonDieRollsResults[w] = dieRollResult[w];                                          // You'll need to add all your player common die rolls here, rather than 0
        }
        int max = commonDieRollsResults[0];                                        // Create an integer to hold most-rolled die value
        int index = 0;

        for (int p = 1; p < 6; p++) {                                              // Find max value by looping through results
            if (max < commonDieRollsResults[p]) {
                max = commonDieRollsResults[p];
                index = p;
            }
        }
        StdOut.println();
        StdOut.println("Most common die roll for the game is " + (index + 1));

        for(int i = 0; i < numPlayers; i++) {
            for (int w = 0; w < 6; w++) {                                              // Loop through common die roll results
                commonDieRollsResults[w] = dieRollPlayerResult[i][w];                   // You'll need to add all your player common die rolls here, rather than 0
            }
            int max1 = commonDieRollsResults[0];
            int index1 = 0;

            for (int p = 1; p < 6; p++) {                                              // Loop through to find it
                if (max1 < commonDieRollsResults[p]) {
                    max1 = commonDieRollsResults[p];
                    index1 = p;
                }
            }
            StdOut.println("Most common die roll for player" + (i + 1) + " is " + (index1 + 1));
        }

        StdOut.println();
        int gameLadders = 0;
        for (int l = 0; l < numPlayers; l++) {                                      // Sum game ladders climbed
            gameLadders = gameLadders + climbed[l];                                          // Do a calculation here to replace 1
        }
        StdOut.println("Total ladders climbed for the game is " + gameLadders);
        for (int l = 0; l < numPlayers; l++) {
            StdOut.println("Total ladders climbed for player " + (l + 1) + " is " + climbed[l]);  // Do a calculation here to replace 0
        }
        StdOut.println();
        int gameChutes = 0;                                                         // Sum game chutes slid
        for (int l = 0; l < numPlayers; l++) {
            gameChutes = gameChutes + slides[l];                                            // Do a calculation here to replace 1
        }
        StdOut.println("Total chutes slid for the game is " + gameChutes);
        for (int l = 0; l < numPlayers; l++) {
            StdOut.println("Total chutes slid for player " + (l + 1) + " is " + slides[l]);  // Do a calculation here to replace 0
        }
        StdOut.println();
    }
}
