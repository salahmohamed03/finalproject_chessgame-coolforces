package MainPackage;

import java.util.ArrayList;

public class User {
    private String Username;
    private char[] Password;
    public double WinRate;
    public ArrayList<Match> History;
    
    //Constructor to intialize username and password
    public User(String Username, char[] Password)
    {
        this.Username=Username;
        this.Password=Password;
    }

    public String getUsername()
    {
        return Username;
    }

    public char[] getPassword()
    {
        return Password;
    }

    
    // make function to add match to the history
    // function to calculate the win rate
    // use json files to store the data for each instance from this class
}
class Match {
    public User Player1;
    public User Player2;
    public ArrayList<Move> moves;
    // make the constructor to initialize the players.
    // make function to add move to the arraylist moves.
    // arraylist moves is array to store the flow of the game like -  1.E4 , E5 - so that the fist one
    // is the white move and the second is the black move.
    // use json files to store the data for each instance from this class
}
class Move{
    String WhiteMove;
    String BlackMove;
    public Move MakeMove(String white, String black) { // use this method to add moves to the array list moves
        Move move = new Move();
        move.WhiteMove = white;
        move.BlackMove = black;
        return move;
    }
}