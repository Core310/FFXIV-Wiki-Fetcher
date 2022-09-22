# FFXIV Wiki scrapper
FFXIV Wiki scrapper searches for DOH/DOL gathering nodes (aka gathering classes) from
the [consolegameswiki](https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Nodes)
and outputs them in a neat and readable format. It is fully documented using java doc (see linked repository webiste).

- Currently, does not support the
  [Ephemeral_Nodes](https://ffxiv.consolegameswiki.com/wiki/Ephemeral_Nodes) page

## QuickStart
Download dependency from [maven central](https://search.maven.org/artifact/io.github.Core310/FFXIV-Wiki-Fetcher/1.0.0/jar)

Use `FindItem.essentialFindAllClosestAsMap(String input)` to find the closest items neatly.

Use `FindItem.setNumberOfDuplicateItems(int num)` to set the number of duplicate items you want returned.

<br> Example: 

```java
FindItem fi = new FindItem();
FindItem.essentialFindAllClosestAsMap("crayon");
```

## Download
Download the latest release from [mavenCentral](https://search.maven.org/artifact/io.github.Core310/FFXIV-Wiki-Fetcher).

## Usage as a library
Use `FindItem.setNumberOfDuplicateItems(int num)` to set the number of duplicate items you want to appear.
- Use the value `-1`to set to an infinite number.
- Using the values `0` or `1` will produce no duplicates (then 2 will produce 1 extra duplicate).
- By default, this value is `-1`.

For example, searching crayonfish may return four instances of Crayfish. Setting the value to -1 will return all four instances,
0 or 1 will produce 1 instance, and 2 would produce 2 instances.

(Preferred) Use `essentialFindAllClosestAsMap` to find and neatly output the most
important item data. May result in a large output if input is too vague.

Outputs: `[Item: Crayfish
Zone: Central Shrouds
Coordinates: X:22, Y:21
Bait Used: Moth Pupa, Mythril Spoon Lure, Honey Worm,
Sinking Minnow, Chocobo Fly, Crow Fly, Crayfish Ball, Versatile Lure
]`

Use `findAllClosestAsMap` to find all closest items and return each item as a key/value pair.
Keys are headers in the original wiki table. Returns a ArrayList<LinkedHashMap<String,String>>.

Outputs: `[{Item=Lava Toad, Zone=Southern Thanalan, Coordinates=(x13,y31), Extra Information=, Level=50}]`

Use `findAnyMatching` to search for an item and return a random item by its raw data.
Returns a String.

## Development:
Contributing is greatly appreciated. Please download and unzip the latest file from the [releases page](https://github.com/Core310/FFXIV-Wiki-Fetcher/releases) 

If you wish to build the file yourself run `makeFile();` in main.java or copy below. Replace FileName with the desired path for the resource file.
```java
        File XIVGather = new File(FileName);
        FileWriter fileWriter = new FileWriter(XIVGather,false);
        MakeFile makeFile = new MakeFile(XIVGather,fileWriter);
        Document doc ;
        for(Wikipages wikipages: Wikipages.values()){
            String link = Jsoup.clean(wikipages.toString(), Safelist.basic());
            doc = Jsoup.connect(link).get();
            makeFile.setParsedPage(doc);
            makeFile.scrap();
        }
        fileWriter.close();
        Formatter formatter = new Formatter(FileName);
```

Please download the following dependencies if you are planning to do so.
- [me.xdrop:fuzzywuzzy 1.4.0](https://github.com/xdrop/fuzzywuzzy) for fuzzy search.
- [jsoup 1.15.2](https://jsoup.org/) for web scrapping.
- [junit 5.9.0](https://github.com/junit-team/junit5) for testing.

Should you need, a JavaDoc can be found on this repository’s website.

Before sending a pull request, please run the test cases (they will automatically run again when you
submit your PR). If any case fails, please fix it and/or explain the change in your PR.

---
FINAL FANTASY is a registered trademark of Square Enix Holdings Co., Ltd.<br />
FINAL FANTASY XIV © SQUARE ENIX CO., LTD.
