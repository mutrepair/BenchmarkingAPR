    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
.appendText(wanted.toString());
        appendQuoting(description);
        description.appendText(")");
    }