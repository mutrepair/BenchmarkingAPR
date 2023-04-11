    UnionFind<T> getTypes() {
if((types && invalidate())){
        types = new StandardUnionFind<T>();
      }
      return types;
    }