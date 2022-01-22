import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Testing_Ground {

    private static ArrayList<Elements> getTHs(Document doc)
    {
        boolean firstSkipped = false;

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

    private static String typeFinder(Elements elements){
            if(elements.text().contains("★")){
                return "Star_Node";
            }
            else if(elements.text().contains("AM") || elements.text().contains("PM")){
                return "TimeBased_Node";
            }
            return "Regular_Node";
    }

    public static void main(String[] args) throws IOException {
         String regNode = "[Level][Type][Zone][Coordinate][Items][Extra][Gathering][Botanist][Miner]";
         //System.out.println("Thingy to find: ");
         String part = "";
         String wikiPage = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes";
         String Reegular_Node_Page = "https://ffxiv.consolegameswiki.com/wiki/Mining_Node_Locations";
         String gatheringPage = "https://na.finalfantasyxiv.com/lodestone/playguide/db/gathering/";
         Document doc = Jsoup.connect(wikiPage).get();//Possible to get a webpage that is super close 2 it?
         // like auto google smtn

         ArrayList<Elements> ths = getTHs(doc);
         ArrayList<Elements> TDs = getTDs(doc);
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append(TDs.get(0).eachText());
         //System.out.println(typeFinder(TDs.get(2)));
         System.out.println(TDs.get(0).eachText().get(0));

        for(String str: elements.eachText()){
        }
    }
}