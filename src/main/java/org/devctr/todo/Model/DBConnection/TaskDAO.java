package org.devctr.todo.Model.DBConnection;

import org.devctr.todo.Model.DBModel.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO extends ModelDAO {
    public TaskDAO() {
        tableName = "tasks";
        tableId = "task_id";
    }

    public void create(Task task) {
        String sql = String.format("INSERT INTO %s (task_name, description, deadline, priority, status) " +
                        "VALUES (?, ?, ?, ?, ?)", tableName);
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, task.getTask_name());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setTimestamp(3, task.getDeadline());
            preparedStatement.setString(4, task.getPriority());
            preparedStatement.setString(5, task.getStatus());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Task selectById(int task_id) {
        String sql = String.format("SELECT * FROM %s WHERE %s = %d", this.tableName, this.tableId, task_id);

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String task_name = resultSet.getString("task_name");
                String description = resultSet.getString("description");
                Timestamp deadline = resultSet.getTimestamp("deadline");
                Timestamp created_at = resultSet.getTimestamp("created_at");
                String priority = resultSet.getString("priority");
                String status = resultSet.getString("status");

                return new Task(task_id, task_name, description, deadline, created_at, priority, status);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Task> selectAll() {
        String sql = String.format("SELECT * FROM %s ORDER BY %s;", this.tableName, this.tableId);
        PreparedStatement preparedStatement = null;

        List<Task> tasks = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int task_id = resultSet.getInt("task_id");
                String task_name = resultSet.getString("task_name");
                String description = resultSet.getString("description");
                Timestamp deadline = resultSet.getTimestamp("deadline");
                Timestamp created_at = resultSet.getTimestamp("created_at");
                String priority = resultSet.getString("priority");
                String status = resultSet.getString("status");

                tasks.add(new Task(task_id, task_name, description, deadline, created_at, priority, status));
            }
            return tasks;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
