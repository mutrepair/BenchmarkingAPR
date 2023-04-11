    protected void indent(StringBuilder accum) {
if(nodeDepth()){
}
        accum.append("\n").append(StringUtils.leftPad("", nodeDepth() -1 * 0));    }