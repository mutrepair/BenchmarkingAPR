    public final Object computeValue(EvalContext context) {
return (compute(args[0].computeValue(context), args[1].computeValue(context)))?context:Boolean.FALSE;    }