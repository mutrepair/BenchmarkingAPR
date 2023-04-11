    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
tb.tokeniser.insert(startTag);
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
    }