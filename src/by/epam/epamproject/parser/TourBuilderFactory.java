package by.epam.epamproject.parser;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TourBuilderFactory {
    private static Logger log = LogManager.getLogger(TourBuilderFactory.class);

    public AbstractToursBuilder createTourBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new ToursDOMBuilder();
            case STAX:
                return new ToursStAXBuilder();
            case SAX:
                return new ToursSAXBuilder();
            default:
                log.error("This by.epam.epamproject.parser type is not present. Information will be processed by DOM by.epam.epamproject.parser");
                return new ToursDOMBuilder();
        }
    }
}
