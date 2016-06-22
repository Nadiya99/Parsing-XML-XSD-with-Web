package by.epam.epamproject.manager;

import java.util.Locale;
import java.util.ResourceBundle;


public class MessageManager {
    private static final String PROPERTY_PATH = "resource.pagecontent";

    private MessageManager() {
    }

    public static String getProperty(String key, String language) {
        Locale locale = new Locale(language);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH, locale);

        return resourceBundle.getString(key);
    }
}
