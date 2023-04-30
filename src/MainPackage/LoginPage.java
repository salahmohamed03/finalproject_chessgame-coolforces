package MainPackage;

import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
=======
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
>>>>>>> 4dc08973a6eef7dfeb99f189a950b589abc0c4da

public class LoginPage extends JFrame implements MouseListener {
    IconsAndColors ic = new IconsAndColors();
    public JFrame frame;
    public int width = 870, height = 1024 *width/1440;
    //int u = width/1440;

    public  JLayeredPane  base;


    public JPasswordField passField;
    public JTextField textField;

    public JPanel btns;
    public JButton loginBtn;
    public JButton registerBtn;
    public JLabel placeHolder;

    public LoginPage(){
        initialize();
    }

    public void  initialize(){

<<<<<<< HEAD
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        JLayeredPane base = new JLayeredPane();
            base.setBounds(0,0,width,height);
            backG.setBounds(0, 0, width, height);


        JPanel interactive= new JPanel();
            interactive.setOpaque(false);
            interactive.setBackground(black);
            interactive.setBounds(879*width/1440,236*height/1024,456*width/1440,484*height/1024);
            interactive.setLayout(new BorderLayout(0,32));

            //interactive content

        //JPanel interHead = new JPanel();
                JLabel loginHeader = new JLabel("Welcome");
                    loginHeader.setHorizontalAlignment(SwingConstants.CENTER);
                    loginHeader.setFont(new Font("Space Grotesk", Font.BOLD, 100));
                    loginHeader.setForeground(white);
                    loginHeader.setPreferredSize(new Dimension(0,155));


        interactive.add(loginHeader,BorderLayout.NORTH);

        JPanel interBody = new JPanel();
            interBody.setOpaque(false);
            interBody.setLayout(new GridLayout(2,1,0,55));
                    JTextField usernameField = createTextField();
                    JPasswordField passwordField = createPassField();
                    JLabel placeHolderlabel =createLabel();


            interBody.add(usernameField);
            interBody.add(placeHolderlabel);
            interBody.add(passwordField);


        interactive.add(interBody,BorderLayout.CENTER);




        JPanel interFoot = new JPanel();
            interFoot.setPreferredSize(new Dimension(0,80));//112
            interFoot.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
            interFoot.setOpaque(false);

                JButton loginBtn = createButton("Login", 1);
                JButton registerBtn = createButton("Register", 2);

            interFoot.add(loginBtn);
            interFoot.add(registerBtn);

        interactive.add(interFoot,BorderLayout.SOUTH);


        base.add(backG, Integer.valueOf(0));
        base.add(interactive, Integer.valueOf(1));

        //add to frame
        //frame.add(backG);
        //frame.add(createTextField("username"));
        //frame.add(createPassField());
        frame.add(base);
=======
        initializeWindow();
        setBackG();
        setHeader();
        setFields();
        setBtns();
        checkInput();
>>>>>>> 4dc08973a6eef7dfeb99f189a950b589abc0c4da

        frame.setVisible(true);
    }

    private void initializeWindow() {
        frame = new JFrame();
        frame.setSize(width , height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        base = new JLayeredPane();
        frame.add(base);
    }

    private void setBackG(){
         ImageIcon backG_image = new ImageIcon("src/Mat/BackG/loginFields.png");
         JLabel backG = new JLabel(resizeWithRatio(backG_image));
        backG.setBounds(0,0,width,height);
        base.add(backG, Integer.valueOf(0));
    }

    public void setHeader(){
                //LOOK
            JLabel loginHeader = new JLabel("LOGIN");
                 loginHeader.setHorizontalAlignment(SwingConstants.CENTER);
                 loginHeader.setFont(new Font("Space Grotesk", Font.BOLD, 100 *width/1440));
                 loginHeader.setForeground(ic.white);

            loginHeader.setBounds(941*width/1440, 277*height/1024, 320*width/1440, 80*height/1024);
            base.add(loginHeader, Integer.valueOf(1));
        }


    public void setFields(){
        JPanel fieldsPanel = new JPanel();
            fieldsPanel.setOpaque(false);
            fieldsPanel.setBounds(879*width/1440, 422*height/1024, 456*width/1440, 190*height/1024);
            fieldsPanel.setLayout(new GridLayout(2,1,0,55*width/1440));


            JTextField usernameField = createTextField("username");
            JPasswordField passwordField = createPassField();
                fieldsPanel.add(usernameField);
                fieldsPanel.add(passwordField);


        base.add(fieldsPanel, Integer.valueOf(1));
    }


    public JTextField createTextField(){
        textField = new JTextField("Username");
        //function to check if the initial value has changed
        // It first checks if value of text has changed and then checks if user pressed enter
        //boolean saveAllowed=false;
        String initialText=textField.getText();
        textField.addActionListener(
            e -> {
                if (!textField.getText().equals(initialText)) {
                    textField.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER) 
                            {
                                String text = textField.getText();
                                //System.out.println(text);
                            }
                        }
                    });
                }
            }
        );

        //textField.setFocusable(false);
        //textfield actionlistener for placeHolder
        textField.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e) 
            {
                if (textField.getText().equals("Username")) {
                    textField.setText("");
                    //textField.setForeground(Color.BLACK);
                }
            }   

            @Override
            public void focusLost(FocusEvent e) 
            {
                if (textField.getText().isEmpty()) {
                    textField.setText("Username");
                    textField.setForeground(mainColor);
                }
            }
            });

        
        //text field design
        textField.setFont(new Font("Space Grotesk", Font.PLAIN, 40*width/1440));
        textField.setBackground(ic.white);
        textField.setForeground(ic.mainColor);
        textField.setOpaque(true);
        textField.setBorder(BorderFactory.createEmptyBorder());

        return textField;
    }

<<<<<<< HEAD
    //Create a placeHolder
    public JLabel createLabel()
    {
        //placeHolder.setFocusable(false);
        placeHolder=new JLabel();
        placeHolder.setForeground(mainColor);
        return placeHolder;
    }

    public JPasswordField createPassField(){
        passField = new JPasswordField();

        //passfield design
        passField.setFont(new Font("Space Grotesk", Font.PLAIN, 40));
        passField.setBackground(white);
        passField.setForeground(mainColor);
=======
        //text field design
        passField.setFont(new Font("Space Grotesk", Font.PLAIN, 40*width/1440));
        passField.setBackground(ic.white);
        passField.setForeground(ic.mainColor);
>>>>>>> 4dc08973a6eef7dfeb99f189a950b589abc0c4da
        passField.setOpaque(true);
        passField.setBorder(BorderFactory.createEmptyBorder());
        passField.setText("Password"); // set the initial text
        passField.setEchoChar('\u2022'); // disables masking
        passField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // clear the password field and temporarily disable masking
                passField.setText("");
                passField.setEchoChar('\u2022');
            }
        
            @Override
            public void focusLost(FocusEvent e) {
                // restore the masking and initial text
                passField.setEchoChar('\u0000'); //restores masking
                passField.setText("Password");
            }
        });


        return passField;
    }
    public void setBtns(){
        btns =  new JPanel();
        btns.setBackground(ic.black);
        btns.setOpaque(false);
        btns.setLayout(new GridLayout(1,2,10*width/1440,0));
        btns.setBounds(870*width/1440, 668*height/1024, 470*width/1440, 60*height/1024);

        loginBtn = createButton("Login",1);
        loginBtn.addMouseListener(this);

        registerBtn = createButton("Register",2);
        registerBtn.addMouseListener(this);

        btns.add(loginBtn); btns.add(registerBtn);

        base.add(btns, Integer.valueOf(2));
    }
    private JButton createButton(String name, int btn){
        JButton button = new JButton(name);
        button.setFocusable(false);

        //design//
        button.setFont(new Font("Space Grotesk", Font.BOLD, 36*width/1440));
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

    public void checkInput(){
        //should be if statement
            denyAccess();
    }

    public void denyAccess(){
        JLabel incrorrect = new JLabel("Credentials are incorrect");
            incrorrect.setHorizontalAlignment(SwingConstants.CENTER);
            incrorrect.setFont(new Font("Space Grotesk", Font.BOLD, 25*width/1440));
            incrorrect.setForeground(ic.mainColor);

            incrorrect.setBounds(920*width/1440, 750*height/1024, 382*width/1440, 25*height/1024);
        base.add(incrorrect, Integer.valueOf(1));

    }
    public ImageIcon resizeWithRatio(ImageIcon icon){
        ImageIcon resized = ic.Resize(icon,icon.getIconWidth()*width/1440, icon.getIconHeight()*width/1440);

        return resized;
    }

    public static void main(String []args)
    {
        LoginPage c = new LoginPage();
        //User player=new User(c.textField.getText(),c.passField.getPassword());
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==loginBtn){
            //check
            HomePage H = new HomePage();
        }
        if (e.getSource()==registerBtn){
            Register R = new Register();
            R.previousPage=0;
        }
        frame.setVisible(false);
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
