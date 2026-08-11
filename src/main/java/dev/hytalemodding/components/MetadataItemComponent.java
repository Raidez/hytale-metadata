package dev.hytalemodding.components;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.EnumCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import dev.hytalemodding.codecs.MetadataValueCodec;
import dev.hytalemodding.enums.MetadataType;

public class MetadataItemComponent implements Component<EntityStore> {

    private MetadataType typeArg;
    private Object defaultArg;

    public static final BuilderCodec<MetadataItemComponent> CODEC = BuilderCodec
            .builder(MetadataItemComponent.class, MetadataItemComponent::new)
            .append(new KeyedCodec<>("Type", new EnumCodec<>(MetadataType.class)),
                    (data, value) -> data.typeArg = value,
                    (data) -> data.typeArg)
            .add()
            .append(new KeyedCodec<>("Default", new MetadataValueCodec()),
                    (data, value) -> data.defaultArg = value,
                    (data) -> data.defaultArg)
            .add()
            .build();

    public MetadataItemComponent() {
        this.typeArg = MetadataType.STRING;
        this.defaultArg = "";
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
