package data;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private final String Username;
    private final String Password;
    public double WinRate;
    public ArrayList<Match> matches = new ArrayList<>();

    //Constructor to intialize username and password
    public User(String Username, String Password)
    {
        this.Username=Username;
        this.Password=Password;
    }

    public String getName()
    {
        return Username;
    }

    public String getPass()
    {
        return Password;
    }


    // make function to add match to the history
    // function to calculate the win rate
    // use json files to store the data for each instance from this class
}
/*class Move{
    String WhiteMove;
    String BlackMove;
    public Move MakeMove(String white, String black) { // use this method to add moves to the array list moves
        Move move = new Move();
        move.WhiteMove = white;
        move.BlackMove = black;
        return move;
    }
}*/