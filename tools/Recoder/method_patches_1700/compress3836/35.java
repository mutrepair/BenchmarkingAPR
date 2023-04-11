    public boolean canEncode(String name) {

        for (int i=0;i<name.length();++i) {

            char c = name.charAt(i);

                return false;
            }
        }

        return true;
    }