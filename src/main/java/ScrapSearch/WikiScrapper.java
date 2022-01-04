package ScrapSearch;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Fetches data from given doc
 */
public class WikiScrapper {
    //Wiki Links
    private final String FolkLoreNodeWiki = //Includes all DOL classes
            "https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes#Miner";
    private final String EphemeralNodes = //Includes all DOL classes
            "https://ffxiv.consolegameswiki.com/wiki/Ephemeral_Nodes#Botanist";
    // TODO: UNUSED 1/3/2022 To add after eveyrthing else
    private final String MiningNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Mining_Node_Locations";
    private final String MiningUnspoiledNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes";
    private final String BNTNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Botanist_Node_Locations";
    private final String BNTUnspoiledNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Botanist_Nodes";
    //Item type arrays
    private final String RegularNode = "[Level][Type][Zone][Coordinate][Items][Extra][Gathering][Botanist][Miner]";
    private final String StarNode = "[Time][Item][Slot #][Location][Coordinate][Level][Star][Folklore][Additional Info]";
    //todo If no star is present, then make it into a TimeBasedNode. If a string starts with FolkLore, skip it
    private String[] Links = new String[]{FolkLoreNodeWiki,MiningNodeWiki,
            MiningUnspoiledNodeWiki,BNTNodeWiki,BNTUnspoiledNodeWiki};//Not adding Emph nodes 4 now

    private final ArrayList<Elements> TDs;

    WikiScrapper(Document doc){
        TDs = getTDs(doc);
    }// TODO: 12/31/2021 not finished

    /**
     * Used for Regular_Nodes where there are CSVs in one cell
     * Reads argument CSV and outputs in an arrayList
     * @param string Argument to pass
     * @return Arraylist by CSVs
     */
    private ArrayList<String> readWikiCSVTableLine(String string){
        ArrayList<String> arrayList = new ArrayList<>();
        if(!string.contains(",")) {
            arrayList.add(string);
            return arrayList;
        }//base case if no commmas

        Scanner scanner = new Scanner(string);
        scanner.useDelimiter(",");
        while (scanner.hasNext()){
            arrayList.add(scanner.next());
        }
        return arrayList;
    }// TODO: 1/2/2022 test if works

    /**
     * Fetches table from given Document and loads each col into an array
     * @param doc jsoup html document
     * @return Each Element is the column
     */
    private ArrayList<Elements> getTDs(Document doc)
    {
        boolean firstSkipped = false;

        ArrayList<Elements> tds = new ArrayList<>();

        for(Element element : doc.select("tr") ) {
            // Skip the first 'tr' tag since it's the header
            if (!firstSkipped) {
                firstSkipped = true;
                continue;
            }

            Elements td = element.select("td");
            tds.add(td);
        }

        return tds;
    }

    /**
     * Fetches header for given table
     * @param doc jsoup Document to perform on
     * @return 
     */
    private static ArrayList<Elements> getTHs(Document doc)
    {
        ArrayList<Elements> tds = new ArrayList<>();

        for(Element element : doc.select("th") ) {
            // Skip the first 'tr' tag since it's the header

            Elements th = element.select("th");
            //Elements td = element.select("td");
            //tds.add(td);
            tds.add(th);
        }

        return tds;
    }

    private void Store(){
        //Given TDs array, find out what item each thing is by looking at the length?

        // TODO: 12/31/2021 loop thru TDs and have a testrun at
        //  storing data in CSV filee

    }

}
