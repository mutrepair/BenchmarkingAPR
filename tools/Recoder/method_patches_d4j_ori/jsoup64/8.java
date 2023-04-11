    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
tb.handleRawtext(startTag);
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }