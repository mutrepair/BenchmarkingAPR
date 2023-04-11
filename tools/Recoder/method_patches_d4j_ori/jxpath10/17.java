    public final Object computeValue(EvalContext context) {
return (compute(args[0].computeValue(context), context))?Boolean.TRUE:Boolean.FALSE;    }