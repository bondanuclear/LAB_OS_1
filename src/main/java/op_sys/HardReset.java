package op_sys;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;


import java.util.logging.Logger;

public class HardReset {
    public static void pressToHardReset() {
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setUseParentHandlers(false);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new JNativeHook());
    }
}
