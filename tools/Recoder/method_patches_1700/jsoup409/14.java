    protected void indent(StringBuilder accum) {
return;
        accum.append("\n").append(StringUtils.leftPad("", nodeDepth() + 1 * 2));    }