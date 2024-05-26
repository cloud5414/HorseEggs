/**
 * Entity writer for entity data.
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
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.LlamaInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.EntityWriter.factory.EntityWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * {@link EntityWriter} クラスを検証するテストクラスです.
 */
public class EntityWriterTest {

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
  
  // ウマ卵テストデータ
  static final Map<String, Object> horseEggDataMap = new HashMap<>() {
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
      put(dataKeyVariant, Horse.Variant.HORSE.name());
      put(dataKeyType, EntityType.HORSE.name());
      put(dataKeyUuidMost, 2968001111801612278L);
      put(dataKeyArmor, Material.DIAMOND_HORSE_ARMOR.name());
      put(dataKeyStyle, Horse.Style.WHITEFIELD.name());
      put(dataKeyStrength, null);
      put(dataKeyOwner, "TestOwner1");
    }
  };

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

  // ラマ卵テストデータ
  static final Map<String, Object> llamaEggDataMap = new HashMap<>() {
    {
      put(dataKeyChest, true);
      put(dataKeySpeed, 0.17499999701976776d);
      put(dataKeyHealth, 21d);
      put(dataKeyUuidLeast, -6995725480010122770L);
      put(dataKeyColor, Llama.Color.GRAY.name());
      put(dataKeyJump, 0.5d);
      put(dataKeyMaxHealth, 21d);
      put(dataKeySaddle, false);
      put(dataKeyVariant, Horse.Variant.LLAMA.name());
      put(dataKeyType, EntityType.LLAMA.name());
      put(dataKeyUuidMost, 2968001111801612278L);
      put(dataKeyArmor, Material.PURPLE_CARPET.name());
      put(dataKeyStrength, 3);
      put(dataKeyOwner, "TestOwner2");
    }
  };

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
   * @throws java.lang.Exception
   */
  @BeforeEach
  public void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  private void setUpHorse() {
    absHorse = horse;
    when(horse.getColor()).thenReturn(Horse.Color.BLACK);
    when(horse.getStyle()).thenReturn(Horse.Style.NONE);

    when(animalTamer.getName()).thenReturn("ウマの飼い主");
    final UUID uuid = new UUID(2968001111801612278L, -6995725480010122770L);
    when(animalTamer.getUniqueId()).thenReturn(uuid);

    when(absHorse.getInventory()).thenReturn(horseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.HORSE);
    when(absHorse.getType()).thenReturn(EntityType.HORSE);
    when(absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH)).thenReturn(attr);
    when(absHorse.getCustomName()).thenReturn("うまいウマ");
    when(absHorse.isTamed()).thenReturn(true);
    when(absHorse.getOwner()).thenReturn(animalTamer);

    when(eggData.getName()).thenReturn((String) horseEggDataMap.get(dataKeyName));
    when(eggData.getMaxHealth()).thenReturn((Double) horseEggDataMap.get(dataKeyMaxHealth));
    when(eggData.getHealth()).thenReturn((Double) horseEggDataMap.get(dataKeyHealth));
    when(eggData.getSpeed()).thenReturn((Double) horseEggDataMap.get(dataKeySpeed));
    when(eggData.getUuidMost()).thenReturn((Long) horseEggDataMap.get(dataKeyUuidMost));
    when(eggData.getUuidLeast()).thenReturn((Long) horseEggDataMap.get(dataKeyUuidLeast));
    when(eggData.getIsSaddled()).thenReturn((Boolean) horseEggDataMap.get(dataKeySaddle));
    when(eggData.getIsCarryingChest()).thenReturn((Boolean) horseEggDataMap.get(dataKeyChest));
    when(eggData.getEntityType()).thenReturn(EntityType.HORSE);

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
  
  private void setUpLlama() {
    absHorse = llama;
    when(llama.getColor()).thenReturn(Llama.Color.WHITE);

    when(absHorse.getInventory()).thenReturn(llamaInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.LLAMA);
    when(absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH)).thenReturn(attr);
    when(absHorse.getType()).thenReturn(EntityType.LLAMA);

    when(eggData.getName()).thenReturn((String) llamaEggDataMap.get(dataKeyName));
    when(eggData.getMaxHealth()).thenReturn((Double) llamaEggDataMap.get(dataKeyMaxHealth));
    when(eggData.getHealth()).thenReturn((Double) llamaEggDataMap.get(dataKeyHealth));
    when(eggData.getSpeed()).thenReturn((Double) llamaEggDataMap.get(dataKeySpeed));
    when(eggData.getUuidMost()).thenReturn((Long) llamaEggDataMap.get(dataKeyUuidMost));
    when(eggData.getUuidLeast()).thenReturn((Long) llamaEggDataMap.get(dataKeyUuidLeast));
    when(eggData.getIsSaddled()).thenReturn((Boolean) llamaEggDataMap.get(dataKeySaddle));
    when(eggData.getIsCarryingChest()).thenReturn((Boolean) llamaEggDataMap.get(dataKeyChest));
    when(eggData.getEntityType()).thenReturn(EntityType.LLAMA);

  }

  /**
   * {@link EntityWriter#EntityWriter(AbstractHorse)} のためのテスト・メソッド。
   */
  @Disabled("EntityWriterTest#testWriteHorseBase で検証")
  @Test
  public final void testEntityWriter() {
    fail("まだ実装されていません"); // TODO
  }

  /**
   * {@link EntityWriter#writeHorse(EggDataBase)} のためのテスト・メソッド。
   */
  @Disabled("継承先クラスで検証")
  @Test
  public final void testWriteHorse() {
//    fail("まだ実装されていません"); // TODO
    
  }

  /**
   * {@link EntityWriter#writeHorseBase(EggDataBase)} のためのテスト・メソッド。
   */
  @Test
  public final void testWriteHorseBase() {
    // ウマ
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      setUpHorse();
      Long uuidMost = Long.valueOf(horseEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(horseEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();

      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);
      when(absHorse.getInventory()).thenReturn(horseInv);

      EntityWriter horseEw = EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse);
      assertTrue(horseEw.writeHorseBase(eggData));
      // AbstractHorse horseAbsHorse = horseEw.getAbsHorse();
      // Assert.assertTrue(Objects.nonNull(horseAbsHorse));
    }

    // ロバ
    absHorse = mock(Donkey.class);
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      setUpDonkey();
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
    }

    // ラマ
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      setUpLlama();
      Long uuidMost = Long.valueOf(llamaEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(llamaEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();

      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);

      EntityWriter llamaEw = EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse);
      assertTrue(llamaEw.writeHorseBase(eggData));

      AbstractHorse llamaAbsHorse = llamaEw.getAbsHorse();
      assertTrue(Objects.nonNull(llamaAbsHorse));
    }
  }

  /**
   * {@link wacky.horseeggs.EntityWriter.EntityWriter#getAbsHorse()} のためのテスト・メソッド。
   */
  @Disabled("EntityWriterTest#testWriteHorseBase で検証")
  @Test
  public final void testGetAbsHorse() {
    fail("まだ実装されていません"); // TODO
  }
}
