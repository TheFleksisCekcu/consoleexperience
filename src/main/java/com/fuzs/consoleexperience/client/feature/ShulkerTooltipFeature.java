package com.fuzs.consoleexperience.client.feature;

import com.fuzs.consoleexperience.helper.IShulkerTooltip;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;

import java.util.List;

public class ShulkerTooltipFeature extends Feature implements IShulkerTooltip {

    private ForgeConfigSpec.IntValue rows;

    @Override
    public void setupFeature() {

        this.addListener(EventPriority.LOW, this::onMakeTooltip);
    }

    @Override
    protected boolean getDefaultState() {

        return true;
    }

    @Override
    protected String getDisplayName() {

        return "Sum Shulker Box Contents";
    }

    @Override
    protected String getDescription() {

        return "Sum up stacks of equal items on the shulker box tooltip.";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {

        this.rows = builder.comment("Maximum amount of rows on the shulker box tooltip.").defineInRange("Shulker Box Rows", 6, 0, Integer.MAX_VALUE);
    }

    private void onMakeTooltip(final ItemTooltipEvent evt) {

        if (Block.getBlockFromItem(evt.getItemStack().getItem()) instanceof ShulkerBoxBlock) {

            List<ITextComponent> tooltip = evt.getToolTip();
            List<ITextComponent> oldContents = Lists.newArrayList();
            evt.getItemStack().getItem().addInformation(evt.getItemStack(),
                    evt.getPlayer() == null ? null : evt.getPlayer().world, oldContents, evt.getFlags());

            if (!tooltip.isEmpty() && !oldContents.isEmpty()) {

                int index = tooltip.indexOf(oldContents.get(0));
                if (index != -1 && tooltip.removeAll(oldContents)) {

                    List<ITextComponent> newContents = Lists.newArrayList();
                    this.addInformation(newContents, evt.getItemStack(), TextFormatting.GRAY, this.rows.get());
                    tooltip.addAll(index, newContents);
                }
            }
        }
    }

}
