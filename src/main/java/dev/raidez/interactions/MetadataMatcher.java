package dev.raidez.interactions;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.EnumCodec;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;

import dev.raidez.enums.MatcherType;

public class MetadataMatcher extends SimpleInstantInteraction {

    private MatcherType typeArg;
    private String valueArg;

    public static final BuilderCodec<MetadataMatcher> CODEC = BuilderCodec
            .builder(MetadataMatcher.class, MetadataMatcher::new, SimpleInstantInteraction.CODEC)
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

    public boolean matches(String value) {
        switch (typeArg) {
            case EQUALS:
                return value.equals(valueArg);
            case NOT_EQUALS:
                return !value.equals(valueArg);
            case GREATER_THAN:
                return value.compareTo(valueArg) > 0;
            case GREATER_THAN_OR_EQUAL:
                return value.compareTo(valueArg) >= 0;
            case LESS_THAN:
                return value.compareTo(valueArg) < 0;
            case LESS_THAN_OR_EQUAL:
                return value.compareTo(valueArg) <= 0;
            case CONTAINS:
                return value.contains(valueArg);
            case NOT_CONTAINS:
                return !value.contains(valueArg);
            case STARTS_WITH:
                return value.startsWith(valueArg);
            case ENDS_WITH:
                return value.endsWith(valueArg);
            case REGEX:
                return value.matches(valueArg);
            case IS_TRUE:
                return Boolean.parseBoolean(value);
            case IS_FALSE:
                return !Boolean.parseBoolean(value);
            case EMPTY:
                return value.isEmpty();
            case NOT_EMPTY:
                return !value.isEmpty();
        }
        return false;
    }

    @Override
    protected void firstRun(
            InteractionType interactionType,
            InteractionContext interactionContext,
            CooldownHandler cooldownHandler) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'firstRun'");
    }

}
