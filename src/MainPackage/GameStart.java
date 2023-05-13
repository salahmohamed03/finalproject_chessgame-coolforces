package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameStart extends Window implements MouseListener{
    public ImageIcon whiteIcon = new ImageIcon("src/Mat/Buttons/selectWBtn.png");
    public ImageIcon whiteIconS = new ImageIcon("src/Mat/Buttons/selectedWBtn.png");
    public ImageIcon blackIcon = new ImageIcon("src/Mat/Buttons/selectBBtn.png");
    public ImageIcon blackIconS = new ImageIcon("src/Mat/Buttons/selectedBBtn.png") ;
    static public boolean gameRunning = false;
    JRadioButton whiteBtn;
    JRadioButton blackBtn;
    public JButton addPlayerBtn;
    public JTextField timerSet;
    public JCheckBox timerOn;
    public static String timerInput;
    public JButton startBtn;
    static boolean selectedColor = true;
//    private User mainUser;
    private User oppUser;
//    public GameStart(User mainUser) // this constructor will accept the main user who just came from homepage
//    {
//        this.mainUser=mainUser;
//        initialize();
//    }
//
//
//    public GameStart(){initialize();}


    @Override
    protected void setupWindow() {
        setBackG("src/Mat/BackG/nGameSettFields.png");
        set_backBtn();
        setHeader();
        setBlackOrWhite();
        setOpponentPanel();
        setTimerPanel();
        setStartBtn();
    }


    @Override
    public void setBtns() {

    }


    private void setHeader(){
        JLabel youPlay = createLabel(mainUser.getName() +" color", 83 ); // testing for main user
        youPlay.setHorizontalAlignment(JLabel.CENTER);
        youPlay.setBounds(470 *width/1440,81 *width/1440,545 *width/1440,132 *width/1440);
        base.add(youPlay, Integer.valueOf(1));

    }

    private void setBlackOrWhite(){
        ButtonGroup wORb = new ButtonGroup();

        whiteBtn = new JRadioButton();
        whiteBtn.setIcon(ic.resizeWithRatio(whiteIconS));
        whiteBtn.setBounds(186 *width/1440,227 *width/1440,508 *width/1440,400 *width/1440);
        whiteBtn.setOpaque(false);


        blackBtn = new JRadioButton();
        blackBtn.setIcon(ic.resizeWithRatio(blackIcon));
        blackBtn.setBounds(745 *width/1440,227 *width/1440,508 *width/1440,400 *width/1440);
        blackBtn.setOpaque(false);


        whiteBtn.addMouseListener(this);
        blackBtn.addMouseListener(this);

        wORb.add(whiteBtn);
        wORb.add(blackBtn);

        base.add(whiteBtn, Integer.valueOf(1));
        base.add(blackBtn, Integer.valueOf(1));

    }

    private void setOpponentPanel(){
        JLabel oppLabel = createLabel("Opponent",60 *width/1440);
        oppLabel.setBounds(292 *width/1440,663 *width/1440,310 *width/1440,80 *width/1440);

        //should be players[]
        ArrayList <String> opponents=new ArrayList<>();
        opponents=getOpponentsArrList(mainUser);
        String [] players = opponents.toArray(new String[opponents.size()]);
        JComboBox<String> playerList = new JComboBox<>(players);
        oppUser = findOppUser("gust");
        playerList.addActionListener(e -> {
            String selectedOption = (String) playerList.getSelectedItem();
            oppUser=findOppUser(selectedOption);
            //addMatch(mainUser, oppUser);
        });



            

        playerList.setBounds(765 *width/1440,685 *width/1440,340 *width/1440,50 *width/1440);
        playerList.setFont(new Font("Space Grotesk", Font.BOLD, 20 *width/1440));
        playerList.setForeground(ic.mainColor);
        playerList.setBackground(ic.white);

        ImageIcon add =  new ImageIcon ("src/Mat/Buttons/addBtn.png");
        addPlayerBtn = new JButton(ic.resizeWithRatio(add));
        addPlayerBtn.setBounds(1150 *width/1440,685 *width/1440,82 *width/1440,47 *width/1440);
        addPlayerBtn.setOpaque(false);
        addPlayerBtn.setBackground(ic.black);
        addPlayerBtn.setBorder(BorderFactory.createEmptyBorder());

        addPlayerBtn.addMouseListener(this);



        base.add(oppLabel, Integer.valueOf(1));
        base.add(playerList,Integer.valueOf(1));
        base.add(addPlayerBtn,Integer.valueOf(1));
    }
    private void setTimerPanel(){
        JLabel timerLabel = createLabel("Timer",60 *width/1440);
        timerLabel.setBounds(350 *width/1440,760 *width/1440,310 *width/1440,50 *width/1440);

        timerSet = createTextField("00:00", 40);
        timerSet.setBounds(865 *width/1440,770 *width/1440,171 *width/1440,40 *width/1440);

        ImageIcon on =  new ImageIcon ("src/Mat/Buttons/timerOnBtn.png");
        ImageIcon off =  new ImageIcon ("src/Mat/Buttons/timerOffBtn.png");
        timerOn = new JCheckBox(ic.resizeWithRatio(off));
        timerOn.setSelectedIcon(ic.resizeWithRatio(on));
        timerOn.setBounds(1100 *width/1440,767 *width/1440,146 *width/1440,47 *width/1440);
        timerOn.setOpaque(false);
        timerOn.setBackground(ic.black);
        timerOn.setBorder(BorderFactory.createEmptyBorder());


        timerOn.addMouseListener(this);




        base.add(timerLabel, Integer.valueOf(1));
        base.add(timerOn, Integer.valueOf(1));
        base.add(timerSet, Integer.valueOf(1));
    }

   private void setStartBtn(){
       startBtn = createButton("START",1);
       startBtn.setBounds(1200 *width/1440,890 *width/1440,190 *width/1440,55 *width/1440);
       startBtn.addMouseListener(this);
       base.add(startBtn, Integer.valueOf(1));
   }


    public static void main(String []args)
    {
        GameStart c = new GameStart();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==backBtn){
            frame.setVisible(false);
            System.out.println("yy");
            h.initializeWithUser(mainUser);
        }
        if (e.getSource()==blackBtn){
            blackBtn.setIcon(ic.resizeWithRatio(blackIconS));
            whiteBtn.setIcon(ic.resizeWithRatio(whiteIcon));
            selectedColor = false;
        }
        if (e.getSource()==whiteBtn){
            whiteBtn.setIcon(ic.resizeWithRatio(whiteIconS));
            blackBtn.setIcon(ic.resizeWithRatio(blackIcon));
            selectedColor = true;
        }
        if(e.getSource()==startBtn){
            //Checks if the timer is on or off so we choose to use the input or not
            if (timerOn.getSelectedObjects() == null) {
                timerInput = null;
            }
            else {
                timerInput = timerSet.getText();
            }
            if(gameRunning)return;
            else gameRunning = true;
            frame.setVisible(false);
            GameLauncher gL =new GameLauncher((selectedColor)?mainUser:oppUser,(selectedColor)?oppUser:mainUser);
        }
        if (e.getSource()==addPlayerBtn)
        {
            r.previousPage=1;
            r.initializeWithUser(mainUser);
            //User mainUser=user;
//            Register R = new Register(mainUser);
//            R.previousPage="GameStart";
//            R.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //submit action need to be changed
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
