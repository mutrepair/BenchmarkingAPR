    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendQuoting(wanted.toString());
        appendQuoting(description);
        description.appendText(")");
    }