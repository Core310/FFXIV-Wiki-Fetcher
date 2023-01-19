import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListFinderTest {
    private final ListFinder listFinder = new ListFinder();

    /**
     * Small helper method to reduce an extra line when calling for assert equals
     * <br> Extra line is listFinder.outPutList().toString()
     * @param arr argument for listFinder.addItem(arr);
     * @return listfinder output as a string
     */
    String testHelper(String[] arr){
        listFinder.addItem(arr);
        return listFinder.outPutList().toString();
    }

    @Test
    void outPutList(){
        String expected;
        expected = """
                      Item: Maple Log
                      Zone: North Shroud
                      Coordinates: (x28,y26)
                      
                      Item: Wind Shard
                      Zone: Central Shroud
                      Coordinates: (x23,y18)
                      
                      Item: Maple Branch
                      Zone: North Shroud
                      Coordinates: (x28,y26)
                      
                      Item: Latex
                      Zone: North Shroud
                      Coordinates: (x28,y26)
                      
                      """;
        assertEquals(expected,testHelper(new String[]{"Mapl Banch", "Laex","Mapl Log","Win Shard"}));
        listFinder.clearLists();

        //Below Is same test as Above. Just testing manual input.
        listFinder.addItem("Mapl Branch");
        listFinder.addItem("Late");
        listFinder.addItem("Mapl Log");
        listFinder.addItem("Win Sard");
        assertEquals(expected,listFinder.outPutList().toString());
        listFinder.clearLists();

        expected = """
                
                """;
    }
}