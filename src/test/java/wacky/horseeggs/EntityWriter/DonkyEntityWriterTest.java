/**
 * Entity writer for donkey data.
 */

package wacky.horseeggs.EntityWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.LlamaInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.EntityWriter.factory.EntityWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * {@link DonkyEntityWriter} クラスを検証するテストクラスです.
 */
class DonkyEntityWriterTest {

  // ロバ卵テストデータ
  static final Map<String, Object> donkeyEggDataMap = new HashMap<>() {
    {
      put(dataKeyChest, true);
      put(dataKeySpeed, 0.17499999701976776d);
      put(dataKeyHealth, 23d);
      put(dataKeyUuidLeast, -6995725480010122770L);
      put(dataKeyJump, 0.5d);
      put(dataKeyMaxHealth, 23d);
      put(dataKeySaddle, true);
      put(dataKeyVariant, Horse.Variant.DONKEY.name());
      put(dataKeyType, EntityType.DONKEY.name());
      put(dataKeyUuidMost, 2968001111801612278L);
      put(dataKeyStrength, null);
      put(dataKeyOwner, null);
    }
  };

  static final String dataKeyChest = "Chest";
  static final String dataKeySpeed = "Speed";
  static final String dataKeyHealth = "Health";
  static final String dataKeyUuidLeast = "UUIDLeast";
  static final String dataKeyUuidMost = "UUIDMost";
  static final String dataKeyColor = "Color";
  static final String dataKeyJump = "Jump";
  static final String dataKeyMaxHealth = "MaxHealth";
  static final String dataKeyName = "Name";
  static final String dataKeyType = "Type";
  static final String dataKeyVariant = "Variant";
  static final String dataKeyArmor = "Armor";
  static final String dataKeyStyle = "Style";
  static final String dataKeySaddle = "Saddle";
  static final String dataKeyStrength = "Strength";
  static final String dataKeyOwner = "Owner";

  @Mock
  EggDataBase eggData;

  @Mock
  Horse horse;

  @Mock
  Donkey donkey;

  @Mock
  Llama llama;

  @Mock
  AbstractHorse absHorse;

  @Mock
  AbstractHorseInventory absHorseInv;

  @Mock
  HorseInventory horseInv;

  @Mock
  LlamaInventory llamaInv;

  @Mock
  AttributeInstance attr;

  @Mock
  AnimalTamer animalTamer;

  @Mock
  ChestedHorse chestedHorse;

  @Mock
  ItemMeta itemMeta;

  @Mock
  Server server;

  @Mock
  ItemFactory itemFactory;

  @Mock
  OfflinePlayer offlinePlayer;

  /**
   * 事前準備.
   *
   * @throws java.lang.Exception すべての例外
   */
  @BeforeEach
  void setUp() throws Exception {
    openMocks();
    setUpDonkey();
  }

  private void setUpDonkey() {
    absHorse = donkey;
    when(absHorse.getInventory()).thenReturn(absHorseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.DONKEY);
    when(absHorse.getType()).thenReturn(EntityType.DONKEY);
    when(absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH)).thenReturn(attr);

    when(chestedHorse.isCarryingChest()).thenReturn(true);

    when(eggData.getName()).thenReturn((String) donkeyEggDataMap.get(dataKeyName));
    when(eggData.getMaxHealth()).thenReturn((Double) donkeyEggDataMap.get(dataKeyMaxHealth));
    when(eggData.getHealth()).thenReturn((Double) donkeyEggDataMap.get(dataKeyHealth));
    when(eggData.getSpeed()).thenReturn((Double) donkeyEggDataMap.get(dataKeySpeed));
    when(eggData.getUuidMost()).thenReturn((Long) donkeyEggDataMap.get(dataKeyUuidMost));
    when(eggData.getUuidLeast()).thenReturn((Long) donkeyEggDataMap.get(dataKeyUuidLeast));
    when(eggData.getIsSaddled()).thenReturn((Boolean) donkeyEggDataMap.get(dataKeySaddle));
    when(eggData.getIsCarryingChest()).thenReturn((Boolean) donkeyEggDataMap.get(dataKeyChest));
    when(eggData.getEntityType()).thenReturn(EntityType.DONKEY);
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * {@link DonkyEntityWriter#writeHorse(EggDataBase)} のためのテスト・メソッド.
   */
  @Test
  final void testWriteHorse() {
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      Long uuidMost = Long.valueOf(donkeyEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(donkeyEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();

      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);
      when(absHorse.getInventory()).thenReturn(absHorseInv);

      EntityWriter donkeyEw =
          EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse);
      assertTrue(donkeyEw.writeHorseBase(eggData));

      AbstractHorse donkeyAbsHorse = donkeyEw.getAbsHorse();
      assertTrue(Objects.nonNull(donkeyAbsHorse));

      boolean isWriteSucccess = donkeyEw.writeHorse(eggData);
      assertTrue(isWriteSucccess);
    }
  }
}
