package Scrapper;

/**
 * Contains all links that are to be searched.
 */
public enum Wikipages {
    BNTRegularNode("https://ffxiv.consolegameswiki.com/wiki/Botanist_Node_Locations"),
    BNTUnspoiledNode("https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Botanist_Nodes"),
    MNRRegularNode("https://ffxiv.consolegameswiki.com/wiki/Mining_Node_Locations"),
    MNRUnspoiledNode("https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes"),
    FolkLoreNode("https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes");
    //EphemeralNode("https://ffxiv.consolegameswiki.com/wiki/Ephemeral_Nodes")
// TODO: 2/2/2022 Emph nodes need a reader/praser

    private final String url;

    /**
     * Default constructor, this should not be used.
     * @param string String to assign to Enum values
     */
    Wikipages(String string) {
        url = string;
    }//todo delete?

    /**
     *
     * @return Enum Value (in this case the URL)
     */
    @Override
    public String toString() {
        return url;
    }
}