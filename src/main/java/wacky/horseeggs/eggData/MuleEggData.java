/**
 * 
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * 
 */
public class MuleEggData extends EggDataBase {

  /**
   * 
   */
  public MuleEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  @Override
  public EntityType getEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return EntityType.MULE;
  }

  @Override
  public Material getFilledEggMaterial() {
    // TODO 自動生成されたメソッド・スタブ
    return Material.MULE_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

}