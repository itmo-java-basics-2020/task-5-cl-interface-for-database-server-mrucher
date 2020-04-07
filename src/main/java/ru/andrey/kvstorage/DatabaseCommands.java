package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CreateDatabaseCommand(env, args);
        }
    },
    CREATE_TABLE {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CreateTableCommand(env, args);
        }
    },
    READ_KEY {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new ReadKeyCommand(env, args);
        }
    },
    UPDATE_KEY {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new UpdateKeyCommand(env, args);
        }
    };
    abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args);
}