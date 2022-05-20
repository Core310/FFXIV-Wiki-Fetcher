package FuzzySearch;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.io.*;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Stack;

//THIS SHOULD go into a diff proj, mainly the discord bot itself as this proj is mainly for
//creating said CSV item file.

public class MainFuzzy {
    private File file;
    private int similarityThreshold = 95;
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
                    br = new BufferedReader(new FileReader(file));
                    //todo TEST if br = new BufferedReader would work.
                    //todo figure out how to set br to beginning of the file (above shold work?)
                }
                //If stack is geq 3 up the similarityThreshold and resetart
                else if (stack.empty()) {
                    throw new //todo create too vague similarityThreshold
                }

                stack.push(curLine);
            }

            //So the stack is at most 2 (if 3 exe last elif case
            StringBuilder stringBuilder = new StringBuilder();
            while (!stack.empty()) stringBuilder.append(stack.pop() + "\n");

            br.close(); //finally case
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