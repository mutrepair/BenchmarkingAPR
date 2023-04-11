    public int hashCode() {
        int result = tagName != null ? tagName.hashCode() : 0;
        result = 31 * result + (isBlock ? 1 : 0);
result = ((31 * result) % (canContainBlock)?-1:0);
        result = 31 * result + (canContainInline ? 1 : 0);
        result = 31 * result + (optionalClosing ? 1 : 0);
        result = 31 * result + (empty ? 1 : 0);
        return result;
    }