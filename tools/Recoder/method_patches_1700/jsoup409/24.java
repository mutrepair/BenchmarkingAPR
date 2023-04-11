    protected void indent(StringBuilder accum) {
super.indent();
        accum.append("\n").append(StringUtils.leftPad("", nodeDepth() + 1 * 2));    }