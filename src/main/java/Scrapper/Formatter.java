package Scrapper;

import Items.*;

import java.io.*;
import java.rmi.UnexpectedException;
import java.util.Arrays;

import static Scrapper.StaticItemTypes.*;

/**
 * Puts an ITEM tag infront of each item for ease of reading.
 */
public class Formatter {
    private final String file;//File to read/write
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
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tLevel\tStar\tAdditional Info" -> {
                itemType = UnspoiledNode;
                return Delete;
            }
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tExtra\tStar" ->{
                itemType = ARRUnspoiledNode;
                return Delete;
            }

            //Ignore cases below possible checkme?
            case "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tRegular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tFishing Locations Big Fishing Fishing Collectables Folklore Fish",
                    "Botanist\tMiner\tFisher",
                    "Gathering",
                    ""
                    -> {
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
        System.out.println(Arrays.toString(csvValues));
        switch (itemType){
            case RegularNode:{
                String[] splitItems = csvValues[4].split(",",-1);//Splits all items into an array to process
                if(splitItems.length == 1){

                    FormattedItem.append(RegularNode.name());//Appends the name of the item first
                    FormattedItem.append("\t");
                    FormattedItem.append(
                            new Regular_Node(
                            csvValues[4],//Item
                            csvValues[2],//Zone
                            csvValues[3],//Cords
                            csvValues[5],//Extra
                            //End of baseItem
                            Integer.parseInt(csvValues[0]),//Level
                            csvValues[1]//Type
                    ));
                    FormattedItem.append("\n");
                }//Currently, this should never run.
                else {
                    //Looks through all items separated by CSV, Creates a new item, and then creates a new line with another new item.
                    for (String splitItem : splitItems) {
                        FormattedItem.append(RegularNode.name());//Appends the name of the item first
                        FormattedItem.append("\t");
                        FormattedItem.append(new Regular_Node(
                                splitItem, //Item
                                csvValues[2],//Zone
                                csvValues[3],//Cords
                                csvValues[5],//Extra
                                //End of baseItem
                                Integer.parseInt(csvValues[0]),//Level
                                csvValues[1]//Type
                        ));
                        FormattedItem.append("\n");
                    }
                }
                break;
            }
            case FolkLoreNode:{
                FormattedItem.append(FolkLoreNode.name());
                FormattedItem.append("\t");
                if(csvValues[3].equals(""))
                    csvValues[3] = "-1";//Edge case for when no value found

                if(csvValues[2].equals("Stonehard Water"))
                    csvValues[3] = "1";//Extreme edge case that I really don't want to deal with right now. Very not worth my time
                //See here for the item: https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes

                FormattedItem.append(new FolkLore_Node(
                        csvValues[2],//Item
                        csvValues[4],//Zone
                        csvValues[5],//Cords
                        csvValues[6],//Extra
                        //End of baseItem
                        csvValues[0],//FolkloreTome
                        csvValues[1],//Time
                        Integer.parseInt(csvValues[3])//Slot

                ));
                FormattedItem.append("\n");
                break;
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
                ));
                FormattedItem.append("\n");
                break;
            }
            case UnspoiledNode:{
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
                )
                );
                FormattedItem.append("\n");
                break;
            }
            case ARRUnspoiledNode:{
                FormattedItem.append(ARRUnspoiledNode.name());
                FormattedItem.append("\t");
                String[] slots = csvValues[2].split(",",-1);
                for(String slot: slots){
                    FormattedItem.append(new Unspoiled_Node(
                            csvValues[1],//Item
                            csvValues[3],//Zone
                            csvValues[4], //Cords
                            csvValues[5],//Extra
                            //End of baseItem
                            csvValues[0],//Time
                            Integer.parseInt(slot),//Slot
                            csvValues[6].length() //star
                    ));
                    FormattedItem.append("\n");
                }
                break;
            }//When an unspoiled node is an ARR one use this instead.

            case null:
                try {
                    throw new UnexpectedException("There should always be an item type assigned");
                } catch (UnexpectedException e) {
                    throw new RuntimeException(e);
                }
            case Delete:
            case Ignore:
                try {
                    throw new UnexpectedException("These items should not appear here, check the main formatter method.");
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
            File tmp = File.createTempFile("tmp", "");//Creates a tmp file to write to, then finally replaces the main file.
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
            String currentLine; //Current line
            String[] csvValues;//Current line read in as CSV in an array

            while((currentLine = br.readLine()) != null) {
                if(currentLine.isBlank())
                    continue;//Because I continue, nothing is ever written to the new file and hence can be ignored.
                csvValues = currentLine.split("\t",-1); //Load all values into an array. Used to normalize items

                switch (setCurrentType(currentLine)) { //Cases to find item type
                    //If header: Set a new ItemType
                    //Else if data, use cur item type.
                    case FolkLoreFishing, FolkLoreNode, RegularNode, UnspoiledNode, Delete -> {
                        //Nothing is written to the tmp file and therefore the final file.
                    }
                    case Ignore -> //Actual item data NOT a header
                            bw.write(formattedItem(csvValues));
                    case default -> throw new UnexpectedException("All cases should have been covered");
                }//End of switch statement
            }//End of while statement

            br.close();
            bw.close();

            File oldFile = new File(file);
            if (oldFile.delete())
                tmp.renameTo(oldFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}