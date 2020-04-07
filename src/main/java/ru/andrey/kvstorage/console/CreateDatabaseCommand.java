package ru.andrey.kvstorage.console;

import org.w3c.dom.ls.LSOutput;
import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabaseCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] args;

    public CreateDatabaseCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (args.length < 2) {
            return DatabaseCommandResult.error("Too few arguments");
        }
        if (args.length > 2) {
            return DatabaseCommandResult.error("Too many arguments");
        }

        if (env.getDatabase(args[1]).isPresent()) {
            return DatabaseCommandResult.error("Database already exists");
        } else {
            return DatabaseCommandResult.success("Database was created");
        }
    }
}