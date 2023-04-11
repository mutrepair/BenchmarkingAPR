    public Object computeValue(EvalContext context) {
if((context == null)){
return null;}        return equal(context, args[1], args[1]) ? Boolean.TRUE : Boolean.FALSE;    }