package co.com.automatizacion.nequi.steps;

import co.com.automatizacion.nequi.controller.MainController;
import co.com.automatizacion.nequi.data.DataControllerDB;
import co.com.automatizacion.nequi.data.dto.DTONequiData;
import co.com.automatizacion.nequi.utils.PageUtils;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class AutomatizacionNequi {

    PageUtils pageUtils = new PageUtils(true);
    MainController mainController = new MainController(pageUtils);
    DataControllerDB dataControllerDB = new DataControllerDB();
    DTONequiData nequiData = new DTONequiData();

    @Cuando("ingreso a la pagina principal")
    public void ingresoALaPaginaPrincipal() {
        mainController.openLoginPage();
    }

    @Entonces("extraigo la informacion requerida")
    public void extraigoLaInformacionRequerida() {
        nequiData = mainController.getNequiData();
        pageUtils.closeBrowser();
    }

    @Y("la subo a la base de datos")
    public void laSuboALaBaseDeDatos() {
        dataControllerDB.insertNequiData(nequiData);
    }
}
