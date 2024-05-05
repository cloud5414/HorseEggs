/**
 * 
 */
package wacky.horseeggs.LoreWriter.factory;

import org.bukkit.entity.EntityType;
import wacky.horseeggs.LoreWriter.DonkeyLoreWriter;
import wacky.horseeggs.LoreWriter.HorseLoreWriter;
import wacky.horseeggs.LoreWriter.LlamaLoreWriter;
import wacky.horseeggs.LoreWriter.LoreWriter;
import wacky.horseeggs.LoreWriter.MuleLoreWriter;
import wacky.horseeggs.LoreWriter.SkeletonHorseLoreWriter;
import wacky.horseeggs.LoreWriter.TraderLlamaLoreWriter;
import wacky.horseeggs.LoreWriter.ZombieHorseLoreWriter;

/**
 * Factory class of LoreWriter.
 */
public class LoreWriterFactory {
  public LoreWriter newLoreWriter(EntityType entityType) {
    switch (entityType) {
      case LLAMA:
        return new LlamaLoreWriter();
      case MULE:
        return new MuleLoreWriter();
      case DONKEY:
        return new DonkeyLoreWriter();
      case HORSE:
        return new HorseLoreWriter();
      case ZOMBIE_HORSE:
        return new ZombieHorseLoreWriter();
      case SKELETON_HORSE:
        return new SkeletonHorseLoreWriter();
      case TRADER_LLAMA:
        return new TraderLlamaLoreWriter();
      default:
        return null;
    }
  }
}