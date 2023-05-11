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
        ChessBoard chessBoardControl = new ChessBoard();
        GameLauncher gameLauncherControl = new GameLauncher(); //see it in the GAME LAUNCHER
        GameActions gameActionsControl = new GameActions();

        setLink(loginControl , registerControl, icC , homePageControl , gameStartControl, chessBoardControl, gameLauncherControl, gameActionsControl, historyControl);

        icC.mainColor=new Color(44, 98, 12);
        loginControl.initialize();

    }
    private static void setLink(LoginPage loginLink, Register registerLink, IconsAndColors iconsColorsLink,HomePage homepageLink , GameStart gameStartLink , ChessBoard chessBoardLink, GameLauncher gameLauncherLink, GameActions gameActionsLink, History historyLink){
        //XC for the page in this method
        //.x small is th page referance in each window
        // names will be change soon but to make it easier in typing
        loginLink.ic = registerLink.ic= homepageLink.ic= gameStartLink.ic= gameActionsLink.ic = historyLink.ic= iconsColorsLink;
        homepageLink.g = registerLink.g = chessBoardLink.g = gameStartLink;
        loginLink.h=registerLink.h=gameStartLink.h= homepageLink;
        homepageLink.hist = historyLink;
        registerLink.l= homepageLink.l = loginLink;
        loginLink.r = homepageLink.r= gameStartLink.r = registerLink;
        gameStartLink.c=gameLauncherLink.game=chessBoardLink; //game actions
        gameStartLink.gL= gameActionsLink.gl=gameLauncherLink;
        gameLauncherLink.actions = gameActionsLink;

    }

}
