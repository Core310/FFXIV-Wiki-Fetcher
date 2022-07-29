import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindItemTest {
    private ArrayList<String> expected, actual;
    private final FindItem findItem = new FindItem(new File("XIVGather.TSV"));

    /**
     * Provides a custom input for assertEquals. This updates class values expected,actual so that assertEquals only uses those values.
     * It then runs assertEquals on the two inputs
     * @param expected expected output
     * @param actual actual output
     */
    private void findAllClosetHelper(String[] expected, ArrayList<String> actual){
        this.expected.clear();
        this.expected.addAll(List.of(expected));
        this.actual = actual;
        assertEquals(this.expected,this.actual);

    }

    @org.junit.jupiter.api.Test
    void findAllClosestAsMap(){

    }

    @org.junit.jupiter.api.Test
    void findAllClosest() {
        expected = new ArrayList<>();
        findAllClosetHelper(new String[]{
                "REGULAR_NODE\tLava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch"},
                findItem.findAllClosest("lava tode"));

        findAllClosetHelper(new String[]{
                "REGULAR_NODE\tBeech Log\tThe Fringes\t(x10,y16)\t\t65\tMature Tree",
                "REGULAR_NODE\tGem Algae\tThe Ruby Sea\t(x26,y19)\t\t65\tLush Vegetation Patch"},
                findItem.findAllClosest("emye log")
                );

        //assertEquals(expectedOutput,curTest);
        //End of regular node tests

        findAllClosetHelper(new String[]{
                "UNSPOILED_NODE\tRarefied Annite\tElpis\t(x8,y36)\t600 Min. Collectability\t90\t10:00AM/PM\t2\t1",
                "UNSPOILED_NODE\tRarefied Pyrite\tThe Dravanian Forelands\t(x31,y32)\t600 Min. Collectability\t51\t4:00 AM/PM\t3\t0"},
                findItem.findAllClosest("rarefiend stuff")
                );
        //End of Unspoiled

        findAllClosetHelper(new String[]{
                "ARR_UNSPOILED_NODE\tWaterfowl Feather (Rare)\tWestern La Noscea\t(x34,y28)\t\t-1\t8:00 AM\t1\t2",
                "ARR_UNSPOILED_NODE\tWaterfowl Feather (Rare)\tWestern La Noscea\t(x34,y28)\t\t-1\t8:00 AM\t5\t2"},
                findItem.findAllClosest("Waterfowl Feather (Rare)")
                );
        //End of ARRUnspoiled

        findAllClosetHelper(new String[]{
                "FOLK_LORE_NODE\tSandalwood Log\tThe Rak'tika Greatwood\t(x24,y36)\tSandalwood Lumber\tVrandtic\t2AM/PM\t6",
        "FOLK_LORE_NODE\tSandalwood Sap\tThe Rak'tika Greatwood\t(x24,y36)\tPliable Glass Fiber\tVrandtic\t2AM/PM\t4"},
                findItem.findAllClosest("Sandalwood")
                );

        //End of folklore

        findAllClosetHelper(new String[]{
                "FOLK_LORE_FISHING_NODE\tBasilosaurus\tThavnair\t(x7.3,y7.6)\t\tANY\tIlsabardian",
        "FISHING_COLLECTABLES_NODE\tBasilosaurus\tThe Perfumed Tides\tn/a\tMooched from Puff-paya\t1055\tMackerel Strip\tFair Skies\t209 Purple"},
                findItem.findAllClosest("Basilosaurus")
                );
        //end of folkFish
        findAllClosetHelper(new String[]{
        "FISHING_NODE	Coral Butterfly	Limsa Lominsa Upper Decks 	X:10, Y:11	Lugworm, Pill Bug, Goby Ball, Rat Tail, Floating Minnow, Steel Jig, Versatile Lure, Heavy Steel Jig, Spoon Worm	Ocean fishing	Fishing Log: Limsa Lominsa Upper Decks	1",
        "FISHING_NODE	Coral Butterfly	Limsa Lominsa Lower Decks 	X:7, Y:12	Lugworm, Pill Bug, Goby Ball, Rat Tail, Floating Minnow, Steel Jig, Versatile Lure, Heavy Steel Jig, Spoon Worm	Ocean fishing	Fishing Log: Limsa Lominsa Lower Decks	1",
        "FISHING_NODE	Coral Butterfly	Middle La Noscea 	X:18, Y:22	Spoon Worm, Floating Minnow, Lugworm, Heavy Steel Jig, Sinking Minnow, Steel Jig, Shrimp Cage Feeder, Crab Ball, Rat Tail, Goby Ball, Pill Bug, Versatile Lure	Ocean fishing	Fishing Log: Zephyr Drift	1",
        "FISHING_NODE	Coral Butterfly	Eastern La Noscea 	X:32, Y:34	Spoon Worm, Floating Minnow, Lugworm, Yumizuno, Heavy Steel Jig, Shrimp Cage Feeder, Crab Ball, Rat Tail, Goby Ball, Pill Bug, Versatile Lure	Ocean fishing	Fishing Log: South Bloodshore	25"
        },findItem.findAllClosest("coralbyutter") );
        //End of FSH


        //End of Big FSH

        //End of Collect FSH
    }

    @org.junit.jupiter.api.Test
    void findAnyMatching() {
        assertEquals("REGULAR_NODE\tLava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch",
                findItem.findAnyMatching("Lava Tode"));//Should only have one close value matching
        assertEquals("REGULAR_NODE\tDark Rye\tLabyrinthos\t(x29, y18)\t\t85\tLush Vegetation Patch",
                findItem.findAnyMatching("r drk rye"));

        findItem.findAnyMatching(" Level 75 Miner Quest");
        //^^ A typo in https://ffxiv.consolegameswiki.com/wiki/Miner_Node_Locations. This is typo is already accounted for in FindItem.java

        //End of reg node

        assertEquals("UNSPOILED_NODE\tRarefied Gyr Abanian Alumen\tThe Fringes\t(x32,y31)\t600 Min. Collectability\t80\t10:00 AM/PM\t2\t1",
                findItem.findAnyMatching("rarefield gyrr aabania"));
        assertEquals("UNSPOILED_NODE\tAdamantite Ore\tAzys Lla\t(x24,y6)\tPerception 408\t60\t12:00 AM/PM\t6\t0",
                findItem.findAnyMatching("adamite ore"));
        assertEquals("UNSPOILED_NODE\tPummelite\tThe Peaks\t(x26,y12)\tA Miner Success Perception 846\t70\t8:00 AM/PM\t6\t0",
                findItem.findAnyMatching("Pummelite"));

        //End of unspoil

        assertEquals("ARR_UNSPOILED_NODE\tNative Gold\tCentral Thanalan\t(x24,y16)\tGregarious Worm Slot 1\t-1\t4:00 AM\t6\t2",
                findItem.findAnyMatching("nat gol"));

        //End of arrUnspoil
        assertEquals("FOLK_LORE_NODE\tPotent Spice\tElpis Thavnair Ultima Thule\t(x33.1,y14.7) (x25.4,y21.8) (x28.0,y13.1)\tArchon Burger, Beef Stroganoff, Peach Juice, Peach Tart, " +
                        "Pumpkin Potage, Pumpkin Ratatouille, Scallop Curry, Scallop Salad, Sykon Cookie, Thavnairian Chai\tAncient Ilsabardian Celestial\t6AM/PM 8AM/PM 10AM/PM\t1",
                findItem.findAnyMatching("Potent Spice"));
        //End of folklore

// TODO: 7/29/2022
        //end of folkFish

        //End of FSH
        assertEquals("BIG_FISH_NODE\tSilver Sovereign\tLower La Noscea\t(24,39)\tVenture\tOschon's Torch\tAnytime\tAny\tYumizuno\t\t301+",
                findItem.findAnyMatching("silver soverign"));
        //End of Big FSH

        //End of Collect FSH

    }
}