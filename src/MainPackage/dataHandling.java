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

    
    //Function to add new user
    User user;
    public void addUser(User user)
    {
        this.user=user;
    }
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
    public void addAndWriteNewData()
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
    public Boolean checkCredentials(String username, char[] password)
    {
        readExistingData();
        String inspectedUsername="";
        String inspectedPassword="";
        String passwordStr = new String(password);
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
            }
            /*System.out.println(username);
            System.out.println(inspectedUsername);
            System.out.println(passwordStr);
            System.out.println(inspectedPassword);*/
            if (inspectedUsername.equals(username) && inspectedPassword.equals(passwordStr))
            {
                return true;
            }
            
        } catch (Exception credentials) {
            System.out.println("incorrect credentials");
        }
        return false;
    }
    
} 
