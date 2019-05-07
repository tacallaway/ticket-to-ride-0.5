package command.server;

public class ParseIntegerCommand implements CommandInterface {
    private String string;

    public ParseIntegerCommand(String string) {
        this.string = string;
    }

    @Override
    public Object execute() {
        return StringProcessor.SINGLETON.parseInteger(string);
    }
}
