import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import scrapper.fileCreator.Formatter;
import scrapper.fileCreator.MakeFile;
import scrapper.fileCreator.Wikipages;

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
        ListFinder listFinder = new ListFinder();
        listFinder.addItem(new String[]{"Fire sh", "ic shrd", "water sha", "wind sha", "orange", "Cock Feather", "Cloves", "Black Pepper", "Nepto Dragon"});
        System.out.println(listFinder.toString());
        FindItem fi = new FindItem();
        System.out.println(fi.essentialFindAllClosestAsMap("Wind Shard").get(0)); //DELETEME
    }

    /*
    private static void searchMap(String itemName){
        FindItem fi  = new FindItem();
        for(LinkedHashMap<String,String> lm: fi.findAllClosestAsMap(itemName)){
            System.out.println(lm.keySet());
            System.out.println(lm.values());
        }
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

    private static void searchFile(String itemnam) {
        FindItem findItem = new FindItem();
        ArrayList<StringBuilder> arr;
        //findItem.setNumberOfDuplicateItems(1);
        for(String tmp: findItem.essentialFindAllClosestAsMap(itemnam)){
            System.out.println(tmp);
        };
    }
     */

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