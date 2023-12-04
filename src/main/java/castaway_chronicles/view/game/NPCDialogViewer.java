package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.NPC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class NPCDialogViewer {
    private final NPC npc;
    public NPCDialogViewer(NPC npc) {
        this.npc = npc;
    }
    public void drawNPCDialog(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + npc.getName() + npc.getState().getFile() + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        gui.drawText(new Position(6,155),190,lines.get(npc.getState().getLine()),0,false);
    }
    public void drawNPCDialogChoices(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + npc.getName() + npc.getState().getFile() + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        int offset  = 155;
        for (int i = npc.getState().getMax()+2; i <npc.getState().getMax() +2 + npc.getState().getChoices(); i++) {
            gui.drawText(new Position(6,offset),190,lines.get(i),0, i == npc.getState().getLine());
            offset += 10;
        }
    }
}
