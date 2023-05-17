package data;

import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.READER;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//imports needed to deal with JSON
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
public abstract class dataHandling extends JFrame
{
    List<JsonObject> dataObjs = new ArrayList<>();
    Gson jsonArr = new GsonBuilder().setPrettyPrinting().create();
    JsonArray jsonArray = jsonArr.toJsonTree(dataObjs).getAsJsonArray();
    //////////////////////////////////////////////////////////////////////////
    Gson gsonObj = new Gson(); // each user will be converted to a gsonobj
    JsonObject jsonObj =new JsonObject();

    FileWriter writer;
    FileReader reader;



    //function to read the JSON file
    public void readExistingData()
    {
        try {
            reader=new FileReader("data.json");
            jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            reader.close();
        } catch (Exception readError) {
            System.out.println("Reading error occured");
        }
    }

    //function to write the data after adding new credentials
    public void addAndWriteNewData(User user)
    {
        jsonObj=gsonObj.toJsonTree(user).getAsJsonObject();
        jsonArray.add(jsonObj);
        writeToJsonFile();
    }

    //function to check credentials
    public Boolean checkCredentials(String inputUsername, String inputPassword)
    {
        readExistingData();
        String inspectedUsername;
        String inspectedPassword;
        try {
            for (JsonElement jsonElement : jsonArray)
            {
                inspectedUsername="";
                inspectedPassword="";
                inspectedUsername=jsonElement.getAsJsonObject().get("Username").getAsString();
                inspectedPassword=jsonElement.getAsJsonObject().get("Password").getAsString();
                if (inspectedUsername.equals(inputUsername) && inspectedPassword.equals(inputPassword))
                {
                    return true;
                }
            }
        } catch (Exception credentials) {
            //
        }
        return false;
    }

    //function to check if user name exists when registering
    public Boolean checkUsername(String inputUsername)
    {
        readExistingData();
        String inspectedUsername="";
        try {
            for (JsonElement jsonElement : jsonArray)
            {
                inspectedUsername=jsonElement.getAsJsonObject().get("Username").getAsString();
                if (inspectedUsername.equals(inputUsername))
                {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("user");
        }
        return true;
    }

    //function to get usernames of all players
    public ArrayList<String> getOpponentsArrList(User user)
    {
        ArrayList<String> opponents = new ArrayList<String>();
        readExistingData();
        for (JsonElement jsonElement : jsonArray)
        {
            if (user.getName().equals(jsonElement.getAsJsonObject().get("Username").getAsString()))
            {
                continue;
            }
            opponents.add(jsonElement.getAsJsonObject().get("Username").getAsString());
        }
        return opponents;
    }



    //function to look for a user
    public User findOppUser(String oppUserName)
    {
        readExistingData();
        JsonObject userObject =  new JsonObject();
        for (JsonElement jsonElement : jsonArray)
        {
            userObject=jsonElement.getAsJsonObject();
            if (oppUserName.equals(userObject.get("Username").getAsString()))
            {
                Gson convert = new Gson();
                User convertedUser = convert.fromJson(userObject, User.class);
                return convertedUser;
            }
        }
        return null;

    }

    //function to update the match then write back modification
    public void addMatch(String userName, Match m)
    {
        readExistingData();
        JsonObject objectToModify = null;
        for (int i = 0 ; i < jsonArray.size();i++)
        {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            if (obj.get("Username").getAsString().equals(userName))
            {
                objectToModify = obj;
                JsonArray matchesJsonArray = objectToModify.getAsJsonArray("matches");
                Gson convert = new Gson();
                JsonObject matchJsonObj=convert.toJsonTree(m).getAsJsonObject();
                matchesJsonArray.add(matchJsonObj);
                jsonArray.set(i, objectToModify);
                break;
            }
        }
        writeToJsonFile();

    }

    //function to write data to the json file
    public void writeToJsonFile()
    {
        try {
            writer = new FileWriter("data.json");
            jsonArr.toJson(jsonArray, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println("Writing Error");
        }
    }

    //function that will get matches
    public ArrayList<Match> getMatches(String userName)
    {
        readExistingData();
        Gson gson = new Gson();
        Match match;
        ArrayList <Match> historyMatches = new ArrayList<>();
        JsonObject objectToModify = null;
        for (int i = 0 ; i < jsonArray.size();i++)
        {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            if (obj.get("Username").getAsString().equals(userName))
            {
                objectToModify = obj;
                JsonArray matchesJsonArray = objectToModify.getAsJsonArray("matches");
                for (JsonElement element : matchesJsonArray)
                {
                    match = gson.fromJson(element, Match.class);
                    historyMatches.add(match);
                }
                break;
            }
        }
        return historyMatches;
    }


    //function to calculate winRate & wins
    public float[] getWinRateAndWins(ArrayList <Match> matches)
    {
        float winRate;
        int counter=0;
        for (int i = 0; i < matches.size(); i++)
        {
            if (matches.get(i).result.equals("Win"))
            {
                counter++;
            }
        }
        winRate = (float) counter/matches.size();
        float[] arr =new float[2];
        arr[0]=winRate;
        arr[1]=counter;
        return arr;
    }


}
