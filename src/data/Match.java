package data;

import java.util.ArrayList;

public class Match {
    public String oppUserStr = "";
    public String result = "";
    public String color = "";
    public String[] moves;

    public Match(String oppUserStr, int result, ArrayList<String> moves, boolean color) {
        this.oppUserStr = "VS " + oppUserStr;
        if (result == 1) {
            this.result = "Win";
        } else if (result == -1) {
            this.result = "Defeat";
        } else {
            this.result = "Draw";
        }
        this.moves = moves.toArray(new String[moves.size()]);
        if (color) {
            this.color = "White";
        } else {
            this.color = "Black";
        }
    }

    public void addResultandMoves() {

    }


    // make the constructor to initialize the players.
    // make function to add move to the arraylist moves.
    // arraylist moves is array to store the flow of the game like -  1.E4 , E5 - so that the fist one
    // is the white move and the second is the black move.
    // use json files to store the data for each instance from this class
}
