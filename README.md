# FFXIV Wiki scrapper
FFXIV Wiki scrapper searches for DOH/DOL gathering nodes (aka gathering classes) from
the [consolegameswiki](https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Nodes)
and outputs them in a neat and readable format. It is fully documented using java doc (see linked repository webiste).

- Simply call `ListFinder.addItem(String searchKey)` for any items that you want to search for. Then call ListFinder.outPut() to output all items 
called for. The searchKey does not need to be exact as this uses fuzzySearching to guess the item you are looking for.
- Download the latest release from [mavenCentral](https://search.maven.org/artifact/io.github.Core310/FFXIV-Wiki-Fetcher).
- Currently, does not support the
  [Ephemeral_Nodes](https://ffxiv.consolegameswiki.com/wiki/Ephemeral_Nodes) page
## Development:
Contributing is greatly appreciated. You can find the source code zip here [releases page](https://github.com/Core310/FFXIV-Wiki-Fetcher/releases).
If you wish to build the file yourself run `makeFile();` in Main.java

Please download the following dependencies if you are planning to contribute.
- [me.xdrop:fuzzywuzzy 1.4.0](https://github.com/xdrop/fuzzywuzzy) for fuzzy search.
- [jsoup 1.15.3](https://jsoup.org/) for web scrapping.
- [junit 5.9.0](https://github.com/junit-team/junit5) for testing.

Should you need, a JavaDoc can be found on this repository’s website.

---
FINAL FANTASY is a registered trademark of Square Enix Holdings Co., Ltd.<br />
FINAL FANTASY XIV © SQUARE ENIX CO., LTD.
