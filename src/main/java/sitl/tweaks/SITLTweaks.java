package sitl.tweaks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import sitl.tweaks.base.SITLTweakFactory;
import sitl.tweaks.base.TweakedItem;
import sitl.tweaks.base.tweaks.CraftingTweak;
import sitl.tweaks.base.tweaks.RemoveTweak;
import sitl.tweaks.handler.TooltipHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = "SITLTWEAKS", name = "S.I.T.L. ModTweaks", version = "1.0.0")
public class SITLTweaks
{
    
    @EventHandler
    public void init(FMLInitializationEvent event){}
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	MinecraftForge.EVENT_BUS.register(new TooltipHandler());
    	SITLTweakFactory.registerTweakedItem(Items.stone_pickaxe, 
    			TweakedItem.TweakType.REMOVED, "Removed due to balancing", new RemoveTweak(Items.stone_pickaxe));
    	
    	SITLTweakFactory.registerTweakedItem(Items.golden_pickaxe, 
    			TweakedItem.TweakType.CHANGED, "Changed recipe for balancing", new CraftingTweak(false, 
    					new ItemStack(Items.golden_pickaxe, 1), new Object[]{
    				"ddd", " s ", " s ", 'd', Blocks.dirt, 's', Items.stick}));
    }
    
    
}
