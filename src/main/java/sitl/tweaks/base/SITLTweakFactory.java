package sitl.tweaks.base;

import java.util.HashMap;
import java.util.Map;

import sitl.tweaks.base.tweaks.ITweakAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SITLTweakFactory {
	private static Map<String, TweakedItem> m_tweakedItems = new HashMap<String, TweakedItem>();
	
	public static boolean isTweaked(Item item){
		return m_tweakedItems.containsKey(item.getUnlocalizedName());
	}
	
	public static boolean isTweaked(ItemStack stack){
		return isTweaked(stack.getItem());
	}
	
	public static TweakedItem getTweakedItem(Item item){
		return m_tweakedItems.get(item.getUnlocalizedName());
	}

	
	public static TweakedItem getTweakedItem(ItemStack stack){
		return getTweakedItem(stack.getItem());
	}
	
	public static TweakedItem registerTweakedItem(Item item, TweakedItem.TweakType type, String reason, ITweakAction action){
		if(m_tweakedItems.containsKey(item.getUnlocalizedName())) return null;
		TweakedItem tweak = new TweakedItem(item, type, reason, action);
		m_tweakedItems.put(item.getUnlocalizedName(), tweak);
		tweak.getAction().performTweak();
		return tweak;
	}
	
	public static TweakedItem registerTweakedItem(ItemStack stack, TweakedItem.TweakType type, String reason, ITweakAction action){
		return registerTweakedItem(stack.getItem(), type, reason, action);
	}
	
	public static boolean removeItemTweak(Item item){
		if(!m_tweakedItems.containsKey(item.getUnlocalizedName())) return false;
		m_tweakedItems.get(item.getUnlocalizedName()).getAction().undoTweak();
		m_tweakedItems.remove(item.getUnlocalizedName());
		return true;
	}
	
	public static boolean removeItemTweak(ItemStack stack){
		return removeItemTweak(stack.getItem());
	}
	
}