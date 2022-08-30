import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListFinderTest {
    private ListFinder listFinder = new ListFinder();
    @Test
    void outPutList(){
        String expected;
        String actual;
        expected = """
                Item: Maple Branch
                Zone: North Shroud
                Coordinates: (x28,y26)

                Item: Latex
                Zone: North Shroud
                Coordinates: (x28,y26)

                Item: Maple Log
                Zone: North Shroud
                Coordinates: (x28,y26)

                Item: Wind Shard
                Zone: Central Shroud
                Coordinates: (x23,y18)


                """;
        //assertEquals(expected,listFinder.addItem(new String[]{""}));

    }
}