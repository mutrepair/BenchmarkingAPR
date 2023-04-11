    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.get());
        appendQuoting(description);
        description.appendText(")");
    }