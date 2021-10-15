package op_sys;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class JNativeHook implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            System.out.println("\nEscape has been pressed to hard reset the program");
            System.exit(0);
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) { }

    public void nativeKeyTyped(NativeKeyEvent e) { }
}
