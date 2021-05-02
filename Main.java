package tictactoe;

import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
import tictactoe.BotHard;

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] fieldMatrix = new char[3][3];
        initFieldMatrix(fieldMatrix);
        playGame(fieldMatrix);
    }

    public static String[] checkInput() {
        while (true) {
            System.out.print("Input command ");
            String[] inputParams = scanner.nextLine().split(" ");
            if ("exit".equals(inputParams[0])) {
                return inputParams;
            } else if (inputParams.length != 3) {
                    System.out.println("Bad parameters!");
            } else if (("start".equals(inputParams[0]) || "exit".equals(inputParams[0])) &&
                          ("easy".equals(inputParams[1]) || "medium".equals(inputParams[1]) || "hard".equals(inputParams[1]) || "user".equals(inputParams[1])) &&
                            ("easy".equals(inputParams[2]) || "medium".equals(inputParams[2]) || "hard".equals(inputParams[2]) || "user".equals(inputParams[2]))) {
                            return inputParams;
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public static void playGame(char[][] array) {
        String[] inputParams = checkInput();
        if ("start".equals(inputParams[0])) {
            printFieldMatrix(array);
            while (analyseField(array)) {
                char inputChar = 'X';
                if ("easy".equals(inputParams[1])) {
                    compEasyChangeFieldMatrix(array, inputChar);
                    analyseField(array);
                } else if ("medium".equals(inputParams[1])) {
                    compMediumChangeFieldMatrix(array, inputChar);
                    analyseField(array);
                } else if ("hard".equals(inputParams[1])) {
                    BotHard.player = 'X';
                    BotHard.opponent = 'O';
                    BotHard.Move bestMove = BotHard.findBestMove(array);
                    array[bestMove.row][bestMove.col] = inputChar;
                    printFieldMatrix(array);
                    analyseField(array);
                } else if ("user".equals(inputParams[1])) {
                    changeFieldMatrix(array, inputChar);
                    analyseField(array);
                }

                if (analyseField(array)) {
                    inputChar = 'O';
                    if ("easy".equals(inputParams[2])) {
                        compEasyChangeFieldMatrix(array, inputChar);
                        analyseField(array);
                    } else if ("medium".equals(inputParams[2])) {
                        compMediumChangeFieldMatrix(array, inputChar);
                        analyseField(array);
                    } else if ("hard".equals(inputParams[2])) {
                        BotHard.player = 'O';
                        BotHard.opponent = 'X';
                        BotHard.Move bestMove = BotHard.findBestMove(array);
                        array[bestMove.row][bestMove.col] = inputChar;
                        printFieldMatrix(array);
                        analyseField(array);
                    } else if ("user".equals(inputParams[2])) {
                        changeFieldMatrix(array, inputChar);
                        analyseField(array);
                    }
                }
            }
        }
    }

    public static void compEasyChangeFieldMatrix(char[][] array, char input) {
        Random generator = new Random();
        boolean empty = true;
        System.out.println("Making move level \"easy\"");
        while (empty) {
            int x = generator.nextInt(3);
            int y = generator.nextInt(3);
            if (array[x][y] != 'X' && array[x][y] != 'O') {
                array[x][y] = input;
                empty = false;
            }
        }
        printFieldMatrix(array);
    }

    public static void compMediumChangeFieldMatrix(char[][] array, char input) {
        Random generator = new Random();
        boolean empty = true;
        System.out.println("Making move level \"medium\"");

        for (int i = 0; i < 3; i++) {
            if (array[i][0] + array[i][1] == 176 || array[i][0] + array[i][1] == 158) {
                if (array[i][2] != 'X' && array[i][2] != 'O') {
                    array[i][2] = input;
                    empty = false;
                    break;
                }
            } else if (array[0][i] + array[1][i] == 176 || array[0][i] + array[1][i] == 158) {
                if (array[2][i] != 'X' && array[2][i] != 'O') {
                    array[2][i] = input;
                    empty = false;
                    break;
                }
            } else if (array[i][1] + array[i][2] == 176 || array[i][1] + array[i][2] == 158) {
                if (array[i][0] != 'X' && array[i][0] != 'O') {
                    array[i][0] = input;
                    empty = false;
                    break;
                }
            } else if (array[1][i] + array[2][i] == 176 || array[1][i] + array[2][i] == 158) {
                if (array[0][i] != 'X' && array[0][i] != 'O') {
                    array[0][i] = input;
                    empty = false;
                    break;
                }
            } else if (array[0][0] + array[1][1] == 176 || array[0][0] + array[1][1] == 158) {
                if (array[2][2] != 'X' && array[2][2] != 'O') {
                    array[2][2] = input;
                    empty = false;
                    break;
                }
            } else if (array[1][1] + array[2][2] == 176 || array[1][1] + array[2][2] == 158) {
                if (array[0][0] != 'X' && array[0][0] != 'O') {
                    array[0][0] = input;
                    empty = false;
                    break;
                }
            } else if (array[0][2] + array[1][1] == 176 || array[0][2] + array[1][1] == 158) {
                if (array[2][0] != 'X' && array[2][0] != 'O') {
                    array[2][0] = input;
                    empty = false;
                    break;
                }
            } else if (array[2][0] + array[1][1] == 176 || array[2][0] + array[1][1] == 158) {
                if (array[0][2] != 'X' && array[0][2] != 'O') {
                    array[0][2] = input;
                    empty = false;
                    break;
                }
            }
        }

        while (empty) {
            int x = generator.nextInt(3);
            int y = generator.nextInt(3);
            if (array[x][y] != 'X' && array[x][y] != 'O') {
                array[x][y] = input;
                empty = false;
            }
        }
        printFieldMatrix(array);
    }

    public static void changeFieldMatrix (char[][] array, char input) {
        boolean b = true;
        while (b) {
            System.out.print("Enter the coordinates: ");
            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (array[x - 1][y - 1] == ' ') {
                    array[x - 1][y - 1] = input;
                    printFieldMatrix(array);
                    b = false;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
    }

    public static void initFieldMatrix (char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                Arrays.fill(array[i], ' ');
            }
        }
    }

    public static void printFieldMatrix (char[][] array) {
        System.out.println("---------");
        for (int i = 0; i < array.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%c ", array[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean analyseField(char[][] array) {
        int numberOfCells = 0;
        int numberOfX = 0;
        int numberOfO = 0;
        int threeO = 0;
        int threeX = 0;
        String output = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < (array[i].length); j++) {
                switch (array[i][j]) {
                    case ' ': {
                        numberOfCells += 1;
                        break;
                    }
                    case 'X': {
                        numberOfX += 1;
                        break;
                    }
                    case 'O': {
                        numberOfO += 1;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (array[i][0] + array[i][1] + array[i][2] == 264) {
                threeX += 1;
            }
            if (array[0][i] + array[1][i] + array[2][i] == 264) {
                threeX += 1;
            }

            if (array[i][0] + array[i][1] + array[i][2] == 237) {
                threeO += 1;
            }
            if (array[0][i] + array[1][i] + array[2][i] == 237) {
                threeO += 1;
            }
        }

        if (array[0][0] + array[1][1] + array[2][2] == 264) {
            threeX += 1;
        }
        if (array[0][2] + array[1][1] + array[2][0] == 264) {
            threeX += 1;
        }
        if (array[0][0] + array[1][1] + array[2][2] == 237) {
            threeO += 1;
        }
        if (array[0][2] + array[1][1] + array[2][0] == 237) {
            threeO += 1;
        }

        if (Math.abs(numberOfO - numberOfX) > 1 || threeO > 0 && threeX > 0) {
            output = "Impossible";
            //System.out.println("Impossible");
        } else {
            if (threeO == 0 && threeX == 0 && numberOfCells > 0) {
                //System.out.println("Game not finished");
                output = "Game not finished";
            }
            if (threeO == 0 && threeX == 0 && numberOfCells == 0) {
                System.out.println("Draw");
            }
            if (threeO > 0 && threeX == 0) {
                System.out.println("O wins");
            }
            if (threeO == 0 && threeX > 0) {
                System.out.println("X wins");
            }
        }
        return (output.equals("Game not finished"));
    }
}
