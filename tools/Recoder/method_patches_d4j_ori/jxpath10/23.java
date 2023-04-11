    public final Object computeValue(EvalContext context) {
return (compute(args[0].get(context), args[1].computeValue(context)))?Boolean.TRUE:Boolean.FALSE;    }