public class Configurations {
    private char[][] board;
    private int lengthToWin;
    private int maxLevels;

    public Configurations(int boardSize, int lengthToWin, int maxLevels) {
        this.board = new char[boardSize][boardSize];
        this.lengthToWin = lengthToWin;
        this.maxLevels = maxLevels;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public HashDictionary createDictionary() {
        return new HashDictionary(7013);
    }

    private String boardToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    public int repeatedConfiguration(HashDictionary hashTable) {
        String configString = boardToString();
        return hashTable.get(configString);
    }

    public void addConfiguration(HashDictionary hashTable, int score) {
        String configString = boardToString();
        Data data = new Data(configString, score);
        hashTable.put(data);
    }

    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    public boolean squareIsEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    public boolean wins(char symbol) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <= board.length - lengthToWin; j++) {
                if (checkLine(i, j, 0, 1, symbol)) return true;
            }
        }

        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i <= board.length - lengthToWin; i++) {
                if (checkLine(i, j, 1, 0, symbol)) return true;
            }
        }

        for (int i = 0; i <= board.length - lengthToWin; i++) {
            for (int j = 0; j <= board.length - lengthToWin; j++) {
                if (checkLine(i, j, 1, 1, symbol)) return true;
            }
        }

        for (int i = 0; i <= board.length - lengthToWin; i++) {
            for (int j = lengthToWin - 1; j < board.length; j++) {
                if (checkLine(i, j, 1, -1, symbol)) return true;
            }
        }

        return false;
    }

    private boolean checkLine(int startRow, int startCol, int rowIncrement, int colIncrement, char symbol) {
        for (int k = 0; k < lengthToWin; k++) {
            if (board[startRow + k * rowIncrement][startCol + k * colIncrement] != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean isDraw() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return !wins('X') && !wins('O');
    }

    public int evalBoard() {
        if (wins('O')) return 3;
        if (wins('X')) return 0;
        if (isDraw()) return 2;
        return 1;
    }
}
