package moomoo.rmq.simulator;

import moomoo.rmq.simulator.config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppInstance {

    private static final Logger log = LoggerFactory.getLogger(AppInstance.class);

    private static AppInstance appInstance = null;

    private ConfigManager configManager;

    public static AppInstance getInstance() {
        if (appInstance == null) {
            appInstance = new AppInstance();
        }
        return appInstance;
    }
    public AppInstance() {
        // nothing
    }

    public void setInstance(String configPath) {
        setConfigManager(configPath);
    }


    public ConfigManager getConfigManager() {
        return configManager;
    }

    private void setConfigManager(String configPath) {
        this.configManager = new ConfigManager(configPath);
    }
}
