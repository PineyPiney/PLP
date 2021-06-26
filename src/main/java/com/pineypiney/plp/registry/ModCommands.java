package com.pineypiney.plp.registry;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class ModCommands {

    public static void registerCommands(){

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(literal("foo")
            .executes(context -> {
                System.out.println("Foo");
                return 0;
            })
        ));

        // /Paper Command
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> dispatcher.register(literal("paper")
            .then(literal("passive")
                .executes(context -> {
                    placePaper(context, "Peace, Love and Plants");
                    return 0;
                })
            )
            .then(literal("skin")
                .executes(context -> {
                    placePaper(context, "Peace, Love and no Pants");
                    return 0;
                })
            )
            .then(literal("passive_skin")
                .executes(context -> {
                    placePaper(context, "Peace, Love and Plants + Peace, Love and no Pants");
                    return 0;
                })
            )
        )));

        System.out.println("Registered Mod Commands");
    }

    public static void placePaper(CommandContext<ServerCommandSource> context, String name) throws CommandSyntaxException {
        ItemStack paperStack = new ItemStack(Items.PAPER, 1);
        paperStack.setCustomName(Text.of(name));
        System.out.println("Stack is " + paperStack + " for player " + context.getSource().getPlayer());
        context.getSource().getPlayer().giveItemStack(paperStack);
    }
}
