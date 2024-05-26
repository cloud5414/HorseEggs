/**
 * Entity writer for zombie horse data.
 */

package wacky.horseeggs.EntityWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.ZombieHorse;
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
 * {@link ZombieHorseEntityWriter} クラスを検証するテストクラスです.
 */
class ZombieHorseEntityWriterTest {

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

  // ゾンビホース卵テストデータ
  static final Map<String, Object> zombieHorseEggDataMap = new HashMap<>() {
    {
      put(dataKeyChest, false);
      put(dataKeySpeed, 1d);
      put(dataKeyHealth, 30d);
      put(dataKeyUuidLeast, -6995725480010122770L);
      put(dataKeyColor, Horse.Color.CHESTNUT.name());
      put(dataKeyJump, 1d);
      put(dataKeyMaxHealth, 30d);
      put(dataKeyName, "馬刺し");
      put(dataKeySaddle, true);
      put(dataKeyVariant, Horse.Variant.UNDEAD_HORSE.name());
      put(dataKeyType, EntityType.ZOMBIE_HORSE.name());
      put(dataKeyUuidMost, 2968001111801612278L);
      put(dataKeyArmor, Material.DIAMOND_HORSE_ARMOR.name());
      put(dataKeyStyle, Horse.Style.WHITEFIELD.name());
      put(dataKeyStrength, null);
      put(dataKeyOwner, "TestOwner1");
    }
  };


  @Mock
  EggDataBase eggData;

  @Mock
  Horse horse;

  @Mock
  ZombieHorse zombieHorse;

  @Mock
  SkeletonHorse skeletonHorse;

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
    setUpZombieHorse();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  private void setUpZombieHorse() {
    absHorse = zombieHorse;
    // when(horse.getColor()).thenReturn(Horse.Color.BLACK);
    // when(horse.getStyle()).thenReturn(Horse.Style.NONE);

    when(animalTamer.getName()).thenReturn("腐の飼い主");
    final UUID uuid = new UUID(2968001111801612278L, -6995725480010122770L);
    when(animalTamer.getUniqueId()).thenReturn(uuid);

    when(absHorse.getInventory()).thenReturn(absHorseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.SKELETON_HORSE);
    when(absHorse.getType()).thenReturn(EntityType.SKELETON_HORSE);
    when(absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH)).thenReturn(attr);
    when(absHorse.getCustomName()).thenReturn("まずいホネ");
    when(absHorse.isTamed()).thenReturn(true);
    when(absHorse.getOwner()).thenReturn(animalTamer);

    when(eggData.getName()).thenReturn((String) zombieHorseEggDataMap.get(dataKeyName));
    when(eggData.getMaxHealth()).thenReturn((Double) zombieHorseEggDataMap.get(dataKeyMaxHealth));
    when(eggData.getHealth()).thenReturn((Double) zombieHorseEggDataMap.get(dataKeyHealth));
    when(eggData.getSpeed()).thenReturn((Double) zombieHorseEggDataMap.get(dataKeySpeed));
    when(eggData.getUuidMost()).thenReturn((Long) zombieHorseEggDataMap.get(dataKeyUuidMost));
    when(eggData.getUuidLeast()).thenReturn((Long) zombieHorseEggDataMap.get(dataKeyUuidLeast));
    when(eggData.getIsSaddled()).thenReturn((Boolean) zombieHorseEggDataMap.get(dataKeySaddle));
    when(eggData.getIsCarryingChest())
        .thenReturn((Boolean) zombieHorseEggDataMap.get(dataKeyChest));
    when(eggData.getEntityType()).thenReturn(EntityType.ZOMBIE_HORSE);
  }

  /**
   * {@link ZombieHorseEntityWriter#writeHorse(EggDataBase)} のためのテスト・メソッド.
   */
  @Test
  final void testWriteHorse() {
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      Long uuidMost = Long.valueOf(zombieHorseEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(zombieHorseEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();

      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);
      when(absHorse.getInventory()).thenReturn(absHorseInv);

      EntityWriter zombieHorseEw =
          EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse);
      assertTrue(zombieHorseEw.writeHorseBase(eggData));

      AbstractHorse zombieAbsHorse = zombieHorseEw.getAbsHorse();
      assertTrue(Objects.nonNull(zombieAbsHorse));

      boolean isWriteSucccess = zombieHorseEw.writeHorse(eggData);
      assertTrue(isWriteSucccess);
    }
  }
}
