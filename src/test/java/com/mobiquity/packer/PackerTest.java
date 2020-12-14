package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Reetesh Kumar
 */
@DisplayName("Packer Unit Test")
public class PackerTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    public void shouldPrintPackageContentsFromInputFile() {
        String expectedOutput = "4\n" +
                "-\n" +
                "2,7\n" +
                "8,9\n";

        assertDoesNotThrow(() -> {
            String packInfo = Packer.pack("example_input");
            assertTrue(packInfo.equals(expectedOutput));
        });
    }

    @Test
    public void shouldThrowIfExceededPackageWeight() {
        String containsExpectedMessage = "It has exceeded the 100 weight limit";

        Throwable exception = assertThrows(APIException.class, () -> {
            Packer.pack("example_input_invalid_package_weight");
        });
        Assertions.assertTrue(exception.getMessage().contains(containsExpectedMessage));

    }

    @Test
    public void shouldThrowIfPackageExceededItemCount() {
        Throwable exception = assertThrows(APIException.class, () -> {
            Packer.pack("example_input_invalid_package_item_count");
        });
        Assertions.assertNotNull(exception.getMessage());

    }

    @Test
    public void shouldThrowIfItemExceededWeight() {
        String containsExpectedMessage = "It has exceeded the weight limit";

        Throwable exception = assertThrows(APIException.class, () -> {
            Packer.pack("example_input_invalid_item_weight");
        });
        Assertions.assertTrue(exception.getMessage().contains(containsExpectedMessage));

    }

    @Test
    public void shouldThrowIfItemExceededCost() {
        String containsExpectedMessage = "It has exceeded the price limit";

        Throwable exception = assertThrows(APIException.class, () -> {
            Packer.pack("example_input_invalid_item_cost");
        });
        Assertions.assertTrue(exception.getMessage().contains(containsExpectedMessage));

    }
}
