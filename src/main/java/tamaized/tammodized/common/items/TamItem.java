package tamaized.tammodized.common.items;

import tamaized.tammodized.registry.ITamRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class TamItem extends Item implements ITamRegistry {

	private final String name;

	public TamItem(CreativeTabs tab, String n, int maxStackSize) {
		super();
		name = n;
		setUnlocalizedName(name);
		setMaxStackSize(maxStackSize);
		setRegistryName(name);
		this.setCreativeTab(tab);
	}

	public String getModelDir() {
		return "items";
	}

	@Override
	public void registerBlock(RegistryEvent.Register<Block> e) {

	}

	@Override
	public void registerItem(RegistryEvent.Register<Item> e) {
		e.getRegistry().register(this);
	}

	@Override
	public void registerModel(ModelRegistryEvent e) {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(getRegistryName().getResourceDomain(), getModelDir() + "/" + getRegistryName().getResourcePath()), "inventory"));
	}
}
