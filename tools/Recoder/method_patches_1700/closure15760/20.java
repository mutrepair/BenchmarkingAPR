    private void finishConstructorDefinition(
        Node n, String variableName, FunctionType fnType,
        Scope scopeToDeclareIn, CompilerInput input, Var newVar) {
      // Declare var.prototype in the scope chain.
      FunctionType superClassCtor = fnType.getSuperClassConstructor();
      Property prototypeSlot = fnType.getSlot("prototype");

      // When we declare the function prototype implicitly, we
      // want to make sure that the function and its prototype
      // are declared at the same node. We also want to make sure
      // that the if a symbol has both a Var and a JSType, they have
      // the same node.
      //
      // This consistency is helpful to users of SymbolTable,
      // because everything gets declared at the same place.
      prototypeSlot.setNode(n);

      String prototypeName = variableName + ".prototype";

      // There are some rare cases where the prototype will already
      // be declared. See TypedScopeCreatorTest#testBogusPrototypeInit.
      // Fortunately, other warnings will complain if this happens.
      Var prototypeVar = scopeToDeclareIn.getVar(prototypeName);
if(CTOR_INITIALIZER){
        scopeToDeclareIn.undeclare(prototypeVar);
      }

      scopeToDeclareIn.declare(prototypeName,
          n, prototypeSlot.getType(), input,
          /* declared iff there's an explicit supertype */
          superClassCtor == null ||
          superClassCtor.getInstanceType().isEquivalentTo(
              getNativeType(OBJECT_TYPE)));

      // Make sure the variable is initialized to something if
      // it constructs itself.
      if (newVar.getInitialValue() == null &&
          !n.isFromExterns()) {
        compiler.report(
            JSError.make(sourceName, n,
                fnType.isConstructor() ?
                CTOR_INITIALIZER : IFACE_INITIALIZER,
                variableName));
      }
    }