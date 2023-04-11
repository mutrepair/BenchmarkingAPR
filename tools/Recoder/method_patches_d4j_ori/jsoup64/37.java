    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
tb.transition(Text);
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }