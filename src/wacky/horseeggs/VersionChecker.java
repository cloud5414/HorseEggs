package wacky.horseeggs;


public class VersionChecker {
	String[] versions = {"v1_13_R2", "v1_14_R1", "v1_15_R1","v1_16_R3"};

	public VersionChecker() {
		// TODO 自動生成されたコンストラクター・スタブ.
	}

	public String check(){
		for(int i = 0; i<4; i++){

			try{
				Class<?> c;
				c = Class.forName("org.bukkit.craftbukkit." + versions[i] + ".CraftServer");
				c.getName();
			}catch(ClassNotFoundException e){
				continue;
			}
			return versions[i];
		}
		return "other";
	}
}