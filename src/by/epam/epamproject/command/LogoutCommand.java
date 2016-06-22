package by.epam.epamproject.command;


import by.epam.epamproject.factory.CommandEnum;
import by.epam.epamproject.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    private static final String PAGE_PATH = "path.page.index";
    private static final String PARAM_NAME_LANGUAGE = "language";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PAGE_PATH);
        String language = (String) request.getSession().getAttribute(CommandEnum.LANGUAGE.name().toLowerCase());
        request.setAttribute(PARAM_NAME_LANGUAGE, language);
        request.getSession().invalidate();
        return page;
    }
}
