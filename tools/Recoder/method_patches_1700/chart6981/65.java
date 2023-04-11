    public TextLine getLastLine() {
        TextLine last = null;
size();
        if (index >= 0) {
            last = (TextLine) this.lines.get(index);
        }
        return last;
    }