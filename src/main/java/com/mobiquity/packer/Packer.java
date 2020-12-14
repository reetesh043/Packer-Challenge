package com.mobiquity.packer;

import com.mobiquity.data.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.processor.PackageProcessor;
import com.mobiquity.utils.FileUtil;
import com.mobiquity.utils.ResponseWriter;
import com.mobiquity.validator.PackageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;

/**
 * @author Reetesh Kumar
 * Packer - The main api class for processing Package Information from file
 * and returning the response to the client.
 */

public class Packer {
    private static Logger LOGGER = LoggerFactory.getLogger(Packer.class);

    private Packer() {
        // default constructor
    }

    /**
     * Converts package information from the file to String
     * This will give the total number of packages and the index number of each package
     *
     * @param path - File path of the package information stored in file
     * @return String - The total number of packages and the index of each package
     * @throws APIException - Throws APIException
     */
    public static String pack(String path) throws APIException, IOException {
        LOGGER.info("Processing Package Information from file:{}", path);
        List<String> listOfContents = new FileUtil().returnFileContent(path);
        List<Package> packageList = PackageProcessor.processPackage(listOfContents);
        validatePackages(packageList);
        String responseString = ResponseWriter.createPackageInfo(packageList);

        LOGGER.info("Package Information from file:\n{}", responseString);
        return responseString;
    }

    /**
     * Validates each Packages and each of its items
     *
     * @param packageList - List of Packages
     * @throws APIException - Throws APIException if validation fails.
     */
    private static void validatePackages(List<Package> packageList) throws APIException {
        for (Package pkg : packageList) {
            LOGGER.info("Validating Package:{} Information");
            PackageValidator.validatePackageNumberOfItems(pkg);
            PackageValidator.validatePackageMaxWeight(pkg);
            PackageValidator.validateItemsMaxWeightAndPrice(pkg.getItemList());
        }
    }


}