    UnionFind<T> getTypes() {
if(!types){
        types = new StandardUnionFind<T>();
      }
      return types;
    }