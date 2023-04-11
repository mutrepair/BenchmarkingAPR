    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.getName());
        appendQuoting(description);
        description.appendText(")");
    }