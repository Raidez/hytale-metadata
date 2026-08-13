package dev.hytalemodding.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import dev.hytalemodding.components.MetadataComponent;

public class MetadataResetCommand extends AbstractPlayerCommand {

    private final RequiredArg<String> keyArg;

    public MetadataResetCommand() {
        super("reset", "Reset metadata for entities");
        this.keyArg = this.withRequiredArg("key", "Metadata key", ArgTypes.STRING);
    }

    @Override
    protected void execute(
            CommandContext commandContext,
            Store<EntityStore> store,
            Ref<EntityStore> ref,
            PlayerRef playerRef,
            World world) {

        var key = commandContext.get(keyArg);

        var metadata = store.ensureAndGetComponent(ref, MetadataComponent.getComponentType());
        metadata.put(key, "");
        commandContext.sendMessage(Message.raw(String.format("Reset %s", key)));
    }

}
