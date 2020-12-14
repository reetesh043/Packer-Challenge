package com.mobiquity.packer.validator;

import com.mobiquity.data.Item;
import com.mobiquity.data.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.validator.PackageValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Reetesh Kumar
 */
@DisplayName("PackageValidator Unit Test")
public class PackageValidatorTest {
    private Package pkg;
    private List<Item> itemList;
    @InjectMocks
    private PackageValidator packageValidator;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void shouldThrowAnApiExceptionIfPackageWeightIsInvalid() {
        itemList = new ArrayList<>();
        for (int itemIndex = 0; itemIndex < 15; itemIndex++) {
            Item item = new Item(itemIndex+1, 100, "90");
            itemList.add(item);
        }
        pkg = new Package(110, itemList);
        String expectedMessage = "Package:" + pkg + " has a weight of " + pkg.getWeight() + ". It has exceeded the 100 weight limit";
        Throwable exception = assertThrows(APIException.class, () -> {
            packageValidator.validatePackageMaxWeight(pkg);
        });
        Assertions.assertTrue(exception.getMessage().equals(expectedMessage));
    }

    @Test
    public void shouldThrowAnApiExceptionIfPackageItemCountIsInvalid() {
        pkg = new Package(80, null);
        itemList = new ArrayList<>();
        for (int itemIndex = 0; itemIndex < 15; itemIndex++) {
            Item item = new Item(itemIndex+1, 100, "90");
            itemList.add(item);
        }
        Item item = new Item(99, 110, "90");
        itemList.add(item);
        pkg = new Package(110, itemList);

        String expectedMessage = "Package:" + pkg + " has a number of items " + pkg.getItemList().size() + ". It has exceeded the number of items limit";
        Throwable exception = assertThrows(APIException.class, () -> {
            packageValidator.validatePackageNumberOfItems(pkg);
        });
        Assertions.assertTrue(exception.getMessage().equals(expectedMessage));
    }

    @Test
    public void shouldThrowAnApiExceptionIfItemWeightIsInvalid() {
        itemList = new ArrayList<>();
        Item item = new Item(99, 110, "90");
        itemList.add(item);
        pkg = new Package(90, itemList);
        String expectedMessage = "Item:" + item + " has a weight of " + item.getWeight() + ". It has exceeded the weight limit";
        Throwable exception = assertThrows(APIException.class, () -> {
            packageValidator.validateItemsMaxWeightAndPrice(itemList);
        });

        Assertions.assertTrue(exception.getMessage().equals(expectedMessage));
    }

    @Test
    public void shouldThrowAnApiExceptionIfItemPriceIsInvalid() {
        itemList = new ArrayList<>();
        Item item = new Item(99, 100, "100.50");
        itemList.add(item);
        pkg = new Package(90, itemList);
        String expectedMessage = "Item:" + item + " has a price of " + item.getPrice() + ". It has exceeded the price limit";
        Throwable exception = assertThrows(APIException.class, () -> {
            packageValidator.validateItemsMaxWeightAndPrice(itemList);
        });

        Assertions.assertTrue(exception.getMessage().equals(expectedMessage));
    }
}