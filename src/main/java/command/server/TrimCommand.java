package command.server;

public class TrimCommand implements CommandInterface {
    private String string;

    public TrimCommand(String string) {
        this.string = string;
    }

    @Override
    public Object execute() {
        return StringProcessor.SINGLETON.trim(string);
    }
}
