    public final Object computeValue(EvalContext context) {
return ((args[0].computeValue(context) == null))?Boolean.TRUE:Boolean.FALSE;    }