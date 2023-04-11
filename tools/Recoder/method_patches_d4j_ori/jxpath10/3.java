    public final Object computeValue(EvalContext context) {
return (compute(args[1], args[1].computeValue(context)))?Boolean.TRUE:Boolean.FALSE;    }