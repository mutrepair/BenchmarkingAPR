    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(description.toString());
        appendQuoting(description);
        description.appendText(")");
    }