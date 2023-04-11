    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
insert(startTag, false);
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }