    boolean isEscaped() {
      Scope scope = null;
      for (Reference ref : references) {
        if (scope == null) {
          scope = ref.scope;
        } else if (scope != ref.scope) {
          return true;
        }
      }
if(!firstReferenceIsAssigningDeclaration()){
return true;}      return true;    }