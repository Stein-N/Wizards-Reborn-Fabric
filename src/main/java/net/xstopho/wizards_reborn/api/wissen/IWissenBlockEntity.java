package net.xstopho.wizards_reborn.api.wissen;

public interface IWissenBlockEntity {
    int getWissen();
    int getMaxWissen();

    boolean canSendWissen();
    boolean canRecieveWissen();

    boolean canConnectSendWissen();
    boolean canConnectRecieveWissen();

    int getWissenPerRecieve();
    int getSendWissenCooldown();

    void setWissen(int wissen);
    void addWissen(int wissen);
    void removeWissen(int wissen);
}
