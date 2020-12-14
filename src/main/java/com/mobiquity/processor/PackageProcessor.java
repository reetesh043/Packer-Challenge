package com.mobiquity.processor;

import com.mobiquity.data.Item;
import com.mobiquity.data.Package;
import com.mobiquity.utils.PackerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Reetesh Kumar
 * PackageProcessor - Processes the Information Parsed from the file
 */
public class PackageProcessor {
    private static Logger LOGGER = LoggerFactory.getLogger(PackageProcessor.class);

    /**
     * Processes the information obtained from the file and converts to a List of Package
     *
     * @return List of Package
     */
    public static List<Package> processPackage(List<String> packages) {
        LOGGER.info("Processing packages information");

        List<Package> packageList = new ArrayList<>();
        packages.forEach(packageFromFile -> {
            String[] strArray = packageFromFile.split(":");
            Integer packageWeight = Integer.valueOf(strArray[0].trim());
            String packageItems = strArray[1].trim();
            List<String> groupItemList = groupItems(packageItems);
            List<Item> itemList = new ArrayList<>();
            groupItemList.forEach(groupItem -> {
                String[] itemSplitToSpecs = groupItem.split(",");
                Integer itemIndex = Integer.valueOf(itemSplitToSpecs[0].trim());
                Double itemWeight = Double.valueOf(itemSplitToSpecs[1].trim());
                String itemPrice = fixItemPriceFormat(itemSplitToSpecs[2].trim());
                itemList.add(new Item(itemIndex, itemWeight, itemPrice));
            });
            Package pkg = new Package(packageWeight, itemList);
            packageList.add(pkg);
        });
        return packageList;
    }

    /**
     * Converts the format of the item price to a currency format
     * example: $1 to $ 1.00
     * Currently support EUR only
     *
     * @param itemPrice - The price of the item
     * @return Price of the item that is converted into currency format
     */
    private static String fixItemPriceFormat(String itemPrice) {
        LOGGER.info("Fixing item price format");

        String formattedItemPrice = itemPrice.replaceAll("[^\\d.]+", "");
        Double doubleValue = Double.valueOf(formattedItemPrice);
        NumberFormat numberFormat =
                NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        numberFormat.setCurrency(Currency.getInstance("EUR"));
        return numberFormat.format(doubleValue);
    }

    /**
     * Groups each Items in package. This will separate the items from one straight string to a list for the package
     * This will help for parsing each Item in the package
     *
     * @param packageItems Items in string format
     * @return List of String which is the information of Items.
     */
    private static List<String> groupItems(String packageItems) {
        List<String> itemGroupList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(packageItems);
        while (matcher.find()) {
            String itemGroup = matcher.group(1);
            itemGroupList.add(itemGroup);
        }
        return itemGroupList;
    }


    /**
     * This method find the list of items which can be packed with maximum cost
     * possible and a total weight not more than maxWeight.
     *
     * @param maxWeight - Maximum possible weight of the package.
     * @param items     - List of items to choose from.
     * @return - Returns the choosen items with maximum possible weight and maximum
     * cost.
     */
    public static String getItemInfo(int maxWeight, List<Item> items) {
        int i, w;
        int n = items.size();
        if (maxWeight <= 0 || items == null || items.size() == 0)
            return "-";
        int K[][] = new int[n + 1][maxWeight + 1];
        int[] selected = new int[n + 1];
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= maxWeight; w++) {
                if (i == 0 || w == 0) {
                    K[i][w] = 0;
                } else if (items.get(i - 1).getWeight() <= w) {
                    Item item = items.get(i - 1);
                    selected[i] = 1;
                    K[i][w] = PackerUtil.max(PackerUtil.priceFormatter(item.getPrice()) + K[i - 1][w - Double.valueOf(item.getWeight()).intValue()],
                            K[i - 1][w]);
                } else {
                    selected[i] = 0;
                    K[i][w] = K[i - 1][w];
                }
            }
        }
        // find the index of the selected item.
        int tempWeight = maxWeight;
        int selectedIndex = 0;

        for (int x = n; x > 0; x--) {
            Item item = items.get(x - 1);
            if ((tempWeight - item.getWeight() >= 0) && (K[x][tempWeight]
                    - K[x - 1][tempWeight - Double.valueOf(item.getWeight()).intValue()] == PackerUtil.priceFormatter(item.getPrice()))) {
                selected[selectedIndex++] = x - 1;
                tempWeight -= item.getWeight();
            }
        }

        StringBuilder output = new StringBuilder();
        if (selectedIndex > 0) {
            for (int j = selectedIndex - 1; j >= 0; j--) {
                output.append(selected[j] + 1);
                if (j > 0) {
                    output.append(",");
                }
            }
        } else {
            output.append("-");
        }

        return output.toString();
    }
}