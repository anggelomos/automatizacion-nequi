package co.com.automatizacion.nequi.data;

import co.com.automatizacion.nequi.utils.LogWriter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataControllerDB {

    MySQLConnector connectionSQL;
    Random rand = new Random();

    public DataControllerDB() {
        connectionSQL = new MySQLConnector();
    }

    public DTORegister getDBUser(int id) {
        DTORegister user = new DTORegister();
        String query = String.format("SELECT * FROM usuarios WHERE id = %s;", id);
        ResultSet responseDB = connectionSQL.query(query);
        try {
            responseDB.next();
            user = setUser(responseDB);
        } catch (SQLException throwables) {
            LogWriter.dbErrorLog("Error al hacer la conexion con la base de datos" + throwables.getMessage());
        }
        return user;
    }

    public DTORegister getRandomDBUser() {
        int userCount = connectionSQL.tableRowCount("usuarios");
        int randomIndex = rand.nextInt(userCount);
        DTORegister randomUser = new DTORegister();
        String query = String.format("SELECT * FROM usuarios WHERE id = %s;", randomIndex);
        ResultSet responseDB = connectionSQL.query(query);
        try {
            responseDB.next();
            randomUser = setUser(responseDB);
        } catch (SQLException throwables) {
            LogWriter.dbErrorLog("Error al hacer la conexion con la base de datos" + throwables.getMessage());
        }
        return randomUser;
    }

    public List<DTORegister> getDBUsers() {
        List<DTORegister> usersDB = new ArrayList<>();
        String query = "SELECT * FROM usuarios;";
        ResultSet responseDB = connectionSQL.query(query);

        try {
            LogWriter.dbInfoLog("Iniciando conexion con la base de datos");
            while (responseDB.next()) {
                DTORegister user = setUser(responseDB);
                usersDB.add(user);
            }
        } catch (SQLException throwables) {
            LogWriter.dbErrorLog("Error al hacer la conexion con la base de datos" + throwables.getMessage());
        }

        connectionSQL.closeConection(responseDB);
        LogWriter.dbInfoLog("Conexion con la base de datos terminada");
        return usersDB;
    }

    private DTORegister setUser(ResultSet result) throws SQLException {
        DTORegister user = new DTORegister();

        user.setId(result.getString("id"));
        user.setGender(result.getString("gender"));
        user.setFirstName(result.getString("firstName"));
        user.setLastName(result.getString("lastName"));
        user.setBirthday(result.getString("birthday"));
        user.setEmail(result.getString("email"));
        user.setCompanyName(result.getString("companyName"));
        user.setNewsletterOption(result.getString("newsletterOption"));
        user.setPassword(result.getString("password"));

        return user;
    }

}
