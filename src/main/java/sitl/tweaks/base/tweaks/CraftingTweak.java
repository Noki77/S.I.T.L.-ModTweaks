package sitl.tweaks.base.tweaks;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

public class CraftingTweak implements ITweakAction {
	
	private ItemStack result;
	private Object[] in;
	private boolean b_shapeless;
	private List<IRecipe> oldRecipes;
	private int i_idInManager;
	private CraftingManager manager = CraftingManager.getInstance();
	public CraftingTweak(boolean isShapeless, ItemStack result, Object... par){
		this.result = result;
		this.in = par;
		this.b_shapeless = isShapeless;
		this.oldRecipes = new ArrayList<IRecipe>();
	}
	
	public void performTweak() {
		List recipes = new ArrayList();
		for(int i = 0; i < manager.getRecipeList().size(); i++){
			Object obj = manager.getRecipeList().get(i);
			if(obj instanceof IRecipe){
				IRecipe cur = (IRecipe) obj;
				if(!((cur.getRecipeOutput() != null) && (cur.getRecipeOutput().getItem() != null) && (cur.getRecipeOutput().getItem() == result.getItem())))
					recipes.add(cur);
				oldRecipes.add(cur);
			}
		}
		
		ReflectionHelper.setPrivateValue(CraftingManager.class, manager, recipes, "recipes");
		
		if(b_shapeless){
			manager.addShapelessRecipe(result, in);
			for(Object obj : recipes){
				if(obj instanceof IRecipe){
					IRecipe cur = (IRecipe) obj;
					if((cur.getRecipeOutput() != null) && (cur.getRecipeOutput().getItem() != null) && (cur.getRecipeOutput().getItem() == result.getItem()))
						i_idInManager = manager.getRecipeList().indexOf(obj);
				}
			}
		} else {
			IRecipe rec = manager.addRecipe(result, in);
			i_idInManager = manager.getRecipeList().indexOf(rec);
		}
	}

	public void undoTweak() {
		manager.getRecipeList().remove(i_idInManager);
		for(IRecipe r : oldRecipes)
			manager.getRecipeList().add(r);
	}

}
