//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package MainPackage;

import java.util.*;

public class GameLauncher extends dataHandling{
    public ChessBoard game ;
    public boolean isTimer;
    public ArrayList<Piece> pieces;
    public Piece selected;
    public String posProm;
    public boolean turnProm;
    public boolean gameStatus;
    public GameActions actions;
    public int result;
    public ArrayList<String> gameMoves;
    public boolean turn;
    private User mainUser;
    private User oppUser;
    public GameLauncher(User mainUser, User oppUser)
    {
        this.mainUser=mainUser;
        this.oppUser = oppUser;
        game = new ChessBoard(mainUser,oppUser);
        start();
        gameMoves = new ArrayList<>();
        game.setClock(this);
        if(game.blackClock.finishedCheck())
        {
            isTimer = false;
        }
        else isTimer = true;
        this.initializePieces();
        this.turn = true;
        game.gameResult = true;
    }
    public void initializePieces() {
        this.pieces = new ArrayList<Piece>();
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
//        this.pieces.add(new rook(true,"H1",game));
//        this.pieces.add(new rook(true,"A1",game));
//        this.pieces.add(new king(true,"E1",game));
//        //this.pieces.add(new rook(false,"H8",game));
//       // this.pieces.add(new rook(false,"A8",game));
//        this.pieces.add(new king(false,"E8",game));
//        this.pieces.add(new queen(true,"E1",game));
//        this.pieces.add(new bishop(true,"E7",game));
//        this.pieces.add(new pawn(false,"F7",game));



//        this.pieces.add(new queen(true,"D1",game));
//        this.pieces.add(new bishop(true,"D4",game));
//        this.pieces.add(new king(false,"D6",game));
//        this.pieces.add(new bishop(false,"D3",game));
//        this.pieces.add(new king(true,"F8",game));

        updateValidMoves(true);
        updateCapture(true);
        updateValidMoves(false);
        updateCapture(false);
        kingEscape(turn);
        kingChecked(turn);
    }
    public void Clock(String clickedSquare) {
        gameStatus = game.gameResult;
        if(!gameStatus)return;
        blockingPiece(turn);
        kingEscape(turn);
        kingChecked(turn);
        handlingMove(clickedSquare);
        kingEscape(turn);
        kingChecked(turn);
        checkPromotion(!turn);
        blockingPiece(turn);
        isEndGame(turn);
    }
    private void blockingPiece(boolean side){
        Piece king = getPiece(side, 5);
        for(Piece p : pieces){
            if((p.id == 4 || p.id == 1)&& p.pieceSide == !side){
                assert king != null;
                Piece attacker = p;
                if(p.position.charAt(0) == king.position.charAt(0)){
                    boolean no = true;
                    int blockers = 0;
                    String pos = "";
                    int dist =king.position.charAt(1) - attacker.position.charAt(1);
                    dist += -Math.signum(dist);
                    while(Math.abs(dist) != 0){
                        if(ally(king,Piece.move(attacker.position,dist,0)))
                        {
                            blockers++;
                            pos = Piece.move(attacker.position,dist,0);
                        }
                        dist += -Math.signum(dist);
                    }
                    dist =king.position.charAt(1) - attacker.position.charAt(1);
                    dist += -Math.signum(dist);
                    while(Math.abs(dist) != 0){
                        String temp = Piece.move(attacker.position,dist,0);
                        if(ally(attacker,temp)){
                            no = false;
                        }
                        dist += -Math.signum(dist);
                    }
                    if(blockers == 1&&no) {
                        freezingPiece(pos,side,0);
                    }
                }
                else if(p.position.charAt(1) == king.position.charAt(1)){
                    boolean no = true;
                    int blockers = 0;
                    String pos = "";
                    int dist =king.position.charAt(0) - attacker.position.charAt(0);
                    dist += -Math.signum(dist);
                    while(Math.abs(dist) != 0){
                        if(ally(king,Piece.move(attacker.position,0,dist)))
                        {
                            blockers++;
                            pos = Piece.move(attacker.position,0,dist);
                        }
                        dist += -Math.signum(dist);
                    }
                    dist =king.position.charAt(0) - attacker.position.charAt(0);
                    dist += -Math.signum(dist);
                    while(Math.abs(dist) != 0){
                        String temp = Piece.move(attacker.position,0,dist);
                        if(ally(attacker,temp)){
                            no = false;
                        }
                        dist += -Math.signum(dist);
                    }
                    if(blockers == 1&&no) {
                        freezingPiece(pos,side,1);
                    }
                }
            }
        }
    }
    public void freezingPiece(String pos, boolean side, int axis){
        for(Piece p : pieces){
            if(p.pieceSide == side && Objects.equals(p.position, pos)){
                ArrayList<String> notAllowed = new ArrayList<String>();
                p.eatingMoves();
            for(String s:p.availableMoves){
                if(s.charAt(axis) != p.position.charAt(axis)){
                    notAllowed.add(s);
                }
            }
            p.availableMoves.removeAll(notAllowed);
            p.eatingMoves();
            }
        }
    }
    public void handlingMove(String clickedSquare) {
        if (this.getPiece(clickedSquare) == null) {
            if (this.selected != null) {
                boolean casL = (!Objects.equals(this.selected.castlingLift, clickedSquare) || !this.selected.canCastleLift) ||this.selected.id != 5;
                boolean casR = (!Objects.equals(this.selected.castlingRight, clickedSquare) || !this.selected.canCastleRight) ||this.selected.id != 5;
                if (!this.selected.moving.contains(clickedSquare) && !this.selected.eating.contains(clickedSquare)&&casR&&casL) {
                    this.selected.Unselect();
                } else {
                    boolean enemy = false;
                    if (this.selected.eating.contains(clickedSquare)) {
                        this.removePiece(clickedSquare);
                        enemy = true;
                    }
                    this.selected.Unselect();
                    gameMoves.add(this.selected.transport(clickedSquare,pieces, enemy));
                    this.turn = !this.turn;
                    if(isTimer)
                        game.controlTimer(turn);
                }
                this.selected = null;
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
                    boolean enemy = false;
                    if (this.selected.eating.contains(clickedSquare)) {
                        this.removePiece(clickedSquare);
                        enemy = true;
                    }
                    this.selected.Unselect();
                    gameMoves.add(this.selected.transport(clickedSquare,pieces,enemy));
                    this.turn = !this.turn;
                    if(isTimer)
                        game.controlTimer(turn);
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
        if(checkWinner(turn) == (Object) true || (game.blackClock.finishedCheck()&&isTimer)){
            actions.showResult(1);
            game.whiteClock.stop();
            game.blackClock.stop();
            game.gameResult = false;
            result = GameStart.selectedColor?1:-1;
            Match m1 = new Match(oppUser.getName(), -result, gameMoves);
            Match m2 = new Match(mainUser.getName(), result, gameMoves);
            addMatch(mainUser.getName(), m1);
            addMatch(oppUser.getName(), m2);
        }
        else if(checkWinner(turn) == (Object) false || (game.whiteClock.finishedCheck()&&isTimer)){
            actions.showResult(-1);
            game.whiteClock.stop();
            game.blackClock.stop();
            game.gameResult = false;
            result = GameStart.selectedColor?-1:1;
            Match m1 = new Match(oppUser.getName(), -result, gameMoves);
            Match m2 = new Match(mainUser.getName(), result, gameMoves);
            addMatch(mainUser.getName(), m1);
            addMatch(oppUser.getName(), m2);
        }
        else if(isDraw(turn)){
            actions.showResult(0);
            game.whiteClock.stop();
            game.blackClock.stop();
            gameStatus = false;

            Match m1 = new Match(oppUser.getName(), 0, gameMoves);
            Match m2 = new Match(mainUser.getName(), 0, gameMoves);
            addMatch(mainUser.getName(), m1);
            addMatch(oppUser.getName(), m2);
        }
    }
    public boolean isDraw(boolean turn){
        if(pieces.size() == 2)return true;
        if(possession(turn).size() == 0)return true;
        return false;
    }
    private Piece getPiece(String pos){
        for(Piece p : pieces){
            if(p.getPosition().equals(pos))
                return p;
        }
        return null;
    }
    private void checkPromotion(boolean turn){
        actions = new GameActions();
        this.actions.gl = this;
        if(getPromoted(turn) != null)
        {
            gameStatus = false;
            posProm = Objects.requireNonNull(getPromoted(turn)).position;
            turnProm = turn;
            removePiece(posProm);
            actions.promotionWindow(turn);
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
        ArrayList<String> temp;
        ArrayList<String> temp2;
        if (!turn) {
            kingChecked(false);
            temp = new ArrayList<String>(this.possession(false));
            temp2 = new ArrayList<>(this.possession(true));
            if(!temp2.contains(kingPosition(false)))temp.add(kingPosition(false));
            temp.removeAll(Objects.requireNonNull(this.getPiece(false, 5)).moving);
            if (temp.size() == 0 && Objects.requireNonNull(this.getPiece(false, 5)).movable().size() == 0) {
                return true;
            }
        } else {
            kingChecked(true);
            temp = new ArrayList<String>(this.possession(true));
            temp2 = new ArrayList<>(this.possession(false));
            if(!temp2.contains(kingPosition(true)))temp.add(kingPosition(true));
            temp.removeAll(Objects.requireNonNull(this.getPiece(true, 5)).moving);
            if (temp.size() == 0 && Objects.requireNonNull(this.getPiece(true, 5)).movable().size() == 0) {
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
        Objects.requireNonNull(getPiece(side, 5)).canCastleLift = false;
        Objects.requireNonNull(getPiece(side, 5)).canCastleRight = false;
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
            for(Piece p : pieces){
                if(p.pieceSide == side && p.id != 5){
                    p.moving.clear();
                    p.eating.clear();
                    p.pawnDiagonal.clear();
                }
            }
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
                    p.pawnDiagonal.retainAll(Collections.singletonList(pos));
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
                        if(pp.twoMoves != null&& pp.availableMoves.contains(pp.twoMoves)){
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
                p.canCastleLift = !temp.contains(p.castlingLift);if(p.castlingLift == null)p.canCastleLift=false;
                p.canCastleRight = !temp.contains(p.castlingRight);if(p.castlingRight == null)p.canCastleRight=false;
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

    }
}
