package sitl.tweaks.base.tweaks;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class RemoveTweak implements ITweakAction {

	private List<IRecipe> oldRecipes;
	private CraftingManager manager = CraftingManager.getInstance();
	private Item target;
	public RemoveTweak(ItemStack target) {
		this(target.getItem());
	}
	
	public RemoveTweak(Item target){
		this.target = target;
		this.oldRecipes = new ArrayList<IRecipe>();
	}
	
	public void performTweak() {
		List recipes = new ArrayList();
		for(int i = 0; i < manager.getRecipeList().size(); i++){
			Object obj = manager.getRecipeList().get(i);
			if(obj instanceof IRecipe){
				IRecipe cur = (IRecipe) obj;
				if(!((cur.getRecipeOutput() != null) && (cur.getRecipeOutput().getItem() != null) && (cur.getRecipeOutput().getItem() == target)))
					recipes.add(cur);
				oldRecipes.add(cur);
			}
		}
		
		ReflectionHelper.setPrivateValue(CraftingManager.class, manager, recipes, "recipes");
	}

	public void undoTweak() {
		for(IRecipe r : oldRecipes)
			manager.getRecipeList().add(r);
	}

}
