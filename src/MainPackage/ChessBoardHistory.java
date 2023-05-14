package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.Color.decode;

public class ChessBoardHistory extends ChessBoardBASE {

    public JTextArea movesText;
    private String[] moves;
    private int movesCount = 0;

    public ChessBoardHistory(){
        initialize();

        setMovesText();

        show();
    }



    public void setMovesText(){

        movesText = new JTextArea(moves[0],5,1);
        movesText.setEditable(false);
        movesText.setBounds(615 *width/870, 245 *width/870,205 *width/870,90 *width/870);
        movesText.setBackground(ic.white);
        movesText.setForeground(ic.mainColor);
        movesText.setFont(new Font("Space Grotesk", Font.BOLD, 15*width/1440));

        movesText.setText(moves[0]+" ");

//        JScrollPane scroll = new JScrollPane (movesText,
//                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        base.add(movesText, Integer.valueOf(1));
    }
    public  void readMoves(String[] movesArray){
        moves=movesArray;
    }

    private void writeMoves(){
            movesText.selectAll();
            movesText.replaceSelection(moves[0]+" ");
        for (int i = 1 ; i<= movesCount; i++) {
            movesText.append(moves[i] + " ");
//            movesCount++;
            System.out.println(i);
            if (i == 8) movesText.append("\n");
        }

    }


    public static void main(String []args)
    {
//        ChessBoardHistory c = new ChessBoardHistory();
//
//        c.setDead(6,true);
//        c.setDead(6,true);
//        c.setDead(2,false);
//        c.setDead(6,true);
//        c.setDead(3,false);
//        String[] movey=new String[] {"jbfbs", "jbfsb", "665fn"};
//
//        c.readMoves(movey);
//        c.show();
    }
    @Override
    protected void setButtons() {

//        JPanel btns = new JPanel();
//        btns.setLayout(new GridLayout(1, 2 ,10 * width/1440,0));
//
//        btns.setBackground(ic.black);
        previousMove = createButton("Prev","pM","src/Mat/Buttons/drawBtn.png",635 *width/870,340 *width/870,"#FF006E");
        nextMove = createButton("Next","nM","src/Mat/Buttons/resignBtn.png",725 *width/870,340 *width/870,"#FF006E");
        nextMove.setForeground(Color.BLUE);

        nextMove.addMouseListener(this);
        previousMove.addMouseListener(this);
//        movesPanel.add(nextMove);
//        movesPanel.add(previousMove);

        base.add(nextMove, Integer.valueOf(3));
        container.add(previousMove);
    }

    @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == nextMove){


                System.out.println("it's me MAARIOOOO");
                movesCount++;

            }
        if(e.getSource() == previousMove){
            System.out.println("it's me MAARIOOOO");
            movesCount--;

        }

        if (movesCount >= moves.length-1){
            nextMove.setForeground(Color.gray);
            nextMove.setEnabled(false);
            movesCount = 5;
        }else if (movesCount<moves.length-1){
            nextMove.setEnabled(true);
            nextMove.setForeground(Color.decode("#FF006E"));
        }
        if(movesCount<=0){
            previousMove.setEnabled(false);
            previousMove.setForeground(Color.gray);
            movesCount=0;
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
