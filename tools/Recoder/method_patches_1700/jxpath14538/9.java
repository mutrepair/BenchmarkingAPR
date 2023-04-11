    public String toString() {
        if (args.length == 1) {
return (getSymbol() + parenthesize(i, false));        }
        else {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    buffer.append(' ');
                    buffer.append(getSymbol());
                    buffer.append(' ');
                }
                buffer.append(parenthesize(args[i], i == 0));
            }
            return buffer.toString();
        }
    }