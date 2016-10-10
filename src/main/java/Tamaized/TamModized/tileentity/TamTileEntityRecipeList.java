package Tamaized.TamModized.tileentity;

import java.util.ArrayList;

import Tamaized.TamModized.TamModized;
import Tamaized.TamModized.tileentity.TamTileEntityRecipeList.TamTERecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class TamTileEntityRecipeList<T extends TamTERecipe> {

	private ArrayList<T> recipes = new ArrayList<T>();

	public boolean registerRecipe(T recipe) {
		if (recipes.contains(recipe)) return false;
		recipes.add(recipe);
		TamModized.logger.info("(" + getModID() + ":" + getName() + "): " + recipe.getInput() + " => " + recipe.getOutput());
		return true;
	}

	public boolean isInput(Item item) {
		for (T r : recipes) {
			if (r.getInput().getItem() == item) return true;
		}
		return false;
	}

	public boolean isInput(ItemStack stack) {
		for (T r : recipes) {
			if (r.getInput().areItemStacksEqual(r.getInput(), stack)) return true;
		}
		return false;
	}

	public ItemStack getOutput(Item item) {
		for (T r : recipes) {
			if (r.getInput().getItem() == item) return r.getOutput();
		}
		return null;
	}

	public ItemStack getOutput(ItemStack stack) {
		for (T r : recipes) {
			if (r.getInput().areItemStacksEqual(r.getInput(), stack)) return r.getOutput();
		}
		return null;
	}

	protected abstract String getName();

	protected abstract String getModID();

	/**
	 * This holds the end result and additional requirement data
	 */
	public class TamTERecipe {

		private final ItemStack input;
		private final ItemStack output;

		public TamTERecipe(ItemStack input, ItemStack output) {
			this.input = input;
			this.output = output;
		}

		public ItemStack getInput() {
			return input;
		}

		public ItemStack getOutput() {
			return output;
		}

		@Override
		public boolean equals(Object obj) {
			return (obj instanceof TamTileEntityRecipeList.TamTERecipe) ? (input.areItemStacksEqual(input, ((TamTERecipe) obj).getInput()) && (output.areItemStacksEqual(output, ((TamTERecipe) obj).getOutput()))) : false;
		}

	}

}
