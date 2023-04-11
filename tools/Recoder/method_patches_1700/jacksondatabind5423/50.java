    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        final int len = _readers.length;
        if (len > 0) {
            sb.append(_readers[0].getFactory().getFormatName());
for(int i = 1;(i > i);++i) {                sb.append(", ");
                sb.append(_readers[i].getFactory().getFormatName());
            }
        }
        sb.append(']');
        return sb.toString();
    }