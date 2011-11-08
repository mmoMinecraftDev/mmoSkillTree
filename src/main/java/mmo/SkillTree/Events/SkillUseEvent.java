package mmo.SkillTree.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class SkillUseEvent extends Event implements Cancellable {
    
    private boolean cancelled = false;
    private Player player;
    
    public SkillUseEvent( Player p ){
        super("SKILL_USE");
        player = p;
    }
    
    public Player getPlayer(){
        return player;
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