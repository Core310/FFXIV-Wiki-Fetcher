package Items;

public interface Item {
    int getLevel();
    String getItemName();
    String getTP();
    String getWikiLink();
    String getExtra();
    int[] asArray();
}
