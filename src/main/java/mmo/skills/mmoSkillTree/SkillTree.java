package mmo.skills.mmoSkillTree;

import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
//import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.bukkit.Server;
import org.getspout.spoutapi.SpoutManager;

import mmo.Core.MMOPlugin;
import mmo.skills.mmoSkillTree.Events.BlockXpListener;
import mmo.skills.mmoSkillTree.Events.CombatXpListener;

public class SkillTree extends MMOPlugin/*JavaPlugin*/ {
	
	public static SkillTree plugin;
	
	private ServerChatPlayerListener playerListener;
	private SkillsKeyboardListener keyboardListener;
	private SkillsGuiButtonEvents screenListener;
	private BlockXpListener blockXpListener;
	public MMOPlayerManager mmoPlayerManager;
	private CombatXpListener combatXpListener;
	public final Logger logger = Logger.getLogger("Minecraft");	
	
	static public boolean hasSpout = false;
	static public boolean folder = false;
	
	protected Server server;
	protected PluginDescriptionFile description;

	
	@Override
	public void onDisable(){
		this.logger.info(description.getName() + " is now disabled.");
	}
	
	@Override
	public void onEnable(){
		description = getDescription();
		server = getServer();
		plugin = this;
		if (!hasSpout) {
			hasSpout = server.getPluginManager().isPluginEnabled("Spout");
		}
		if (!folder && !getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		
		/*if (support.get(Support.MMO_AUTO_EXTRACT)) {
			extractFile("^config.yml$");*/
			extractFile(".*\\.(png|jpg|ogg|midi|wav|zip)$", true);
		//}
		
		playerListener = new ServerChatPlayerListener();
		keyboardListener = new SkillsKeyboardListener();
		screenListener = new SkillsGuiButtonEvents();
		blockXpListener = new BlockXpListener();
		combatXpListener = new CombatXpListener();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Type.PLAYER_CHAT, this.playerListener, Priority.Normal, this);
		pm.registerEvent(Type.PLAYER_JOIN, this.playerListener, Priority.Normal, this);
		pm.registerEvent(Type.CUSTOM_EVENT, this.keyboardListener, Priority.Normal, this);
		pm.registerEvent(Type.CUSTOM_EVENT, this.screenListener, Priority.Normal, this);
		pm.registerEvent(Type.BLOCK_BREAK, this.blockXpListener, Priority.Monitor, this);
		pm.registerEvent(Type.CUSTOM_EVENT, this.combatXpListener, Priority.Normal, this);
		
		mmoPlayerManager = new MMOPlayerManager();
		
		//PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(description.getName() + " version " + description.getVersion() + " is now enabled.");
		
	}
	
	/**
	 * Extract files from the plugin jar.
	 * @param regex a pattern of files to extract
	 * @return if any files were extracted
	 * mmoMinecraft code
	 */
	public boolean extractFile(String regex) {
		return extractFile(regex, false);
	}
	
	/**
	 * Extract files from the plugin jar and optionally cache them on the client.
	 * @param regex a pattern of files to extract
	 * @param cache if any files found should be added to the Spout cache
	 * @return if any files were extracted
	 * mmoMinecraft code
	 */
	public boolean extractFile(String regex, boolean cache) {
		boolean found = false;
		try {
			boolean folder = false;
			JarFile jar = new JarFile(getFile());
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry jarentry = (JarEntry) entries.nextElement();
				String name = jarentry.getName();
				if (name.matches(regex)) {
					//this.logger.info(name);
					if (!folder) {
						File resources = new File(getDataFolder(), "resources");
						if( !resources.exists() )
							new File(getDataFolder(), "resources").mkdir();
						folder = true;
					}
					if (folder && name.equals("config.yml")) {
						name = description.getName() + ".yml";
					}
					try {
						File file = new File(getDataFolder(), "resources" + File.separator + name);
						if (!file.exists()) {
							InputStream is = jar.getInputStream(jarentry);
							FileOutputStream fos = new FileOutputStream(file);
							while (is.available() > 0) {
								fos.write(is.read());
							}
							fos.close();
							is.close();
							found = true;
						}
						if (hasSpout && cache && name.matches(regex)) {
							SpoutManager.getFileManager().addToCache(plugin, file);
						}
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
		}
		return found;
	}
}