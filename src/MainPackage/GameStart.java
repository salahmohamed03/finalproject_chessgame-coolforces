package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameStart extends JFrame implements MouseListener {
    IconsAndColors ic = new IconsAndColors();

    public JFrame frame;

    public int width = ic.width, height = ic.height;
    public JLayeredPane base;
    public JButton backBtn;

    public ImageIcon whiteIcon = new ImageIcon("src/Mat/Buttons/selectWBtn.png");
    public ImageIcon whiteIconS = new ImageIcon("src/Mat/Buttons/selectedWBtn.png");
    public ImageIcon blackIcon = new ImageIcon("src/Mat/Buttons/selectBBtn.png");
    public ImageIcon blackIconS = new ImageIcon("src/Mat/Buttons/selectedBBtn.png") ;

    JRadioButton whiteBtn;
    JRadioButton blackBtn;
    public JButton addPlayerBtn;
    public JTextField timerSet;
    public JCheckBox timerOn;
    public JButton startBtn;

    public GameStart(){initialize();}

    public void initialize(){

        initializeWindow();
        setBackG();
        set_backBtn();
        setHeader();
        setBlackOrWhite();
        setOpponentPanel();
        setTimerPanel();
        setStartBtn();

        frame.add(base);
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
    private void setBackG(){
         ImageIcon backG_image = new ImageIcon("src/Mat/BackG/nGameSettFields.png");
         JLabel backG = new JLabel(resizeWithRatio(backG_image));
        backG.setBounds(0,0,width,height);
        base.add(backG, Integer.valueOf(0));
    }
    private void set_backBtn(){
        ImageIcon backImg = new ImageIcon("src/Mat/Buttons/backBtn.png");
        backBtn = new JButton(resizeWithRatio(backImg));
        backBtn.setOpaque(false);
        backBtn.setFocusable(false);
        backBtn.setBorderPainted(false);
        backBtn.setBackground(ic.mainColor);

        backBtn.addMouseListener(this);
        backBtn.setBounds(46*width/1440,39*height/1024,65*width/1440,65*height/1024);

        base.add(backBtn, Integer.valueOf(1));

    }

    private void setHeader(){
        JLabel youPlay = createLabel("You play as", 83 );
        youPlay.setBounds(470 *width/1440,81 *width/1440,545 *width/1440,132 *width/1440);
        base.add(youPlay, Integer.valueOf(1));

    }

    private void setBlackOrWhite(){
        ButtonGroup wORb = new ButtonGroup();

        whiteBtn = new JRadioButton();
        whiteBtn.setIcon(resizeWithRatio(whiteIcon));
        whiteBtn.setBounds(186 *width/1440,227 *width/1440,508 *width/1440,400 *width/1440);
        whiteBtn.setOpaque(false);


        blackBtn = new JRadioButton();
        blackBtn.setIcon(resizeWithRatio(blackIcon));
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
        String[] players = {"player1","player2","player3"};
        JComboBox playerList = new JComboBox(players);
        playerList.setBounds(765 *width/1440,685 *width/1440,340 *width/1440,50 *width/1440);
        playerList.setFont(new Font("Space Grotesk", Font.BOLD, 20 *width/1440));
        playerList.setForeground(ic.mainColor);
        playerList.setBackground(ic.white);

        ImageIcon add =  new ImageIcon ("src/Mat/Buttons/addBtn.png");
        addPlayerBtn = new JButton(resizeWithRatio(add));
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

        timerSet = createTextField("00:00:00");
        timerSet.setBounds(828 *width/1440,770 *width/1440,171 *width/1440,40 *width/1440);

        ImageIcon on =  new ImageIcon ("src/Mat/Buttons/timerOnBtn.png");
        ImageIcon off =  new ImageIcon ("src/Mat/Buttons/timerOffBtn.png");
        timerOn = new JCheckBox(resizeWithRatio(off));
        timerOn.setSelectedIcon(resizeWithRatio(on));
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

    private JButton create_backBtn(){
        ImageIcon backImg = new ImageIcon("src/Mat/Buttons/backBtn.png");
        JButton backBtn = new JButton(resizeWithRatio(backImg));
        backBtn.setOpaque(false);
        backBtn.setFocusable(false);
        backBtn.setBorderPainted(false);
        backBtn.setBackground(ic.mainColor);
        backBtn.setBounds(46*width/1440,39*height/1024,65*width/1440,65*height/1024);
        return backBtn;
    }
    private  JLabel createLabel(String text, int s){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Space Grotesk", Font.BOLD, s *width/1440));
        label.setForeground(ic.white);
        return label;
    }
    public JTextField createTextField(String placeHolder){
        JTextField textField = new JTextField(placeHolder);

        //text field design
        textField.setFont(new Font("Space Grotesk", Font.PLAIN, 40 *width/1440));
        textField.setBackground(ic.white);
        textField.setForeground(ic.mainColor);
        textField.setOpaque(true);
        textField.setBorder(BorderFactory.createEmptyBorder());

        return textField;
    }
    private JButton createButton(String name, int btn){
        JButton button = new JButton(name);
        button.setFocusable(false);

        //design//
        button.setFont(new Font("Space Grotesk", Font.BOLD, 36 *width/1440));
        ImageIcon pinkBtn = new ImageIcon("src/Mat/Buttons/pinkBtn.png");
        ImageIcon whiteBtn = new ImageIcon("src/Mat/Buttons/whiteBtn.png");
        switch (btn) {
            case 1 -> {
                button.setIcon(resizeWithRatio(pinkBtn));
                button.setForeground(ic.white);
            }
            case 2 -> {
                button.setIcon(resizeWithRatio(whiteBtn));
                button.setForeground(ic.mainColor);
            }
        }
        button.setBackground(ic.black);//any color. Just to be transparent
        button.setOpaque(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);

        button.setBorder(BorderFactory.createEmptyBorder());

        return button;
    }
    public ImageIcon resizeWithRatio(ImageIcon icon){
        ImageIcon resized = ic.Resize(icon,icon.getIconWidth()*width/1440, icon.getIconHeight()*width/1440);

        return resized;
    }
    public static void main(String []args)
    {
        GameStart c = new GameStart();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==blackBtn){
            blackBtn.setIcon(resizeWithRatio(blackIconS));
            whiteBtn.setIcon(resizeWithRatio(whiteIcon));
        }
        if (e.getSource()==whiteBtn){
            whiteBtn.setIcon(resizeWithRatio(whiteIconS));
            blackBtn.setIcon(resizeWithRatio(blackIcon));
        }
        if(e.getSource()==backBtn){                     // need to be changed so do not create a new page IT IS JUST FOR GUI SHOW
            HomePage H = new HomePage();
            frame.setVisible(false);
        }
        if(e.getSource()==startBtn){
            ChessBoard C = new ChessBoard();
            frame.setVisible(false);
        }
        if (e.getSource()==addPlayerBtn){
            Register R = new Register();
            R.previousPage = 1;  //should be in main
            R.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //submit action need to be changed
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
