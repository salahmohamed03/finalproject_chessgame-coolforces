package MainPackage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {

    static IconsAndColors icC = new IconsAndColors();
    LoginPage loginControl = new LoginPage();
    Register registerControl = new Register();
    HomePage homePageControl = new HomePage();
    History historyControl = new History();
    GameStart gameStartControl = new GameStart();
    static Settings settingsControl = new Settings();
    ChessBoard chessBoardControl;
    public static void main(String[] args) {
        IconsAndColors icC = new IconsAndColors();
        LoginPage loginControl = new LoginPage();
        Register registerControl = new Register();
        HomePage homePageControl = new HomePage();
        History historyControl = new History();
        GameStart gameStartControl = new GameStart();
        Settings settingsControl = new Settings();
        ChessBoard chessBoardControl;

       // GameLauncher gameLauncherControl = new GameLauncher(); //see it in the GAME LAUNCHER
        GameActions gameActionsControl = new GameActions();

        setLink(loginControl , registerControl, icC , homePageControl , gameStartControl, gameActionsControl, historyControl, settingsControl );

//        icC.mainColor=new Color(44, 98, 12);
//        icC.white= new Color(75, 71, 71);

        loginControl.initialize();
        System.out.println("done");


    }
//    protected static void setSettingsBtns(){
//        if(settingsControl.selectedSize==1){
//            icC.height=1024;
//            icC.width= 1440;
//            System.out.println("big screen");
//
//        } else if(settingsControl.selectedSize==2){
//            icC.height=512;
//            icC.width= 720 ;
//            System.out.println("small");
//
//        }else if(settingsControl.selectedSize==0){
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            icC.height = (int) screenSize.getHeight();
//            icC.width = 1440 *icC.height/1024 ;
//
//        }
//        //COLORS SELECTION
//        if(settingsControl.selectedColor==0){
//            System.out.println("pink press");
//            settingsControl.frame.setVisible(false);
//            icC.mainColor=new Color(0x3F0D2B);
//            settingsControl.frame.setVisible(true);
//
//        } else if(settingsControl.selectedColor==2){
//            settingsControl.frame.setVisible(false);
//            icC.mainColor=new Color(0x0F1194);
//            settingsControl.frame.setVisible(true);
//        }
//    }
    private static void setLink(LoginPage loginLink, Register registerLink, IconsAndColors iconsColorsLink,HomePage homepageLink , GameStart gameStartLink , GameActions gameActionsLink, History historyLink, Settings settingsLink){
        //XC for the page in this method
        //.x small is th page referance in each window
        // names will be change soon but to make it easier in typing
        loginLink.ic = registerLink.ic= homepageLink.ic= gameStartLink.ic= gameActionsLink.ic = historyLink.ic=settingsLink.ic= iconsColorsLink;
        homepageLink.g = registerLink.g =  gameStartLink;
//        ChessBoard.setGameStart(gameStartLink);
        loginLink.h=registerLink.h=gameStartLink.h=settingsLink.h= homepageLink;
        homepageLink.hist = historyLink;
        registerLink.l= homepageLink.l = loginLink;
        loginLink.r = homepageLink.r= gameStartLink.r = registerLink;
        ChessBoardBASE.setIconsAndColors(iconsColorsLink);
        ChessBoard.gs= gameStartLink;
        homepageLink.s=settingsLink;

//        gameStartLink.gL= gameActionsLink.gl=gameLauncherLink;
//        gameLauncherLink.actions = gameActionsLink;

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
