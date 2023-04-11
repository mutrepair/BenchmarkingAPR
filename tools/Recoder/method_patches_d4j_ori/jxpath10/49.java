    public final Object computeValue(EvalContext context) {
return ((args == null) && compute(args[0].computeValue(context), args[1].computeValue(context)));    }