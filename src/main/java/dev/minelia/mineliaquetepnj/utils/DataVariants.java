package dev.minelia.mineliaquetepnj.utils;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class DataVariants {

  public enum BlockCategory {
    ORES(Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.DIAMOND_ORE,
        Material.EMERALD_ORE),
    LOGS(Material.LOG, Material.LOG_2),
    PLANKS(Material.WOOD),
    STONE(Material.STONE, Material.COBBLESTONE, Material.MOSSY_COBBLESTONE),
    SAND(Material.SAND, Material.SANDSTONE, Material.RED_SANDSTONE),
    DIRT(Material.DIRT, Material.GRASS, Material.GRAVEL, Material.SOUL_SAND, Material.MYCEL);

    private final List<Material> materials;

    BlockCategory(Material... materials) {
      this.materials = Arrays.asList(materials);
    }

    public List<Material> getMaterials() {
      return materials;
    }
  }

  public enum VariantBlocks {

    //STONES
    STONE(Material.STONE, 0),
    GRANITE(Material.STONE, 1),
    POLISHED_GRANITE(Material.STONE, 2),
    DIORITE(Material.STONE, 3),
    POLISHED_DIORITE(Material.STONE, 4),
    ANDESITE(Material.STONE, 5),
    POLISHED_ANDESITE(Material.STONE, 6),
    STONE_BRICKS(Material.SMOOTH_BRICK, 0),
    MOSSY_STONE_BRICKS(Material.SMOOTH_BRICK, 1),
    CRACKED_STONE_BRICKS(Material.SMOOTH_BRICK, 2),
    CHISELED_STONE_BRICKS(Material.SMOOTH_BRICK, 3),
    QUARTZ_BLOCK(Material.QUARTZ_BLOCK, 0),
    CHISELED_QUARTZ_BLOCK(Material.QUARTZ_BLOCK, 1),
    PILAR_QUARTZ_BLOCK(Material.QUARTZ_BLOCK, 2, 3, 4),
    PRISMARINE(Material.PRISMARINE, 0),
    PRISMARINE_BRICKS(Material.PRISMARINE, 1),
    DARK_PRISMARINE(Material.PRISMARINE, 2),

    //GRASS
    DIRT(Material.DIRT, 0),
    COARSE_DIRT(Material.DIRT, 1),
    PODZOL(Material.DIRT, 2),

    //SANDS
    SAND(Material.SAND, 0),
    RED_SAND(Material.SAND, 1),
    SANDSTONE(Material.SANDSTONE, 0),
    CHISELED_SANDSTONE(Material.SANDSTONE, 1),
    SMOOTH_SANDSTONE(Material.SANDSTONE, 2),
    RED_SANDSTONE(Material.RED_SANDSTONE, 0),
    CHISELED_RED_SANDSTONE(Material.RED_SANDSTONE, 1),
    SMOOTH_RED_SANDSTONE(Material.RED_SANDSTONE, 2),

    //PLANKS
    OAK_PLANK(Material.WOOD, 0),
    SPRUCE_PLANK(Material.WOOD, 1),
    BIRCH_PLANK(Material.WOOD, 2),
    JUNGLE_PLANK(Material.WOOD, 3),
    ACACIA_PLANK(Material.WOOD, 4),
    DARK_OAK_PLANK(Material.WOOD, 5),

    //LOGS
    OAK_LOG(Material.LOG, 0, 4, 8),
    SPRUCE_LOG(Material.LOG, 1, 5, 9),
    BIRCH_LOG(Material.LOG, 2, 6, 10),
    JUNGLE_LOG(Material.LOG, 3, 7, 11),
    ACACIA_LOG(Material.LOG_2, 0, 4, 8),
    DARK_OAK_LOG(Material.LOG_2, 1, 7, 9),

    //STAIRS
    COBBLESTONE_STAIRS(Material.COBBLESTONE_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    BRICK_STAIRS(Material.BRICK_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    STONE_BRICK_STAIRS(Material.SMOOTH_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    NETHER_BRICK_STAIRS(Material.NETHER_BRICK_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    OAK_STAIRS(Material.WOOD_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    SPRUCE_STAIRS(Material.SPRUCE_WOOD_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    BIRCH_STAIRS(Material.BIRCH_WOOD_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    JUNGLE_STAIRS(Material.JUNGLE_WOOD_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    DARK_OAK_STAIRS(Material.DARK_OAK_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    SANDSTONE_STAIRS(Material.SANDSTONE_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    RED_SANDSTONE_STAIRS(Material.RED_SANDSTONE_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),
    QUARTZ_STAIRS(Material.QUARTZ_STAIRS, 0, 1, 2, 3, 4, 5, 6, 7),

    //GLASS
    WHITE_GLASS(Material.STAINED_GLASS, 0),
    ORANGE_GLASS(Material.STAINED_GLASS, 1),
    MAGENTA_GLASS(Material.STAINED_GLASS, 2),
    LIGHT_BLUE_GLASS(Material.STAINED_GLASS, 3),
    YELLOW_GLASS(Material.STAINED_GLASS, 4),
    LIME_GLASS(Material.STAINED_GLASS, 5),
    PINK_GLASS(Material.STAINED_GLASS, 6),
    GRAY_GLASS(Material.STAINED_GLASS, 7),
    LIGHT_GRAY_GLASS(Material.STAINED_GLASS, 8),
    CYAN_GLASS(Material.STAINED_GLASS, 9),
    PURPLE_GLASS(Material.STAINED_GLASS, 10),
    BLUE_GLASS(Material.STAINED_GLASS, 11),
    BROWN_GLASS(Material.STAINED_GLASS, 12),
    GREEN_GLASS(Material.STAINED_GLASS, 13),
    RED_GLASS(Material.STAINED_GLASS, 14),
    BLACK_GLASS(Material.STAINED_GLASS, 15),

    //WOOLS
    WHITE_WOOL(Material.WOOL, 0),
    ORANGE_WOOL(Material.WOOL, 1),
    MAGENTA_WOOL(Material.WOOL, 2),
    LIGHT_BLUE_WOOL(Material.WOOL, 3),
    YELLOW_WOOL(Material.WOOL, 4),
    LIME_WOOL(Material.WOOL, 5),
    PINK_WOOL(Material.WOOL, 6),
    GRAY_WOOL(Material.WOOL, 7),
    LIGHT_GRAY_WOOL(Material.WOOL, 8),
    CYAN_WOOL(Material.WOOL, 9),
    PURLE_WOOL(Material.WOOL, 10),
    BLUE_WOOL(Material.WOOL, 11),
    BROWN_WOOL(Material.WOOL, 12),
    GREEN_WOOL(Material.WOOL, 13),
    RED_WOOL(Material.WOOL, 14),
    BLACK_WOOL(Material.WOOL, 15),

    //SLABS
    STONE_SLAB(Material.STEP, 0, 8),
    SANDSTONE_SLAB(Material.STEP, 1, 9),
    COBBLESTONE_SLAB(Material.STEP, 3, 11),
    BRICKS_SLAB(Material.STEP, 4, 12),
    STONE_BRICKS_SLABS(Material.STEP, 5, 13),
    NETHER_BRICK_SLAB(Material.STEP, 6, 14),
    QUARTZ_SLAB(Material.STEP, 7, 15),
    OAK_SLAB(Material.WOOD_STEP, 0, 8),
    SPRUCE_SLAB(Material.WOOD_STEP, 1, 9),
    BIRCH_SLAB(Material.WOOD_STEP, 2, 10),
    JUNGLE_SLAB(Material.WOOD_STEP, 3, 11),
    ACACIA_SLAB(Material.WOOD_STEP, 4, 12),
    DARK_OAK_SLAB(Material.WOOD_STEP, 5, 13),
    RED_SANDTONE_SLAB(Material.STONE_SLAB2, 0),

    //CLAYS
    WHITE_CLAY(Material.STAINED_CLAY, 0),
    ORANGE_CLAY(Material.STAINED_CLAY, 1),
    MAGENTA_CLAY(Material.STAINED_CLAY, 2),
    LIGHT_BLUE_CLAY(Material.STAINED_CLAY, 3),
    YELLOW_CLAY(Material.STAINED_CLAY, 4),
    LIME_CLAY(Material.STAINED_CLAY, 5),
    PINK_CLAY(Material.STAINED_CLAY, 6),
    GRAY_CLAY(Material.STAINED_CLAY, 7),
    LIGHT_GRAY_CLAY(Material.STAINED_CLAY, 8),
    CYAN_CLAY(Material.STAINED_CLAY, 9),
    PURLE_CLAY(Material.STAINED_CLAY, 10),
    BLUE_CLAY(Material.STAINED_CLAY, 11),
    BROWN_CLAY(Material.STAINED_CLAY, 12),
    GREEN_CLAY(Material.STAINED_CLAY, 13),
    RED_CLAY(Material.STAINED_CLAY, 14),
    BLACK_CLAY(Material.STAINED_CLAY, 15),

    //FENCES
    COBBLESTONE_FENCE(Material.COBBLE_WALL, 0),
    MOSSY_COBBLESTONE_FENCE(Material.COBBLE_WALL, 1),
    OAK_FENCE(Material.FENCE, 0),
    SPRUCE_FENCE(Material.SPRUCE_FENCE, 0),
    BIRCH_FENCE(Material.BIRCH_FENCE, 0),
    JUNGLE_FENCE(Material.JUNGLE_FENCE, 0),
    ACACIA_FENCE(Material.ACACIA_FENCE, 0),
    DARK_OAK_FENCE(Material.DARK_OAK_FENCE, 0),

    //LEAVES
    OAK_LEAVE(Material.LEAVES, 0),
    SPRUCE_LEAVE(Material.LEAVES, 1),
    BIRCH_LEAVE(Material.LEAVES, 2),
    JUNGLE_LEAVE(Material.LEAVES, 3),
    ACACIA_LEAVE(Material.LEAVES_2, 0),
    DARK_OAK_LEAVE(Material.LEAVES_2, 1),

    //OTHER
    SPONGE(Material.SPONGE, 0),
    WET_SPONGE(Material.SPONGE, 1),
    PUMPKIN(Material.PUMPKIN, 0, 1, 2, 3),
    JACK_LANTERN(Material.JACK_O_LANTERN, 0, 1, 2, 3),
    HAY_BALE(Material.HAY_BLOCK, 0, 4, 8);

    private final Material material;
    private final List<Integer> data;

    VariantBlocks(Material material, Integer... data) {
      this.material = material;
      this.data = Arrays.asList(data);
    }

    public Material getMaterial() {
      return material;
    }

    public List<Integer> getData() {
      return data;
    }

    public static VariantBlocks getVariant(Block block) {
      Material material = block.getType();
      int id = getBlockData(block);
      for (VariantBlocks current : VariantBlocks.values()) {
        if (current.getMaterial().equals(material) && current.data.contains(id)) {
          return current;
        }
      }
      throw new IllegalArgumentException("Block is not a variant block : " + material + " | " + id);
    }

    public static VariantBlocks getVariant(Material material, int data) {
      for (VariantBlocks current : VariantBlocks.values()) {
        if (current.getMaterial().equals(material) && current.data.contains(data)) {
          return current;
        }
      }
      throw new IllegalArgumentException("Block is not a variant block : " + material + " | " + data);
    }

    public static boolean contains(Block block) {
      Material material = block.getType();
      int data = getBlockData(block);
      for (VariantBlocks current : VariantBlocks.values()) {
        if (current.getMaterial().equals(material) && current.getData().contains(data)) {
          return true;
        }
      }
      return false;
    }

    public static boolean contains(Material material, int data) {
      for (VariantBlocks current : VariantBlocks.values()) {
        if (current.getMaterial().equals(material) && current.getData().contains(data)) {
          return true;
        }
      }
      return false;
    }

    public static boolean contains(String material) {
      for (VariantBlocks current : VariantBlocks.values()) {
        if (current.getMaterial().name().equalsIgnoreCase(material)) {
          return true;
        }
      }
      return false;
    }

    private static int getBlockData(Block block) {
      return block.getState().getData().toItemStack().getDurability();
    }
  }

  public enum Liquids {
    WATER(Material.WATER, Material.STATIONARY_WATER),
    LAVA(Material.LAVA, Material.STATIONARY_LAVA);

    private final List<Material> materials;

    Liquids(Material... materials) {
      this.materials = Arrays.asList(materials);
    }

    public List<Material> getMaterials() {
      return materials;
    }

    public static boolean contains(Material material) {
      for (Liquids current : values()) {
        for (Material mat : current.getMaterials()) {
          if (mat.equals(material)) {
            return true;
          }
        }
      }
      return false;
    }

    public static Liquids getLiquid(Material material) {
      for (Liquids current : values()) {
        for (Material mat : current.getMaterials()) {
          if (mat.equals(material)) {
            return current;
          }
        }
      }
      throw new IllegalArgumentException("This material is not a liquid");
    }
  }

  public enum MobHostility {
    PASSIVE(Mobs.BAT, Mobs.CHICKEN, Mobs.COW, Mobs.MUSHROOM_COW, Mobs.OCELOT, Mobs.PIG, Mobs.RABBIT, Mobs.SHEEP, Mobs.SQUID, Mobs.VILLAGER,
        Mobs.WOLF),
    HOSTILE(Mobs.BLAZE, Mobs.CAVE_SPIDER, Mobs.CREEPER, Mobs.ENDERMAN, Mobs.ENDERMITE, Mobs.GHAST, Mobs.GIANT, Mobs.GUARDIAN, Mobs.MAGMA_CUBE,
        Mobs.SILVERFISH, Mobs.SKELETON, Mobs.SLIME, Mobs.SPIDER, Mobs.WITCH, Mobs.WITHER, Mobs.ZOMBIE),
    NEUTRAL(Mobs.HORSE, Mobs.IRON_GOLEM, Mobs.PIG_ZOMBIE, Mobs.SNOWMAN);

    private final Set<Mobs> mobs;

    MobHostility(Mobs... mobs) {
      this.mobs = Sets.newHashSet(mobs);
    }

    public Set<Mobs> getMobs() {
      return mobs;
    }

    public static MobHostility getMobHostility(Mobs mob) {
      for (MobHostility ia : values()) {
        if (ia.getMobs().contains(mob)) {
          return ia;
        }
      }
      return null;
    }

    public static boolean contains(Mobs mob) {
      for (MobHostility ia : MobHostility.values()) {
        if (ia.mobs.contains(mob)) {
          return true;
        }
      }
      return false;
    }
  }

  public enum MobType {
    ANIMAL(Mobs.BAT, Mobs.CHICKEN, Mobs.COW, Mobs.MUSHROOM_COW, Mobs.OCELOT, Mobs.PIG, Mobs.RABBIT, Mobs.SHEEP, Mobs.SQUID, Mobs.VILLAGER, Mobs.WOLF),
    MONSTER(Mobs.BLAZE, Mobs.CAVE_SPIDER, Mobs.CREEPER, Mobs.ENDERMAN, Mobs.ENDERMITE, Mobs.GHAST, Mobs.GIANT, Mobs.GUARDIAN, Mobs.MAGMA_CUBE,
        Mobs.SILVERFISH, Mobs.SKELETON, Mobs.SLIME, Mobs.SPIDER, Mobs.WITCH, Mobs.WITHER, Mobs.ZOMBIE),
    PLAYER(Mobs.ANGEL, Mobs.DEMON);

    private final Set<Mobs> mobs;

    MobType(Mobs... mobs) {
      this.mobs = Sets.newHashSet(mobs);
    }

    public Set<Mobs> getMobs() {
      return mobs;
    }

    public static MobType getMobType(Mobs mob) {
      for (MobType type : values()) {
        if (type.getMobs().contains(mob)) {
          return type;
        }
      }
      return null;
    }

    public static MobType getMobType(String mob) {
      for (MobType type : values()) {
        for (Mobs current : type.getMobs()) {
          if (current.name().equalsIgnoreCase(mob)) {
            return type;
          }
        }
      }
      return null;
    }

    public static boolean contains(Mobs mob) {
      for (MobType mobType : MobType.values()) {
        if (mobType.getMobs().contains(mob)) {
          return true;
        }
      }
      return false;
    }

    public static boolean contains(String mob) {
      for (MobType current : MobType.values()) {
        if (current.name().equalsIgnoreCase(mob)) {
          return true;
        }
      }
      return false;
    }
  }

  public enum Mobs {
    BAT,
    BLAZE,
    CAVE_SPIDER,
    CHICKEN,
    COW,
    CREEPER,
    ENDER_DRAGON,
    ENDERMAN,
    ENDERMITE,
    GHAST,
    GIANT,
    GUARDIAN,
    HORSE,
    IRON_GOLEM,
    MAGMA_CUBE,
    MUSHROOM_COW,
    OCELOT,
    PIG,
    PIG_ZOMBIE,
    RABBIT,
    SHEEP,
    SILVERFISH,
    SKELETON,
    SLIME,
    SNOWMAN,
    SPIDER,
    SQUID,
    VILLAGER,
    WITCH,
    WITHER,
    WOLF,
    ZOMBIE,
    ANGEL,
    DEMON,
    UNKNOWN;

    public static boolean contains(String name) {
      for (Mobs mob : values()) {
        if (mob.name().equalsIgnoreCase(name)) {
          return true;
        }
      }
      return false;
    }

    public static Mobs getByName(String name) {
      for (Mobs mob : values()) {
        if (mob.name().equalsIgnoreCase(name)) {
          return mob;
        }
      }
      return UNKNOWN;
    }
  }
}