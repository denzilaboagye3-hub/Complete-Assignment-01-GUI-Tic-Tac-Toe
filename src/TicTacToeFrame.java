import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {

    private JButton[][] buttons = new JButton[3][3];
    private JButton quitButton;

    private boolean xTurn = true;
    private int moveCount = 0;

    public TicTacToeFrame() {

        setTitle("Tic Tac Toe");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createGameBoard();
        createBottomPanel();
    }

    private void createGameBoard() {

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        ActionListener gameListener = e -> {

            JButton clickedButton = (JButton) e.getSource();

            if (!clickedButton.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "That square is already taken. Choose another square."
                );

                return;
            }

            if (xTurn) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }

            moveCount++;

            if (checkWinner()) {

                String winner;

                if (xTurn) {
                    winner = "X";
                } else {
                    winner = "O";
                }

                JOptionPane.showMessageDialog(
                        this,
                        winner + " wins!"
                );

                askToPlayAgain();

            } else if (moveCount == 9) {

                JOptionPane.showMessageDialog(
                        this,
                        "It's a tie!"
                );

                askToPlayAgain();

            } else {

                xTurn = !xTurn;
            }
        };

        Font buttonFont =
                new Font("Arial", Font.BOLD, 72);

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                buttons[row][col] = new JButton("");

                buttons[row][col].setFont(buttonFont);

                buttons[row][col].addActionListener(
                        gameListener
                );

                boardPanel.add(buttons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void createBottomPanel() {

        JPanel bottomPanel = new JPanel();

        quitButton = new JButton("Quit");

        quitButton.addActionListener(
                e -> quitGame()
        );

        bottomPanel.add(quitButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private boolean checkWinner() {

        // Check rows
        for (int row = 0; row < 3; row++) {

            if (!buttons[row][0].getText().isEmpty() &&
                    buttons[row][0].getText()
                            .equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText()
                            .equals(buttons[row][2].getText())) {

                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {

            if (!buttons[0][col].getText().isEmpty() &&
                    buttons[0][col].getText()
                            .equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText()
                            .equals(buttons[2][col].getText())) {

                return true;
            }
        }

        // First diagonal
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText()
                        .equals(buttons[1][1].getText()) &&
                buttons[1][1].getText()
                        .equals(buttons[2][2].getText())) {

            return true;
        }

        // Second diagonal
        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText()
                        .equals(buttons[1][1].getText()) &&
                buttons[1][1].getText()
                        .equals(buttons[2][0].getText())) {

            return true;
        }

        return false;
    }

    private void askToPlayAgain() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Would you like to play again?",
                "Play Again",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            resetGame();

        } else {

            System.exit(0);
        }
    }

    private void resetGame() {

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                buttons[row][col].setText("");
            }
        }

        xTurn = true;
        moveCount = 0;
    }

    private void quitGame() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit?",
                "Confirm Quit",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            System.exit(0);
        }
    }
}