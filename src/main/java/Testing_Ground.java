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
    private static ArrayList<Elements> getTableKey(Document document)
    {
        ArrayList<Elements> Table = new ArrayList<>();

        for(Element element : document.select("tr") ) {
            Elements th = element.select("th");
            Elements td = element.select("td");
            if(th.text().length() >= 1){
                Table.add(th);
            }
            //For some reason, After the th is stored, an extra line is added in the regular file,this removes that extra line I think
            Table.add(td);
        }

        return Table;
    }

    static final String FileName = "XIVGatherTest.TSV";

    public static void main(String[] args) throws IOException {
        //todo test itr thru and print each as dataType



        /**
         *
         *             File test = new File(FileName);
         *             FileWriter fileWriter = new FileWriter(test,false);
         *             Document doc ;//jsoup doc
         *
         *         doc = Jsoup.connect("https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes").get();
         *         for (Elements elements : getTableKey(doc)) {
         *             String elementText = String.join("\t", elements.eachText());
         *             //System.out.println(elementText);
         *             fileWriter.write(elementText);
         *             fileWriter.write("\n");//A must to seperate line
         *         }
         *             fileWriter.close();
         */

    }
}