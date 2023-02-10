package scrapper.fileCreator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Fetches data from given doc (link).
 * <p> Without Formatter.java, builds raw data (useful for debugging).
 * <p>
 * Process:
 * <p> Call constructor (load file to write to, use external filewriter).
 * <p> Use setPrasedPage to set link to fetch data from
 * <p> Call scrap to grab values
 * <p> Scrap calls Store internally and finally writes to file.
 *
 * @see Formatter
 */
public class MakeFile {
    private final File file;
    private final FileWriter fileWriter;
    private ArrayList<Elements> TableValues;//Table values
    private Document ParsedPage;//Current page parsed

    /**
     * After creating this object, you must set the doc (setParsedPage)
     *
     * @param file File to store into.
     */
    public MakeFile(File file, FileWriter fileWriter) {
        this.file = file;
        this.fileWriter = fileWriter;
    }

    /**
     * Helps create arraylist TableValues.
     * Fetches table from given Document and loads each table into an array. This includes blank table values (values with no text).
     *
     * @param doc should always be the internal private doc
     * @return Arraylist of all Tables
     */
    private static ArrayList<Elements> grabTable(Document doc) {
        ArrayList<Elements> Table = new ArrayList<>();//Return arrayList
        for (Element curTD : doc.select("td")) {
            if (!curTD.hasText()) {
                curTD.append("\u00a0");//That append (\u00a0) creates a blank space
            }
        }//This for loops deals with empty values in the table. Loops through all TDs, see if empty. If so appends a blank value.

        for (Element element : doc.select("tr")) {
            Elements th = element.select("th");
            Elements td = element.select("td");
            if (th.text().length() > 1) {
                Table.add(th);
            }//Removes extra line (see below)
            //For some reason, After the th is stored, an extra line is added in the regular file.
            Table.add(td);
        }//The main for loop that adds values to the final return arrayList by looking through each table row.
        return Table;
    }

    /**
     * This function should be called when ready to run all the methods inside this class (so after setters are made).
     * Helps create TableValues and calls Store();
     */
    public void scrap() throws IOException {
        if (ParsedPage == null || file == null) {
            throw new UnsupportedOperationException(
                    """
                             doc and file must be set (see setters)
                            Example: wikiScrapper.setParsedPage(*jsoupdocument*);
                            """
            );
        }//Base case if no setters are called
        TableValues = grabTable(ParsedPage);
        Store();
    }

    /**
     * Stores all elements fetched from the TDs array, nukes the constructor argument FILE,
     * and stores all the fetched data inside the said file.
     */
    private void Store() throws IOException {
        for (Elements elements : TableValues) {
            String elementText = String.join("\t", elements.eachText());//Grabs current arrayIndex and separates using delim
            fileWriter.write(elementText);//Current Table cell
            fileWriter.write("\n");//Line separator
        }
    }

    /**
     * Sets the current page to parse.
     * Call this method each time you want to scrap a page.
     *
     * @param parsedPage Page to parse
     */
    public void setParsedPage(Document parsedPage) {
        this.ParsedPage = parsedPage;
    }
}
