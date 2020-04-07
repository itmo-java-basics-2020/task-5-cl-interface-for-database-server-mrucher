package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] args;

    public UpdateKeyCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (args.length < 5) {
            return DatabaseCommandResult.error("Too few arguments");
        }
        if (args.length > 5) {
            return DatabaseCommandResult.error("Too many arguments");
        }

        Optional<Database> database = env.getDatabase(args[1]);
        String tableName = args[2];
        String key = args[3];
        String value = args[4];

        if (database.isPresent()) {
            database.get().write(tableName, key, value);
            return DatabaseCommandResult.success("Update successfully");
        } else {
            return DatabaseCommandResult.error("Database is not exists");
        }
    }
}