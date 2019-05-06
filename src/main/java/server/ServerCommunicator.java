package server;

import io.javalin.Javalin;

public class ServerCommunicator {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.post("/to-lower-case", new ToLowercaseHandler());
        app.post("/trim", new TrimHandler());
        app.post("/parse-integer", new ParseIntegerHandler());
    }
}