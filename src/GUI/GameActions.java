package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import data.*;
import gamePlay.*;

public class GameActions implements MouseListener{

        JFrame frame;
        private JPanel promotePanel;
        private JLayeredPane base ;
        private JLabel promoteBackG;
        private JLabel header;
        public GameLauncher gl;
        private JButton queenP, bishopP, rookP, knightP;
        public int selected = 4;
        static IconsAndColors ic = new IconsAndColors();
        private JPanel btnsPromote;
        private  JLabel resultLabel;
        private final int width = ic.width;
    private final int height = ic.height;

        public void promotionWindow(boolean side){
            initializeWindow();
            setPromoteBackG();
            setPromotePanel();
            setpromoteHeader();
            setBtns(side);

            frame.add(base);
            frame.setVisible(true);
        }
        private void initializeWindow(){

            frame = new JFrame();
            frame.setUndecorated(true);
            frame.setSize(435 *width/1440, 256 *width/1440);
            frame.setBackground(new Color(1.0f,1.0f,1.0f,0));
            frame.setLocation(500 *width/1440, 397 *width/1440);
            frame.setAlwaysOnTop(true);

            base = new JLayeredPane();
            frame.add(base);

        }
        private void setPromotePanel(){
            promotePanel = new JPanel();
            promotePanel.setLayout(new BorderLayout());
            promotePanel.setBounds(0,0, 435 *width/1440, 256 *width/1440);
            promotePanel.setOpaque(false);

            base.add(promotePanel, Integer.valueOf(3));


        }
        private void setPromoteBackG(){
            ImageIcon pBackG = new ImageIcon(ic.colorPath+"Comp/game/promote/BackG.png");
            promoteBackG = new JLabel();
            promoteBackG.setIcon(ic.resizeWithRatio(pBackG));
            promoteBackG.setBounds(0,0, 435 *width/1440, 256 *width/1440);
            base.add(promoteBackG, Integer.valueOf(0));
            //System.out.println("ss");
        }
        private void setpromoteHeader(){
            header = new JLabel("Select a piece to promote to");
            header.setFont(new Font("Space Grotesk", Font.BOLD, 22 *width/1440));
            header.setHorizontalAlignment(JLabel.CENTER);
            header.setVerticalAlignment(JLabel.CENTER);
            header.setForeground(ic.mainColor);
            header.setPreferredSize(new Dimension(0, 100 *width/1440));
            promotePanel.add(header, BorderLayout.NORTH);

            //System.out.println("55");

        }
        private void setBtns(boolean side){
            btnsPromote = new JPanel();
            btnsPromote.setBorder(new EmptyBorder(20 *width/1440,20 *width/1440,20 *width/1440,20 *width/1440));
            btnsPromote.setLayout(new GridLayout(0,4, 10 *width/1440, 0));
            btnsPromote.setOpaque(false);
            //Should be if statement to decide wether they are black or white
            if(side)
                makeWhiteBtns();
            else
                makeBlackBtns();
            promotePanel.add(btnsPromote, BorderLayout.SOUTH);
            System.out.println("322");
        }
        private void makeBlackBtns(){
            bishopP = makePromoteBtn(ic.blackBishopP);
            queenP = makePromoteBtn(ic.blackQueenP);
            knightP = makePromoteBtn(ic.blackKnightP);
            rookP = makePromoteBtn(ic.blackRookP);
        }
        private void makeWhiteBtns(){
            bishopP = makePromoteBtn(ic.whiteBishopP);
            queenP = makePromoteBtn(ic.whiteQueenP);
            knightP = makePromoteBtn(ic.whiteKnightP);
            rookP = makePromoteBtn(ic.whiteRookP);
        }
        private JButton makePromoteBtn(ImageIcon piece){
            JButton button = new JButton(ic.resizeWithRatio(piece));
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.CENTER);
            button.setBackground(ic.black);
            button.setOpaque(false);
            button.setFocusable(false);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.addMouseListener(this);
            btnsPromote.add(button);
            return button;
        }
        void promoteDispose(){
            frame.setVisible(false);
        }

        //GAME RESULT

        public void showResult(int i){
            initializeResultFrame();
            checkResult(i);
            frame.setVisible(true);
        }
        private void initializeResultFrame(){
            frame = new JFrame();
            frame.setUndecorated(true);
            frame.setLocation(450 *width/1440, 284 *width/1440);
            frame.setSize(1002 *width/1440, 456 *width/1440);
            frame.setBackground(new Color(1.0f,1.0f,1.0f,0));
            frame.setAlwaysOnTop(true);
        }
        private void checkResult(int r) {
            //1 white win // 0 draw // -1 black wins
           ImageIcon wWin = new ImageIcon(ic.colorPath+"Comp/game/Wwin.png");
            ImageIcon bWin = new ImageIcon(ic.colorPath+"Comp/game/bWin.png");
            ImageIcon draw = new ImageIcon(ic.colorPath+"Comp/game/draw.png");
            resultLabel = new JLabel();
            resultLabel.addMouseListener(this);
            switch (r){
                case 1:
                    resultLabel.setIcon(ic.resizeWithRatio(wWin));
                    break;
                case 0:
                    resultLabel.setIcon(ic.resizeWithRatio(draw));
                    break;
                case -1:
                    resultLabel.setIcon(ic.resizeWithRatio(bWin));
                    break;
            }
            frame.add(resultLabel);
        }
        @Override
        public void mouseClicked(MouseEvent e) {


                if (e.getSource() == bishopP) {
                    System.out.println("Bishop");
                    selected = 3;
                    gl.gameStatus = true;
                    gl.promote(gl.posProm,gl.turnProm,selected);
                    String temp = gl.gameMoves.get(gl.gameMoves.size()-1);
                    temp+= ",B";
                    gl.gameMoves.remove(gl.gameMoves.size()-1);

                    gl.gameMoves.add(temp);
                    promoteDispose();
                    gl.Clock("no");
                } else if (e.getSource() == queenP) {
                    System.out.println("Queen");
                    selected = 4;
                    gl.gameStatus = true;
                    gl.promote(gl.posProm,gl.turnProm,selected);
                    String temp = gl.gameMoves.get(gl.gameMoves.size()-1);
                    temp+= ",Q";
                    gl.gameMoves.remove(gl.gameMoves.size()-1);
                    gl.gameMoves.add(temp);
                    promoteDispose();
                    gl.Clock("no");
                } else if (e.getSource() == knightP) {
                    System.out.println("Knight");
                    selected = 2;
                    gl.gameStatus = true;
                    gl.promote(gl.posProm,gl.turnProm,selected);
                    String temp = gl.gameMoves.get(gl.gameMoves.size()-1);
                    temp+= ",k";
                    gl.gameMoves.remove(gl.gameMoves.size()-1);
                    gl.gameMoves.add(temp);
                    promoteDispose();
                    gl.Clock("no");
                } else if (e.getSource() == rookP) {
                    System.out.println("Rook");
                    selected = 1;
                    gl.gameStatus = true;
                    gl.promote(gl.posProm,gl.turnProm,selected);
                    String temp = gl.gameMoves.get(gl.gameMoves.size()-1);
                    temp+= ",R";
                    gl.gameMoves.remove(gl.gameMoves.size()-1);
                    gl.gameMoves.add(temp);
                    promoteDispose();
                    gl.Clock("no");
                }
                if ((e.getSource() == resultLabel)) {
                    frame.dispose();
                    System.out.println("aaa");
                }
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
        public static void main(String[] args)
        {
            GameActions g = new GameActions();
            g.promotionWindow(false);
            g.promotionWindow(true);
        }
    }