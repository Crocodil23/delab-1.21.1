package net.crocodil.delab.gui.AlloysFurnace;

import com.mojang.blaze3d.systems.RenderSystem;
import net.crocodil.delab.Delab;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;

public class AlloysFurnaceScreen extends AbstractContainerScreen<AlloyFurnaceMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/gui/alloys_furnace.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/gui/burn_progress.png");
    private static final ResourceLocation LIT_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/gui/lit_progress.png");

    public AlloysFurnaceScreen(AlloyFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 177;
        this.inventoryLabelY = this.imageHeight - 94;
    }
    @Override
    protected void init() {
        super.init();
        // center the title relative to imageWidth (this.title is a Component)

        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        // leave titleLabelY alone (vanilla furnaces use ~6)
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderLitProgress(guiGraphics, x, y);
    }
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE,x + 68, y + 26, 0, 0, menu.getScaledArrowProgress(), 48, 31, 48);
        }
    }
    private void renderLitProgress(GuiGraphics guiGraphics, int x, int y)
    {
        if(menu.blockEntity.isLit()) {
            int scaled = menu.getBurnScale();
            guiGraphics.blit(LIT_TEXTURE, x + 15, y + 47 + (14 - scaled),
                    0, 14 - scaled, 14, scaled,
                    14, 14);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
