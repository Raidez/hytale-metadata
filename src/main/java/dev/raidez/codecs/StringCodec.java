package dev.raidez.codecs;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.Codec;

public class StringCodec extends AnyCodec {

    private String defaultArg;

    public static final BuilderCodec<StringCodec> CODEC = BuilderCodec.builder(
            StringCodec.class, StringCodec::new)
            .append(new KeyedCodec<>("Default", Codec.STRING, true),
                    (data, value) -> data.defaultArg = value,
                    (data) -> data.defaultArg)
            .add()
            .build();

}
