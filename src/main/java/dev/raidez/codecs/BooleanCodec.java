package dev.raidez.codecs;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class BooleanCodec extends AnyCodec {

    private boolean defaultArg;

    public static final BuilderCodec<BooleanCodec> CODEC = BuilderCodec
            .builder(
                    BooleanCodec.class, BooleanCodec::new)
            .append(new KeyedCodec<>("Default", Codec.BOOLEAN, true),
                    (data, value) -> data.defaultArg = value,
                    (data) -> data.defaultArg)
            .add()
            .build();

}
