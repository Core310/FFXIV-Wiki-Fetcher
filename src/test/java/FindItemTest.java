import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FindItemTest {
    FindItem findItem = new FindItem(new File("XIVGather.TSV"));
    @org.junit.jupiter.api.Test
    void HelperFindAllClosestValues() {
        ArrayList<String> expectedOutput;
        ArrayList<String> curTest;

        expectedOutput = new ArrayList<>();
        expectedOutput.add("REGULAR_NODE\t Lava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch");
        curTest = findItem.HelperFindAllClosestValues("lava tode");
        assertEquals(expectedOutput,curTest);

        expectedOutput.clear();
        expectedOutput.add("REGULAR_NODE\t Magma Beet\tThe Churning Mists\t(x20,y21)\t\t55\tLush Vegetation Patch");
        expectedOutput.add("REGULAR_NODE\t Fava Beans\tThe Azim Steppe\t(x33,12)\tNamazu Beast Tribe Required\t60\tLush Vegetation Patch");
        expectedOutput.add("REGULAR_NODE\tZelkova Log\tThe Lochs\t(x26,y9)\t\t70\tMature Tree");
        curTest = findItem.HelperFindAllClosestValues("lava getr");
        assertEquals(expectedOutput,curTest);
        //End of regular node tests

        expectedOutput.clear();
        expectedOutput.add("UNSPOILED_NODE\tRarefied Annite\tElpis\t(x8,y36)\t600 Min. Collectability\t90\tn/a\t2\t1\t10:00AM/PM");
        expectedOutput.add("UNSPOILED_NODE\tRarefied Pyrite\tThe Dravanian Forelands\t(x31,y32)\t600 Min. Collectability\t51\tn/a\t3\t0\t4:00 AM/PM");
        curTest = findItem.HelperFindAllClosestValues("rarefiend stuff");
        assertEquals(expectedOutput,curTest);

        expectedOutput.clear();
        //End of Unspoiled
    }

    @org.junit.jupiter.api.Test
    void findAnyMatching() {
        String curTest;
        curTest = findItem.findAnyMatching("Lava Tode");// Reg node test
        assertEquals("REGULAR_NODE\t Lava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch",curTest);//Should only have one close value matching
        curTest = findItem.findAnyMatching("r drk rye");// Unspoil node test
        assertEquals("REGULAR_NODE\t Dark Rye\tLabyrinthos\t(x29, y18)\t\t85\tLush Vegetation Patch",curTest);
        curTest = findItem.findAnyMatching(" Level 75 Miner Quest"); //A typo in https://ffxiv.consolegameswiki.com/wiki/Miner_Node_Locations.
        assertEquals("REGULAR_NODE\t Level 75 Miner Quest\tIl Mheg\t(x8,y20)\t\t75\tMineral Deposit",curTest);//LOOK AT ME
        /*
         IF THE ABOVE CASE FAILS THAT MEANS A THE TYPO HAS BEEN FIXED. Go to line 69~ of FindItem.java or find the following code:
                     if(curLine.equals("REGULAR_NODE\t Level 75 Miner Quest\tIl Mheg\t(x8,y20)\t\t75\tMineral Deposit")){}
         */

    }
}