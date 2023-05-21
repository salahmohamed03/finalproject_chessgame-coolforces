package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import data.*;

public class HomePage extends Window implements MouseListener {
    //LoginPage l;
    public JLabel welcome;
    //STATS
    public JPanel stats;
    public JPanel matchesPanel;
    public JPanel winCountPanel;
    public JPanel winRatePanel;
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

    @Override
    protected void setupWindow() {
        setBackG(ic.colorPath+"BackG/main.png");
        setLogo();
        setWelcome();
        setStats();
        setBtns();
        setLogOutBtn();
    }

    public void setLogo(){
        ImageIcon logo = new ImageIcon(ic.colorPath+"Comp/main/logo.png");
        JLabel logoLabel = new JLabel(ic.resizeWithRatio(logo));
        logoLabel.setBounds(1020*width/1440, 96*height/1024, 250*width/1440, 250*height/1024);
        base.add(logoLabel, Integer.valueOf(1));
    }

    public void setWelcome(){
        welcome = new JLabel("Welcome " + mainUser.getName(), SwingConstants.CENTER); // username should be getUsername
        welcome.setFont(new Font("Space Grotesk", Font.BOLD, 30*width/1440));
        welcome.setForeground(ic.white);
        welcome.setBounds(984*width/1440, 330*height/1024, 304*width/1440, 25*height/1024);
        base.add(welcome, Integer.valueOf(1));
    }

    public void setStats(){
        stats = new JPanel();
        stats.setBounds(915*width/1440,383*height/1024,420*width/1440,80*height/1024);
        stats.setBackground(ic.white);
        stats.setLayout(new GridLayout(1,3,20*width/1440,0));
        stats.setOpaque(false);

        matchesPanel =  new JPanel();
        winCountPanel = new JPanel();
        winRatePanel =  new JPanel();

        setStatsPanel(matchesPanel);
        setStatsPanel(winCountPanel);
        setStatsPanel(winRatePanel);


        base.add(stats,Integer.valueOf(1));
    }

    public void setStatsPanel(JPanel typePanel){
        label = new JLabel();

        setStatsPanelLook(typePanel);
        if (typePanel == matchesPanel){
            getStats(matches);
            label.setText("Matches");
        } else if (typePanel == winCountPanel) {
            getStats(winCount);
            label.setText("Wins");
        } else if (typePanel == winRatePanel) {
            getStats(winRate);
            label.setText("Win Rate");
        }

        stats.add(typePanel);
    }

    public void getStats (JLabel type){
        ArrayList <Match> dispMatches = new ArrayList<>();
        dispMatches.addAll(getMatches(mainUser.getName()));
        int[] winRateAndWins = Arrays.copyOf(getWinRateAndWins(dispMatches),2);
        if (type == matches) {
            matches = new JLabel(Integer.toString(dispMatches.size())); //Just a value for Gui (needs to be changed)
            setStatsLook(matches, "number");

            matchesPanel.add(matches,BorderLayout.CENTER);
        } else if (type == winCount) {
            winCount = new JLabel(Integer.toString((int)winRateAndWins[1])); //Just a value for Gui (needs to be changed)
            setStatsLook(winCount, "number");

            winCountPanel.add(winCount,BorderLayout.CENTER);
            //stats.add(winCount,BorderLayout.NORTH);
        } else if (type == winRate) {
            winRate = new JLabel(Integer.toString(winRateAndWins[0])); //Just a value for Gui (needs to be changed)
            setStatsLook(winRate, "number");
            setStatsLook(winRate, "number");
            winRate.setFont(new Font("Space Grotesk", Font.BOLD, 40*width/1440));

            winRatePanel.add(winRate,BorderLayout.CENTER);
        }

    }
    public void setStatsPanelLook (JPanel panel){
        panel.setOpaque(false);
        panel.setBackground(ic.black);
        panel.setLayout(new BorderLayout(0,0));

        setStatsLook(label,"text");
        label.setPreferredSize(new Dimension(0,30*height/1024));
        panel.add(label,BorderLayout.SOUTH);

    }
    public void setStatsLook( JLabel label , String type){ //type is number or type

        if(type.equals("number"))  {
            label.setFont(new Font("Space Grotesk", Font.BOLD, 60*width/1440));
        }
        else if (type.equals("text")) {
            label.setFont(new Font("Space Grotesk", Font.BOLD, 20*width/1440));
            //System.out.println("t"); //for testing
        }

        label.setPreferredSize(new Dimension(0,100*height/1024));
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(ic.mainColor);
        label.setBackground(ic.black);
        label.setOpaque(false);

    }


    public void setBtns(){
        btns =  new JPanel();
        btns.setBackground(ic.black);
        btns.setOpaque(false);
        btns.setBounds(915*width/1440, 486*height/1024, 442*width/1440, 273*height/1024);

        newGame = createButton("NEW GAME",3);
        history = createButton("HISTORY",3);
        settings=createButton("SETTINGS",3); //------> under development SHOULD CHANGE COLORS

        btns.add(newGame); btns.add(history); btns.add(settings);

        base.add(btns, Integer.valueOf(1));
    }

    public  void setLogOutBtn(){
        logOut = createButton("Log out",3);
        logOut.setIcon(null); //to edit the btn appearance
        logOut.setFont(new Font("Space Grotesk", Font.PLAIN, 25*width/1440));
        logOut.setBounds(1086*width/1440, 750*height/1024, 101*width/1440, 30*height/1024);

        base.add(logOut, Integer.valueOf(1));

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource()==history){
            historyLocal.initialize(mainUser);
        }else{
            frame.setVisible(false);
            if (e.getSource()==newGame){
                gameStartLocal.initializeWithUser(mainUser);
            }
            if (e.getSource()==settings){
                settingsLocal.initializeWithUser(mainUser);
            }
            if (e.getSource()==logOut){
                loginLocal.initialize();
            }
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
