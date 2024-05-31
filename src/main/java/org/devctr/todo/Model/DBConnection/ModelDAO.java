package org.devctr.todo.Model.DBConnection;

import org.devctr.todo.Model.DBConnection.Factory.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelDAO {
    protected ConnectionFactory connection;
    protected Statement statement;
    protected String tableName;
    protected String tableId;

    public ModelDAO() {
        connection = new ConnectionFactory();

        try {
            statement = connection.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String id, String column, String value){
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", this.tableName, column, this.tableId);
        PreparedStatement preparedStatement;

        try{
            preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }

    public void delete(String id){
        String sql = String.format("DELETE FROM %s WHERE %s = ?", this.tableName, this.tableId);
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
