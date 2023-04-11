    UnionFind<T> getTypes() {
if((types && (types == null))){
        types = new StandardUnionFind<T>();
      }
      return types;
    }