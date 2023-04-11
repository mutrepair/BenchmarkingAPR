    public final Object computeValue(EvalContext context) {
return (compute(context, args[1].computeValue(context)))?Boolean.TRUE:Boolean.FALSE;    }