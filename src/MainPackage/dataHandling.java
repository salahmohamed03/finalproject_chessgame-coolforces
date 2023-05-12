package MainPackage;

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
public abstract class dataHandling extends JFrame {
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
            //writeJsonArray=readJsonArray;
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
        try {
            writer=new FileWriter("data.json");
            jsonArr.toJson(jsonArray, writer);
            writer.close();
            } catch (Exception writeError) {
                System.out.println("Writing error happened");
            }
    }

    //function to check credentials
    public Boolean checkCredentials(String inputUsername, char[] inputPassword)
    {
        readExistingData();
        String inspectedUsername;
        String inspectedPassword;
        String passwordStr = new String(inputPassword);
        try {
            for (JsonElement jsonElement : jsonArray)
            {
                inspectedUsername="";
                inspectedPassword="";
                inspectedUsername=jsonElement.getAsJsonObject().get("Username").getAsString();
                JsonArray passwordArray = jsonElement.getAsJsonObject().get("Password").getAsJsonArray();
                for (JsonElement element : passwordArray) {
                    inspectedPassword += element.getAsString();
                }
                if (inspectedUsername.equals(inputUsername) && inspectedPassword.equals(passwordStr))
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

    // function to add match
    /* 
    public void addMatch(User mainUser,User oppUser)
    {
       Match m1 = new Match(oppUser.getName());
       mainUser.matches.add(m1);
       Match m2 = new Match(mainUser.getName());
       oppUser.matches.add(m2);
       readExistingData();
       addAndWriteNewData(mainUser);
       //readExistingData();
       //addAndWriteNewData(oppUser); 
    }*/

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
        //System.out.println("accessed addMatch");
        readExistingData();
        JsonObject objectToModify = null;
        for (int i = 0 ; i < jsonArray.size();i++) 
        {
        JsonObject obj = (JsonObject) jsonArray.get(i);
        if (obj.get("Username").getAsString().equals(userName))
        {
            objectToModify = obj;
            //System.out.println("found a user of username");
            //System.out.println(objectToModify.get("Username").getAsString());
            //System.out.printf("index is %d\n", i);
            JsonArray matchesJsonArray = objectToModify.getAsJsonArray("matches");
            Gson convert = new Gson();
            JsonObject matchJsonObj=convert.toJsonTree(m).getAsJsonObject();
            matchesJsonArray.add(matchJsonObj);
            jsonArray.set(i, objectToModify);
            //System.out.println("match added");
            break;
        }
        }
        try {
            writer = new FileWriter("data.json");
            jsonArr.toJson(jsonArray, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println("error in match update");
        }
    }

    //function to create the match


  

}
