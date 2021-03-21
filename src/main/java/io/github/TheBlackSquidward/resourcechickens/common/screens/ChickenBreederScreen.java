package io.github.TheBlackSquidward.resourcechickens.common.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.TheBlackSquidward.resourcechickens.ResourceChickens;
import io.github.TheBlackSquidward.resourcechickens.common.containers.ChickenBreederContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ChickenBreederScreen extends ContainerScreen<ChickenBreederContainer> {

    private final ResourceLocation GUI = new ResourceLocation(ResourceChickens.MODID, "textures/gui/chicken_breeder_gui.png");

    public ChickenBreederScreen(ChickenBreederContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    protected void drawBackground(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.getMinecraft().getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.drawTexture(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
        drawTexture(matrixStack, relX + 84, relY + 22, 176, 0, getProgressWidth(), 12);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(matrixStack, mouseX, mouseY);
    }

    private int getProgressWidth() {
        double progress = this.container.getProgress();
        return (progress == 0.0D) ? 0 : (1 + (int) (progress * 25.0D));
    }
}
