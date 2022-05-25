package FuzzySearch;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.io.*;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Stack;

//THIS SHOULD go into a diff proj, mainly the discord bot itself as this proj is mainly for
//creating said CSV item file.

public class MainFuzzy { //fixme this is broken!
    private File file; // File tto read from
    private int similarityThreshold = 95;// similarity when searching between 2 strings
    private int  stackCounter =0; // Counter to tell how many times stack size and empty cases are called
    MainFuzzy(File ItemFile){
        file = ItemFile;
    }// TODO: 12/31/2021 LATER, what shuld args be?

    /**
     * @param SearchKey Finds similar value in given constructor file (should be item file).
     * Input an item to FIND. Loops thru the constructor file, returning at most 2 objects
     */
    public String SearchItem(String SearchKey){
        try {
            Stack<String> stack = new Stack<>();
            String curLine;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((curLine = br.readLine()) != null){
                if(FuzzySearch.ratio(curLine,SearchKey) < similarityThreshold)
                    continue;

                //If ratio between both strings is less than similarityThreshold skip
                else if (similarityThreshold == 100 && stack.size() >= 2)
                    throw new UnexpectedException("Duplicate in given file found");

                //If similarityThreshold is 100 AND stack has 2 or more there must be a duplicate in the file
                else if (stack.size() >= 3) {


                    similarityThreshold++;
                    stackCounter++;
                    SearchItem(SearchKey);//fixme test this recusive function
                }
                //If stack is geq 3 up the similarityThreshold and play tthe function again
                stack.push(curLine);
            } //end of while
            if(similarityThreshold <=90) return "Too vague!"; //Base case if string is too vague and doesnt match any.

            if(stack.empty()){
                similarityThreshold--;
                stackCounter++;
                SearchItem(SearchKey); //If stack is emptty
            }//fixme, what if executes first recur and then this is executed (if stack geq
            // 3 on last run and leq0 on this run.

            //So the stack is at most 2 (if 3 exe last elif case
            StringBuilder stringBuilder = new StringBuilder();
            while (!stack.empty()) stringBuilder.append(stack.pop() + "\n");

            br.close(); //finally case
            stackCounter =0; //Clear stack counter for the next round.
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSimilarityThreshold(int similarityThreshold) {
        this.similarityThreshold = similarityThreshold;
    }

    public int getSimilarityThreshold() {
        return similarityThreshold;
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