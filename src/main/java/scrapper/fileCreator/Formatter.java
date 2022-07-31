package scrapper.fileCreator;

import scrapper.readers.items.*;
import scrapper.readers.items.baseNode.StaticItemTypes;

import java.io.*;
import java.rmi.UnexpectedException;

import static scrapper.readers.items.baseNode.StaticItemTypes.*;

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
                itemType = FOLK_LORE_NODE;
                return DELETE;
            }
            case "Folklore Tome\tTime\tItem\tLocation\tCoordinates\tAdditional Info" -> {
                itemType = FOLK_LORE_FISHING_NODE;
                return DELETE;
            }
            case "Level\tType\tZone\tCoordinate\tItems\tExtra" -> {
                itemType = REGULAR_NODE;
                return DELETE;
            }
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tLevel\tStar\tAdditional Info" -> {
                itemType = UNSPOILED_NODE;
                return DELETE;
            }
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tExtra\tStar" ->{
                itemType = ARR_UNSPOILED_NODE;
                return DELETE;
            }
            case "Fishing Log\tLevel\tType\tCoordinates\tFish\tBait Used" ->{
                itemType = FISHING_NODE;
                return DELETE;
            }
            case "Fish\tZone\tFishing Hole\t(X,Y)\tEorzea Time\tWeather\tBait\tMooch\tGathering\tDesynth Rewards" ->{
                itemType = BIG_FISH_NODE;
                return DELETE;
            }
            case "Item\tMin. Collectability\tLocation\tCatch Method\tTime/Weather\tScrips\tAdditional Info" ->{
                itemType = FISHING_COLLECTABLES_NODE;
                return DELETE;
            }

            //Ignore cases below possible checkme?
            case "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tRegular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tFishing Locations Big Fishing Fishing Collectables Folklore Fish",
                    "Botanist\tMiner\tFisher",
                    "Gathering",
                    ""
                    -> {
                return DELETE;
            }
        }
        return IGNORE;
    }

    /**
     * A helper method to format().
     * itemType Determined from an item return value when setCurrentType returns Ignore (data to extract). (See StaticItemTypes method)
     * @return New line that should replace the old line.
     */
    private String formattedItem(String[] csvValues){
        StringBuilder stringBuilder = new StringBuilder(); //String to replace the current line read in
        switch (itemType){
            case REGULAR_NODE:{// TODO: 7/26/22 Put all cases into an its own seperate function
                String[] splitItems = csvValues[4].split(",",-1);//Splits all items into an array to process
                if(splitItems.length == 1){
                    stringBuilder.append(REGULAR_NODE.name());//Appends the name of the item first
                    stringBuilder.append("\t");
                    stringBuilder.append(
                            new Regular_Node(
                            csvValues[4],//Item
                            csvValues[2],//Zone
                            csvValues[3],//Cords
                            csvValues[5],//Extra
                            //End of baseItem
                            Integer.parseInt(csvValues[0]),//Level
                            csvValues[1]//Type
                    ));
                    stringBuilder.append("\n");
                }//Currently, this should never run.
                else {
                    //Looks through all items separated by CSV, Creates a new item, and then creates a new line with another new item.
                    for (String splitItem : splitItems) {
                        stringBuilder.append(REGULAR_NODE.name());//Appends the name of the item first
                        stringBuilder.append("\t");
                        if(splitItem.charAt(0) == ' ')
                            splitItem = splitItem.replaceFirst(" ", "");
                        stringBuilder.append(new Regular_Node(
                                splitItem, //Item
                                csvValues[2],//Zone
                                csvValues[3],//Cords
                                csvValues[5],//Extra
                                //End of baseItem
                                Integer.parseInt(csvValues[0]),//Level
                                csvValues[1]//Type
                        ));
                        stringBuilder.append("\n");
                    }
                }
                break;
            }
            case FOLK_LORE_NODE:{
                stringBuilder.append(FOLK_LORE_NODE.name());
                stringBuilder.append("\t");
                if(csvValues[3].equals(""))
                    csvValues[3] = "-1";//Edge case for when no value found

                if(csvValues[2].equals("Stonehard Water"))
                    csvValues[3] = "1";//Extreme edge case that I really don't want to deal with right now. Very not worth my time
                //See here for the item: https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes

                stringBuilder.append(new FolkLore_NodeNode(
                        csvValues[2],//Item
                        csvValues[4],//Zone
                        csvValues[5],//Cords
                        csvValues[6],//Extra
                        //End of baseItem
                        csvValues[0],//FolkloreTome
                        csvValues[1],//Time
                        Integer.parseInt(csvValues[3])//Slot
                ));
                stringBuilder.append("\n");
                break;
            }
            case FOLK_LORE_FISHING_NODE:{
                stringBuilder.append(FOLK_LORE_FISHING_NODE.name());
                stringBuilder.append("\t");
                stringBuilder.append(new FolkLore_Fishing_Node(
                        csvValues[2],//Item
                        csvValues[3],//Zone
                        csvValues[4],//Cords
                        csvValues[5],//Extra
                        //End of baseItem
                        csvValues[0],////FolkloreTome
                        csvValues[1]//Time
                ));
                stringBuilder.append("\n");
                break;
            }
            case UNSPOILED_NODE:{
                stringBuilder.append(UNSPOILED_NODE.name());
                stringBuilder.append("\t");
                stringBuilder.append(new Unspoiled_Node(
                        csvValues[1],//Item
                        csvValues[3],//Zone
                        csvValues[4], //Cords
                        csvValues[7],//Extra
                        //End of baseItem
                        Integer.parseInt(csvValues[5]),//level
                        csvValues[0],//Time
                        Integer.parseInt(csvValues[2]),//Slot
                        csvValues[6].length() //star
                )
                );
                stringBuilder.append("\n");
                break;
            }
            case ARR_UNSPOILED_NODE:{
                String[] slots = csvValues[2].split(",",-1);
                for(String slot: slots){
                    if(slot.charAt(0) == ' ')
                        slot = slot.replaceFirst(" ", "");

                    stringBuilder.append(ARR_UNSPOILED_NODE.name());
                    stringBuilder.append("\t");
                    stringBuilder.append(new Unspoiled_Node(
                            csvValues[1],//Item
                            csvValues[3],//Zone
                            csvValues[4], //Cords
                            csvValues[5],//Extra
                            //End of baseItem
                            csvValues[0],//Time

                            Integer.parseInt(slot),//Slot
                            csvValues[6].length() //star
                    ));
                    stringBuilder.append("\n");
                }
                break;
            }//When an unspoiled node is an ARR one use this instead.
            case FISHING_NODE:{
                String[] fish = csvValues[4].split(",",-1);
                for(String curFish: fish){
                    stringBuilder.append(FISHING_NODE );
                    stringBuilder.append("\t");
                    if(curFish.charAt(0) == ' ')
                        curFish = curFish.replaceFirst(" ", "");


                    csvValues[3] = csvValues[3].replaceAll("\\)","");//Replace the ')' in the cords
                    //See here https://ffxiv.consolegameswiki.com/wiki/Fishing_Locations
                    String[] zoneAndCords = csvValues[3].split("\\(");//Make cords and zone seperate
                    //zone = 0, cords =1
                    if(zoneAndCords.length != 2)
                        try {
                            throw new UnexpectedException("Array must be atmost length of 2");
                        } catch (UnexpectedException e) {
                            throw new RuntimeException(e);
                        }
                    stringBuilder.append(new Fishing_Node(
                    curFish,//fish
                    zoneAndCords[0], //Zone
                    zoneAndCords[1], //Cords
                    csvValues[5],//baitUsed

                    csvValues[2],//type
                    csvValues[0],//Fishing log
                    Integer.parseInt(csvValues[1])//level
                    ));
                    stringBuilder.append("\n");
                }
                break;
            }

            case BIG_FISH_NODE:{
                stringBuilder.append(BIG_FISH_NODE );
                stringBuilder.append("\t");
                stringBuilder.append(new BigFish_Node(
                csvValues[0],//fish
                csvValues[1],//Zone
                csvValues[3],//Cords
                csvValues[9],//desynthRewards

                csvValues[2],//fishingHole,
                csvValues[4],//ezoraTime,
                csvValues[5],//weather,
                csvValues[6],//bait,
                csvValues[7],//mooch,
                csvValues[8] //gathering
                ));
                stringBuilder.append("\n");
                break;
            }
            case FISHING_COLLECTABLES_NODE:{
                stringBuilder.append(FISHING_COLLECTABLES_NODE );
                stringBuilder.append("\t");
                stringBuilder.append(new Fish_Collectable_Node(
                csvValues[0],//Item
                csvValues[2],//Zone
                "n/a",//Cords
                csvValues[6],//Extra

                csvValues[1],//minCollectability
                csvValues[3],//catchMethod
                csvValues[4],//timeWeather
                csvValues[5] //scripts
                    ));
                stringBuilder.append("\n");
                break;
            }

            case DELETE:
            case IGNORE:
                try {
                    throw new UnexpectedException("These items should not appear here, check the main formatter method.");
                } catch (UnexpectedException e) {
                     throw new RuntimeException(e);
                }
        } //End of switch case
        return stringBuilder.toString();
    }

    /**
     * Formats the file by: Goes through line by line.
     * <p>[StaticItemTypes Method]
     * Searches for table HEADERS in wiki which determines the current ITEMTYPE (internal var). This is obtained from the StaticItemTypes method.
     * <p>Eg. Gather header -> each item is now a Gather item.
     * <p>When a data line is found (normal table values) will execute
     * <p>[obtainItemString Method]
     * <p>This will format the current line according to the ITEMTYPE.
     * <p>Then this method will replace the current line <p>
     * <p>Reads line by line with a Buffered reader and writer, putts into queue, and replaces each line.
     * <p>This method creates several duplicate items and has another method called later on.
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
                    case DELETE -> {
                        //Nothing is written to the tmp file and therefore the final file.
                    }
                    case IGNORE -> //Actual item data NOT a header
                            bw.write(formattedItem(csvValues));
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