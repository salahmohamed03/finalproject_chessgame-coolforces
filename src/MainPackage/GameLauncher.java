//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package MainPackage;

import java.util.*;

public class GameLauncher {
    static public ChessBoard game = new ChessBoard();
    public ArrayList<Piece> pieces;
    public Piece selected;
    public String posProm;
    public boolean turnProm;
    public boolean promotionStatus;
    public GameActions promotion;
    public GameActions result;
    public boolean turn;
    public GameLauncher() {
        this.game.setClock(this);
        this.initializePieces();
        this.turn = true;
        promotionStatus = true;
    }

    public void initializePieces() {
        this.pieces = new ArrayList();
        this.pieces.add(new bishop(true, "F1", this.game));
        this.pieces.add(new bishop(true, "C1", this.game));
        this.pieces.add(new king(true, "E1", this.game));
        this.pieces.add(new queen(true, "F3", this.game));
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
        this.pieces.add(new king(false, "E6", this.game));
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
        this.pieces.add(new pawn(false, "H7", this.game));
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
            boolean side = (Objects.requireNonNull(this.getPiece(clickedSquare))).pieceSide;
            Piece piece = this.getPiece(clickedSquare);
            if (side != this.turn) {
                if (this.selected == null) {
                    return;
                }
                if (!this.selected.moving.contains(clickedSquare) && !this.selected.eating.contains(clickedSquare)) {
                    this.selected.Unselect();
                } else {
                    if (this.selected.eating.contains(clickedSquare)) {
                        this.removePiece(clickedSquare);
                    }
                    this.selected.Unselect();
                    this.selected.transport(clickedSquare,pieces);
                    this.turn = !this.turn;
                }
                this.selected = null;
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
        if(!promotionStatus)return;
        kingEscape(turn);
        handlingMove(clickedSquare);
        checkPromotion(!turn);
        isEndGame(turn);
    }
    private void isEndGame(boolean turn){
        if(checkWinner(turn) == (Object) true){
            promotion.showResult(1);
        }
        else if(checkWinner(turn) == (Object) false){
            promotion.showResult(-1);
        }
    }
    private Piece getPiece(String pos){
        for(Piece p : pieces){
            if(p.getPosition().equals(pos))
                return p;
        }
        return null;
    }
    private void checkPromotion(boolean turn){
        promotion = new GameActions();
        this.promotion.gl = this;
        if(getPromoted(turn) != null)
        {
            promotionStatus = false;
            posProm = Objects.requireNonNull(getPromoted(turn)).position;
            turnProm = turn;
            removePiece(posProm);
            promotion.promotionWindow(turn);
        }
    }
    public void promote(String pos,boolean side,int id){
        switch (id) {
            case 1 -> {pieces.add(new rook(side, pos, game));}
            case 2 -> {pieces.add(new knight(side, pos, game));}
            case 3 -> {pieces.add(new bishop(side, pos, game));}
            case 4 -> {pieces.add(new queen(side, pos, game));}
        }
    }
    private Piece getPromoted(boolean side){
        char row = side?'8':'1';
        String pos = "A"+row;
        for(int i = 0 ;i < 8;i++){
            Piece check = getPiece(Piece.move(pos, 0, i));
            if(check == null)continue;
            if(check.id == 6){
                return check;
            }
        }
        return null;
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
                ArrayList<String> result =  new ArrayList<String>();
                result.addAll(p.eating);
                result.addAll(p.movable());
                return result;
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
    public boolean ally(Piece mine, String pos) {
        if (this.getPiece(pos) == null) {
            return false;
        } else {
            return ((Piece)Objects.requireNonNull(this.getPiece(pos))).pieceSide == mine.pieceSide;
        }
    }
    public static void main(String[] args) {
        new GameLauncher();
        game.show();
    }
}
