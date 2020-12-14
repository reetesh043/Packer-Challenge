package com.mobiquity.utils;

import com.mobiquity.data.Item;
import com.mobiquity.data.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Reetesh Kumar
 */

@DisplayName("ResponseWriter Unit Test")
public class ResponseWriterTest {
    private List<Package> packageList;

    @BeforeEach
    public void setup() {
        packageList = new ArrayList<>();
        for (int packageIndex = 0; packageIndex < 4; packageIndex++) {
            List<Item> itemList = new ArrayList<>();
            for (int itemIndex = 0; itemIndex < 15; itemIndex++) {
                Item item = new Item(itemIndex + 1, 100, "90");
                itemList.add(item);
            }
            Package pkg = new Package(80, itemList);
            packageList.add(pkg);
        }
    }

    @Test
    public void shouldReadFilesAndReturnLinesAsList() {
        String printPackageInfo = ResponseWriter.createPackageInfo(packageList);
        Assertions.assertNotNull(printPackageInfo);
    }
}
