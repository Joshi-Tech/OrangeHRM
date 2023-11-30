package utils;



import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StringExtract {

    private static final ResourceBundle resourceBundle;

    static {resourceBundle=ResourceBundle.getBundle("VariousStrings/stringFile");}

    public static String getString(String key){
        return resourceBundle.getString(key);
    }

    public static String getStrings(List<String> keys){
        List<String> listOfTexts=keys.stream().map(StringExtract::getString).toList();
        return StringUtils.join(listOfTexts,"");
    }

    public static List<String> getListFromString(String key){
        return Arrays.stream(getString(key).split("\\|")).toList();
    }
}
