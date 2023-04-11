    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.toString(description));
        appendQuoting(description);
        description.appendText(")");
    }