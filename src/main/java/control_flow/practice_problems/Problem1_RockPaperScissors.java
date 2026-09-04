import java.util.Random;
import java.util.Scanner;

class RockPaperScissors {

    String playRound(String playerMove, String computerMove) {
        playerMove = playerMove.toLowerCase();
        computerMove = computerMove.toLowerCase();

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("rock") && computerMove.equals("scissors")) ||
            (playerMove.equals("paper") && computerMove.equals("rock")) ||
            (playerMove.equals("scissors") && computerMove.equals("paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }
}

public class Problem1_RockPaperScissors {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        RockPaperScissors game = new RockPaperScissors();

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("Round | Player Move | Computer Move | Result");

        for (int round = 1; round <= 5; round++) {

            System.out.print("Enter your move (Rock/Paper/Scissors): ");
            String playerMove = scanner.nextLine();

            String computerMove = moves[random.nextInt(3)];

            String result = game.playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println(round + " | " + playerMove +
                    " | " + computerMove + " | " + result);
        }

        double winPercentage = (wins / 5.0) * 100;

        System.out.println("\nFinal Summary");
        System.out.println("Wins: " + wins +
                " | Losses: " + losses +
                " | Draws: " + draws +
                " | Win % = " + winPercentage + "%");

        scanner.close();
    }
}