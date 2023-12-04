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
    private NPC npc;
    public NPCDialogViewer(NPC npc) {
        this.npc = npc;
    }
    public void drawNPCDialog(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + npc.getName() + npc.getState().getFile() + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        gui.drawImage(new Position(2,118), "dialog");
        gui.drawText(new Position(6,122),190,lines.get(npc.getState().getLine()),0,false);
    }
    public void drawNPCDialogChoices(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + npc.getName() + npc.getState().getFile() + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        gui.drawImage(new Position(2,118), "dialog");
        int offset  = 122;
        for (int i = npc.getState().getMax()+2; i <npc.getState().getMax() +2 + npc.getState().getChoices(); i++) {
            if (i == npc.getState().getLine()) {
                gui.drawText(new Position(6,offset),190,lines.get(i),0,true);
            }
            else {
                gui.drawText(new Position(6,offset),190,lines.get(i),0,false);
            }
            offset += 10;
        }
    }
}
