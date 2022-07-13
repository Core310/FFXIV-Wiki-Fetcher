package Scrapper;

import Items.*;

import java.io.*;
import java.rmi.UnexpectedException;

import static Scrapper.StaticItemTypes.*;

/**
 * Puts an ITEM tag infront of each item for ease of reading.
 */
public class Formatter {
    private String file;//File to read/write
    private StaticItemTypes itemType; //Current Enum Item Type

    /**
     * Default constructor, should be the only one needed.
     * @param file File to run on.
     */
    public Formatter(String file){
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
            case "Folklore Tome\tTime\tItem\tSlot\tLocation\tCoordinates\tUsed to make" -> {
                itemType = FolkLoreNode;
                return Delete;
            }
            case "Folklore Tome\tTime\tItem\tLocation\tCoordinates\tAdditional Info" -> {
                itemType = FolkLoreFishing;
                return Delete;
            }
            case "Level\tType\tZone\tCoordinate\tItems\tExtra" -> {
                itemType = RegularNode;
                return Delete;
            }

            case "Time\tItem\tSlot #\tLocation\tCoordinate\tLevel\tStar\tAdditional Info\n", "Time\tItem\tSlot #\tLocation\tCoordinate\tExtra\tStar" -> {
                itemType = UnspoiledNode;
                return Delete;
            }
            //Ignore cases below possible checkme?
            case "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tRegular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tFishing Log Big Fishing Fishing Collectables Folklore Fish\n",
                    "Botanist\tMiner\tFisher\n",
                    "Gathering",
                    " ",
                    ""
                    -> {
                return Delete;
            }
        }
        return Ignore;// FIXME: 7/13/2022 This keeps running instead of finding the headrs
    }

    /**
     * A helper method to format().
     * itemType Determined from an item return value when setCurrentType returns Ignore (data to extract). (See StaticItemTypes method)
     * @return New line that should replace the old line.
     */
    private String formattedItem(String[] csvValues){//I should work idk
        StringBuilder FormattedItem = new StringBuilder(); //String to replace the current line read in

        switch (itemType){
            case RegularNode:{
                int numberOfItems = csvValues[4].split(",").length;
                if(csvValues[5] == null) csvValues[5] = "";

                if(numberOfItems == 1){
                    FormattedItem.append(RegularNode.name());//Appends the name of the item first
                    FormattedItem.append("\t");
                    FormattedItem.append(new Regular_Node(
                            csvValues[4],//Item
                            csvValues[2],//Zone
                            csvValues[3],//Cords
                            csvValues[5],//Extra
                            //End of baseItem
                            Integer.parseInt(csvValues[0]),//Level
                            csvValues[1]//Type
                    ).toString());
                }
                else {
                    //Looks through all items separated by CSV, Creates a new item, and then creates a new line with another new item.
                    for (int i = 0; i < numberOfItems; i++) {
                        FormattedItem.append(RegularNode.name());//Appends the name of the item first
                        FormattedItem.append("\t");
                        FormattedItem.append(new Regular_Node(
                                csvValues[i],//Item
                                csvValues[2],//Zone
                                csvValues[3],//Cords
                                csvValues[5],//Extra
                                //End of baseItem
                                Integer.parseInt(csvValues[0]),//Level
                                csvValues[1]//Type
                        ).toString());
                        FormattedItem.append("\n"); // This should work but will likely be a future problem to look @
                    }
                }


            }
            case FolkLoreNode:{
                FormattedItem.append(FolkLoreNode.name());
                FormattedItem.append("\t");
                FormattedItem.append(new FolkLore_Node(
                        csvValues[2],//Item
                        csvValues[4],//Zone
                        csvValues[5],//Cords
                        csvValues[6],//Extra
                        //End of baseItem
                        csvValues[0],//FolkloreTome
                        csvValues[1],//Time
                        Integer.parseInt(csvValues[3])//Slot

                ).toString());
            }
            case FolkLoreFishing:{
                FormattedItem.append(FolkLoreFishing.name());
                FormattedItem.append("\t");
                FormattedItem.append(new FolkLore_Fishing(
                        csvValues[2],//Item
                        csvValues[3],//Zone
                        csvValues[4],//Cords
                        csvValues[5],//Extra
                        //End of baseItem
                        csvValues[0],////FolkloreTome
                        csvValues[1]//Time
                ).toString());

            }
            case UnspoiledNode:{
                if(csvValues[5] == null){ // FIXME: 7/12/2022 This likely wont work
                    csvValues[5] = String.valueOf(0);
                }//Dealing with Level value being null

                FormattedItem.append(UnspoiledNode.name());
                FormattedItem.append("\t");
                FormattedItem.append(new Unspoiled_Node(
                        csvValues[1],//Item
                        csvValues[3],//Zone
                        csvValues[4], //Cords
                        csvValues[7],//Extra
                        //End of baseItem
                        csvValues[0],//Time
                        Integer.parseInt(csvValues[2]),//Slot
                        Integer.parseInt(csvValues[5]),//level
                        csvValues[6].length() //star
                ).toString()
                );
            }
            case null:
                try {
                    System.out.println(itemType);
                    throw new UnexpectedException("There should always be an item type assigned");
                } catch (UnexpectedException e) {
                    throw new RuntimeException(e);
                }
            case Delete:
            case Ignore:
                try {
                    throw new UnexpectedException("These items should not appear");
                } catch (UnexpectedException e) {
                    throw new RuntimeException(e);
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
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            String currentLine; //Current line
            String[] csvValues;//Current line read in as CSV in an array

            while((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
                csvValues = currentLine.split("\t",-1); //Load all values into an array. Used to normalize items

                switch (setCurrentType(currentLine)) { //Cases to find item type
                    //If header: Set a new ItemType
                    //Else if data, use cur item type.
                    case FolkLoreFishing, FolkLoreNode, RegularNode, UnspoiledNode, Delete -> {
                        bw.write("");
                        System.out.println("Deleting cur line");
                        //todo delete current line
                    }
                    case Ignore -> { //Actual item data NOT a header
                        bw.write(formattedItem(csvValues));
                    }
                    case default -> {throw new UnexpectedException("All cases should have been covered");}
                }//End of switch statement
            }//End of while statement
            br.close();
            bw.close();
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
}