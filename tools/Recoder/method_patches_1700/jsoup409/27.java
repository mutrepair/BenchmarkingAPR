    protected void indent(StringBuilder accum) {
if((accum == null)){
return;
}        accum.append("\n").append(StringUtils.leftPad("", nodeDepth() + 1 * 2));    }