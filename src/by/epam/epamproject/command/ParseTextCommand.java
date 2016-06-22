package by.epam.epamproject.command;


import by.epam.epamproject.entity.RestTour;
import by.epam.epamproject.entity.Tour;
import by.epam.epamproject.entity.TreatmentTour;
import by.epam.epamproject.factory.CommandEnum;
import by.epam.epamproject.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.epamproject.parser.AbstractToursBuilder;
import by.epam.epamproject.parser.TourBuilderFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class ParseTextCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ParseTextCommand.class);

    private static final String PARAM_NAME_PARSER_TYPE = "parserType";
    private static final String PARAM_NAME_LANGUAGE = "language";
    private static final String STAX_PARSER_TYPE = "stax";
    private static final String FILE_PATH = "data/Tours.xml";
    private static final String FILE_PREFIX = "file:///";
    private static final String PARAM_NAME_REST_TOURS = "restTours";
    private static final String PARAM_NAME_TREATMENT_TOURS = "treatmentTours";
    private static final String PAGE_PATH = "path.page.result";

    @Override
    public String execute(HttpServletRequest request) {
        String parserType = request.getParameter(PARAM_NAME_PARSER_TYPE);

        String language = (String) request.getSession().getAttribute(CommandEnum.LANGUAGE.name().toLowerCase());
        request.setAttribute(PARAM_NAME_LANGUAGE, language);

        request.setAttribute(PARAM_NAME_PARSER_TYPE, parserType);

        TourBuilderFactory tourBuilderFactory = new TourBuilderFactory();
        AbstractToursBuilder builder = tourBuilderFactory.createTourBuilder(parserType);

        String filename = "";
        if (!parserType.equals(STAX_PARSER_TYPE)) {
            filename = FILE_PREFIX;
        }
        filename += request.getServletContext().getRealPath("/") + FILE_PATH;
        builder.buildSetTours(filename);

        Set<Tour> tours = builder.getTours();
        Set<TreatmentTour> treatmentTours = new HashSet<TreatmentTour>();
        Set<RestTour> restTours = new HashSet<RestTour>();

        for(Tour tour : tours) {
            if (tour instanceof TreatmentTour) {
                treatmentTours.add((TreatmentTour)tour);
            } else {
                restTours.add((RestTour) tour);
            }
        }

        request.setAttribute(PARAM_NAME_TREATMENT_TOURS, treatmentTours);
        request.setAttribute(PARAM_NAME_REST_TOURS, restTours);

        String page = ConfigurationManager.getProperty(PAGE_PATH);

        return page;
    }
}
