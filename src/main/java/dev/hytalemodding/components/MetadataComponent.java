package dev.hytalemodding.components;

import java.util.Map;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.map.MapCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import dev.hytalemodding.ExamplePlugin;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class MetadataComponent implements Component<EntityStore> {

    private Map<String, MetadataItemComponent> metadataArg;

    public static ComponentType<EntityStore, MetadataComponent> getComponentType() {
        return ExamplePlugin.get().getMetadataComponent();
    }

    public static final BuilderCodec<MetadataComponent> CODEC = BuilderCodec
            .builder(MetadataComponent.class, MetadataComponent::new)
            .append(new KeyedCodec<>("Metadata",
                    new MapCodec<>(MetadataItemComponent.CODEC, Object2ObjectOpenHashMap::new)),
                    (data, value) -> data.metadataArg = value,
                    (data) -> data.metadataArg)
            .add()
            .build();

    public MetadataComponent() {
        this.metadataArg = new Object2ObjectOpenHashMap<>();
    }

    public MetadataComponent(MetadataComponent clone) {
        this.metadataArg = clone.metadataArg;
    }

    @Override
    public MetadataComponent clone() {
        return new MetadataComponent(this);
    }

}
