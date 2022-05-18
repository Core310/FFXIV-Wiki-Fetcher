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
    MainFuzzy(File ItemFile){
        file = ItemFile;
    }// TODO: 12/31/2021 LATER, what shuld args be?

    /**
     * @param SearchKey Finds closest value in given constructor file (should be item file).
     *
     */
    public String SearchItem(String SearchKey){
        try {
            Stack<String> stack = new Stack<>();
            String curLine;
            int similarityThreshold = 95;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((curLine = br.readLine()) != null){
                if(FuzzySearch.ratio(curLine,SearchKey) < similarityThreshold) continue; //If ratio between both strings is less than similarityThreshold skip
                else if (similarityThreshold == 100 && stack.size() >= 2) {
                    throw new UnexpectedException("Duplicate in given file found");
                } //If similarityThreshold is 100 AND stack has 2 or more there must be a duplicate in the file
                else if (stack.size() >= 3) {
                    similarityThreshold++;
                    br = new BufferedReader(File)
                    //todo TEST if br = new BufferedReader would work.
                    //todo figure out how to set br to beginning of the file
                }//If stack is geq 3 up the similarityThreshold and resetart


                stack.push(curLine);

                //Create an array to store all values above 95. If > 3 then reset
                //and
            }



            br.close(); //finally case
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;// TODO: 3/11/22  
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