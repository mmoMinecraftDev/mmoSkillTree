package mmo.SkillTree.Events;
import java.util.HashSet;
import mmo.SkillTree.MMOSkillTree;
import mmo.SkillTree.Skills.Skill;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;

public class SkillListener extends CustomEventListener{
    MMOSkillTree skillTreePlugin;
    public SkillListener(MMOSkillTree subPlugin){
         skillTreePlugin = subPlugin;
    }
    
    @Override
    public void onCustomEvent(Event event){
        
        if( event instanceof AttackEvent ){
            onAttack((AttackEvent)event);
        } else if( event instanceof SkillUseEvent ){
            onSkillUse((SkillUseEvent)event);
        }
    }
    
    public void onAttack(AttackEvent event){
        String eventName = event.getType() == Type.CUSTOM_EVENT ? event.getEventName() : event.getType().toString();
        HashSet<Skill> skills = skillTreePlugin.listeners.get(eventName);
        if( skills != null ){
            for (Skill skill : skills) {
                skill.onAttack(event);
            }
        }
    }

    private void onSkillUse( SkillUseEvent event ){
        String eventName = event.getType() == Type.CUSTOM_EVENT ? event.getEventName() : event.getType().toString();
        HashSet<Skill> skills = skillTreePlugin.listeners.get(eventName);
        if( skills != null ){
            for (Skill skill : skills) {
                skill.onSkillUse(event);
            }
        }
    }
}