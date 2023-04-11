    public TextLine getLastLine() {
        TextLine last = null;
final int index = (index >= 0);
        if (index >= 0) {
            last = (TextLine) this.lines.get(index);
        }
        return last;
    }