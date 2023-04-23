package MainPackage;
import javax.swing.*;
import java.awt.*;

public class PieceIcons {
    public Image black_bishop;
    public Image black_king;
    public Image black_knight;
    public Image black_pawn;
    public Image black_queen;
    public Image black_rook;
    public Image white_bishop;
    public Image white_king;
    public Image white_knight;
    public Image white_pawn;
    public Image white_queen;
    public Image white_rook;
    public PieceIcons()
    {
        ImageIcon temp1 = new ImageIcon("src/pieces/black_bishop.png");
        black_bishop = temp1.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp2 = new ImageIcon("src/pieces/black_king.png");
        black_king = temp2.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp3 = new ImageIcon("src/pieces/black_knight.png");
        black_knight = temp3.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp4 = new ImageIcon("src/pieces/black_pawn.png");
        black_pawn = temp4.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp5 = new ImageIcon("src/pieces/black_queen.png");
        black_queen = temp5.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp6 = new ImageIcon("src/pieces/black_rook.png");
        black_rook = temp6.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp11= new ImageIcon("src/pieces/white_bishop.png");
        white_bishop = temp11.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp22= new ImageIcon("src/pieces/white_king.png");
        white_king = temp22.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp33= new ImageIcon("src/pieces/white_knight.png");
        white_knight = temp33.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp44= new ImageIcon("src/pieces/white_pawn.png");
        white_pawn = temp44.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp55= new ImageIcon("src/pieces/white_queen.png");
        white_queen = temp55.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon temp66= new ImageIcon("src/pieces/white_rook.png");
        white_rook = temp66.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
    }
}
