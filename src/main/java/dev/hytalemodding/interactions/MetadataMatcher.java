package dev.hytalemodding.interactions;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.EnumCodec;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInteraction;

import dev.hytalemodding.enums.MatcherType;

public class MetadataMatcher extends SimpleInteraction {

    private MatcherType typeArg;
    private String valueArg;

    public static final BuilderCodec<MetadataMatcher> CODEC = BuilderCodec
            .builder(MetadataMatcher.class, MetadataMatcher::new, SimpleInteraction.CODEC)
            .append(new KeyedCodec<>("Type", new EnumCodec<>(MatcherType.class)
                    .documentKey(MatcherType.EQUALS, "Equals")
                    .documentKey(MatcherType.NOT_EQUALS, "Not Equals")
                    .documentKey(MatcherType.GREATER_THAN, "Greater Than")
                    .documentKey(MatcherType.GREATER_THAN_OR_EQUAL, "Greater Than or Equal")
                    .documentKey(MatcherType.LESS_THAN, "Less Than")
                    .documentKey(MatcherType.LESS_THAN_OR_EQUAL, "Less Than or Equal")
                    .documentKey(MatcherType.CONTAINS, "Contains")
                    .documentKey(MatcherType.NOT_CONTAINS, "Does Not Contain")
                    .documentKey(MatcherType.STARTS_WITH, "Starts With")
                    .documentKey(MatcherType.ENDS_WITH, "Ends With")
                    .documentKey(MatcherType.REGEX, "Matches Regex")
                    .documentKey(MatcherType.IS_TRUE, "Is True")
                    .documentKey(MatcherType.IS_FALSE, "Is False")
                    .documentKey(MatcherType.EMPTY, "Is Empty")
                    .documentKey(MatcherType.NOT_EMPTY, "Is Not Empty"), true),
                    (data, value) -> data.typeArg = value,
                    (data) -> data.typeArg)

            .add()
            .append(new KeyedCodec<>("Value", Codec.STRING),
                    (data, value) -> data.valueArg = value,
                    (data) -> data.valueArg)
            .add()
            .build();

    public MetadataMatcher() {
        this.valueArg = "";
    }

}
