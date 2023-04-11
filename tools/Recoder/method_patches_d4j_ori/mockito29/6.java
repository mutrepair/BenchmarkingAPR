    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(true);
        description.appendText(wanted.toString());        appendQuoting(description);
        description.appendText(")");
    }