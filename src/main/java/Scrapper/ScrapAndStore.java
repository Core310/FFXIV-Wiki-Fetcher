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
    private ArrayList<Elements> TableValues;//TDs
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
            throw new UnsupportedOperationException("\n doc and file must be set (see setters)" +
                    "\nExample: wikiScrapper.setParsedPage(*jsoupdocument*);\n"
            );
        }//Base case if no setters are called
        TableValues = grabTable(ParsedPage);
        Store();
    }

    /**
     * Fetches table from given Document and loads each col into an array
     * @param document should always be the internal private doc
     * @return Arraylist of THs (column)
     */
    private static ArrayList<Elements> grabTable(Document document)
    {
        ArrayList<Elements> Table = new ArrayList<>();

        for(Element element : document.select("tr") ) {
            Elements th = element.select("th");
            Elements td = element.select("td");
            if(th.text().length() > 1){
                Table.add(th);
            }
            //For some reason, After the th is stored, an extra line is added in the regular file,this removes that extra line I think
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

                // TODO: 3/9/22 Does Table Header = the current TH value? If not, write and set TH to it.

                //if(elements.eachText().size() <=1) continue;
                String elementText = String.join("\t",elements.eachText());
                fileWriter.write(elementText);//Current Table cell
                fileWriter.write("\n");//Line seperator

            }
    }//
     // TODO: 2/21/22 Load TH first and then TD, with TD, Make sure that TD is made clear which will hopefully be able 2 tell what item type dealing w/

    /**
     * Sets the current page to parse.
     * Call this method each time you want to scrap a page.
     * @param parsedPage Page to parse
     */
    public void setParsedPage(Document parsedPage) {
        this.ParsedPage = parsedPage;
    }


}
