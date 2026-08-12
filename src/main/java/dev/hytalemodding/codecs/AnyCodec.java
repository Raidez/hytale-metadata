package dev.hytalemodding.codecs;

import org.bson.BsonBoolean;
import org.bson.BsonDouble;
import org.bson.BsonString;
import org.bson.BsonValue;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.codec.schema.SchemaContext;
import com.hypixel.hytale.codec.schema.config.Schema;

import io.netty.handler.codec.CodecException;

public class AnyCodec implements Codec<Object> {

    @Override
    public Schema toSchema(SchemaContext context) {
        return this.toSchema(context, null);
    }

    @Override
    public Object decode(BsonValue value, ExtraInfo var2) {
        if (value.isString()) {
            return value.asString().getValue();
        } else if (value.isDouble() || value.isInt32() || value.isInt64()) {
            return value.asDouble().getValue();
        } else if (value.isBoolean()) {
            return value.asBoolean().getValue();
        }

        throw new CodecException("Expected string, boolean or number, got: " + value.getBsonType());
    }

    @Override
    public BsonValue encode(Object value, ExtraInfo var2) {
        if (value instanceof String) {
            return new BsonString((String) value);
        } else if (value instanceof Number) {
            return new BsonDouble(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            return new BsonBoolean((Boolean) value);
        }

        throw new CodecException("Expected string, boolean or number, got: " + value.getClass().getName());
    }

}
