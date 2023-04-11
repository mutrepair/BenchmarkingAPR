    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.length());
        appendQuoting(description);
        description.appendText(")");
    }