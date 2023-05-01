package MainPackage;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.util.ArrayList;
import java.util.Objects;

public class GameLauncher {
    public ChessBoard game;
    public ArrayList<Piece> pieces;
    public Piece selected;
    public boolean turn;
    public GameLauncher(){
        game = new ChessBoard();
        game.setClock(this);
        initializePieces();
        turn = true;
    }
    public void initializePieces(){
        pieces = new ArrayList<Piece>();
        // white
        pieces.add( new bishop(true,"F1",game));
        pieces.add(new bishop(true,"C1",game));
        pieces.add(new king(true, "E1",game));
        pieces.add(new queen(true, "D1",game));
        pieces.add(new knight(true,"G1",game));
        pieces.add(new knight(true,"B1",game));
        pieces.add(new rook(true,"H1",game));
        pieces.add(new rook(true,"A1",game));
        pieces.add(new pawn(true,"A2",game));
        pieces.add( new pawn(true,"B2",game));
        pieces.add( new pawn(true,"C2",game));
        pieces.add( new pawn(true,"D2",game));
        pieces.add( new pawn(true,"E2",game));
        pieces.add( new pawn(true,"F2",game));
        pieces.add( new pawn(true,"G2",game));
        pieces.add( new pawn(true,"H2",game));
        pieces.add( new bishop(false,"F8",game));
        pieces.add( new bishop(false,"C8",game));
        pieces.add( new king(false, "E8",game));
        pieces.add( new queen(false, "D8",game));
        pieces.add( new knight(false,"G8",game));
        pieces.add( new knight(false,"B8",game));
        pieces.add( new rook(false,"H8",game));
        pieces.add( new rook(false,"A8",game));
        pieces.add( new pawn(false,"A7",game));
        pieces.add( new pawn(false,"B7",game));
        pieces.add( new pawn(false,"C7",game));
        pieces.add( new pawn(false,"D7",game));
        pieces.add( new pawn(false,"E7",game));
        pieces.add( new pawn(false,"F7",game));
        pieces.add( new pawn(false,"G7",game));
        pieces.add( new pawn(false,"H7",game));
    }
    public void Clock(String clickedSquare) {
        if(getPiece(clickedSquare) == null){
            if(selected == null)return;
            if(selected.availableMoves.contains(clickedSquare)||selected.eating.contains(clickedSquare)){

                if(selected.eating.contains(clickedSquare))
                    removePiece(clickedSquare);
                selected.Unselect();
                selected.transport(clickedSquare);turn = !turn;
                selected = null;
            }
            else{
                selected.Unselect();
                selected = null;
            }
            return;
        }
        boolean side = Objects.requireNonNull(getPiece(clickedSquare)).pieceSide;
        Piece piece = getPiece(clickedSquare);

        if(side != turn){
            if(selected == null) return;
            if(selected.availableMoves.contains(clickedSquare)||selected.eating.contains(clickedSquare)){

                if(selected.eating.contains(clickedSquare))
                    removePiece(clickedSquare);
                selected.Unselect();
                selected.transport(clickedSquare);turn = !turn;
                selected = null;
            }
            else {
                selected.Unselect();
                selected = null;
            }
        }
        else{
            assert piece != null;
            if(selected != null)
                 selected.Unselect();
            piece.Select();
            selected = piece;
        }
    }
    private Piece getPiece(String pos){
        for(Piece p : pieces){
            if(p.getPosition().equals(pos))
                return p;
        }
        return null;
    }
    private void removePiece(String pos){
        for(int i = 0 ; i< pieces.size(); i++){
            if(pieces.get(i).getPosition().equals(pos))
                pieces.remove(i);
        }
    }
    private void filterAllies(Piece mine)
    {
        for(int i = 0 ;i < mine.availableMoves.size();i++)
        {
            if(ally(mine, mine.availableMoves.get(i)))mine.availableMoves.remove(i);
        }
    }
    public boolean ally(Piece mine,String pos){
        if(getPiece(pos) == null)return false;
        return Objects.requireNonNull(getPiece(pos)).pieceSide == mine.pieceSide;
    }
    public static void main(String [] args)
    {
        new GameLauncher();
    }
}
