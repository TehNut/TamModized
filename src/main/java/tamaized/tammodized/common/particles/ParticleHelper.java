package tamaized.tammodized.common.particles;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.TamModized;
import tamaized.tammodized.common.helper.PacketHelper;
import tamaized.tammodized.network.ClientPacketHandler;

import java.io.DataOutputStream;
import java.io.IOException;

public class ParticleHelper {

	public static void sendPacketToClients(World world, int handlerID, Vec3d pos, int range, ParticlePacketHelper packetHelper) {
		try {
			PacketHelper.PacketWrapper packet = PacketHelper.createPacket(TamModized.channel, TamModized.networkChannelName, ClientPacketHandler.TYPE_PARTICLE);
			DataOutputStream stream = packet.getStream();
			stream.writeInt(handlerID);
			stream.writeDouble(pos.x);
			stream.writeDouble(pos.y);
			stream.writeDouble(pos.z);
			packetHelper.encode(stream);
			packet.sendPacket(new TargetPoint(world.provider.getDimension(), pos.x, pos.y, pos.z, range));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SideOnly(Side.CLIENT)
	public static void decodePacket(ByteBufInputStream packet) {
		try {
			int handlerID = packet.readInt();
			Vec3d pos = new Vec3d(packet.readDouble(), packet.readDouble(), packet.readDouble());
			spawnParticle(ParticlePacketHandlerRegistry.getHandler(handlerID).decode(packet, Minecraft.getMinecraft().world, pos));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SideOnly(Side.CLIENT)
	public static void spawnParticle(TamParticle particle) {
		Minecraft.getMinecraft().effectRenderer.addEffect(particle);
	}

	public static void spawnVanillaParticleOnServer(World world, EnumParticleTypes particle, double x, double y, double z, double xS, double yS, double zS) {
		try {
			PacketHelper.PacketWrapper packet = PacketHelper.createPacket(TamModized.channel, TamModized.networkChannelName, ClientPacketHandler.TYPE_PARTICLE_VANILLA);
			DataOutputStream stream = packet.getStream();
			stream.writeInt(particle.getParticleID());
			stream.writeDouble(x);
			stream.writeDouble(y);
			stream.writeDouble(z);
			stream.writeDouble(xS);
			stream.writeDouble(yS);
			stream.writeDouble(zS);
			packet.sendPacket(new TargetPoint(world.provider.getDimension(), x, y, z, 64));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SideOnly(Side.CLIENT)
	public static void decodePacketVanillaParticle(ByteBufInputStream stream) throws IOException {
		spawnVanillaParticleOnClient(net.minecraft.client.Minecraft.getMinecraft().world, EnumParticleTypes.getParticleFromId(stream.readInt()), stream.readDouble(), stream.readDouble(), stream.readDouble(), stream.readDouble(), stream.readDouble(), stream.readDouble());
	}

	@SideOnly(Side.CLIENT)
	public static void spawnVanillaParticleOnClient(World world, EnumParticleTypes particle, double x, double y, double z, double xS, double yS, double zS) {
		world.spawnParticle(particle, x, y, z, xS, yS, zS);
	}

	public static interface IParticlePacketData {

	}

	public static class ParticlePacketHelper {

		private final ParticlePacketBase packetHandler;
		private final IParticlePacketData data;

		public ParticlePacketHelper(ParticlePacketBase packetBase, IParticlePacketData dat) {
			packetHandler = packetBase;
			data = dat;
		}

		public ParticlePacketHelper(int packetBase, IParticlePacketData dat) {
			this(ParticlePacketHandlerRegistry.getHandler(packetBase), dat);
		}

		public final void encode(DataOutputStream stream) throws IOException {
			packetHandler.encode(stream, data);
		}

	}

}
