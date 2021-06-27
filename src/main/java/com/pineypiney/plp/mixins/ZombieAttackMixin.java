package com.pineypiney.plp.mixins;

import com.pineypiney.plp.registry.ModSounds;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieAttackGoal.class)
public abstract class ZombieAttackMixin extends MeleeAttackGoal {

    @Shadow public abstract void stop();
    @Shadow @Final
    private ZombieEntity zombie;

    public ZombieAttackMixin(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
    }

    @Override
    public boolean canStart(){
        return super.canStart() && !holdingThePeace(zombie);
    }

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void tick(CallbackInfo ci){
        if(holdingThePeace(zombie)){
            playMusic();
            stop();
            ci.cancel();
        }
    }

    private boolean holdingThePeace(ZombieEntity zombie){
        if(zombie != null){
            Iterable<ItemStack> handItems = zombie.getItemsHand();
            for(ItemStack itemStack : handItems){
                if(itemStack.getItem().equals(Items.PAPER)){
                    if(itemStack.getName().asString().toLowerCase().contains("peace, love and plants")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void playMusic(){
        zombie.world.playSound(null, zombie.getBlockPos(), ModSounds.NANI_EVENT, SoundCategory.MASTER, 5, 1);
    }
}
