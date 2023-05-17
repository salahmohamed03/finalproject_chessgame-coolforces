package MainPackage;

import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.READER;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//imports needed to deal with JSON
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Register extends Window implements MouseListener{

    public int previousPage;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton submit;
    protected JLabel incrorrect;


    @Override
    protected void setupWindow() {
        setBackG(ic.colorPath+"BackG/registerFields.png");
        set_backBtn();
        setHeader();
        setFields();
        setBtns();

        denyAccess();

    }

    @Override
    public void setBtns() {

        submit = createButton("Submit",1);
        submit.requestFocusInWindow();
        submit.setBounds(626*width/1440, 720*height/1024, 188*width/1440, 52*height/1024);
        submit.addMouseListener(this);
        base.add(submit, Integer.valueOf(1));
    }


    private void setHeader(){
        JLabel Header = new JLabel("REGISTER");
        Header.setHorizontalAlignment(SwingConstants.CENTER);
        Header.setFont(new Font("Space Grotesk", Font.BOLD, 100 *width/1440));
        Header.setForeground(ic.white);

        Header.setBounds(464*width/1440, 325*height/1024, 512*width/1440, 80*height/1024);
        base.add(Header, Integer.valueOf(1));

    }
    public void setFields(){
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBounds(310*width/1440, 468*height/1024, 820*width/1440, 199*height/1024);
        fieldsPanel.setLayout(new GridLayout(2,1,0,58 *width/1440));


        usernameField = createTextField("username", 50);
        passwordField = createPassField();

        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordField);


        base.add(fieldsPanel, Integer.valueOf(1));
    }
    protected void denyAccess(){
        incrorrect = new JLabel();
        incrorrect.setHorizontalAlignment(SwingConstants.CENTER);
        incrorrect.setFont(new Font("Space Grotesk", Font.BOLD, 25*width/1440));
        incrorrect.setForeground(ic.white);

        incrorrect.setBounds(310*width/1440, 655*height/1024, 820*width/1440, 80*height/1024);
        base.add(incrorrect, Integer.valueOf(2));

    }


    public static void main(String []args)
    {
        Register c = new Register();
        c.previousPage= 0;
        System.out.println(c.previousPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource() == backBtn){
            // 0 for login page
            // 1 for Game start
            frame.setVisible(false);
            if( previousPage==0)
            {
                loginLocal.initialize();
            }
            if( previousPage==1)
            {
                this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }

        //this block saves the data to a json file upon clicking on submit
        ///////we should tell the user not to include spaces as they will be already neglected
        if(e.getSource()== submit)
        {
            if (valid())
            {
                String passwordStr = new String (passwordField.getPassword());
                User regUser=new User(usernameField.getText().trim() , passwordStr);
                if (checkCredentials(regUser.getName(), regUser.getPass()))
                {
                    incrorrect.setText("This player already exists.Login instead.");
                }
                else if (checkUsername(regUser.getName()))
                {
                    addAndWriteNewData(regUser);
                    //g.initializeWithUser(mainUser);
                    // If coming from gamestart main user is passed again to keep his authority
                    //and the regUser is also passed as an opponent
                    frame.setVisible(false);
                    if (previousPage==1)
                    {
                        //program will go to gameStart again
                        System.out.println(mainUser.getName());
                        gameStartLocal.initializeWithUser(mainUser);
                    }
                    else  // here this is the typical registration
                    //the regUser will be the mainUser
                    {
                        homeLocal.initializeWithUser(regUser);
                    }
                    this.dispose();
                }
                else
                {
                    incrorrect.setText("Username is taken.Try using another username");
                }
            }
            else {
                incrorrect.setText("Fields cannot be empty");
            }
        }
    }



    //function to check the validity of the submission
    public boolean valid()
    {
        String comparedPass= new String (passwordField.getPassword());
        if (!usernameField.getText().equals("username") && !usernameField.getText().isEmpty())
        {
            if (!comparedPass.isEmpty() && !comparedPass.equals("password"))
            {
                return true;
            }
        }
        return false;
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
