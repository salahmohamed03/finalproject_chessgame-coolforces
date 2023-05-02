package MainPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChessBoard{
    private JFrame board;
    Promote P = new Promote();
    private GameLauncher game;
    public IconsAndColors icon = new IconsAndColors();
    public JButton button,resign,draw, resign2,draw2;
    public String current;
    public Icon drag;
    public String previous;
    private JPanel container;
    private JPanel ChessBoardPanel;
    private  int width = icon.width, heigth = icon.height;
    public ChessBoard(){initialize();}
    private void initialize() {
        //  icon = new IconsAndColors();
        initialize_board();
        setButtons();
        draw_chessBoard();
        //initializePieces();
        set_backgrounds();
        board.setVisible(true);// show the board

        P.promote(); //FOR testing
    }
    private void initialize_board(){
        board = new JFrame();
        container = new JPanel(null);
        board.add(container);
        board.setTitle("chess");
        board.setSize(width,heigth);
        board.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        board.setLocationRelativeTo(null);
        board.setResizable(false);
    }
    private void draw_chessBoard(){
        ChessBoardPanel = new JPanel(new GridLayout(8 , 8 , 0 , 0));
        container.add(ChessBoardPanel);
        ChessBoardPanel.setBounds(70 *width/870,70 *width/870,478 *width/870,478 *width/870);
        JButton [][]pos = new JButton[8][8];
        for(int i = 0 ;i < 8; i++)
            for(int j = 0 ; j < 8; j++)
            {
                initialize_button(i,j);
                action();
                if ((i + j) % 2 == 0) {
                    button.setBackground(icon.white);
                } else {
                    button.setBackground(icon.secondColor);
                }
                pos[i][j] = button;
                ChessBoardPanel.add(pos[i][j]);
            }
    }
    private void initializePieces(){
        get_button("A2").setIcon((icon.white_pawn));
        get_button("B2").setIcon((icon.white_pawn));
        get_button("C2").setIcon((icon.white_pawn));
        get_button("D2").setIcon((icon.white_pawn));
        get_button("E2").setIcon((icon.white_pawn));
        get_button("F2").setIcon((icon.white_pawn));
        get_button("G2").setIcon((icon.white_pawn));
        get_button("H2").setIcon((icon.white_pawn));

        get_button("A7").setIcon((icon.black_pawn));
        get_button("B7").setIcon((icon.black_pawn));
        get_button("C7").setIcon((icon.black_pawn));
        get_button("D7").setIcon((icon.black_pawn));
        get_button("E7").setIcon((icon.black_pawn));
        get_button("F7").setIcon((icon.black_pawn));
        get_button("G7").setIcon((icon.black_pawn));
        get_button("H7").setIcon((icon.black_pawn));

        get_button("A8").setIcon((icon.black_rook));
        get_button("B8").setIcon(icon.black_knight);
        get_button("C8").setIcon(icon.black_bishop);
        get_button("D8").setIcon((icon.black_queen));
        get_button("E8").setIcon((icon.black_king));
        get_button("F8").setIcon(icon.black_bishop);
        get_button("G8").setIcon(icon.black_knight);
        get_button("H8").setIcon((icon.black_rook));

        get_button("A1").setIcon((icon.white_rook));
        get_button("B1").setIcon(icon.white_knight);
        get_button("C1").setIcon(icon.white_bishop);
        get_button("D1").setIcon(icon.white_queen);
        get_button("E1").setIcon((icon.white_king));
        get_button("F1").setIcon(icon.white_bishop);
        get_button("G1").setIcon(icon.white_knight);
        get_button("H1").setIcon((icon.white_rook));
    }
    private JButton createButton(String name,String ref, String filename, int x , int y,String color) {
        JButton temp = new JButton(name);
        temp.setName(ref);
        temp.setFocusable(false);
        temp.setFont(new Font("Space Grotesk Light",Font.BOLD,20 *width/870));
        temp.setBackground(Color.red);
        temp.setForeground(Color.decode(color));
        ImageIcon temp1 = new ImageIcon(filename);//src/Mat/Buttons/resignBtn.png
        Image btnBG = temp1.getImage().getScaledInstance(75 *width/870,35 *width/870,Image.SCALE_SMOOTH);
        temp.setIcon(new ImageIcon (btnBG));
        temp.setHorizontalTextPosition(JButton.CENTER);
        temp.setVerticalTextPosition(JButton.CENTER);
        temp.setOpaque(false);
        temp.setBorder(BorderFactory.createEmptyBorder());
        temp.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hello world");
            }
        });
        temp.setBounds(x,y,75 *width/870,35 *width/870);
        return temp;
    }
    private void setButtons() {
        resign = createButton("Resign","re1","src/Mat/Buttons/drawBtn.png",635 *width/870,250 *width/870,"#FF006E");
        draw = createButton("Draw","dr1","src/Mat/Buttons/resignBtn.png",725 *width/870,250 *width/870,"#5F5F5F");//5F5F5F
        resign2 = createButton("Resign","re2","src/Mat/Buttons/drawBtn.png",635 *width/870,340 *width/870,"#FF006E");
        draw2 = createButton("Draw","dr2","src/Mat/Buttons/resignBtn.png",725 *width/870,340 *width/870,"#5F5F5F");
        container.add(resign);
        container.add(draw);
        container.add(resign2);
        container.add(draw2);
    }
    private void set_backgrounds() {

        ImageIcon temp1 = new ImageIcon("src/Mat/BackG/rgameFields.png");
        JLabel background = new JLabel();
        background.setIcon(icon.resizeWithRatio(temp1, 870));
        background.setBounds(0,0,864 *width/870,614 *width/870);
        container.add(background);
    }
    private void initialize_button(int i , int j){
        button = new JButton();
        char name = (char)(65+j);
        button.setName(name + Integer.toString(9 -(i+1)));
        button.setFocusable(false);
        button.setDefaultCapable(false);
        button.setFocusPainted(false);
    }
    public void setClock(GameLauncher gl){
        this.game = gl;
    }
    private void action() {
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton temp = (JButton)e.getSource();
                current = temp.getName();
                game.Clock(current);
            }
        });
    }
    private int get_position(String pos){
        int result;
        result =(8 - (pos.charAt(1) - '0'))*8;
        result+= (pos.charAt(0)-'A');
        return result;
    }
    private Icon getPiece(Icon piece){
        if(piece == icon.black_pawn)return        icon.black_pawnN   ;
        else if(piece == icon.black_bishop)return icon.black_bishopN ;
        else if(piece == icon.black_king)return   icon.black_kingN   ;
        else if(piece == icon.black_knight)return icon.black_knightN ;
        else if(piece == icon.black_rook)return   icon.black_rookN   ;
        else if(piece == icon.black_queen)return  icon.black_queenN  ;
        else if(piece == icon.white_pawn)return   icon.white_pawnN   ;
        else if(piece == icon.white_bishop)return icon.white_bishopN ;
        else if(piece == icon.white_king)return   icon.white_kingN   ;
        else if(piece == icon.white_knight)return icon.white_knightN ;
        else if(piece == icon.white_rook)return   icon.white_rookN   ;
        else if(piece == icon.white_queen)return  icon.white_queenN  ;
        else if(piece == icon.black_pawnN)return  icon.black_pawn   ;
        else if(piece == icon.black_bishopN)return icon.black_bishop ;
        else if(piece == icon.black_kingN)return   icon.black_king   ;
        else if(piece == icon.black_knightN)return icon.black_knight ;
        else if(piece == icon.black_rookN)return   icon.black_rook   ;
        else if(piece == icon.black_queenN)return  icon.black_queen  ;
        else if(piece == icon.white_pawnN)return   icon.white_pawn   ;
        else if(piece == icon.white_bishopN)return icon.white_bishop ;
        else if(piece == icon.white_kingN)return   icon.white_king   ;
        else if(piece == icon.white_knightN)return icon.white_knight ;
        else if(piece == icon.white_rookN)return   icon.white_rook   ;
        else if(piece == icon.white_queenN)return  icon.white_queen  ;
        return null;
    }
    public JButton get_button(String pos) {
        return (JButton) ChessBoardPanel.getComponent(get_position(pos));
    }
    public void move_piece(String p1 ,String p2) {
        Icon temp = get_button(p1).getIcon();
        if(temp == null)return;
        get_button(p1).setIcon(null);
        get_button(p2).setIcon(temp);
    }
    public void boardLight(String pos , boolean flag){
        boolean empty = get_button(pos).getIcon() == null;
        ImageIcon temp1 = new ImageIcon("src/Mat/Comp/game/avMove.png");
        Image av = temp1.getImage().getScaledInstance(30 *width/870,30 *width/870,Image.SCALE_SMOOTH);
        ImageIcon temp2 = new ImageIcon("src/Mat/Comp/game/noMove.png");
        Image no = temp2.getImage().getScaledInstance(30 *width/870,30 *width/870,Image.SCALE_SMOOTH);
        if(flag){
            if(empty)
                get_button(pos).setIcon(new ImageIcon(av));
            else
            {
                get_button(pos).setIcon(getPiece(get_button(pos).getIcon()));

            }
        }
        else {
            if(empty)
                get_button(pos).setIcon(null);
            else{
                get_button(pos).setIcon(getPiece(get_button(pos).getIcon()));
            }

        }
    }
    public boolean Empty(String pos){
        if(get_button(pos).getIcon() == null)return true;
        return false;
    }
    public static void main(String []args)
    {
        ChessBoard c = new ChessBoard();
    }
}