package by.epam.epamproject.command;


import by.epam.epamproject.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class BackCommand implements ActionCommand {
    private static final String PAGE_PATH = "path.page.main";
    private static final String PARAM_NAME_USER = "user";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PAGE_PATH);
        request.setAttribute(PARAM_NAME_USER, request.getSession().getAttribute(PARAM_NAME_USER));
        return page;
    }
}
