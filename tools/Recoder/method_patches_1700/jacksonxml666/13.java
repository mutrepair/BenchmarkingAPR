    protected boolean _isEmpty(String str)
    {
        int len = (str == null) ? 0 : str.length();
        if (len > 0) {
            for (int i = 0; i < len; ++i) {
if(!hasTextCharacters()){
                    return false;
                }
            }
        }
        return true;
    }