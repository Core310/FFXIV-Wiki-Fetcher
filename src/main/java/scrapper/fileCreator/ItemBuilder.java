package scrapper.fileCreator;

import scrapper.readers.items.*;

import static scrapper.readers.items.baseNode.StaticItemTypes.*;

/**
 * Formats items inputted by csvValues in the switch case statement of formatter
 *
 * @see Formatter
 */
public class ItemBuilder {
    private final StringBuilder stringBuilder = new StringBuilder();

    protected StringBuilder build_REGULAR_NODE(String[] csvValues) {
        String[] splitItems = csvValues[4].split(",", -1);//Splits all items into an array to process
        if (splitItems.length == 1) {
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
                if (splitItem.charAt(0) == ' ')
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
        return stringBuilder;
    }

    protected StringBuilder build_FOLK_LORE_NODE(String[] csvValues) {
        stringBuilder.append(FOLK_LORE_NODE.name());
        stringBuilder.append("\t");
        if (csvValues[3].equals(""))
            csvValues[3] = "-1";//Edge case for when no value found

        if (csvValues[2].equals("Stonehard Water"))
            csvValues[3] = "1";//Extreme edge case that I really don't want to deal with right now. Very not worth my time
        //See here for the item: https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes

        stringBuilder.append(new FolkLore_Node(
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
        return stringBuilder;
    }

    protected StringBuilder build_FOLK_LORE_FISH_NODE(String[] csvValues) {

        stringBuilder.append(FOLK_LORE_FISH_NODE.name());
        stringBuilder.append("\t");
        stringBuilder.append(new FolkLore_Fish_Node(
                csvValues[2],//Item
                csvValues[3],//Zone
                csvValues[4],//Cords
                csvValues[5],//Extra
                //End of baseItem
                csvValues[0],////FolkloreTome
                csvValues[1]//Time
        ));
        stringBuilder.append("\n");
        return stringBuilder;
    }

    protected StringBuilder build_UNSPOILED_NODE(String[] csvValues) {

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
        return stringBuilder;
    }

    protected StringBuilder build_ARR_UNSPOILED_NODE(String[] csvValues) {
        String[] slots = csvValues[2].split(",", -1);
        for (String slot : slots) {
            if (slot.charAt(0) == ' ')
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
        return stringBuilder;
    }

    protected StringBuilder build_FISH_NODE(String[] csvValues) {

        String[] fish = csvValues[4].split(",", -1);
        for (String curFish : fish) {
            stringBuilder.append(FISH_NODE);
            stringBuilder.append("\t");
            if (curFish.charAt(0) == ' ')
                curFish = curFish.replaceFirst(" ", "");


            csvValues[3] = csvValues[3].replaceAll("\\)", "");//Replace the ')' in the cords
            //See here https://ffxiv.consolegameswiki.com/wiki/Fishing_Locations
            String[] zoneAndCords = csvValues[3].split("\\(");//Make cords and zone seperate
            //zone = 0, cords =1
            if (zoneAndCords.length != 2)
                throw new ArrayIndexOutOfBoundsException("Array must be at most length of 2");
            stringBuilder.append(new Fish_Node(
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
        return stringBuilder;
    }

    protected StringBuilder build_FISH_BIG_NODE(String[] csvValues) {
        stringBuilder.append(FISH_BIG_NODE);
        stringBuilder.append("\t");
        stringBuilder.append(new Fish_Big_Node(
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
        return stringBuilder;
    }

    protected StringBuilder build_FISH_COLLECTABLES_NODE(String[] csvValues) {

        stringBuilder.append(FISH_COLLECTABLES_NODE);
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
        return stringBuilder;
    }

    protected StringBuilder build_EPHEMERAL_NODE(String[] csvValues) {
        System.out.println(csvValues[4]);//used for debugging
        if (csvValues[4].contains(",")) {
            String[] items = csvValues[4].split(",", -1);
            for (String curItem : items) {
                stringBuilder.append(EPHEMERAL_NODE);
                stringBuilder.append("\t");
                stringBuilder.append(new Ephemeral_Node(
                        curItem,
                        csvValues[1],//zone
                        csvValues[2],//cords
                        csvValues[5],//extra
                        csvValues[0],//time
                        csvValues[3]//tp
                ));
                stringBuilder.append("\n");
            }
        } else {
            stringBuilder.append(EPHEMERAL_NODE);
            stringBuilder.append("\t");

            stringBuilder.append(new Ephemeral_Node(
                    csvValues[4],//item feature have for loop for mutli items
                    csvValues[1],//zone
                    csvValues[2],//cords
                    csvValues[5],//extra
                    csvValues[0],//time
                    csvValues[3]//tp

            ));
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    protected StringBuilder build_EPHEMERAL_FISH_NODE(String[] csvValues) {
        stringBuilder.append(EPHEMERAL_FISH_NODE);
        stringBuilder.append("\t");
        stringBuilder.append(new Ephemeral_Fish_Node(
                csvValues[4],//fish
                csvValues[1],//zone
                csvValues[2],//cord
                csvValues[7],//extra
                csvValues[3],//tp
                csvValues[5],//weather
                csvValues[6],//bait
                csvValues[0]//conditions
        ));
        stringBuilder.append("\n");
        return stringBuilder;
    }

}
