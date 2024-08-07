package me.friendly.exeter.events.unused;

import me.friendly.api.event.Event;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class BlockRendererEvent
extends Event {
    private Block block;
    private BlockPos blockPos;

    public BlockRendererEvent(Block block, BlockPos blockPos) {
        this.block = block;
        this.blockPos = blockPos;
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }
}

