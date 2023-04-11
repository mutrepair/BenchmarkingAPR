    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(true);
        appendQuoting(description);
        description.appendText(")");
    }