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

@SuppressWarnings("ALL")
/**
 * Used to build the data file and manually test it.
 * @see Wikipages
 */
public class Main {
    static final String FileName = "src/main/resources/XIVGather.TSV";

    public static void main(String[] args) throws IOException {
        ListFinder.addItem(new String[]{"clod jelfish", "crim trou", "rarefield reef roc", "yugar salmon", "giantpiel"});
        System.out.println(ListFinder.outPut());
    }

    /**
     * Test method to make the file
     */
    private static void makeFile() throws IOException {
        File XIVGather = new File(FileName);
        FileWriter fileWriter = new FileWriter(XIVGather, false);
        MakeFile makeFile = new MakeFile(XIVGather, fileWriter);
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