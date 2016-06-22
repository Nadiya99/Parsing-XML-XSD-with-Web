package by.epam.epamproject.factory;


import by.epam.epamproject.command.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    LANGUAGE {
        {
            this.command = new LanguageCommand();
        }
    },
    PARSETEXT {
        {
            this.command = new ParseTextCommand();
        }
    },
    BACK {
        {
            this.command = new BackCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
