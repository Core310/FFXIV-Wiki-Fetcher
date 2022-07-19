import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FindItemTest {
    @org.junit.jupiter.api.Test
    void findAllClosestValues() {
        FindItem findItem = new FindItem(new File("XIVGather.TSV"));
        String[] expectedOutput = {"[RegularNode"," Lava Toad","Southern Thanalan","(x13,y31)","","50","Lush Vegetation Patch]"};
        assertArrayEquals(
                expectedOutput
                ,
                findItem.HelperFindAllClosestValues("lava toad").toArray());
    }

    @org.junit.jupiter.api.Test
    void findAnyMatching() {


    }
}