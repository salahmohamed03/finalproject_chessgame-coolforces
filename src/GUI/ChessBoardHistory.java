package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import data.*;

public class ChessBoardHistory extends ChessBoardBASE {

    public JTextArea movesText;
    public IconsAndColors icon = new IconsAndColors();
    private String[] moves;
    dataHandling d;
    private int movesCount = 0;
    JButton nextMove;
    JButton previousMove;
    private boolean turn = true;

    public ChessBoardHistory(String[] moves,User mainUser,String oppUserName,String side)
    {
        isgame = false;
        this.mainUser = mainUser;
        if (side.equals("White"))
        {
            WhitePName = mainUser.getName();
            BlackPName=oppUserName;
        } else {
            BlackPName = mainUser.getName();
            WhitePName=oppUserName;
        }
        checkingHistory=true;
        readMoves(moves);
        initialize();
        setMovesText();
        show();
        initializePieces();
    }

    private void initializePieces() {
        this.getButton("A2").setIcon(this.icon.white_pawn);
        this.getButton("B2").setIcon(this.icon.white_pawn);
        this.getButton("C2").setIcon(this.icon.white_pawn);
        this.getButton("D2").setIcon(this.icon.white_pawn);
        this.getButton("E2").setIcon(this.icon.white_pawn);
        this.getButton("F2").setIcon(this.icon.white_pawn);
        this.getButton("G2").setIcon(this.icon.white_pawn);
        this.getButton("H2").setIcon(this.icon.white_pawn);
        this.getButton("A7").setIcon(this.icon.black_pawn);
        this.getButton("B7").setIcon(this.icon.black_pawn);
        this.getButton("C7").setIcon(this.icon.black_pawn);
        this.getButton("D7").setIcon(this.icon.black_pawn);
        this.getButton("E7").setIcon(this.icon.black_pawn);
        this.getButton("F7").setIcon(this.icon.black_pawn);
        this.getButton("G7").setIcon(this.icon.black_pawn);
        this.getButton("H7").setIcon(this.icon.black_pawn);
        this.getButton("A8").setIcon(this.icon.black_rook);
        this.getButton("B8").setIcon(this.icon.black_knight);
        this.getButton("C8").setIcon(this.icon.black_bishop);
        this.getButton("D8").setIcon(this.icon.black_queen);
        this.getButton("E8").setIcon(this.icon.black_king);
        this.getButton("F8").setIcon(this.icon.black_bishop);
        this.getButton("G8").setIcon(this.icon.black_knight);
        this.getButton("H8").setIcon(this.icon.black_rook);
        this.getButton("A1").setIcon(this.icon.white_rook);
        this.getButton("B1").setIcon(this.icon.white_knight);
        this.getButton("C1").setIcon(this.icon.white_bishop);
        this.getButton("D1").setIcon(this.icon.white_queen);
        this.getButton("E1").setIcon(this.icon.white_king);
        this.getButton("F1").setIcon(this.icon.white_bishop);
        this.getButton("G1").setIcon(this.icon.white_knight);
        this.getButton("H1").setIcon(this.icon.white_rook);
    }

    public void setMovesText(){

        movesText = new JTextArea(moves[0],5,1);
        movesText.setEditable(false);
        movesText.setBounds(615 *width/870, 245 *width/870,205 *width/870,90 *width/870);
        movesText.setBackground(ic.white);
        movesText.setForeground(ic.mainColor);
        movesText.setFont(new Font("Space Grotesk", Font.BOLD, 15*width/1440));

        movesText.setText(" ");

        base.add(movesText, Integer.valueOf(1));
    }
    public  void readMoves(String[] movesArray)
    {
        moves=Arrays.copyOf(movesArray,movesArray.length);
    }

    private void writeMoves(){
        movesText.selectAll();
        movesText.replaceSelection(" ");
        for (int i = 0 ; i < movesCount; i++) {
            movesText.append(moves[i] + " ");
            if (i == 7) movesText.append("\n");
        }

    }
    @Override
    protected void setButtons() {


        previousMove = createButton("Prev","pM","src/Mat/Buttons/drawBtn.png",635 *width/870,340 *width/870,"#FF006E");
        nextMove = createButton("Next","nM","src/Mat/Buttons/resignBtn.png",725 *width/870,340 *width/870,"#FF006E");

        nextMove.addMouseListener(this);
        previousMove.addMouseListener(this);

        base.add(nextMove, Integer.valueOf(3));
        container.add(previousMove);
    }
    public void reviewMove(String move){
        ArrayList<String> moveInfo = new ArrayList<>(List.of(move.split(",")));
        if(moveInfo.size() == 2){ //normal move
            move_piece(moveInfo.get(0),moveInfo.get(1));
        }

        if(moveInfo.get(1).length() == 4){ // eating move
            ArrayList<String> eat =new ArrayList<>(List.of(moveInfo.get(1).split("x")));
            move_piece(moveInfo.get(0),eat.get(1));
            setDead(getPieceId(moveInfo.get(2)),!turn);
        }
        else if(moveInfo.size() == 3){ // normal move promotion
            move_piece(moveInfo.get(0),moveInfo.get(1));
            getButton(moveInfo.get(1)).setIcon(PieceIcon(moveInfo.get(2),turn));
        }

        if(moveInfo.size() == 4){
            if(moveInfo.get(3).length() == 2){ // castling
                move_piece(moveInfo.get(0),moveInfo.get(1));
                move_piece(moveInfo.get(2),moveInfo.get(3));
            }
            else { // promotion eating
                ArrayList<String> eat =new ArrayList<>(List.of(moveInfo.get(1).split("x")));
                getButton(eat.get(1)).setIcon(PieceIcon(moveInfo.get(3),turn));
            }
        }
        turn = !turn;
    }
    public void backTrack(String move){

        ArrayList<String> moveInfo = new ArrayList<>(List.of(move.split(",")));
        if(moveInfo.size() == 2){ //normal move
            move_piece(moveInfo.get(1),moveInfo.get(0));
        }

        if(moveInfo.get(1).length() == 4){ // eating move
            ArrayList<String> eat =new ArrayList<>(List.of(moveInfo.get(1).split("x")));
            move_piece(eat.get(1),moveInfo.get(0));
            getButton(eat.get(1)).setIcon(PieceIcon(moveInfo.get(2),turn));
            setAlive(getPieceId(moveInfo.get(2)),turn);
        }
        else if(moveInfo.size() == 3){ // normal move promotion
            move_piece(moveInfo.get(1),moveInfo.get(0));
            getButton(moveInfo.get(0)).setIcon(PieceIcon("P",!turn));
        }

        if(moveInfo.size() == 4){
            if(moveInfo.get(3).length() == 2){ // castling
                move_piece(moveInfo.get(1),moveInfo.get(0));
                move_piece(moveInfo.get(3),moveInfo.get(2));
            }
            else { // promotion eating
                ArrayList<String> eat =new ArrayList<>(List.of(moveInfo.get(1).split("x")));
                getButton(moveInfo.get(0)).setIcon(PieceIcon("P",!turn));
                // getButton(moveInfo.get(0)).setIcon(PieceIcon(moveInfo.get(2), turn));
            }
        }
        turn = !turn;



    }
    public int getPieceId(String name){
        return switch (name) {
            case "R" -> 1;
            case "k" -> 2;
            case "B" -> 3;
            case "Q" -> 4;
            case "K" -> 5;
            default -> 6;
        };
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == nextMove){
            if(movesCount == moves.length)return;
            reviewMove(moves[movesCount]);
            movesCount++;
        }
        if(e.getSource() == previousMove){
            if(movesCount == 0)return;
            movesCount--;
            backTrack(moves[movesCount]);

        }
        if(e.getSource()== backBtn){
            board.setVisible(false);
        }

        if (movesCount >= moves.length){
            nextMove.setForeground(Color.gray);
            nextMove.setEnabled(false);
            //movesCount = 5;
        }else {
            nextMove.setEnabled(true);
            nextMove.setForeground(Color.decode("#FF006E"));
        }
        if(movesCount<=0){
            previousMove.setEnabled(false);
            previousMove.setForeground(Color.gray);
            // movesCount=0;
        }else {
            previousMove.setEnabled(true);
            previousMove.setForeground(Color.decode("#FF006E"));
        }
        writeMoves();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}