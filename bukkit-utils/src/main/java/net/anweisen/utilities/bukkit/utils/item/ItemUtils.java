package net.anweisen.utilities.bukkit.utils.item;

import net.anweisen.utilities.bukkit.utils.misc.BukkitReflectionUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 */
public final class ItemUtils {

	private ItemUtils() {}

	@Nonnull
	public static Material convertFoodToCookedFood(@Nonnull Material material) {
		try {
			return Material.valueOf("COOKED_" + material.name());
		} catch (Exception ex) {
			return material; // No cooked material is available
		}
	}

	public static boolean isObtainableInSurvival(@Nonnull Material material) {
		String name = material.name();
		if (BukkitReflectionUtils.isAir(material)) return false;
		if (name.endsWith("_SPAWN_EGG")) return false;
		if (name.startsWith("INFESTED_")) return false;
		if (name.startsWith("LEGACY_")) return false; // Legacy items should not be obtainable
		switch (name) { // Use name instead of enum its self, to prevent NoSuchFieldErrors in older versions where this specific enum does not exist
			case "CHAIN_COMMAND_BLOCK":
			case "REPEATING_COMMAND_BLOCK":
			case "COMMAND_BLOCK":
			case "COMMAND_BLOCK_MINECART":
			case "JIGSAW":
			case "STRUCTURE_BLOCK":
			case "STRUCTURE_VOID":
			case "BARRIER":
			case "BEDROCK":
			case "KNOWLEDGE_BOOK":
			case "DEBUG_STICK":
			case "END_PORTAL_FRAME":
			case "END_PORTAL":
			case "NETHER_PORTAL":
			case "END_GATEWAY":
			case "LAVA":
			case "WATER":
			case "LARGE_FERN":
			case "TALL_GRASS":
			case "TALL_SEAGRASS":
			case "PATH_BLOCK":
			case "CHORUS_PLANT":
			case "PETRIFIED_OAK_SLAB":
			case "FARMLAND":
			case "PLAYER_HEAD":
			case "GLOBE_BANNER_PATTERN":
			case "SPAWNER":
			case "AMETHYST_CLUSTER":
			case "BUDDING_AMETHYST":
			case "POWDER_SNOW":
				return false;
		}

		return true;
	}

	public static void damageItem(@Nonnull ItemStack item) {
		damageItem(item, 1);
	}

	public static void damageItem(@Nonnull ItemStack item, int amount) {
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return;
		if (!(meta instanceof Damageable)) return;
		Damageable damageable = (Damageable) meta;
		damageable.setDamage(damageable.getDamage() + amount);
		item.setItemMeta(meta);
	}

}
