    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
description.appendText(LOG.toString());
        appendQuoting(description);
        description.appendText(")");
    }