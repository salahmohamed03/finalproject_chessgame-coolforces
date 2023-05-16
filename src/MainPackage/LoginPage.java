package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginPage extends Window implements MouseListener {

//    Register r ;
//    HomePage h ;

    public JTextField usernameField;
    public JPasswordField passwordField;

    public JButton loginBtn;
    public JButton registerBtn;
//    public LoginPage(){
//        initialize();
//    }

    @Override
    protected void setupWindow() {
        setBackG(ic.colorPath+"BackG/loginFields.png");
        setHeader();
        setFields();
        setBtns();
//        checkInput();
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


         usernameField = createTextField("username", 40);
         passwordField = createPassField();

         //function to prevent Spaces
         passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    e.consume();
                    System.out.println("Spaces are not allowed in password"); 
                    //ya talalinho 7ot deh fel gui
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordField);


        base.add(fieldsPanel, Integer.valueOf(1));
    }



    @Override
    public void setBtns(){
        JPanel btnsPanel = new JPanel();
        btnsPanel.setBackground(ic.black);
        btnsPanel.setOpaque(false);
        btnsPanel.setLayout(new GridLayout(1,2,10*width/1440,0));
        btnsPanel.setBounds(870*width/1440, 668*height/1024, 470*width/1440, 60*height/1024);

        loginBtn = createButton("Login",1);
        registerBtn = createButton("Register",2);

        btnsPanel.add(loginBtn); btnsPanel.add(registerBtn);

        base.add(btnsPanel, Integer.valueOf(2));
    }
//    public void checkInput(){
//        //should be if statement
//        denyAccess();
//    }

    public void denyAccess(){
        JLabel incrorrect = new JLabel("Credentials are incorrect");
        incrorrect.setHorizontalAlignment(SwingConstants.CENTER);
        incrorrect.setFont(new Font("Space Grotesk", Font.BOLD, 25*width/1440));
        incrorrect.setForeground(ic.mainColor);

        incrorrect.setBounds(920*width/1440, 750*height/1024, 382*width/1440, 25*height/1024);
        base.add(incrorrect, Integer.valueOf(1));

    }

    public static void main(String []args)
    {
        LoginPage c = new LoginPage();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource()==loginBtn){
            //this user is simply the one logging in and will always be the main user
            // because no need for the user who wants to be added from gamestart to log in
            // If he already exists, his name will be in the combobox
            String passwordStr = new String (passwordField.getPassword());
            User logUser=new User(usernameField.getText(),passwordStr);
            if (checkCredentials(logUser.getName().trim() , logUser.getPass()))
            {
                frame.setVisible(false);
                System.out.println("true");
                h.initializeWithUser(logUser);
            }
            else
            {
                //frame.setVisible(true);
                denyAccess();
                System.out.println("deny");
            }
            
        }
        if (e.getSource()==registerBtn){
            frame.setVisible(false);
            r.initialize();
            r.previousPage=0;
            System.out.println(r.previousPage);
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
