    public Object computeValue(EvalContext context) {
return (equal(context, args[(args - context)], args[1]))?Boolean.TRUE:Boolean.FALSE;    }