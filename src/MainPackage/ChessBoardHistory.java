package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessBoardHistory extends ChessBoardBASE implements MouseListener {

    public JTextArea movesText;
    private int movesCount = 0;
    public JButton nextMove;
    public JButton previousMove;
    public JButton backBtn;
    public ChessBoardHistory(){initialize(); setMovesText(); setButtons(); }



    public void setMovesText(){

        movesText = new JTextArea("",5,1);
        movesText.setEditable(false);
        movesText.setBounds(615 *width/870, 245 *width/870,205 *width/870,90 *width/870);
        movesText.setBackground(icon.white);
        movesText.setForeground(icon.mainColor);
        movesText.setFont(new Font("Space Grotesk", Font.BOLD, 15*width/1440));

//        JScrollPane scroll = new JScrollPane (movesText,
//                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        base.add(movesText, Integer.valueOf(1));
    }
    public void insertMove(String move){
        movesText.append(move);
        movesCount++;
        System.out.println(movesCount);
        if(movesCount == 8 ) movesText.append("\n");
    }
    private void set_backBtn(){
        ImageIcon backImg = new ImageIcon("src/Mat/Buttons/backBtn.png");
        backBtn = new JButton(icon.resizeWithRatio(backImg));
        backBtn.setOpaque(false);
        backBtn.setFocusable(false);
        backBtn.setBorderPainted(false);
        backBtn.setBackground(icon.mainColor);

        backBtn.addMouseListener(this);
        backBtn.setBounds(25*width/1440,20 *width/1440 ,65*width/1440,65 *width/1440);

        base.add(backBtn, Integer.valueOf(1));

    }

    public static void main(String []args)
    {
        ChessBoardHistory c = new ChessBoardHistory();

        c.setDead(6,true);
        c.setDead(6,true);
        c.setDead(2,false);
        c.setDead(6,true);
        c.setDead(3,false);
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.insertMove(" 5Qn6");
        c.show();
    }
    @Override
    protected void setButtons() {
        set_backBtn();
//        JPanel btns = new JPanel();
//        btns.setLayout(new GridLayout(1, 2 ,10 * width/1440,0));
//
//        btns.setBackground(icon.black);
        previousMove = createButton("Back","pM","src/Mat/Buttons/drawBtn.png",635 *width/870,340 *width/870,"#FF006E");
        nextMove = createButton("Next","nM","src/Mat/Buttons/resignBtn.png",725 *width/870,340 *width/870,"#FF006E");
//        movesPanel.add(nextMove);
//        movesPanel.add(previousMove);

        container.add(nextMove);
        container.add(previousMove);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
