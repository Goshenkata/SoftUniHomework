package goldDigger;

import goldDigger.core.ControllerImpl;
import goldDigger.core.Engine;
import goldDigger.core.EngineImpl;
import goldDigger.core.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
