/*
 * This file is part of mmoSkillTree <http://github.com/mmoMinecraftDev/mmoSkillTree>.
 *
 * mmoSkillTree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mmo.SkillTree;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class MMOPlayerManager {
	private Map<String, SkillsPlayer> mmoPlayers;
	private MMOSkillTree plugin;

	public MMOPlayerManager(MMOSkillTree plugin) {
		this.plugin = plugin;
		this.mmoPlayers = new HashMap<String, SkillsPlayer>();
	}

	public SkillsPlayer get(Player player) {
		String name = player.getName().toLowerCase();
		SkillsPlayer mmoPlayer = mmoPlayers.get(name);
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
		mmoPlayers.put(player.getName().toLowerCase(), new SkillsPlayer(plugin, player));
	}
}
