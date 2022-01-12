import ScrapSearch.WikiScrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        final String FolkLoreNodeWiki = //Includes all DOL classes
                "https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes#Miner";
        final String EphemeralNodes = //Includes all DOL classes, todo figure out how to implement
                "https://ffxiv.consolegameswiki.com/wiki/Ephemeral_Nodes#Botanist";
        final String MiningNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Mining_Node_Locations";
        final String MiningUnspoiledNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes";
        final String BNTNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Botanist_Node_Locations";
        final String BNTUnspoiledNodeWiki = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Botanist_Nodes";
        //Item type arrays
        final String RegularNode = "[Level][Type][Zone][Coordinate][Items][Extra][Gathering][Botanist][Miner]";
        final String StarNode = "[Time][Item][Slot #][Location][Coordinate][Level][Star][Folklore][Additional Info]";
        //todo If no star is present, then make it into a TimeBasedNode. If a string starts with FolkLore, skip it
        String[] Links = new String[]{FolkLoreNodeWiki,MiningNodeWiki,
                MiningUnspoiledNodeWiki,BNTNodeWiki,BNTUnspoiledNodeWiki};//Not adding Emph nodes 4 now
        //above is all pages that are prased currently

        WikiScrapper wikiScrapper = new WikiScrapper();


    }
    void sortFile(File file){
        //todo should sort file by item name and then store all the letter indexs somewhere
    }

}