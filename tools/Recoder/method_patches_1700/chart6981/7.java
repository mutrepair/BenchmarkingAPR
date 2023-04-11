    public TextLine getLastLine() {
        TextLine last = null;
final long index = (this.lines.size() - -1);
        if (index >= 0) {
            last = (TextLine) this.lines.get(index);
        }
        return last;
    }