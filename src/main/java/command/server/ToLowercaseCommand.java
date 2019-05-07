package command.server;

public class ToLowercaseCommand implements CommandInterface {
    private String string;

    public ToLowercaseCommand(String string) {
        this.string = string;
    }

    @Override
    public Object execute() {
        return StringProcessor.SINGLETON.toLowerCase(string);
    }
}
