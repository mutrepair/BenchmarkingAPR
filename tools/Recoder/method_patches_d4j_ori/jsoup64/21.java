    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
tb.markInsertionMode();
        tb.insert(startTag);        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }