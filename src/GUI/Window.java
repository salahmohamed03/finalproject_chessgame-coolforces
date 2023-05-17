package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import data.*;
import gamePlay.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class Window extends dataHandling implements MouseListener {
    public IconsAndColors ic = new IconsAndColors();
    public LoginPage loginLocal;
    public Register registerLocal;
    public HomePage homeLocal;
    public GameStart gameStartLocal;
    public Settings settingsLocal;
    public History historyLocal;
    public JButton pinkColor;
    public JButton blueColor;
    public JFrame frame;
    public int width, height;
//    int u = width/1440;

    protected    JLayeredPane  base;


    protected ImageIcon backG_image;
    protected JLabel backG;
    protected JButton backBtn;
    public User mainUser;


    private int refDim = 1440;

    protected void initialize(){
        width = ic.width; height = ic.height;
        initializeFrame();
        setupWindow();
        putBackG();
        frame.setVisible(true);
    }

    public void initializeWithUser(User mainUser)
    {
        this.mainUser=mainUser;
        initialize();
    }
    abstract protected void setupWindow();

    protected void initializeFrame() {
        frame = new JFrame();
        frame.setSize(width , height);
        frame.setTitle("7ambolla");
        frame.setIconImage(new ImageIcon (ic.colorPath+"Comp/main/logo.png").getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        base = new JLayeredPane();
        frame.add(base);
    }
    public void setBackG(String fileName){
        backG_image = new ImageIcon(fileName);
    }

    protected void putBackG(){
        backG = new JLabel(ic.resizeWithRatio(backG_image, refDim));
        if(refDim==870)
            backG.setBounds(-10,-5,width,height);
        else
            backG.setBounds(0,0,width,height);
        base.add(backG, Integer.valueOf(0));
        refDim = 1440;
    }

    public abstract void setBtns();
    public JTextField createTextField(String placeHolder , int size)
    {
        JTextField textField = new JTextField(placeHolder);

        //function to control the behavior of the place holder
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e)
            {
                if (textField.getText().equals(placeHolder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the placeholder text if no text is entered
                if (textField.getText().isEmpty()) {
                    textField.setText(placeHolder);
                }
            }
        });


        //text field design Login 40 size
        //Reg 50 size
        textField.setFont(new Font("Space Grotesk", Font.PLAIN, size *width/1440));
        textField.setBackground(ic.white);
        textField.setForeground(ic.mainColor);
        textField.setOpaque(true);
        textField.setBorder(BorderFactory.createEmptyBorder());

        return textField;
    }
    public JPasswordField createPassField(){
        JPasswordField passField = new JPasswordField("password",8);
        passField.setEchoChar((char)0);
        //text field design
        passField.setFont(new Font("Space Grotesk", Font.PLAIN, 40*width/1440));
        passField.setBackground(ic.white);
        passField.setForeground(ic.mainColor);
        passField.setOpaque(true);
        passField.setBorder(BorderFactory.createEmptyBorder());

        //function to prevent Spaces
        passField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        //function to manage the masking of the password placeholder

        passField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String password = new String(passField.getPassword());
                if (password.equals("password")) {
                    passField.setText(""); // Clear the text field if the default value "password" is present
                }
                passField.setEchoChar('\u25CF'); // Set the echo character to a bullet (●) to mask the password
            }

            @Override
            public void focusLost(FocusEvent e) {
                String password = new String(passField.getPassword());
                if (password.isEmpty()) {
                    passField.setEchoChar((char) 0); // Set the echo character to 0 to demask the password if the field is empty
                    passField.setText("password"); // Set the default value "password" if the field is empty
                }
            }
        });




        return passField;
    }

    protected   JLabel createLabel(String text, int s){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Space Grotesk", Font.BOLD, s *width/1440));
        label.setForeground(ic.white);
        return label;
    }

    protected JButton createButton(String name, int type){
        JButton button = new JButton(name);
        button.setFocusable(false);

        //design//
        button.setFont(new Font("Space Grotesk", Font.BOLD, 36*width/1440));
        ImageIcon settingsBtn = new ImageIcon(ic.colorPath+"Buttons/settingsBtn.png");
        ImageIcon pinkBtn = new ImageIcon(ic.colorPath+"Buttons/pinkBtn.png");
        ImageIcon whiteBtn = new ImageIcon(ic.colorPath+"Buttons/whiteBtn.png");
        ImageIcon homeBtn = new ImageIcon(ic.colorPath+"Buttons/pinkBtn2.png");

        switch (type) {
            case 0 -> {
                button.setIcon(ic.resizeWithRatio(settingsBtn));
                button.setForeground(ic.white);
            }
            case 1 -> {
                button.setIcon(ic.resizeWithRatio(pinkBtn));
                button.setForeground(ic.white);
            }
            case 2 -> {
                button.setIcon(ic.resizeWithRatio(whiteBtn));
                button.setForeground(ic.mainColor);
            }
            case 3 ->{
                button.setFont(new Font("Space Grotesk", Font.BOLD, 55*width/1440));
                button.setIcon(ic.resizeWithRatio(homeBtn));
                button.setForeground(ic.white);
            }
        }
        button.setBackground(ic.black);//any color. Just to be transparent
        button.setOpaque(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);

        button.setBorder(BorderFactory.createEmptyBorder());
        button.addMouseListener(this);

        return button;
    }
    protected void set_backBtn(){
        ImageIcon backImg = new ImageIcon("src/Mat/Buttons/backBtn.png");
        backBtn = new JButton(ic.resizeWithRatio(backImg));
        backBtn.setOpaque(false);
        backBtn.setFocusable(false);
        backBtn.setBorderPainted(false);
        backBtn.setBackground(ic.mainColor);

        backBtn.addMouseListener(this);
        backBtn.setBounds(46*width/1440,39*height/1024,65*width/1440,65*height/1024);

        base.add(backBtn, Integer.valueOf(1));

    }
}
