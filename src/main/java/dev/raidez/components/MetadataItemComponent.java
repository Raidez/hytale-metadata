package dev.raidez.components;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.EnumCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import dev.raidez.enums.MetadataType;

public class MetadataItemComponent implements Component<EntityStore> {

    private MetadataType typeArg;
    private String defaultArg;

    public static final BuilderCodec<MetadataItemComponent> CODEC = BuilderCodec
            .builder(MetadataItemComponent.class, MetadataItemComponent::new)
            .append(new KeyedCodec<>("Type", new EnumCodec<>(MetadataType.class)),
                    (data, value) -> data.typeArg = value,
                    (data) -> data.typeArg)
            .add()
            .append(new KeyedCodec<>("Default", Codec.STRING),
                    (data, value) -> data.defaultArg = value,
                    (data) -> data.defaultArg)
            .add()
            .build();

    public MetadataItemComponent() {
        this.typeArg = MetadataType.STRING;
        this.defaultArg = "";
    }

    public MetadataItemComponent(MetadataType typeArg, String defaultArg) {
        this.typeArg = typeArg;
        this.defaultArg = defaultArg;
    }

    public MetadataItemComponent(MetadataItemComponent clone) {
        this.typeArg = clone.typeArg;
        this.defaultArg = clone.defaultArg;
    }

    @Override
    public MetadataItemComponent clone() {
        return new MetadataItemComponent(this);
    }
}
