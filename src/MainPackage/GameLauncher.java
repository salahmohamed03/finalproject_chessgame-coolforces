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
    public boolean gameStatus;
    public GameActions promotion;
    public GameActions result;
    public boolean turn;
    public GameLauncher() {
        game.setClock(this);
        this.initializePieces();
        this.turn = false;
        gameStatus = true;
    }
    public void Clock(String clickedSquare) {
        if(!gameStatus)return;
        kingEscape(turn);
        kingChecked(turn);
        handlingMove(clickedSquare);
        kingEscape(turn);
        kingChecked(turn);
        checkPromotion(!turn);
        isEndGame(turn);
    }
    public void initializePieces() {
        this.pieces = new ArrayList();
        this.pieces.add(new bishop(true, "F1", game));
        this.pieces.add(new bishop(true, "C1", game));
        this.pieces.add(new king(true, "E1", game));
        this.pieces.add(new queen(true, "D1", game));
        this.pieces.add(new knight(true, "G1", game));
        this.pieces.add(new knight(true, "B1", game));
        this.pieces.add(new rook(true, "H1", game));
        this.pieces.add(new rook(true, "A1", game));
        this.pieces.add(new pawn(true, "A2", game));
        this.pieces.add(new pawn(true, "B2", game));
        this.pieces.add(new pawn(true, "C2", game));
        this.pieces.add(new pawn(true, "D2", game));
        this.pieces.add(new pawn(true, "E2", game));
        this.pieces.add(new pawn(true, "F2", game));
        this.pieces.add(new pawn(true, "G2", game));
        this.pieces.add(new pawn(true, "H2", game));
        this.pieces.add(new bishop(false, "F8",game));
        this.pieces.add(new bishop(false, "C8",game));
        this.pieces.add(new king(false, "E8", game));
        this.pieces.add(new queen(false, "D8", game));
        this.pieces.add(new knight(false, "G8",game));
        this.pieces.add(new knight(false, "B8",game));
        this.pieces.add(new rook(false, "H8", game));
        this.pieces.add(new rook(false, "A8", game));
        this.pieces.add(new pawn(false, "A7", game));
        this.pieces.add(new pawn(false, "B7", game));
        this.pieces.add(new pawn(false, "C7", game));
        this.pieces.add(new pawn(false, "D7", game));
        this.pieces.add(new pawn(false, "E7", game));
        this.pieces.add(new pawn(false, "F7", game));
        this.pieces.add(new pawn(false, "G7", game));
        this.pieces.add(new pawn(false, "H7", game));

        // test
        this.pieces.add(new queen(true,"A4",game));
        this.pieces.add(new rook(true,"E1",game));
        this.pieces.add(new king(false,"E4",game));
        this.pieces.add(new pawn(true,"E2",game));
        this.pieces.add(new king(true,"F8",game));

        updateValidMoves(true);
        updateCapture(true);
        updateValidMoves(false);
        updateCapture(false);
        kingEscape(turn);
        kingChecked(turn);
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
    private void isEndGame(boolean turn){
        if(checkWinner(turn) == (Object) true){
            promotion.showResult(1);
            gameStatus = false;
        }
        else if(checkWinner(turn) == (Object) false){
            promotion.showResult(-1);
            gameStatus = false;
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
            gameStatus = false;
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
        updateValidMoves(side);
        updateCapture(side);
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
                ArrayList<String> temp = new ArrayList<String>();
                    temp.addAll(p.eating);
                    temp.addAll(p.moving);
                    temp.addAll(p.pawnDiagonal);
                result.addAll(temp);
            }
        }
        return result;
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
    private Object checkWinner(boolean turn) {
        ArrayList temp;
        if (!turn) {
            kingChecked(false);
            temp = new ArrayList(this.possession(false));
            temp.removeAll(this.getPiece(false, 5).moving);
            if (temp.size() == 0 && this.getPiece(false, 5).movable().size() == 0) {
                return true;
            }
        } else {
            kingChecked(true);
            temp = new ArrayList(this.possession(true));
            temp.removeAll(this.getPiece(true, 5).moving);
            if (temp.size() == 0 && this.getPiece(true, 5).movable().size() == 0) {
                return false;
            }
        }
        return null;
    }
    private Piece getPiece(boolean side , int id){
        for(Piece p :pieces){
            if(p.pieceSide == side && p.id == id){
                return p;
            }
        }
        return null;
    }
    private void kingChecked(boolean side) {
        if (threateningKing(side).size() == 0) return;
        if (threateningKing(side).size() == 1) {
            Piece attacker = threateningKing(side).get(0);
            if (attacker.id == 2 || attacker.id == 3 || attacker.id == 6)
                eatThreat(attacker.position, side);
            else if (attacker.id == 4 || attacker.id == 1) {
                if (attacker.position.charAt(0) == kingPosition(side).charAt(0)) {
                    blockCheck(1, attacker.position, side);
                    kingWay(0, attacker.position, side);
                } else if (attacker.position.charAt(1) == kingPosition(side).charAt(1)) {
                    blockCheck(0, attacker.position, side);
                    kingWay(1, attacker.position, side);
                } else if (attacker.id == 4) {
                    eatThreat(attacker.position, side);
                }
            }
        } else {
            for(Piece attacker: threateningKing(side)) {
                if (attacker.id == 4 || attacker.id == 1) {
                    if (attacker.position.charAt(0) == kingPosition(side).charAt(0)) {
                        kingWay(0, attacker.position, side);
                    } else if (attacker.position.charAt(1) == kingPosition(side).charAt(1)) {
                        kingWay(1, attacker.position, side);
                    }
                }
            }
        }
    }
    private  void kingWay(int axis,String position ,boolean side){
        ArrayList<String> notAllowed = new ArrayList<String>();
        for(Piece p:pieces){
            if(p.id == 5 &&p.pieceSide == side){
                for(String pos:p.moving){if(pos.charAt(axis)==position.charAt(axis))notAllowed.add(pos);}
                p.moving.removeAll(notAllowed);
            }
        }
    }
    private void eatThreat(String pos, boolean side){
        for(Piece p :pieces)
            if(p.pieceSide == side&&p.id != 5) {
                p.moving.clear();
                p.eating.retainAll(Collections.singletonList(pos));
                p.pawnDiagonal.retainAll(Collections.singletonList(pos));
            }
    }
    private void blockCheck(int axis, String pos , boolean side){
        ArrayList<String> allowed = new ArrayList<String>();
        int dist = pos.charAt(axis) - kingPosition(side).charAt(axis);
        if(dist == 1){
            eatThreat(pos,side);
        }
        else {
            dist += -Math.signum(dist);
            while(Math.abs(dist) != 0){
                allowed.add(Piece.move(pos,(axis == 0)?0:dist*-1,(axis == 0)?dist*-1:0));
                dist += -Math.signum(dist);
            }
            for(Piece p:pieces){
                if(p.pieceSide == side&&p.id != 5) {
                    p.eating.retainAll(Collections.singletonList(pos));
                    p.moving.retainAll(allowed);
                }
            }
        }
    }
    private ArrayList<Piece> threateningKing(boolean side){
        ArrayList<Piece> threat = new ArrayList<Piece>();
        for(Piece p: pieces){
            if(p.eating.contains(kingPosition(side))){
                threat.add(p);
            }
        }
        return threat;
    }
    private String kingPosition(boolean side){
        for(Piece p : pieces){
            if(p.pieceSide == side && p.id == 5){
                return p.position;
            }
        }
        return null;
    }
    private void kingEscape(boolean side){
        for(Piece p : pieces){
            if(p.pieceSide == side&&p.id == 5)
            {
                ArrayList<String> temp = possession(!side);
                for(Piece pp : pieces){
                    if(pp.pieceSide == !side &&pp.id == 6){
                        if(pp.twoMoves != null){
                            for(int i = 0 ;i < temp.size();i++){
                                if(Objects.equals(temp.get(i), pp.twoMoves)){
                                    temp.remove(i);
                                    break;
                                }
                            }
                        }
                    }
                }
                p.availableMoves.removeAll(temp);
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
    public void start(){
        game.show();
    }
    public static void main(String[] args) {
        new GameLauncher();
        game.show();
    }
}
