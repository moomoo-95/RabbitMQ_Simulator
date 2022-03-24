package moomoo.rmq.simulator.config;

import org.ini4j.Ini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ConfigManager {

    private static final Logger log = LoggerFactory.getLogger(ConfigManager.class);

    private static final String CONFIG_LOG = "Load [{}] config...(OK)";
    private static final String PORT_RANGE_LOG = "[{}] config [{}] : [{}] Error (1024 - 32767)";

    private Ini ini = null;

    // SECTION
    private static final String SECTION_COMMON = "COMMON";
    private static final String SECTION_RMQ = "RMQ";

    // Field
    // COMMON
    private static final String FIELD_COMMON_XML_PATH = "XML_PATH";
    // RMQ
    private static final String FIELD_RMQ_LOCAL = "LOCAL";
    private static final String FIELD_RMQ_A2S = "A2S";
    private static final String FIELD_RMQ_HOST = "HOST";
    private static final String FIELD_RMQ_USER = "USER";
    private static final String FIELD_RMQ_PORT = "PORT";
    private static final String FIELD_RMQ_PASS = "PASS";
    private static final String FIELD_RMQ_THREAD_SIZE = "THREAD_SIZE";
    private static final String FIELD_RMQ_QUEUE_SIZE = "QUEUE_SIZE";
    private static final String FIELD_RMQ_TIME_OUT = "TIME_OUT";

    //COMMON
    private String commonXmlPath = "";
    // RMQ
    private String rmqLocal = "";
    private String rmqA2s = "";
    private String rmqHost = "";
    private String rmqUser = "";
    private int rmqPort = 0;
    private String rmqPass = "";
    private int rmqThreadSize = 0;
    private int rmqQueueSize = 0;
    private int rmqTimeOut = 0;

    public ConfigManager(String configPath) {
        File iniFile = new File(configPath);
        if (!iniFile.isFile() || !iniFile.exists()) {
            log.warn("Not found the config path. (path={})", configPath);
            return;
        }

        try {
            this.ini = new Ini(iniFile);

            loadCommonConfig();
            loadRmqConfig();
        } catch (Exception e) {
            log.error("ConfigManager ", e);
        }
    }

    private void loadCommonConfig() {
        this.commonXmlPath = getIniValue(SECTION_COMMON, FIELD_COMMON_XML_PATH);

        log.debug(CONFIG_LOG, SECTION_COMMON);
    }

    private void loadRmqConfig() {
        this.rmqLocal = getIniValue(SECTION_RMQ, FIELD_RMQ_LOCAL);
        this.rmqA2s = getIniValue(SECTION_RMQ, FIELD_RMQ_A2S);
        this.rmqHost = getIniValue(SECTION_RMQ, FIELD_RMQ_HOST);
        this.rmqUser = getIniValue(SECTION_RMQ, FIELD_RMQ_USER);
        this.rmqPort = Integer.parseInt(getIniValue(SECTION_RMQ, FIELD_RMQ_PORT));
        if (rmqPort < 1024 || rmqPort > 32767) {
            log.error(PORT_RANGE_LOG, SECTION_RMQ, FIELD_RMQ_PORT, rmqPort);
            System.exit(1);
        }
        this.rmqPass = getIniValue(SECTION_RMQ, FIELD_RMQ_PASS);
        this.rmqThreadSize = Integer.parseInt(getIniValue(SECTION_RMQ, FIELD_RMQ_THREAD_SIZE));
        this.rmqQueueSize = Integer.parseInt(getIniValue(SECTION_RMQ, FIELD_RMQ_QUEUE_SIZE));
        this.rmqTimeOut = Integer.parseInt(getIniValue(SECTION_RMQ, FIELD_RMQ_TIME_OUT));

        log.debug(CONFIG_LOG, SECTION_RMQ);
    }

    private String getIniValue(String section, String key){
        String value = ini.get(section, key);
        if (value == null) {
            log.error("[{}] \"{}\" is null.", section, key);
            System.exit(1);
            return null;
        }

        value = value.trim();
        log.debug("Get [{}] config [{}] : [{}]", section, key, value);
        return  value;
    }

    private void setIniValue(String section, String key, String value) {
        try {
            ini.put(section, key, value);
            ini.store();

            log.debug("Set [{}] config [{}] : [{}]", section, key, value);
        } catch (Exception e) {
            log.warn("Fail to set [{}] config [{}] : [{}] ", section, key, value);
        }
    }

    //common
    public String getCommonXmlPath() {
        return commonXmlPath;
    }

    // rmq
    public String getRmqLocal() {
        return rmqLocal;
    }

    public String getRmqA2s() {
        return rmqA2s;
    }

    public String getRmqHost() {
        return rmqHost;
    }

    public String getRmqUser() {
        return rmqUser;
    }

    public int getRmqPort() {
        return rmqPort;
    }

    public String getRmqPass() {
        return rmqPass;
    }

    public int getRmqThreadSize() {
        return rmqThreadSize;
    }

    public int getRmqQueueSize() {
        return rmqQueueSize;
    }

    public int getRmqTimeOut() {
        return rmqTimeOut;
    }
}
