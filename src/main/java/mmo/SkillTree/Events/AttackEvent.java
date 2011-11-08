package mmo.SkillTree.Events;

import mmo.SkillTree.Weapons.WeaponSet;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class AttackEvent extends Event implements Cancellable {
    
    private boolean cancelled = false;
    private Player player;
    private WeaponSet weaponSet;
    
    public AttackEvent( Player p, WeaponSet weaponSet ){
        super("ATTACK");
        player = p;
        this.weaponSet = weaponSet;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public WeaponSet getWeapon(){
        return weaponSet;
    }
    
    @Override
    public boolean isCancelled(){
        return cancelled;
    }

    @Override
    public void setCancelled( boolean bln ){
        this.cancelled = bln;
    }
    
}