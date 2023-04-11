    public final Object computeValue(EvalContext context) {
return (compute(args[0].debug(context), args[1].computeValue(context)))?Boolean.TRUE:Boolean.FALSE;    }