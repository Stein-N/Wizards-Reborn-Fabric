package net.xstopho.wizards_reborn.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientTickHandler {
    private ClientTickHandler() {}

    public static int ticksInGame = 0;
    public static float partialTicks = 0;

    public static void clientTickEnd() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!client.isPaused()) {
                ticksInGame++;
                partialTicks = 0;
            }
        });
    }
}
