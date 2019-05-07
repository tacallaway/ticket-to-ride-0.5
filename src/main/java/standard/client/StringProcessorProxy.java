package standard.client;

import standard.common.StringProcessorInterface;

public class StringProcessorProxy implements StringProcessorInterface {

    public static StringProcessorProxy SINGLETON = new StringProcessorProxy();

    private StringProcessorProxy() {}
    
    @Override
    public String toLowerCase(String string) {
        return ClientCommunicator.SINGLETON.toLowerCase(string);
    }

    @Override
    public String trim(String string) {
        return ClientCommunicator.SINGLETON.trim(string);
    }

    @Override
    public int parseInteger(String string) throws NumberFormatException {
        return ClientCommunicator.SINGLETON.parseInteger(string);
    }
}
