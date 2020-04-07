package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultDefaultClass(true, result);
    }

    static DatabaseCommandResult error(String message) {
        return new DatabaseCommandResultDefaultClass(false, message);
    }

    class DatabaseCommandResultDefaultClass implements DatabaseCommandResult {

        boolean isSuccess;
        String resultInfo;

        private DatabaseCommandResultDefaultClass(boolean isSuccess, String resultInfo) {
            this.isSuccess = isSuccess;
            this.resultInfo = resultInfo;
        }

        @Override
        public Optional<String> getResult() {
            if (isSuccess) {
                return Optional.of(resultInfo);
            } else {
                return Optional.empty();
            }
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            if (isSuccess) {
                return DatabaseCommandStatus.SUCCESS;
            } else {
                return DatabaseCommandStatus.FAILED;
            }
        }

        @Override
        public boolean isSuccess() {
            return isSuccess;
        }

        @Override
        public String getErrorMessage() {
            if (isSuccess) {
                return null;
            } else {
                return resultInfo;
            }
        }
    }
}