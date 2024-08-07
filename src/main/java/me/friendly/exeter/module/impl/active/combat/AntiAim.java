package me.friendly.exeter.module.impl.active.combat;

import me.friendly.api.event.Listener;
import me.friendly.exeter.core.Exeter;
import me.friendly.exeter.events.PacketEvent;
import me.friendly.exeter.module.Module;

public final class AntiAim
extends Module {
    public AntiAim() {
        super("Anti Aim", new String[]{"antiaim", "aa"});
        Exeter.getInstance().getEventManager().register(new Listener<PacketEvent>("anti_aim_packet_listener"){

            @Override
            public void call(PacketEvent event) {
//                if (event.getPacket() instanceof SPacketPlayerPosLook) {
//                    SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
//                    if (((AntiAim)AntiAim.this).minecraft.player.rotationYaw != -180.0f && ((AntiAim)AntiAim.this).minecraft.player.rotationPitch != 0.0f) {
//                        packet.yaw(((AntiAim)AntiAim.this).minecraft.player.rotationYaw);
//                        packet.pitch(((AntiAim)AntiAim.this).minecraft.player.rotationPitch);
//                    }
//                }
            }
        });
    }
}

