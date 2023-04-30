package MainPackage;

import java.util.ArrayList;

public abstract class Piece {
    protected ChessBoard Board;
    protected boolean pieceSide;
    protected String position;

    //Function provides the new position of a piece
    protected static String move(String pos , int vertical, int horizontal) {
        String posLetter = String.valueOf((char)(pos.charAt(0)+horizontal));
        String posNumber = String.valueOf((char)(pos.charAt(1)+vertical));
        String newPos = posLetter + posNumber;
        if((newPos.charAt(0)<'A') || (newPos.charAt(0)>'H') || (newPos.charAt(1)<'1') || (newPos.charAt(1)>'8'))
            return null;
        return newPos;
    }
}

class bishop extends Piece {
    public bishop(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        this.pieceSide = side;
        this.position = pos;
    }

    //This function returns all moves that the bishop can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();
        //Checking for the horizontal move
        String horizontalRight = move(position, 0, 1);
        String horizontalLeft = move(position, 0, -1);
        if ((horizontalRight != null)) {
            result.add(horizontalRight);
        }
        if ((horizontalLeft != null)) {
            result.add(horizontalLeft);
        }
        //Checking for diagonal moves
        for (int i = 1; i <= 3; i++) {
            String firstDiagonalRight = move(position, i, i);
            String firstDiagonalLeft = move(position, -i, -i);
            String secondDiagonalRight = move(position, -i, i);
            String secondDiagonalLeft = move(position, i, -i);
            if (firstDiagonalRight != null) {
                result.add(firstDiagonalRight);
            }
            if (firstDiagonalLeft != null) {
                result.add(firstDiagonalLeft);
            }
            if (secondDiagonalRight != null) {
                result.add(secondDiagonalRight);
            }
            if (secondDiagonalLeft != null) {
                result.add(secondDiagonalLeft);
            }
        }
        return result;
    }

    public void Select() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, true);
        }
    }

    public void Unselect() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, false);
        }
    }
}

class pawn extends Piece {
    public pawn(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        this.pieceSide = side;
        this.position = pos;
    }

    //This function returns all moves that the pawn can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();
        
        return result;
    }

    public void Select() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, true);
        }
    }

    public void Unselect() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, false);
        }
    }
}

class king extends Piece {
    public king(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        this.pieceSide = side;
        this.position = pos;
    }

    //This function returns all moves that the king can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();

        return result;
    }

    public void Select() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, true);
        }
    }

    public void Unselect() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, false);
        }
    }
}

class queen extends Piece {
    public queen(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        this.pieceSide = side;
        this.position = pos;
    }

    //This function returns all moves that the queen can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            String horizontalRight = move(position, 0, i);
            if (horizontalRight != null) {
                result.add(horizontalRight);
                if (!Board.Empty(horizontalRight)) {
                    break;
                }
            }
        }

        for (int i = 1; i <= 7; i++) {
            String horizontalLeft = move(position, 0, -i);
            if (horizontalLeft != null) {
                result.add(horizontalLeft);
                if (!Board.Empty(horizontalLeft)) {
                    break;
                }
            }
        }

        for (int i = 1; i <= 7; i++) {
            String verticalUp = move(position, i, 0);
            if (verticalUp != null) {
                result.add(verticalUp);
                if (!Board.Empty(verticalUp)) {
                    break;
                }
            }
        }

        for (int i = 1; i <= 7; i++) {
            String verticalDown = move(position, -i, 0);
            if (verticalDown != null) {
                result.add(verticalDown);
                if (!Board.Empty(verticalDown)) {
                    break;
                }
            }
        }
        //Checking for diagonal moves
        for (int i = 1; i <= 3; i++) {
            String firstDiagonalRight = move(position, i, i);
            String firstDiagonalLeft = move(position, -i, -i);
            String secondDiagonalRight = move(position, -i, i);
            String secondDiagonalLeft = move(position, i, -i);
            if (firstDiagonalRight != null) {
                result.add(firstDiagonalRight);
            }
            if (firstDiagonalLeft != null) {
                result.add(firstDiagonalLeft);
            }
            if (secondDiagonalRight != null) {
                result.add(secondDiagonalRight);
            }
            if (secondDiagonalLeft != null) {
                result.add(secondDiagonalLeft);
            }
        }
        return result;
    }

    public void Select() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, true);
        }
    }

    public void Unselect() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, false);
        }
    }
}

class rook extends Piece {
    public rook(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        this.pieceSide = side;
        this.position = pos;
    }

    //This function returns all moves that the rook can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            String horizontalRight = move(position, 0, i);
            if (horizontalRight != null) {
                result.add(horizontalRight);
                if (!Board.Empty(horizontalRight)) {
                    break;
                }
            }
        }

        for (int i = 1; i <= 7; i++) {
            String horizontalLeft = move(position, 0, -i);
            if (horizontalLeft != null) {
                result.add(horizontalLeft);
                if (!Board.Empty(horizontalLeft)) {
                    break;
                }
            }
        }

        for (int i = 1; i <= 7; i++) {
            String verticalUp = move(position, i, 0);
            if (verticalUp != null) {
                result.add(verticalUp);
                if (!Board.Empty(verticalUp)) {
                    break;
                }
            }
        }

        for (int i = 1; i <= 7; i++) {
            String verticalDown = move(position, -i, 0);
            if (verticalDown != null) {
                result.add(verticalDown);
                if (!Board.Empty(verticalDown)) {
                    break;
                }
            }
        }
        return result;
    }

    public void Select() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, true);
        }
    }

    public void Unselect() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, false);
        }
    }
}

class knight extends Piece {
    public knight(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        this.pieceSide = side;
        this.position = pos;
    }

    //This function returns all moves that the knight can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();
        if (move(position, 2, 3) != null) {
            result.add(move(position, 2, 3));
        }
        if (move(position, 2, -3) != null) {
            result.add(move(position, 2, -3));
        }
        if (move(position, -2, 3) != null) {
            result.add(move(position, -2, 3));
        }
        if (move(position, -2, -3) != null) {
            result.add(move(position, -2, -3));
        }
        if (move(position, 3, 2) != null) {
            result.add(move(position, 3, 2));
        }
        if (move(position, -3, 2) != null) {
            result.add(move(position, -3, 2));
        }
        if (move(position, 3, -2) != null) {
            result.add(move(position, 3, -2));
        }
        if (move(position, -3, -2) != null) {
            result.add(move(position, -3, -2));
        }
        return result;
    }

    public void Select() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, true);
        }
    }

    public void Unselect() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, false);
        }
    }
}


class test{
    public static void main(String []args )
    {
        ChessBoard cb = new ChessBoard();
        queen b = new queen(true, "D3",cb);
        cb.move_piece("D1","D3");
        cb.move_piece("C1", "A3");
        b.Select();
    }
}


