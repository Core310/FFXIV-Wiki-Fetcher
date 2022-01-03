import Items.Regular_Node;
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

        for(Element element : doc.select("th") ) {
            // Skip the first 'tr' tag since it's the header

            Elements th = element.select("th");
            //Elements td = element.select("td");
            //tds.add(td);
            tds.add(th);
        }

        return tds;
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Thingy to find: ");
        String part = "";
        String wikiPage = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes";
        String Reegular_Node_Page = "https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Botanist_Nodes";
        String gatheringPage = "https://na.finalfantasyxiv.com/lodestone/playguide/db/gathering/";
        Document doc = Jsoup.connect(Reegular_Node_Page + part).get();//Possible to get a webpage that is super close 2 it?
        // like auto google smtn

        ArrayList<Elements> tds = getTDs(doc);

        for(int i =0;i<9;i++) System.out.println(tds.get(i).eachText());
        //TimeBased_Nodes unspoiled_nodes = new TimeBased_Nodes("ree","w1",1,"ree2","xtra","5" ,5,"ree");
        // TODO: 1/3/2022 Find out how to grab headers in table
    }
}