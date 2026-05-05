package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JsonReader {
    public static List<ModelJson> read() throws Exception{
    ObjectMapper mapper = new ObjectMapper();

    ModelJson[] array = mapper.readValue(
            new File("data.json"),
            ModelJson[].class
    );
    return Arrays.asList(array);
}
}
