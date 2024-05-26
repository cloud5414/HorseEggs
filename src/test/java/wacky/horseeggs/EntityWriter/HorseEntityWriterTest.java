/**
 * Entity writer for horse data.
 */

package wacky.horseeggs.EntityWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LlamaInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.EntityWriter.factory.EntityWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;
import wacky.horseeggs.eggData.HorseEggData;

/**
 * {@link HorseEntityWriter} クラスを検証するテストクラスです.
 */
class HorseEntityWriterTest {

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
  static final String dataKeyArmorColor = "";

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
      put(dataKeyArmor, Material.LEATHER_HORSE_ARMOR.name());
      put(dataKeyStyle, Horse.Style.WHITEFIELD.name());
      put(dataKeyStrength, null);
      put(dataKeyOwner, "TestOwner1");
      put(dataKeyArmorColor, "255,29,29,33");
    }
  };

  @Mock
  EggDataBase eggData;

  @Mock
  Horse horse;

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
  ItemStack itemStack;

  @Mock
  ItemMeta itemMeta;

  @Mock
  LeatherArmorMeta leatherArmorMeta;

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
    setUpHorse();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  private void setUpHorse() {
    absHorse = horse;
    when(horse.getColor()).thenReturn(Horse.Color.BLACK);
    when(horse.getStyle()).thenReturn(Horse.Style.NONE);
    when(horse.getInventory()).thenReturn(horseInv);

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

    when(eggData.getName()).thenReturn(horseEggDataMap.get(dataKeyName).toString());
    when(eggData.getMaxHealth())
        .thenReturn(Double.valueOf(horseEggDataMap.get(dataKeyMaxHealth).toString()));
    when(eggData.getHealth())
        .thenReturn(Double.valueOf(horseEggDataMap.get(dataKeyHealth).toString()));
    when(eggData.getSpeed())
        .thenReturn(Double.valueOf(horseEggDataMap.get(dataKeySpeed).toString()));
    when(eggData.getUuidMost())
        .thenReturn(Long.valueOf(horseEggDataMap.get(dataKeyUuidMost).toString()));
    when(eggData.getUuidLeast())
        .thenReturn(Long.valueOf(horseEggDataMap.get(dataKeyUuidLeast).toString()));
    when(eggData.getIsSaddled())
        .thenReturn(Boolean.valueOf(horseEggDataMap.get(dataKeySaddle).toString()));
    when(eggData.getIsCarryingChest())
        .thenReturn(Boolean.valueOf(horseEggDataMap.get(dataKeyChest).toString()));
    when(eggData.getColor()).thenReturn(horseEggDataMap.get(dataKeyColor).toString());
    when(eggData.getStyle()).thenReturn(horseEggDataMap.get(dataKeyStyle).toString());
    when(eggData.getArmor()).thenReturn(horseEggDataMap.get(dataKeyArmor).toString());
    when(eggData.getArmorColor()).thenReturn(horseEggDataMap.get(dataKeyArmorColor).toString());
    when(eggData.getEntityType()).thenReturn(EntityType.HORSE);
  }

  /**
   * {@link HorseEntityWriter#writeHorse(EggDataBase)} のためのテスト・メソッド.
   */
  @Test
  final void testWriteHorse() {
    /*
     * NOTE:
     * 実装されているテストコードでは、JUnit実行時に革ウマ鎧のItemMetaが
     * 常にnullになってしまいます。
     * 解決できるまでは、革ウマ鎧の色保存／再現はリモートサーバーを
     * デバッグ実行して、直接確認・検証を行ってください。
     */

    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      Long uuidMost = Long.valueOf(horseEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(horseEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();
      bukkit.when(() -> Bukkit.getItemFactory()).thenReturn(itemFactory);

      when(server.getItemFactory()).thenReturn(itemFactory);
      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);

      when(itemStack.getItemMeta()).thenReturn(leatherArmorMeta);

      /* TODO: HorseInventory から取得した革ウマ鎧のItemMetaが常に null になっているため、
       * 色を設定できない
       */
      /*
      when(itemStack.getItemMeta()).thenReturn(itemMeta);

      ItemStack lthrArmrItemStack = new ItemStack(Material.LEATHER_HORSE_ARMOR, 1);
      ItemMeta itemMeta = itemStack.getItemMeta();
      LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;
      leatherArmorMeta.setColor(Color.fromARGB(255, 29, 29, 33));
      */
      
      when(horseInv.getArmor()).thenReturn(itemStack);

      EntityWriter horseEw = EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse);
      assertTrue(horseEw.writeHorseBase(eggData));

      AbstractHorse horseAbsHorse = horseEw.getAbsHorse();
      assertTrue(Objects.nonNull(horseAbsHorse));

      boolean isWriteSucccess = horseEw.writeHorse(eggData);
      assertTrue(isWriteSucccess);
    }
  }
}
