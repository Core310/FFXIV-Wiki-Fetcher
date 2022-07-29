import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import scrapper.fileCreator.Formatter;
import scrapper.fileCreator.MakeFile;
import scrapper.fileCreator.Wikipages;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class main {
    static final String FileName = "XIVGather.TSV";
    public static void main(String[] args) throws IOException {
        searchFile("Basilosaurus");
    }

    /**
     * Test  method to search the file for certain values.
     */
    private static void searchFile(String itemnam) {
        FindItem findItem = new FindItem(new File("XIVGather.TSV"));
        ArrayList<String> arr;


        arr = findItem.findAllClosest(itemnam);
        for (String a : arr) {
            System.out.println(a);
        }
        System.out.println("--- findCloestAsMap");

        System.out.println(findItem.findAllClosestAsMap(itemnam));

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
        formatter.formatFile();
    }
}