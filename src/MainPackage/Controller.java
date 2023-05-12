package MainPackage;

import java.awt.*;

public class Controller {


    public static void main(String[] args) {
        IconsAndColors icC = new IconsAndColors();
        LoginPage loginControl = new LoginPage();
        Register registerControl = new Register();
        HomePage homePageControl = new HomePage();
        History historyControl = new History();
        GameStart gameStartControl = new GameStart();
        ChessBoard chessBoardControl;

       // GameLauncher gameLauncherControl = new GameLauncher(); //see it in the GAME LAUNCHER
        GameActions gameActionsControl = new GameActions();

        setLink(loginControl , registerControl, icC , homePageControl , gameStartControl, gameActionsControl, historyControl );
        icC.mainColor=new Color(44, 98, 12);
        loginControl.initialize();

    }
    private static void setLink(LoginPage loginLink, Register registerLink, IconsAndColors iconsColorsLink,HomePage homepageLink , GameStart gameStartLink , GameActions gameActionsLink, History historyLink){
        //XC for the page in this method
        //.x small is th page referance in each window
        // names will be change soon but to make it easier in typing
        loginLink.ic = registerLink.ic= homepageLink.ic= gameStartLink.ic= gameActionsLink.ic = historyLink.ic= iconsColorsLink;
        homepageLink.g = registerLink.g =  gameStartLink;
//        ChessBoard.setGameStart(gameStartLink);
        loginLink.h=registerLink.h=gameStartLink.h= homepageLink;
        homepageLink.hist = historyLink;
        registerLink.l= homepageLink.l = loginLink;
        loginLink.r = homepageLink.r= gameStartLink.r = registerLink;
        ChessBoardBASE.setIconsAndColors(iconsColorsLink);
        ChessBoard.gs= gameStartLink;

//        gameStartLink.gL= gameActionsLink.gl=gameLauncherLink;
//        gameLauncherLink.actions = gameActionsLink;

    }

}
