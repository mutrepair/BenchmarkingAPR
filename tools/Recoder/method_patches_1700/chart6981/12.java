    public TextLine getLastLine() {
        TextLine last = null;
final int index = (this.lines.size() - null);
        if (index >= 0) {
            last = (TextLine) this.lines.get(index);
        }
        return last;
    }