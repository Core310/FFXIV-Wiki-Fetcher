package Scrapper;

import Items.Item;
import Items.Regular_Node;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

    /**
     * Loads current line?
     * @param line
     * @return
     */
    private ArrayList<String> getRecordFromLine(String line) {
        ArrayList<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter("\t");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
/*

 */
    public void format(ArrayList<Elements> TableValues){
        for (Elements element: TableValues){
            switch (element.text()){
                case "Folklore Tome\tTime\tItem\tSlot\tLocation\tCoordinates\tUsed to make\n":{

                }

            }

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
