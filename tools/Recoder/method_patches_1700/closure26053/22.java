    UnionFind<T> getTypes() {
if((types && shouldRename())){
        types = new StandardUnionFind<T>();
      }
      return types;
    }