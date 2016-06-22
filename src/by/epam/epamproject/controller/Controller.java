package by.epam.epamproject.controller;

import by.epam.epamproject.command.ActionCommand;
import by.epam.epamproject.factory.ActionFactory;
import by.epam.epamproject.factory.CommandEnum;
import by.epam.epamproject.manager.ConfigurationManager;
import by.epam.epamproject.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;


@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static Logger log = LogManager.getLogger(Controller.class);
    private static final String PARAM_NAME_LANGUAGE = "language";
    private static final String PAGE_PATH = "path.page.index";
    private static final String PARAM_NAME_NULL_PAGE = "nullPage";
    private static final String ERROR_PROPERTY_NAME = "message.nullpage";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("WAS 1");
        if (request.getSession().getAttribute(CommandEnum.LANGUAGE.name().toLowerCase()) == null) {
            request.getSession(true).setAttribute(PARAM_NAME_LANGUAGE, Locale.getDefault().getLanguage());
        }
        log.debug("WAS 2");
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        String page = command.execute(request);
        log.debug("WAS 3");
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty(PAGE_PATH);
            String language = (String) request.getSession().getAttribute(CommandEnum.LANGUAGE.name().toLowerCase());
            request.getSession().setAttribute(PARAM_NAME_NULL_PAGE, MessageManager.getProperty(ERROR_PROPERTY_NAME, language));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
