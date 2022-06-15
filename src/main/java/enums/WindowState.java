package enums;

/**
 * Enumerator, holds the possible states of a window
 */
public enum WindowState {
    /**
     * HIDDEN: hides the window
     */
    HIDDEN,
    /**
     * SHOWN: sets the window to shown
     */
    SHOWN,
    /**
     * FULLSCREEN: sets the window to full screen
     */
    FULLSCREEN,
    /**
     * WINDOWED: sets the window to windowed mode based on its height and width
     */
    WINDOWED,
    /**
     * BORDERLESS: sets the window to a maximized window without borders
     */
    BORDERLESS
}
