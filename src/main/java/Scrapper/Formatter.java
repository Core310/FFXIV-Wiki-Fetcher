package Scrapper;

import Items.Item;
import Items.Regular_Node;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Formats a given argument file for reading. Should be called after the file is written into.
 */
public class Formatter {
    private File file;

    /**
     * Default constructor, should be the only one needed.
     * @param file File to run on.
     */
    Formatter(File file){
        this.file = file;
    }


    private String getType(String curLine){
        switch (curLine){
            case "Folklore Tome\tTime\tItem\tSlot\tLocation\tCoordinates\tUsed to make\n":{
                return "FolkLore_Slot_UsedToMake";
            }
            case "Folklore Tome\tTime\tItem\tLocation\tCoordinates\tAdditional Info\n":{
                return "FolkLore_Regular";
            }
            case "Level\tType\tZone\tCoordinate\tItems\tExtra\n": {
                return "Regular";
            }
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tLevel\tStar\tAdditional Info\n":{
                return "TimeBasedStar";
            }
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tExtra\tStar\n":{
                return "TimeBasedStar2";
            }

            //Ignore cases below
            case "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tRegular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tFishing Log Big Fishing Fishing Collectables Folklore Fish\n":
            case "Botanist\tMiner\tFisher\n":
            case "Gathering": {
                return "Ignore";
            }
        }
        return "Data";
    }// TODO: 3/17/22 Document

    /**
     * Formats the file
     */
    public void format(){
        //todo Replace current line in file
        try {// TODO: 3/31/22 Use a buffer to read the whole file in, then repalce each line using the buffer
            Scanner scanner = new Scanner(file);
            String currentLine;
            String csvValues[];
            String itemType;
            while(scanner.hasNextLine()){
                currentLine = scanner.nextLine();
                csvValues = currentLine.split("\t");
                switch (getType(currentLine)){
                    case "FolkLore_Slot_UsedToMake":{
                        //should replace the line with blank space
                        itemType = "FolkLore_Slot_UsedToMake";

                    }
                    case "FolkLore_Regular":{
                        itemType = "FolkLore_Regular";

                    }
                    case "Regular":{
                        itemType = "Regular";
                        Regular_Node regular_node;
                        String[] Items = csvValues[4].split(",");
                        for(int i =0;i<Items.length;i++){
                            regular_node = new Regular_Node(
                                    Integer.parseInt(csvValues[0]),
                                    csvValues[1],
                                    csvValues[2],
                                    csvValues[3],
                                    Items[i],
                                    csvValues[5]
                            );
                            

                        }
                        //delete current line, loop thru Items arr and create new item for e/a


                    }
                    // case"Level\tType\tZone\tCoordinate\tItems\tExtra\n"
                    case "TimeBasedStar":{

                    }
                    case "TimeBasedStar2":{

                    }
                    case "Ignore":{
                        //should replace the line with blank space
                    }
                    case "Data":{
                        //Replace current line with loaded itemType, start off with itemType then data
                    }
                }// TODO: 3/17/22 Load each case into a ITEM class, then repalce the current line

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void SortByItemName(){


        /*
        //should sort file by item name, MUST be run after being formatted.
        //Should I load the whole entire file into an arraylist/array and take
        //up a massivea mnt of memory? It's only run once so shuld b fine?
         */
    }

    /**
     * Removes duplicate values as well as last page headers.
     * Last page header is found at the end of each wiki page:
     * "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes,Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes,Fishing Locations Fishing Collectables Folklore Nodes"
     */
    public boolean CheckRemoveHeader(String string){
        String DuplicateStandardHeader = "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes,Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes,Fishing Locations Fishing Collectables Folklore Nodes";
        if(string.equals(DuplicateStandardHeader)) return true;
        return false;
    }//Has O(1) Time as should be called when indexing thru file

    public boolean CheckRemoveDuplicate(String string, String prevString){
        return string.equals(prevString);//returns true if duplicate
    }

    //setters

    /**
     * Sets the object to run on a new file. (Constructor forces a file by default).
     * @param file New file to run on
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Gets current file running
     * @return Current instanced FILE object.
     */
    public File getFile() {
        return file;
    }
}
