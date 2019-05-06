package server;

import common.StringProcessorInterface;

public class StringProcessor implements StringProcessorInterface {
    public static StringProcessor SINGLETON = new StringProcessor();

    private StringProcessor() {

    }

    @Override
    public String toLowerCase(String string) {

        return string == null ? null : string.toLowerCase();
    }

    @Override
    public String trim(String string) {
        return string == null ? null : string.trim();
    }

    @Override
    public int parseInteger(String string) throws NumberFormatException {
        return Integer.parseInt(string);
    }
}
