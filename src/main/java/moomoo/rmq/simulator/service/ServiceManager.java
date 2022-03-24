package moomoo.rmq.simulator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.*;

public class ServiceManager {

    private static final Logger log = LoggerFactory.getLogger(ServiceManager.class);

    private static ServiceManager serviceManager = null;

    private boolean isQuit = false;

    public ServiceManager() {
        // nothing
    }

    public static ServiceManager getInstance() {
        if (serviceManager == null) {
            serviceManager = new ServiceManager();
        }
        return serviceManager;
    }

    public void loop() {
        try {
            this.startService();
        } catch (Exception e) {
            log.error("ServiceManager.loop ", e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.error("Process is about to quit (Ctrl+C)");
            this.stopService();
        }));

        while (!isQuit) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                log.error("ServiceManager.loop", e);
            }
        }

    }

    public void startService() {

        systemLock();

//        RmqManager.getInstance().startRmq();
    }

    public void stopService() {
//        RmqManager.getInstance().stop();
        isQuit = true;
    }

    /**
     * 시스템 lock 상태 확인
     */
    void systemLock() {
        String tmpdir = System.getProperty("java.io.tmpdir");
        File f = new File(tmpdir, System.getProperty("lock_file", "a2s.lock"));
        try {
            try (FileChannel fileChannel = FileChannel.open(f.toPath(), CREATE, READ, WRITE)) {
                FileLock lock = fileChannel.tryLock();
                if (lock != null) {
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                        try {
                            log.error("shutdown");
                            lock.release();
                            fileChannel.close();
                            Files.delete(f.toPath());
                        } catch (IOException e) {
                            //ignore
                        }
                    }));
                } else {
                    log.error("PBX_ACP_A2S process already running");
                    Thread.sleep(500L);
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            log.error("ServiceManagerImpl.systemLock", e);
        }
    }
}
