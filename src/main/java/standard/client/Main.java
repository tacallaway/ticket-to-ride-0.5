package standard.client;

public class Main {
    public static void main(String[] args) {

        if (args.length < 2) {
            showUsage();
        }

        switch(args[0]) {
            case "toLowerCase":
                System.out.println(StringProcessorProxy.SINGLETON.toLowerCase(args[1]));
                break;
            case "trim":
                System.out.println(StringProcessorProxy.SINGLETON.trim(args[1]));
                break;
            case "parseInteger":
                System.out.println(StringProcessorProxy.SINGLETON.parseInteger(args[1]));
                break;
            default:
                showUsage();
        }
    }

    private static void showUsage() {
        System.out.println("Usage: java standard.client.MainCommand <function> <string>");
        System.out.println("Functions:");
        System.out.println("    toLowerCase - force string to lowercase");
        System.out.println("    trim - trim string");
        System.out.println("    parseInteger - parse string to integer");
        System.exit(0);
    }
}
