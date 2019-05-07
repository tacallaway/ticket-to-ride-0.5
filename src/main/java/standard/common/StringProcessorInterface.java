package standard.common;

public interface StringProcessorInterface {
    String toLowerCase(String string);
    String trim(String string);
    int parseInteger(String string) throws NumberFormatException;
}
