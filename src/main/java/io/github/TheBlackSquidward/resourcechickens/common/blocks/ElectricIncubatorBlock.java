package io.github.TheBlackSquidward.resourcechickens.common.blocks;

import io.github.TheBlackSquidward.resourcechickens.common.containers.ChickenBreederContainer;
import io.github.TheBlackSquidward.resourcechickens.common.containers.ElectricIncubatorContainer;
import io.github.TheBlackSquidward.resourcechickens.common.te.ChickenBreederTE;
import io.github.TheBlackSquidward.resourcechickens.common.te.ElectricIncubatorTE;
import io.github.TheBlackSquidward.resourcechickens.common.te.ElectricRoostTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class ElectricIncubatorBlock extends Block {

    public ElectricIncubatorBlock() {
        super(Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ElectricIncubatorTE();
    }

    @Override
    public ActionResultType onUse(BlockState p_225533_1_, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult p_225533_6_) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof ElectricIncubatorTE) {
                INamedContainerProvider containerProvider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("gui.resourcechickens.electric_incubator");
                    }

                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new ElectricIncubatorContainer(i, world, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
                return ActionResultType.SUCCESS;
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }else{
            return ActionResultType.FAIL;
        }
    }

}