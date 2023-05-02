package MainPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Promote implements MouseListener {
    private JFrame promotePanel;
    private JLabel promoteBackG;
    private JLabel header;
    public GameLauncher gl;
    private JButton queenP, bishopP, rookP, knightP;
    public int selected = 4;
    IconsAndColors ic = new IconsAndColors();
    private JPanel btnsPromote;
    private  int width = ic.width, height = ic.height;
    public  Promote() {
        promotePanel = new JFrame();
        promotePanel.setUndecorated(true);
        promotePanel.setLocation(500 *width/1440, 397 *width/1440);
        promotePanel.setLayout(new BorderLayout());
        promotePanel.setSize(435 *width/1440, 256 *width/1440);
        promotePanel.getContentPane().setBackground( Color.white );
        setpromoteHeader();
        setBtns();
    }
    private void setPromoteBackG(){
        ImageIcon pBackG = new ImageIcon("src/Mat/Comp/game/promote/BackG.png");
        promoteBackG = new JLabel();
        promoteBackG.setIcon(ic.resizeWithRatio(pBackG));
        promoteBackG.setBounds(266 *width/1440,390 *width/1440, 498 *width/1440, 243 *width/1440);
        //base.add(promoteBackG, Integer.valueOf(2));
    }
    public void promotionWindow(){
        promotePanel.setVisible(true);
    }
    private void setpromoteHeader(){
        header = new JLabel("Select a piece to promote to");
        header.setFont(new Font("Space Grotesk", Font.BOLD, 22 *width/1440));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);
        header.setForeground(ic.mainColor);
        header.setPreferredSize(new Dimension(0, 45 *width/1440));
        promotePanel.add(header, BorderLayout.NORTH);

    }
    private void setBtns(){
        btnsPromote = new JPanel();
        btnsPromote.setBorder(new EmptyBorder(20 *width/1440,20 *width/1440,20 *width/1440,20 *width/1440));
        btnsPromote.setLayout(new GridLayout(0,4, 10 *width/1440, 0));
        btnsPromote.setOpaque(false);
        //Should be if statement to decide wether they are black or white
        bishopP = makePromoteBtn(ic.blackBishopP);
        queenP = makePromoteBtn(ic.blackQueenP);
        knightP = makePromoteBtn(ic.blackKnightP);
        rookP = makePromoteBtn(ic.blackRookP);

        promotePanel.add(btnsPromote, BorderLayout.SOUTH);
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
        promotePanel.setVisible(false);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource()==bishopP){
            System.out.println("Bishop");
            selected = 3;
            promoteDispose();
        } else if(e.getSource()==queenP){
            System.out.println("Queen");
            selected = 4;
            promoteDispose();
        }
        else if(e.getSource()==knightP){
            System.out.println("Knight");
            selected = 2;
            promoteDispose();
        }
        else if(e.getSource()==rookP){
            System.out.println("Rook");
            selected = 1;
            promoteDispose();
        }

        gl.promote(gl.posProm,gl.turnProm,selected);
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
