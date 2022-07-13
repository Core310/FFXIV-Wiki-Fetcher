package Scrapper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Fetches data from given doc.
 *
 * Process:
 * Call constructor (load file to write to, use external filewriter).
 * Use setPrasedPage to set link to fetch data from
 * Call scrap to grab values
 * Scrap calls Store which uses several other private methodsx
 */
public class ScrapAndStore {
    private ArrayList<Elements> TableValues;//Table values
    private Document ParsedPage;//Current page parsed
    private final File file;
    private final FileWriter fileWriter;

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
            throw new UnsupportedOperationException("\n doc and file must be set (see setters)" +
                    "\nExample: wikiScrapper.setParsedPage(*jsoupdocument*);\n"
            );
        }//Base case if no setters are called
        TableValues = grabTable(ParsedPage);
        Store();
    }

    /**
     * Fetches table from given Document and loads each table into an array
     * @param document should always be the internal private doc
     * @return Arraylist of all Tables
     */
    private static ArrayList<Elements> grabTable(Document document)
    {
        ArrayList<Elements> Table = new ArrayList<>();

        for(Element element : document.select("tr") ) {
            Elements th = element.select("th");
            Elements td = element.select("td");
            if(th.text().length() > 1){
                Table.add(th);
            }//Removes extra line (see below)
            //For some reason, After the th is stored, an extra line is added in the regular file.
            Table.add(td);
        }

        return Table;
    }

    /**
     * Stores all elements fetched from the TDs array, nukes the constructor argument FILE,
     * and stores all the fetched data inside the said file.
     */
    private void Store() throws IOException {
            for (Elements elements : TableValues) {
                String elementText = String.join("\t",elements.eachText());//Grabs current arrayIndex and separates using delim
                fileWriter.write(elementText);//Current Table cell
                fileWriter.write("\n");//Line separator
            }
    }
    /**
     * Sets the current page to parse.
     * Call this method each time you want to scrap a page.
     * @param parsedPage Page to parse
     */
    public void setParsedPage(Document parsedPage) {
        this.ParsedPage = parsedPage;
    }

    public ArrayList<Elements> getTableValues() {
        return TableValues;
    }
}
