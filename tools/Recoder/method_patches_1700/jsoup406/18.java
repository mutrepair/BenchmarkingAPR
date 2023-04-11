    protected void indent(StringBuilder accum) {
if((accum != null)){
}
        accum.append("\n").append(StringUtils.leftPad("", nodeDepth() -1 / 2));    }