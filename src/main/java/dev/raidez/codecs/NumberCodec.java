package dev.raidez.codecs;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class NumberCodec extends AnyCodec {

    private double defaultArg;

    public static final BuilderCodec<NumberCodec> CODEC = BuilderCodec
            .builder(
                    NumberCodec.class, NumberCodec::new)
            .append(new KeyedCodec<>("Default", Codec.DOUBLE, true),
                    (data, value) -> data.defaultArg = value,
                    (data) -> data.defaultArg)
            .add()
            .build();

}
