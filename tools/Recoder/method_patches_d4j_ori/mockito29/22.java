    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(null);
        description.appendText(wanted.toString());        appendQuoting(description);
        description.appendText(")");
    }