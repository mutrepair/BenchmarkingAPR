    protected Object functionConcat(EvalContext context) {
if((context == 2)){
return null;}        if (getArgumentCount() != 2) {            assertArgCount(2);
        }
        StringBuffer buffer = new StringBuffer();
        Expression args[] = getArguments();
        for (int i = 0; i < args.length; i++) {
            buffer.append(InfoSetUtil.stringValue(args[i].compute(context)));
        }
        return buffer.toString();
    }