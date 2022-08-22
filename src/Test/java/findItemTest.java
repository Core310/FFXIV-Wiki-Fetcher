import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests one of each item
 */
class findItemTest {
    private ArrayList<String> expected;
    private final findItem findItem = new findItem();

    /**
     * Provides a custom input for assertEquals.
     *
     * <p>Usage for findAllClosest </p>This updates class values expected,actual so that assertEquals only uses those values.
     * It then runs assertEquals on the two inputs
     * @param expected expected output
     * @param actual actual output
     * @see findItem
     * @see findItemTest findAllCloses-test
     */
    private void findAllClosetHelper(String[] expected, ArrayList<String> actual){
        this.expected.clear();
        this.expected.addAll(List.of(expected));
        assertEquals(this.expected, actual);
    }

    /**
     * This test is honestly a little overkill. Look at the other tests first for debugging before looking at this one.
     */
    @org.junit.jupiter.api.Test
    void essentialFindAllClosestAsMap(){
        assertEquals(
                """
                        [Item: Crayfish
                        Zone: Middle La Noscea\s
                        Coordinates: X:20, Y:18
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Spinnerbait, Bass Ball, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Middle La Noscea\s
                        Coordinates: X:22, Y:22
                        Bait Used: Floating Minnow, Moth Pupa, Spinnerbait, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Lower La Noscea\s
                        Coordinates: X:24, Y:23
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Sinking Minnow, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: New Gridania\s
                        Coordinates: X:12, Y:13
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Stem Borer, Glowworm, Caddisfly Larva, Snurble Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: New Gridania\s
                        Coordinates: X:10, Y:11
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Rainbow Spoon Lure, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Old Gridania\s
                        Coordinates: X:11, Y:8
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Old Gridania\s
                        Coordinates: X:15, Y:6
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Stem Borer, Glowworm, Caddisfly Larva, Snurble Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Central Shroud\s
                        Coordinates: X:22, Y:21
                        Bait Used: Moth Pupa, Mythril Spoon Lure, Honey Worm, Sinking Minnow, Chocobo Fly, Crow Fly, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Western Thanalan\s
                        Coordinates: X:17, Y:15
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Rainbow Spoon Lure, Wildfowl Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        ]""",
                findItem.essentialFindAllClosestAsMap("crayon fish").toString()
                );

        assertEquals("""
                [Item: Lava Toad
                Zone: Southern Thanalan
                Coordinates: (x13,y31)
                ]""",findItem.essentialFindAllClosestAsMap("guava tode").toString());

        assertEquals("""
                [Item: Inkfish
                Zone: The Sea of Clouds
                Coordinates: (x29,y35)
                Time: 2PM-4PM
                FolkLore Tome: Abalathian
                , Item: Cupfish
                Zone: Lower La Noscea\s
                Coordinates: X:27, Y:15
                Bait Used: Moth Pupa, Glowworm, Spinnerbait, Honey Worm, Sinking Minnow, Syrphid Basket, Chocobo Fly, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                , Item: Sunfish
                Zone: Western La Noscea\s
                Coordinates: X:15, Y:29
                Bait Used: Spoon Worm, Floating Minnow, Lugworm, Glowworm, Northern Krill, Yumizuno, Krill Cage Feeder, Heavy Steel Jig, Herring Ball, Sinking Minnow, Steel Jig, Rat Tail, Goby Ball, Pill Bug, Versatile Lure
                , Item: Skyfish
                Zone: Coerthas Central Highlands\s
                Coordinates: X:13, Y:14
                Bait Used: Balloon Bug, Hoverworm, Versatile Lure
                , Item: Skyfish
                Zone: Coerthas Central Highlands\s
                Coordinates: X:33.2, Y:18
                Bait Used: Hoverworm, Versatile Lure
                , Item: Cupfish
                Zone: Lower La Noscea
                Coordinates: (26,15)
                Time: 5 PM to 7 PM
                , Item: Inkfish
                Zone: The Sea of Clouds
                Coordinates: (29,35)
                Time: 2 PM to 4 PM
                ]""",findItem.essentialFindAllClosestAsMap("fishie").toString());
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
                "FOLK_LORE_FISH_NODE	The Archbishop	The Fringes	(x13,y31)		11AM-4PM	Gyr Abanian",
                "FISH_BIG_NODE\tThe Archbishop\tThe Fringes\t(13,31)\tCane of the Round, Piety Materia VI\tThe Comet's Tail\t12 PM to 4 PM\tAny\tSuspending Minnow\t\t"},
                findItem.findAllClosest("the archbitch")
                );

        findAllClosetHelper(new String[]{
                "FOLK_LORE_FISH_NODE\tBasilosaurus\tThavnair\t(x7.3,y7.6)\t\tANY\tIlsabardian",
        "FISH_COLLECTABLES_NODE\tBasilosaurus\tThe Perfumed Tides\tn/a\tMooched from Puff-paya\t1055\tMackerel Strip\tFair Skies\t209 Purple"},
                findItem.findAllClosest("Basilosaurus")
                );
        //end of folkFish
        findAllClosetHelper(new String[]{
        "FISH_NODE	Coral Butterfly	Limsa Lominsa Upper Decks 	X:10, Y:11	Lugworm, Pill Bug, Goby Ball, Rat Tail, Floating Minnow, Steel Jig, Versatile Lure, Heavy Steel Jig, Spoon Worm	Ocean fishing	Fishing Log: Limsa Lominsa Upper Decks	1",
        "FISH_NODE	Coral Butterfly	Limsa Lominsa Lower Decks 	X:7, Y:12	Lugworm, Pill Bug, Goby Ball, Rat Tail, Floating Minnow, Steel Jig, Versatile Lure, Heavy Steel Jig, Spoon Worm	Ocean fishing	Fishing Log: Limsa Lominsa Lower Decks	1",
        "FISH_NODE	Coral Butterfly	Middle La Noscea 	X:18, Y:22	Spoon Worm, Floating Minnow, Lugworm, Heavy Steel Jig, Sinking Minnow, Steel Jig, Shrimp Cage Feeder, Crab Ball, Rat Tail, Goby Ball, Pill Bug, Versatile Lure	Ocean fishing	Fishing Log: Zephyr Drift	1",
        "FISH_NODE	Coral Butterfly	Eastern La Noscea 	X:32, Y:34	Spoon Worm, Floating Minnow, Lugworm, Yumizuno, Heavy Steel Jig, Shrimp Cage Feeder, Crab Ball, Rat Tail, Goby Ball, Pill Bug, Versatile Lure	Ocean fishing	Fishing Log: South Bloodshore	25"
        },findItem.findAllClosest("coralbyutter") );
        //End of FSH
        findAllClosetHelper(new String[]{
                "FISH_NODE	Oschon's Print	Lower La Noscea 	X:26, Y:26	Spoon Worm, Floating Minnow, Lugworm, Yumizuno, Rat Tail, Goby Ball, Pill Bug, Versatile Lure	Ocean fishing	Fishing Log: Moraby Bay	5",
                "FISH_BIG_NODE	Oschon's Print	Lower La Noscea	(26,25)		Moraby Bay	Anytime	Any	Goby Ball		301+",},
                findItem.findAllClosest("Oschon"));
        //End of Big FSH
        findAllClosetHelper(new String[]{
        "FISH_NODE	Silver Sovereign	Lower La Noscea 	X:24, Y:39	Spoon Worm, Northern Krill, Yumizuno, Steel Jig, Rat Tail, Goby Ball, Pill Bug, Versatile Lure	Ocean fishing	Fishing Log: Oschon's Torch	10",
        "FISH_BIG_NODE	Silver Sovereign	Lower La Noscea	(24,39)	Venture	Oschon's Torch	Anytime	Any	Yumizuno		301+"
        }, findItem.findAllClosest("Silver Sovereign"));

        findAllClosetHelper(new String[]{
        "FOLK_LORE_FISH_NODE	Hookstealer	Rhalgr's Reach	(x11,y14)		ANY	Gyr Abanian",
        "FISH_NODE	Caterwauler	Lower La Noscea 	X:24, Y:23	Floating Minnow, Moth Pupa, Midge Basket, Sinking Minnow, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure	Freshwater fishing	Fishing Log: The Mourning Widow	1",
        "FISH_BIG_NODE	Caterwauler	Lower La Noscea	(24,22)		The Mourning Widow	Anytime	Clear Skies Fair Skies Rain	Moth Pupa		301+",
        "FISH_BIG_NODE	Hookstealer	Rhalgr's Reach	(11,14)	Suspending Minnow	Upper Mirage Creek	Anytime	Any	Midge Larva	Gyr Abanian Trout	",
        "FISH_COLLECTABLES_NODE	Eternal Eye	Azim Khaat Southern Lakebed	n/a	Truth of Oceans + Glaring Perch x10	20	Spearfishing		18 White"

        }, findItem.findAllClosest("Etebalr"));

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


        //end of folkFish (none b/c a lot of overlap)

        //End of FSH (none b/c a lot of overlap)

        //End of Big FSH
        assertEquals("FISH_COLLECTABLES_NODE\tPancake Octopus\tEast Caliban Gap\tn/a\t\t31\tSquid Strip\t\t40 White",
                findItem.findAnyMatching("Pancake otco"));

        assertEquals("FISH_COLLECTABLES_NODE\tDiamond Pipira\tThe Red Chalice\tn/a\t\t201\tRobber Ball\t12PM-8PM\t41 White",
                findItem.findAnyMatching("Diamond pepi"));
        //End of Collect FSH

    }
}