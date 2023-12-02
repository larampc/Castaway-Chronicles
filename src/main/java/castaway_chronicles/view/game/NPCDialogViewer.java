package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.NPC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class NPCDialogViewer {
    private NPC npc;
    public NPCDialogViewer(NPC npc) {
        this.npc = npc;
    }
    public void drawNPCDialog(GUI gui) throws IOException, InterruptedException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + npc.getName() + npc.getState().getFile() + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        gui.drawDialog(lines.get(npc.getState().getLine()));
    }
}
