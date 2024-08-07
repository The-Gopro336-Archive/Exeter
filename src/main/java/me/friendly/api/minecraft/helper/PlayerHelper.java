package me.friendly.api.minecraft.helper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public final class PlayerHelper {
    private static Minecraft minecraft = Minecraft.getMinecraft();

    public static Block getBlockBelowPlayer(double height) {
        return WorldHelper.getBlock(PlayerHelper.minecraft.player.posX, PlayerHelper.minecraft.player.posY - height, PlayerHelper.minecraft.player.posZ);
    }

    public static Block getBlockAbovePlayer(double height) {
        return WorldHelper.getBlock(PlayerHelper.minecraft.player.posX, PlayerHelper.minecraft.player.posY + height, PlayerHelper.minecraft.player.posZ);
    }

    public static boolean isInLiquid() {
        if (PlayerHelper.minecraft.player == null) {
            return false;
        }
        boolean inLiquid = false;
        int y = (int)PlayerHelper.minecraft.player.getEntityBoundingBox().minY;
        for (int x = MathHelper.floor(PlayerHelper.minecraft.player.getEntityBoundingBox().minX); x < MathHelper.floor(PlayerHelper.minecraft.player.getEntityBoundingBox().maxX) + 1; ++x) {
            for (int z = MathHelper.floor(PlayerHelper.minecraft.player.getEntityBoundingBox().minZ); z < MathHelper.floor(PlayerHelper.minecraft.player.getEntityBoundingBox().maxZ) + 1; ++z) {
                Block block = PlayerHelper.minecraft.world.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (block == null || block instanceof BlockAir) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                if (block instanceof BlockLiquid) {
                    return true;
                }
                inLiquid = true;
            }
        }
        return inLiquid;
    }

    public static boolean isInLiquid(double offset) {
        return PlayerHelper.getBlockBelowPlayer(-offset) instanceof BlockLiquid;
    }

    public static boolean isOnLiquid() {
        AxisAlignedBB boundingBox = PlayerHelper.minecraft.player.getEntityBoundingBox();
        boundingBox = boundingBox.contract(0.0, 0.0, 0.0).offset(0.0, -0.02, 0.0);
        boolean onLiquid = false;
        int y = (int)boundingBox.minY;
        for (int x = MathHelper.floor(boundingBox.minX); x < MathHelper.floor(boundingBox.maxX + 1.0); ++x) {
            for (int z = MathHelper.floor(boundingBox.minZ); z < MathHelper.floor(boundingBox.maxZ + 1.0); ++z) {
                Block block = WorldHelper.getBlock(x, y, z);
                if (block == Blocks.AIR) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                onLiquid = true;
            }
        }
        return onLiquid;
    }

    public static boolean isAiming(float yaw, float pitch, int fov) {
        float pitchDiff;
        yaw = PlayerHelper.wrapAngleTo180(yaw);
        pitch = PlayerHelper.wrapAngleTo180(pitch);
        float curYaw = PlayerHelper.wrapAngleTo180(PlayerHelper.minecraft.player.rotationYaw);
        float curPitch = PlayerHelper.wrapAngleTo180(PlayerHelper.minecraft.player.rotationPitch);
        float yawDiff = Math.abs(yaw - curYaw);
        return yawDiff + (pitchDiff = Math.abs(pitch - curPitch)) <= (float)fov;
    }

    public static float getFOV(float[] rotations) {
        float yaw = rotations[0];
        float pitch = rotations[1];
        yaw = PlayerHelper.wrapAngleTo180(yaw);
        pitch = PlayerHelper.wrapAngleTo180(pitch);
        float curYaw = PlayerHelper.wrapAngleTo180(PlayerHelper.minecraft.player.rotationYaw);
        float curPitch = PlayerHelper.wrapAngleTo180(PlayerHelper.minecraft.player.rotationPitch);
        float yawDiff = Math.abs(yaw - curYaw);
        float pitchDiff = Math.abs(pitch - curPitch);
        return yawDiff + pitchDiff;
    }

    public static float wrapAngleTo180(float angle) {
        if ((angle %= 360.0f) >= 180.0f) {
            angle -= 360.0f;
        }
        if (angle < -180.0f) {
            angle += 360.0f;
        }
        return angle;
    }

    public static boolean isMoving() {
        return (double)PlayerHelper.minecraft.player.moveForward != 0.0 || (double)PlayerHelper.minecraft.player.moveStrafing != 0.0;
    }

    public static boolean isPressingMoveKeybinds() {
        return PlayerHelper.minecraft.gameSettings.keyBindForward.isPressed() || PlayerHelper.minecraft.gameSettings.keyBindBack.isPressed() || PlayerHelper.minecraft.gameSettings.keyBindLeft.isPressed() || PlayerHelper.minecraft.gameSettings.keyBindRight.isPressed();
    }

    public static String getFacingWithProperCapitals() {
        String directionLabel;
        switch (directionLabel = minecraft.getRenderViewEntity().getHorizontalFacing().getName()) {
            case "north": {
                directionLabel = "North";
                break;
            }
            case "south": {
                directionLabel = "South";
                break;
            }
            case "west": {
                directionLabel = "West";
                break;
            }
            case "east": {
                directionLabel = "East";
            }
        }
        return directionLabel;
    }
}

