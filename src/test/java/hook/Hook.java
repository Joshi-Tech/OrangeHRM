package hook;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverManager;

public class Hook extends DriverManager {
    private static final DriverManager driverManager = new DriverManager();

    @Before
    public void setUp() {
        // DriverManager will initialize the driver (local or remote)
    }

    @After
    public void tearDown() {
        driverManager.quitDriver();
    }
}
