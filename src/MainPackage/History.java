package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class History extends dataHandling implements MouseListener {


    public JFrame frame;
    public JLabel block;
    IconsAndColors ic = new IconsAndColors();
    public int width , height;
    //matches history
    JPanel matchHistory;
    JPanel  container;

    ArrayList <Match> matches =  new ArrayList<>();
    JLabel[] matchArray;
    private User mainUser;

    public static void main(String[] args) {
        History H = new History();

    }
    public History (){}

    public void initialize(User mainUser) {
        this.mainUser=mainUser;
        width =  (ic.width );
        height = (ic.height);
        initializeWindow();
        setMatchHistory();

        createScroll();
        frame.setVisible(true);
    }

    private void initializeWindow() {
        frame = new JFrame();
        frame.setSize(width -10*width/1440 , height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void createScroll() {

        JScrollPane scroll = new JScrollPane(container);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(ic.black);

        frame.add(scroll);

    }

    public void setMatchHistory() {
        container = new JPanel();
        container.setBackground(Color.BLACK);
        container.setOpaque(false);
        matchHistory = new JPanel();
        matchHistory.setBackground(ic.black);
        matchHistory.setLayout(new GridLayout(0, 1, 0, 10));  //rows should be variables +1 is important
            //ROWS EQUAL ZERO SO IT CAN GET ANY NUMBER OF LABELS
        matchHistory.setBounds(0,0,width,180*matches.size() *width/1440);
        matchHistory.setBackground(Color.red);
        matchHistory.setOpaque(false);
        matchHistory.setBorder(BorderFactory.createEmptyBorder(20 * width / 1440, 0, 0, 0));


        matches.addAll(getMatches(mainUser.getName()));
        matchArray = new JLabel[matches.size()]; // number of matches
        String results[]=Arrays.copyOf(getResults(matches),matches.size());
        String Opps[]=Arrays.copyOf(getOppsHistory(matches),matches.size());


        matchArray = new JLabel[matches.size()];

        for (int i = 0; i < matches.size(); i++) {
            matchArray[i] = createMatch(Opps[i], results[i]); //Only 15 characters!!!
            matchArray[i].addMouseListener(this);
            matchHistory.add(matchArray[i]);
        }
        container.add(matchHistory);
        frame.add(container);

    }

    private JLabel createMatch(String opp, String result) {
        ImageIcon win = new ImageIcon(ic.colorPath+"Comp/hist/win.png");
        ImageIcon draw = new ImageIcon(ic.colorPath+"Comp/hist/draw.png");
        ImageIcon lose = new ImageIcon(ic.colorPath+"Comp/hist/lose.png");
        // BE CAREFUL THERE ARE SPACES !!!!!!!
        block = new JLabel(makeOppLeft(opp));

        switch (result) {
            case "Win":
                block.setIcon(ic.resizeWithRatio(win));
                break;
            case "Draw":
                block.setIcon(ic.resizeWithRatio(draw));
                break;
            case "Defeat":
                block.setIcon(ic.resizeWithRatio(lose));
                break;
        }

        setMatchLook(block);
        return block;
    }

    private String makeOppLeft(String opponent) {
        int size = 140;
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
        for (int i = 0; i < matches.size() ; i++) {
            if (e.getSource() == matchArray[i]) //should change
            {
                ChessBoardHistory j = new ChessBoardHistory(matches.get(i).moves, this.mainUser, matches.get(i).oppUserStr, matches.get(i).color);
                System.out.println(mainUser.getName());
            }
        }
    }


    //function to get the result of the matches
    public String[] getResults(ArrayList <Match> matches)
    {
        ArrayList <String> results =  new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) 
        {
            results.add(matches.get(i).result);
        }
        String arr[]= results.toArray(new String[results.size()]);
        return arr;
    }

    //function to get opponents
    public String[] getOppsHistory(ArrayList <Match> matches)
    {
        ArrayList <String> Opps =  new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) 
        {
                Opps.add(matches.get(i).oppUserStr);
        }
        String arr[]= Opps.toArray(new String[Opps.size()]);
        return arr;
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