package com.mobiquity.utils;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * @author Reetesh Kumar
 */
@DisplayName("File Reader Unit Test")
public class FileUtilTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    public void shouldReadFilesAndReturnLinesAsList() {
        Assertions.assertDoesNotThrow(() -> {
            List<String> listOfContents = new FileUtil().returnFileContent("/example_input");;
            Assertions.assertFalse(listOfContents.isEmpty());
        });
    }

    @Test
    public void shouldThrowAPIExceptionIfFileNotFound() {
        String containsExpectedMessage = "filePath cannot be found /example_input1";
        APIException apiException = Assertions.assertThrows(APIException.class, () -> {
            new FileUtil().returnFileContent("/example_input1");
        });
        Assertions.assertTrue(apiException.getMessage().contains(containsExpectedMessage));
    }
}
