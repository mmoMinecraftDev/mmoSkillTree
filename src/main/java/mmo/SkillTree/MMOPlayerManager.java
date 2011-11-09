package mmo.SkillTree;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class MMOPlayerManager {

	private Map<String, MMOPlayer> mmoPlayers;
	private MMOSkillTree plugin;

	public MMOPlayerManager(MMOSkillTree plugin) {
		this.plugin = plugin;
		this.mmoPlayers = new HashMap<String, MMOPlayer>();
	}

	public MMOPlayer get(Player player) {
		String name = player.getName().toLowerCase();
		MMOPlayer mmoPlayer = mmoPlayers.get(name);
		if (mmoPlayer != null) {
			if (mmoPlayer.getPlayer().getEntityId() != player.getEntityId()) {
				mmoPlayers.remove(name);
			} else {
				return mmoPlayer;
			}
		}
		//null, so make it.
		//Need to load from storage here instead, as well.
		add(player);
		mmoPlayer = mmoPlayers.get(name);
		return mmoPlayer;
	}

	public void add(Player player) {
		mmoPlayers.put(player.getName().toLowerCase(), new MMOPlayer(plugin, player));
	}
}
