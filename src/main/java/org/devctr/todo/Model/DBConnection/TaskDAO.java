package org.devctr.todo.Model.DBConnection;

public class TaskDAO extends ModelDAO {
    public TaskDAO() {
        tableName = "tasks";
        tableId = "taskId";
    }
}
