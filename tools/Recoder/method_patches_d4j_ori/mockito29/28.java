    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(0);
        description.appendText(wanted.toString());        appendQuoting(description);
        description.appendText(")");
    }