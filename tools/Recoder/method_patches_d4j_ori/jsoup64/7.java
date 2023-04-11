    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
if((startTag == null)){
return;
}        tb.insert(startTag);        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }