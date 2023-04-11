    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.toString("null"));
        appendQuoting(description);
        description.appendText(")");
    }