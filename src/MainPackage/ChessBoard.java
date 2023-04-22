package MainPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChessBoard extends JFrame{
    public JFrame board;
    public JButton button;
    public String current;
    JPanel ChessBoardPanel;
    Image pawn;
    public ChessBoard(){initialize();}

    public void initialize() {
        ImageIcon pawnPath = new ImageIcon("src/pieces/pawn.png");
        pawn = pawnPath.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        board = new JFrame();
        board.setTitle("chess");
        board.setSize(750,535);
        board.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        board.setLocationRelativeTo(null);
        board.setResizable(false);
        ChessBoardPanel = new JPanel(new GridLayout(8 , 8 , 0 , 0));
        table();
        get_button("E4").setIcon(new ImageIcon(pawn));
        board.setVisible(true);
    }

    public void table(){

        JPanel container = new JPanel(null);
        container.add(ChessBoardPanel);

        ChessBoardPanel.setBounds(0,0,500,500);
        JButton [][]pos = new JButton[8][8];
        for(int i = 0 ;i < 8; i++)
            for(int j = 0 ; j < 8; j++)
            {
                initialize_button(i,j);
                action();
                if ((i + j) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.BLUE);
                }
                pos[i][j] = button;
                ChessBoardPanel.add(pos[i][j]);
            }
        board.add(container);
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
                current = button.getName();
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
}
