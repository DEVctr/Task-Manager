package org.devctr.todo.Model.Utils.Enums;

public enum TaskStatus {
    todo, doing, done, archived;

    public String getStatus() {
        return switch (this) {
            case doing -> "Doing";
            case done -> "Done";
            case todo -> "Todo";
            case archived -> "Archived";
        };
    }
}
