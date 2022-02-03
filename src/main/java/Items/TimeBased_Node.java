package Items;

/**
 * Base class used mainly for Folklore Nodes.
 * . This should NOT create Unspoiled Nodes but rather be a template to it. (Extends to unspoiled nodes)
 * Only diff is variable int time
 */
public class TimeBased_Node extends Regular_Node implements Item{
    String Time;

    /**
     * Default constructor for time-based Nodes (With no star rating).
     *
     * @param ItemName
     * @param TP
     * @param Level
     * @param WikiLink
     * @param Extra
     * @param time
     */
    public TimeBased_Node(String ItemName, String TP, int Level, String WikiLink, String Extra,
                          String time
                           ) {
        super(ItemName, TP, Level, WikiLink, Extra);
        this.Time = time;
    }


    //internal class variable getters. No setters again for protection and less clutter.
    public String getTime() {
        return Time;
    }
    @Override
    public String toString() {
        return "TimeBased_Node{"  +
                "ItemName='" + getItemName() + '\'' +
                ", TP='" + getTP() + '\'' +
                ", Level=" + getLevel() +
                ", WikiLink='" + getWikiLink() + '\'' +
                ", extra='" + getExtra() + '\''+
                "time=" + getTime() +
                '}';
    }

}
