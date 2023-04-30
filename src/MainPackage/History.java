package MainPackage;
import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class History extends JFrame implements MouseListener {
=======

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class History {
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa
    Color mainColor =  Color.decode("#FF006E");
    Color secondColor =  Color.decode("#AE2965");
    Color black =  Color.decode("#1B1725");
    Color white =  Color.decode("#FDFFFC");
    public JFrame frame;
    private ImageIcon backG_image = new ImageIcon("src/Mat/BackG/histFields.png");
    private JLabel backG = new JLabel(backG_image);
    public int width = 1440,height = 1024;
    public JScrollPane scroll;
    JPanel content;

    JLayeredPane base;
    public JButton backBtn;

    //stats
    public JPanel stats;
    public JPanel matchesPanel = new JPanel();
    public JPanel winCountPanel = new JPanel();
    public JPanel winRatePanel = new JPanel();
    public JLabel matches;
    public JLabel matchesLabel;
    public JLabel winCount;
    public JLabel winCountLabel;
    public JLabel winRate;
    public JLabel winRateLabel;
    public JLabel label;
    public JPanel typePanel = new JPanel();
    public JPanel panel = new JPanel();
<<<<<<< HEAD
    public JLabel block;
=======
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa


    //matches history
    JPanel matchHistory;


<<<<<<< HEAD
    JLabel[] matchArray;


=======
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa

    public History(){initialize();}

    public void initialize(){


        initializeWindow();
<<<<<<< HEAD
        setMatchHistory();
        createScroll();
        frame.setVisible(true);
        //setBackG();
        //setBtns();
        //setStats();
       // setMatchHistory();
=======
        setBackG();
        setBtns();
        setStats();
        setMatchHistory();
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa
    }
    private void initializeWindow() {
        frame = new JFrame();
        frame.setSize(width+30, height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
<<<<<<< HEAD



        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void createScroll(){

        scroll = new JScrollPane(matchHistory);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //change to needed
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(black);
        frame.getContentPane().add(scroll);

    }



=======
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        content = new JPanel();
        content.setLayout(new BorderLayout());

        createScroll(content);

        base = new JLayeredPane();
        content.add(base);

    }

    private void createScroll(JPanel panel){

        scroll = new JScrollPane(content);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //change to needed
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.getContentPane().add(scroll);

    }
    public void setBackG(){
        backG.setBounds(0,0,width,height);
        base.add(backG, Integer.valueOf(0));

    }
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa
    public void setBtns() {
        backBtn = createButton(null,3);
        base.add(backBtn, Integer.valueOf(1));
    }

    //STATS HEADER

    public void setStats(){
        stats = new JPanel();
        stats.setBounds(150*width/1440,140*height/1024,1144*width/1440,150*height/1024);
        stats.setBackground(white);
        stats.setLayout(new GridLayout(1,3,150,0));
        stats.setOpaque(false);

        setStatsPanel(matchesPanel);
        setStatsPanel(winCountPanel);
        setStatsPanel(winRatePanel);


        base.add(stats,Integer.valueOf(1));
    }

    public void setStatsPanel(JPanel typePanel){
        label = new JLabel();
        //System.out.println("true"); // for testing
        setStatsPanelLook(typePanel);
        if (typePanel == matchesPanel){
            getStats(matches);
            label.setText("Matches");
        } else if (typePanel == winCountPanel) {
            getStats(winCount);
            label.setText("Wins");
        } else if (typePanel == winRatePanel) {
            getStats(winRate);
            label.setText("Win Rate%");
        }

        stats.add(typePanel);
    }

    public void setStatsPanelLook (JPanel panel){
        panel.setOpaque(false);
        panel.setBackground(black);
        panel.setLayout(new BorderLayout(0,0));

        setStatsLook(label,"text");
        label.setPreferredSize(new Dimension(0,30));
        panel.add(label,BorderLayout.SOUTH);

    }
    public void getStats (JLabel type){
        if (type == matches) {
            matches = new JLabel("30"); //Just a value for Gui (needs to be changed)
            setStatsLook(matches, "number");

            matchesPanel.add(matches,BorderLayout.CENTER);
        } else if (type == winCount) {
            winCount = new JLabel("15"); //Just a value for Gui (needs to be changed)
            setStatsLook(winCount, "number");

            winCountPanel.add(winCount,BorderLayout.CENTER);
            //stats.add(winCount,BorderLayout.NORTH);
        } else if (type == winRate) {
            winRate = new JLabel("50%"); //Just a value for Gui (needs to be changed)
            setStatsLook(winRate, "number");

            winRatePanel.add(winRate,BorderLayout.CENTER);
        }

    }
    public void setStatsLook( JLabel label , String type){ //type is number or type

        if(type.equals("number"))  {
            label.setFont(new Font("Space Grotesk", Font.BOLD, 133));
        }
        else if (type.equals("text")) {
            label.setFont(new Font("Space Grotesk", Font.PLAIN, 31));
            //System.out.println("t"); //for testing
        }

        label.setPreferredSize(new Dimension(0,100));
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(white);
        label.setBackground(black);
        label.setOpaque(false);

    }
<<<<<<< HEAD


    public void setMatchHistory(){
        matchHistory = new JPanel();
        matchHistory.setBackground(black);
        matchHistory.setLayout(new GridLayout(15+1,1,0,20)); /// rows should be variables +1 is important

        matchHistory.setOpaque(true);
        matchHistory.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        matchArray = new JLabel[15];

        //setHeaderMargin();
      //  matchHistory.add(new JLabel());

        for (int i = 0 ; i<14 +1 ;i++){
            matchArray[i]=createMatch("Opponent",1);
            matchArray[i].addMouseListener(this);
            matchHistory.add(matchArray[i]);
        }

        frame.add(matchHistory );



=======
    public void setMatchHistory(){
        matchHistory = new JPanel();
        matchHistory.setBounds(30*width/1440,368*height/1024,1361*width/1440,600*100*height/1024); //Height should be 71 * number of matches (length of Array)
        matchHistory.setBackground(white);
        matchHistory.setLayout(new FlowLayout());
        matchHistory.setOpaque(false);

        for (int i = 0 ; i<10;i++){
            matchHistory.add(createMatch("Opponent",1));
        }

        base.add(matchHistory,Integer.valueOf(1));
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa
    }

    private JLabel createMatch(String opp, int result){ /*String name of opponent and the result*/
        ImageIcon win = new ImageIcon("src/Mat/Comp/hist/win.png");
        ImageIcon draw = new ImageIcon("src/Mat/Comp/hist/draw.png");
        ImageIcon lose = new ImageIcon("src/Mat/Comp/hist/lose.png");
<<<<<<< HEAD
        block = new JLabel(opp+"                                                                                                                                  ");
=======
        JLabel block = new JLabel(opp+"                                                                                                                                  ");
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa
        //BE CAREFUL ITS A SPACES!!!!!!!
        //1 = win
        //0 = draw
        //-1 = lose

        if (result == 1) {
            block.setIcon(win);
        } else if (result == 0) {
            block.setIcon(draw);
        } else if (result == -1) {
            block.setIcon(lose);
        }

<<<<<<< HEAD
=======

>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa
        setMatchLook(block);




        return block;
    }
    private void setMatchLook(JLabel subject){
        subject.setBackground(black);//any color. Just to be transparent
        subject.setOpaque(false);
        subject.setHorizontalTextPosition(JLabel.CENTER);
        subject.setVerticalTextPosition(JLabel.CENTER);
<<<<<<< HEAD
        subject.setHorizontalAlignment(JLabel.CENTER);
=======
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa

        subject.setFont(new Font("Space Grotesk", Font.BOLD, 31));
        subject.setForeground(white);
        subject.setBackground(black);
    }







    private JButton createButton(String name, int btn){
        JButton button = new JButton(name);
        button.setFocusable(false);

        //design//
        button.setFont(new Font("Space Grotesk", Font.BOLD, 36));
        ImageIcon pinkBtn = new ImageIcon("src/Mat/Buttons/pinkBtn.png");
        ImageIcon whiteBtn = new ImageIcon("src/Mat/Buttons/whiteBtn.png");
        ImageIcon backBtn = new ImageIcon("src/Mat/Buttons/backBtn.png");

        switch (btn) {
            case 1 -> {
                button.setIcon(pinkBtn);
                button.setForeground(white);
                break;
            }
            case 2 -> {
                button.setIcon(whiteBtn);
                button.setForeground(mainColor);
                break;
            }
            case 3 ->{
                button.setIcon(backBtn);
                button.setBounds(46*width/1440,39*height/1024,65*width/1440,65*height/1024);
                break;
            }
        }
        button.setBackground(black);//any color. Just to be transparent
        button.setOpaque(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);

        button.setBorderPainted(false);

        return button;
    }
<<<<<<< HEAD
    public static void main(String []args)
    {
        History H = new History();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
       for (int i = 0 ; i<14 +1 ;i++){
            if (e.getSource() == matchArray[i]) //should change
                System.out.println("true");
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



    /*public static void main(String []args)
    {
        History c = new History();
        c.frame.setVisible(true);

    }*/
=======



    public static void main(String []args)
    {
        History c = new History();
        c.frame.setVisible(true);

    }
>>>>>>> cc9ffc2f04ed4d757d65d307691fea72349a34aa
}
