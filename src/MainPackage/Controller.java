package MainPackage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {

    public static void main(String[] args) {
        IconsAndColors icC = new IconsAndColors();
        LoginPage loginControl = new LoginPage();
        Register registerControl = new Register();
        HomePage homePageControl = new HomePage();
        History historyControl = new History();
        GameStart gameStartControl = new GameStart();
        Settings settingsControl = new Settings();
        GameActions.ic=icC;
       // GameLauncher gameLauncherControl = new GameLauncher(); //see it in the GAME LAUNCHER

//        GameActions gameActionsControl = new GameActions();

        setLink(loginControl , registerControl, icC , homePageControl , gameStartControl, historyControl, settingsControl );


        loginControl.initialize();

        System.out.println("done");


    }
    private static void setLink(LoginPage loginLink, Register registerLink, IconsAndColors iconsColorsLink,HomePage homepageLink , GameStart gameStartLink ,  History historyLink, Settings settingsLink){
        //XC for the page in this method
        //.x small is th page referance in each window
        // names will be change soon but to make it easier in typing
        loginLink.ic = registerLink.ic= homepageLink.ic= gameStartLink.ic=  historyLink.ic=settingsLink.ic= iconsColorsLink;
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
