import java.util.*;


public class BattleShips {

    public static void main (String[] args){
        char[][] oceanMap = new char[10][10];

        welcome();
        printOcean(oceanMap);

        int[][] playerShips = deployShips();
        System.out.println(Arrays.deepToString(playerShips));

        System.out.println("Your ships have been deployed: ");
        System.out.println();

        printShips(oceanMap, playerShips);

        int[][] computerShips = computerDeployShips(playerShips);
        System.out.println(Arrays.deepToString(computerShips));
        System.out.println();

        System.out.println("You can start guessing first");
        int[][] playersGuess = playersTurn(oceanMap);
        displayResults(playersGuess, computerShips, oceanMap);
        System.out.println();

        char[][] computerGuessMap = new char[10][10];
        int[][] computersGuess = computersTurn(oceanMap, computerGuessMap);
        displayComputerResults(computersGuess, computerShips, oceanMap, computerGuessMap);

        boolean gameOver = tabulateResults(oceanMap);

        while (!gameOver) {
            playersGuess = playersTurn(oceanMap);
            displayResults(playersGuess, computerShips, oceanMap);
            System.out.println();

            computersGuess = computersTurn(oceanMap, computerGuessMap);
            displayComputerResults(computersGuess, computerShips, oceanMap, computerGuessMap);

            gameOver = tabulateResults(oceanMap);
        }

    }

    public static void welcome(){

        System.out.println(("*".repeat(4)) + " Welcome to BattleShips game " + "*".repeat(4) );
        System.out.println();
        System.out.println("Right now, the sea is empty.");
        System.out.println();
    }


    public static void printOcean(char[][] oceanMap){
        System.out.println("  "+"0123456789"+"  ");

        for (int i=0; i< oceanMap.length; i++) {
            System.out.print(i+"|");

            for (int j=0; j<oceanMap[i].length; j++){

                if (oceanMap[i][j] == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(oceanMap[i][j]);
                }

            }

            System.out.println ("|"+i);
        }
        System.out.println("  "+"0123456789"+"  ");
        System.out.println();

    }


    public static int[][] deployShips(){
        int[][] playerShips = new int[5][2];

        System.out.println("Deploy your ships: ");

        for (int i = 0; i< playerShips.length; i++){
            getShipCoordinates(playerShips, i);
        }

/*
        for (int i =0; i< playerShips.length; i++) {

            System.out.print("Enter X coordinate for your " + (i + 1) + " ship: ");
            playerShips[i][0] = input.nextInt();
            while((playerShips[i][0] < 0) || (playerShips[i][0] > 9)){
                System.out.println("That was an invalid entry, please enter a number between 0 and 9.");
                System.out.print("Enter X coordinate for your " + (i + 1) + " ship: ");
                playerShips[i][0] = input.nextInt();
            }

            System.out.print("Enter y coordinate for your " + (i + 1) + " ship: ");
            playerShips[i][1] = input.nextInt();
            while((playerShips[i][1] < 0) || (playerShips[i][1] > 9)){
                System.out.println("That was an invalid entry, please enter a number between 0 and 9.");
                System.out.print("Enter y coordinate for your " + (i + 1) + " ship: ");
                playerShips[i][1] = input.nextInt();
            }


            if (i>0) {
                while (checkDuplicates(playerShips, i+1)){
                    System.out.println("you have a duplicate location, please enter a new location.");

                    System.out.println();
                    System.out.print("Enter X coordinate for your " + (i + 1) + " ship: ");
                    playerShips[i][0] = input.nextInt();

                    System.out.print("Enter y coordinate for your " + (i + 1) + " ship: ");

                    playerShips[i][1] = input.nextInt();
                }
            }
        }

 */
        //System.out.println(checkDuplicates(playerShips, playerShips.length));
        return playerShips;
    }


    public static void getShipCoordinates(int[][] playerShips, int i){
        Scanner input = new Scanner(System.in);
        boolean validXCoordinates = false;
        boolean validYCoordinates = false;


        while ((!validXCoordinates) || (!validYCoordinates)){

            System.out.print("Enter X coordinate for your " + (i + 1) + " ship: ");
            playerShips[i][0] = input.nextInt();

            System.out.print("Enter y coordinate for your " + (i + 1) + " ship: ");
            playerShips[i][1] = input.nextInt();

            if ((playerShips[i][0] < 0) || (playerShips[i][0] > 9)) {
                System.out.println("That was an invalid X coordinate, please enter a number between 0 and 9.");
            }
            else{
                validXCoordinates = true;
            }

            if ((playerShips[i][1] < 0) || (playerShips[i][1] > 9)) {
                System.out.println("That was an invalid Y coordinate, please enter a number between 0 and 9.");
            }
            else if ( (i>0) && (checkDuplicates(playerShips, i+1))){
                System.out.println("you have a duplicate location, please enter a new location.");
                validXCoordinates = false;
                validYCoordinates = false;
            }
            else {
                validYCoordinates = true;
            }
        }

    }


    public static boolean checkDuplicates(int[][] shipLocations, int checkLength){

        boolean checkDuplicates = false;

        for (int n = 1; n < checkLength; n++) {
            for (int i = n; i < checkLength; i++) {
                if ((shipLocations[n - 1][0] == shipLocations[i][0]) && (shipLocations[n - 1][1] == shipLocations[i][1])) {
                    System.out.println();
                    checkDuplicates = true;
                }
            }
        }

        return checkDuplicates;

    }


    public static void printShips(char[][] oceanMap, int[][] playerShips){

        for (int i=0; i<playerShips.length; i++){

            oceanMap[playerShips[i][0]][playerShips[i][1]] = '@';

        }

        printOcean(oceanMap);
        System.out.println();


    }


    public static int[][] computerDeployShips(int[][] playerShips) {
        int[][] computerShips = new int[5][2];

        System.out.println("Computer is deploying ships");

        for (int i=0; i<computerShips.length; i++) {
            computerShips[i][0] = (int)(Math.random() * 10);
            computerShips[i][1] = (int)(Math.random() * 10);

            while (alreadyDeployed(computerShips[i][0],computerShips[i][1], playerShips) ){
                computerShips[i][0] = (int)(Math.random() * 10);
                computerShips[i][1] = (int)(Math.random() * 10);
            }

            if (i>0) {
                while (checkDuplicates(computerShips, i+1)){
                    computerShips[i][0] = (int)(Math.random() * 10);
                    computerShips[i][1] = (int)(Math.random() * 10);

                    while (alreadyDeployed(computerShips[i][0],computerShips[i][1], playerShips) ){
                        computerShips[i][0] = (int)(Math.random() * 10);
                        computerShips[i][1] = (int)(Math.random() * 10);
                    }
                }
            }
            System.out.println( (i+1) + ". ship deployed");
        }
        return computerShips;
    }


    public static boolean alreadyDeployed(int x, int y, int[][] playerShips){
        boolean alreadyDeployed = false;

        for (int i=0; i<playerShips.length; i++) {

            if (playerShips[i][0] == x) {
                if (playerShips[i][1] == y){
                    alreadyDeployed = true;
                }
            }
        }

        return alreadyDeployed;
    }


    public static int[][] playersTurn(char[][] oceanMap) {
        Scanner input = new Scanner(System.in);
        int[][] playersGuess = new int[1][2];

        System.out.println("YOUR TURN");
        System.out.println("Enter X coordinate: ");
        playersGuess[0][0] = input.nextInt();
        while((playersGuess[0][0] < 0) || (playersGuess[0][0] > 9)){
            System.out.println("That was an invalid entry, please enter a number between 0 and 9.");
            System.out.println("Enter X coordinate: ");
            playersGuess[0][0] = input.nextInt();
        }

        System.out.println("Enter Y coordinate: ");
        playersGuess[0][1] = input.nextInt();
        while((playersGuess[0][1] < 0) || (playersGuess[0][1] > 9)){
            System.out.println("That was an invalid entry, please enter a number between 0 and 9.");
            System.out.println("Enter Y coordinate: ");
            playersGuess[0][0] = input.nextInt();
        }


        while (previousGuessed(playersGuess, oceanMap)) {
            System.out.println("You've already guessed these co-ordinates, please enter again.");

            System.out.println("Enter X coordinate: ");
            playersGuess[0][0] = input.nextInt();
            while((playersGuess[0][0] < 0) || (playersGuess[0][0] > 9)){
                System.out.println("That was an invalid entry, please enter a number between 0 and 9.");
                System.out.println("Enter X coordinate: ");
                playersGuess[0][0] = input.nextInt();
            }

            System.out.println("Enter Y coordinate: ");
            playersGuess[0][1] = input.nextInt();
            while((playersGuess[0][1] < 0) || (playersGuess[0][1] > 9)){
                System.out.println("That was an invalid entry, please enter a number between 0 and 9.");
                System.out.println("Enter Y coordinate: ");
                playersGuess[0][0] = input.nextInt();
            }

        }


        return playersGuess;
    }


    public static boolean previousGuessed(int[][] playersGuess, char[][] oceanMap){
        boolean previousGuessed = false;

        if ((oceanMap[playersGuess[0][0]][playersGuess[0][1]] == '!') ||
                (oceanMap[playersGuess[0][0]][playersGuess[0][1]] == 'x') ||
                (oceanMap[playersGuess[0][0]][playersGuess[0][1]] == '-')) {
            previousGuessed = true;
        }

        return previousGuessed;
    }


    public static void displayResults(int[][] playersGuess, int[][] computerShips, char[][] oceanMap) {

        //scenario 1
        for (int i =0; i<computerShips.length; i++) {
            if ((computerShips[i][0] == playersGuess[0][0]) &&  (computerShips[i][1] == playersGuess[0][1])){
                System.out.println("Boom! You sunk the ship!");
                oceanMap[playersGuess[0][0]][playersGuess[0][1]] = '!';
            }
        }

        //scenario 2
        if (oceanMap[playersGuess[0][0]][playersGuess[0][1]] == '@'){
            System.out.println("Oh no, you sunk your own ship :(");
            oceanMap[playersGuess[0][0]][playersGuess[0][1]] = 'x';
        }

        else if (oceanMap[playersGuess[0][0]][playersGuess[0][1]] == 0){
            System.out.println("Sorry, you missed");
            oceanMap[playersGuess[0][0]][playersGuess[0][1]] = '-';
        }

        printOcean(oceanMap);

    }

    public static int[][] computersTurn(char[][] oceanMap, char[][] computerGuessMap) {
        int[][] computersGuess = new int[1][2];

        System.out.println("COMPUTERS TURN");
        computersGuess[0][0] = (int)(Math.random() * 10);
        computersGuess[0][1] = (int)(Math.random() * 10);

        while (previousGuessed(computersGuess, oceanMap)) {
            computersGuess[0][0] = (int)(Math.random() * 10);
            computersGuess[0][1] = (int)(Math.random() * 10);
        }

        while (previousGuessed(computersGuess, computerGuessMap)){
            computersGuess[0][0] = (int)(Math.random() * 10);
            computersGuess[0][1] = (int)(Math.random() * 10);

            while (previousGuessed(computersGuess, oceanMap)) {
                computersGuess[0][0] = (int)(Math.random() * 10);
                computersGuess[0][1] = (int)(Math.random() * 10);
            }
        }


        return computersGuess;
    }

    public static void displayComputerResults(int[][] computersGuess, int[][] computerShips, char[][] oceanMap, char[][] computerGuessMap) {


        //scenario 2
        for (int i =0; i<computerShips.length; i++) {
            if ((computerShips[i][0] == computersGuess[0][0]) &&  (computerShips[i][1] == computersGuess[0][1])){
                System.out.println("The Computer sunk one of its own ships");
                oceanMap[computersGuess[0][0]][computersGuess[0][1]] = '!';
                computerGuessMap[computersGuess[0][0]][computersGuess[0][1]] = 'x';
            }
        }

        //scenario 1
        if (oceanMap[computersGuess[0][0]][computersGuess[0][1]] == '@'){
            System.out.println("The Computer sunk one of your ships!");
            oceanMap[computersGuess[0][0]][computersGuess[0][1]] = 'x';
            computerGuessMap[computersGuess[0][0]][computersGuess[0][1]] = '!';
        }

        else if (oceanMap[computersGuess[0][0]][computersGuess[0][1]] == 0){
            System.out.println("The computer missed");
            computerGuessMap[computersGuess[0][0]][computersGuess[0][1]] = '-';
        }

        printOcean(oceanMap);

    }

    public static boolean tabulateResults(char[][] oceanMap) {

        boolean gameOver = false;

        int numComputerShipsSunk = 0;
        int numPlayerShipsLeft = 0;

        for (int i = 0; i < oceanMap.length; i++) {

            for (int j = 0; j < oceanMap[i].length; j++) {

                if (oceanMap[i][j] == '!') {
                    numComputerShipsSunk += 1;
                } else if (oceanMap[i][j] == '@') {
                    numPlayerShipsLeft += 1;
                }
            }
        }

        int numComputerShipsLeft = 5 - numComputerShipsSunk;

        if ((numComputerShipsLeft == 0) | (numPlayerShipsLeft == 0)){
            gameOver = true;
        }

        System.out.println("Your ships: " + numPlayerShipsLeft + " | Computer Ships: " + numComputerShipsLeft);
        System.out.println("-".repeat(10));

        if (numComputerShipsLeft == 0) {
            System.out.println("YOU WON!");
        }
        else if (numPlayerShipsLeft == 0) {
            System.out.println("You Lost");
            System.out.println("Game Over");
        }

        return gameOver;
    }

}
