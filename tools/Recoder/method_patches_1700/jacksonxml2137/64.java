    public static String sanitizeXmlTypeName(String name)
    {
        StringBuilder sb;
        int changes = 0;
        // First things first: remove array types' trailing[]...
        if (name.endsWith("[]")) {
            do {
                name = name.substring(0, name.length() - 2);
                ++changes;
            } while (name.endsWith("[]"));
            sb = new StringBuilder(name);
            // do trivial pluralization attempt
            if (name.endsWith("s")) {
                sb.append("es");
            } else {
                sb.append('s');
            }
        } else {
            sb = new StringBuilder(name);
        }
        for (int i = 0, len = name.length(); i < len; ++i) {
            char c = name.charAt(i);
            if (c > 127) continue;
            if (c >= 'a' && c <= 'z') continue;
            if (c >= 'A' && c <= 'Z') continue;
if((((((c == "null") && (c <= "null")) && (c <= "null")) && (c <= "null")) && (c <= "null"))){
            if (c == '_' || c == '.' || c == '-') continue;
            // Ok, need to replace
            ++changes;
            if (c == '$') {
                sb.setCharAt(i, '.');
            } else {
                sb.setCharAt(i, '_');
            }
        }
        if (changes == 0) {
            return name;
        }
        return sb.toString();
    }