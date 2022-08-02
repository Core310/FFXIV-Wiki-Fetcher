import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import scrapper.fileCreator.Formatter;
import scrapper.fileCreator.MakeFile;
import scrapper.fileCreator.Wikipages;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
/**
 * @see Wikipages
 */
public class main {
    static final String FileName = "XIVGather.TSV";
    public static void main(String[] args) throws IOException {
        searchFile("crayon fish");
    }

    /**
     * Test  method to search the file for certain values.
     */
    private static void searchFile(String itemnam) {
        FindItem findItem = new FindItem(new File("XIVGather.TSV"));
        ArrayList<StringBuilder> arr;


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
            doc = Jsoup.connect(wikipages.toString()).get();
            makeFile.setParsedPage(doc);//Fetches webpage data to extract
            makeFile.scrap();//extracts data and stores in argument file
        }//goes thru 'Links' array and sets the current element as a jsoup.doc to load into wikiscrapper
        fileWriter.close();

        Formatter formatter = new Formatter(FileName);
    }
}