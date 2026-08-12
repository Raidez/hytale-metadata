package dev.hytalemodding;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;

import dev.hytalemodding.commands.MetadataCommand;
import dev.hytalemodding.components.MetadataComponent;
import dev.hytalemodding.config.ExampleConfig;
import dev.hytalemodding.events.ExampleEvent;
import dev.hytalemodding.interactions.ChangeMetadataInteraction;
import dev.hytalemodding.interactions.MatchMetadataInteraction;

public class ExamplePlugin extends JavaPlugin {

    private static ExamplePlugin instance;
    private static Config<ExampleConfig> config = null;

    private ComponentType<EntityStore, MetadataComponent> metadataComponent;

    public ExamplePlugin(JavaPluginInit init) {
        super(init);
        instance = this;
        config = this.withConfig("example_config", ExampleConfig.CODEC);
    }

    @Override
    protected void setup() {
        config.save();
        if (getConfig().get().isEnabledWelcomeMessage()) {
            this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
        }

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

    public static ExamplePlugin get() {
        return instance;
    }

    public static Config<ExampleConfig> getConfig() {
        return config;
    }

    public ComponentType<EntityStore, MetadataComponent> getMetadataComponent() {
        return metadataComponent;
    }
}
