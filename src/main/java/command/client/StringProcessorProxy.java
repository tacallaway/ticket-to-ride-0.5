package command.client;

import command.common.StringProcessorInterface;

public class StringProcessorProxy implements StringProcessorInterface {

    public static StringProcessorProxy SINGLETON = new StringProcessorProxy();

    private StringProcessorProxy() {}
    
    @Override
    public String toLowerCase(String string) {

        CommandData data = new CommandData("toLowerCase", string);
        return String.valueOf(ClientCommunicator.SINGLETON.send(data));
    }

    @Override
    public String trim(String string) {

        CommandData data = new CommandData("trim", string);
        return String.valueOf(ClientCommunicator.SINGLETON.send(data));
    }

    @Override
    public int parseInteger(String string) throws NumberFormatException {

        CommandData data = new CommandData("parseInteger", string);

        return ((Double)ClientCommunicator.SINGLETON.send(data)).intValue();
    }
}
