package org.devctr.todo.Model.Utils.Enums;

public enum TaskPriority {
    urgent;

    public String getPriority() {
        return switch (this) {
            case urgent -> "Urgent";
            default -> "Non-Urgent";
        };
    }
}
