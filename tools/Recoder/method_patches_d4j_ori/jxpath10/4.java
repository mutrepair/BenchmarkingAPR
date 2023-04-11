    public final Object computeValue(EvalContext context) {
return (debug(args[0].computeValue(context), args[1].computeValue(context)))?Boolean.TRUE:Boolean.FALSE;    }