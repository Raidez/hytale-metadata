package dev.hytalemodding.interactions;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.array.ArrayCodec;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;

public class MatchMetadataInteraction extends SimpleInstantInteraction {

    private String keyArg;
    private MetadataMatcher[] matchersArg;

    public static final BuilderCodec<MatchMetadataInteraction> CODEC = BuilderCodec
            .builder(MatchMetadataInteraction.class, MatchMetadataInteraction::new, SimpleInstantInteraction.CODEC)
            .append(new KeyedCodec<>("Key", Codec.STRING, true),
                    (data, value) -> data.keyArg = value,
                    (data) -> data.keyArg)
            .add()
            .append(new KeyedCodec<>("Matchers", new ArrayCodec<>(MetadataMatcher.CODEC, MetadataMatcher[]::new), true),
                    (data, value) -> data.matchersArg = value,
                    (data) -> data.matchersArg)
            .add()
            .build();

    @Override
    protected void firstRun(InteractionType var1, InteractionContext var2, CooldownHandler var3) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'firstRun'");
    }

}
