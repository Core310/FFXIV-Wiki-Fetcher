package ffxivWikiFinder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import fileBuilder.fileCreator.Formatter;
import fileBuilder.fileCreator.MakeFile;
import fileBuilder.fileCreator.Wikipages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("ALL")
/**
 * Used to build the data file and manually test it.
 * @see Wikipages
 */
public class Main {
    static final String FileName = "src/main/resources/XIVGather.TSV";

    public static void main(String[] args) throws IOException {
        
        //ListFinder.addItem(new String[]{"clod jelfish", "crim trou", "rarefield reef roc", "yugar salmon", "giantpiel"});
        ListFinder.addItem("fishie");
        System.out.println(ListFinder.outPut());
        /*
                        This should build off the current libary and make into a simple API. So the only thing that changes here is making formatGroupedZones public. Then make another part of this
         We query listFinder input from API string stream. Then we return the ListFinder formatGroupedZones as a json object. The json object is then given to the end node to be delt with
         however. The input could be in a table form, then just press tab to go next.
                 */
    }

    private static void makeFile() throws IOException {
        File XIVGather = new File(FileName);
        FileWriter fileWriter = new FileWriter(XIVGather, false);
        MakeFile makeFile = new MakeFile(fileWriter);
        Document doc;//jsoup doc
        for (Wikipages wikipages : Wikipages.values()) {
            String link = Jsoup.clean(wikipages.toString(), Safelist.basic());//*may* produce a bug. Delete this line if tes cases do not run.
            doc = Jsoup.connect(link).get();
            makeFile.setParsedPage(doc);//Fetches webpage data to extract
            makeFile.scrap();//extracts data and stores in argument file
        }//goes thru 'Links' array and sets the current element as a jsoup.doc to load into wikiscrapper
        fileWriter.close();

        Formatter formatter = new Formatter(FileName);
    }
}