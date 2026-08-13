package dev.raidez.interactions;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.array.ArrayCodec;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;

import dev.raidez.components.MetadataComponent;

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
    protected void firstRun(
            InteractionType interactionType,
            InteractionContext interactionContext,
            CooldownHandler cooldownHandler) {

        var entity = interactionContext.getEntity();
        var commandBuffer = interactionContext.getCommandBuffer();

        var metadata = commandBuffer.ensureAndGetComponent(entity, MetadataComponent.getComponentType());

        var noMatch = true;
        var value = metadata.get(keyArg);
        for (var matcher : matchersArg) {
            if (matcher.matches(value)) {
                matcher.firstRun(interactionType, interactionContext, cooldownHandler);
                noMatch = false;
            }
        }

        if (noMatch) {

        }
    }

}
