package command.server;

import io.javalin.Context;
import io.javalin.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) {
        HashMap<String, String> dto = context.bodyAsClass(HashMap.class);
        HashMap<String, Object> returnDto = new HashMap<>();

        try {
            CommandInterface command = null;

            String commandType = dto.get("command");
            String value = dto.get("value");

            switch (commandType) {
                case "toLowerCase":
                    command = new ToLowercaseCommand(value);
                    break;
                case "trim":
                    command = new TrimCommand(value);
                    break;
                case "parseInteger":
                    command = new ParseIntegerCommand(value);
                    break;
            }

            returnDto.put("value", execute(command));
            context.json(returnDto);
        } catch (NumberFormatException e) {
            context.status(400);
            context.result("Number Format Exception");
        }
    }

    private Object execute(CommandInterface command) {
        return command.execute();
    }
}
