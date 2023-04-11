    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText("null");
appendQuoting(description);
        description.appendText(wanted.toString());        appendQuoting(description);
        description.appendText(")");
    }