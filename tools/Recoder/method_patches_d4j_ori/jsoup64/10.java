    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
insert(TokeniserState.Rawtext);
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }