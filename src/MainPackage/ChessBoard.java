package MainPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChessBoard{
    private JFrame board;
    private JLayeredPane base = new JLayeredPane();
    private GameLauncher game;
    public IconsAndColors icon = new IconsAndColors();
    public JButton button,resign,draw, resign2,draw2;
    public String current;
    public Icon drag;
    public String previous;
    private JPanel container;
    private JPanel ChessBoardPanel;

    private int xPosInfo = 1015;
    private JPanel blackDeadPanel;
    private JPanel whiteDeadPanel;
    private  int width = icon.width, heigth = icon.height;
    public ChessBoard(){initialize();}
    private void initialize() {
        icon = new IconsAndColors();
        initialize_board();
        setButtons();
        draw_chessBoard();
        //initializePieces();
        set_backgrounds();

        setTimer();
    }
    public void show(){
        board.setVisible(true);
    }
    private void initialize_board(){
        board = new JFrame();
        container = new JPanel(null);
        container.setBounds(0,0,width,heigth);
        setPlayerInfo("Talal","Rahaal", 38); //should get the usernames
        base.add(container, Integer.valueOf(0));
        board.setTitle("chess");
        board.setSize(width,heigth);
        board.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        board.setLocationRelativeTo(null);
        board.setResizable(false);
        board.add(base);
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
        getButton("A2").setIcon((icon.white_pawn));
        getButton("B2").setIcon((icon.white_pawn));
        getButton("C2").setIcon((icon.white_pawn));
        getButton("D2").setIcon((icon.white_pawn));
        getButton("E2").setIcon((icon.white_pawn));
        getButton("F2").setIcon((icon.white_pawn));
        getButton("G2").setIcon((icon.white_pawn));
        getButton("H2").setIcon((icon.white_pawn));

        getButton("A7").setIcon((icon.black_pawn));
        getButton("B7").setIcon((icon.black_pawn));
        getButton("C7").setIcon((icon.black_pawn));
        getButton("D7").setIcon((icon.black_pawn));
        getButton("E7").setIcon((icon.black_pawn));
        getButton("F7").setIcon((icon.black_pawn));
        getButton("G7").setIcon((icon.black_pawn));
        getButton("H7").setIcon((icon.black_pawn));

        getButton("A8").setIcon((icon.black_rook));
        getButton("B8").setIcon(icon.black_knight);
        getButton("C8").setIcon(icon.black_bishop);
        getButton("D8").setIcon((icon.black_queen));
        getButton("E8").setIcon((icon.black_king));
        getButton("F8").setIcon(icon.black_bishop);
        getButton("G8").setIcon(icon.black_knight);
        getButton("H8").setIcon((icon.black_rook));

        getButton("A1").setIcon((icon.white_rook));
        getButton("B1").setIcon(icon.white_knight);
        getButton("C1").setIcon(icon.white_bishop);
        getButton("D1").setIcon(icon.white_queen);
        getButton("E1").setIcon((icon.white_king));
        getButton("F1").setIcon(icon.white_bishop);
        getButton("G1").setIcon(icon.white_knight);
        getButton("H1").setIcon((icon.white_rook));
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
    private int getPosition(String pos){
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
    private Icon threatened(Icon piece){
        if(piece == icon.black_pawn)return        icon.black_pawnE   ;
        else if(piece == icon.black_bishop)return icon.black_bishopE ;
        else if(piece == icon.black_king)return   icon.black_kingE   ;
        else if(piece == icon.black_knight)return icon.black_knightE ;
        else if(piece == icon.black_rook)return   icon.black_rookE   ;
        else if(piece == icon.black_queen)return  icon.black_queenE  ;
        else if(piece == icon.white_pawn)return   icon.white_pawnE   ;
        else if(piece == icon.white_bishop)return icon.white_bishopE ;
        else if(piece == icon.white_king)return   icon.white_kingE   ;
        else if(piece == icon.white_knight)return icon.white_knightE ;
        else if(piece == icon.white_rook)return   icon.white_rookE   ;
        else if(piece == icon.white_queen)return  icon.white_queenE  ;
        else if(piece == icon.black_pawnE)return  icon.black_pawn   ;
        else if(piece == icon.black_bishopE)return icon.black_bishop ;
        else if(piece == icon.black_kingE)return   icon.black_king   ;
        else if(piece == icon.black_knightE)return icon.black_knight ;
        else if(piece == icon.black_rookE)return   icon.black_rook   ;
        else if(piece == icon.black_queenE)return  icon.black_queen  ;
        else if(piece == icon.white_pawnE)return   icon.white_pawn   ;
        else if(piece == icon.white_bishopE)return icon.white_bishop ;
        else if(piece == icon.white_kingE)return   icon.white_king   ;
        else if(piece == icon.white_knightE)return icon.white_knight ;
        else if(piece == icon.white_rookE)return   icon.white_rook   ;
        else if(piece == icon.white_queenE)return  icon.white_queen  ;
        return null;
    }
    public Object getSide(String pos){
        Icon piece = getButton(pos).getIcon();
        if(piece == icon.black_pawn)return  false;
        else if(piece == icon.black_bishop)return false;
        else if(piece == icon.black_king)return false;
        else if(piece == icon.black_knight)return false;
        else if(piece == icon.black_rook)return false;
        else if(piece == icon.black_queen)return false;
        else if(piece == icon.white_pawn)return  true;
        else if(piece == icon.white_bishop)return  true;
        else if(piece == icon.white_king)return  true;
        else if(piece == icon.white_knight)return  true;
        else if(piece == icon.white_rook)return  true;
        else if(piece == icon.white_queen)return  true;
        else if(piece == icon.black_pawnN)return  false;
        else if(piece == icon.black_bishopN)return false;
        else if(piece == icon.black_kingN)return false;
        else if(piece == icon.black_knightN)return false;
        else if(piece == icon.black_rookN)return false;
        else if(piece == icon.black_queenN)return false;
        else if(piece == icon.white_pawnN)return  true;
        else if(piece == icon.white_bishopN)return  true;
        else if(piece == icon.white_kingN)return  true;
        else if(piece == icon.white_knightN)return  true;
        else if(piece == icon.white_rookN)return  true;
        else if(piece == icon.white_queenN)return  true;
        else if(piece == icon.black_pawnE)return  false;
        else if(piece == icon.black_bishopE)return false;
        else if(piece == icon.black_kingE)return false;
        else if(piece == icon.black_knightE)return false;
        else if(piece == icon.black_rookE)return false;
        else if(piece == icon.black_queenE)return false;
        else if(piece == icon.white_pawnE)return  true;
        else if(piece == icon.white_bishopE)return  true;
        else if(piece == icon.white_kingE)return  true;
        else if(piece == icon.white_knightE)return  true;
        else if(piece == icon.white_rookE)return  true;
        else if(piece == icon.white_queenE)return  true;
        return null;
    }
    public Object isAlly(String p1, String p2){
        if(getSide(p2) == null)return null;
        return (boolean) (getSide(p1) == getSide(p2));
    }
    public JButton getButton(String pos) {
        return (JButton) ChessBoardPanel.getComponent(getPosition(pos));
    }
    public void move_piece(String p1 ,String p2) {
        Icon temp = getButton(p1).getIcon();
        if(temp == null)return;
        getButton(p1).setIcon(null);
        getButton(p2).setIcon(temp);
    }
    public void boardLight(String pos , boolean flag){
        boolean empty = getButton(pos).getIcon() == null;
        ImageIcon temp1 = new ImageIcon("src/Mat/Comp/game/avMove.png");
        Image av = temp1.getImage().getScaledInstance(30 *width/870,30 *width/870,Image.SCALE_SMOOTH);
        ImageIcon temp2 = new ImageIcon("src/Mat/Comp/game/noMove.png");
        Image no = temp2.getImage().getScaledInstance(30 *width/870,30 *width/870,Image.SCALE_SMOOTH);
        if(flag){
            if(empty)
                getButton(pos).setIcon(new ImageIcon(av));
            else
            {
                getButton(pos).setIcon(getPiece(getButton(pos).getIcon()));

            }
        }
        else {
            if(empty)
                getButton(pos).setIcon(null);
            else{
                getButton(pos).setIcon(getPiece(getButton(pos).getIcon()));
            }

        }
    }
    public void eatingLight(String pos){
        getButton(pos).setIcon(threatened(getButton(pos).getIcon()));
    }
    public boolean Empty(String pos){
        if(getButton(pos).getIcon() == null)return true;
        return false;
    }
    public void setPlayerInfo(String w, String b, int size /* double wRate , double bRate*/){
        createNameLabel(b, size , false );
        createWinRateLabel(50,20,false);
        createNameLabel(w,size , true);
        createWinRateLabel(36.55,20,true);
    }
    private  void createNameLabel(String text, int s , boolean side ){
        JLabel label = new JLabel(text);
        int yPosition ;
        label.setFont(new Font("Space Grotesk", Font.BOLD, s *width/1440));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        if(side){ yPosition = 883;}
        else {yPosition = 58;}
        label.setBounds(xPosInfo *width/1440, yPosition *width/1440, 349 *width/1440 , 88 *width/1440);
        label.setForeground(icon.mainColor);
        base.add(label, 1);
    }
    private  void createWinRateLabel(double rate, int s , boolean side){
        JLabel label = new JLabel("Win Rate: "+rate + "%");
        int yPosition ;
        label.setFont(new Font("Space Grotesk", Font.BOLD, s *width/1440));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        int gap = 43;
        if(side){ yPosition = 883 -gap ;}
        else {yPosition = 58 +gap;}
        label.setBounds(xPosInfo *width/1440, yPosition *width/1440, 349 *width/1440 , 88 *width/1440);
        label.setForeground(icon.white);
        base.add(label, 1);
    }

    private void setDeadPanels(){
        blackDeadPanel = new JPanel();
        blackDeadPanel.setBounds(1012 *width/1440, 314 *width/1440, 354 * width/1440, 71 *width/1440);
        blackDeadPanel.setLayout(new GridLayout(1,5));
        blackDeadPanel.setOpaque(false);


        whiteDeadPanel = new JPanel();
        whiteDeadPanel.setBounds(1012 *width/1440, 644 *width/1440, 354 * width/1440, 71 *width/1440);
        whiteDeadPanel.setLayout(new GridLayout(1,5));
        whiteDeadPanel.setOpaque(false);


        showDeadIcons(1,2,0,4,5,5,1,2,3,4);

        base.add(blackDeadPanel, Integer.valueOf(1));
        base.add(whiteDeadPanel, Integer.valueOf(1));

    }
    private void showDeadIcons(int wP, int wB, int wK, int wQ, int wR, int bP, int bB, int bK, int bQ, int bR){
//        //white
        whiteDeadPanel.add(setDeadIcon(icon.white_pawnD,wP));
        whiteDeadPanel.add(setDeadIcon(icon.white_bishopD,wB));
        whiteDeadPanel.add(setDeadIcon(icon.white_knightD,wK));
        whiteDeadPanel.add(setDeadIcon(icon.white_queenD,wQ));
        whiteDeadPanel.add(setDeadIcon(icon.white_rookD,wR));
        //black
        blackDeadPanel.add(setDeadIcon(icon.black_pawnD,bP));
        blackDeadPanel.add(setDeadIcon(icon.black_bishopD,bB));
        blackDeadPanel.add(setDeadIcon(icon.black_knightD,bK));
        blackDeadPanel.add(setDeadIcon(icon.black_queenD,bQ));
        blackDeadPanel.add(setDeadIcon(icon.black_rookD,bR));
    }
    private JLabel setDeadIcon(ImageIcon dIcon , int number){

        if (number == 0) return  new JLabel();

        JLabel dLabel = new JLabel("X" + String.valueOf(number));
        dLabel.setIcon(icon.resizeWithRatio(dIcon));
        dLabel.setFont(new Font("Space Grotesk", Font.BOLD, 20 *width/1440));
        dLabel.setHorizontalTextPosition(JLabel.RIGHT);
        dLabel.setVerticalTextPosition(JLabel.BOTTOM);
        dLabel.setForeground(icon.white);

        dLabel.setOpaque(false);
        return dLabel;
    }
    // Timer set
    private void setTimer(){
        //create 2 ChessClock Objects here one for white & the other for white
        //set Font .setFont(new Font("Space Grotesk", Font.BOLD, 70 *width/1440));
        // for the black set Bounds (1031 *width/1440, 191 *width/1440, 334 *width/1440, 92 *width/1440)
        // for the black set Bounds (1031 *width/1440, 753 *width/1440, 334 *width/1440, 92 *width/1440)
        // in the end base.add(nameOfTimer, Integer.valueOf(1)) for the white and the black // to show on board
    }

    // a method fot time start & stop take the arguments (boolean side , boolean start ) //true to start and false to stop




    // a method to reset take the argument (boolean side)



    // a method for finished check takes the argument (){
    // if white chessCloak finished
    //        return the boolean true
    // if the black
    //      return the boolean false
    // }
    public static void main(String []args)
    {
        ChessBoard c = new ChessBoard();
        c.setDeadPanels();
        c.show();
    }
}