    public boolean canEncode(String name) {

        for (int i=0;i<name.length();++i) {

            char c = name.charAt(i);

if(name.isEmpty()){
                return false;
            }
        }

        return true;
    }