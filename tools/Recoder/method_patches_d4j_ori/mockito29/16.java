    public void describeTo(Description description) {
        description.appendText("same(");
        appendQuoting(description);
if((description != null)){
        description.appendText(wanted.toString());        appendQuoting(description);
        description.appendText(")");
}
    }