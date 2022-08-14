import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import scrapper.fileCreator.Formatter;
import scrapper.fileCreator.MakeFile;
import scrapper.fileCreator.Wikipages;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
/**
 * Used to build the data file and manually test it.
 * @see Wikipages
 */
public class main {
    static final String FileName = "src/main/resources/XIVGather.TSV";
    public static void main(String[] args) throws IOException {
        searchFile("fingers");
    }

    /**
     * Test  method to search the file for certain values.
     */
    private static void searchFile(String itemnam) {
        FindItem findItem = new FindItem(new File(FileName));
        ArrayList<StringBuilder> arr;
        //findItem.setNumberOfDuplicateItems(1);
        System.out.println(findItem.essentialFindAllClosestAsMap(itemnam));


    }

    /**
     * Test method to make the file
     */
    private static void makeFile() throws IOException {
        File XIVGather = new File(FileName);
        FileWriter fileWriter = new FileWriter(XIVGather,false);
        MakeFile makeFile = new MakeFile(XIVGather,fileWriter);
        Document doc ;//jsoup doc
        for(Wikipages wikipages: Wikipages.values()){
            String link = Jsoup.clean(wikipages.toString(), Safelist.basic());//*may* produce a bug. Delete this line if tes cases do not run.
            doc = Jsoup.connect(link).get();
            makeFile.setParsedPage(doc);//Fetches webpage data to extract
            makeFile.scrap();//extracts data and stores in argument file
        }//goes thru 'Links' array and sets the current element as a jsoup.doc to load into wikiscrapper
        fileWriter.close();

        Formatter formatter = new Formatter(FileName);
    }
}