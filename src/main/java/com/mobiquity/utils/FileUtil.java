package com.mobiquity.utils;

import com.mobiquity.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Reetesh Kumar
 * FileReader - Utility class for reading the Package information from the file
 */
public class FileUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public FileUtil() {
    }

    /**
     * Look in different places to retrieve the file.
     * @param filePath pathname of the file
     * @return an open InputStream if filePath can be found.
     */
    private InputStream getResourceAsStream(String filePath)
    {
        InputStream result = getClass().getClassLoader().getResourceAsStream(filePath);
        if (result == null) {
            result = getClass().getResourceAsStream(filePath);
            if (result == null) {
                result = ClassLoader.getSystemResourceAsStream(filePath);
            }
        }
        return result;
    }

    /**
     * Check filePath and attempt to return an InputStream.
     * @param filePath pathname of the file
     * @return an open InputStream if filePath can be found.
     */
    public List<String> returnFileContent(String filePath) throws APIException, IOException {
        if (filePath == null) {
            throw new APIException("filePath is null");
        }

        InputStream inputStream = this.getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new APIException("filePath cannot be found " + filePath);
        }
        List<String> numberOfRows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while (((line = reader.readLine()) != null)) {
            numberOfRows.add(line);
        }
        return numberOfRows;
    }
}
