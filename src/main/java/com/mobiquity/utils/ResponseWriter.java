package com.mobiquity.utils;

import com.mobiquity.data.Item;
import com.mobiquity.data.Package;
import com.mobiquity.processor.PackageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Reetesh Kumar
 * ResponseWriter - Utility class for creating the response of processed Package and Item information
 */
public class ResponseWriter {
    private static Logger LOGGER = LoggerFactory.getLogger(ResponseWriter.class);

    private ResponseWriter() {
        // default constructor
    }

    /**
     * Prints the Packages Information
     *
     * @param packageList - List of Packages
     * @return String - Information regarding the number of packages and the indexes of each item of each package
     */
    public static String createPackageInfo(List<Package> packageList) {
        LOGGER.info("Creating Package Information");

        StringBuilder packageInfo = new StringBuilder();
        packageList.forEach(pkg -> {
            List<Item> itemList = pkg.getItemList();
            packageInfo.append(PackageProcessor.getItemInfo(pkg.getWeight(), itemList));
            packageInfo.append("\n");
        });
        return packageInfo.toString();
    }
}