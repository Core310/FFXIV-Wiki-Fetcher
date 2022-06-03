package Scrapper;

import java.io.*;

import static Scrapper.StaticItemTypes.*;

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

    /**
     * Sets a new item type if the current line is a table header that declares the current item type.
     * @param curLine Current line to look at
     * @return One of the StaticItemTypes
     */
    private StaticItemTypes getType(String curLine){
        switch (curLine){
            case "Folklore Tome\tTime\tItem\tSlot\tLocation\tCoordinates\tUsed to make\n":{
                return FolkLoreFishing;
            }
            case "Folklore Tome\tTime\tItem\tLocation\tCoordinates\tAdditional Info\n":{
                return FolkLoreNode;
            }
            case "Level\tType\tZone\tCoordinate\tItems\tExtra\n": {
                return RegularNode;
            }
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tLevel\tStar\tAdditional Info\n":
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tExtra\tStar\n": {
                return UnspoiledNode;
            }

            //Ignore cases below possible fixme?
            case "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tRegular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tFishing Log Big Fishing Fishing Collectables Folklore Fish\n":
            case "Botanist\tMiner\tFisher\n":
            case "Gathering": {
                return Delete;
            }
        }
        return Ignore;
    }

    /**
     * Formats the file by:
     * Goes thru line by line. Searches for table HEADERS in wiki. Deletes these headers and stores the current item type
     * (determined by header) in an internal variable. Each item from then on is determined from that internal
     * variable. Eg. Gather header -> each item is now a Gather item. 
     *
     * Reads line by line with a Buffered reader and writer, putts into queue, and replaces each line.
     * This method creates several duplicate items.
     */
    public void format(){
        //todo Replace current line in file. Eventually, make all ITEMs in the first column for ease of fuzzy Search
        try {// TODO: 3/31/22 Use a buffer to read the whole file in, then repalce each line using the buffer
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String currentLine;
            String csvValues[];
            String itemType = null;

            while((currentLine = br.readLine()) != null){ // Look thru whole file
                csvValues = currentLine.split("\t"); //Load all values into an array. Used to normalize iteems

                switch (getType(currentLine)){ //Cases to find item type
                    //If header: Set a new ItemType
                    //Else if ignore, ignore
                    //Else if data then use current ItemType to load data
                    case FolkLoreFishing:{
                        //Then replace the line with blank space
                        itemType = "FolkLore_Slot_UsedToMake";
						//todo delete current line
						
                    }
                    case FolkLoreNode:{
                        itemType = "FolkLore_Regular";

                    }
                    case RegularNode:{
                        itemType = "Regular";
                        //delete current line
                    }
                    // case"Level\tType\tZone\tCoordinate\tItems\tExtra\n"
                    case UnspoiledNode:{

                    }

                    case Delete:{
						
                        //should replace the line with blank space
                    }
                    case Ignore:{ //Actual item data NOT a header
						switch (itemType){
                            case "Regular":{
                                
                            }
                            
                            
                        }
                        
                        
                        //Replace current line with loaded itemType, start off with itemType then data
                    }
                }// TODO: 3/17/22 Load each case into a ITEM class, then repalce the current line

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
