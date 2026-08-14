package dev.raidez.codecs;

import com.hypixel.hytale.codec.lookup.CodecMapCodec;

public abstract class AnyCodec {

    public static final CodecMapCodec<AnyCodec> CODEC = new CodecMapCodec<>("Type");
}
