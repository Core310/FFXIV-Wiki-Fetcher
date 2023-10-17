package fileBuilder.fileCreator;

import fileBuilder.readers.items.baseNode.StaticItemTypes;

import java.io.*;

import static fileBuilder.readers.items.baseNode.StaticItemTypes.*;

/**
 * Puts an ITEM tag infront of each item for ease of reading and formats every item.
 * <br> Formats each item to fit the reading format
 * <p>Order of methods run: formatFile -> setCurrentType -> formattedItem</p>
 * Simply call the constructor to run this class.
 *
 * @see ItemBuilder
 * @see MakeFile
 */
public class Formatter {
    private final String file;//File to read/write
    private StaticItemTypes itemType; //Current Enum Item Type

    /**
     * Default constructor, should be the only one needed.
     *
     * @param file File to run on.
     */
    public Formatter(String file) {
        this.file = file;
        formatFile();
    }

    /**
     * A helper method for formattedItem()
     * Looks at current line, if is a header that describes an item type, sets global var itemType to whatever that current item is.
     * If it is not an item, such as an ending header, or is data, it will return either Delete, or Ignore where Ignore = data.
     *
     * @param curLine Current line to look at
     * @return One of the StaticItemTypes
     */
    private StaticItemTypes setCurrentType(String curLine) {
        switch (curLine) {
            case "Folklore Tome\tTime\tItem\tSlot\tLocation\tCoordinates\tUsed to make" -> {
                itemType = FOLK_LORE_NODE;
                return DELETE;
            }
            case "Folklore Tome\tTime\tItem\tLocation\tCoordinates\tAdditional Info" -> {
                itemType = FOLK_LORE_FISH_NODE;
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
            case "Time\tItem\tSlot #\tLocation\tCoordinate\tExtra\tStar" -> {
                itemType = ARR_UNSPOILED_NODE;
                return DELETE;
            }
            case "Fishing Log\tLevel\tType\tCoordinates\tFish\tBait Used" -> {
                itemType = FISH_NODE;
                return DELETE;
            }
            case "Fish\tZone\tFishing Hole\t(X,Y)\tEorzea Time\tWeather\tBait\tMooch\tGathering\tDesynth Rewards" -> {
                itemType = FISH_BIG_NODE;
                return DELETE;
            }
            case "Item\tMin. Collectability\tLocation\tCatch Method\tTime/Weather\tScrips\tAdditional Info" -> {
                itemType = FISH_COLLECTABLES_NODE;
                return DELETE;
            }
            case "Time\tZone\tCoordinates\tClosest Teleport\tItems\tItems gained from Aetherial Reduction" -> {
                itemType = EPHEMERAL_NODE;
                return DELETE;
            }
            case "Conditions\tZone\tCoordinates\tClosest Teleport\tFish Name\tWeather\tBait\tItems gained from Aetherial Reduction" -> {
                itemType = EPHEMERAL_FISH_NODE;
                return DELETE;
            }
            case "Aethersand wanted\tGained from" -> {
                itemType = SKIP;
                return DELETE;
            }

            //Ignore cases below possible checkme?
            case "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tRegular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes\tFishing Locations Big Fishing Fishing Collectables Folklore Fish",
                    "Botanist\tMiner\tFisher",
                    "Gathering",
                    "Name\tPoints\tTask\tReward\tPatch",
                    "" -> {
                return DELETE;
            }
        }
        return IGNORE;
    }

    /**
     * A helper method to format().
     * itemType Determined from an item return value when setCurrentType returns Ignore (data to extract). (See StaticItemTypes method)
     *
     * @return New line that should replace the old line.
     * @see ItemBuilder
     */
    private String formattedItem(String[] csvValues) {
        ItemBuilder itemBuilder = new ItemBuilder();
        StringBuilder stringBuilder = new StringBuilder(); //String to replace the current line read in
        switch (itemType) {
            case REGULAR_NODE -> {
                stringBuilder = itemBuilder.build_REGULAR_NODE(csvValues);
            }
            case FOLK_LORE_NODE -> {
                stringBuilder = itemBuilder.build_FOLK_LORE_NODE(csvValues);
            }
            case FOLK_LORE_FISH_NODE -> {
                stringBuilder = itemBuilder.build_FOLK_LORE_FISH_NODE(csvValues);
            }
            case UNSPOILED_NODE -> {
                stringBuilder = itemBuilder.build_UNSPOILED_NODE(csvValues);
            }
            case ARR_UNSPOILED_NODE -> {
                stringBuilder = itemBuilder.build_ARR_UNSPOILED_NODE(csvValues);
            }//When an unspoiled node is an ARR one use this instead.
            case FISH_NODE -> {
                stringBuilder = itemBuilder.build_FISH_NODE(csvValues);
            }
            case FISH_BIG_NODE -> {
                stringBuilder = itemBuilder.build_FISH_BIG_NODE(csvValues);
            }
            case FISH_COLLECTABLES_NODE -> {
                stringBuilder = itemBuilder.build_FISH_COLLECTABLES_NODE(csvValues);
            }
            case EPHEMERAL_NODE -> {
                stringBuilder = itemBuilder.build_EPHEMERAL_NODE(csvValues);
            }
            case EPHEMERAL_FISH_NODE -> {
                stringBuilder = itemBuilder.build_EPHEMERAL_FISH_NODE(csvValues);
            }
            case DELETE, IGNORE ->
                    throw new IllegalArgumentException("These items should not appear here, check the main formatter method.");
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
    private void formatFile() {
        try {
            File tmp = File.createTempFile("tmp", "");//Creates a tmp file to write to, then finally replaces the main file.
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
            String currentLine; //Current line
            String[] csvValues;//Current line read in as CSV in an array

            while ((currentLine = br.readLine()) != null) {
                if (currentLine.isBlank())
                    continue;//Because I continue, nothing is ever written to the new file and hence can be ignored.
                csvValues = currentLine.split("\t", -1); //Load all values into an array. Used to normalize items

                switch (setCurrentType(currentLine)) { //Cases to find item type
                    //If header: Set a new ItemType
                    //Else if data, use cur item type.
                    case DELETE -> {
                        //Nothing is written to the tmp file and therefore the final file.
                    }
                    case IGNORE ->{ //Actual item data NOT a header
                            bw.write(formattedItem(csvValues));
                    }
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