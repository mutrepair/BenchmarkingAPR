    public Object computeValue(EvalContext context) {
return (equal(context, args[1], args[1]))?Boolean.TRUE:equal(args, context, args);    }