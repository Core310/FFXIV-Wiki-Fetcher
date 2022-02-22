package Scrapper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Fetches data from given doc,
 */
public class ScrapAndStore {
    private ArrayList<Elements> TDs;//TDs
    private Document ParsedPage;//Current page parsed
    private File file;
    private FileWriter fileWriter;

    /**
     * After creating this object, you must set the doc (setParsedPage)
     * @param file File to store into.
     */
    public ScrapAndStore(File file, FileWriter fileWriter) {
        this.file = file;
        this.fileWriter = fileWriter;
    }

    /**
     * This function should be called when ready to run all the methods inside this class (so after setters are made).
     */
    public void scrap() throws IOException {
        if(ParsedPage == null || file == null){
            throw new UnsupportedOperationException("\ndoc and file must be set (see setters)" +
                    "\nExample: wikiScrapper.setParsedPage(*jsoupdocument*);\n"
            );
        }//Base case if no setters are called
        TDs = getTDs(ParsedPage);
        Store();
    }

    /**
     * Fetches table from given Document and loads each col into an array
     * @param document should always be the internal private doc
     * @return Arraylist of THs (column)
     */
    private ArrayList<Elements> getTDs(Document document)
    {
        boolean firstSkipped = false;

        ArrayList<Elements> tds = new ArrayList<>();

        for(Element element : document.select("tr") ) {
            // Skip the first 'tr' tag since it's the header
            if (!firstSkipped) {//shou
                firstSkipped = true;
                continue;
            }

            Elements td = element.select("td");
            tds.add(td);
        }

        return tds;
    }
    /**
     * Stores all elements fetched from the TDs array, nukes the constructor argument FILE,
     * and stores all the fetched data inside the said file.
     */
    private void Store() throws IOException {
            for (Elements elements : TDs) {
                if(elements.eachText().size() <=1) continue;
                String elementText = String.join(",",elements.eachText());
                //elements.eachText() comes out as a regular string. The String.join function
                //deletes each space and replaces it with a ',' to convert it into csv.
                fileWriter.write(elementText);
                fileWriter.write(System.getProperty("line.separator"));

            }
    }// TODO: 2/21/22 Load TH first and then TD, with TD, Make sure that TD is made clear which will hopefully be able 2 tell what item type dealing w/

    /**
     * Sets the current page to parse.
     * Call this method each time you want to scrap a page.
     * @param parsedPage Page to parse
     */
    public void setParsedPage(Document parsedPage) {
        this.ParsedPage = parsedPage;
    }


}
