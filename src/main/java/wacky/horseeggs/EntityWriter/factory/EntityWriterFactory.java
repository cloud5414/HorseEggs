/**
 * Factory for EntityWriter.
 */

package wacky.horseeggs.EntityWriter.factory;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import wacky.horseeggs.EntityWriter.DonkyEntityWriter;
import wacky.horseeggs.EntityWriter.EntityWriter;
import wacky.horseeggs.EntityWriter.HorseEntityWriter;
import wacky.horseeggs.EntityWriter.LlamaEntityWriter;
import wacky.horseeggs.EntityWriter.MuleEntityWriter;
import wacky.horseeggs.EntityWriter.SkeltonHorseEntityWriter;
import wacky.horseeggs.EntityWriter.TraderLlamaEntityWriter;
import wacky.horseeggs.EntityWriter.ZombieHorseEntityWriter;

/**
 * Factory class of EntityWriter.
 */
public class EntityWriterFactory {
  /**
   * Factory method for EntityWriter.
   *
   * @param entityType {@link EntityType}
   */
  public static EntityWriter newEntityWriter(EntityType entityType, AbstractHorse horse) {
    switch (entityType) {
      case LLAMA:
        return new LlamaEntityWriter(horse);
      case MULE:
        return new MuleEntityWriter(horse);
      case DONKEY:
        return new DonkyEntityWriter(horse);
      case HORSE:
        return new HorseEntityWriter(horse);
      case ZOMBIE_HORSE:
        return new ZombieHorseEntityWriter(horse);
      case SKELETON_HORSE:
        return new SkeltonHorseEntityWriter(horse);
      case TRADER_LLAMA:
        return new TraderLlamaEntityWriter(horse);
      default:
        return null;
    }
  }
}
