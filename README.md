# FFXIV Wiki scrapper
This library searches for DOH/DOL items (aka gathering classes).

## Usage
To build the file yourself run `makeFile();` in main.java

---

Use `findAllClosestAsMap` to find all closest items and return each item as a key/value pair. 
Keys are headers in the original wiki table. Returns a ArrayList<LinkedHashMap<String,String>>.

```
FindItem.findAllClosestAsMap("Lava Toad");
```
Should output: `[{Item=Lava Toad, Zone=Southern Thanalan, Coordinates=(x13,y31), Extra Information=, Level=50}]`

---

Use `FindItem.findAnyMatching` to search for an item and return a random item by its raw data.
Returns a String.
```
FindItem.findAnyMatching("Lava Toad"); 
```
Should output `REGULAR_NODE	Lava Toad	Southern Thanalan	(x13,y31)		50	Lush Vegetation Patch`

## Development:
Contributing is greatly appreciated.

Please download the following dependencies if you are planning to do so.
- [me.xdrop:fuzzywuzzy 1.4.0](https://github.com/xdrop/fuzzywuzzy) for fuzzy search.
- [jsoup 1.15.2](https://jsoup.org/) for web scrapping.
- [junit 5.8.2](https://github.com/junit-team/junit5) for testing.

Should you need, a JavaDoc can be found on this repository’s website.


---
FINAL FANTASY is a registered trademark of Square Enix Holdings Co., Ltd.<br />
FINAL FANTASY XIV © SQUARE ENIX CO., LTD.
