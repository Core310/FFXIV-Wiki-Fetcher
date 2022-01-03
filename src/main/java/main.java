import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    private static ArrayList<Elements> getTDs(Document doc)
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


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Thingy to find: ");
        String part = "";
        String wikiPage = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes";
        String gatheringPage = "https://na.finalfantasyxiv.com/lodestone/playguide/db/gathering/";
        Document doc = Jsoup.connect(wikiPage + part).get();//Possible to get a webpage that is super close 2 it?
        // like auto google smtn

        ArrayList<Elements> tds = getTDs(doc);
        for(Elements i : tds) {
            System.out.println(i.eachText());
        }
        System.out.println(tds.get(0).eachText().get(0));//pos 1 prints out time
        //TimeBased_Nodes unspoiled_nodes = new TimeBased_Nodes("ree","w1",1,"ree2","xtra","5" ,5,"ree");

    }
}