import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FindItemTest {
    FindItem findItem = new FindItem(new File("XIVGather.TSV"));
    @org.junit.jupiter.api.Test
    void findAllClosestAsMap(){

    }

    @org.junit.jupiter.api.Test
    void findAllClosest() {
        ArrayList<String> expectedOutput;
        ArrayList<String> curTest;

        expectedOutput = new ArrayList<>();
        expectedOutput.add("REGULAR_NODE\t Lava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch");
        curTest = findItem.findAllClosest("lava tode");
        assertEquals(expectedOutput,curTest);

        expectedOutput.clear();
        expectedOutput.add("REGULAR_NODE\t Magma Beet\tThe Churning Mists\t(x20,y21)\t\t55\tLush Vegetation Patch");
        expectedOutput.add("REGULAR_NODE\t Fava Beans\tThe Azim Steppe\t(x33,12)\tNamazu Beast Tribe Required\t60\tLush Vegetation Patch");
        expectedOutput.add("REGULAR_NODE\tZelkova Log\tThe Lochs\t(x26,y9)\t\t70\tMature Tree");
        curTest = findItem.findAllClosest("lava getr");
        assertEquals(expectedOutput,curTest);

        expectedOutput.clear();
        expectedOutput.add("REGULAR_NODE\tBeech Log\tThe Fringes\t(x10,y16)\t\t65\tMature Tree");
        expectedOutput.add("REGULAR_NODE\tGem Algae\tThe Ruby Sea\t(x26,y19)\t\t65\tLush Vegetation Patch");
        curTest = findItem.findAllClosest("emye log");
        assertEquals(expectedOutput,curTest);
        //End of regular node tests

        expectedOutput.clear();
        expectedOutput.add("UNSPOILED_NODE\tRarefied Annite\tElpis\t(x8,y36)\t600 Min. Collectability\t90\tn/a\t2\t1\t10:00AM/PM");
        expectedOutput.add("UNSPOILED_NODE\tRarefied Pyrite\tThe Dravanian Forelands\t(x31,y32)\t600 Min. Collectability\t51\tn/a\t3\t0\t4:00 AM/PM");
        curTest = findItem.findAllClosest("rarefiend stuff");
        assertEquals(expectedOutput,curTest);
        //End of Unspoiled

        expectedOutput.clear();
        expectedOutput.add("ARR_UNSPOILED_NODE\tWaterfowl Feather (Rare)\tWestern La Noscea\t(x34,y28)\t\t-1\tn/a\t1\t2\t8:00 AM");
        expectedOutput.add("ARR_UNSPOILED_NODE\tWaterfowl Feather (Rare)\tWestern La Noscea\t(x34,y28)\t\t-1\tn/a\t5\t2\t8:00 AM");
        curTest = findItem.findAllClosest("Waterfowl Feather (Rare)");
        assertEquals(expectedOutput,curTest);
        //End of ARRUnspoiled

        expectedOutput.clear();
        expectedOutput.add("FOLK_LORE_NODE\tSandalwood Log\tThe Rak'tika Greatwood\t(x24,y36)\tSandalwood Lumber\t2AM/PM\tVrandtic\t6");
        expectedOutput.add("FOLK_LORE_NODE\tSandalwood Sap\tThe Rak'tika Greatwood\t(x24,y36)\tPliable Glass Fiber\t2AM/PM\tVrandtic\t4");
        curTest = findItem.findAllClosest("Sandalwood");
        assertEquals(expectedOutput,curTest);
        //End of folklore

        //end of folkFish
    }

    @org.junit.jupiter.api.Test
    void findAnyMatching() {
        String curTest;
        curTest = findItem.findAnyMatching("Lava Tode");// Reg node test
        assertEquals("REGULAR_NODE\t Lava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch",curTest);//Should only have one close value matching
        curTest = findItem.findAnyMatching("r drk rye");// Unspoil node test
        assertEquals("REGULAR_NODE\t Dark Rye\tLabyrinthos\t(x29, y18)\t\t85\tLush Vegetation Patch",curTest);
        curTest = findItem.findAnyMatching(" Level 75 Miner Quest");
        //^^ A typo in https://ffxiv.consolegameswiki.com/wiki/Miner_Node_Locations. This is typo is already accounted for in FindItem.java
        //End of reg nodee

        curTest = findItem.findAnyMatching("rarefield gyrr aabania");
        assertEquals("UNSPOILED_NODE\tRarefied Gyr Abanian Alumen\tThe Fringes\t(x32,y31)\t600 Min. Collectability\t80\tn/a\t2\t1\t10:00 AM/PM",curTest);
        curTest = findItem.findAnyMatching("adamite ore");
        assertEquals("UNSPOILED_NODE\tAdamantite Ore\tAzys Lla\t(x24,y6)\tPerception 408\t60\tn/a\t6\t0\t12:00 AM/PM",curTest);
        //End of unspoil

        curTest = findItem.findAnyMatching("nat gol");
        assertEquals("ARR_UNSPOILED_NODE\tNative Gold\tCentral Thanalan\t(x24,y16)\tGregarious Worm Slot 1\t-1\tn/a\t6\t2\t4:00 AM",curTest);

        //End of arrUnspoil
        curTest = findItem.findAnyMatching("Potent Spice");
        assertEquals("FOLK_LORE_NODE\tPotent Spice\tElpis Thavnair Ultima Thule\t(x33.1,y14.7) (x25.4,y21.8) (x28.0,y13.1)\tArchon Burger, Beef Stroganoff, Peach Juice, Peach Tart, Pumpkin Potage, Pumpkin Ratatouille, Scallop Curry, Scallop Salad, Sykon Cookie, Thavnairian Chai\t6AM/PM 8AM/PM 10AM/PM\tAncient Ilsabardian Celestial\t1",
                curTest);
        //End of folklore
        curTest = findItem.findAnyMatching("Basilosaurus");
        assertEquals("FOLK_LORE_FISHING\tBasilosaurus\tThavnair\t(x7.3,y7.6)\t\tANY\tIlsabardian",curTest);

        //end of folkFish

    }
}