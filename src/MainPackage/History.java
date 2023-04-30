package MainPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class History extends JFrame implements MouseListener {



    private    JScrollPane scroll;
    IconsAndColors ic = new IconsAndColors();

    public JFrame frame;

    public int width = 870,height = 1024 *width/1440;

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
        scroll.getViewport().setBackground(ic.black);
        frame.getContentPane().add(scroll);

    }







    public void setMatchHistory() {
        matchHistory = new JPanel();
        matchHistory.setBackground(ic.black);
        matchHistory.setLayout(new GridLayout(15+1 , 1, 0, 20 *width/1440));  //rows should be variables +1 is important

        matchHistory.setOpaque(true);
        matchHistory.setBorder(BorderFactory.createEmptyBorder(20 *width/1440, 0, 0, 0));

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
                    case 1 : block.setIcon(resizeWithRatio(win)); break;
                    case 0 : block.setIcon(resizeWithRatio(draw)); break;
                    case -1 : block.setIcon(resizeWithRatio(lose)); break;
                }

        setMatchLook(block);
        return block;
    }


    private void setMatchLook(JLabel subject) {
        subject.setOpaque(false);
        subject.setHorizontalTextPosition(JLabel.CENTER);
        subject.setVerticalTextPosition(JLabel.CENTER);


        subject.setHorizontalAlignment(JLabel.CENTER);

        subject.setFont(new Font("Space Grotesk", Font.BOLD, 31 *width/1440));
        subject.setForeground(ic.white);
    }
    public ImageIcon resizeWithRatio(ImageIcon icon){
        ImageIcon resized = ic.Resize(icon,icon.getIconWidth()*width/1440, icon.getIconHeight()*width/1440);

        return resized;
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