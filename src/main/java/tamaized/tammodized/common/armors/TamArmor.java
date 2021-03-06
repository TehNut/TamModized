package tamaized.tammodized.common.armors;

import tamaized.tammodized.registry.ITamRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class TamArmor extends ItemArmor implements ITamRegistry {

	private final String name;

	public TamArmor(CreativeTabs tab, ArmorMaterial armorMaterial, int par3, EntityEquipmentSlot par4, String type, String n) {
		super(armorMaterial, par3, par4);
		name = n;
		setUnlocalizedName(name);
		setRegistryName(n);
		this.setMaxStackSize(1);
		this.setCreativeTab(tab);
	}

	public String getModelDir() {
		return "armors";
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
