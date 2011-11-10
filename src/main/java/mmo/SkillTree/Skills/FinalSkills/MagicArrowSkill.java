package mmo.SkillTree.Skills.FinalSkills;

import mmo.SkillTree.Events.AttackEvent;
import mmo.SkillTree.Events.SkillUseEvent;
import mmo.SkillTree.Skills.OnAttackSkill;
import mmo.SkillTree.Weapons.WeaponSet;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class MagicArrowSkill extends OnAttackSkill{

	public MagicArrowSkill( Player player ){
		super(player);
	}

	@Override
	public void onAttack( AttackEvent event ){
		WeaponSet weaponSet = event.getWeapon();
		Player p = event.getPlayer();
		if( weaponSet.equals( WeaponSet.Bow ) && p.equals(this.player) ){
			PlayerInventory inventory = p.getInventory();
			ItemStack[] allItems = inventory.getContents();
			boolean foundStack = false;
			for( ItemStack itemStack : allItems ){
				if( itemStack != null/*Are you serious, bukkit?*/ && itemStack.getType().equals( Material.ARROW ) && foundStack == false ){
					if( itemStack.getAmount() != itemStack.getMaxStackSize() ){
						itemStack.setAmount( itemStack.getAmount() + 1 );
						foundStack = true;
					}
				}
			}
			if( foundStack == false ){
				inventory.setItem( inventory.firstEmpty(), new ItemStack( Material.ARROW ) );
			}

			skillUseEvent();
		}
	}
}
