package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

import java.io.IOException;

public class Main {

    public static void main(String [] args) throws APIException, IOException {
        Packer.pack("/example_input");
    }
}
