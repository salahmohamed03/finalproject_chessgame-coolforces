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

    public ArrayList <Match> matches =  new ArrayList<>();
    JLabel[] matchArray;
    private User mainUser;



    public static void main(String[] args) {
        History H = new History();

    }
    public History (){}

    String mainUserName="";
    public void initialize(String mainUserName) {
        this.mainUserName=mainUserName;
        width =  (ic.width );
        height = (ic.height);
        initializeWindow();
        setMatchHistory();
        createScroll();
        frame.setVisible(true);
    }

    private void initializeWindow() {
        frame = new JFrame();
        frame.setSize(width -30 , height);
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
        matchHistory.setLayout(new GridLayout(0, 1, 0, 10 * width / 1440));  //rows should be variables +1 is important
            //ROWS EQUAL ZERO SO IT CAN GET ANY NUMBER OF LABELS
        matchHistory.setBounds(0,0,width,matches.size()*80);
        matchHistory.setOpaque(true);
        matchHistory.setBorder(BorderFactory.createEmptyBorder(20 * width / 1440, 0, 0, 0));


        matches.addAll(getMatches(mainUserName));
        matchArray = new JLabel[matches.size()]; // number of matches
        String results[]=Arrays.copyOf(getResults(matches),matches.size());
        String Opps[]=Arrays.copyOf(getOppsHistory(matches),matches.size());


        matchArray = new JLabel[matches.size()];

        for (int i = 0; i < matches.size(); i++) {
            matchArray[i] = createMatch(Opps[i], results[i]); //Only 15 characters!!!
            matchArray[i].addMouseListener(this);
            matchHistory.add(matchArray[i]);
        }

        frame.add(matchHistory);
    }

    private JLabel createMatch(String opp, String result) {
        ImageIcon win = new ImageIcon("src/Mat/Comp/hist/win.png");
        ImageIcon draw = new ImageIcon("src/Mat/Comp/hist/draw.png");
        ImageIcon lose = new ImageIcon("src/Mat/Comp/hist/lose.png");
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
        for (int i = 0; i < matches.size() ; i++) {
            if (e.getSource() == matchArray[i]) //should change
            {
                System.out.println(true);
//                ChessBoardHistory j = new ChessBoardHistory(mainUser);
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