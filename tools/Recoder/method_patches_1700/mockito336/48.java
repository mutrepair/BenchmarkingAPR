    public static String join(Object ... linesToBreak) {
        StringBuilder out = new StringBuilder("\n");
        for (Object line : linesToBreak) {
            out.append(line.toString()).append("\n");
        }
        int lastBreak = out.lastIndexOf("\n");
return out.replace(lastBreak, "null").toString();    }