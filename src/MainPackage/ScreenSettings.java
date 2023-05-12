package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ScreenSettings extends Window {

    public JButton bigScreenSize;
    public JButton smallScreenSize;
    public JButton autoScreenSize;
    public JButton pinkColor;
    public JButton blueColor;

    public ScreenSettings() {
        initialize();
    }

    @Override
    protected void setupWindow() {
        setBackG("src/Mat/BackG/nGameSett.png");
        setHeader();
        set_backBtn();
        setBtns();
    }

    public void setHeader() {
        JLabel screenHeader = new JLabel("SCREEN SIZE");
        screenHeader.setHorizontalAlignment(SwingConstants.CENTER);
        screenHeader.setFont(new Font("Space Grotesk", Font.BOLD, 60*width/1440));
        screenHeader.setForeground(ic.white);

        screenHeader.setBounds(350*width/1440, 205*height/1024, 720*width/1440, 80*height/1024);
        base.add(screenHeader, Integer.valueOf(1));

        JLabel colorSelect = new JLabel("COLOR");
        colorSelect.setHorizontalAlignment(SwingConstants.CENTER);
        colorSelect.setFont(new Font("Space Grotesk", Font.BOLD, 60*width/1440));
        colorSelect.setForeground(ic.white);

        colorSelect.setBounds(350*width/1440, 500*height/1024, 720*width/1440, 50*height/1024);
        base.add(colorSelect, Integer.valueOf(1));
    }

    @Override
    public void setBtns() {
        JPanel btnsPanel = new JPanel();
        btnsPanel.setBackground(ic.black);
        btnsPanel.setOpaque(false);
        btnsPanel.setLayout(new GridLayout(1, 3, 22*width/1440, 0));
        btnsPanel.setBounds(200*width/1440, 300*height/1024, 1030*width/1440, 95*height/1024);

        bigScreenSize = createButton("1440 * 1024", 0);
        bigScreenSize.setFont(new Font("Space Grotesk", Font.BOLD, 40*width/1440));
        smallScreenSize = createButton("720 * 512", 0);
        smallScreenSize.setFont(new Font("Space Grotesk", Font.BOLD, 40*width/1440));
        autoScreenSize = createButton("AUTOMATIC", 0);
        autoScreenSize.setFont(new Font("Space Grotesk", Font.BOLD, 40*width/1440));

        btnsPanel.add(bigScreenSize);
        btnsPanel.add(smallScreenSize);
        btnsPanel.add(autoScreenSize);

        JPanel colorsPanel = new JPanel();
        colorsPanel.setBackground(ic.black);
        colorsPanel.setOpaque(false);
        colorsPanel.setLayout(new GridLayout(1, 2, 30*width/1440, 0));
        colorsPanel.setBounds(400*width/1440, 600*height/1024, 540, 120);

        ImageIcon pink =  new ImageIcon ("src/Mat/Buttons/color1.png");
        pinkColor = new JButton(ic.resizeWithRatio(pink));
        pinkColor.setOpaque(false);
        pinkColor.setBackground(ic.black);
        pinkColor.setBorder(BorderFactory.createEmptyBorder());
        pinkColor.addMouseListener(this);

        ImageIcon blue =  new ImageIcon ("src/Mat/Buttons/color2.png");
        blueColor = new JButton(ic.resizeWithRatio(blue));
        blueColor.setOpaque(false);
        blueColor.setBackground(ic.black);
        blueColor.setBorder(BorderFactory.createEmptyBorder());
        blueColor.addMouseListener(this);

        colorsPanel.add(pinkColor);
        colorsPanel.add(blueColor);

        base.add(btnsPanel, Integer.valueOf(2));
        base.add(colorsPanel, Integer.valueOf(2));
    }

    public static void main(String []args) {
        ScreenSettings test = new ScreenSettings();
    }

    @Override
    public void mouseClicked(MouseEvent e) {


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

