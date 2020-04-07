package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.DatabaseException;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public static void main(String[] args) {

    }

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Command name is missing");
        }

        boolean commandExists = true;
        String[] args = commandText.split(" ");

        try {
            DatabaseCommands.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            commandExists = false;
        }

        if (!commandExists) {
            return DatabaseCommandResult.error("Incorrcect command name");
        }

        DatabaseCommand next = DatabaseCommands.valueOf(args[0]).getCommand(env, args);
        try {
            return next.execute();
        } catch (DatabaseException dbe) {
            return DatabaseCommandResult.error(dbe.getMessage());
        }
    }
}