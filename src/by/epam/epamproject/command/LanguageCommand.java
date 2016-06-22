package by.epam.epamproject.command;


import by.epam.epamproject.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LanguageCommand implements ActionCommand {
    private static final String PARAM_NAME_LANGUAGE = "language";
    private static final String PARAM_NAME_PAGE = "page";
    private static final String PAGE_PATH = "path.page.";

    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(PARAM_NAME_LANGUAGE);
        request.getSession(true).setAttribute(PARAM_NAME_LANGUAGE, language);
        request.setAttribute(PARAM_NAME_LANGUAGE, language);

        String page = ConfigurationManager.getProperty(PAGE_PATH + request.getParameter(PARAM_NAME_PAGE));

        return page;
    }
}
