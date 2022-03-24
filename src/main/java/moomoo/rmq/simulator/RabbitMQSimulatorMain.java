package moomoo.rmq.simulator;


public class RabbitMQSimulatorMain {

    public static void main(String[] args) {

        AppInstance.getInstance().setInstance(args[0]);

//        XmlParser xmlParser = new XmlParser();
//        if (xmlParser.readMainXmlFile()) {
//            ScenarioCli scenarioCli = new ScenarioCli();
//            scenarioCli.startCli();
//
//            ServiceManager.getInstance().loop();
//        }
    }
}
