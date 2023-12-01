package castaway_chronicles.gui;

import com.googlecode.lanterna.input.KeyStroke;


public class KeyAction extends Action<KeyStroke>{
    private KeyStroke keyStroke;
    public KeyAction(String type, KeyStroke keyStroke) {
        super(type);
        this.keyStroke = keyStroke;
    }
    @Override
    public KeyStroke getInfo() {
        return keyStroke;
    }
}
