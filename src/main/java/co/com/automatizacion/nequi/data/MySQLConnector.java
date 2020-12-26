package co.com.automatizacion.nequi.data;

import co.com.automatizacion.nequi.utils.PageUtils;
import co.com.automatizacion.nequi.utils.LogWriter;

import java.sql.*;
import java.text.MessageFormat;

public class MySQLConnector {

    private String user;
    private String password;
    private String port;
    private String host;
    private String database;
    private String url;
    private Connection connect;
    private Statement statement;
    private ResultSet result;

    public MySQLConnector() {
        user = PageUtils.getPropertyValue("MySQLUsuarios.user");
        password = PageUtils.getPropertyValue("MySQLUsuarios.password");
        host = PageUtils.getPropertyValue("MySQLUsuarios.host");
        port = PageUtils.getPropertyValue("MySQLUsuarios.port");
        database = PageUtils.getPropertyValue("MySQLUsuarios.databaseName");
        url = MessageFormat.format("jdbc:mysql://{0}:{1}/{2}?", host,port, database);

        try {
            connect = DriverManager.getConnection(url, user, password);
            statement = connect.createStatement();
        } catch (SQLException throwables) {
            LogWriter.dbFatalLog("Unable to connect to database.\n" + throwables.getMessage());
        }
    }

    public void closeConection(ResultSet result) {
        LogWriter.dbInfoLog("Cerrando conexion con la base de datos");
        try {
            result.close();
            statement.close();
            connect.close();
        } catch (SQLException throwables) {
            LogWriter.dbErrorLog("Error al cerrar la conexion con la base de datos." + throwables.getMessage());
        }
    }

    public ResultSet query(String query) {

        try {
            result = statement.executeQuery(query);

        } catch (SQLException throwables) {
            LogWriter.dbErrorLog("Unable to execute the query: '" + query + "'\n" + throwables.getMessage());
        }
        return result;
    }

    public int tableRowCount(String table) {
        ResultSet countResult = null;
        String sqlQuery = String.format("SELECT COUNT(*) from %s", table);
        try {
            countResult = statement.executeQuery(sqlQuery);
            countResult.next();
            return countResult.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}
