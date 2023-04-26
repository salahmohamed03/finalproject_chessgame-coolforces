package MainPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChessBoard extends JFrame{
    public JFrame board;
    private PieceIcons icon;
    public JButton button,resign,draw, resign2,draw2;
    public String current;
    Icon drag;
    public String previous;
    private boolean turn;
    private JPanel container;
    private JPanel ChessBoardPanel;
    public ChessBoard(){initialize();}
    public void initialize() {
        icon = new PieceIcons();
        turn = false;
        initialize_board();
        setButtons();
        draw_chessBoard();
        initializePieces();
        //move_piece("E4","A8");
        set_backgrounds();
        board.setVisible(true);// show the board
    }
    public void initialize_board(){
        board = new JFrame();
        container = new JPanel(null);
        board.add(container);
        board.setTitle("chess");
        board.setSize(870,650);
        board.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        board.setLocationRelativeTo(null);
        board.setResizable(false);
    }
    public void draw_chessBoard(){
        ChessBoardPanel = new JPanel(new GridLayout(8 , 8 , 0 , 0));
        container.add(ChessBoardPanel);
        ChessBoardPanel.setBounds(70 ,70,478,478);
        JButton [][]pos = new JButton[8][8];
        for(int i = 0 ;i < 8; i++)
            for(int j = 0 ; j < 8; j++)
            {
                initialize_button(i,j);
                action();
                if ((i + j) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(new Color(160, 0, 64));
                }
                pos[i][j] = button;
                ChessBoardPanel.add(pos[i][j]);
            }
    }
    public void initializePieces(){
        get_button("A2").setIcon(new ImageIcon(icon.white_pawn));
        get_button("B2").setIcon(new ImageIcon(icon.white_pawn));
        get_button("C2").setIcon(new ImageIcon(icon.white_pawn));
        get_button("D2").setIcon(new ImageIcon(icon.white_pawn));
        get_button("E2").setIcon(new ImageIcon(icon.white_pawn));
        get_button("F2").setIcon(new ImageIcon(icon.white_pawn));
        get_button("G2").setIcon(new ImageIcon(icon.white_pawn));
        get_button("H2").setIcon(new ImageIcon(icon.white_pawn));
    }
    public JButton createButton(String name, String filename, int x , int y) {
        JButton temp = new JButton(name);
        temp.setFocusable(false);
        temp.setFont(new Font("SpaceGrotesk",Font.PLAIN,20));
        ImageIcon temp1 = new ImageIcon(filename);//src/Mat/Buttons/resignBtn.png
        Image btnBG = temp1.getImage().getScaledInstance(66,30,Image.SCALE_SMOOTH);
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
        temp.setBounds(x,y,66,30);
        return temp;
    }
    public void setButtons()
    {
        resign = createButton("resign","src/Mat/Buttons/drawBtn.png",640,250);
        draw = createButton("draw","src/Mat/Buttons/resignBtn.png",730,250);
        resign2 = createButton("resign2","src/Mat/Buttons/drawBtn.png",640,340);
        draw2 = createButton("draw2","src/Mat/Buttons/resignBtn.png",730,340);
        container.add(resign);
        container.add(draw);
        container.add(resign2);
        container.add(draw2);
    }
    private void set_backgrounds() {
       // ImageIcon backgroundImage = new  ImageIcon("src/Mat/BackG/gameFields.png");
        Image backgroundImage;
        ImageIcon temp1 = new ImageIcon("src/Mat/BackG/rgameFields.png");
       // backgroundImage = temp1.getImage().getScaledInstance(860,600,Image.SCALE_DEFAULT);
        JLabel background = new JLabel();
        background.setIcon(temp1);
        background.setBounds(0,0,864,614);
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
    private void action() {
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                System.out.println(button.getName());
                previous = current;
                current = ((JButton) e.getSource()).getName();
                if(current == previous){
                    previous = null ;
                    return;
                }
                if(drag != null)
                {
                    move_piece(previous,current);
                    previous = null;
                    current = null;
                    drag = null;
                }
                else if(get_button(current).getIcon() != null)
                {
                    drag = get_button(current).getIcon();
                }
            }
        });
    }
    private int get_position(String pos){
        int result;
        result =(8 - (pos.charAt(1) - '0'))*8;
        result+= (pos.charAt(0)-'A');
        return result;
    }
   private JButton get_button(String pos) {
       return (JButton) ChessBoardPanel.getComponent(get_position(pos));
   }
   private void move_piece(String p1 ,String p2) {
       Icon temp = get_button(p1).getIcon();
       if(temp == null)return;
       get_button(p1).setIcon(null);
       get_button(p2).setIcon(temp);
   }

}
