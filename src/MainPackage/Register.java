package MainPackage;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Register extends JFrame {
    Color mainColor =  Color.decode("#FF006E");
    Color secondColor =  Color.decode("#AE2965");
    Color black =  Color.decode("#1B1725");
    Color white =  Color.decode("#FDFFFC");
    public JFrame frame;
    private ImageIcon backG_image = new ImageIcon("src/Mat/BackG/registerFields.png");
    private JLabel backG = new JLabel(backG_image);
    public int width,height;
    public JPasswordField passField;
    public JTextField textField;

    public JButton submit;
    public JButton backBtn;

    public Register(){initialize();}

    public void initialize(){
        width = 1440;
        height = 1024;

        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle("Chess game");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        JLayeredPane base = new JLayeredPane();
        base.setBounds(0,0,width,height);


        backG.setBounds(0, 0, width, height);


       backBtn = create_backBtn();

        JPanel interactive= new JPanel();
            interactive.setOpaque(false);
            interactive.setBackground(mainColor);
            interactive.setBounds(310*width/1440,310*height/1024,820*width/1440,480*height/1024);
            interactive.setLayout(new BorderLayout(0,25));

                JLabel register = new JLabel("REGISTER");
                     register.setHorizontalAlignment(SwingConstants.CENTER);
                     register.setFont(new Font("Space Grotesk", Font.BOLD, 100));
                     register.setPreferredSize(new Dimension(0,138));
                     register.setForeground(white);
                     //register.setPreferredSize(new Dimension());

                 interactive.add(register,BorderLayout.NORTH);

                JPanel fields = new JPanel();
                    fields.setOpaque(false);

                    fields.setLayout(new GridLayout(2,1,0,75));

                        JTextField username = createTextField("username");
                        JPasswordField password = createPassField();

                    fields.add(username);
                    fields.add(password);

                 interactive.add(fields,BorderLayout.CENTER);

                submit = createButton("Submit", 1);
                submit.setPreferredSize(new Dimension(20,110));
                interactive.add(submit,BorderLayout.SOUTH);


        base.add(backG, Integer.valueOf(0));
        base.add(interactive, Integer.valueOf(1));
        base.add(backBtn, Integer.valueOf(1));

        frame.add(base);
        frame.setVisible(true);
    }
    public void dispose (){
        frame.setVisible(false);
    }


    public JTextField createTextField(String placeHolder){
        textField = new JTextField(placeHolder);

        //text field design
        textField.setFont(new Font("Space Grotesk", Font.PLAIN, 50));
        textField.setBackground(white);
        textField.setForeground(mainColor);
        textField.setOpaque(true);
        textField.setBorder(BorderFactory.createEmptyBorder());

        return textField;
    }
    public JPasswordField createPassField(){
        passField = new JPasswordField("password",8);

        //text field design
        passField.setFont(new Font("Space Grotesk", Font.PLAIN, 50));
        passField.setBackground(white);
        passField.setForeground(mainColor);
        passField.setOpaque(true);
        passField.setBorder(BorderFactory.createEmptyBorder());

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

    private JButton create_backBtn(){
        ImageIcon backImg = new ImageIcon("src/Mat/Buttons/backBtn.png");
        JButton backBtn = new JButton(backImg);
        backBtn.setOpaque(false);
        backBtn.setFocusable(false);
        backBtn.setBorderPainted(false);
        backBtn.setBackground(mainColor);
        backBtn.setBounds(46*width/1440,39*height/1024,65*width/1440,65*height/1024);
        return backBtn;
    }
}
