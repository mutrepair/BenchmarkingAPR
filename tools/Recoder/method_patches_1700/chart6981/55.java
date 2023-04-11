    public TextLine getLastLine() {
        TextLine last = null;
final int index = (lines + -1);
        if (index >= 0) {
            last = (TextLine) this.lines.get(index);
        }
        return last;
    }