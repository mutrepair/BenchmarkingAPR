    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(wanted.size());
        appendQuoting(description);
        description.appendText(")");
    }