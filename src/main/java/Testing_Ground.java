import Scrapper.ScrapAndStore;
import Scrapper.Wikipages;
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

    private static ArrayList<Elements> getTHs(Document document)
    {
        boolean firstSkipped = false;

        ArrayList<Elements> ths = new ArrayList<>();

        for(Element element : document.select("tr") ) {
            Elements thead = element.select("th");
            ths.add(thead);
        }
        return ths;
    }

    private static ArrayList<Elements> getTableKey(Document document)
    {
        boolean firstSkipped = false;

        ArrayList<Elements> tds = new ArrayList<>();

        for(Element element : document.select("table") ) {
            Elements td = element.select("th");
            tds.add(td);
        }

        return tds;
    }

    static final String FileName = "XIVGather.csv";
    static File file = new File(FileName);//This way the file should always be overwritten

    public static void main(String[] args) throws IOException {

            File test = new File(FileName);
            FileWriter fileWriter = new FileWriter(test,false);
            ScrapAndStore scrapAndStore = new ScrapAndStore(test,fileWriter);
            Document doc ;//jsoup doc

        doc = Jsoup.connect("https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Botanist_Nodes").get();
        for (Elements elements : getTableKey(doc)) {
            String elementText = String.join(",", elements.eachText());
            System.out.println(elementText);
        }

            for(Wikipages wikipages: Wikipages.values()){

            }//goes thru 'Links' array and sets the current element as a jsoup.doc to load into wikiscrapper
            fileWriter.close();
    }
    }