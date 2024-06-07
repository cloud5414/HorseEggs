/**
 * Factory for entity writer.
 */

package wacky.horseeggs.EntityWriter.factory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import wacky.horseeggs.EntityWriter.EntityWriter;

/**
 * {@link EntityWriterFactory} クラスを検証するテストクラスです.
 */
public class EntityWriterFactoryTest {
  @Mock
  AbstractHorse absHorse;

  /**
   * 事前設定.
   *
   * @throws java.lang.Exception すべての例外
   */
  @BeforeEach
  public void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
//    MockitoAnnotations.openMocks(this);
  }

  /**
   * {@link EntityWriterFactory#newEntityWriter(EntityType, AbstractHorse)} のためのテスト・メソッド.
   */
  @Test
  public final void testNewEntityWriter() {
    EntityWriter llamaEw = EntityWriterFactory.newEntityWriter(EntityType.LLAMA, absHorse);
    assertTrue(Objects.nonNull(llamaEw));

    EntityWriter muleEw = EntityWriterFactory.newEntityWriter(EntityType.MULE, absHorse);
    assertTrue(Objects.nonNull(muleEw));

    EntityWriter donkeyEw = EntityWriterFactory.newEntityWriter(EntityType.DONKEY, absHorse);
    assertTrue(Objects.nonNull(donkeyEw));

    EntityWriter horseEw = EntityWriterFactory.newEntityWriter(EntityType.HORSE, absHorse);
    assertTrue(Objects.nonNull(horseEw));

    EntityWriter zombieHorseEw =
        EntityWriterFactory.newEntityWriter(EntityType.ZOMBIE_HORSE, absHorse);
    assertTrue(Objects.nonNull(zombieHorseEw));

    EntityWriter skeltonHorseEw =
        EntityWriterFactory.newEntityWriter(EntityType.SKELETON_HORSE, absHorse);
    assertTrue(Objects.nonNull(skeltonHorseEw));

    EntityWriter traderLlamaEw =
        EntityWriterFactory.newEntityWriter(EntityType.TRADER_LLAMA, absHorse);
    assertTrue(Objects.nonNull(traderLlamaEw));

    EntityWriter otherEw = EntityWriterFactory.newEntityWriter(EntityType.BAT, absHorse);
    assertTrue(Objects.isNull(otherEw));
  }

}
