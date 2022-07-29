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
        expectedOutput.add("REGULAR_NODE\tLava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch");
        curTest = findItem.findAllClosest("lava tode");
        assertEquals(expectedOutput,curTest);


        expectedOutput.clear();
        expectedOutput.add("REGULAR_NODE\tBeech Log\tThe Fringes\t(x10,y16)\t\t65\tMature Tree");
        expectedOutput.add("REGULAR_NODE\tGem Algae\tThe Ruby Sea\t(x26,y19)\t\t65\tLush Vegetation Patch");
        curTest = findItem.findAllClosest("emye log");
        //assertEquals(expectedOutput,curTest);
        //End of regular node tests

        expectedOutput.clear();
        expectedOutput.add("UNSPOILED_NODE\tRarefied Annite\tElpis\t(x8,y36)\t600 Min. Collectability\t90\t10:00AM/PM\t2\t1");
        expectedOutput.add("UNSPOILED_NODE\tRarefied Pyrite\tThe Dravanian Forelands\t(x31,y32)\t600 Min. Collectability\t51\t4:00 AM/PM\t3\t0");
        curTest = findItem.findAllClosest("rarefiend stuff");
        assertEquals(expectedOutput,curTest);
        //End of Unspoiled

        expectedOutput.clear();
        expectedOutput.add("ARR_UNSPOILED_NODE\tWaterfowl Feather (Rare)\tWestern La Noscea\t(x34,y28)\t\t-1\t8:00 AM\t1\t2");
        expectedOutput.add("ARR_UNSPOILED_NODE\tWaterfowl Feather (Rare)\tWestern La Noscea\t(x34,y28)\t\t-1\t8:00 AM\t5\t2");
        curTest = findItem.findAllClosest("Waterfowl Feather (Rare)");
        assertEquals(expectedOutput,curTest);
        //End of ARRUnspoiled

        expectedOutput.clear();
        expectedOutput.add("FOLK_LORE_NODE\tSandalwood Log\tThe Rak'tika Greatwood\t(x24,y36)\tSandalwood Lumber\tVrandtic\t2AM/PM\t6");
        expectedOutput.add("FOLK_LORE_NODE\tSandalwood Sap\tThe Rak'tika Greatwood\t(x24,y36)\tPliable Glass Fiber\tVrandtic\t2AM/PM\t4");
        curTest = findItem.findAllClosest("Sandalwood");
        assertEquals(expectedOutput,curTest);
        //End of folklore
        expectedOutput.clear();
        expectedOutput.add("FOLK_LORE_FISHING_NODE\tBasilosaurus\tThavnair\t(x7.3,y7.6)\t\tANY\tIlsabardian");
        expectedOutput.add("FISHING_COLLECTABLES_NODE\tBasilosaurus\tThe Perfumed Tides\tn/a\tMooched from Puff-paya\t1055\tMackerel Strip\tFair Skies\t209 Purple");
        curTest = findItem.findAllClosest("Basilosaurus");
        assertEquals(expectedOutput,curTest);
        //end of folkFish
    }

    @org.junit.jupiter.api.Test
    void findAnyMatching() {
        String curTest;
        curTest = findItem.findAnyMatching("Lava Tode");// Reg node test
        assertEquals("REGULAR_NODE\tLava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch",curTest);//Should only have one close value matching
        curTest = findItem.findAnyMatching("r drk rye");// Unspoil node test
        assertEquals("REGULAR_NODE\tDark Rye\tLabyrinthos\t(x29, y18)\t\t85\tLush Vegetation Patch",curTest);
        curTest = findItem.findAnyMatching(" Level 75 Miner Quest");
        //^^ A typo in https://ffxiv.consolegameswiki.com/wiki/Miner_Node_Locations. This is typo is already accounted for in FindItem.java

        //End of reg nodee

        curTest = findItem.findAnyMatching("rarefield gyrr aabania");
        assertEquals("UNSPOILED_NODE\tRarefied Gyr Abanian Alumen\tThe Fringes\t(x32,y31)\t600 Min. Collectability\t80\t10:00 AM/PM\t2\t1",curTest);
        curTest = findItem.findAnyMatching("adamite ore");
        assertEquals("UNSPOILED_NODE\tAdamantite Ore\tAzys Lla\t(x24,y6)\tPerception 408\t60\t12:00 AM/PM\t6\t0",curTest);
        curTest = findItem.findAnyMatching("Pummelite");
        assertEquals("UNSPOILED_NODE\tPummelite\tThe Peaks\t(x26,y12)\tA Miner Success Perception 846\t70\t8:00 AM/PM\t6\t0",curTest);

        //End of unspoil

        curTest = findItem.findAnyMatching("nat gol");
        assertEquals("ARR_UNSPOILED_NODE\tNative Gold\tCentral Thanalan\t(x24,y16)\tGregarious Worm Slot 1\t-1\t4:00 AM\t6\t2",curTest);

        //End of arrUnspoil
        curTest = findItem.findAnyMatching("Potent Spice");
        assertEquals("FOLK_LORE_NODE\tPotent Spice\tElpis Thavnair Ultima Thule\t(x33.1,y14.7) (x25.4,y21.8) (x28.0,y13.1)\tArchon Burger, Beef Stroganoff, Peach Juice, Peach Tart, " +
                        "Pumpkin Potage, Pumpkin Ratatouille, Scallop Curry, Scallop Salad, Sykon Cookie, Thavnairian Chai\tAncient Ilsabardian Celestial\t6AM/PM 8AM/PM 10AM/PM\t1",
                curTest);
        //End of folklore


        //end of folkFish

    }
}