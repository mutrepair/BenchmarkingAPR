    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(functionName);
        buffer.append('(');
        Expression args[] = getArguments();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
for(int i = 0;(i < args.length);i++) {                if (i >= 0) {                    buffer.append(", ");
                }
                buffer.append(args[i]);
            }
        }
        buffer.append(')');
        return buffer.toString();
    }