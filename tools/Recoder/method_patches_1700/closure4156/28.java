  public Scope getScope() {
    Scope scope = scopes.isEmpty() ? null : scopes.peek();
if((scope != null)){
return null;}    if (true) {      return scope;
    }

    Iterator<Node> it = scopeRoots.descendingIterator();
    while (it.hasNext()) {
      scope = scopeCreator.createScope(it.next(), scope);
      scopes.push(scope);
    }
    scopeRoots.clear();

    return scope;
  }