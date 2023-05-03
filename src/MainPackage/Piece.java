package MainPackage;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece {
    protected ChessBoard Board;
    public int id;
    protected Icon icon;
    protected ArrayList<String> availableMoves;
    protected boolean pieceSide;
    protected String position;
    public ArrayList<String> eating = new ArrayList<String>();
    public ArrayList<String> moving = new ArrayList<String>();

    //Function provides the new position of a piece
    protected static String move(String pos , int vertical, int horizontal) {
        String posLetter = String.valueOf((char)(pos.charAt(0)+horizontal));
        String posNumber = String.valueOf((char)(pos.charAt(1)+vertical));
        String newPos = posLetter + posNumber;
        if((newPos.charAt(0)<'A') || (newPos.charAt(0)>'H') || (newPos.charAt(1)<'1') || (newPos.charAt(1)>'8'))
            return null;
        return newPos;
    }

    public String getPosition(){
        return position;
    }
    public void removePiece(){
        this.position = null;
    }
    public void transport(String p2,ArrayList<Piece> pieces){
        if(p2.equals(position))return;
        Board.move_piece(position,p2);
        position = p2;
        for(Piece p: pieces)
        {
            p.availableMoves = p.ValidMoves();
            p.eating.clear();
            p.moving.clear();
            p.eatingMoves();
        }
    }
    public abstract ArrayList<String> ValidMoves();
    public void Select() {
        for (String move: moving) {
            Board.boardLight(move, true);
        }
        for(String move : eating)
        {
            Board.eatingLight(move);
        }
    }

    public void Unselect() {
        for (String move: moving) {
            Board.boardLight(move, false);
        }
        for(String move : eating)
        {
            Board.eatingLight(move);
        }
    }
    public void eatingMoves(){
        eating.clear();
        moving.clear();
        for(int i = 0 ;i < availableMoves.size();i++){
           Object t = Board.isAlly(position,(availableMoves.get(i)));
            if(Board.isAlly(position,(availableMoves.get(i))) == (Object) false){
                eating.add(availableMoves.get(i));
            }
            else
                moving.add(availableMoves.get(i));
        }
    }
}

class bishop extends Piece {

    public bishop(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        id = 3;
        this.icon = (side)?Board.icon.white_bishop:Board.icon.black_bishop;
        this.pieceSide = side;
        this.position = pos;
        cb.getButton(pos).setIcon(icon);

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

}

class pawn extends Piece {
    public pawn(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        id = 6;
        this.icon = (side)?Board.icon.white_pawn:Board.icon.black_pawn;
        this.pieceSide = side;
        this.position = pos;
        cb.getButton(pos).setIcon(icon);
    }
    //This function returns all moves that the pawn can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();
        int sign =(pieceSide)?1:-1;
        String move_one= move(position,sign,0);
        String eat_right=move(position,sign,sign);
        String eat_left=move(position,sign,-1*sign);

        if(move_one!=null)
             result.add(move_one);

        String move_two= move(position,2*sign,0);
        if(position.charAt(1)==((pieceSide)?'2':'7')&& Board.Empty(move_one))
            if(move_two != null)
                 result.add(move_two);
        if(eat_left != null)
             result.add(eat_left);
        if(eat_right!=null)
             result.add(eat_right);
        return result;
    }
    public void eatingMoves(){
        int size = availableMoves.size();
        int sign = pieceSide?1:-1;
        for(int i = 0 ;i < size;i++){
            if(Board.isAlly(position,(availableMoves.get(i))) == (Object) false){
                    eating.add(availableMoves.get(i));
            }
            else{
                String p1 =  move(position, sign, 1);
                String p2 =  move(position, sign, -1);
                if(Board.isAlly(position,(availableMoves.get(i))) == null)
                    if(!Objects.equals(availableMoves.get(i), p1) && !Objects.equals(availableMoves.get(i), p2))
                        moving.add(availableMoves.get(i));
            }
        }
    }
}
class king extends Piece {
    public king(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        id = 5;
        this.icon = (side)?Board.icon.white_king:Board.icon.black_king;
        this.pieceSide = side;
        this.position = pos;
        cb.getButton(pos).setIcon(icon);
    }

    //This function returns all moves that the king can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();
        String move_right=move(position,0,1);
        if(move_right!=null)
            result.add(move_right);
        String move_left=move(position,0,-1);
        if(move_left!=null)
            result.add(move_left);
        String move_up=move(position,1,0);
        if(move_up!=null)
            result.add(move_up);
        String move_down=move(position,-1,0);
        if(move_down!=null)
            result.add(move_down);
        String move_up_right=move(position,1,1);
        if(move_up_right!=null)
            result.add(move_up_right);
        String move_up_left=move(position,1,-1);
        if(move_up_left!=null)
            result.add(move_up_left);
        String move_down_right=move(position,-1,1);
        if(move_down_right!=null)
            result.add(move_down_right);
        String move_down_left=move(position,-1,-1);
        if(move_down_left!=null)
            result.add(move_down_left);
        return result;
    }
}

class queen extends Piece {
    public queen(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        id = 4;
        this.icon = (side)?Board.icon.white_queen:Board.icon.black_queen;
        this.pieceSide = side;
        this.position = pos;
        cb.getButton(pos).setIcon(icon);
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

}

class rook extends Piece {
    public rook(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        id = 1;
        this.icon = (side)?Board.icon.white_rook:Board.icon.black_rook;
        this.pieceSide = side;
        this.position = pos;
        cb.getButton(pos).setIcon(icon);
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
}

class knight extends Piece {
    public knight(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        id = 2;
        this.icon = (side)?Board.icon.white_knight:Board.icon.black_knight;
        this.pieceSide = side;
        this.position = pos;
        cb.getButton(pos).setIcon(icon);
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

}


class test{
    public static void main(String []args )
    {
        ChessBoard cb = new ChessBoard();

        pawn b = new pawn(true, "E2",cb);
        pawn c = new pawn(true, "D2",cb);
        cb.eatingLight("E2");
        //pawn bb = new pawn(false, "E4",cb);
        // b.Select();
        //b.Unselect();
    }
}


