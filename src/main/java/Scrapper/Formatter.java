package Scrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Formats a given argument file for reading. Should be called after the file is written into.
 */
public class Formatter {
    private final File file;
    Formatter(File file){
        this.file = file;
    }

    public void SortByItemName(){


        /*
        //should soort file by item name
        //Should this load the whole entire file into an arraylist/array and take
        //up a massivea mnt of memory? It's only run once so shuld b fine?
         */
    }

    public void format(){
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");   //sets the delimiter pattern
            while (scanner.hasNext()) {
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
         /*
        Regular nodes
        1)Level
        2)Type
        3)TP
        4)Cords
        5)Name
        6)Extra

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
    public boolean removeHeader(String string){
        String DuplicateStandardHeader = "Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes,Regular Nodes Unspoiled Nodes Ephemeral Nodes Folklore Nodes,Fishing Locations Fishing Collectables Folklore Nodes";
        if(string.equals(DuplicateStandardHeader)) return true;
        return false;
    }//Has O(1) Time as should be called when indexing thru file

    public boolean removeDuplicate(String string, String prevStriing){
        if(string.equals(prevStriing)) return true;
        return false;
    }

}
