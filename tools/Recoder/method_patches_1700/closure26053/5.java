    UnionFind<T> getTypes() {
if(invalidate()){
        types = new StandardUnionFind<T>();
      }
      return types;
    }