package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public abstract class ChessBoardBASE implements MouseListener {
    protected JFrame board;
    protected JLayeredPane base = new JLayeredPane();
    protected GameLauncher game;
    static public boolean gameResult;
    public static IconsAndColors ic = new IconsAndColors();
    public JButton button;
    public String current;
    public ChessClock whiteClock;
    public ChessClock blackClock;
    public Icon drag;
    public String previous;
    protected JPanel container;
    private JPanel ChessBoardPanel;

    private int xPosInfo = 1015;
    private JPanel blackDeadPanel;
    private JPanel whiteDeadPanel;
    int wP=0, wB=0,  wK=0, wQ=0,  wR=0, bP=0, bB=0, bK=0, bQ=0, bR=0;
    public User mainUser;
    public User oppUser;
    JLabel wPawnDead ,wBishopDead ,wKnightDead ,wQueenDead ,wRookDead, bPawnDead ,bBishopDead ,bKnightDead ,bQueenDead ,bRookDead ;

    public JButton backBtn = new JButton();
    protected   int width , heigth ;

    public static void setIconsAndColors(IconsAndColors newIconsAndColors){
        ic = newIconsAndColors;
    }
    public void initialize() {
        
        initialize_board();
        setButtons();
        draw_chessBoard();
        set_backBtn();
        //initializePieces();
        set_backgrounds();
        //show();
        setTimer();
    }
    public void show(){
        board.setVisible(true);
    }
    private void initialize_board(){
        width = ic.width; heigth = ic.height;
        board = new JFrame();
        container = new JPanel(null);
        container.setBounds(0,0,width,heigth);
        setPlayerInfo(mainUser.getName(),oppUser.getName(), 38); //should get the usernames
        setD(); // for dead panels

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
                    button.setBackground(ic.white);
                } else {
                    button.setBackground(ic.secondColor);
                }
                pos[i][j] = button;
                ChessBoardPanel.add(pos[i][j]);
            }
    }
    protected JButton createButton(String name,String ref, String filename, int x , int y,String color) {
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
        temp.setBounds(x,y,75 *width/870,35 *width/870);
        return temp;
    }

    protected abstract void setButtons();
    private void set_backgrounds() {

        ImageIcon temp1 = new ImageIcon("src/Mat/BackG/rgameFields.png");
        JLabel background = new JLabel();
        background.setIcon(ic.resizeWithRatio(temp1, 870));
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
                ChessBoard.drawOffer = 0;
                ChessBoard.drawSide = 0;
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
        if(piece == ic.black_pawn)return        ic.black_pawnN   ;
        else if(piece == ic.black_bishop)return ic.black_bishopN ;
        else if(piece == ic.black_king)return   ic.black_kingN   ;
        else if(piece == ic.black_knight)return ic.black_knightN ;
        else if(piece == ic.black_rook)return   ic.black_rookN   ;
        else if(piece == ic.black_queen)return  ic.black_queenN  ;
        else if(piece == ic.white_pawn)return   ic.white_pawnN   ;
        else if(piece == ic.white_bishop)return ic.white_bishopN ;
        else if(piece == ic.white_king)return   ic.white_kingN   ;
        else if(piece == ic.white_knight)return ic.white_knightN ;
        else if(piece == ic.white_rook)return   ic.white_rookN   ;
        else if(piece == ic.white_queen)return  ic.white_queenN  ;
        else if(piece == ic.black_pawnN)return  ic.black_pawn   ;
        else if(piece == ic.black_bishopN)return ic.black_bishop ;
        else if(piece == ic.black_kingN)return   ic.black_king   ;
        else if(piece == ic.black_knightN)return ic.black_knight ;
        else if(piece == ic.black_rookN)return   ic.black_rook   ;
        else if(piece == ic.black_queenN)return  ic.black_queen  ;
        else if(piece == ic.white_pawnN)return   ic.white_pawn   ;
        else if(piece == ic.white_bishopN)return ic.white_bishop ;
        else if(piece == ic.white_kingN)return   ic.white_king   ;
        else if(piece == ic.white_knightN)return ic.white_knight ;
        else if(piece == ic.white_rookN)return   ic.white_rook   ;
        else if(piece == ic.white_queenN)return  ic.white_queen  ;
        return null;
    }
    private Icon threatened(Icon piece){
        if(piece == ic.black_pawn)return        ic.black_pawnE   ;
        else if(piece == ic.black_bishop)return ic.black_bishopE ;
        else if(piece == ic.black_king)return   ic.black_kingE   ;
        else if(piece == ic.black_knight)return ic.black_knightE ;
        else if(piece == ic.black_rook)return   ic.black_rookE   ;
        else if(piece == ic.black_queen)return  ic.black_queenE  ;
        else if(piece == ic.white_pawn)return   ic.white_pawnE   ;
        else if(piece == ic.white_bishop)return ic.white_bishopE ;
        else if(piece == ic.white_king)return   ic.white_kingE   ;
        else if(piece == ic.white_knight)return ic.white_knightE ;
        else if(piece == ic.white_rook)return   ic.white_rookE   ;
        else if(piece == ic.white_queen)return  ic.white_queenE  ;
        else if(piece == ic.black_pawnE)return  ic.black_pawn   ;
        else if(piece == ic.black_bishopE)return ic.black_bishop ;
        else if(piece == ic.black_kingE)return   ic.black_king   ;
        else if(piece == ic.black_knightE)return ic.black_knight ;
        else if(piece == ic.black_rookE)return   ic.black_rook   ;
        else if(piece == ic.black_queenE)return  ic.black_queen  ;
        else if(piece == ic.white_pawnE)return   ic.white_pawn   ;
        else if(piece == ic.white_bishopE)return ic.white_bishop ;
        else if(piece == ic.white_kingE)return   ic.white_king   ;
        else if(piece == ic.white_knightE)return ic.white_knight ;
        else if(piece == ic.white_rookE)return   ic.white_rook   ;
        else if(piece == ic.white_queenE)return  ic.white_queen  ;
        return null;
    }
    public Object getSide(String pos){
        Icon piece = getButton(pos).getIcon();
        if(piece == ic.black_pawn)return  false;
        else if(piece == ic.black_bishop)return false;
        else if(piece == ic.black_king)return false;
        else if(piece == ic.black_knight)return false;
        else if(piece == ic.black_rook)return false;
        else if(piece == ic.black_queen)return false;
        else if(piece == ic.white_pawn)return  true;
        else if(piece == ic.white_bishop)return  true;
        else if(piece == ic.white_king)return  true;
        else if(piece == ic.white_knight)return  true;
        else if(piece == ic.white_rook)return  true;
        else if(piece == ic.white_queen)return  true;
        else if(piece == ic.black_pawnN)return  false;
        else if(piece == ic.black_bishopN)return false;
        else if(piece == ic.black_kingN)return false;
        else if(piece == ic.black_knightN)return false;
        else if(piece == ic.black_rookN)return false;
        else if(piece == ic.black_queenN)return false;
        else if(piece == ic.white_pawnN)return  true;
        else if(piece == ic.white_bishopN)return  true;
        else if(piece == ic.white_kingN)return  true;
        else if(piece == ic.white_knightN)return  true;
        else if(piece == ic.white_rookN)return  true;
        else if(piece == ic.white_queenN)return  true;
        else if(piece == ic.black_pawnE)return  false;
        else if(piece == ic.black_bishopE)return false;
        else if(piece == ic.black_kingE)return false;
        else if(piece == ic.black_knightE)return false;
        else if(piece == ic.black_rookE)return false;
        else if(piece == ic.black_queenE)return false;
        else if(piece == ic.white_pawnE)return  true;
        else if(piece == ic.white_bishopE)return  true;
        else if(piece == ic.white_kingE)return  true;
        else if(piece == ic.white_knightE)return  true;
        else if(piece == ic.white_rookE)return  true;
        else if(piece == ic.white_queenE)return  true;
        return null;
    }
    public pieceIcon getPieceInfo(String pos){
        pieceIcon res = new pieceIcon();
        Icon i = getButton(pos).getIcon();
        if(i == ic.black_pawn ){res.id = 6;res.side = false;}
        else if(i == ic.black_bishop ){res.id = 3;res.side = false;}
        else if(i == ic.black_king ){res.id = 5;res.side = false;}
        else if(i == ic.black_knight ){res.id = 2;res.side = false;}
        else if(i == ic.black_rook ){res.id = 1;res.side = false;}
        else if(i == ic.black_queen ){res.id = 4;res.side = false;}
        else if(i == ic.white_pawn ) {res.id = 6;res.side =true ;}
        else if(i == ic.white_bishop ) {res.id = 3;res.side =true ;}
        else if(i == ic.white_king ) {res.id = 5;res.side =true ;}
        else if(i == ic.white_knight ) {res.id = 2;res.side =true ;}
        else if(i == ic.white_rook ) {res.id = 1;res.side =true ;}
        else if(i == ic.white_queen ) {res.id = 4;res.side =true ;}
        else if(i == ic.black_pawnN ){res.id = 6;res.side = false;}
        else if(i == ic.black_bishopN ){res.id = 3;res.side = false;}
        else if(i == ic.black_kingN ){res.id = 5;res.side = false;}
        else if(i == ic.black_knightN ){res.id = 2;res.side = false;}
        else if(i == ic.black_rookN ){res.id = 1;res.side = false;}
        else if(i == ic.black_queenN ){res.id = 4;res.side = false;}
        else if(i == ic.white_pawnN ) {res.id = 6;res.side =true ;}
        else if(i == ic.white_bishopN ) {res.id = 3;res.side =true ;}
        else if(i == ic.white_kingN ) {res.id = 5;res.side =true ;}
        else if(i == ic.white_knightN ) {res.id = 2;res.side =true ;}
        else if(i == ic.white_rookN ) {res.id = 1;res.side =true ;}
        else if(i == ic.white_queenN ) {res.id = 4;res.side =true ;}
        else if(i == ic.black_pawnE ){res.id = 6;res.side = false;}
        else if(i == ic.black_bishopE ){res.id = 3;res.side = false;}
        else if(i == ic.black_kingE ){res.id = 5;res.side = false;}
        else if(i == ic.black_knightE ){res.id = 2;res.side = false;}
        else if(i == ic.black_rookE ){res.id = 1;res.side = false;}
        else if(i == ic.black_queenE ){res.id = 4;res.side = false;}
        else if(i == ic.white_pawnE ) {res.id = 6;res.side =true ;}
        else if(i == ic.white_bishopE ) {res.id = 3;res.side =true ;}
        else if(i == ic.white_kingE ) {res.id = 5;res.side =true ;}
        else if(i == ic.white_knightE ) {res.id = 2;res.side =true ;}
        else if(i == ic.white_rookE ) {res.id = 1;res.side =true ;}
        else if(i == ic.white_queenE ) {res.id = 4;res.side =true ;}
        return res;
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
        label.setForeground(ic.mainColor);
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
        label.setForeground(ic.white);
        base.add(label, 1);
    }

    public  void setD(){
        setDeadpanels();

        showDeadIcons();
    }
    public void setDead(int id , boolean side){

        if(side){
            switch (id){
                case 1:
                    wR++;
                    wRookDead.setText("X" + String.valueOf(wR));
                    break;
                case 2:
                    wK++;
                    wKnightDead.setText("X" + String.valueOf(wK));
                    break;
                case 3:
                    wB++;
                    wBishopDead.setText("X" + String.valueOf(wB));
                    break;
                case 4:
                    wQ++;
                    wQueenDead.setText("X" + String.valueOf(wQ));
                    break;
                case 6:
                    wP++;
                    wPawnDead.setText("X" + String.valueOf(wP));

                    break;
            }
        } else {
            switch (id){
                case 1:
                    bR++;
                    bRookDead.setText("X" + String.valueOf(bR));
                    break;
                case 2:
                    bK++;
                    bKnightDead.setText("X" + String.valueOf(bK));
                    break;
                case 3:
                    bB++;
                    bBishopDead.setText("X" + String.valueOf(bB));
                    break;
                case 4:
                    bQ++;
                    bQueenDead.setText("X" + String.valueOf(bQ));
                    break;
                case 6:
                    bP++;
                    bPawnDead.setText("X" + String.valueOf(bP));
                    break;
            }

        }

    }
    private void setDeadpanels(){
        blackDeadPanel = new JPanel();
        blackDeadPanel.setBounds(1012 *width/1440, 314 *width/1440, 354 * width/1440, 71 *width/1440);
        blackDeadPanel.setLayout(new GridLayout(1,5));
        blackDeadPanel.setOpaque(false);


        whiteDeadPanel = new JPanel();
        whiteDeadPanel.setBounds(1012 *width/1440, 644 *width/1440, 354 * width/1440, 71 *width/1440);
        whiteDeadPanel.setLayout(new GridLayout(1,5));
        whiteDeadPanel.setOpaque(false);


        base.add(blackDeadPanel, Integer.valueOf(1));
        base.add(whiteDeadPanel, Integer.valueOf(1));
    }

    public void showDeadIcons(){
//        //white
        wPawnDead = setDeadIcon(ic.white_pawnD,wP);
        wBishopDead = setDeadIcon(ic.white_bishopD,wB);
        wKnightDead = setDeadIcon(ic.white_knightD,wK);
        wQueenDead = setDeadIcon(ic.white_queenD,wQ);
        wRookDead = setDeadIcon(ic.white_rookD,wR);

        whiteDeadPanel.add(wPawnDead);
        whiteDeadPanel.add(wBishopDead);
        whiteDeadPanel.add(wKnightDead);
        whiteDeadPanel.add(wQueenDead);
        whiteDeadPanel.add(wRookDead);
        //black

        bPawnDead = setDeadIcon(ic.black_pawnD,bP);
        bBishopDead = setDeadIcon(ic.black_bishopD,bB);
        bKnightDead = setDeadIcon(ic.black_knightD,bK);
        bQueenDead =setDeadIcon(ic.black_queenD,bQ);
        bRookDead = setDeadIcon(ic.black_rookD,bR);


        blackDeadPanel.add(bPawnDead);
        blackDeadPanel.add(bBishopDead);
        blackDeadPanel.add(bKnightDead);
        blackDeadPanel.add(bQueenDead);
        blackDeadPanel.add(bRookDead);
    }
    private JLabel setDeadIcon(ImageIcon dIcon , int number){


        JLabel dLabel = new JLabel("X" + String.valueOf(number));

        dLabel.setIcon(ic.resizeWithRatio(dIcon));
        dLabel.setFont(new Font("Space Grotesk", Font.BOLD, 20 *width/1440));
        dLabel.setHorizontalTextPosition(JLabel.RIGHT);
        dLabel.setVerticalTextPosition(JLabel.BOTTOM);
        dLabel.setForeground(ic.white);


        dLabel.setOpaque(false);
        return dLabel;
    }

    private void setTimer() {
        long minutesInput;
        long secondsInput;

        //Checks if we got input from the user in the game menu
        if (GameStart.timerInput == null) {
            minutesInput = 0;
            secondsInput = 0;
        }
        else {
            char[] input = GameStart.timerInput.toCharArray();
            String minuteString = new StringBuilder().append(input[0]).append(input[1]).toString();
            minutesInput = Integer.parseInt(minuteString);
            String secondString = new StringBuilder().append(input[3]).append(input[4]).toString();
            secondsInput = Integer.parseInt(secondString);
        }

        //Creates 2 ChessClock objects for white & the other for black
        whiteClock = new ChessClock(minutesInput, secondsInput,1,this);
        JLabel whiteClockLabel = whiteClock.getLabel();
        whiteClockLabel.setFont(new Font("Space Grotesk", Font.BOLD, 80 *width/1440));
        whiteClockLabel.setBounds(1020 *width/1440, 750*width/1440, 334 *width/1440, 92 *width/1440);
        whiteClockLabel.setForeground(ic.white);

        blackClock = new ChessClock(minutesInput, secondsInput,-1,this);
        JLabel blackClockLabel = blackClock.getLabel();
        blackClockLabel.setFont(new Font("Space Grotesk", Font.BOLD, 80 *width/1440));
        blackClockLabel.setBounds(1020 *width/1440, 192 *width/1440, 334 *width/1440, 92 *width/1440);
        blackClockLabel.setForeground(ic.white);

        base.add(whiteClockLabel, Integer.valueOf(1));
        base.add(blackClockLabel, Integer.valueOf(1));
    }

    //Starts and Stops the timer of each clock
    public void controlTimer(boolean side) {
        if (side) {
            whiteClock.start();
            blackClock.stop();
        }
        else {
            whiteClock.stop();
            blackClock.start();
        }
    }
    protected void set_backBtn(){
        ImageIcon backImg = new ImageIcon("src/Mat/Buttons/backBtn.png");
        backBtn = new JButton(ic.resizeWithRatio(backImg));
        backBtn.setOpaque(false);
        backBtn.setFocusable(false);
        backBtn.setBorderPainted(false);
        backBtn.setBackground(ic.mainColor);

        backBtn.addMouseListener(this);
        backBtn.setBounds(25*width/1440,20*width/1024,65*width/1440,65*width/1024);

        base.add(backBtn, Integer.valueOf(1));

    }
    public String toPieceChar(int id){
             if(id == 1)return "R";
             else if(id == 2)return "k";
             else if(id == 3)return "B";
             else if(id == 4)return "Q";
             else if(id == 5)return "K";
        return "P";
    }



    // Functions to take users from chessboard
    public void takeUsers(User mainUser,User oppUser)
    {
        this.mainUser=mainUser;
        this.oppUser=oppUser;
    }


}

class pieceIcon{
    int id;
    boolean side;
}