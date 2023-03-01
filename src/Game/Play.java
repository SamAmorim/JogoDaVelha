package Game;

import java.util.Scanner;

public class Play {

    Scanner input = new Scanner(System.in);
    private Celula[][] gameTable = new Celula[3][3];
    private short[] cord = new short[2];
    char win = ' ';
    boolean gameRunning = true;
    char playerNow = 'X';
    short move = 0;

    public void start() {

        for (int i = 0; i < gameTable.length; i++) {
            for (int j = 0; j < gameTable.length; j++) {
                gameTable[i][j] = new Celula();
            }
        }

        while (gameRunning) {
            try {

                System.out.print(paintTable(gameTable));

                play(playerNow, cord, input);
                // validação
                if (validate(gameTable)) {
                    gameRunning = false;
                    System.out.println(paintTable(gameTable));
                    System.out.println("parabens " + win + " você ganhou");
                } else if (move == 9) {
                    gameRunning = false;
                    System.out.print(paintTable(gameTable));
                    System.out.println("empate!!!");
                }

                if (playerNow == 'X') {
                    playerNow = '0';
                } else {
                    playerNow = 'X';
                }
            } catch (Exception e) {
                System.out.println("erro");
            }
        }
    }

    private void play(char player, short[] cord, Scanner input) {

        System.out.print("em qual linha você deseja jogar " + playerNow + ": ");
        cord[0] = (short) (input.nextShort() - 1);
        System.out.print("em qual coluna você deseja jogar " + playerNow + ": ");
        cord[1] = (short) (input.nextShort() - 1);

        if (gameTable[cord[0]][cord[1]].getValues() == ' ') {

            gameTable[cord[0]][cord[1]].setValues(player);
            this.move++;

        } else {
            System.out.println("\njogada invalida, jogue novamente\n");
            play(player, cord, input);
        }
    }

    private boolean validate(Celula[][] gabeTable) {

        char firstPos = ' ';

        for (int i = 0; i < gabeTable.length; i++) {
            firstPos = gameTable[i][0].getValues();
            // verificação de linha
            if (firstPos == 'X' || firstPos == '0') {
                if (firstPos == gameTable[i][0].getValues() && firstPos == gameTable[i][1].getValues()
                        && firstPos == gameTable[i][2].getValues()) {
                    this.win = playerNow;
                    return true;
                }
            }
            // validação de coluna
            firstPos = gameTable[0][i].getValues();
            if (firstPos == 'X' || firstPos == '0') {

                if (firstPos == gameTable[0][i].getValues() && firstPos == gameTable[1][i].getValues()
                        && firstPos == gameTable[2][i].getValues()) {
                    this.win = playerNow;
                    return true;
                }
            }

            // validação diagonal
            firstPos = gameTable[0][0].getValues();
            if (firstPos == 'X' || firstPos == '0') {

                if (firstPos == gameTable[0][0].getValues() && firstPos == gameTable[1][1].getValues()
                        && firstPos == gameTable[2][2].getValues()) {
                    this.win = playerNow;
                    return true;
                }
            }
            firstPos = gameTable[0][2].getValues();
            if (firstPos == 'X' || firstPos == '0') {

                if (firstPos == gameTable[0][2].getValues() && firstPos == gameTable[1][1].getValues()
                        && firstPos == gameTable[2][0].getValues()) {
                    this.win = playerNow;
                    return true;
                }
            }
        }

        return false;

    }

    private String paintTable(Celula[][] gameTable) {

        return ("   [1][2][3]\n"
                + "[1]" + "[" + gameTable[0][0].getValues() + "]" + "[" + gameTable[0][1].getValues() + "]" + "["
                + gameTable[0][2].getValues() + "]\n" +
                "[2]" + "[" + gameTable[1][0].getValues() + "]" + "[" + gameTable[1][1].getValues() + "]" + "["
                + gameTable[1][2].getValues() + "]\n" +
                "[3]" + "[" + gameTable[2][0].getValues() + "]" + "[" + gameTable[2][1].getValues() + "]" + "["
                + gameTable[2][2].getValues() + "]\n");
    }

}
