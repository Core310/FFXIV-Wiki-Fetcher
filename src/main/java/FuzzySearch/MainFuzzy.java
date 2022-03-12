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

    public String SearchItem(String SearchKey){
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