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
            if (!firstSkipped) {//shou
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
        ArrayList<Elements> ths = new ArrayList<>();

        for(Element element : document.select("table") ) {
            Elements thead = element.select("th");
            ths.add(thead);
        }
        return ths;
    }

    private static ArrayList<Elements> getTableKey(Document document)
    {

        ArrayList<Elements> Table = new ArrayList<>();
        for(Element element : document.select("tr") ) {

            Elements td = element.select("td");
            Table.add(td);

            Elements th = element.select("th");
            Table.add(th);
        }

        return Table;
    }

    static final String FileName = "XIVGatherTest.TSV";

    public static void main(String[] args) throws IOException {

            File test = new File(FileName);
            FileWriter fileWriter = new FileWriter(test,false);
            Document doc ;//jsoup doc

        doc = Jsoup.connect("https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes").get();
        //System.out.println(getTHs(doc));
        for (Elements elements : getTableKey(doc)) {

            String elementText = String.join("\t", elements.eachText());
            //System.out.println(elementText);
            fileWriter.write(elementText);
            fileWriter.write("\n");
        }
            fileWriter.close();
    }
    }