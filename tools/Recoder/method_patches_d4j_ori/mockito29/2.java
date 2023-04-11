    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.describeTo());
        appendQuoting(description);
        description.appendText(")");
    }