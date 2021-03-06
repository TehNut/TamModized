package tamaized.tammodized.common.helper;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import tamaized.tammodized.TamModized;
import tamaized.tammodized.network.ClientPacketHandler;

import java.io.IOException;

public class FloatyTextHelper {

	public static void sendText(EntityPlayer player, String text) {
		if (player == null || player.world == null || player.world.isRemote) {
			tamaized.tammodized.client.FloatyTextOverlay.addFloatyText(text);
		} else {
			try {
				encode(player, text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void encode(EntityPlayer e, String text) throws IOException {
		if (!(e instanceof EntityPlayerMP)) return;
		EntityPlayerMP player = (EntityPlayerMP) e;
		PacketHelper.PacketWrapper packet = PacketHelper.createPacket(TamModized.channel, TamModized.networkChannelName, ClientPacketHandler.FloatyText);
		packet.getStream().writeUTF(text);
		packet.sendPacket(player);
	}

	public static void decode(ByteBufInputStream bbis) throws IOException {
		sendText(null, bbis.readUTF());
	}

}
