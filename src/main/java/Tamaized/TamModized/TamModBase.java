package Tamaized.TamModized;

import net.minecraft.entity.Entity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Logger;

import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.TamModized.registry.TamRegistryHandler;

public abstract class TamModBase {

	public static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;

	public static Logger logger;

	private TamRegistryHandler registryHandler = new TamRegistryHandler();
	
	private int modEntityID = 0;
	
	static {
		FluidRegistry.enableUniversalBucket();
	}

	/**
	 * super this first if possible
	 */
	public void preInit(FMLPreInitializationEvent event) {
		registryHandler.preInit();
	}

	/**
	 * super this first if possible
	 */
	public void init(FMLInitializationEvent event) {
		registryHandler.init();
	}

	/**
	 * super this first if possible
	 */
	public void postInit(FMLPostInitializationEvent e) {
		registryHandler.postInit();
	}

	protected void register(ITamRegistry r) {
		registryHandler.register(r);
	}

	/**
	 * call this in clientProxy
	 */
	public void clientPreInit() {
		registryHandler.clientPreInit();
	}

	/**
	 * call this in clientProxy
	 */
	public void clientInit() {
		registryHandler.clientInit();
	}

	/**
	 * call this in clientProxy
	 */
	public void clientPostInit() {
		registryHandler.clientPostInit();
	}
	
	protected void registerEntity(Class<? extends Entity> entityClass, String entityName, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates){
		EntityRegistry.registerModEntity(entityClass, entityName, modEntityID, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
		modEntityID++;
	}

}