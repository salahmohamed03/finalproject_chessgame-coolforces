package MainPackage;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.util.Objects;

public class GameLauncher {
    public ChessBoard game;
    public Piece[] pieces;
    public Piece selected;
    public boolean turn;
    public GameLauncher(){
        game = new ChessBoard();
        game.setClock(this);
        initializePieces();
        turn = true;
    }
    public void initializePieces(){
        pieces = new Piece[32];
        // white
        pieces[0] = new bishop(true,"F1",game);
        pieces[1] = new bishop(true,"C1",game);
        pieces[2] = new king(true, "E1",game);
        pieces[3] = new queen(true, "D1",game);
        pieces[4] = new knight(true,"G1",game);
        pieces[5] = new knight(true,"B1",game);
        pieces[6] = new rook(true,"H1",game);
        pieces[7] = new rook(true,"A1",game);
        pieces[8] = new pawn(true,"A2",game);
        pieces[9]  = new pawn(true,"B2",game);
        pieces[10] = new pawn(true,"C2",game);
        pieces[11] = new pawn(true,"D2",game);
        pieces[12] = new pawn(true,"E2",game);
        pieces[13] = new pawn(true,"F2",game);
        pieces[14] = new pawn(true,"G2",game);
        pieces[15] = new pawn(true,"H2",game);
        pieces[16] = new bishop(false,"F8",game);
        pieces[17] = new bishop(false,"C8",game);
        pieces[18] = new king(false, "E8",game);
        pieces[19] = new queen(false, "D8",game);
        pieces[20] = new knight(false,"G8",game);
        pieces[21] = new knight(false,"B8",game);
        pieces[22] = new rook(false,"H8",game);
        pieces[23] = new rook(false,"A8",game);
        pieces[24] = new pawn(false,"A7",game);
        pieces[25] = new pawn(false,"B7",game);
        pieces[26] = new pawn(false,"C7",game);
        pieces[27] = new pawn(false,"D7",game);
        pieces[28] = new pawn(false,"E7",game);
        pieces[29] = new pawn(false,"F7",game);
        pieces[30] = new pawn(false,"G7",game);
        pieces[31] = new pawn(false,"H7",game);
    }
    public void Clock(String clickedSquare)
    {
        if(getPiece(clickedSquare) == null){
            if(selected == null)return;
            if(selected.availableMoves.contains(clickedSquare)){
                selected.Unselect();
                selected.transport(clickedSquare);
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
            if(selected.availableMoves.contains(clickedSquare)){
                selected.Unselect();
                selected.transport(clickedSquare);
                selected = null;
            }
        }
        else{
            assert piece != null;
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
    public static void main(String [] args)
    {
        new GameLauncher();
    }
}
