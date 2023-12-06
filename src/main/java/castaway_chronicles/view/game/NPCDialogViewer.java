package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.NPC;

import java.io.IOException;
import java.net.URISyntaxException;


public class NPCDialogViewer {
    private final NPC npc;
    public NPCDialogViewer(NPC npc) {
        this.npc = npc;
    }
    public void drawNPCDialog(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        gui.drawText(new Position(6,155),190,npc.getDialogState().getCurrentLine(),0,false);
    }
    public void drawNPCDialogChoices(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        int offset  = 155;
        for (int i = 0; i < npc.getDialogState().getChoices().getNumberEntries(); i++) {
            gui.drawText(new Position(6,offset),190,npc.getDialogState().getChoices().getEntry(i),0, i == npc.getDialogState().getChoices().getCurrentEntry());
            offset += 10;
        }
    }
}
