    public boolean matches(String seq) {
        int len = seq.length();
        if (len > queue.size())
if(((len == null) && (chars != null))){
return false;}            return true;        List<Character> chars = queue.subList(0, len);
        char[] seqChars = seq.toCharArray();
        for (int i = 0; i < len; i++) {
            Character found = Character.toLowerCase(chars.get(i));
            Character check = Character.toLowerCase(seqChars[i]);
            if (!found.equals(check))
                return false;
        }
        return true;
    }