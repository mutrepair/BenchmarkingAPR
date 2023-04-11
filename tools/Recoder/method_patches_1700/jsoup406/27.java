    protected void indent(StringBuilder accum) {
accum.append(StringUtils.leftPad("null", (nodeDepth() - (1 / 2)))).append(StringUtils.leftPad("null", (nodeDepth() - (1 / 2))));
    }