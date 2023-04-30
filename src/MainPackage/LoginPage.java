package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage extends JFrame {
    Color mainColor =  Color.decode("#FF006E");
    Color secondColor =  Color.decode("#AE2965");
    Color black =  Color.decode("#1B1725");
    Color white =  Color.decode("#FDFFFC");
    public JFrame frame;
    public int width, height;
    private ImageIcon backG_image = new ImageIcon("src/Mat/BackG/loginFields.png");
    private JLabel backG = new JLabel(backG_image);

    public JPasswordField passField;
    public JTextField textField;
    public JButton loginBtn;
    public JButton registerBtn;
    public JLabel placeHolder;

    public LoginPage(){
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

        frame.setVisible(true);
    }

    public void dispose (){
        frame.setVisible(false);
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
        textField.setFont(new Font("Space Grotesk", Font.PLAIN, 40));
        textField.setBackground(white);
        textField.setForeground(mainColor);
        textField.setOpaque(true);
        textField.setBorder(BorderFactory.createEmptyBorder());

        return textField;
    }

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
    private JButton createButton(String name, int btn){
        JButton button = new JButton(name);
        button.setFocusable(false);

        //design//
        button.setFont(new Font("Space Grotesk", Font.BOLD, 36));
        ImageIcon pinkBtn = new ImageIcon("src/Mat/Buttons/pinkBtn.png");
        ImageIcon whiteBtn = new ImageIcon("src/Mat/Buttons/whiteBtn.png");
        switch (btn) {
            case 1 -> {
                button.setIcon(pinkBtn);
                button.setForeground(white);
            }
            case 2 -> {
                button.setIcon(whiteBtn);
                button.setForeground(mainColor);
            }
        }
        button.setBackground(black);//any color. Just to be transparent
        button.setOpaque(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);

        button.setBorder(BorderFactory.createEmptyBorder());

        return button;
    }
    //check method and action listener
    public static void main(String []args)
    {
        LoginPage c = new LoginPage();
        //User player=new User(c.textField.getText(),c.passField.getPassword());
        
    }

}
