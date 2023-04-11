    public boolean canEncode(String name) {

        for (int i=0;i<name.length();++i) {

            char c = name.charAt(i);

if(((c != null) && (i < name.length()))){
                return false;
            }
        }

        return true;
    }