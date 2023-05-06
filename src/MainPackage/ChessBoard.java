package MainPackage;

public class ChessBoard extends ChessBoardBASE{
    public ChessBoard(){initialize();}

    public static void main(String []args)
    {
        ChessBoard c = new ChessBoard();
        c.setDead(6,true);
        c.setDead(6,true);
        c.setDead(2,false);
        c.setDead(6,true);
        c.setDead(3,false);

        c.show();
    }

    @Override
    protected void setButtons() {

        //black buttons
            resign = createButton("Resign","re1","src/Mat/Buttons/drawBtn.png",635 *width/870,250 *width/870,"#FF006E");
            draw = createButton("Draw","dr1","src/Mat/Buttons/resignBtn.png",725 *width/870,250 *width/870,"#5F5F5F");//5F5F5F
        //white buttons
            resign2 = createButton("Resign","re2","src/Mat/Buttons/drawBtn.png",635 *width/870,340 *width/870,"#FF006E");
            draw2 = createButton("Draw","dr2","src/Mat/Buttons/resignBtn.png",725 *width/870,340 *width/870,"#5F5F5F");
            container.add(resign);
            container.add(draw);
            container.add(resign2);
            container.add(draw2);
    }
}