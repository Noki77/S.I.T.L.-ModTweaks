package sitl.tweaks.base;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;

import sitl.tweaks.base.tweaks.ITweakAction;
import sitl.tweaks.util.SITLUtils;

public class TweakedItem {
	
	private String s_unlocName;
	private List<String> l_reason;
	private TweakType type;
	private ITweakAction action;
	
	protected TweakedItem(ItemStack stack, TweakType type, String reason, ITweakAction action){
		this(stack.getItem(), type, reason, action);
	}
	
	protected TweakedItem(Item item, TweakType type, String reason, ITweakAction action){
		this.s_unlocName = item.getUnlocalizedName();
		this.type = type;
		l_reason = SITLUtils.shortStringToMcTT(reason, EnumChatFormatting.YELLOW, null);
		this.action = action;
	}
	
	public String getItemUnlocalizedName(){
		return s_unlocName;
	}
	
	public String getReason(){
		return SITLUtils.convertListIntoString(l_reason);
	}
	
	public TweakType getType(){
		return type;
	}
	
	public void addToTooltip(ItemTooltipEvent event){
		List<String> tooltip = new ArrayList<String>();
		tooltip.add(event.toolTip.get(0));
		if(Keyboard.isKeyDown(Keyboard.KEY_TAB)){
			tooltip.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.UNDERLINE + "ITEM TWEAKED - " + type);
			for(String s : l_reason)
				tooltip.add(s);
		} else {
			tooltip.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.UNDERLINE + "ITEM TWEAKED");
			tooltip.add(EnumChatFormatting.AQUA + "Hold " + EnumChatFormatting.RESET + "" + 
							EnumChatFormatting.RED + "TAB" + EnumChatFormatting.AQUA + " for more details");
		}
		
		for(int i = 1; i < event.toolTip.size(); i++){
			tooltip.add(event.toolTip.get(i));
		}
		
		event.toolTip.clear();
		for(String s : tooltip)
			event.toolTip.add(s);
	}
	
	public ITweakAction getAction(){
		return action;
	}
	
	public enum TweakType{
		
		CHANGED("Recipe changed"),
		REMOVED("Recipe removed"),
		BANNED("Recipe removed, item banned"),
		ADDED("Added"),
		INFO("Information");
		
		private String mess;
		private TweakType(String mess){
			this.mess = mess;
		}
		
		public String toString(){
			return mess;
		}
		
	}
}
