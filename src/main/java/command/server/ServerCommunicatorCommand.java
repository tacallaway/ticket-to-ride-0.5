package command.server;

import io.javalin.Javalin;

public class ServerCommunicatorCommand {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7001);
        app.post("/execute", new CommandHandler());
    }
}