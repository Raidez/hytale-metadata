package dev.hytalemodding.interactions;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.EnumCodec;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;

import dev.hytalemodding.enums.MetadataOperation;

public class ChangeMetadataInteraction extends SimpleInstantInteraction {

    private MetadataOperation operationArg = MetadataOperation.SET;
    private String keyArg;
    private String valueArg;

    public static final BuilderCodec<ChangeMetadataInteraction> CODEC = BuilderCodec
            .builder(ChangeMetadataInteraction.class, ChangeMetadataInteraction::new, SimpleInstantInteraction.CODEC)
            .append(new KeyedCodec<>("Operation", new EnumCodec<>(MetadataOperation.class)
                    .documentKey(MetadataOperation.SET, "Set value to specified value")
                    .documentKey(MetadataOperation.RESET, "Reset value to default")
                    .documentKey(MetadataOperation.COPY_FROM, "Copy value from another key")
                    .documentKey(MetadataOperation.INCREMENT, "Increment value by specified amount (number only)")
                    .documentKey(MetadataOperation.DECREMENT, "Decrement value by specified amount (number only)")
                    .documentKey(MetadataOperation.TOGGLE, "Toggle value (boolean only)")
                    .documentKey(MetadataOperation.CONCAT, "Concat value (string only)")
                    .documentKey(MetadataOperation.CONCAT_FROM, "Concat value from another key (string only)"), true),
                    (data, value) -> data.operationArg = value,
                    (data) -> data.operationArg)
            .add()
            .append(new KeyedCodec<>("Key", Codec.STRING, true),
                    (data, value) -> data.keyArg = value,
                    (data) -> data.keyArg)
            .add()
            .append(new KeyedCodec<>("Value", Codec.STRING, true),
                    (data, value) -> data.valueArg = value,
                    (data) -> data.valueArg)
            .add()
            .build();

    @Override
    protected void firstRun(InteractionType var1, InteractionContext var2, CooldownHandler var3) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'firstRun'");
    }

}
