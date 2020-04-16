//TIC TAC TOE IN PROCEDURAL STYLE

import java.util.Scanner;

public class TICTACTOE {

    public static char[][] map;
    public static final int SIZE = 3;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static int COUNTER_OF_TURNS = 1;

    // POPULATE THE MAP WITH DOT_EMPTY
    public static void initMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    //PRINT MAP
    public static void printMap() {
        System.out.print("# ");

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }

        System.out.println();

        for (int i = 0; i < map.length; i++) {

            System.out.print((i + 1) + "|");
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    //CHECK FOR VALID CELL
    public static boolean isCellEmpty(int x, int y) {
        boolean flag = true;
        x--;
        y--;

        if (map[x][y] != DOT_EMPTY) {
            flag = false;
        }
        return flag;
    }

    //CHECK OF WIN
    public static boolean checkOfWin() {
        boolean flag = false;

        String checkLineX = "";
        String checkLineO = "";
        String diagonal1 = "";
        String diagonal2 = "";
        String row = "";
        String column = "";

        //LINE FOR WIN
        for (int i = 0; i < map.length; i++) {
            checkLineX += DOT_X;
            checkLineO += DOT_O;
        }

        //DIAGONALS
        for (int i = 0; i < map.length; i++) {
            diagonal1 += map[i][i];
            diagonal2 += map[i][map.length - i - 1];
        }

        if (checkLineX.equals(diagonal1) || checkLineX.equals(diagonal2) ||
                checkLineO.equals(diagonal1) || checkLineO.equals(diagonal2)) {
            flag = true;
        }

        //ROWS AND COLUMNS
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                row += map[i][j];
                column += map[j][i];
            }

            if (checkLineX.equals(row) || checkLineX.equals(column) ||
                    checkLineO.equals(row) || checkLineO.equals(column)) {
                flag = true;
            }

            row = "";
            column = "";
        }

        return flag;
    }

    //SET Xs AND Os
    public static void setXO(int x, int y, char mark) {
        x--;
        y--;

        if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
            map[x][y] = mark;
        } else {
            System.out.println("Вы ввели числа за пределами поля");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x, y;
        System.out.println("Это игра крестики-нолики. Вы ходите первым и играете крестиками (X).\n" +
                "Компьютер ходит вторым и играет ноликами (O).\n" +
                "Для того, чтобы сделать ход нужно ввести номер столбца и номер строки. Пример: 2 2.\n\n");

        initMap();
        printMap();

        while (!checkOfWin()) {
            //HUMAN TURN
            if (COUNTER_OF_TURNS % 2 == 1) {
                System.out.print("Ваш ход: ");

                x = sc.nextInt();
                y = sc.nextInt();

                if (isCellEmpty(x, y)) {
                    setXO(x, y, DOT_X);
                } else {
                    System.out.println("Ячейка занята");
                    continue;
                }

            }
            // COMPUTER TURN
            else {
                x = (int) (1 + Math.random() * SIZE);
                y = (int) (1 + Math.random() * SIZE);

                if (isCellEmpty(x, y)) {
                    setXO(x, y, DOT_O);
                } else {
                    continue;
                }
            }

            System.out.println();

            printMap();
            COUNTER_OF_TURNS++;
        }

        if (COUNTER_OF_TURNS % 2 == 1) {
            System.out.println("Компьтер победил");
        } else {
            System.out.println("Вы победили");
        }
    }
}
