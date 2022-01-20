package com.roynulrohan;

/*
Roynul Rohan
ICS3U
BattleShip Game
*/

import java.util.Scanner;

public class Battleship {
    public static Scanner input = new Scanner(System.in);
    public static char[][] playerGrid = new char[10][10];
    public static char[][] botGrid = new char[10][10];
    public static char emptySpace = '.';
    public static char hit = 'x';
    public static char miss = 'm';

    public static int battleship, cruiser, destroyer, submarine;
    public static int botBattleship, botCruiser, botDestroyer, botSubmarine;
    public static int playerShips = 4, botShips = 4;

    public static boolean win = false;

    public static void main(String[] args) {
        String move = "", botMove = "";
        int i = -1, j = -1;

        makeGrid();
        makeShips(playerGrid);
        makeShips(botGrid);
        shipCount();

        do {
            clearScreen();
            shipDisplay();
            printGrid();

            System.out.println();

            if (i != -1 && j != -1) {
                if (botGrid[i][j] == hit) {
                    System.out.println(intToChar(j) + "" + i + " was a hit!");
                } else if (botGrid[i][j] == miss) {
                    System.out.println(intToChar(j) + "" + i + " was a miss.");
                }
            }

            if (!botMove.equals("")) {
                System.out.print("Enemy attacked " + botMove);

                if (playerGrid[Integer.parseInt(botMove.substring(1))][charToInt(botMove.charAt(0))] == hit) {
                    System.out.println(" and hit.");
                } else if (playerGrid[Integer.parseInt(botMove.substring(1))][charToInt(botMove.charAt(0))] == miss) {
                    System.out.println(" and missed.");
                }
            }

            shipUpdates();

            System.out.print("\nSelect a location on the enemy's grid. (eg D7)\nEnter location: ");
            do {
                do {
                    move = input.nextLine().trim().toUpperCase();
                    if (isValid(move)) {
                        System.out.print("Error. Try again: ");
                    }
                } while (isValid(move));

                i = Integer.parseInt(move.substring(1));
                j = charToInt(move.charAt(0));

                if (isEmpty(i, j, botGrid)) {
                    System.out.print("Location already attacked, try again: ");
                }
            } while (isEmpty(i, j, botGrid));

            attack(i, j, botGrid);
            botMove = botMove();
            shipCount();

            win = checkWin();
        } while (!win);

        clearScreen();
        shipDisplay();
        printGrid();

        System.out.print("\n\nGame Over.");
        if (playerShips == 0) {
            System.out.println(" The enemy won.");
        } else if (botShips == 0) {
            System.out.println(" You won!");
        }
    }

    public static String botMove() {
        int i, j;
        String move;

        do {
            i = getRand(0, 10);
            j = getRand(0, 10);
        } while (isEmpty(i, j, playerGrid));

        attack(i, j, playerGrid);
        move = intToChar(j) + String.valueOf(i);

        return move;
    }

    public static boolean checkWin() {
        return playerShips == 0 || botShips == 0;
    }

    public static boolean isValid(String move) {
        if (move != null && !move.equals("")) {
            if (move.length() == 2) {
                if (move.charAt(0) == 'A' || move.charAt(0) == 'B' || move.charAt(0) == 'C' || move.charAt(0) == 'D' || move.charAt(0) == 'E' || move.charAt(0) == 'F' || move.charAt(0) == 'G' || move.charAt(0) == 'H' || move.charAt(0) == 'I' || move.charAt(0) == 'J') {
                    for (int i = 0; i < 10; i++) {
                        if (Integer.parseInt(move.substring(1)) == i) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean isEmpty(int i, int j, char[][] grid) {
        return grid[i][j] == hit || grid[i][j] == miss;
    }

    public static void attack(int i, int j, char[][] grid) {
        if (grid[i][j] == emptySpace) {
            grid[i][j] = miss;
        } else {
            grid[i][j] = hit;
        }
    }

    public static void shipUpdates() {
        if (battleship == 0) {
            battleship = -1;
            System.out.println("\nYour battleship has been destroyer! You have " + playerShips + " ships left.");
        } else if (cruiser == 0) {
            cruiser = -1;
            System.out.println("\nYour cruiser has been destroyed! You have " + playerShips + " ships left.");
        } else if (destroyer == 0) {
            destroyer = -1;
            System.out.println("\nYour destroyer has been destroyed! You have " + playerShips + " ships left.");

        } else if (submarine == 0) {
            submarine = -1;
            System.out.println("\nYour submarine has been destroyed! You have " + playerShips + " ships left.");

        }

        if (botBattleship == 0) {
            botBattleship = -1;
            System.out.println("\nEnemy's battleship has been destroyed! They have " + botShips + " ships left.");
        } else if (botCruiser == 0) {
            botCruiser = -1;
            System.out.println("\nEnemy's cruiser has been destroyed! They have " + botShips + " ships left.");

        } else if (botDestroyer == 0) {
            botDestroyer = -1;
            System.out.println("\nEnemy's destroyer has been destroyed! They have " + botShips + " ships left.");

        } else if (botSubmarine == 0) {
            botSubmarine = -1;
            System.out.println("\nEnemy's submarine has been destroyed! They have " + botShips + " ships left.");
        }
    }

    public static void shipCount() {
        int b = 0, c = 0, d = 0, s = 0;
        int b2 = 0, c2 = 0, d2 = 0, s2 = 0;

        for (int i = 0; i < playerGrid.length; i++) {
            for (int j = 0; j < playerGrid.length; j++) {
                if (playerGrid[i][j] == 'B') {
                    b += 1;
                } else if (playerGrid[i][j] == 'C') {
                    c += 1;
                } else if (playerGrid[i][j] == 'D') {
                    d += 1;
                } else if (playerGrid[i][j] == 'S') {
                    s += 1;
                }

                if (botGrid[i][j] == 'B') {
                    b2 += 1;
                } else if (botGrid[i][j] == 'C') {
                    c2 += 1;
                } else if (botGrid[i][j] == 'D') {
                    d2 += 1;
                } else if (botGrid[i][j] == 'S') {
                    s2 += 1;
                }
            }
        }

        if (battleship != -1) {
            battleship = b;
        }
        if (cruiser != -1) {
            cruiser = c;
        }
        if (destroyer != -1) {
            destroyer = d;
        }
        if (submarine != -1) {
            submarine = s;
        }

        if (botBattleship != -1) {
            botBattleship = b2;
        }
        if (botCruiser != -1) {
            botCruiser = c2;
        }
        if (botDestroyer != -1) {
            botDestroyer = d2;
        }
        if (botSubmarine != -1) {
            botSubmarine = s2;
        }

        if (battleship == 0) {
            playerShips -= 1;
        } else if (cruiser == 0) {
            playerShips -= 1;
        } else if (destroyer == 0) {
            playerShips -= 1;
        } else if (submarine == 0) {
            playerShips -= 1;
        }

        if (botBattleship == 0) {
            botShips -= 1;
        } else if (botCruiser == 0) {
            botShips -= 1;
        } else if (botDestroyer == 0) {
            botShips -= 1;
        } else if (botSubmarine == 0) {
            botShips -= 1;
        }
    }

    public static void shipDisplay() {
        // top header
        System.out.println("\n--------- Your Ships ---------- Enemy's Ships -------\n");

        // player battleship
        System.out.print("Battleship: ");
        if (battleship == 5) {
            System.out.print("Active        ");
        } else if (battleship > 0 && battleship < 5) {
            System.out.print("Damaged       ");
        } else {
            System.out.print("Destroyed     ");
        }

        // bot battleship
        System.out.print("|| Battleship: ");
        if (botBattleship == 0 || botBattleship == -1) {
            System.out.println("Destroyed");
        } else if (botBattleship > 0) {
            System.out.println("Active");
        }

        // player cruiser
        System.out.print("Cruiser:    ");
        if (cruiser == 4) {
            System.out.print("Active        ");
        } else if (cruiser > 0 && cruiser < 4) {
            System.out.print("Damaged       ");
        } else {
            System.out.print("Destroyed     ");
        }

        // bot cruiser
        System.out.print("|| Cruiser:    ");
        if (botCruiser == 0 || botCruiser == -1) {
            System.out.println("Destroyed");
        } else if (botCruiser > 0) {
            System.out.println("Active");
        }

        // player destroyer
        System.out.print("Destroyer:  ");
        if (destroyer == 3) {
            System.out.print("Active        ");
        } else if (destroyer > 0 && destroyer < 3) {
            System.out.print("Damaged       ");
        } else {
            System.out.print("Destroyed     ");
        }

        // bot destroyer
        System.out.print("|| Destroyer:  ");
        if (botDestroyer == 0 || botDestroyer == -1) {
            System.out.println("Destroyed");
        } else if (botDestroyer > 0) {
            System.out.println("Active");
        }

        // player submarine
        System.out.print("Submarine:  ");
        if (submarine == 2) {
            System.out.print("Active        ");
        } else if (submarine == 1) {
            System.out.print("Damaged       ");
        } else {
            System.out.print("Destroyed     ");
        }

        // bot submarine
        System.out.print("|| Submarine:  ");
        if (botSubmarine == 0 || botSubmarine == -1) {
            System.out.println("Destroyed");
        } else if (botSubmarine > 0) {
            System.out.println("Active");
        }
        System.out.println("\n-----------------------------------------------------");
    }

    // this method will create ships for given grid
    public static void makeShips(char[][] grid) {
        int direction; // determines if the ship will be vertical or horizontal
        int x, y; // coordinates of ship on grid
        boolean shipPlaced = false;

        // battleship - 5 long
        do { // in a loop to make sure the ship is placed in empty slots so ships dont
            // overlap
            direction = getRand(1, 2); // 1 is horizontal, 2 is vertical
            x = getRand(0, 10);
            y = getRand(0, 10);

            // first checks if horizontal or vertical
            if (direction == 1) { // if direction is horizontal
                // if y is less than 5 then it will count forward, if y is greater than 4 then
                // it will count backwards
                // this is so the counters dont go off the grid which will crash the program
                if (y < 5) {
                    if (grid[x][y] == emptySpace && grid[x][y + 1] == emptySpace && grid[x][y + 2] == emptySpace && grid[x][y + 3] == emptySpace && grid[x][y + 4] == emptySpace) {
                        grid[x][y] = 'B';
                        grid[x][y + 1] = 'B';
                        grid[x][y + 2] = 'B';
                        grid[x][y + 3] = 'B';
                        grid[x][y + 4] = 'B';
                        shipPlaced = true;
                    }
                } else {
                    if (grid[x][y] == emptySpace && grid[x][y - 1] == emptySpace && grid[x][y - 2] == emptySpace && grid[x][y - 3] == emptySpace && grid[x][y - 4] == emptySpace) {
                        grid[x][y] = 'B';
                        grid[x][y - 1] = 'B';
                        grid[x][y - 2] = 'B';
                        grid[x][y - 3] = 'B';
                        grid[x][y - 4] = 'B';
                        shipPlaced = true;
                    }
                }
            } else { // if direction is vertical
                if (x < 5) {
                    if (grid[x][y] == emptySpace && grid[x + 1][y] == emptySpace && grid[x + 2][y] == emptySpace && grid[x + 3][y] == emptySpace && grid[x + 4][y] == emptySpace) {
                        grid[x][y] = 'B';
                        grid[x + 1][y] = 'B';
                        grid[x + 2][y] = 'B';
                        grid[x + 3][y] = 'B';
                        grid[x + 4][y] = 'B';
                        shipPlaced = true;
                    }
                } else {
                    if (grid[x - 1][y] == emptySpace && grid[x][y] == emptySpace && grid[x - 2][y] == emptySpace && grid[x - 3][y] == emptySpace && grid[x - 4][y] == emptySpace) {
                        grid[x][y] = 'B';
                        grid[x - 1][y] = 'B';
                        grid[x - 2][y] = 'B';
                        grid[x - 3][y] = 'B';
                        grid[x - 4][y] = 'B';
                        shipPlaced = true;
                    }
                }
            }
        } while (!shipPlaced);

        // cruiser - 4 long
        shipPlaced = false; // resets because now we're working with a new ship

        do {
            direction = getRand(1, 2);
            x = getRand(0, 10);
            y = getRand(0, 10);

            if (direction == 1) {
                if (y < 5) {
                    if (grid[x][y] == emptySpace && grid[x][y + 1] == emptySpace && grid[x][y + 2] == emptySpace && grid[x][y + 3] == emptySpace) {
                        grid[x][y] = 'C';
                        grid[x][y + 1] = 'C';
                        grid[x][y + 2] = 'C';
                        grid[x][y + 3] = 'C';
                        shipPlaced = true;
                    }
                } else {
                    if (grid[x][y] == emptySpace && grid[x][y - 1] == emptySpace && grid[x][y - 2] == emptySpace && grid[x][y - 3] == emptySpace) {
                        grid[x][y] = 'C';
                        grid[x][y - 1] = 'C';
                        grid[x][y - 2] = 'C';
                        grid[x][y - 3] = 'C';
                        shipPlaced = true;
                    }
                }
            } else {
                if (x < 5) {
                    if (grid[x][y] == emptySpace && grid[x + 1][y] == emptySpace && grid[x + 2][y] == emptySpace && grid[x + 3][y] == emptySpace) {
                        grid[x][y] = 'C';
                        grid[x + 1][y] = 'C';
                        grid[x + 2][y] = 'C';
                        grid[x + 3][y] = 'C';
                        shipPlaced = true;
                    }
                } else {
                    if (grid[x - 1][y] == emptySpace && grid[x][y] == emptySpace && grid[x - 2][y] == emptySpace && grid[x - 3][y] == emptySpace) {
                        grid[x][y] = 'C';
                        grid[x - 1][y] = 'C';
                        grid[x - 2][y] = 'C';
                        grid[x - 3][y] = 'C';
                        shipPlaced = true;
                    }
                }
            }
        } while (!shipPlaced);

        // Destroyer - 3 long
        shipPlaced = false; // resets because now we're working with a new ship

        do {
            direction = getRand(1, 2);
            x = getRand(0, 10);
            y = getRand(0, 10);

            if (direction == 1) {
                if (y < 5) {
                    if (grid[x][y] == emptySpace && grid[x][y + 1] == emptySpace && grid[x][y + 2] == emptySpace) {
                        grid[x][y] = 'D';
                        grid[x][y + 1] = 'D';
                        grid[x][y + 2] = 'D';
                        shipPlaced = true;
                    }
                } else {
                    if (grid[x][y] == emptySpace && grid[x][y - 1] == emptySpace && grid[x][y - 2] == emptySpace) {
                        grid[x][y] = 'D';
                        grid[x][y - 1] = 'D';
                        grid[x][y - 2] = 'D';

                        shipPlaced = true;
                    }
                }
            } else {
                if (x < 5) {
                    if (grid[x][y] == emptySpace && grid[x + 1][y] == emptySpace && grid[x + 2][y] == emptySpace) {
                        grid[x][y] = 'D';
                        grid[x + 1][y] = 'D';
                        grid[x + 2][y] = 'D';

                        shipPlaced = true;
                    }
                } else {
                    if (grid[x - 1][y] == emptySpace && grid[x][y] == emptySpace && grid[x - 2][y] == emptySpace) {
                        grid[x][y] = 'D';
                        grid[x - 1][y] = 'D';
                        grid[x - 2][y] = 'D';

                        shipPlaced = true;
                    }
                }
            }
        } while (!shipPlaced);

        // Submarine - 2 long
        shipPlaced = false; // resets because now we're working with a new ship

        do {
            direction = getRand(1, 2);
            x = getRand(0, 10);
            y = getRand(0, 10);

            if (direction == 1) {
                if (y < 5) {
                    if (grid[x][y] == emptySpace && grid[x][y + 1] == emptySpace) {
                        grid[x][y] = 'S';
                        grid[x][y + 1] = 'S';

                        shipPlaced = true;
                    }
                } else {
                    if (grid[x][y] == emptySpace && grid[x][y - 1] == emptySpace) {
                        grid[x][y] = 'S';
                        grid[x][y - 1] = 'S';

                        shipPlaced = true;
                    }
                }
            } else {
                if (x < 5) {
                    if (grid[x][y] == emptySpace && grid[x + 1][y] == emptySpace) {
                        grid[x][y] = 'S';
                        grid[x + 1][y] = 'S';

                        shipPlaced = true;
                    }
                } else {
                    if (grid[x - 1][y] == emptySpace && grid[x][y] == emptySpace) {
                        grid[x][y] = 'S';
                        grid[x - 1][y] = 'S';

                        shipPlaced = true;
                    }
                }
            }
        } while (!shipPlaced);
    }

    public static int charToInt(char x) {
        if (x == 'A') {
            return 0;
        } else if (x == 'B') {
            return 1;
        } else if (x == 'C') {
            return 2;
        } else if (x == 'D') {
            return 3;
        } else if (x == 'E') {
            return 4;
        } else if (x == 'F') {
            return 5;
        } else if (x == 'G') {
            return 6;
        } else if (x == 'H') {
            return 7;
        } else if (x == 'I') {
            return 8;
        } else if (x == 'J') {
            return 9;
        }

        return -1;
    }

    public static char intToChar(int x) {
        if (x == 0) {
            return 'A';
        } else if (x == 1) {
            return 'B';
        } else if (x == 2) {
            return 'C';
        } else if (x == 3) {
            return 'D';
        } else if (x == 4) {
            return 'E';
        } else if (x == 5) {
            return 'F';
        } else if (x == 6) {
            return 'G';
        } else if (x == 7) {
            return 'H';
        } else if (x == 8) {
            return 'I';
        } else if (x == 9) {
            return 'J';
        }

        return 1;
    }

    public static int getRand(int min, int max) {
        return (int) (Math.random() * max) + min;
    }

    // this method initializes the grids
    public static void makeGrid() {
        for (int i = 0; i < playerGrid.length; i++) {
            for (int j = 0; j < playerGrid[0].length; j++) {
                playerGrid[i][j] = emptySpace;
                botGrid[i][j] = emptySpace;
            }
        }

    } // End of makeGrid method.

    // This method prints the grid.
    public static void printGrid() {
        // top header
        System.out.println("          Your Grid             Enemy's Grid");
        System.out.println("-----------------------------------------------------");

        // for each i, a row of both the grids are printed
        for (int i = 0; i < playerGrid.length; i++) {
            System.out.print(i + " >> ");
            // playerGrid
            for (int j = 0; j < playerGrid[0].length; j++) {
                System.out.print(playerGrid[i][j] + " ");
            }

            System.out.print(" || ");

            // botGrid
            for (int j = 0; j < botGrid.length; j++) {
                if (!win) {
                    if (botGrid[i][j] == emptySpace || botGrid[i][j] == hit || botGrid[i][j] == miss) {
                        System.out.print(botGrid[i][j] + " ");
                    } else {
                        System.out.print(emptySpace + " ");
                    }
                } else {
                    System.out.print(botGrid[i][j] + " ");
                }
            }

            System.out.print("<< " + i);
            System.out.println(); // goes to next line
        }

        // bottom header
        System.out.println("-----------------------------------------------------");
        System.out.println("     A B C D E F G H I J     A B C D E F G H I J");

    } // End of printGrid method.

    public static void clearScreen() {
        for (int i = 0; i < 25; i++) {
            System.out.println("\n");
        }
    }
}
