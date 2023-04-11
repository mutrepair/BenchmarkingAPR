    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.());
        appendQuoting(description);
        description.appendText(")");
    }