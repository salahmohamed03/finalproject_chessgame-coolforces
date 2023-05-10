package MainPackage;
import javax.swing.*;
import java.awt.*;

public class IconsAndColors {
    public Icon black_bishop;
    public Icon black_king;
    public Icon black_knight;
    public Icon black_pawn;
    public Icon black_queen;
    public Icon black_rook;
    public Icon white_bishop;
    public Icon white_king;
    public Icon white_knight;
    public Icon white_pawn;
    public Icon white_queen;
    public Icon white_rook;
    public Icon black_bishopN;
    public Icon black_kingN;
    public Icon black_knightN;
    public Icon black_pawnN;
    public Icon black_queenN;
    public Icon black_rookN;
    public Icon white_bishopN;
    public Icon white_kingN;
    public Icon white_knightN;

    public Icon white_pawnN;
    public Icon white_queenN;
    public Icon white_rookN;
    public Color mainColor;
    public Color secondColor ;
    public Color black ;
    public Color white ;
    public Icon black_bishopE;
    public Icon black_kingE;
    public Icon black_knightE;
    public Icon black_pawnE;
    public Icon black_queenE;
    public Icon black_rookE;
    public Icon white_bishopE;
    public Icon white_kingE;
    public Icon white_knightE;
    public Icon white_pawnE;
    public Icon white_queenE;
    public Icon white_rookE;

    public ImageIcon black_pawnD  ;
    public ImageIcon black_bishopD ;
    public ImageIcon black_kingD ;
    public ImageIcon black_knightD;
    public ImageIcon black_rookD ;
    public ImageIcon black_queenD ;
    public ImageIcon white_pawnD  ;
    public ImageIcon white_bishopD ;
    public ImageIcon white_kingD;
    public ImageIcon white_knightD;
    public ImageIcon white_rookD ;
    public ImageIcon white_queenD;

    public ImageIcon blackBishopP ;
    public ImageIcon blackKnightP ;
    public ImageIcon blackQueenP ;
    public ImageIcon blackRookP ;
    public ImageIcon whiteBishopP;
    public ImageIcon whiteKnightP ;
    public ImageIcon whiteQueenP ;
    public ImageIcon whiteRookP ;
    public ImageIcon bWin,wWin,draw;
    public int width, height;

    public IconsAndColors() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = (int) screenSize.getHeight();
        width = 1440 *height/1024 ;



        ImageIcon temp1 = new ImageIcon("src/Mat/Comp/game/Black/bishop.png");
        black_bishop =new ImageIcon( temp1.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp2 = new ImageIcon("src/Mat/Comp/game/Black/king.png");
        black_king =new ImageIcon( temp2.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp3 = new ImageIcon("src/Mat/Comp/game/Black/knight.png");
        black_knight =new ImageIcon( temp3.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp4 = new ImageIcon("src/Mat/Comp/game/Black/pawn.png");
        black_pawn =new ImageIcon( temp4.getImage().getScaledInstance(32, 45, Image.SCALE_SMOOTH));
        ImageIcon temp5 = new ImageIcon("src/Mat/Comp/game/Black/queen.png");
        black_queen =new ImageIcon( temp5.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp6 = new ImageIcon("src/Mat/Comp/game/Black/rock.png");
        black_rook =new ImageIcon( temp6.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp11 = new ImageIcon("src/Mat/Comp/game/White/bishop.png");
        white_bishop =new ImageIcon( temp11.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp22 = new ImageIcon("src/Mat/Comp/game/White/king.png");
        white_king =new ImageIcon( temp22.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp33 = new ImageIcon("src/Mat/Comp/game/White/knight.png");
        white_knight =new ImageIcon( temp33.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp44 = new ImageIcon("src/Mat/Comp/game/White/pawn.png");
        white_pawn =new ImageIcon( temp44.getImage().getScaledInstance(32, 45, Image.SCALE_SMOOTH));
        ImageIcon temp55 = new ImageIcon("src/Mat/Comp/game/White/queen.png");
        white_queen =new ImageIcon( temp55.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        ImageIcon temp66 = new ImageIcon("src/Mat/Comp/game/White/rock.png");
        white_rook =new ImageIcon( temp66.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        black_pawnN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/pawnN.png"),66  *width/870,57 *width/870);
        black_bishopN = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/bishopN.png"),66 *width/870,57 *width/870);
        black_kingN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/kingN.png"),66 *width/870,57 *width/870);
        black_knightN = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/KnightN.png"),66 *width/870,57 *width/870);
        black_rookN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/rockN.png"),66 *width/870,57 *width/870);
        black_queenN  = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/queenN.png"),66 *width/870,57 *width/870);
        white_pawnN   = this.Resize(new ImageIcon("src/Mat/Comp/game/White/pawnN.png"),66 *width/870,57 *width/870);
        white_bishopN = this.Resize(new ImageIcon("src/Mat/Comp/game/White/bishopN.png"),66 *width/870,57 *width/870);
        white_kingN   = this.Resize(new ImageIcon("src/Mat/Comp/game/White/kingN.png"),66 *width/870,57 *width/870);
        white_knightN = this.Resize(new ImageIcon("src/Mat/Comp/game/White/KnightN.png"),66 *width/870,57 *width/870);
        white_rookN = this.Resize(new ImageIcon("src/Mat/Comp/game/White/rockN.png"),66 *width/870,57 *width/870);
        white_queenN  = this.Resize(new ImageIcon("src/Mat/Comp/game/White/queenN.png"),66 *width/870,57 *width/870);
        mainColor =  Color.decode("#FF006E");
        secondColor =  Color.decode("#AE2965");
        black =  Color.decode("#1B1725");
        white =  Color.decode("#FDFFFC");

        black_pawnE   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/pawnS.png"),66  *width/870,57 *width/870);
        black_bishopE = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/bishopS.png"),66 *width/870,57 *width/870);
        black_kingE   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/kingS.png"),66 *width/870,57 *width/870);
        black_knightE = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/KnightS.png"),66 *width/870,57 *width/870);
        black_rookE   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/rockS.png"),66 *width/870,57 *width/870);
        black_queenE  = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/queenS.png"),66 *width/870,57 *width/870);
        white_pawnE   = this.Resize(new ImageIcon("src/Mat/Comp/game/White/pawnS.png"),66 *width/870,57 *width/870);
        white_bishopE = this.Resize(new ImageIcon("src/Mat/Comp/game/White/bishopS.png"),66 *width/870,57 *width/870);
        white_kingE   = this.Resize(new ImageIcon("src/Mat/Comp/game/White/kingS.png"),66 *width/870,57 *width/870);
        white_knightE = this.Resize(new ImageIcon("src/Mat/Comp/game/White/KnightS.png"),66 *width/870,57 *width/870);
        white_rookE = this.Resize(new ImageIcon("src/Mat/Comp/game/White/rockS.png"),66 *width/870,57 *width/870);
        white_queenE  = this.Resize(new ImageIcon("src/Mat/Comp/game/White/queenS.png"),66 *width/870,57 *width/870);

        int widthD = 36  *width/1440, heightD =38 *width/1440;


        black_pawnD   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/pawn.png"),(int)(widthD *.85), heightD);
        black_bishopD = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/bishop.png"),widthD,heightD);
        black_knightD = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/Knight.png"),widthD,heightD);
        black_rookD   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/rock.png"),widthD,heightD);
        black_queenD  = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/queen.png"),widthD,heightD);
        white_pawnD   = this.Resize(new ImageIcon("src/Mat/Comp/game/White/pawn.png"),(int)(widthD *.85),heightD);
        white_bishopD = this.Resize(new ImageIcon("src/Mat/Comp/game/White/bishop.png"),widthD,heightD);
        white_knightD = this.Resize(new ImageIcon("src/Mat/Comp/game/White/Knight.png"),widthD,heightD);
        white_rookD = this.Resize(new ImageIcon("src/Mat/Comp/game/White/rock.png"),widthD,heightD);
        white_queenD  = this.Resize(new ImageIcon("src/Mat/Comp/game/White/queen.png"),widthD,heightD);

        blackBishopP = new ImageIcon("src/Mat/Comp/game/promote/BlackBishopP.png");
        blackKnightP = new ImageIcon("src/Mat/Comp/game/promote/BlackKnightP.png");
        blackQueenP = new ImageIcon("src/Mat/Comp/game/promote/BlackQueenP.png");
        blackRookP = new ImageIcon("src/Mat/Comp/game/promote/BlackRookP.png");
        whiteBishopP = new ImageIcon("src/Mat/Comp/game/promote/WhiteBishopP.png");
        whiteKnightP = new ImageIcon("src/Mat/Comp/game/promote/WhiteKnightP.png");
        whiteQueenP = new ImageIcon("src/Mat/Comp/game/promote/WhiteQueenP.png");
        whiteRookP = new ImageIcon("src/Mat/Comp/game/promote/WhiteRookP.png");

        wWin = new ImageIcon("src/Mat/Comp/game/Wwin.png");
        bWin = new ImageIcon("src/Mat/Comp/game/bWin.png");
        draw = new ImageIcon("src/Mat/Comp/game/draw.png");
    }

    public ImageIcon Resize(ImageIcon icon, int width, int height){
        Image scaled = icon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
    public ImageIcon resizeWithRatio(ImageIcon icon, int refWidth){ //refWidth is the width you worked on!!
        ImageIcon resized = Resize(icon,icon.getIconWidth() *width/refWidth, icon.getIconHeight()*width/refWidth);

        return resized;
    }
    public ImageIcon resizeWithRatio(ImageIcon icon){ //override!!
        ImageIcon resized = Resize(icon,icon.getIconWidth() *width/1440, icon.getIconHeight()*width/1440);

        return resized;
    }

}
