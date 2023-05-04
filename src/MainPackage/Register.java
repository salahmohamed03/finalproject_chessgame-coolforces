package MainPackage;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Register extends JFrame implements MouseListener {
    public int previousPage  ;
    IconsAndColors ic = new IconsAndColors();
    public JFrame frame;

    public int width = ic.width, height = ic.height;
    public JLayeredPane base;
    public JPasswordField passField;
    public JTextField textField;

    public JButton submit;
    public JButton backBtn;

    List<JsonObject> dataObjs = new ArrayList<>();   
    Gson jsonArr = new GsonBuilder().setPrettyPrinting().create(); 
    JsonArray jsonArray = jsonArr.toJsonTree(dataObjs).getAsJsonArray();
    Gson gsonObj = new Gson(); // each user will be converted to a jsonobj
    JsonObject jsonObj =new JsonObject();

    FileWriter writer;
    //JsonWriter arrWriter=new JsonWriter(writer);
    //Gson gsonWriter =  new Gson(); // object used to writer the array

    public Register(){initialize();}

    public void initialize(){
        initializeWindow();
        setBackG();
        set_backBtn();
        setHeader();
        setFields();
        setBtn();

        frame.setVisible(true);

    }
    private void initializeWindow() {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //System.out.println(previousPage);
            if (previousPage == 0){
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }


        base = new JLayeredPane();
        frame.add(base);
    }
    private void setBackG(){
         ImageIcon backG_image = new ImageIcon("src/Mat/BackG/registerFields.png");
         JLabel backG = new JLabel(ic.resizeWithRatio(backG_image));
        backG.setBounds(0,0,width,height);
        base.add(backG, Integer.valueOf(0));
    }
    private void set_backBtn(){
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
    private void setHeader(){
        JLabel Header = new JLabel("REGISTER");
        Header.setHorizontalAlignment(SwingConstants.CENTER);
        Header.setFont(new Font("Space Grotesk", Font.BOLD, 100 *width/1440));
        Header.setForeground(ic.white);

        Header.setBounds(464*width/1440, 325*height/1024, 512*width/1440, 80*height/1024);
        base.add(Header, Integer.valueOf(1));

    }
    private void setBtn() {
        submit = createButton("Submit",1);
        submit.setBounds(626*width/1440, 720*height/1024, 188*width/1440, 52*height/1024);
        submit.addMouseListener(this);
        base.add(submit, Integer.valueOf(1));

    }
    public void setFields(){
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBounds(310*width/1440, 467*height/1024, 820*width/1440, 199*height/1024);
        fieldsPanel.setLayout(new GridLayout(2,1,0,58 *width/1440));


        JTextField usernameField = createTextField("username");
        JPasswordField passwordField = createPassField();
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordField);


        base.add(fieldsPanel, Integer.valueOf(1));
    }

    public JTextField createTextField(String placeHolder){
        textField = new JTextField(placeHolder);

        //text field design
        textField.setFont(new Font("Space Grotesk", Font.PLAIN, 50 *width/1440));
        textField.setBackground(ic.white);
        textField.setForeground(ic.mainColor);
        textField.setOpaque(true);
        textField.setBorder(BorderFactory.createEmptyBorder());

        return textField;
    }
    public JPasswordField createPassField(){
        passField = new JPasswordField("password",8);

        //text field design
        passField.setFont(new Font("Space Grotesk", Font.PLAIN, 50 *width/1440));
        passField.setBackground(ic.white);
        passField.setForeground(ic.mainColor);
        passField.setOpaque(true);
        passField.setBorder(BorderFactory.createEmptyBorder());

        return passField;
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
                button.setIcon(ic.resizeWithRatio(pinkBtn));
                button.setForeground(ic.white);
            }
            case 2 -> {
                button.setIcon(ic.resizeWithRatio(whiteBtn));
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


    public static void main(String []args)
    {
        Register c = new Register();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource() == backBtn){
            // 0 for login page
            // 1 for Game start
            if( previousPage == 0){
                System.out.println("tt");
            LoginPage L = new LoginPage();
            frame.setVisible(false);
            }
            if( previousPage == 1){
                frame.setVisible(false);
            }
        }

        //this block saves the data to a json file upon clicking on submit
        
        if(e.getSource()== submit)
        {
            if (!textField.getText().equals("username") &&
            !String.valueOf(passField.getPassword()).equals(String.valueOf("password")))
           {
               User user=new User(textField.getText(), passField.getPassword());
               jsonObj=gsonObj.toJsonTree(user).getAsJsonObject();
               jsonArray.add(jsonObj); 
               try {
                   writer=new FileWriter("data.json",true);
                   jsonArr.toJson(jsonArray, writer);
                   writer.close();
               } catch (Exception error) {
                   System.out.println("error happened");
                   error.getStackTrace();
               }
           }
        }
        
        //


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
