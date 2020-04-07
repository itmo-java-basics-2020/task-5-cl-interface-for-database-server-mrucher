package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] args;

    public ReadKeyCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (args.length < 4) {
            return DatabaseCommandResult.error("Too few arguments");
        }
        if (args.length > 4) {
            return DatabaseCommandResult.error("Too many arguments");
        }

        Optional<Database> database = env.getDatabase(args[1]);
        String tableName = args[2];
        String key = args[3];

        if (database.isPresent()) {
            database.get().read(tableName, key);
            return DatabaseCommandResult.success("Key was read successfully");
        } else {
            return DatabaseCommandResult.error("Database is not exists");
        }
    }
}
