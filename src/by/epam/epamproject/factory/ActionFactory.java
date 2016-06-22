package by.epam.epamproject.factory;


import by.epam.epamproject.command.ActionCommand;
import by.epam.epamproject.command.EmptyCommand;
import by.epam.epamproject.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final String PARAM_NAME_COMMAND = "by/epam/epamproject/command";
    private static final String PARAM_NAME_ERROR = "wrongAction";
    private static final String ERROR_PROPERTY_NAME = "message.wrongaction";

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            String language = (String) request.getSession().getAttribute(CommandEnum.LANGUAGE.name().toLowerCase());
            request.setAttribute(PARAM_NAME_ERROR, action + MessageManager.getProperty(ERROR_PROPERTY_NAME, language));
        }
        return current;
    }
}
