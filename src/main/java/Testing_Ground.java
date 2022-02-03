import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Testing_Ground {
    private static ArrayList<Elements> getTDs(Document document)
    {
        boolean firstSkipped = false;

        ArrayList<Elements> tds = new ArrayList<>();

        for(Element element : document.select("tr") ) {
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

    private static void StoreCSV(String string, FileWriter fileWriter){
        try {
            fileWriter.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("TC.csv");

         String regNode = "[Level][Type][Zone][Coordinate][Items][Extra][Gathering][Botanist][Miner]";
         //System.out.println("Thingy to find: ");
         String part = "";
         String wikiPage = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes";
         String Reegular_Node_Page = "https://ffxiv.consolegameswiki.com/wiki/Mining_Node_Locations";
         String gatheringPage = "https://na.finalfantasyxiv.com/lodestone/playguide/db/gathering/";
         Document doc = Jsoup.connect(wikiPage).get();//Possible to get a webpage that is super close 2 it?
         // like auto google smtn
        String ItemType;
         ArrayList<Elements> TDs = getTDs(doc);

        }
    }