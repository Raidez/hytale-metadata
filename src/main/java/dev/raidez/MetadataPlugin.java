package dev.raidez;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import dev.raidez.codecs.AnyCodec;
import dev.raidez.codecs.BooleanCodec;
import dev.raidez.codecs.NumberCodec;
import dev.raidez.codecs.StringCodec;
import dev.raidez.commands.MetadataCommand;
import dev.raidez.components.MetadataComponent;
import dev.raidez.interactions.ChangeMetadataInteraction;
import dev.raidez.interactions.MatchMetadataInteraction;

public class MetadataPlugin extends JavaPlugin {

    private static MetadataPlugin instance;

    private ComponentType<EntityStore, MetadataComponent> metadataComponent;

    public MetadataPlugin(JavaPluginInit init) {
        super(init);
        instance = this;
    }

    @Override
    protected void setup() {

        // Register codecs
        // https://hytale-docs-mcp.craftserve.com/codecs/#polymorphic-types
        AnyCodec.CODEC.register("String", StringCodec.class, StringCodec.CODEC);
        AnyCodec.CODEC.register("Boolean", BooleanCodec.class, BooleanCodec.CODEC);
        AnyCodec.CODEC.register("Number", NumberCodec.class, NumberCodec.CODEC);

        // Register components
        this.metadataComponent = this.getEntityStoreRegistry()
                .registerComponent(MetadataComponent.class, "MetadataComponent", MetadataComponent.CODEC);

        // Register interactions
        this.getCodecRegistry(Interaction.CODEC)
                .register("ChangeMetadata", ChangeMetadataInteraction.class, ChangeMetadataInteraction.CODEC);
        this.getCodecRegistry(Interaction.CODEC)
                .register("MatchMetadata", MatchMetadataInteraction.class, MatchMetadataInteraction.CODEC);

        // Register commands
        this.getCommandRegistry()
                .registerCommand(new MetadataCommand());
    }

    // Getters

    public static MetadataPlugin get() {
        return instance;
    }

    public ComponentType<EntityStore, MetadataComponent> getMetadataComponent() {
        return metadataComponent;
    }
}
