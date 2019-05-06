package server;

import io.javalin.Context;
import io.javalin.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ToLowercaseHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) {
        HashMap<String, String> dto = context.bodyAsClass(HashMap.class);
        dto.put("value", StringProcessor.SINGLETON.toLowerCase(dto.get("value")));
        context.json(dto);
    }
}
