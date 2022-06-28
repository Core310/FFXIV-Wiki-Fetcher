package Scrapper;

import Items.FolkLore_Fishing;
import Items.FolkLore_Node;
import Items.Regular_Node;
import Items.Unspoiled_Node;

import java.io.*;
import java.rmi.UnexpectedException;

import static Scrapper.StaticItemTypes.*;

/**
 * Puts an ITEM tag infront of each item for ease of reading.
 */
public class Formatter {
    private File file;//File to read/write
    private StaticItemTypes itemType; //Current Enum Item Type

    /**
     * Default constructor, should be the only one needed.
     * @param file File to run on.
     */
    Formatter(File file){
        this.file = file;
    }

    /**
     * A helper method for format()
     * Looks at current line, if is a header that describes an item type, sets global var itemType to whatever that current item is.
     * If it is not an item, such as an ending header, or is data, it will return either Delete, or Ignore where Ignore = data.
     * @param curLine Current line to look at
     * @return One of the StaticItemTypes
     */
    private StaticItemTypes setCurrentType(String curLine){
        switch (curLine) {
            case "Folklore Tome\tTime\tItem\tSlot\tLocation\tCoordinates\tUsed to make\n" -> {
                itemType = FolkLoreNode;
                return FolkLoreNode;
            }
            case "Folklore Tome\tTime\tItem\tLocation\tCoordinates\tAdditional Info\n" -> {
                itemType = FolkLoreFishing;
                return FolkLoreFishing;
            }
            case "Level\tType\tZone\tCoordinate\tItems\tExtra\n" -> {
                itemType = RegularNode;
                return RegularNode;
            }
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tLevel\tStar\tAdditional Info\n", "Time\tItem\tSlot #\tLocation\tCoordinate\tExtra\tStar\n" -> {
                itemType = UnspoiledNode;
                return UnspoiledNode;
            }


            //Ignore cases below possible fixme?
            case "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tRegular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tFishing Log Big Fishing Fishing Collectables Folklore Fish\n", "Botanist\tMiner\tFisher\n", "Gathering" -> {
                return Delete;
            }
        }
        return Ignore;
    }

    /**
     * A helper method to format().
     * itemType Determined from an item return value when setCurrentType returns Ignore (data to extract). (See StaticItemTypes method)
     * @return New line that should replace the old line.
     */
    private String formattedItem(String[] csvValues){
        StringBuilder FormattedItem = new StringBuilder(); //String to replace the current line read in

        switch (itemType){
            case RegularNode:{
                FormattedItem.append(RegularNode.name());//Appends the name of the item first
                FormattedItem.append(",");
                FormattedItem.append(new Regular_Node(
                        Integer.parseInt(csvValues[0]),
                        csvValues[1],
                        csvValues[2],
                        csvValues[3],
                        csvValues[4],
                        csvValues[5]
                ).toString());

            }
            case FolkLoreNode:{
                FormattedItem.append(FolkLoreNode.name());
                FormattedItem.append(",");
                FormattedItem.append(new FolkLore_Node(
                        csvValues[0],
                        csvValues[1],
                        csvValues[2],
                        csvValues[3],
                        csvValues[4],
                        csvValues[5],
                        Integer.parseInt(csvValues[6])

                ).toString());
            }
            case FolkLoreFishing:{
                FormattedItem.append(FolkLoreFishing.name());
                FormattedItem.append(",");
                FormattedItem.append(new FolkLore_Fishing(
                        csvValues[0],
                        csvValues[1],
                        csvValues[2],
                        csvValues[3],
                        csvValues[4],
                        csvValues[5]
                ).toString());

            }
            case UnspoiledNode:{
                int level;
                // TODO: 6/15/2022 How do I deal with a no level value? Sim what abt if no
                if(csvValues[]){ //If no level value then put -1

                }

                FormattedItem.append(UnspoiledNode.name());
                FormattedItem.append(",");
                FormattedItem.append(new Unspoiled_Node(
                        csvValues[0],//Time
                        csvValues[1],//Item
                        Integer.parseInt(csvValues[2]),//slot
                        csvValues[3],//location
                        csvValues[4],//cords
                        Integer.parseInt(csvValues[5]), //level
                        Integer.parseInt(csvValues[6]),
                        csvValues[7]
                ).toString()
                );
            }
        } //End of switch case
        return FormattedItem.toString();
    }

    /**
     * Formats the file by:
     * Goes through line by line.
     * [StaticItemTypes Method]
     * Searches for table HEADERS in wiki which determines the current ITEMTYPE (internal var). This is obtained from the StaticItemTypes method.
     * Eg. Gather header -> each item is now a Gather item.
     *
     * When a data line is found (normal table values) will execute
     * [obtainItemString Method]
     * This will format the current line according to the ITEMTYPE.
     *
     * Then this method will replace the current line
     *
     * Reads line by line with a Buffered reader and writer, putts into queue, and replaces each line.
     * This method creates several duplicate items and has another method called later on.
     */
    public void formatFile(){
        //todo Replace current line in file. Eventually, make all ITEMs in the first column for ease of fuzzy Search
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String currentLine; //Current line
            String[] csvValues;//Current line read in as CSV in an array

            while((currentLine = br.readLine()) != null){
                csvValues = currentLine.split("\t"); //Load all values into an array. Used to normalize iteems

                switch (setCurrentType(currentLine)) { //Cases to find item type
                    //If header: Set a new ItemType
                    //Else if data, use cur item type.
                    case FolkLoreFishing, FolkLoreNode, RegularNode, UnspoiledNode, Delete -> {
                        //todo delete current line
                        continue;
                    }
                    case Ignore -> { //Actual item data NOT a header
                        formattedItem(csvValues);
                        //todo Finally, replace current line with the FormattedItem string
                        continue;
                    }
                }//End of switch statement
                throw new UnexpectedException("No item type was assigned!"); //An item type should always be caught by the switch case
            }//End of while statement
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