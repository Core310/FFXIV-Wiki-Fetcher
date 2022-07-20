import scrapper.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class main {
    static final String FileName = "XIVGather.TSV";
    public static void main(String[] args) {
        searchFile();
    }

    /**
     * Test  method to search the file for certain values.
     */
    private static void searchFile(){
        FindItem findItem = new FindItem(new File ("XIVGather.TSV"));
        ArrayList<String> arr;

        arr = findItem.HelperFindAllClosestValues("lava tode");
        for(String a: arr){
            System.out.println(a);
        }
        arr = findItem.HelperFindAllClosestValues("lava getr");
        for(String b: arr){
            System.out.println(b);
        }

        //findItem.findAllClosestValues("lava getr");
    }

    /**
     * Test method to make the file
     */
    private static void makeFile() throws IOException {
        File XIVGather = new File(FileName);
        FileWriter fileWriter = new FileWriter(XIVGather,false);
        ScrapAndStore scrapAndStore = new ScrapAndStore(XIVGather,fileWriter);
        Document doc ;//jsoup doc
        for(Wikipages wikipages: Wikipages.values()){
            doc = Jsoup.connect(wikipages.toString()).get();
            scrapAndStore.setParsedPage(doc);//Fetches webpage data to extract
            scrapAndStore.scrap();//extracts data and stores in argument file
        }//goes thru 'Links' array and sets the current element as a jsoup.doc to load into wikiscrapper
        fileWriter.close();

        Formatter formatter = new Formatter(FileName);
        formatter.formatFile();
    }
}