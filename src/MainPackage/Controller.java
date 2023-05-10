package MainPackage;

import java.awt.*;

public class Controller {


    public static void main(String[] args) {
        IconsAndColors icC = new IconsAndColors();
        LoginPage L = new LoginPage();
        Register R = new Register();
        HomePage H = new HomePage();
        GameStart G = new GameStart();

        setLink(L , R, icC , H , G);

        L.initialize();

    }
    private static void setLink(LoginPage LC, Register RC, IconsAndColors ICC,HomePage HC , GameStart GC){
        LC.ic = RC.ic= HC.ic= GC.ic = ICC;
        LC.h=RC.h=GC.h=HC;
        RC.l= HC.l = LC;
        LC.r = HC.r= GC.r = RC;
        HC.g = RC.g = GC;

    }

}
