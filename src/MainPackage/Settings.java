package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Settings extends Window {

    public JButton bigScreenSize;
    public JButton smallScreenSize;
    public JButton autoScreenSize;


//    public Settings() {
//        initialize();
//    }

    @Override
    protected void setupWindow() {
        setBackG("src/Mat/BackG/nGameSett.png");
        setHeader();
        set_backBtn();
        setBtns();
    }

    private void setHeader() {
        JLabel screenHeader = new JLabel("SCREEN SIZE");
        setHeaderLook(screenHeader);

        screenHeader.setBounds(350*width/1440, 205*width/1440, 720 *width/1440, 80*width/1440);
        base.add(screenHeader, Integer.valueOf(1));

        JLabel colorSelect = new JLabel("COLOR");
        setHeaderLook(colorSelect);

        colorSelect.setBounds(350*width/1440, 500*width/1440, 720*width/1440, 50*width/1440);
        base.add(colorSelect, Integer.valueOf(1));
    }
    private void setHeaderLook(JLabel Header){
        Header.setHorizontalAlignment(SwingConstants.CENTER);
        Header.setFont(new Font("Space Grotesk", Font.BOLD, 60*width/1440));
        Header.setForeground(ic.white);
    }

    @Override
    public void setBtns() {
        setScreenBtns();
        setColorBtns();
    }
    private void setScreenBtns(){
        JPanel btnsPanel = new JPanel();
        btnsPanel.setBackground(ic.black);
        btnsPanel.setOpaque(false);
        btnsPanel.setLayout(new GridLayout(1, 3, 22*width/1440, 0));
        btnsPanel.setBounds(200*width/1440, 300*width/1440, 1030*width/1440, 95*width/1440);

        bigScreenSize = createButton("1440 * 1024", 0);
        bigScreenSize.setFont(new Font("Space Grotesk", Font.BOLD, 40*width/1440));
        smallScreenSize = createButton("720 * 512", 0);
        smallScreenSize.setFont(new Font("Space Grotesk", Font.BOLD, 40*width/1440));
        autoScreenSize = createButton("AUTOMATIC", 0);
        autoScreenSize.setFont(new Font("Space Grotesk", Font.BOLD, 40*width/1440));

        btnsPanel.add(bigScreenSize);
        btnsPanel.add(smallScreenSize);
        btnsPanel.add(autoScreenSize);

        base.add(btnsPanel, Integer.valueOf(2));
    }
    private void setColorBtns(){
        JPanel colorsPanel = new JPanel();
        colorsPanel.setBackground(ic.black);
        colorsPanel.setOpaque(false);
        colorsPanel.setLayout(new GridLayout(1, 2, 30 *width/1440, 0));
        colorsPanel.setBounds(460*width/1440, 600*width/1440, 540*width/1440, 150*width/1440);

        ImageIcon pink =  new ImageIcon ("src/Mat/Buttons/color1.png");
        pinkColor = new JButton(ic.resizeWithRatio(pink));
        setColorBtnLook(pinkColor);

        ImageIcon blue =  new ImageIcon ("src/Mat/Buttons/color2.png");
        blueColor = new JButton(ic.resizeWithRatio(blue));
        setColorBtnLook(blueColor);

        colorsPanel.add(pinkColor);
        colorsPanel.add(blueColor);

        base.add(colorsPanel, Integer.valueOf(2));

    }
    private void setColorBtnLook(JButton btn){
        btn.setOpaque(false);
        btn.setFocusable(false);
        btn.setBackground(ic.black);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.addMouseListener(this);


    }

    public static void main(String []args) {
        Settings test = new Settings();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==backBtn){
            frame.setVisible(false);
            h.initializeWithUser(mainUser);
        }
        //SCREEN SIZE
        if(e.getSource() == bigScreenSize){

            dispose();
            ic.height=1024;
            ic.width= 1440;
            System.out.println("big screen");
            frame.setVisible(true);

        } else if(e.getSource() == smallScreenSize){
            ic.height=512;
            ic.width= 720;

        }else if(e.getSource() == autoScreenSize){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            ic.height = (int) screenSize.getHeight();
            ic.width = 1440 *ic.height/1024 ;
        }
        //COLORS SELECTION
        if(e.getSource() == pinkColor){
            System.out.println("pink press");
            frame.setVisible(false);
            ic.mainColor=new Color(0x3F0D2B);
            frame.setVisible(true);

        } else if(e.getSource() == blueColor){
            frame.setVisible(false);
            ic.mainColor=new Color(0x0F1194);
            frame.setVisible(true);

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

