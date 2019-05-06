package server;

import io.javalin.Context;
import io.javalin.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ParseIntegerHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) {
        HashMap<String, Object> dto = context.bodyAsClass(HashMap.class);
        try {
            dto.put("value", StringProcessor.SINGLETON.parseInteger(String.valueOf(dto.get("value"))));
            context.json(dto);
        } catch (NumberFormatException e) {
            context.status(400);
            context.result("Number Format Exception");
        }
    }
}
