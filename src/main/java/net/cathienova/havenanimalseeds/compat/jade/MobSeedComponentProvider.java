package net.cathienova.havenanimalseeds.compat.jade;

import net.cathienova.havenanimalseeds.config.HavenConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum MobSeedComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().contains("RemainingTime")) {
            IElementHelper elements = tooltip.getElementHelper();
            IElement icon = elements.item(new ItemStack(Items.CLOCK), 0.5f).size(new Vec2(10, 10)).translate(new Vec2(0, -1));
            icon.message(null);
            tooltip.add(icon);
            int remainingTime = accessor.getServerData().getInt("RemainingTime");
            tooltip.append(Component.translatable("mobseed.remaining_time", remainingTime));

            if (accessor.getPlayer() != null && accessor.getPlayer().distanceToSqr(accessor.getPosition().getX(), accessor.getPosition().getY(), accessor.getPosition().getZ()) < Math.pow(HavenConfig.playerGrowthDistance, 2)) {
                tooltip.add(Component.translatable("mobseed.tooclose"));
            }

        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation("havenanimalseeds", "mobseed_remaining_time");
    }
}
