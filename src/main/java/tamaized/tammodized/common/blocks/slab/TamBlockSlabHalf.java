package tamaized.tammodized.common.blocks.slab;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TamBlockSlabHalf extends TamBlockSlab.Half {

	private final Item item;

	public TamBlockSlabHalf(CreativeTabs tab, Material materialIn, String n, Item item) {
		super(tab, materialIn, n);
		this.item = item;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return item;
	}

}
