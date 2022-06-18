package FuzzySearch;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.io.*;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Stack;

//THIS SHOULD go into a diff proj, mainly the discord bot itself as this proj is mainly for
//creating said CSV item file.

public class MainFuzzy { //fixme refractor to take out
    private File file; // File tto read from
    private int similarityThreshold = 95;// similarity when searching between 2 strings

    private Stack<String> currentStack = new Stack<>();
    private Stack<String> prevStack = new Stack<>();

    public MainFuzzy(File ItemFile){
        file = ItemFile;
    }

    /**
     * Input an item to FIND. Loops thru the constructor file, returning the least amount of objects found.
     * Psudo code below:
     * Algo as follows: Loop thru whole file and compare SearchKey w/ similarityThreshold. Store if meets conditions else cont.
     * Once reaches eof compare the last run array with the current. If last run null (is first run) or new result != 0 then run again
     * If current run is 0, return last run.
     * If current and last run is null or 0, similarityThreshold++
     * If return array > 5 (predefined value) return "Too vague!"
     *
     * @param SearchKey Finds similar value in given constructor file (should be item file).
     */
    public String SearchItem(String SearchKey){
        try {
            //This isn't a linked list b/c I only need the cur and prev stack

            String curLine;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((curLine = br.readLine()) != null){
                //If curLine and ratio of SearchKey does ! meet similarityThreshold
                if(FuzzySearch.ratio(curLine,SearchKey) < similarityThreshold)
                    continue;


                // Edge case: If similarityThreshold is 100, and more than 1 value is returned there must be a dupe
                else if (similarityThreshold == 100 && currentStack.size() >= 2)
                    throw new UnexpectedException("Duplicate in given file found");

                currentStack.push(curLine);
            } //end of while
            if(similarityThreshold <=90) return "Too vague!"; //Base case if string is too vague and doesnt match any.

            if(currentStack.empty()){
                similarityThreshold--;
                stackCounter++;
                SearchItem(SearchKey); //If stack is emptty
            }//fixme, what if executes first recur and then this is executed (if stack geq
            // 3 on last run and leq0 on this run.


            //Wrapup bits below
            br.close();
            if(currentStack.size() > 5) return "Too vague!"; //Final case to see if input is too vague.
            return stringBuilder.toString(); //todo wrap up the stack into a final output string

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSimilarityThreshold(int similarityThreshold) {
        this.similarityThreshold = similarityThreshold;
    }

    public void setFile(File file) {
        this.file = file;
    }
    public int getSimilarityThreshold() {
        return similarityThreshold;
    }

    public File getFile() {
        return file;
    }

    public static void main(String[] args) {
        String baseWikiUrl = "https://ffxiv.consolegameswiki.com/wiki/";
        int baseWikiUrlLength = baseWikiUrl.length();

        String searchKey = "Lava_Toad";
        String RealString = baseWikiUrl+searchKey;

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("https://ffxiv.consolegameswiki.com/wiki/lavatoad");
        arrayList.add("https://ffxiv.consolegameswiki.com/wiki/toadLava");
        arrayList.add("https://ffxiv.consolegameswiki.com/wiki/");
        arrayList.add("https://ffxiv.consolegameswiki.com/wiki/lavaegg");
        arrayList.add(RealString);
        if (RealString.length() == baseWikiUrlLength)
            System.out.println("No value found to inset, todo pre-defined error ");// TODO: 3/11/22  
        //above is just some base case that wuld prevent from looping thru whole list
        //then we loop thru the list

        ExtractedResult ratio = FuzzySearch.extractOne("lava toad",
                arrayList);
        System.out.println(ratio);
    }
}