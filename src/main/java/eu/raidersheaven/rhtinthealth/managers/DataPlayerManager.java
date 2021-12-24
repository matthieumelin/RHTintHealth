package eu.raidersheaven.rhtinthealth.managers;

import eu.raidersheaven.rhtinthealth.data.DataPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DataPlayerManager {
    private List<DataPlayer> datas;

    public DataPlayerManager() {
        this.datas = new ArrayList<>();
    }

    private void loadAll() {

    }

    public void add(Player player) {
        DataPlayer dataPlayer = get(player);
        if (dataPlayer == null) {
            dataPlayer = new DataPlayer(player, false);
            datas.add(dataPlayer);
        }
    }

    public void remove(Player player) {
        DataPlayer dataPlayer = get(player);
        if (dataPlayer != null) {
            datas.remove(dataPlayer);
        }
    }

    public DataPlayer get(Player player) {
        return datas.stream().filter(data -> data != null && data.getPlayer().equals(player)).findFirst().orElse(new DataPlayer(player, false));
    }
}
