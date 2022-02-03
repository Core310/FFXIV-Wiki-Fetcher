import ScrapSearch.WikiScrapper;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class main {
    static final String FolkLoreNodeWiki = //Includes all DOL classes
            "https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes#Miner";
    static final String EphemeralNodes = //Includes all DOL classes, todo figure out how to implement
            "https://ffxiv.consolegameswiki.com/wiki/Ephemeral_Nodes#Botanist";
    static final String MiningNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Mining_Node_Locations";
    static final String MiningUnspoiledNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes";
    static final String BNTNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Botanist_Node_Locations";
    static final String BNTUnspoiledNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Botanist_Nodes";
    //Item type arrays
    static final String RegularNode = "[Level][Type][Zone][Coordinate][Items][Extra][Gathering][Botanist][Miner]";
    //todo DELETE (old cont,) If no star is present, then make it into a TimeBasedNode. If a string starts with FolkLore, skip it
    //above is all pages that are prased currently
    static File file = new File("XIVGatherCSV.csv");//This way the file should always be overwritten
    public static void main(String[] args) throws IOException {
        String[] Links = new String[]{
                FolkLoreNodeWiki,
                MiningNodeWiki,
                MiningUnspoiledNodeWiki,
                BNTNodeWiki,
                BNTUnspoiledNodeWiki
        };//Not adding Emph nodes 4 now
        File test = new File("XIVGatherCSV.csv");
        FileWriter fileWriter = new FileWriter(test,false);
        WikiScrapper wikiScrapper = new WikiScrapper(test,fileWriter);
        Document doc;//jsoup doc
        for(String string: Links){
            doc = new Document(string);
            wikiScrapper.setParsedPage(doc);//Fetches webpage data to extract
            wikiScrapper.scrap();//extracts data and stores in argument file
        }//goes thru 'Links' array and sets the current element as a jsoup.doc to load into wikiscrapper
        fileWriter.close();
        sortFile(file);
        //that SHOULD be all the code in main file as file is made and sorted and ready to be read.
    }

    static void sortFile(File file){
        //todo should sort file by item name and then store all the letter indexs somewhere
    }

}