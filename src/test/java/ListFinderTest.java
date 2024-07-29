import ffxivWikiFinder.ListFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for ListFinder (the final product)
 */
class ListFinderTest {
    @Test
    void outPutList() {
        String expected;
        ListFinder.addItem(new String[]{"Fire sh", "ic shrd", "water sha", "wind sha", "orange", "Cock Feather", "Cloves", "Black Pepper", "Nepto Dragon"});
        expected = """
                [Item: Wind Shard, Zone: Central Thanalan, Coordinates: (x18,y25)]
                [Item: Cock Feather, Zone: Central Thanalan, Coordinates: (x22,y26)]
                [Item: Cloves, Zone: Central Thanalan, Coordinates: (x22,y26)]
                [Item: Black Pepper, Zone: Central Thanalan, Coordinates: (x25,y20)]
                [Item: Fire Shard, Zone: Western Thanalan, Coordinates: (x26,y25)]
                [Item: Water Shard, Zone: Western Thanalan, Coordinates: (x23,y18)]
                [Item: Ice Shard, Zone: Central Shroud, Coordinates: (x23,y18)]
                [Item: Nepto Dragon, Zone: Eastern La Noscea, Coordinates: (38,24), Time: Anytime]
                [Item: Coriander, Zone: Coerthas Western Highlands, Coordinates: (x12,y14)]
                [Item: Blood Orange, Zone: Upper La Noscea, Coordinates: (x28,y25), Time: 7:00 AM]""";
        assertEquals(expected,ListFinder.outPut());
        ListFinder.clearQueries();
        ListFinder.addItem(new String[]{"clod jelfish", "crim trou", "rarefield reef roc", "yugar salmon", "giantpiel"});
        expected = """
                [Item: Cloud Jellyfish, Zone: Coerthas Central Highlands , Coordinates: X:13, Y:14, Bait Used: Balloon Bug, Hoverworm, Versatile Lure]
                [Item: Crimson Trout, Zone: Coerthas Central Highlands , Coordinates: X:20, Y:30, Bait Used: Moth Pupa, Topwater Frog, Stem Borer, Caddisfly Larva, Wildfowl Fly, Honey Worm, Sinking Minnow, Silver Spoon Lure, Bass Ball, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure]
                [Item: Rarefied Reef Rock, Zone: The Tempest, Coordinates: (x33,y21), Extra Information: 600 Min. Collectability, Time: 2:00AM/PM]
                [Item: Yugr'am Salmon, Zone: East Shroud , Coordinates: X:20, Y:21, Bait Used: Floating Minnow, Midge Basket, Mythril Spoon Lure, Streamer, Silver Spoon Lure, Chocobo Fly, Bass Ball, Brass Spoon Lure, Crow Fly, Bloodworm, Versatile Lure]
                [Item: Giant Taimen, Zone: Kholusia, Coordinates: (27.0,14.0), Time: Anytime, FolkLore Tome: Vrandtic]""";
        
        
        assertEquals(expected,ListFinder.outPut());
    }
}