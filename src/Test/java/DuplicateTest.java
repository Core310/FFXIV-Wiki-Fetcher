import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateTest {
    @org.junit.jupiter.api.Test
    void duplicateTest(){
        assertEquals(//TODO 14/10/2022
                """
                        
                        """
                ,searchMap("Shark Tuna"));


        assertEquals(//TODO 14/10/2022
                """              
                        [Item, Zone, Coordinates, Bait Used, Type, Fishing Log, Level]
                        [Crayfish, Lower La Noscea , X:24, Y:23, Floating Minnow, Moth Pupa, Midge Basket, Sinking Minnow, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Freshwater fishing, Fishing Log: The Mourning Widow, 0]
                        [Item, Zone, Coordinates, Bait Used, Type, Fishing Log, Level]
                        [Crayfish, Old Gridania , X:15, Y:6, Floating Minnow, Moth Pupa, Midge Basket, Stem Borer, Glowworm, Caddisfly Larva, Snurble Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Freshwater fishing, Fishing Log: Whispering Gorge, 0]
                        [Item, Zone, Coordinates, Bait Used, Type, Fishing Log, Level]
                        [Crayfish, Central Shroud , X:22, Y:21, Moth Pupa, Mythril Spoon Lure, Honey Worm, Sinking Minnow, Chocobo Fly, Crow Fly, Crayfish Ball, Versatile Lure, Freshwater fishing, Fishing Log: The Vein, 0]
                        [Item, Zone, Coordinates, Bait Used, Type, Fishing Log, Level]
                        [Crayfish, Western Thanalan , X:17, Y:15, Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Rainbow Spoon Lure, Wildfowl Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Freshwater fishing, Fishing Log: The Footfalls, 0]
                        [Item, Zone, Coordinates, Bait Used, Type, Fishing Log, Level]
                        [Crayfish, Middle La Noscea , X:20, Y:18, Floating Minnow, Moth Pupa, Midge Basket, Spinnerbait, Bass Ball, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Freshwater fishing, Fishing Log: West Agelyss River, 0]
                        [Item, Zone, Coordinates, Bait Used, Type, Fishing Log, Level]
                        [Crayfish, New Gridania , X:12, Y:13, Floating Minnow, Moth Pupa, Midge Basket, Stem Borer, Glowworm, Caddisfly Larva, Snurble Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Freshwater fishing, Fishing Log: Jadeite Flood, 0]                              
                        """

                ,searchMap("crayon fish"));

    }


    private String searchMap(String itemName){
        FindItem fi  = new FindItem();
        StringBuilder sb = new StringBuilder();
        for(LinkedHashMap<String,String> lm: fi.findAllClosestAsMap(itemName)){
            sb.append(lm.keySet()).append("\n");
            sb.append(lm.values()).append("\n");
        }
        return sb.toString();
    }

    private  void searchAllRaw(String itemName){
        FindItem fi = new FindItem();
        //String base[] = fi.findAllClosest(itemName).get(0).split("\t",-1);
        //String base2[] = fi.findAllClosest(itemName).get(1).split("\t",-1);
        //String t = (base[1].replaceAll(" ","") + " " + base[2].replaceAll(" ",""));
        //String t1= (base2[1].replaceAll(" ","") + " " + base2[2].replaceAll(" ",""));
        //System.out.println(t1 + "\n" + t);
        for(String cur: fi.findAllClosest(itemName))
            System.out.println(cur);
    }

    /**
     * Test  method to search the file for certain values.
     */
    private  void searchFile(String itemnam) {
        FindItem findItem = new FindItem();
        ArrayList<StringBuilder> arr;
        //findItem.setNumberOfDuplicateItems(1);
        for(StringBuilder tmp: findItem.essentialFindAllClosestAsMap(itemnam)){
            System.out.println(tmp);
        };
    }
}
