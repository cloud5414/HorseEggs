/**
 * 
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * 
 */
public class DonkeyEggData extends EggDataBase {

  /**
   * 
   */
  public DonkeyEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  @Override
  public EntityType getEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return EntityType.DONKEY;
  }

  @Override
  public Material getFilledEggMaterial() {
    // TODO 自動生成されたメソッド・スタブ
    return Material.DONKEY_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

}