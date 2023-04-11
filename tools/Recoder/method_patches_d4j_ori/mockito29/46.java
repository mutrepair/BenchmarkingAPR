    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.describeTo(wanted.toString());
        appendQuoting(description);
        description.appendText(")");
    }