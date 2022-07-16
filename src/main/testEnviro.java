import FuzzySearch.FindItem;

import java.io.File;

public class testEnviro {

    public static void main(String[] args) {
        FindItem findItem = new FindItem(new File ("XIVGather.TSV"));
        System.out.println(findItem.findAllClosestValues("Lava Tode"));
    }
}
