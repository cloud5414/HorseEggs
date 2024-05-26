/**
 * Entity writer for trader llama data.
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
import org.bukkit.entity.TraderLlama;
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
 * {@link TraderLlamaEntityWriter} クラスを検証するテストクラスです.
 */
class TraderLlamaEntityWriterTest {

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

  // 商人のラマ卵テストデータ
  static final Map<String, Object> traderLlamaEggDataMap = new HashMap<>() {
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
  TraderLlama traderLlama;

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
    setUpTraderLlama();
  }

  private void setUpTraderLlama() {
    absHorse = traderLlama;
    when(llama.getColor()).thenReturn(Llama.Color.WHITE);

    when(absHorse.getInventory()).thenReturn(llamaInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.LLAMA);
    when(absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH)).thenReturn(attr);
    when(absHorse.getType()).thenReturn(EntityType.TRADER_LLAMA);

    when(eggData.getName()).thenReturn((String) traderLlamaEggDataMap.get(dataKeyName));
    when(eggData.getMaxHealth()).thenReturn((Double) traderLlamaEggDataMap.get(dataKeyMaxHealth));
    when(eggData.getHealth()).thenReturn((Double) traderLlamaEggDataMap.get(dataKeyHealth));
    when(eggData.getSpeed()).thenReturn((Double) traderLlamaEggDataMap.get(dataKeySpeed));
    when(eggData.getUuidMost()).thenReturn((Long) traderLlamaEggDataMap.get(dataKeyUuidMost));
    when(eggData.getUuidLeast()).thenReturn((Long) traderLlamaEggDataMap.get(dataKeyUuidLeast));
    when(eggData.getIsSaddled()).thenReturn((Boolean) traderLlamaEggDataMap.get(dataKeySaddle));
    when(eggData.getIsCarryingChest())
        .thenReturn((Boolean) traderLlamaEggDataMap.get(dataKeyChest));
    when(eggData.getEntityType()).thenReturn(EntityType.TRADER_LLAMA);
    when(eggData.getColor()).thenReturn(Llama.Color.WHITE.name());
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * {@link TraderLlamaEntityWriter#writeHorse(EggDataBase)} のためのテスト・メソッド.
   */
  @Test
  final void testWriteHorse() {
    // 商人のラマ
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      Long uuidMost = Long.valueOf(traderLlamaEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(traderLlamaEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();

      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);

      EntityWriter traderLlamaEw =
          EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse);
      assertTrue(traderLlamaEw.writeHorseBase(eggData));

      AbstractHorse traderLlamaAbsHorse = traderLlamaEw.getAbsHorse();
      assertTrue(Objects.nonNull(traderLlamaAbsHorse));

      boolean isWriteSuccess = traderLlamaEw.writeHorse(eggData);
      assertTrue(isWriteSuccess);

      when(eggData.getArmor()).thenReturn((String) traderLlamaEggDataMap.get(dataKeyArmor));
      isWriteSuccess = traderLlamaEw.writeHorse(eggData);
      assertTrue(isWriteSuccess);
    }
  }

}
