package dev.hytalemodding.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class MetadataSetCommand extends AbstractPlayerCommand {

    private final RequiredArg<String> keyArg;
    private final RequiredArg<String> valueArg;

    public MetadataSetCommand() {
        super("set", "Set metadata for entities");
        this.keyArg = this.withRequiredArg("key", "Metadata key", ArgTypes.STRING);
        this.valueArg = this.withRequiredArg("value", "Metadata value", ArgTypes.STRING);
    }

    @Override
    protected void execute(
            CommandContext commandContext,
            Store<EntityStore> store,
            Ref<EntityStore> ref,
            PlayerRef playerRef,
            World world) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
