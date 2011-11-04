package mmo.skills.mmoSkillTree.Events;

import java.util.HashMap;

import mmo.skills.mmoSkillTree.MMOPlayer;
import mmo.skills.mmoSkillTree.SkillTree;
import mmo.skills.mmoSkillTree.Skills.SkillSet;
import mmo.skills.mmoSkillTree.Weapons.WeaponSet;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Block;
import org.getspout.spoutapi.player.SpoutPlayer;

public class BlockXpListener extends BlockListener {
	private static SkillTree plugin;
	public BlockXpListener(){
		plugin = SkillTree.plugin;
	}
	
	@Override
	public void onBlockBreak(BlockBreakEvent event){
		if (event.isCancelled())
            return;
		SpoutPlayer p = (SpoutPlayer) event.getPlayer();
		Block block = event.getBlock();
		ItemStack wielding = p.getItemInHand();
		HashMap<Integer, Integer> mining = new HashMap<Integer, Integer>();
		mining.put(1, 15);//stone
		mining.put(4, 25);//cobblestone. Doesn't occur naturally much.. need checks.
		mining.put(24, 35);//sandstone
		mining.put(16, 40);//Coal
		mining.put(15, 70);//Iron
		mining.put(14, 100);//Gold
		mining.put(21, 100);//Lapis
		mining.put(48, 150);//Moss Stone
		mining.put(56, 300);//Diamond
		mining.put(73, 100);//Redstone
		mining.put(74, 100);//Um.. also redstone?
		mining.put(87, 35);//Netherrack
		mining.put(89, 50);//Glowstone
		mining.put(97, 500);//Silverfish stone. Surprise!
		mining.put(98, 35);//Stones in strongholds i guess
		//Missing some others like 98:1, as those aren't integers. They'll just have to share for now.
		HashMap<Integer, Integer> digging = new HashMap<Integer, Integer>();
		digging.put(13, 10);//gravel
		digging.put(12, 10);//sand
		digging.put(3, 10);//dirt
		digging.put(2, 15);//grass
		digging.put(82, 100);//clay
		digging.put(88, 35);//soul sand
		HashMap<Integer, Integer> chopping = new HashMap<Integer, Integer>();
		chopping.put(17, 50);//all wood
		
		WeaponSet weaponSet = itemSet(wielding);
		if( weaponSet.is(SkillSet.Tool) ){
			int blockId = block.getTypeId();
			int expVal = chopping.containsKey(blockId) ? chopping.get(blockId) : 0;
			if( expVal == 0 )
				expVal = digging.containsKey(blockId) ? digging.get(blockId) : 0;
			if( expVal == 0 )
				expVal = mining.containsKey(blockId) ? mining.get(blockId) : 0;
			if( expVal > 0 ){
				MMOPlayer mmoPlayer = plugin.mmoPlayerManager.get(p); 
				mmoPlayer.addXp(expVal, SkillSet.Tool);
			}
		}
	}
	
	/*
	 * TODO: Extend item set to return this. :( Need to know what weaponSet an item is all over.
	 */
	private WeaponSet itemSet(ItemStack wielding){
		int id = wielding.getTypeId();
		if( id == 278/*diamond*/ || id == 257/*iron*/ || id == 285/*gold*/ || id == 274/*stone*/ || id == 270/*wood*/)
			return WeaponSet.Pickaxe;
		else if( id == 277/*diamond*/ || id == 256/*iron*/ || id == 285/*gold*/ || id == 273/*stone*/ || id == 269/*wood*/)
			return WeaponSet.Shovel;
		else if( id == 279/*diamond*/ || id == 258/*iron*/ || id == 286/*gold*/ || id == 275/*stone*/ || id == 271/*wood*/)
			return WeaponSet.Axe;
		else if( id == 290 || id == 291 || id == 292 || id == 293 || id == 294 )
			return WeaponSet.Hoe;
		else if( id == 268/*wood*/ || id == 272/*stone*/ || id == 267/*iron*/ || id == 283/*gold*/ || id == 276/*diamond*/ )
			return WeaponSet.Sword;
		else if( id == 261 )
			return WeaponSet.Bow;
		else
			return WeaponSet.Unarmed;
	}
	
}
