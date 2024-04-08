package fr.izeleam.minelia.mineliaquetepnj;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.izeleam.minelia.mineliaquetepnj.menus.QuestMenu;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class NPC implements Listener {

  private static final NPC instance = new NPC();

  public static NPC getNPC() {
    return instance;
  }

  private final Location location;
  private final String name;
  private EntityPlayer entityPlayer;
  private final String texture;
  private final String signature;

  public NPC () {
    final FileConfiguration config = MineliaQuetePNJ.getInstance().getConfig();

    this.location = new Location(Bukkit.getWorld(config.getString("npc.location.world")), config.getDouble("npc.location.x") + 0.5, config.getDouble("npc.location.y"), config.getDouble("npc.location.z") + 0.5, config.getInt("npc.location.yaw"), config.getInt("npc.location.pitch"));
    this.name = config.getString("npc.name");
    this.texture = config.getString("npc.texture");
    this.signature = config.getString("npc.signature");
  }

  public void spawn() {
    MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
    WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();

    GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.translateAlternateColorCodes('&', this.name));
    gameProfile.getProperties().put("textures", new Property("textures", this.texture, this.signature));

    this.entityPlayer = new EntityPlayer(minecraftServer, worldServer, gameProfile, new PlayerInteractManager(worldServer));
    this.entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
  }

  public void show(Player player) {
    PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;

    playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, this.entityPlayer));
    playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(this.entityPlayer));
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.getPlayer().getLocation().distance(this.location) < 2) {
      QuestMenu.getMenu().open(event.getPlayer());
    }
  }
}