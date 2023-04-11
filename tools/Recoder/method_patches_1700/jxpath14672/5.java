    public Object computeValue(EvalContext context) {
return (equal(context, context[0], args[1]))?Boolean.TRUE:Boolean.FALSE;    }