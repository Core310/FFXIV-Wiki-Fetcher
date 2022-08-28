import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListFinderTest {
    private ListFinder listFinder = new ListFinder();
    @Test
    void outPutList(){
        String expected;
        String actual;
        expected = "Item: Maple Branch\n" +
                "Zone: North Shroud\n" +
                "Coordinates: (x28,y26)\n" +
                "\n" +
                "Item: Latex\n" +
                "Zone: North Shroud\n" +
                "Coordinates: (x28,y26)\n" +
                "\n" +
                "Item: Maple Log\n" +
                "Zone: North Shroud\n" +
                "Coordinates: (x28,y26)\n" +
                "\n" +
                "Item: Wind Shard\n" +
                "Zone: Central Shroud\n" +
                "Coordinates: (x23,y18)\n" +
                "\n" +
                "\n";
        assertEquals(expected,);

    }
}