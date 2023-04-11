    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
tb.debug(startTag);
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }