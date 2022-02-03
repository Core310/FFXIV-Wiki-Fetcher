package FuzzySearch;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.io.File;
import java.util.ArrayList;

//THIS SHOULD go into a diff proj, mainly the discord bot itself as this proj is mainly for
//creating said CSV item file.

public class MainFuzzy {
    private File Partsfile;
    MainFuzzy(File Partsfile){this.Partsfile = Partsfile;
    }// TODO: 12/31/2021 LATER, what shuld args be?

    /**
     * @param SearchKey Finds closest value in given constructor file (should be item file).
     *                  
     */
  /*
    public String SearchItem(String SearchKey){
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(Partsfile));// TODO: 1/27/22 Find how to extract item name and row nubers out of table
            //for loop over the letter index given depending on the first letter of the searchKey
            //itr linear thru whole index getting all the weights. If a weight ='s 100 OR 99,  print it.
            //Else take the highest value. If two values = eg. 2 values equal 96%, print both. If more
            //than 2 values, throw error
            ArrayList<Integer> ComparedValues = new ArrayList<>();// TODO: 1/27/22 Convert to a max-heap so no sort has to be done each time
            char LetterIdx = SearchKey.charAt(0);
            int STARTIDX = 0; // TODO: 1/26/22 assign to starting index of file based on LetterIdx., delete me when done
            int ENDIDX =1; // TODO: 1/26/22 Assign to ending idx based on Letteridx, just put the numbers below in the for loop
            for(int i =0;i<99999;i++){
                int value = FuzzySearch.ratio(SearchKey,);//Then the second value will be the file at i index
                if(value >=99): return ;// TODO: 1/27/22 return the current object at file index
                ComparedValues.add(value);
            }
            Collections.sort(ComparedValues);


            return ComparedValues.get(0);

        } catch (FileNotFoundException e) {
            System.out.println("Expected File in SearchItem");
            e.printStackTrace();
        }
    }
   */// TODO: 2/2/2022  

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
            System.out.println("No value found to inset, todo pre-defined error ");
        //above is just some base case that wuld prevent from looping thru whole list
        //then we loop thru the list

        ExtractedResult ratio = FuzzySearch.extractOne("lava toad",
                arrayList);
        System.out.println(ratio);
    }
}