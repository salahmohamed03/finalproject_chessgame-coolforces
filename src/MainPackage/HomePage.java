package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomePage extends JFrame implements MouseListener {
    Color mainColor =  Color.decode("#FF006E");
    Color secondColor =  Color.decode("#AE2965");
    Color black =  Color.decode("#1B1725");
    Color white =  Color.decode("#FDFFFC");
    public JFrame frame;
    public int width = 1440, height = 1024;
    private final ImageIcon backG_image = new ImageIcon("src/Mat/BackG/main.png");
    private final JLabel backG = new JLabel(backG_image);

    public  JLayeredPane base;

    public JLabel welcome;
    //STATS
    public JPanel stats;
    public JPanel matchesPanel = new JPanel();
    public JPanel winCountPanel = new JPanel();
    public JPanel winRatePanel = new JPanel();
    public JLabel matches;
    public JLabel winCount;
    public JLabel winRate;
    public JLabel label;

    //BTNS

    public JPanel btns;
    public  JButton newGame;
    public  JButton history;
    public  JButton settings;
    public JButton logOut;

    public HomePage(){
        initialize();
    }

    public void  initialize(){

        initializeWindow();
        setBackG();
        setLogo();
        setWelcome();
        setStats();
        setBtns();
        setLogOutBtn();

        frame.setVisible(true);

    }

    private void initializeWindow() {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        base = new JLayeredPane();
        frame.add(base);

    }

    public void setBackG(){
        backG.setBounds(0,0,width,height);
        base.add(backG, Integer.valueOf(0));

    }
    public void setLogo(){
         ImageIcon logo = new ImageIcon("src/Mat/Comp/main/logo.png");
         JLabel logoLabel = new JLabel(logo);
         logoLabel.setBounds(1043*width/1440, 96*height/1024, 188*width/1440, 188*height/1024);
         base.add(logoLabel, Integer.valueOf(1));
    }

    public void setWelcome(){
        welcome = new JLabel("Welcome " + "username"); // username should be getUsername
        welcome.setFont(new Font("Space Grotesk", Font.BOLD, 30));
        welcome.setForeground(white);
        welcome.setBounds(984*width/1440, 330*height/1024, 304*width/1440, 25*height/1024);

        base.add(welcome, Integer.valueOf(1));
    }

    public void setStats(){
        stats = new JPanel();
        stats.setBounds(915*width/1440,383*height/1024,420*width/1440,80*height/1024);
        stats.setBackground(white);
        stats.setLayout(new GridLayout(1,3,20,0));
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
        label.setPreferredSize(new Dimension(0,30*height/1024));
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
            label.setFont(new Font("Space Grotesk", Font.BOLD, 60));
        }
        else if (type.equals("text")) {
            label.setFont(new Font("Space Grotesk", Font.BOLD, 20));
            //System.out.println("t"); //for testing
        }

        label.setPreferredSize(new Dimension(0,100*height/1024));
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(mainColor);
        label.setBackground(black);
        label.setOpaque(false);

    }


    public void setBtns(){
        btns =  new JPanel();
        btns.setBackground(black);
        btns.setOpaque(false);
        btns.setBounds(915*width/1440, 486*height/1024, 442*width/1440, 273*height/1024);

            newGame = createButton("NEW GAME");
            history = createButton("HISTORY");
            settings=createButton("SETTINGS");

        btns.add(newGame); btns.add(history); btns.add(settings);

        base.add(btns, Integer.valueOf(1));
    }

    public  void setLogOutBtn(){
        logOut = createButton("Log out");
        logOut.setIcon(null); //to edit the btn appearance
        logOut.setFont(new Font("Space Grotesk", Font.PLAIN, 25));
        logOut.setBounds(1086*width/1440, 750*height/1024, 101*width/1440, 30*height/1024);

        base.add(logOut, Integer.valueOf(1));

    }
    private JButton createButton(String name){
        JButton button = new JButton(name);
        button.setFocusable(false);
        //design//
        button.setFont(new Font("Space Grotesk", Font.BOLD, 55));
        //button.setBounds(80,30,120,40);
        ImageIcon buttonBackG = new ImageIcon("src/Mat/Buttons/pinkBtn2.png");
        button.setIcon(buttonBackG);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBackground(black);
        button.setForeground(white);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addMouseListener(this);
        return button;
    }

    public static void main(String []args)
    {
        HomePage H = new HomePage();
        H.frame.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==newGame){
            GameStart G = new GameStart();
            frame.setVisible(false);
        }
        if (e.getSource()==history){
            History H = new History();
        }
       // if (e.getSource()==settings){
       // }
        if (e.getSource()==logOut){
            LoginPage L = new LoginPage();
            frame.setVisible(false);
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
