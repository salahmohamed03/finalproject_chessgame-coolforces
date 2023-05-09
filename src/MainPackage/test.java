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
public class test extends JFrame{
    List<JsonObject> dataObjsTest = new ArrayList<>();   
    Gson jsonArrTest = new GsonBuilder().setPrettyPrinting().create(); 
    JsonArray jsonArrayTest = jsonArrTest.toJsonTree(dataObjsTest).getAsJsonArray();
    Gson gsonObjTest = new Gson(); // each user will be converted to a gsonobj
    JsonObject jsonObjTest =new JsonObject();

    FileWriter writerTest;
    FileReader readerTest;


    public void readExistingDataTest()
    {
        try {
            readerTest=new FileReader("test.json");
            jsonArrayTest = JsonParser.parseReader(readerTest).getAsJsonArray();
            //writeJsonArray=readJsonArray;
            readerTest.close();
        } catch (Exception readError) {
            System.out.println("Reading error occured");
        }   
    }

    public void addAndWriteNewData(User user)
    {      
        jsonObjTest=gsonObjTest.toJsonTree(user).getAsJsonObject();
        jsonArrayTest.add(jsonObjTest); 
        try {
            writerTest=new FileWriter("test.json");
            jsonArrTest.toJson(jsonArrayTest, writerTest);
            writerTest.close();
            } catch (Exception writeError) {
                System.out.println("Writing error happened");
            }
    }

}
