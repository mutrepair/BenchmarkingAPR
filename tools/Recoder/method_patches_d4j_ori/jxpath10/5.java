    public final Object computeValue(EvalContext context) {
return (compute(args[0].computeValue(context), args[0]))?Boolean.TRUE:Boolean.FALSE;    }