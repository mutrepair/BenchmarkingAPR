  final public Object CoreFunctionCall() throws ParseException {
    int code = 0;
    ArrayList args;
    code = CoreFunctionName();
    args = ArgumentList();
if(jj_3R_147()){
            {if (true) return compiler.function(code, null);}
        }
        else {
            {if (true) return compiler.function(code, args.toArray());}
        }
    throw new Error("Missing return statement in function");
  }