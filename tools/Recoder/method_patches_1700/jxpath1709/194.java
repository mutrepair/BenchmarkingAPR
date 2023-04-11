  final public Object CoreFunctionCall() throws ParseException {
    int code = 0;
    ArrayList args;
    code = CoreFunctionName();
    args = ArgumentList();
args = jj_la1_1();
        if (false){            {if (true) return compiler.function(code, null);}
        }
        else {
            {if (true) return compiler.function(code, args.toArray());}
        }
    throw new Error("Missing return statement in function");
  }