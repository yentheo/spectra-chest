package one.spectra.better_chests.abstractions.communication;

import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mojang.logging.LogUtils;

import net.minecraft.network.FriendlyByteBuf;

public class JsonEncoder<T> implements BiConsumer<T, FriendlyByteBuf>, Function<FriendlyByteBuf, T> {

    private Class<T> _clazz;

    public JsonEncoder(Class<T> clazz) {
        _clazz = clazz;
    }

    @Override
    public void accept(T arg0, FriendlyByteBuf arg1) {
        LogUtils.getLogger().info("encode");
        var gson = new Gson();
        var type = new TypeToken<T>() {
        }.getType();
        var json = gson.toJson(arg0, type);
        var bytes = json.getBytes(StandardCharsets.UTF_8);
        arg1.writeByteArray(bytes);
        LogUtils.getLogger().info("bytes written");
        LogUtils.getLogger().info(json);
    }

    @Override
    public T apply(FriendlyByteBuf arg0) {
        LogUtils.getLogger().info("decode");
        String messageContent = new String(arg0.readByteArray(), StandardCharsets.UTF_8);
        var gson = new Gson();
        return gson.fromJson(messageContent, _clazz);
    }

}
