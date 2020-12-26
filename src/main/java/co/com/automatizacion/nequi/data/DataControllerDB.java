package co.com.automatizacion.nequi.data;

import co.com.automatizacion.nequi.data.dto.DTONequiData;
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

    public void insertNequiData(DTONequiData nequiData) {
        String insertQuery = "REPLACE INTO nequiData (registerDate, disponible, bolsillos, colchon, metas, tarjeta, congelado, total) VALUES ('%s', %d, %d, %d, %d, %d, %d, %d)";
        connectionSQL.insertQuery(String.format(insertQuery, nequiData.getRegisterDate(),
                                                             nequiData.getDisponible(),
                                                             nequiData.getBolsillos(),
                                                             nequiData.getColchon(),
                                                             nequiData.getMetas(),
                                                             nequiData.getTarjeta(),
                                                             nequiData.getCongelado(),
                                                             nequiData.getTotal()
                                                            ));
    }
}
