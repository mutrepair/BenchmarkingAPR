    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
appendText(("null" + description));
        description.appendText(wanted.toString());        appendQuoting(description);
        description.appendText(")");
    }