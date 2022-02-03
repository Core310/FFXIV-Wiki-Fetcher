package Items;

/**
 * A base object for Nodes with stars.
 * Only diff from Regular_Node is time and star variables.
 */
public class Star_Nodes extends Regular_Node implements Item{
    int star;
    /**
     *      Default constructor.
     *      Time, Star, and FolkLore maps to internal vars. The rest to Items.Regular_Node.java
     *      This covers the classes BNT and Miner
     * @param ItemName
     * @param TP
     * @param Level
     * @param WikiLink
     * @param Extra
     * @param star
     */
    public Star_Nodes(String ItemName,
                           String TP,
                           int Level,
                           String WikiLink,
                           String Extra,
                           int star) {
        super(ItemName, TP, Level, WikiLink, Extra);
        this.star = star;
    }
    public int getStar() {
        return star;
    }

    @Override
    public String toString() {
        return "Star_Nodes{" +
                "star=" + star +
                '}';
    }// TODO: 2/2/2022 Add the rest of the vairables
}
