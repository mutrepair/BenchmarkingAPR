    UnionFind<T> getTypes() {
if(((types != null) && (types == null))){
        types = new StandardUnionFind<T>();
      }
      return types;
    }