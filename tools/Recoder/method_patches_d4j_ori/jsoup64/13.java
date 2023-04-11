    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
insert(startTag, tb);
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }