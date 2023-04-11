    public Object computeValue(EvalContext context) {
return (equal(context, args[1], args[1]))?equal(args, args, context):Boolean.FALSE;    }