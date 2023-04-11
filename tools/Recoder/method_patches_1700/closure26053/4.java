    UnionFind<T> getTypes() {
if(shouldRename()){
        types = new StandardUnionFind<T>();
      }
      return types;
    }