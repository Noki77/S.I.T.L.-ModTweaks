package sitl.tweaks.handler;

import org.lwjgl.input.Keyboard;

import sitl.tweaks.base.SITLTweakFactory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipHandler {
	
	@SubscribeEvent
	public void onTooltipOpen(ItemTooltipEvent event){
		if(SITLTweakFactory.isTweaked(event.itemStack))
			SITLTweakFactory.getTweakedItem(event.itemStack).addToTooltip(event);
	}

}
