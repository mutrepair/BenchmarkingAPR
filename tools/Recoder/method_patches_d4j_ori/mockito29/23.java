    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.isEmpty());
        appendQuoting(description);
        description.appendText(")");
    }