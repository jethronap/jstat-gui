package detail.utils;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


public class StringUtils {


    /**
     * Convert a simple non-nested string in a json format
     * into a Map. Expected input is
     *
     * {
     *     "key":"value",
     *     "another_key":"another_value"
     * }
     */

    public static Map<String, Object> jsonStringToMap(String json){

        Map<String, Object> response = new HashMap<>();

        try {

            if(json == null || json == ""){
                return response;
            }

            response = new ObjectMapper().readValue(json, HashMap.class);

        }
        catch(Exception e){

        }

        return response;

    }
}
