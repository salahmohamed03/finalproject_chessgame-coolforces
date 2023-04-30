package MainPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class History extends JFrame implements MouseListener {



    private    JScrollPane scroll;
    Color mainColor =  Color.decode("#FF006E");

    Color secondColor =  Color.decode("#AE2965");
    Color black =  Color.decode("#1B1725");
    Color white =  Color.decode("#FDFFFC");
    public JFrame frame;

    public int width = 1440,height = 1024;
    public JButton backBtn;

    public JLabel label;
    public JPanel typePanel = new JPanel();
    public JPanel panel = new JPanel();

    public JLabel block;






    //matches history
    JPanel matchHistory;





    JLabel[] matchArray;






    public History(){initialize();}


    public void initialize(){

        initializeWindow();

        setMatchHistory();
        createScroll();
        frame.setVisible(true);;


    }
    private void initializeWindow() {
        frame = new JFrame();
        frame.setSize(width+30, height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);



        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void createScroll(){

        scroll = new JScrollPane(matchHistory);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(black);
        frame.getContentPane().add(scroll);

    }







    public void setMatchHistory() {
        matchHistory = new JPanel();
        matchHistory.setBackground(black);
        matchHistory.setLayout(new GridLayout(15+1 , 1, 0, 20));  //rows should be variables +1 is important

        matchHistory.setOpaque(true);
        matchHistory.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        matchArray = new JLabel[15];

        for (int i = 0; i < 14 + 1; i++) {
            matchArray[i] = createMatch("Opponent", 1);
            matchArray[i].addMouseListener(this);
            matchHistory.add(matchArray[i]);
        }

        frame.add(matchHistory);
    }




    private JLabel createMatch(String opp, int result){
        ImageIcon win = new ImageIcon("src/Mat/Comp/hist/win.png");
        ImageIcon draw = new ImageIcon("src/Mat/Comp/hist/draw.png");
        ImageIcon lose = new ImageIcon("src/Mat/Comp/hist/lose.png");
                                        // BE CAREFUL THERE ARE SPACES !!!!!!!
        block = new JLabel(opp+"                                                                                                                                ");

                switch (result) {
                    case 1 : block.setIcon(win); break;
                    case 0 : block.setIcon(draw); break;
                    case -1 : block.setIcon(lose); break;
                }

        setMatchLook(block);
        return block;
    }


    private void setMatchLook(JLabel subject) {
        subject.setOpaque(false);
        subject.setHorizontalTextPosition(JLabel.CENTER);
        subject.setVerticalTextPosition(JLabel.CENTER);


        subject.setHorizontalAlignment(JLabel.CENTER);

        subject.setFont(new Font("Space Grotesk", Font.BOLD, 31));
        subject.setForeground(white);
    }

    public static void main(String []args)
    {
        History H = new History();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0 ; i < 14 +1 ;i++){
            if (e.getSource() == matchArray[i]) //should change
            System.out.println(true);
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