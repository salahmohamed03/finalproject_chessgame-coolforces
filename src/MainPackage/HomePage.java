package MainPackage;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    Color mainColor =  Color.decode("#FF006E");
    Color secondColor =  Color.decode("#AE2965");
    Color black =  Color.decode("#1B1725");
    Color white =  Color.decode("#FDFFFC");
    public JFrame frame;
    public int width, height;
    private final ImageIcon backG_image = new ImageIcon("src/Mat/BackG/main.png");
    private final JLabel backG = new JLabel(backG_image);

    private ImageIcon logo = new ImageIcon("src/Mat/Comp/main/logo.png");
    private JLabel logoLabel = new JLabel(logo);

    public JButton logOut;


    public String username ;
    public HomePage(){
        initialize();
    }

    public void  initialize(){
        width = 1440;
        height = 1024;

        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(); /*BACKGROUND*/
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(black);
        backG.setHorizontalAlignment(JLabel.LEFT);

        mainPanel.add(backG);

        JPanel side = new JPanel();
        side.setLayout(new BorderLayout(0,20));
        side.setPreferredSize(new Dimension(525,1024));
        side.setBackground(Color.decode("#110D1C"));

        JPanel topSide = new JPanel();
            topSide.setPreferredSize(new Dimension(525, 350));
            topSide.setLayout(new BorderLayout(0,30));
            topSide.setOpaque(false);

                JLabel welcome = new JLabel(("welcome " + username), SwingConstants.CENTER);
                    welcome.setFont(new Font("Space Grotesk", Font.BOLD, 25));
                    welcome.setForeground(white);

            topSide.add(logoLabel, BorderLayout.CENTER);
            topSide.add(welcome, BorderLayout.SOUTH);

        side.add(topSide,BorderLayout.NORTH);


        JPanel btns = new JPanel();
        btns.setLayout(new GridLayout(3, 1, 0, 49));
        btns.setOpaque(false);
        JButton newGameBtn = createButton("NEW GAME");
        JButton historyBtn = createButton("HISTORY");
        JButton settingBtn = createButton("SETTINGS");

        btns.add(newGameBtn);
        btns.add(historyBtn);
        btns.add(settingBtn);

            side.add(btns,BorderLayout.CENTER);



        JPanel bottomSide = new JPanel();
        bottomSide.setPreferredSize(new Dimension(525, 230));
        bottomSide.setOpaque(false);

            logOut = new JButton("Log out");
                logOut.setFocusable(false);
                logOut.setOpaque(false);

                logOut.setFont(new Font("Space Grotesk", Font.BOLD, 20));
                logOut.setForeground(white);
                logOut.setBackground(black);
                logOut.setBorder(BorderFactory.createEmptyBorder());

            bottomSide.add(logOut);
        side.add(bottomSide,BorderLayout.SOUTH);


        mainPanel.add(side,BorderLayout.EAST);
        frame.add(mainPanel);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    private JButton createButton(String name){
        JButton button = new JButton(name);
        button.setFocusable(false);
        //design//
        button.setFont(new Font("Space Grotesk", Font.PLAIN, 55));
        //button.setBounds(80,30,120,40);
        ImageIcon buttonBack = new ImageIcon("src/Mat/Buttons/pinkBtn2.png");
        button.setIcon(buttonBack);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBackground(Color.decode("#110D1C"));
        button.setForeground(white);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }
}
