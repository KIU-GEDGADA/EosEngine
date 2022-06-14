package enums;

/**
 * Enumerator, holds the possible states of a window:
 * -HIDDEN: hides the window
 * -SHOWN: shows the window
 * -FULLSCREEN: sets the window to full screen
 * -WINDOWED: sets the window to windowed mode based on its height and width
 * -BORDERLESS: sets the window to a maximized window without borders
 */
public enum WindowState {
    HIDDEN,
    SHOWN,
    FULLSCREEN,
    WINDOWED,
    BORDERLESS
}
