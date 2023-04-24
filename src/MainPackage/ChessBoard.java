package MainPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChessBoard extends JFrame{
    public JFrame board;
    public JButton button;
    public String current;
    public String previous;
    private boolean turn;
    private JPanel container;
    private JPanel ChessBoardPanel;
    public ChessBoard(){initialize();}

    public void initialize() {
        PieceIcons icon = new PieceIcons();
        turn = false;
        initialize_board();
        draw_chessBoard();
        get_button("E4").setIcon(new ImageIcon(icon.black_bishop));// test
        get_button("E5").setIcon(new ImageIcon(icon.white_bishop));// test
        //move_piece("E4","A8");
        //set_backgrounds();
        board.setVisible(true);// show the board
    }
    public void initialize_board(){
        board = new JFrame();
        initialize_container();
        board.add(container);
        board.setTitle("chess");
        board.setSize(864,614);
        board.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        board.setLocationRelativeTo(null);
        board.setResizable(false);
    }
    private void initialize_container(){
        container = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, Color.decode("#361624"), w, 0, Color.decode("#FF006E"));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
    }
    public void draw_chessBoard(){
        ChessBoardPanel = new JPanel(new GridLayout(8 , 8 , 0 , 0));
        container.add(ChessBoardPanel);
        ChessBoardPanel.setBounds(40 ,40,480,480);
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
    private void set_backgrounds()
    {
        ImageIcon backgroundImage = new  ImageIcon("src/pieces/test.jpg");
        JLabel background = new JLabel();
        background.setIcon(backgroundImage);
       // background.setBounds(0,0,50,50);
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
    private void action()
    {
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JButton button = (JButton) e.getSource();
                System.out.println(button.getName());
                previous = current;
                current = button.getName();
                if(previous == null)return;
                Icon temp = get_button(previous).getIcon();
                if(temp != null)
                {
                    move_piece(previous,current);
                    current = null;
                    previous = null;
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
   private JButton get_button(String pos)
   {
       return (JButton) ChessBoardPanel.getComponent(get_position(pos));
   }
   private void move_piece(String p1 ,String p2)
   {
       Icon temp = get_button(p1).getIcon();
       if(temp == null)return;
       get_button(p1).setIcon(null);
       get_button(p2).setIcon(temp);
   }

}
