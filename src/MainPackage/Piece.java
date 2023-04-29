package MainPackage;

import java.lang.reflect.Array;
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
        ArrayList<String> greenMove = new ArrayList<>();
         //Checking for the horizontal move
        String horizontalRight = move(position, 0, 1);
        String horizontalLeft = move(position, 0, -1);
        if ((horizontalRight != null) && (Board.Empty(horizontalRight))) {
            greenMove.add(horizontalRight);
        }
        if ((horizontalLeft != null) && (Board.Empty(horizontalLeft))) { 
            greenMove.add(horizontalLeft);
        }
        //Checking for diagonal moves
        for (int i = 1; i <= 3; i++) {
            String firstDiagonalRight = move(position, i, i);
            String firstDiagonalLeft = move(position, -i, -i);
            String secondDiagonalRight = move(position, -i, i);
            String secondDiagonalLeft = move(position, i, -i);
     
            if ((firstDiagonalRight != null) && (Board.Empty(firstDiagonalRight))) {
                greenMove.add(firstDiagonalRight);
            }
         
            if ((firstDiagonalLeft != null) && (Board.Empty(firstDiagonalLeft))) {
                greenMove.add(firstDiagonalLeft);
            }
     
            if ((secondDiagonalRight != null) && (Board.Empty(secondDiagonalRight))) {
                greenMove.add(secondDiagonalRight);
            }
     
            if ((secondDiagonalLeft != null) && (Board.Empty(secondDiagonalLeft))) {
                greenMove.add(secondDiagonalLeft);
            }
         }
             return greenMove;
    }

    public ArrayList<String> InvalidMoves() {

        //This array List will contain all the invalid moves
        ArrayList<String> redMove = new ArrayList<>();
        //Checking for the horizontal move
        String horizontalRight = move(position, 0, 1);
        String horizontalLeft = move(position, 0, -1);
        if ((horizontalRight != null) && !(Board.Empty(horizontalRight))) {
            redMove.add(horizontalRight);
        }
        if ((horizontalLeft != null) && !(Board.Empty(horizontalLeft))) { 
            redMove.add(horizontalLeft);
        }
        //Checking for diagonal moves
        for (int i = 1; i <= 3; i++) {
            String firstDiagonalRight = move(position, i, i);
            String firstDiagonalLeft = move(position, -i, -i);
            String secondDiagonalRight = move(position, -i, i);
            String secondDiagonalLeft = move(position, i, -i);
    
            if ((firstDiagonalRight != null) && !(Board.Empty(firstDiagonalRight))) {
                redMove.add(firstDiagonalRight);
            }
        
            if ((firstDiagonalLeft != null) && !(Board.Empty(firstDiagonalLeft))) {
                redMove.add(firstDiagonalLeft);
            }
    
            if ((secondDiagonalRight != null) && !(Board.Empty(secondDiagonalRight))) {
                redMove.add(secondDiagonalRight);
            }
    
            if ((secondDiagonalLeft != null) && !(Board.Empty(secondDiagonalLeft))) {
                redMove.add(secondDiagonalLeft);
            }
        }
            return redMove;
    }
        
    //Colors the available moves green
    public void Select() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, true);
        }
    }
    //Colors the unavailable moves red
    public void Unselect() {
        ArrayList<String> UnavailableMoves = InvalidMoves();
        for (String move: UnavailableMoves) {
            Board.boardLight(move, false);
        }
    }
}

class test{
    public static void main(String []args )
    {

    }
}

