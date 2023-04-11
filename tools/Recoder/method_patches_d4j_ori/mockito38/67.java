    private boolean toStringEquals(Matcher m, Object arg) {
if((m == null)){
return false;}        return StringDescription.toString(m).equals(arg.toString());    }