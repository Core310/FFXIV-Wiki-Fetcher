public class Item {
    private String ItemName;
    private int ItemID;
    private String TP;
    private int Level;
    private int iLvl;
    private String WikiLink;//Official wiki

    /**
     * Default constructor. All arguments map to internal variables.
     * @param ItemName
     * @param ItemId
     * @param TP
     * @param Level
     * @param iLvl
     * @param WikiLink
     */
    public Item(
            String ItemName,
            int ItemId,
            String TP,
            int Level,
            int iLvl,
            String WikiLink
    ){
      this.ItemName = ItemName;
      this.ItemID = ItemId;
      this.TP = TP;
      this.Level = Level;
      this.iLvl = iLvl;
      this.WikiLink = WikiLink;
    }

    /**
     * @return all internal private values
     */
    @Override
    public String toString() {
        return "Item{" +
                "ItemName='" + ItemName + '\'' +
                ", ItemID=" + ItemID +
                ", TP='" + TP + '\'' +
                ", Level=" + Level +
                ", iLvl=" + iLvl +
                ", WikiLink='" + WikiLink + '\'' +
                '}';
    }

    //getters & setters below
    //setters in comment link. Technically not needed b/c hv constructor
    /**
     *
     public void setiLvl(int iLvl) {
     this.iLvl = iLvl;
     }

     public void setItemID(int itemID) {
     ItemID = itemID;
     }

     public void setItemName(String itemName) {
     ItemName = itemName;
     }

     public void setLevel(int level) {
     Level = level;
     }

     public void setTP(String TP) {
     this.TP = TP;
     }

     public void setWikiLink(String wikiLink) {
     WikiLink = wikiLink;
     }

     */
    public int getiLvl() {
        return iLvl;
    }

    public int getItemID() {
        return ItemID;
    }

    public int getLevel() {
        return Level;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getTP() {
        return TP;
    }

    public String getWikiLink() {
        return WikiLink;
    }

}
