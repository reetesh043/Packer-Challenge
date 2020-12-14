package com.mobiquity.validator;

import com.mobiquity.data.Item;
import com.mobiquity.data.Package;
import com.mobiquity.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * @author Reetesh Kumar
 * PackageValidator - Validates the Package and List of Items in the Package.
 */
public class PackageValidator {
    private static Logger LOGGER = LoggerFactory.getLogger(PackageValidator.class);
    public static final Integer MAX_PACKAGE_WEIGHT = 100;
    public static final Integer MAX_ITEM_WEIGHT = 100;
    public static final Integer MAX_ITEM_LIST = 15;
    public static final Integer MAX_ITEM_PRICE = 100;

    /**
     * Validates Package weight.
     *
     * @param pkg - Instance of Package
     * @throws APIException -  If weight reaches more than 100 then it will throw an APIException
     */
    public static void validatePackageMaxWeight(Package pkg) throws APIException {
        LOGGER.info("Validating Package Weight");
        if (pkg.getWeight() > MAX_PACKAGE_WEIGHT) {
            LOGGER.error("Package has a weight of:{} is invalid!", pkg.getWeight());
            throw new APIException("Package:" + pkg + " has a weight of " + pkg.getWeight() + ". It has exceeded the 100 weight limit");
        }
    }

    /**
     * Validates Package's number of items.
     *
     * @param pkg - Instance of Package
     * @throws APIException -  If Package contains more than 15 items it will throw an APIException
     */
    public static void validatePackageNumberOfItems(Package pkg) throws APIException {
        LOGGER.info("Validating number of items in the package");
        int packageItemSize = pkg.getItemList().size();
        if (packageItemSize > MAX_ITEM_LIST) {
            throw new APIException("Package:" + pkg + " has a number of items " + packageItemSize + ". It has exceeded the number of items limit");
        }
    }

    /**
     * Validates Weight and Price of each Items in a Package.
     *
     * @param items - List of items in the Package
     * @throws APIException -  If the Item has exceeded the weight or cost limit
     */
    public static void validateItemsMaxWeightAndPrice(List<Item> items) throws APIException {
        LOGGER.info("Validating items weight and price");
        for (Item item : items) {
            validateItemWeightAndPrice(item);
        }
    }

    /**
     * Validates Weight and Price of the Item in a Package.
     *
     * @param item - Instance of an Item
     * @throws APIException -  If the Item has exceeded the weight or cost limit
     */
    private static void validateItemWeightAndPrice(Item item) throws APIException {
        boolean itemNotExceededMaxWeight = isItemNotExceededMaxWeight(item);
        boolean isItemNotExceededMaxPrice = isItemNotExceededMaxPrice(item);
        if (!itemNotExceededMaxWeight) {
            LOGGER.error("Item weight : {} exceeded maximum limit!", item.getWeight());
            throw new APIException("Item:" + item + " has a weight of " + item.getWeight() + ". It has exceeded the weight limit");
        }

        if (!isItemNotExceededMaxPrice) {
            LOGGER.error("Item price :{} exceeded maximum limit!", item.getPrice());
            throw new APIException("Item:" + item + " has a price of " + item.getPrice() + ". It has exceeded the price limit");
        }
    }

    /**
     * Checks if Item Weight exceeded 100 or not
     *
     * @param item - Instance of an Item
     * @return boolean - true if weight is less than or equal 100. Otherwise false
     */
    private static boolean isItemNotExceededMaxWeight(Item item) {
        return item.getWeight() <= MAX_ITEM_WEIGHT ? true : false;
    }

    /**
     * Checks if Item Price exceeded 100 or not
     *
     * @param item - Instance of an Item
     * @return boolean - true if price is less than or equal 100. Otherwise false
     */
    private static boolean isItemNotExceededMaxPrice(Item item) {
        String str = item.getPrice()
                .replaceAll("[^\\d.,]","")
                .replace(",",".");
        Double price = Double.valueOf(str);
        return price <= MAX_ITEM_PRICE ? true : false;
    }
}
