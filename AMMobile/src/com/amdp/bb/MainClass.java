package com.amdp.bb;

import com.amdp.blackberry.screen.LoginScreen;

import net.rim.device.api.ui.UiApplication;

/**
 * This class extends the UiApplication class, providing a graphical user interface.
 */
public class MainClass extends UiApplication {
    /**
     * Entry point for application
     * 
     * @param args
     *            Command line arguments (not used)
     */
    public static void main( String[] args ) {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        MainClass theApp = new MainClass();
        theApp.enterEventDispatcher();
    }

    /**
     * Creates a new HelloBlackBerry object
     */
    public MainClass() {
        // Push a screen onto the UI stack for rendering.
        pushScreen( new LoginScreen() );
    }
}
