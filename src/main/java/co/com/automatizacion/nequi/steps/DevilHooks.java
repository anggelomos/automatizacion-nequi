package co.com.automatizacion.nequi.steps;

import co.com.automatizacion.nequi.utils.PageUtils;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class DevilHooks {

    @Before
    public void before(Scenario scenario) {
        PageUtils.setScenarioName(scenario.getName());
    }
}
