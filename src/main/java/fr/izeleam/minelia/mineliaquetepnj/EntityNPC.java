package fr.izeleam.minelia.mineliaquetepnj;


import static org.bukkit.Bukkit.getServer;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EntityNPC {

  private static final EntityNPC npc = new EntityNPC();

  public static EntityNPC getNPC() {
    return npc;
  }

  private EntityNPC() {
  }

  public void spawn() {
    WorldServer world = ((CraftServer) getServer()).getServer().getWorldServer(0);
    EntityPlayer npc = new EntityPlayer(((CraftServer) getServer()).getServer(), world, new GameProfile(UUID.randomUUID(), "NPC")
        , new PlayerInteractManager(world));

    FileConfiguration config = MineliaQuetePNJ.getInstance().getConfig();

    npc.setCustomName(config.getString("npc.name"));
    npc.setCustomNameVisible(true);

    npc.setLocation(config.getDouble("npc.location.x"), config.getDouble("npc.location.y"), config.getDouble("npc.location.z")
        , (float) config.getDouble("npc.location.yaw"), (float) config.getDouble("npc.location.pitch"));

    world.addEntity(npc);
  }
}