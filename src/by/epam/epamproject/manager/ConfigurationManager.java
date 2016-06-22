package by.epam.epamproject.manager;


import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final String PROPERTY_PATH = "resource.pathconfig";

    private ConfigurationManager() { }

    public static String getProperty(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
        return resourceBundle.getString(key);
    }
}
