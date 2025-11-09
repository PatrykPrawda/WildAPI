package pl.wildlabs.wildapi.functions.data;

import org.bukkit.Location;

public class HologramData {
    String name;
    String text;
    int time;
    Location location;

    public HologramData(String hologramName, String hologramText, int hologramTime, Location hologramLocation) {
        this.name = hologramName;
        this.text = hologramText;
        this.time = hologramTime;
        this.location = hologramLocation;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }

    public int getTime() {
        return this.time;
    }

    public Location getLocation() {
        return this.location;
    }
}
