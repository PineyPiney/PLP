package com.pineypiney.plp.mixins;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieBaseEntityRenderer.class)
public abstract class ZombieRendererMixin<T extends ZombieEntity, M extends ZombieEntityModel<T>> extends BipedEntityRenderer<T, M> {
    public ZombieRendererMixin(EntityRendererFactory.Context ctx, M model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(at = @At("HEAD"), method = "getTexture", cancellable = true)
    private void getTexture(ZombieEntity zombieEntity, CallbackInfoReturnable<Identifier> ci){
        if(noPants(zombieEntity)){
            ci.setReturnValue(new Identifier("textures/entity/zombie/pantsless_zombie.png"));
        }
    }

    private boolean noPants(ZombieEntity zombie){
        if(zombie != null){
            Iterable<ItemStack> handItems = zombie.getItemsHand();
            for(ItemStack itemStack : handItems){
                if(itemStack.getItem().equals(Items.PAPER)){
                    if(itemStack.getName().asString().toLowerCase().contains("peace, love and no pants")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
