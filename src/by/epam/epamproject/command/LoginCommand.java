package by.epam.epamproject.command;


import by.epam.epamproject.factory.CommandEnum;
import by.epam.epamproject.manager.ConfigurationManager;
import by.epam.epamproject.manager.LoginLogic;
import by.epam.epamproject.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PAGE_PATH_MAIN = "path.page.main";
    private static final String PAGE_PATH_LOGIN = "path.page.login";
    private static final String PARAM_NAME_USER = "user";
    private static final String PARAM_NAME_LANGUAGE = "language";
    private static final String PARAM_NAME_ERROR = "errorLoginPassMessage";
    private static final String ERROR_PROPERTY_NAME = "message.loginerror";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        if (LoginLogic.checkLogin(login, pass)) {
            request.setAttribute(PARAM_NAME_USER, login);
            request.getSession().setAttribute(PARAM_NAME_USER, login);

            String language = (String) request.getSession().getAttribute(CommandEnum.LANGUAGE.name().toLowerCase());
            request.setAttribute(PARAM_NAME_LANGUAGE, language);

            page = ConfigurationManager.getProperty(PAGE_PATH_MAIN);
        } else {
            String language = (String) request.getSession().getAttribute(CommandEnum.LANGUAGE.name().toLowerCase());

            request.setAttribute(PARAM_NAME_ERROR, MessageManager.getProperty(ERROR_PROPERTY_NAME, language));
            page = ConfigurationManager.getProperty(PAGE_PATH_LOGIN);
        }
        return page;
    }
}
