import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Thingy to find: ");
        String part = "";
        String wikiPage = "https://ffxiv.consolegameswiki.com/wiki/Botanist_Node_Locations";
        String gatheringPage = "https://na.finalfantasyxiv.com/lodestone/playguide/db/gathering/";
        Document doc = Jsoup.connect(wikiPage + part).get();//Possible to get a webpage that is super close 2 it?
        // like auto google smtn

        boolean firstSkipped = false;

        ArrayList<Elements> tds = new ArrayList<Elements>();

        for(Element element : doc.select("tr") ) {
            // Skip the first 'tr' tag since it's the header
            if (!firstSkipped) {
                firstSkipped = true;
                continue;
            }

            Elements td = element.select("td");
            tds.add(td);
        }

        System.out.println(tds);
    }
}