package MainPackage;

import javax.swing.*;
import java.awt.event.MouseEvent;


public class ChessBoard extends ChessBoardBASE{
    private GameActions actions = new GameActions();
    protected LoginPage l = new LoginPage();
    static protected GameStart gs = new GameStart();
    public JButton resign,draw, resign2,draw2;
    static int drawOffer;
    static int drawSide;
    static boolean offer;
    private User mainUser;
    //private User oppUser;
    public ChessBoard(User mainUser, User oppUser)
    {
        this.mainUser = mainUser;
        //this.oppUser = oppUser;
        //Passing users to chess board
        takeUsers(mainUser,oppUser);
        initialize();
    }

    @Override
    protected void setButtons() {

        //black buttons
            resign = createButton("Resign","re1","src/Mat/Buttons/drawBtn.png",635 *width/870,250 *width/870,"#FF006E");
            draw = createButton("Draw","dr1","src/Mat/Buttons/resignBtn.png",725 *width/870,250 *width/870,"#5F5F5F");//5F5F5F
        //white buttons
            resign2 = createButton("Resign","re2","src/Mat/Buttons/drawBtn.png",635 *width/870,340 *width/870,"#FF006E");
            draw2 = createButton("Draw","dr2","src/Mat/Buttons/resignBtn.png",725 *width/870,340 *width/870,"#5F5F5F");

            resign.addMouseListener(this);
            resign2.addMouseListener(this);
            draw.addMouseListener(this);
            draw2.addMouseListener(this);

            container.add(resign);
            container.add(draw);
            container.add(resign2);
            container.add(draw2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == resign){
            ChessClock.winner = 1;
            game.Clock("no");
        } else if(e.getSource() == resign2){
            ChessClock.winner = -1;
            game.Clock("no");
        }else if(e.getSource() == draw){
            drawOffer++;
            if(drawOffer == 2&& drawSide == 2){
                offer = true;
                game.Clock("no");
            }
            drawSide = 1;
        }else if(e.getSource() == draw2){
            drawOffer++;
            if(drawOffer == 2 && drawSide == 1) {
                offer = true;
                game.Clock("no");
            }
            drawSide = 2;
        } else  if(e.getSource()==backBtn){
            board.setVisible(false);
            GameStart.gameRunning = false;
            whiteClock.stop();
            blackClock.stop();
            System.out.println("yy");
            gs.initializeWithUser(GameStart.selectedColor?mainUser:oppUser);
            GameStart.selectedColor = true;
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
}