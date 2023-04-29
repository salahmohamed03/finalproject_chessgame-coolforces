package MainPackage;

import java.util.ArrayList;

public abstract class Piece {
    protected ChessBoard Board;
    protected boolean pieceSide;
    protected String position;
    // mostafa or anas
    protected String move(String pos , int vertical, int horizontal){
        //salah: pos is represented like "A1" or "E4"
        //salah: your task is to return the new position after moving
        //Anas: vertical --->number inc, horizontal--->letter increase
        //Anas: letters:A---H, numbers: 1---8
        String newPos = "";

        // your code

        return newPos;
    }
}
class bishop extends Piece{
    public bishop(boolean side, String pos, ChessBoard cb){
        this.Board = cb;
        this.pieceSide = side;
        this.position = pos;
    }
    // mostafa or anas
    public ArrayList<String> ValidMoves() {
        ArrayList<String> result = new ArrayList<>();
        //this function returns all moves that the bishop can move
        //use Board.Empty(for example "E4") to check if certain position have piece on it
        //use the function move to get the position after certain move
        // use result.add("E4") to add a valid move
        return null;
    }
    // mostafa or anas
    public void ShowValidMoves(){
        // use Board.boardLight(the position , true to set is valid move)
        // use the previous function to get the valid moves
    }
}
// After that make it for all pieces

