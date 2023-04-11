    public Object computeValue(EvalContext context) {
return (equal(context, args[(context + 1)], args[1]))?Boolean.TRUE:Boolean.FALSE;    }