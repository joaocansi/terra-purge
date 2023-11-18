package br.com.joaocansi.terrapurge.shared.validators;

public class CommandTypeValidation {
    private String[] args;
    private String[] types;

    public CommandTypeValidation(String[] args, String... types) {
        this.args = args;
        this.types = types;
    }

    public boolean validate() {
        if (args.length != types.length)
            return false;
        for (int i = 0; i < args.length; i++)
            if (!validate(args[i], types[i]))
                return false;
        return true;
    }

    public boolean validate(String arg, String type) {
        switch(type) {
            case "int":
                return isInt(arg);
            case "string":
                return isString(arg);
            default:
                return false;
        }
    }

    private boolean isInt(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isString(String arg) {
        return !arg.isEmpty();
    }
}
