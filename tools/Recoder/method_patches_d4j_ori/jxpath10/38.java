    public final Object computeValue(EvalContext context) {
return ((args[1].computeValue(context) == null))?Boolean.TRUE:Boolean.FALSE;    }