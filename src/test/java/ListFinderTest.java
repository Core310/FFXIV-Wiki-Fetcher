import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListFinderTest {
    private final ListFinder listFinder = new ListFinder();

    @Test
    void outPutList() {
        String expected;
        listFinder.addItem(new String[]{"Fire sh", "ic shrd", "water sha", "wind sha", "orange", "Cock Feather", "Cloves", "Black Pepper", "Nepto Dragon"});
        expected = "[Item: Wind Shard, Zone: Central Thanalan, Coordinates: (x18,y25)]\n" +
                "[Item: Cock Feather, Zone: Central Thanalan, Coordinates: (x22,y26)]\n" +
                "[Item: Cloves, Zone: Central Thanalan, Coordinates: (x22,y26)]\n" +
                "[Item: Black Pepper, Zone: Central Thanalan, Coordinates: (x25,y20)]\n" +
                "[Item: Fire Shard, Zone: Western Thanalan, Coordinates: (x26,y25)]\n" +
                "[Item: Water Shard, Zone: Western Thanalan, Coordinates: (x23,y18)]\n" +
                "[Item: Ice Shard, Zone: Central Shroud, Coordinates: (x23,y18)]\n" +
                "[Item: Coriander, Zone: Coerthas Western Highlands, Coordinates: (x12,y14)]\n" +
                "[Item: Blood Orange, Zone: Upper La Noscea, Coordinates: (x28,y25), Time: 7:00 AM]\n" +
                "[Item: Nepto Dragon, Zone: Eastern La Noscea , Coordinates: X:38, Y:24, Bait Used: Spoon Worm, Floating Minnow, Lugworm, Northern Krill, Yumizuno, Herring Ball, Sinking Minnow, Crab Ball, Rat Tail, Goby Ball, Pill Bug, Versatile Lure, Time: Anytime]";
        assertEquals(expected,listFinder.toString());
        listFinder.clearQueries();



    }
}