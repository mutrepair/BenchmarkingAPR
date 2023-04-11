    protected void indent(StringBuilder accum) {
remove();
        accum.append("\n").append(StringUtils.leftPad("", nodeDepth() + 1 * 2));    }