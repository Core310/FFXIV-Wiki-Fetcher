import Scrapper.ScrapAndStore;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class main {
    static File file = new File("XIVGatherCSV.csv");//This way the file should always be overwritten
    public static void main(String[] args) throws IOException {
        File test = new File("XIVGatherCSV.csv");
        FileWriter fileWriter = new FileWriter(test,false);
        ScrapAndStore scrapAndStore = new ScrapAndStore(test,fileWriter);
        Document doc ;//jsoup doc
        for(Wikipages wikipages: Wikipages.values()){
            doc = Jsoup.connect(wikipages.toString()).get();
            scrapAndStore.setParsedPage(doc);//Fetches webpage data to extract
            scrapAndStore.scrap();//extracts data and stores in argument file
        }//goes thru 'Links' array and sets the current element as a jsoup.doc to load into wikiscrapper
        fileWriter.close();
        sortFile(file);
        //that SHOULD be all the code in main file as file is made and sorted and ready to be read.
    }

    static void sortFile(File file){
        //todo should sort file by item name and then store all the letter indexs somewhere
    }

}