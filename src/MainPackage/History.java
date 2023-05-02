package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class History extends JFrame implements MouseListener {


    public JFrame frame;
    public JLabel block;
    IconsAndColors ic = new IconsAndColors();
    public int width = ic.width, height = ic.height;
    //matches history
    JPanel matchHistory;

    JLabel[] matchArray;


    public History() {
        initialize();
    }

    public static void main(String[] args) {
        History H = new History();

    }

    public void initialize() {

        initializeWindow();

        setMatchHistory();
        createScroll();
        frame.setVisible(true);


    }

    private void initializeWindow() {
        frame = new JFrame();
        frame.setSize(width + 30, height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void createScroll() {

        JScrollPane scroll = new JScrollPane(matchHistory);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(ic.black);
        frame.getContentPane().add(scroll);

    }

    public void setMatchHistory() {
        matchHistory = new JPanel();
        matchHistory.setBackground(ic.black);
        matchHistory.setLayout(new GridLayout(15 + 1, 1, 0, 20 * width / 1440));  //rows should be variables +1 is important

        matchHistory.setOpaque(true);
        matchHistory.setBorder(BorderFactory.createEmptyBorder(20 * width / 1440, 0, 0, 0));

        matchArray = new JLabel[15];

        for (int i = 0; i < 14 + 1; i++) {
            matchArray[i] = createMatch("Opponent", 1); //Only 15 characters!!!
            matchArray[i].addMouseListener(this);
            matchHistory.add(matchArray[i]);
        }

        frame.add(matchHistory);
    }

    private JLabel createMatch(String opp, int result) {
        ImageIcon win = new ImageIcon("src/Mat/Comp/hist/win.png");
        ImageIcon draw = new ImageIcon("src/Mat/Comp/hist/draw.png");
        ImageIcon lose = new ImageIcon("src/Mat/Comp/hist/lose.png");
        // BE CAREFUL THERE ARE SPACES !!!!!!!
        block = new JLabel(makeOppLeft(opp));

        switch (result) {
            case 1:
                block.setIcon(ic.resizeWithRatio(win));
                break;
            case 0:
                block.setIcon(ic.resizeWithRatio(draw));
                break;
            case -1:
                block.setIcon(ic.resizeWithRatio(lose));
                break;
        }

        setMatchLook(block);
        return block;
    }

    private String makeOppLeft(String opponent) {
        int size = 145;
        char[] spaces = new char[size];
        Arrays.fill(spaces, ' ');
        //System.out.println(opponent.length());
        for (int i = 0; i < opponent.length(); i++) {
            spaces[i] = opponent.charAt(i);
        }

        return new String(spaces);
    }

    private void setMatchLook(JLabel subject) {
        subject.setOpaque(false);
        subject.setHorizontalTextPosition(JLabel.CENTER);
        subject.setVerticalTextPosition(JLabel.CENTER);


        subject.setHorizontalAlignment(JLabel.CENTER);

        subject.setFont(new Font("Space Grotesk", Font.BOLD, 31 * width / 1440));
        subject.setForeground(ic.white);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < 14 + 1; i++) {
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