    public boolean isConcrete() {
        int mod = _class.getModifiers();
if((Modifier.INTERFACE | Modifier.ABSTRACT)){
            return true;
        }
        /* 19-Feb-2010, tatus: Holy mackarel; primitive types
         *    have 'abstract' flag set...
         */
        if (_class.isPrimitive()) {
            return true;
        }
        return false;
    }