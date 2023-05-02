//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package MainPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class GameLauncher {
    public ChessBoard game = new ChessBoard();
    public ArrayList<Piece> pieces;
    public Piece selected;
    public boolean turn;

    public GameLauncher() {
        this.game.setClock(this);
        this.initializePieces();
        this.turn = true;
    }

    public void initializePieces() {
        this.pieces = new ArrayList();
        this.pieces.add(new bishop(true, "F1", this.game));
        this.pieces.add(new bishop(true, "C1", this.game));
        this.pieces.add(new king(true, "E1", this.game));
        this.pieces.add(new queen(true, "D1", this.game));
        this.pieces.add(new knight(true, "G1", this.game));
        this.pieces.add(new knight(true, "B1", this.game));
        this.pieces.add(new rook(true, "H1", this.game));
        this.pieces.add(new rook(true, "A1", this.game));
        this.pieces.add(new pawn(true, "A2", this.game));
        this.pieces.add(new pawn(true, "B2", this.game));
        this.pieces.add(new pawn(true, "C2", this.game));
        this.pieces.add(new pawn(true, "D2", this.game));
        this.pieces.add(new pawn(true, "E2", this.game));
        this.pieces.add(new pawn(true, "F2", this.game));
        this.pieces.add(new pawn(true, "G2", this.game));
        this.pieces.add(new pawn(true, "H2", this.game));
        this.pieces.add(new bishop(false, "F8", this.game));
        this.pieces.add(new bishop(false, "C8", this.game));
        this.pieces.add(new king(false, "E4", this.game));
        this.pieces.add(new queen(false, "D8", this.game));
        this.pieces.add(new knight(false, "G8", this.game));
        this.pieces.add(new knight(false, "B8", this.game));
        this.pieces.add(new rook(false, "H8", this.game));
        this.pieces.add(new rook(false, "A8", this.game));
        this.pieces.add(new pawn(false, "A7", this.game));
        this.pieces.add(new pawn(false, "B7", this.game));
        this.pieces.add(new pawn(false, "C7", this.game));
        this.pieces.add(new pawn(false, "D7", this.game));
        this.pieces.add(new pawn(false, "E7", this.game));
        this.pieces.add(new pawn(false, "F7", this.game));
        this.pieces.add(new pawn(false, "G7", this.game));
        this.pieces.add(new pawn(false, "H6", this.game));
        updateValidMoves(true);
        updateCapture(true);
        updateValidMoves(false);
        updateCapture(false);
    }
    public void handlingMove(String clickedSquare) {
        if (this.getPiece(clickedSquare) == null) {
            if (this.selected != null) {
                if (!this.selected.moving.contains(clickedSquare) && !this.selected.eating.contains(clickedSquare)) {
                    this.selected.Unselect();
                    this.selected = null;
                } else {
                    if (this.selected.eating.contains(clickedSquare)) {
                        this.removePiece(clickedSquare);
                    }

                    this.selected.Unselect();
                    this.selected.transport(clickedSquare,pieces);
                    this.turn = !this.turn;
                    this.selected = null;
                }

            }
        } else {
            boolean side = ((Piece)Objects.requireNonNull(this.getPiece(clickedSquare))).pieceSide;
            Piece piece = this.getPiece(clickedSquare);
            if (side != this.turn) {
                if (this.selected == null) {
                    return;
                }

                if (!this.selected.moving.contains(clickedSquare) && !this.selected.eating.contains(clickedSquare)) {
                    this.selected.Unselect();
                    this.selected = null;
                } else {
                    if (this.selected.eating.contains(clickedSquare)) {
                        this.removePiece(clickedSquare);
                    }

                    this.selected.Unselect();
                    this.selected.transport(clickedSquare,pieces);
                    this.turn = !this.turn;
                    this.selected = null;
                }
            } else {
                assert piece != null;

                if (this.selected != null) {
                    this.selected.Unselect();
                }

                piece.Select();
                this.selected = piece;
            }

        }
    }
    public void Clock(String clickedSquare) {
        kingEscape(turn);
        handlingMove(clickedSquare);
        System.out.println(checkWinner(turn));
    }

    private Piece getPiece(String pos) {
        Iterator var2 = this.pieces.iterator();

        Piece p;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            p = (Piece)var2.next();
        } while(!p.getPosition().equals(pos));

        return p;
    }
        public ArrayList<String> possession(boolean side){
        ArrayList<String> result = new ArrayList<>();
        for(Piece p : pieces){
            if(p.pieceSide == side)
            {
                ArrayList<String> temp = p.ValidMoves();
                for(int i = 0 ; i< temp.size();i++)
                {
                    result.add(temp.get(i));
                }
            }
        }
        return result;
    }
    private ArrayList<String> kingMoves(boolean side){
        for(Piece p : pieces){
            if(p.pieceSide == side && p.id == 5){
                return p.ValidMoves();
            }
        }
        return null;
    }
    private void updateCapture(boolean turn){
        for(Piece p : pieces){
            if(p.pieceSide == turn){
                p.eatingMoves();
            }
        }
    }
    private void updateValidMoves(boolean turn){
        for(Piece p: pieces)
        {
            if(p.pieceSide == turn)
            {
                p.availableMoves = p.ValidMoves();
            }
        }
    }
    private Object checkWinner(boolean turn){
        if(!turn){
            if(possession(true).containsAll(Objects.requireNonNull(kingMoves(false))))
                return true;
        }
        else{
            if(possession(false).containsAll(Objects.requireNonNull(kingMoves(true))))
                return false;
        }
        return null;
    }
    private void kingEscape(boolean side){
        for(Piece p : pieces){
            if(p.pieceSide == side&&p.id == 5)
            {
                ArrayList<String> temp = possession(!side);
                p.availableMoves.removeAll(possession(!side));
                p.eatingMoves();
            }
        }
    }
    private void removePiece(String pos) {
        for(int i = 0; i < this.pieces.size(); ++i) {
            if (((Piece)this.pieces.get(i)).getPosition().equals(pos)) {
                this.pieces.remove(i);
            }
        }

    }
    private void filterAllies(Piece mine) {
        for(int i = 0; i < mine.availableMoves.size(); ++i) {
            if (this.ally(mine, (String)mine.availableMoves.get(i))) {
                mine.availableMoves.remove(i);
            }
        }

    }

    public boolean ally(Piece mine, String pos) {
        if (this.getPiece(pos) == null) {
            return false;
        } else {
            return ((Piece)Objects.requireNonNull(this.getPiece(pos))).pieceSide == mine.pieceSide;
        }
    }

    public static void main(String[] args) {
        new GameLauncher();
        Promote p = new Promote();
    }
}

//    public ArrayList<String> possession(boolean side){
//        ArrayList<String> result = new ArrayList<>();
//        for(Piece p : pieces){
//            if(p.pieceSide == side)
//            {
//                ArrayList<String> temp = p.ValidMoves();
//                for(int i = 0 ; i< temp.size();i++)
//                {
//                    result.add(temp.get(i));
//                }
//            }
//        }
//        return result;
//    }
//    private ArrayList<String> kingMoves(boolean side){
//        for(Piece p : pieces){
//            if(p.pieceSide == side && p.id == 5){
//                return p.ValidMoves();
//            }
//        }
//        return null;
//    }
//    private void updateCapture(boolean turn){
//        for(Piece p : pieces){
//            if(p.pieceSide == turn){
//                p.eatingMoves();
//            }
//        }
//    }
//    private void updateValidMoves(boolean turn){
//        for(Piece p: pieces)
//        {
//            if(p.pieceSide == turn)
//            {
//                p.availableMoves = p.ValidMoves();
//            }
//        }
//    }
//    private Object checkWinner(boolean turn){
//        if(!turn){
//            if(possession(true).containsAll(Objects.requireNonNull(kingMoves(false))))
//                return true;
//        }
//        else{
//            if(possession(false).containsAll(Objects.requireNonNull(kingMoves(true))))
//                return false;
//        }
//        return null;
//    }