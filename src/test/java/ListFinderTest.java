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
    private String testHelper(String[] arr){
        listFinder.addItem(arr);
        return listFinder.outPutList().toString();
    }

    @Test
    void outPutList(){
        String expected;
    }
}