import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FindItemTest {
    FindItem findItem = new FindItem(new File("XIVGather.TSV"));
    @org.junit.jupiter.api.Test
    void HelperFindAllClosestValues() {
        ArrayList<String> expectedOutput;
        ArrayList<String> curTest;

        expectedOutput = new ArrayList<>();
        expectedOutput.add("RegularNode\t Lava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch\t");
        curTest = findItem.HelperFindAllClosestValues("lava tode");
        assertEquals(expectedOutput,curTest);

        expectedOutput.clear();
        expectedOutput.add("RegularNode\t Magma Beet\tThe Churning Mists\t(x20,y21)\t\t55\tLush Vegetation Patch\t");
        expectedOutput.add("RegularNode\t Fava Beans\tThe Azim Steppe\t(x33,12)\tNamazu Beast Tribe Required\t60\tLush Vegetation Patch\t");
        expectedOutput.add("RegularNode\tZelkova Log\tThe Lochs\t(x26,y9)\t\t70\tMature Tree\t");
        System.out.println(Arrays.toString(findItem.HelperFindAllClosestValues("lava getr").toArray()));
        curTest = findItem.HelperFindAllClosestValues("lava getr");
        assertEquals(expectedOutput,curTest);

    }

    @org.junit.jupiter.api.Test
    void findAnyMatching() {
        String curTest;
        curTest = findItem.findAnyMatching("Lava Tode");
        assertEquals("RegularNode\t Lava Toad\tSouthern Thanalan\t(x13,y31)\t\t50\tLush Vegetation Patch\t",curTest);//Should only have one close value matching

    }
}