#include <iostream>
#include <vector>

using namespace std;

class TicTacToe {
private:
    vector<vector<char>> board;
    char currentPlayer;

public:
    TicTacToe() {
        board = { {'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'} };
        currentPlayer = 'X';
    }

    void displayBoard() {
        cout << "\n\tTic-Tac-Toe\n\n";
        cout << "Player 1 (X)  -  Player 2 (O)\n\n";
        cout << "     |     |     \n";
        cout << "  " << board[0][0] << "  |  " << board[0][1] << "  |  " << board[0][2] << "  \n";
        cout << "_____|_____|_____\n";
        cout << "     |     |     \n";
        cout << "  " << board[1][0] << "  |  " << board[1][1] << "  |  " << board[1][2] << "  \n";
        cout << "_____|_____|_____\n";
        cout << "     |     |     \n";
        cout << "  " << board[2][0] << "  |  " << board[2][1] << "  |  " << board[2][2] << "  \n";
        cout << "     |     |     \n\n";
    }

    bool makeMove(int choice) {
        int row = (choice - 1) / 3;
        int col = (choice - 1) % 3;

        if (choice < 1 || choice > 9 || board[row][col] == 'X' || board[row][col] == 'O') {
            cout << "Invalid move! Try again.\n";
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    int checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) return 1;
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) return 1;
        }
        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) return 1;
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) return 1;

        // Check for tie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') return 0; // Game ongoing
            }
        }
        return -1; // Tie
    }

    void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    char getCurrentPlayer() {
        return currentPlayer;
    }
};

int main() {
    TicTacToe game;
    int choice;
    int status = 0;

    cout << "=== Welcome to C++ Tic-Tac-Toe ===\n";

    while (status == 0) {
        game.displayBoard();
        cout << "Player " << game.getCurrentPlayer() << ", enter a number (1-9): ";
        cin >> choice;

        if (cin.fail()) {
            cin.clear();
            cin.ignore(10000, '\n');
            cout << "Invalid input. Please enter a valid number.\n";
            continue;
        }

        if (game.makeMove(choice)) {
            status = game.checkWin();
            if (status == 0) {
                game.switchPlayer();
            }
        }
    }

    game.displayBoard();
    if (status == 1) {
        cout << "Congratulations! Player " << game.getCurrentPlayer() << " wins!\n";
    } else {
        cout << "It's a draw game!\n";
    }

    return 0;
}