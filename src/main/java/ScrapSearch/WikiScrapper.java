package ScrapSearch;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Fetches data from given doc. Doc is from a webppage.
 */
public class WikiScrapper {

    private ArrayList<Elements> TDs;//TDs
    private ArrayList<Elements> THs;//THs

    private Document doc;//Current page parsed
    private File file;

    /**
     * After calling the default constructor, you must run setDoc and setFile
     */
    public WikiScrapper(){}


    /**
     * This function should be called when ready to run all the methods inside this class (so after setters are made).
     */
    public void scrap(){
        if(doc == null || file == null){
            throw new UnsupportedOperationException("doc and file must be set (see setters)");
        }//Base case if no setters are called
        TDs = getTDs(doc);
        THs = getTHs(doc);
        Store();
        //todo should call all methods, be the "starter" to store and scrap all data
    }

    /**
     * Used for Regular_Nodes where there are CSVs in one cell
     * Reads argument CSV and outputs in an arrayList
     * @param string Argument to pass
     * @return Arraylist by CSVs
     */
    private ArrayList<String> readWikiCSVTableLine(String string){
        ArrayList<String> arrayList = new ArrayList<>();
        if(!string.contains(",")) {
            arrayList.add(string);
            return arrayList;
        }//base case if no commmas

        Scanner scanner = new Scanner(string);
        scanner.useDelimiter(",");
        while (scanner.hasNext()){
            arrayList.add(scanner.next());
        }
        return arrayList;
    }// TODO: 1/2/2022 test if works

    /**
     * Fetches table from given Document and loads each col into an array
     * @param document should always be the internal private doc
     * @return Ararylist of THs (column)
     */
    private ArrayList<Elements> getTDs(Document document)
    {
        boolean firstSkipped = false;

        ArrayList<Elements> tds = new ArrayList<>();

        for(Element element : document.select("tr") ) {
            // Skip the first 'tr' tag since it's the header
            if (!firstSkipped) {
                firstSkipped = true;
                continue;
            }

            Elements td = element.select("td");
            tds.add(td);
        }

        return tds;
    }

    /**
     * Fetches header for given table
     * @param document should always be the internal private doc
     * @return arraylist of THs(headers)
     */
    private ArrayList<Elements> getTHs(Document document)
    {
        ArrayList<Elements> tds = new ArrayList<>();

        for(Element element : document.select("th") ) {
            // Skip the first 'tr' tag since it's the header

            Elements th = element.select("th");
            //Elements td = element.select("td");
            //tds.add(td);
            tds.add(th);
        }

        return tds;
    }

    private void Store(){
        //Given TDs array, find out what item each thing is by looking at the length?

        // TODO: 12/31/2021 loop thru TDs and have a testrun at
        //  storing data in CSV filee

    }// TODO: 1/12/2022  


    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
