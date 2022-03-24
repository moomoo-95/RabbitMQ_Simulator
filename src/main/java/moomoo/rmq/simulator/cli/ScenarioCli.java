package moomoo.rmq.simulator.cli;

import moomoo.rmq.simulator.AppInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ScenarioCli {

    private static final Logger log = LoggerFactory.getLogger(ScenarioCli.class);

    private final AppInstance instance = AppInstance.getInstance();
//
//    private final ConcurrentHashMap<String, ArrayList<ScenarioInfo>> classMap;
//    private final ArrayList<String> keyList;

    public ScenarioCli() {
//        classMap = instance.getScenarioClassMap();
//        keyList = new ArrayList<>();
//
//        Set<String> set = classMap.keySet();
//        Iterator<String> iterator = set.iterator();
//        while(iterator.hasNext()){
//            keyList.add(iterator.next());
//        }
    }

//    public void startCli() {
//        int selectNumber = -1;
//        String selectKey = "";
//
//        while (selectNumber == -1) {
//            selectNumber = classListPrint();
//        }
//        selectKey = keyList.get(selectNumber);
//        selectNumber = -1;
//
//        while (selectNumber == -1) {
//            selectNumber = scenarioListPrint(selectKey);
//        }
//    }

//    private int classListPrint() {
//        log.debug("Please select a Class.");
//
//        for (int index = 0; index < keyList.size(); index++) {
//            log.debug("{}. {}", index, keyList.get(index));
//        }
//        Scanner scanner = new Scanner(System.in);
//        String selectClass = scanner.next();
//        if(isInteger(selectClass)) {
//            int result = Integer.parseInt(selectClass);
//            if (result >= 0 && result < keyList.size()) {
//                log.debug("{}. {} select.", result, keyList.get(result));
//                return result;
//            }
//        }
//        return -1;
//    }

//    private int scenarioListPrint(String classKey) {
//        log.debug("Please select a scenario in {}.", classKey);
//
//        ArrayList<ScenarioInfo> scenarioInfoList = classMap.get(classKey);
//
//        for (int index = 0; index < scenarioInfoList.size(); index++) {
//            ScenarioInfo scenarioInfo = scenarioInfoList.get(index);
//            log.debug("{}. {} : {}", index, scenarioInfo.getName(), scenarioInfo.getDescription());
//        }
//        Scanner scanner = new Scanner(System.in);
//        String selectScenarioInfo = scanner.next();
//        if(isInteger(selectScenarioInfo)) {
//            int result = Integer.parseInt(selectScenarioInfo);
//            if (result >= 0 && result < scenarioInfoList.size()) {
//                ScenarioInfo scenarioInfo = scenarioInfoList.get(result);
//                log.debug("{}. {} select.", result, scenarioInfo.getName());
//                instance.setScenarioInfo(scenarioInfo);
//                return result;
//            }
//        }
//        return -1;
//    }

    private boolean isInteger(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}