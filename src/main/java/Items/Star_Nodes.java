package Items;

/**
 * A base object for Nodes with stars.
 * Only diff from Regular_Node is time and star variables.
 */
public class Star_Nodes extends TimeBased_Nodes implements Item{
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
     * @param time
     * @param star
     */
    public Star_Nodes(String ItemName,
                      String TP,
                      int Level,
                      String WikiLink,
                      String Extra,
                      String time,
                      int star) {
        super(ItemName, TP, Level, WikiLink, Extra, time);
        this.star = star;
    }

    public int getStar() {
        return star;
    }

    public String toString() {
        return "TimeBased_Nodes{"  +
                "ItemName='" + getItemName() + '\'' +
                ", TP='" + getTP() + '\'' +
                ", Level=" + getLevel() +
                ", WikiLink='" + getWikiLink() + '\'' +
                ", extra='" + getExtra() + '\''+
                "star=" + getTime() +
                "time=" + getTime() +
                '}';
    }
}
