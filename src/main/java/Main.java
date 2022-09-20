import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import scrapper.fileCreator.Formatter;
import scrapper.fileCreator.MakeFile;
import scrapper.fileCreator.Wikipages;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@SuppressWarnings("ALL")
/**
 * Used to build the data file and manually test it.
 * @see Wikipages
 */
public class Main {
    static final String FileName = "src/main/resources/XIVGather.TSV";
    public static void main(String[] args) throws IOException {
        ArrayList<String> al = new ArrayList<>();
        FindItem fi = new FindItem();
        ListFinder listFinder = new ListFinder();
        //listFinder.addItem(new String[]{"Shark Tuna", " "});
        //listFinder.addItem(new String[]{"Fire sh", "ic shrd","water sha", "wind sha", "orange"});
        searchMap("Shark Tuna");
        searchAllRaw("Shark Tuna");
        searchMap("crayon fish");//FIXME 19/9/2022 Value not updated in loop
    }
    private static void searchMap(String itemName){
        FindItem fi  = new FindItem();
        for(LinkedHashMap<String,String> lm: fi.findAllClosestAsMap(itemName))
            System.out.println(lm.keySet() +"\n" + lm.values());

    }

    private static void searchAllRaw(String itemName){
        FindItem fi = new FindItem();
        //String base[] = fi.findAllClosest(itemName).get(0).split("\t",-1);
        //String base2[] = fi.findAllClosest(itemName).get(1).split("\t",-1);
        //String t = (base[1].replaceAll(" ","") + " " + base[2].replaceAll(" ",""));
        //String t1= (base2[1].replaceAll(" ","") + " " + base2[2].replaceAll(" ",""));
        //System.out.println(t1 + "\n" + t);
        for(String cur: fi.findAllClosest(itemName))
            System.out.println(cur);
    }

    /**
     * Test  method to search the file for certain values.
     */
    private static void searchFile(String itemnam) {
        FindItem findItem = new FindItem();
        ArrayList<StringBuilder> arr;
        //findItem.setNumberOfDuplicateItems(1);
        for(StringBuilder tmp: findItem.essentialFindAllClosestAsMap(itemnam)){
            System.out.println(tmp);
        };
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