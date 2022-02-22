package Scrapper;

import Items.Item;
import Items.Regular_Node;

import java.io.File;
import java.io.FileNotFoundException;
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

    public void SortByItemName(){


        /*
        //should sort file by item name, MUST be run after being formatted.
        //Should I load the whole entire file into an arraylist/array and take
        //up a massivea mnt of memory? It's only run once so shuld b fine?
         */
    }

    private ArrayList<String> getRecordFromLine(String line) {
        ArrayList<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
/*

 */
    public void format(){
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");   //sets the delimiter pattern
            ArrayList<String> CurrentLine = new ArrayList<>();
            Item curItem;
            while (scanner.hasNextLine()) {// TODO: 2/9/22 find out how to load each line into array, overwrite it, and then
            CurrentLine = getRecordFromLine(scanner.nextLine());

            switch (CurrentLine.size()){
                /**
                 *                 case 6: {//Regular Node case
                 *                     curItem = new Regular_Node(
                 *                             CurrentLine.get(4),
                 *                             CurrentLine.get(2),
                 *                             CurrentLine.get(0),
                 *                             CurrentLine.get(0000),
                 *                             CurrentLine.get(5)
                 *                         //Now somehow replace the current line with curItem
                 *                     );//Repeat for the rest of the item cases.
                 *
                 *                 }
                 */

                case 7: {//FolkLore Node Case

                }
                case 9: {//Unspoiled Nodes

                }
            }

            /*
                        String ItemName,
            String TP,
            int Level,
            String WikiLink,
            String extra
             */
                /*
                Load each line into an array
                Load array into an ITEM type
                Copy ITEM type to replace the current line
                Go to the next line and loop until scanner has no more
                See here: https://www.baeldung.com/java-csv-file-array
                 */

            }
            // TODO: 2/3/2022 Format the file so that each item name is displayed first, and every item follows the ITEM class format
        }
         /* Wiki tables:
        Regular nodes
        0)Level
        1)Type
        2)TP
        3)Cords
        4)Name
        5)Extra

        Unspoiled Nodes:
       1)Time
       2)Item name
       3)Slot (delete)
       4)TP
       5)Cords
       6)Level
       7)Star
       8)FolkLore
       9)Extra

       Folklore Nodes:
        1)FolkLore Tome (delete)
        2)Time
        3)Item Name
        4)Slot (delete)
        5)TP
        6)cords
        7)Used to make (delete)

         */
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }}

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
        if(string.equals(prevString)) return true;
        return false;
    }

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
