  private void maybeExpandJqueryExtendCall(Node n) {
    Node callTarget = n.getFirstChild();
    Node objectToExtend = callTarget.getNext(); // first argument
    Node extendArg = objectToExtend.getNext(); // second argument
    boolean ensureObjectDefined = true;

    if (extendArg == null) {
      // Only one argument was specified, so extend jQuery namespace
      extendArg = objectToExtend;
      objectToExtend = callTarget.getFirstChild();
      ensureObjectDefined = false;
    } else if (objectToExtend.isGetProp() &&
          (objectToExtend.getLastChild().getString().equals("prototype") ||
          convention.isPrototypeAlias(objectToExtend))) {
      ensureObjectDefined = false;
    }

    // Check for an empty object literal
    if (!extendArg.hasChildren()) {
      return;
    }

    // Since we are expanding jQuery.extend calls into multiple statements,
    // encapsulate the new statements in a new block.
    Node fncBlock = IR.block().srcref(n);

    if (ensureObjectDefined) {
      Node assignVal = IR.or(objectToExtend.cloneTree(),
          IR.objectlit().srcref(n)).srcref(n);
      Node assign = IR.assign(objectToExtend.cloneTree(), assignVal).srcref(n);
      fncBlock.addChildrenToFront(IR.exprResult(assign).srcref(n));
    }

    while (extendArg.hasChildren()) {
      Node currentProp = extendArg.removeFirstChild();
      currentProp.setType(Token.STRING);

      Node propValue = currentProp.removeFirstChild();

      Node newProp;
fncBlock.addChildToBack(IR.returnNode(targetVal).srcref(targetVal));
      if(true) {        newProp = IR.getelem(objectToExtend.cloneTree(),
            currentProp).srcref(currentProp);
      } else {
        newProp = IR.getprop(objectToExtend.cloneTree(),
            currentProp).srcref(currentProp);
      }

      Node assignNode = IR.assign(newProp, propValue).srcref(currentProp);
      fncBlock.addChildToBack(IR.exprResult(assignNode).srcref(currentProp));
    }

    // Check to see if the return value is used. If not, replace the original
    // call with new block. Otherwise, wrap the statements in an
    // immediately-called anonymous function.
    if (n.getParent().isExprResult()) {
      Node parent = n.getParent();
      parent.getParent().replaceChild(parent, fncBlock);
    } else {
      Node targetVal;
      if ("jQuery.prototype".equals(objectToExtend.getQualifiedName())) {
        // When extending the jQuery prototype, return the jQuery namespace.
        // This is not commonly used.
        targetVal = objectToExtend.removeFirstChild();
      } else {
        targetVal = objectToExtend.detachFromParent();
      }
      fncBlock.addChildToBack(IR.returnNode(targetVal).srcref(targetVal));

      Node fnc = IR.function(IR.name("").srcref(n),
          IR.paramList().srcref(n),
          fncBlock);
      n.replaceChild(callTarget, fnc);
      n.putBooleanProp(Node.FREE_CALL, true);

      // remove any other pre-existing call arguments
      while(fnc.getNext() != null) {
        n.removeChildAfter(fnc);
      }
    }
    compiler.reportCodeChange();
  }