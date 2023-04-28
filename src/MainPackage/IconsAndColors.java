package MainPackage;
import javax.swing.*;
import java.awt.*;

public class IconsAndColors {
    public Icon black_bishop;
    public Icon black_king;
    public Icon black_knight;
    public Icon black_pawn;
    public Icon black_queen;
    public Icon black_rock;
    public Icon white_bishop;
    public Icon white_king;
    public Icon white_knight;
    public Icon white_pawn;
    public Icon white_queen;
    public Icon white_rock;
    public Icon black_bishopN;
    public Icon black_kingN;
    public Icon black_knightN;
    public Icon black_pawnN;
    public Icon black_queenN;
    public Icon black_rockN;
    public Icon white_bishopN;
    public Icon white_kingN;
    public Icon white_knightN;
    public Icon white_pawnN;
    public Icon white_queenN;
    public Icon white_rockN;
    public Color mainColor;
    public Color secondColor ;
    public Color black ;
    public Color white ;
    public IconsAndColors() {
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
        black_rock =new ImageIcon( temp6.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
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
        white_rock =new ImageIcon( temp66.getImage().getScaledInstance(42, 45, Image.SCALE_SMOOTH));
        black_pawnN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/pawnN.png"),66,57);
        black_bishopN = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/bishopN.png"),66,57);
        black_kingN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/kingN.png"),66,57);
        black_knightN = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/KnightN.png"),66,57);
        black_rockN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/rockN.png"),66,57);
        black_queenN  = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/queenN.png"),66,57);
        white_pawnN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/pawnN.png"),66,57);
        white_bishopN = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/bishopN.png"),66,57);
        white_kingN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/kingN.png"),66,57);
        white_knightN = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/KnightN.png"),66,57);
        white_rockN   = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/rockN.png"),66,57);
        white_queenN  = this.Resize(new ImageIcon("src/Mat/Comp/game/Black/queenN.png"),66,57);
        mainColor =  Color.decode("#FF006E");
        secondColor =  Color.decode("#AE2965");
        black =  Color.decode("#1B1725");
        white =  Color.decode("#FDFFFC");
    }

    public ImageIcon Resize(ImageIcon icon, int width, int height){
        Image scaled = icon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
