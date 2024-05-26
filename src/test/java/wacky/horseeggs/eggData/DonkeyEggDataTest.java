/**
 * Egg data of donkey.
 */

package wacky.horseeggs.eggData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Objects;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.AbstractHorseInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * {@link DonkeyEggData} クラスを検証するテストクラスです.
 */
public class DonkeyEggDataTest {
  @Mock
  Donkey donkey;

  @Mock
  AbstractHorse absHorse;

  @Mock
  AbstractHorseInventory absHorseInv;

  @Mock
  ChestedHorse chestedHorse;

  /**
   * 事前準備.
   *
   * @throws java.lang.Exception すべての例外
   */
  @BeforeEach
  public void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  private void setUpDonkey() {
    absHorse = donkey;
    when(absHorse.getInventory()).thenReturn(absHorseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.DONKEY);
    when(absHorse.getType()).thenReturn(EntityType.DONKEY);
    when(chestedHorse.isCarryingChest()).thenReturn(false);

  }

  /**
   * {@link DonkeyEggData#getEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityType() {
    this.setUpDonkey();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.DONKEY, absHorse);
    assertTrue(Objects.nonNull(eggData.getEntityType()));
  }

  /**
   * {@link DonkeyEggData#getFilledEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggEntityType() {
    this.setUpDonkey();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.DONKEY, absHorse);
    assertTrue(Objects.nonNull(eggData.getFilledEggEntityType()));
  }

  /**
   * {@link DonkeyEggData#getFilledEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggMaterial() {
    this.setUpDonkey();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.DONKEY, absHorse);
    assertTrue(Objects.nonNull(eggData.getFilledEggMaterial()));
  }

  /**
   * {@link DonkeyEggData#DonkeyEggData()} のためのテスト・メソッド.
   */
  @Test
  public final void testDonkeyEggData() {
    this.setUpDonkey();

    EggDataBase eggData = new DonkeyEggData();
    assertTrue(Objects.nonNull(eggData));
  }
}
