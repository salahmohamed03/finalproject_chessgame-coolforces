package MainPackage;

import java.awt.*;

public class Controller {


    public static void main(String[] args) {
        IconsAndColors icC = new IconsAndColors();
        LoginPage L = new LoginPage();

        Register R = new Register();  //capital letter for main in Controller
        HomePage H = new HomePage();
        GameStart G = new GameStart();
        setLink(L , R, icC , H , G);

        icC.mainColor=new Color(44, 98, 12);
        L.initialize();

    }
    private static void setLink(LoginPage LC, Register RC, IconsAndColors ICC,HomePage HC , GameStart GC){
        //XC for the page in this method
        //.x small is th page referance in each window
        // names will be change soon but to make it easier in typing
        LC.ic = RC.ic= HC.ic= GC.ic = ICC;
        HC.g = RC.g = GC;
        LC.h=RC.h=GC.h=HC;
        RC.l= HC.l = LC;
        LC.r = HC.r= GC.r = RC;

    }

}
